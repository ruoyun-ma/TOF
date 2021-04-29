package rs2d.sequence.gradientecho;

// ---------------------------------------------------------------------
//  
//                 GRADIENT_ECHO_dev PSD 
//  
// ---------------------------------------------------------------------
// 26/04/2021   Vx1.2 XG: abstract KernelGE is inherited; FatSat/ SatBand/ Flow/ TOF/ Elliptical Components are implemented
// 02/03/2021   V11 XG: from now on, we use SeqPrep/Gradient/RF library

import common.Gradient;
import rs2d.commons.log.Log;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.sequence.element.Opcode;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequenceGenerator.util.TimeEvents;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.Order;

import java.util.*;
import static java.util.Arrays.asList;

import common.*;
import kernel.*;
import model.*;

import static rs2d.sequence.gradientecho.S.*;

import static rs2d.sequence.gradientecho.U.*;


// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class Bold extends KernelGE {
    private String sequenceVersion = "Version x1.2";
    private boolean isElliptical;
    private double slice_thickness_excitation;

    public Bold() {
        super();
        addUserParams();
    }

    @Override
    public void init() {
        super.init();
        //TRANSFORM PLUGIN
        TextParam transformPlugin = getParam(TRANSFORM_PLUGIN);
        transformPlugin.setSuggestedValues(asList("Sequential4D", "Elliptical3D", "Sequential4D_TOF"));
        transformPlugin.setRestrictedToSuggested(true);

        // KSPACE_FILLING
        TextParam ksFilling = getParam(KSPACE_FILLING);
        ksFilling.setSuggestedValues(asList("Linear", "3DElliptic"));
        ksFilling.setRestrictedToSuggested(true);
    }

    // ==============================
// -----   GENERATE
// ==============================
    @Override
    public void initUserParam() {
        super.initUserParam();
        getParam(SEQUENCE_VERSION).setValue(sequenceVersion);

        isElliptical = getText(KSPACE_FILLING).equalsIgnoreCase("3DElliptic");
    }

    //--------------------------------------------------------------------------------------
    // ini functions for beforeRouting() and initAfterRouting()
    //--------------------------------------------------------------------------------------
    @Override
    protected void iniModels() {
        setModels(new ArrayList<>(Arrays.asList("ExtTrig", "FatSat", "TofSat", "FlowComp", "SatBand")), this);
    }

    @Override
    protected void iniTransformPlugin() throws Exception {
        ///// transform plugin
        if (isMultiplanar) {
            kspace_filling = "Linear";
            getParam(KSPACE_FILLING).setValue(kspace_filling);
            getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
        } else {
            switch (kspace_filling) {
                case "Linear":
                    getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
                    if (!isMultiplanar && nb_interleaved_slice > 1) {
                        getParam(TRANSFORM_PLUGIN).setValue("Sequential4D_TOF");
                    }
                    break;
                case "3DElliptic":
                    getParam(TRANSFORM_PLUGIN).setValue("Elliptical3D");
                    break;
                default:
                    kspace_filling = "Linear";
                    getParam(KSPACE_FILLING).setValue(kspace_filling);
                    getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
                    break;
            }
        }
        isElliptical = kspace_filling.equalsIgnoreCase("3DElliptic");

        plugin = getTransformPlugin();
        plugin.setParameters(new ArrayList<>(getUserParams()));
        plugin.getScanOrder(); //output traj. graphs for Elliptical3D plugin
    }

    @Override
    protected void iniScanLoop() {
        super.iniScanLoop();
        try {
            if (models.get("TofSat").isEnabled() && !isMultiplanar) {
                set(Loop_long, Opcode.CodeEnum.Continu);
                set(Loop_short, Opcode.CodeEnum.StoreLoopAddress);
            } else {
                set(Loop_long, Opcode.CodeEnum.StoreLoopAddress);
                set(Loop_short, Opcode.CodeEnum.Continu);
            }
        } catch (Exception e) {
            Log.warning(getClass(), "Sequence Param Missing: Loop_short; use default: Loop_long");
        }
        set(Nb_sb, ((SatBand) models.get("SatBand")).nb_satband - 1);
    }

    //--------------------------------------------------------------------------------------
    // prep functions for afterRouting()
    //--------------------------------------------------------------------------------------
    @Override
    protected void prepRFandSliceGrad() throws Exception {
        getGradRiseTime();
        // -----------------------------------------------
        // Calculation RF pulse parameters  1/4 : Pulse declaration & Fatsat Flip angle calculation
        // -----------------------------------------------
        set(Time_tx, txLength90);    // set RF pulse length to sequence
        pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset);

        double flip_angle = getDouble(FLIP_ANGLE);
        flip_angle = models.get("TofSat").isEnabled() && models.get("TofSat").getRfPulses().isSlr() ? 90 : flip_angle;
        getParam(FLIP_ANGLE).setValue(flip_angle);

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/4 : Shape
        // -----------------------------------------------
        if (hasParam(TOF3D_TX_RAMP_SLOPE) && "RAMP".equalsIgnoreCase(getText(TX_SHAPE))) {
            pulseTX.setSincGenRampSlope(getDouble(TOF3D_TX_RAMP_SLOPE));
        }
        pulseTX.setShape((getText(TX_SHAPE)), nb_shape_points, "Hamming");

        // -----------------------------------------------
        // Calculation RF pulse parameters  3/4 : RF pulse & attenuation
        // -----------------------------------------------
        if (getBoolean(TX_AMP_ATT_AUTO)) {
            if (!pulseTX.checkPower(flip_angle, observeFrequency, nucleus)) {
                notifyOutOfRangeParam(TX_LENGTH, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX.getPulseDuration();
            }
            pulseTX.prepAtt(80, getListInt(TX_ROUTE));
            pulseTX.prepTxAmp(getListInt(TX_ROUTE));
            rfPulses.add(pulseTX);
            rfPulsesTree.put(pulseTX.getPower(), pulseTX);
            getUPDisp();
        } else {
            pulseTX.setAtt(getInt(TX_ATT));
            pulseTX.setAmp(getDouble(TX_AMP));
            this.getParam(TX_AMP_90).setValue(getDouble(TX_AMP) * 90 / flip_angle);     // display 90° amplitude
            this.getParam(TX_AMP_180).setValue(getDouble(TX_AMP) * 90 / flip_angle);   // display 180° amplitude
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  4/4: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor(TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        slice_thickness_excitation = (isMultiplanar ? sliceThickness : (sliceThickness * userMatrixDimension3D));
        gradSlice = Gradient.createGradient(getSequence(), Grad_amp_slice, Time_tx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);

        if (hasParam(TOF3D_EXT_SHIRNK_FACTOR) && !isMultiplanar) {
            if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation * (100 - getDouble(TOF3D_EXT_SHIRNK_FACTOR)) / 100)) {
                slice_thickness_excitation = gradSlice.getSliceThickness() / ((100 - getDouble(TOF3D_EXT_SHIRNK_FACTOR)) / 100);
                double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
                notifyOutOfRangeParam(SLICE_THICKNESS, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
                sliceThickness = slice_thickness_min;
            }
        } else {
            if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation)) {
                slice_thickness_excitation = gradSlice.getSliceThickness();
                double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
                notifyOutOfRangeParam(SLICE_THICKNESS, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
                sliceThickness = slice_thickness_min;
            }
        }

        gradSlice.applyAmplitude();

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        if (isMultiplanar && isEnableSlice) {
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, acqMatrixDimension3D, spacingBetweenSlice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acqMatrixDimension1D * echoTrainLength, nb_interleaved_slice);
            pulseTX.setFrequencyOffset(nb_interleaved_slice != 1 ? Order.ThreeLoop : Order.Three);

        } else {
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, acqMatrixDimension4D, getDouble(SPACING_BETWEEN_SLAB) + (slice_thickness_excitation - gradSlice.getSliceThickness()), off_center_distance_3D);
            pulseTX.reoderOffsetFreq(nb_interleaved_slice);
            if (nb_interleaved_slice > 1) {
                pulseTX.setFrequencyOffset(Order.FourLoop);
            } else {
                pulseTX.setFrequencyOffset(Order.Four);
            }
        }

        // ------------------------------------------------------------------
        // calculate TX FREQUENCY offsets compensation
        // ------------------------------------------------------------------
        double grad_ratio_slice_refoc = isEnableSlice ? getDouble(SLICE_REFOCUSING_GRADIENT_RATIO) : 0.0;   // get slice refocussing ratio

        RFPulse pulseTXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep);
        pulseTXPrep.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        RFPulse pulseTXComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_comp);
        pulseTXComp.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);
    }

    //--------------------------------------------------------------------------------------
    // get functions
    //--------------------------------------------------------------------------------------
    @Override
    protected void getAcq3D() {
        super.getAcq3D();

        if (!isMultiplanar) {
            getParam(SPACING_BETWEEN_SLAB).setValue(userMatrixDimension4D > 1 ? -getDouble(TOF3D_MOTSA_OVERLAP) / 100 * sliceThickness * userMatrixDimension3D : 0);
        } else {
            if (models.get("TofSat").isEnabled()) {
                nb_shoot_3d = acqMatrixDimension3D; // TOF does not allow interleaved slice within the TR
                nb_interleaved_slice = (int) Math.ceil((acqMatrixDimension3D / (double) nb_shoot_3d));
                getParam(NUMBER_OF_SHOOT_3D).setValue(nb_shoot_3d);
                getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(isMultiplanar ? nb_interleaved_slice : 0);
            }
        }
    }

    @Override
    protected void getRx() {
        super.getRx();
        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID - 1, Events.Acq.ID - 1) + observation_time / 2.0;
        double timeADC2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID + 1, Events.Delay2.ID) + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_prep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_comp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);
    }

    @Override
    protected void getPrephaseGrad() {
        super.getPrephaseGrad();

        if (isEnableRead && models.get("FlowComp").isEnabled()) {
            gradReadPrep.refocalizeGradientWithFlowComp(gradReadout, getDouble(PREPHASING_READ_GRADIENT_RATIO), ((FlowComp) models.get("FlowComp")).gradReadPrepFlowComp);
            gradSliceRefPhase3D.refocalizeGradientWithFlowComp(gradSlice, getDouble(SLICE_REFOCUSING_GRADIENT_RATIO), ((FlowComp) models.get("FlowComp")).gradSliceRefPhase3DFlowComp);
        }

        if (!isMultiplanar && isEnablePhase3D) {
            gradSliceRefPhase3D.preparePhaseEncodingForCheck(acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            Log.info(getClass(), " flow comp not supported for PE dir");

            if (isElliptical) {
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin);
            } else {
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin, acqMatrixDimension3D);
            }
        }
    }

    @Override
    protected void getPEGrad() {
        super.getPEGrad();
        if (isEnablePhase) {
            Log.info(getClass(), " flow comp not suported ");
            if (isElliptical) {
                gradPhase2D.reoderPhaseEncoding(plugin);
            }
        }
    }

    @Override
    protected void getGradOpt() {
        super.getGradOpt();
        if (isElliptical) {
            gradSliceRefPhase3D.applyAmplitude(Order.Two);
        }
    }

    @Override
    protected void getTimeandDelay() throws Exception {

        Events.checkEventShortcut(getSequence());
        // ------------------------------------------
        // delays for FIR
        // ------------------------------------------
        boolean is_FIR = Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint();
        double lo_FIR_dead_point = is_FIR ? Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount() : 0;
        double min_FIR_delay = (lo_FIR_dead_point + 2) / spectralWidth;
        double min_FIR_4pts_delay = 4 / spectralWidth;

        // ------------------------------------------
        // calculate delays adapted to current TE & search for incoherence
        // ------------------------------------------
        // calculate actual delays between Rf-pulses and ADC
        double time1 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.P90.ID + 1, Events.Acq.ID - 1);
        time1 = time1 + txLength90 / 2 + observation_time / 2;// Actual_TE
        time1 -= TimeEvents.getTimeForEvents(getSequence(), Events.Delay1.ID); // Actual_TE without delay1

        // get minimal TE value & search for incoherence
        double max_time = ceilToSubDecimal(time1, 5);
        double te_min = max_time + minInstructionDelay;
        if (te < te_min) {
            te_min = ceilToSubDecimal(te_min, 5);
            notifyOutOfRangeParam(ECHO_TIME, te_min, ((NumberParam) getParam(ECHO_TIME)).getMaxValue(), "TE too short for the User Mx1D and SW");
            te = te_min;//
        }

        // set calculated the time delays to get the proper TE
        double delay1 = te - time1;

        double effectiveEchoSpacing = getDouble(INTERLEAVED_EFF_ECHO_SPACING);
        Table time_TE_delay1 = setSequenceTableValues(Time_TE_delay1, is_interleaved_echo_train ? Order.Four : Order.FourLoop);
        if (is_interleaved_echo_train) {
            if (echoTrainLength != 1) {
                echo_spacing = effectiveEchoSpacing * nb_InterleavedEchoTrain;
                getParam(ECHO_SPACING).setValue(echo_spacing);
            }

            for (int ind4D = 0; ind4D < nb_InterleavedEchoTrain; ind4D++) {
                time_TE_delay1.add(delay1 + ind4D * effectiveEchoSpacing);
            }

        } else {
            time_TE_delay1.add(delay1);
        }


        // ------------------------------------------
        // calculate delays adapted to correct spacing in case of ETL & search for incoherence
        // ------------------------------------------
        double delay2;
        double time_flyback_ramp = minInstructionDelay;
        double timeGradTopFlyback = minInstructionDelay;
        double delay2_min = Math.max(min_FIR_4pts_delay - (grad_rise_time + 2 * time_flyback_ramp + timeGradTopFlyback), minInstructionDelay);
        delay2_min = Math.max(delay2_min, min_FIR_delay - (2 * grad_rise_time + 2 * time_flyback_ramp + timeGradTopFlyback + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.LoopStartEcho.ID) + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID, Events.LoopEndEcho.ID)));
        if (echoTrainLength > 1) {
            double time2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.LoopEndEcho.ID); // Actual EchoLoop time
            time2 -= TimeEvents.getTimeForEvents(getSequence(), Events.Delay2.ID); // Actual EchoLoop time without Delay2
            double echo_spacing_min = time2 + delay2_min;
            if (echo_spacing < echo_spacing_min) {
                if (is_interleaved_echo_train) {
                    double effectiveEchoSpacingMin = echo_spacing_min / nb_InterleavedEchoTrain;
                    notifyOutOfRangeParam(INTERLEAVED_EFF_ECHO_SPACING, effectiveEchoSpacingMin, ((NumberParam) getParam(INTERLEAVED_EFF_ECHO_SPACING)).getMaxValue(), "Effective echo spacing too short for interleaved mode.");
                } else {
                    echo_spacing_min = ceilToSubDecimal(echo_spacing_min, 5);
                    notifyOutOfRangeParam(ECHO_SPACING, echo_spacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo spacing too short for the User Mx1D and SW");
                    echo_spacing = echo_spacing_min;
                }
            }
            delay2 = echo_spacing - time2;
        } else {
            delay2 = delay2_min;
        }
        set(Time_TE_delay2, delay2);

    }

    @Override
    protected void getTR() {
        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        double delay_before_multi_planar_loop;
        double delay_sat_band = models.get("SatBand").getDuration();
        double delay_before_echo_loop;

        if (models.get("TofSat").isEnabled() && !isMultiplanar) {
            delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopSatBandStart.ID - 1)
                    + models.get("ExtTrig").getDuration()
                    + delay_sat_band
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.LoopSatBandStart.ID - 1);
            delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStartShort.ID, Events.LoopStartEcho.ID - 1);
        } else {
            delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID - 1)
                    + models.get("ExtTrig").getDuration();
            delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.LoopSatBandStart.ID - 1)
                    + delay_sat_band + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.LoopStartEcho.ID - 1);
        }
        double delay_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.LoopEndEcho.ID);
        double delay_spoiler = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID + 1, Events.LoopMultiPlanarEnd.ID - 2);// grad_phase_application_time + grad_rise_time * 2;
        min_flush_delay = minInstructionDelay;

        double time_seq_to_end_spoiler = delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler) * nb_interleaved_slice;
        double tr_min = time_seq_to_end_spoiler + minInstructionDelay * (nb_interleaved_slice * 2) + min_flush_delay;// 2 +( 2 minInstructionDelay: Events. 22 +(20&21

        if (tr < tr_min) {
            tr_min = ceilToSubDecimal(tr_min, 3);
            notifyOutOfRangeParam(REPETITION_TIME, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (ETL * User Mx3D/Shoot3D) in a singl scan");
            tr = tr_min;
        }

        // ------------------------------------------
        // set calculated TR
        // ------------------------------------------
        // set  TR delay to compensate and trigger delays
        double last_delay = minInstructionDelay;
        double tr_delay;
        Table time_tr_delay = setSequenceTableValues(Time_TR_delay, Order.Four);
        if (((ExtTrig) models.get("ExtTrig")).nb_trigger != 1) {
            for (int i = 0; i < ((ExtTrig) models.get("ExtTrig")).nb_trigger; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler - ((ExtTrig) models.get("ExtTrig")).time_external_trigger_delay_max + ((ExtTrig) models.get("ExtTrig")).triggerdelay.get(i).doubleValue();
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_interleaved_slice - minInstructionDelay;
                time_tr_delay.add(tr_delay);
            }
        } else {
            //tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_slices_acquired_in_single_scan - minInstructionDelay;
            //tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_interleaved_slice - minInstructionDelay;

            tr_delay = (tr - tr_min) / nb_interleaved_slice;
            time_tr_delay.add(tr_delay);
        }
        set(Time_last_delay, last_delay);
        set(Time_flush_delay, min_flush_delay);
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    protected void addUserParams() {
        addMissingUserParams(U.values());
    }

    public String getName() {
        return "GRADIENT_ECHO_TOF_vx";
    }

    public String getVersion() {
        return "master";
    }
    //</editor-fold>
}