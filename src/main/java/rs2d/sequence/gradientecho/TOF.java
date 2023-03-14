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
import rs2d.spinlab.api.Hardware;
import rs2d.spinlab.exception.ConfigurationException;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.element.Opcode;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequenceGenerator.GeneratorParamEnum;
import rs2d.spinlab.sequenceGenerator.util.TimeEvents;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.Order;

import java.util.*;

import static java.util.Arrays.asList;

import common.*;
import kernel.*;
import model.*;

import static java.util.Arrays.parallelSetAll;
import static rs2d.sequence.gradientecho.S.*;
import static rs2d.sequence.gradientecho.U.*;

// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
// * < V2.1 TOF 3D with MT sat, TOF 2D with travelling bands, simultaneous use of TofSat and SatBand not possible
//        * V2.1: separate TofSat and Satband, 3D TOF with saturation bands
public class TOF extends KernelGE {
    private String sequenceVersion = "Version 2.1";
    private boolean isElliptical;
    private double slice_thickness_excitation;
    private boolean isMultiSlab;


    public TOF() {
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
    public void initUserParam() throws ConfigurationException {
        super.initUserParam();
        super.isDebugMode = true;
        getParam(SEQUENCE_VERSION).setValue(sequenceVersion);

        if (isMultiplanar) {
            getParam(NUMBER_OF_SLAB).setValue(1);
            getParam(TOF3D_MT_INDIV).setValue(false);
        }
        else {
            if (getInt(NUMBER_OF_SLAB) > 1)
                getParam(SLAB_OVERLAP).setValue(floorEven(getDouble(SLAB_OVERLAP) / 100 * userMatrixDimension3D) / (double) userMatrixDimension3D * 100);
            isMultiSlab = true;
        }

        isElliptical = kspace_filling.equalsIgnoreCase("3DElliptic") && !isMultiplanar;
        if (isElliptical)
            getParam(USER_PARTIAL_SLICE).setValue(100);
    }

    //--------------------------------------------------------------------------------------
    // ini functions for beforeRouting() and initAfterRouting()
    //--------------------------------------------------------------------------------------
    @Override
    protected void iniModels() {
        setModels(new ArrayList<>(Arrays.asList(new ExtTrig(), new FatSat(), new TofSat(), new FlowComp(), new SatBand())), this);
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

        plugin = getTransformPlugin();
        plugin.setParameters(new ArrayList<>(getUserParams()));
        plugin.getScanOrder(); //output traj. graphs for Elliptical3D plugin
    }

    @Override
    protected void iniScanLoop() {
        super.iniScanLoop();
        try {
            if (models.get(TofSat.class).isEnabled() && !isMultiplanar) {
                set(Loop_long, Opcode.CodeEnum.Continu);
                set(Loop_short, Opcode.CodeEnum.StoreLoopAddress);
            } else {
                set(Loop_long, Opcode.CodeEnum.StoreLoopAddress);
                set(Loop_short, Opcode.CodeEnum.Continu);
            }
        } catch (Exception e) {
            Log.warning(getClass(), "Sequence Param Missing: Loop_short; use default: Loop_long");
        }

        if (getBoolean(TOF3D_MT_INDIV) ) {
            set(Loop_xd, Opcode.CodeEnum.GotoWhile);
            if (models.get(FatSat.class).isEnabled())
                set(Loop_xd_start, Events.FatSatPulse.ID - 1);
            else
                set(Loop_xd_start, Events.P90.ID - 1);
        } else {
            set(Loop_xd, Opcode.CodeEnum.GotoFirstWhile);
            set(Loop_xd_start, 0);
        }

        if (isElliptical) {
            nb_scan_2d = getInt(ACQUISITION_NB_ECHO_TRAIN);
            nb_scan_3d = 1;
            set(Nb_2d, nb_scan_2d);
            set(Nb_3d, nb_scan_3d);
        }

        set(Nb_sb, models.get(SatBand.class).nb_satband - 1);
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
        set(Tx_att_offset, 0);
        pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_att_offset, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset, nucleus);

        double flip_angle = getDouble(FLIP_ANGLE);
        flip_angle = models.get(TofSat.class).isEnabled() && models.get(TofSat.class).getRfPulses().isSlr() ? 90 : flip_angle;
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
            if (!pulseTX.prepPower(flip_angle, observeFrequency)) {
                notifyOutOfRangeParam(TX_LENGTH, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX.getPulseDuration();
            }
            pulseTX.prepChannelAtt(80, getListInt(TX_ROUTE));
            pulseTX.prepTxAmp(getListInt(TX_ROUTE));
            rfPulses.add(pulseTX);
            getUPDisp();
        } else {
            pulseTX.setAtt(getInt(TX_ATT));
            pulseTX.setAmp(getDouble(TX_AMP));
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  4/4: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90;
        if (isMultiSlab) {
            boolean isSelective = getBoolean(TX_SELECTION_PULSE);  // not clear yet for multislab, the bandwidth factor should be chosen from 2D or 3D excitation
            tx_bandwidth_factor_90 = isSelective ? this.getTx_bandwidth_factor(TX_SHAPE, TX_BANDWIDTH_FACTOR) : this.getTx_bandwidth_factor(TX_SHAPE, TX_BANDWIDTH_FACTOR_3D);
        } else {
            tx_bandwidth_factor_90 = getTx_bandwidth_factor(TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        }
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        slice_thickness_excitation = (isMultiplanar ? sliceThickness : (sliceThickness * userMatrixDimension3D));

        gradSlice = Gradient.createGradient(getSequence(), Grad_amp_slice, Time_tx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);

        if (hasParam(TOF3D_EXT_SHIRNK_FACTOR) && !isMultiplanar) {
            if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation * (100 - getDouble(TOF3D_EXT_SHIRNK_FACTOR)) / 100)) {
                notifyOutOfRangeParam(FIELD_OF_VIEW_3D, fov3d, ((NumberParam) getParam(FIELD_OF_VIEW_3D)).getMaxValue(), "Pulse length too short to reach this fov3d");
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
            pulseTX.reoderOffsetFreq(plugin, acqMatrixDimension1D, nb_interleaved_slice);
            pulseTX.setFrequencyOffset(nb_interleaved_slice != 1 ? Order.ThreeLoop : Order.Three);

        } else {
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, getInt(NUMBER_OF_SLAB), getDouble(SPACING_BETWEEN_SLAB) + (slice_thickness_excitation - gradSlice.getSliceThickness()), off_center_distance_3D);
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

        RFPulse pulseTXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep, nucleus);
        pulseTXPrep.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        RFPulse pulseTXComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_comp, nucleus);
        pulseTXComp.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        // ------------------------------------------------------------------
        // calculate effective FOV in slab direction for Satband with 3D TOF
        // ------------------------------------------------------------------
        if(!isMultiplanar && (getInt(NUMBER_OF_SLAB)>1)) {
            double fovMultiSlab = (getInt(NUMBER_OF_SLAB) -1) * (fov3d + getDouble(SPACING_BETWEEN_SLAB)) + fov3d;
            models.get(SatBand.class).setMultiSab(true);
            models.get(SatBand.class).setFovMultiSlab(fovMultiSlab);
            Log.info(getClass(), "fovMultiSlab = " + fovMultiSlab);
            Log.info(getClass(), "fov3D = " + fov3d);
            Log.info(getClass(), "satband = " + models.get(SatBand.class).gradSatBandSlice.getAmplitude());
        }
    }

    //--------------------------------------------------------------------------------------
    // get functions
    //--------------------------------------------------------------------------------------
    @Override
    protected void getAcq3D() {
        super.getAcq3D();

        if (!isMultiplanar) {
            getParam(SPACING_BETWEEN_SLAB).setValue(getInt(NUMBER_OF_SLAB) > 1 ? -getDouble(SLAB_OVERLAP) / 100 * sliceThickness * userMatrixDimension3D : 0);
        } else {
            if (models.get(TofSat.class).isEnabled()) {
                nb_shoot_3d = acqMatrixDimension3D; // TOF does not allow interleaved slice within the TR
                nb_interleaved_slice = (int) Math.ceil((acqMatrixDimension3D / (double) nb_shoot_3d));
                getParam(NUMBER_OF_SHOOT_3D).setValue(nb_shoot_3d);
                getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(isMultiplanar ? nb_interleaved_slice : 0);
            }
        }
    }

    @Override
    protected void getAcq4D() {
        super.getAcq4D();
        acqMatrixDimension4D *= getInt(NUMBER_OF_SLAB);
        userMatrixDimension4D = acqMatrixDimension4D;

        getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(isKSCenterMode ? 1 : acqMatrixDimension4D);
        getParam(USER_MATRIX_DIMENSION_4D).setValue(userMatrixDimension4D);
    }

    @Override
    protected void getRx() {
        super.getRx();
        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID - 1, Events.Acq.ID - 1) + observation_time / 2.0;
        double timeADC2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID + 1, Events.Delay2.ID) + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_prep, nucleus);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_comp, nucleus);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);
    }

    @Override
    protected void getPrephaseGrad() {
        super.getPrephaseGrad();

        if (isEnableRead && models.get(FlowComp.class).isEnabled()) {
            gradReadPrep.refocalizeGradientWithFlowComp(gradReadout, getDouble(PREPHASING_READ_GRADIENT_RATIO), models.get(FlowComp.class).gradReadPrepFlowComp);
            gradSliceRefPhase3D.refocalizeGradientWithFlowComp(gradSlice, getDouble(SLICE_REFOCUSING_GRADIENT_RATIO), models.get(FlowComp.class).gradSliceRefPhase3DFlowComp);
        }
    }

    @Override
    protected void getPEGrad() {
        super.getPEGrad();

        if (isEnablePhase && isElliptical) {
            Log.info(getClass(), " flow comp not supported ");
            if (isElliptical)
                gradPhase2D.preparePhaseEncoding(userMatrixDimension2D, fovPhase, is_k_s_centred);
            else
                gradPhase2D.preparePhaseEncoding(acqMatrixDimension2D, fovPhase, is_k_s_centred);
            
            gradPhase2D.reoderPhaseEncoding(plugin);
        }

        if (!isMultiplanar && isEnablePhase3D) {
            Log.info(getClass(), " flow comp not supported for PE dir");
            gradSliceRefPhase3D.preparePhaseEncoding(acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            if (isElliptical) {
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin);
            } else {
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin, acqMatrixDimension3D);
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
        // update Dwell Time
        // -----------------------------------------
        getParam(DWELL_TIME).setValue(roundToDecimal(1/spectralWidth,7));
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
        Table time_TE_delay1 = setSequenceTableValues(Time_TE_delay1, Order.FourLoop);
        time_TE_delay1.add(delay1);

        // ------------------------------------------
        // calculate delays adapted to correct spacing in case of ETL & search for incoherence
        // ------------------------------------------
        double delay2_min = Math.max(min_FIR_4pts_delay - grad_rise_time, minInstructionDelay);
        delay2_min = Math.max(delay2_min,
                min_FIR_delay - (TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID + 1, Events.LoopMultiPlanarEnd.ID)
                        + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.Acq.ID - 1)));
        set(Time_TE_delay2, delay2_min);

    }

    @Override
    protected void getTR() {
        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        nb_slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nb_interleaved_slice) : 1;
        double delay_before_multi_planar_loop;
        double delay_sat_band = models.get(SatBand.class).getDuration();
        double delay_before_echo_loop;
        Log.info(getClass()," delay_sat_band = " +  delay_sat_band);
        Log.info(getClass()," ext trigger duration = " +  models.get(ExtTrig.class).getDuration());

        if (models.get(TofSat.class).isEnabled() && !isMultiplanar) {
            Log.info(getClass(),"multislab tof");
            delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID)
                    + models.get(ExtTrig.class).getDuration()
                    + delay_sat_band
                    + models.get(TofSat.class).getDuration()
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.LoopMultiPlanarStartShort.ID - 1);
            Log.info(getClass(), "delay_before_multi_planar_loop = " + delay_before_multi_planar_loop);
            delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStartShort.ID, Events.Delay1.ID);
            Log.info(getClass(), "delay_before_echo_loop = " + delay_before_echo_loop);
        } else {
            delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID - 1)
                    + models.get(ExtTrig.class).getDuration();
            delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.LoopSatBandStart.ID - 1)
                    + delay_sat_band + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.Delay1.ID);
        }
        double delay_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Delay1.ID + 1, Events.Delay2.ID + 1);
        double delay_spoiler = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Delay2.ID + 2, Events.LoopMultiPlanarEnd.ID - 2);// grad_phase_application_time + grad_rise_time * 2;
        min_flush_delay = minInstructionDelay;

        double time_seq_to_end_spoiler = delay_before_multi_planar_loop + (delay_before_echo_loop + delay_echo_loop + delay_spoiler) * nb_slices_acquired_in_single_scan;
        double tr_min = time_seq_to_end_spoiler + minInstructionDelay * (nb_slices_acquired_in_single_scan * 2) + min_flush_delay;// 2 +( 2 minInstructionDelay: Events. 22 +(20&21
        Log.info(getClass(), "time_seq_to_end_spoiler = " + time_seq_to_end_spoiler);
        Log.info(getClass(), "tr min = " + tr_min);
        if (getBoolean(TOF3D_MT_INDIV))
            tr_min = tr_min - models.get(TofSat.class).getDuration();

        if (tr < tr_min) {
            tr_min = ceilToSubDecimal(tr_min, 3);
            notifyOutOfRangeParam(REPETITION_TIME, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (User Mx3D/Shoot3D) in a single scan");
            tr = tr_min;
        }

        // ------------------------------------------
        // set calculated TR
        // ------------------------------------------
        // set  TR delay to compensate and trigger delays
        double last_delay = minInstructionDelay;
        double tr_delay;
        Table time_tr_delay = setSequenceTableValues(Time_TR_delay, Order.Four);
        if (models.get(ExtTrig.class).nb_trigger != 1) {
            for (int i = 0; i < models.get(ExtTrig.class).nb_trigger; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler - models.get(ExtTrig.class).time_external_trigger_delay_max + models.get(ExtTrig.class).triggerdelay.get(i).doubleValue();
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_slices_acquired_in_single_scan - minInstructionDelay;
                time_tr_delay.add(tr_delay);
            }
        } else {

            tr_delay = (tr - tr_min) / nb_slices_acquired_in_single_scan;
            time_tr_delay.add(tr_delay);
        }
        set(Time_last_delay, last_delay);
        set(Time_flush_delay, min_flush_delay);


    }

    protected void getAcqTime() {
        super.getAcqTime();
        if (!this.isMultiplanar && getBoolean(TOF3D_MT_INDIV)) {
            getParam(SEQUENCE_TIME).setValue(getDouble(SEQUENCE_TIME) + models.get(TofSat.class).getDuration() * (getInt(NUMBER_OF_SLAB) + nb_preScan));
        }
    }

    /**
     * internal function to get TX bandwidth time product: not confond with getTx_bandwidth_factor from SeqPrepBasic as in Satband module, slice-selective is always on, while
     * it might not be the case for the parent sequence, e.g. applying satband on whole-volume excitation or slab excitation.
     *
     * @param tx_shape      : TX shape user parameter
     * @param tx_bandwith_factor_param: TX_BANDWIDTH_FACTOR or TX_BANDWIDTH_FACTOR_3D, depending on whether it is slice selection
     *
     * return the BWTP of the selected pulse shape
     *
     */
    private double getTx_bandwidth_factor(GeneratorParamEnum tx_shape, GeneratorParamEnum tx_bandwith_factor_param) {
        double tx_bandwidth_factor;
        String tx_shape_name = getText(tx_shape);

        List<Double> tx_bandwith_factor_table = getListDouble(tx_bandwith_factor_param);

        if ("GAUSSIAN".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(1);
        } else if ("SINC3".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(2);
        } else if ("SINC5".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(3);
        } else if ("SLR_8_5152".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(4);
        } else if ("SLR_4_2576".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(5);
        } else if ("RAMP".equalsIgnoreCase(tx_shape_name)) {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(6);
        } else {
            tx_bandwidth_factor = tx_bandwith_factor_table.get(0);
        }

        return tx_bandwidth_factor;
    }


    @Override
    protected void printForDebug() throws Exception {
        
        if (getBoolean(DEBUG_MODE)) {
            Log.info(getClass(), "satband rf = " + models.get(SatBand.class).pulseTXSatBand.getFrequencyOffset(0));
            Log.info(getClass(), "gmax = " + Math.abs(GradientMath.getMaxGradientStrength()));
            Log.info(getClass(), "tofsat enabled = " +models.get(TofSat.class).isEnabled());
            Log.info(getClass(), "nb_slices_acquired_in_single_scan = " + nb_slices_acquired_in_single_scan);
            Log.info(getClass(), "nb_interleaved_slice = " + nb_interleaved_slice);
            if (models.get(TofSat.class).isEnabled()) {
                Log.info(getClass(), "tofsat rf gamma b1 calculated = " + models.get(TofSat.class).pulseTXTofSat.getPowerGammaB1());
                Log.info(getClass(), "TOFSAT duration = " + models.get(TofSat.class).getDuration());
                Log.info(getClass(), "SATBAND duration = " + models.get(SatBand.class).getDuration());
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    protected void addUserParams() {
        addMissingUserParams(U.values());
    }

    public String getName() {
        return "TOF";
    }

    public String getVersion() {
        return "V2.1_spinlab_2022.06.3";
    }
    //</editor-fold>
}