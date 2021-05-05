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

import static common.CommonUP.PREPHASING_READ_GRADIENT_RATIO;
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
    private boolean isEPICalibration;

    public Bold() {
        super();
        addUserParams();
    }

    @Override
    public void init() {
        super.init();
        //TRANSFORM PLUGIN
        TextParam transformPlugin = getParam(TRANSFORM_PLUGIN);
        transformPlugin.setSuggestedValues(asList("SEEPISequential"));
        transformPlugin.setRestrictedToSuggested(true);

        // KSPACE_FILLING
        TextParam ksFilling = getParam(KSPACE_FILLING);
        ksFilling.setSuggestedValues(asList("Linear"));
        ksFilling.setRestrictedToSuggested(true);
    }

    // ==============================
// -----   GENERATE
// ==============================
    @Override
    public void initUserParam() {
        super.initUserParam();
        getParam(SEQUENCE_VERSION).setValue(sequenceVersion);
        getParam(MULTI_PLANAR_EXCITATION).setValue(true);
        isMultiplanar = getBoolean(MULTI_PLANAR_EXCITATION);
        isEPICalibration = getBoolean(EPI_CALIBRATION);
        if (isEPICalibration) {
            isKSCenterMode = true;
            isEnablePhase3D = false;
            isEnablePhase = false;
        }
    }

    //--------------------------------------------------------------------------------------
    // ini functions for beforeRouting() and initAfterRouting()
    //--------------------------------------------------------------------------------------
    @Override
    protected void iniModels() {
        setModels(new ArrayList<>(Arrays.asList("ExtTrig", "FatSat", "SatBand")), this);
    }

    @Override
    protected void iniTransformPlugin() throws Exception {
        ///// transform plugin
        kspace_filling = "Linear";
        getParam(KSPACE_FILLING).setValue(kspace_filling);
        getParam(TRANSFORM_PLUGIN).setValue("SEEPISequential");

        plugin = getTransformPlugin();
        plugin.setParameters(new ArrayList<>(getUserParams()));
        plugin.getScanOrder(); //output traj. graphs for Elliptical3D plugin
    }

    @Override
    protected void iniScanLoop() {
        super.iniScanLoop();
        set(Loop_long, Opcode.CodeEnum.StoreLoopAddress);
        set(Loop_short, Opcode.CodeEnum.Continu);

        nb_scan_2d = acqMatrixDimension2D / echoTrainLength;
        set(Nb_2d, nb_scan_2d);
        set(Nb_sb, ((SatBand) models.get("SatBand")).nb_satband - 1);
        set(Nb_echo, echoTrainLength-1);
    }

    //--------------------------------------------------------------------------------------
    // prep functions for afterRouting()
    //--------------------------------------------------------------------------------------
    @Override
    protected void prepRFandSliceGrad() throws Exception {
        super.prepRFandSliceGrad();
        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        if (nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acqMatrixDimension1D * echoTrainLength, nb_slices_acquired_in_single_scan);
            pulseTX.setFrequencyOffset(nb_slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
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

    @Override
    protected void prepDicom() { //TODO  XG, EPI echo in the center of k-space
        super.prepDicom();

        // Set  ECHO_TIME
        if (echoTrainLength != 1) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acqMatrixDimension4D; i++) {
                arrayListEcho.add(echo_spacing * i);
            }
            NumberParam echoTime = getParam(ECHO_TIME);
            ListNumberParam list = new ListNumberParam(echoTime, arrayListEcho);       // associate TE to images for DICOM export
            putVariableParameter(list, 4);
        }
    }

    //--------------------------------------------------------------------------------------
    // get functions
    //--------------------------------------------------------------------------------------
    @Override
    protected void getAcq2D() {
        super.getAcq2D();
        // advanced parallel imaging is not supported yet.
        getParam(ECHO_TRAIN_LENGTH).setValue(getInt(ACQUISITION_MATRIX_DIMENSION_2D));
        echoTrainLength = getInt(ECHO_TRAIN_LENGTH);
    }

    @Override
    protected void getROGrad() throws Exception {
        //super.getROGrad();
        set(Time_grad_ramp_epi, getDouble(GRADIENT_RISE_TIME_EPI));
        this.gradReadout = Gradient.createGradient(this.getSequence(), KernelGE.SP.Grad_amp_read_read, CommonSP.Time_rx, CommonSP.Grad_shape_rise_up, CommonSP.Grad_shape_rise_down, Time_grad_ramp_epi, this.nucleus);
        if (this.isEnableRead && !this.gradReadout.calculateReadoutGradient(this.spectralWidth, this.getDouble(CommonUP.RESOLUTION_FREQUENCY) * (double) this.acqMatrixDimension1D)) {
            double spectral_width_max = this.gradReadout.getSpectralWidth();
            if (this.getBoolean(CommonUP.SPECTRAL_WIDTH_OPT)) {
                this.notifyOutOfRangeParam(CommonUP.SPECTRAL_WIDTH, ((NumberParam) this.getParam(CommonUP.SPECTRAL_WIDTH)).getMinValue(), spectral_width_max / (double) (this.isFovDoubled ? 2 : 1), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                this.notifyOutOfRangeParam(CommonUP.SPECTRAL_WIDTH_PER_PIXEL, ((NumberParam) this.getParam(CommonUP.SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), spectral_width_max / (double) this.acqMatrixDimension1D, "SPECTRAL_WIDTH too high for the readout gradient");
            }

            this.spectralWidth = spectral_width_max;
        }

        this.gradReadout.applyAmplitude(Order.LoopB);
        this.set(CommonSP.Spectral_width, this.spectralWidth);
        gradReadout.applyReadoutEchoPlanarAmplitude(echoTrainLength, Order.LoopB);
    }

    @Override
    protected void getRx() {
        super.getRx();
        pulseRX.setFrequencyOffsetReadoutEchoPlanar(gradReadout, off_center_distance_1D, echoTrainLength, Order.LoopB);
        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID - 1, Events.Acq.ID - 1) + observation_time / 2.0;
        double timeADC2 = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Acq.ID + 1, Events.Delay2.ID - 2) + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_prep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_comp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);
    }

    @Override
    protected void getPrephaseGrad() {
        super.getPrephaseGrad();

        if (isEnableRead) {
            gradReadPrep.refocalizeGradient(gradReadout, 0, getDouble(PREPHASING_READ_GRADIENT_RATIO));
        }
    }

    @Override
    protected void getPEGrad() {
        super.getPEGrad();

        if (isEnablePhase) {
            gradPhase2D.preparePhaseEncoding(acqMatrixDimension2D, fovPhase, is_k_s_centred);
            gradPhase2D.reoderPhaseEncodingForSEEPI(echoTrainLength);
        }

        set(Time_grad_phase_blipTop, minInstructionDelay);
        Gradient gradPhase2D_blip = Gradient.createGradient(getSequence(), Grad_amp_phase_EPI, Time_grad_phase_blipTop, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_epi);
        if (!gradPhase2D_blip.prepareEPIBlip(1 + 1, fovPhase)) {
            System.out.println("XXXXXXXXXXXX SOMETHING WRONG");
//            gradPhaseEPI_blipRampTime_min = ((gradPhase2D_blip.getTotalArea() / 100.0) - gradPhase2D_blip.getMinTopTime()) / (2 * gradPhase2D_blip.getShapeFactor());
//            notifyOutOfRangeParam(GRADIENT_EPI_RAMP, gradPhaseEPI_blipRampTime_min, ((NumberParam) getParam(GRADIENT_EPI_RAMP)).getMaxValue(), "blip ram too short");
//            gradPhaseEPI_blipRampTime = gradPhaseEPI_blipRampTime_min;
//            getParam(GRADIENT_EPI_RAMP).setValue(gradPhaseEPI_blipRampTime);   // display observation time
        }
        gradPhase2D_blip.inversePolarity();
        gradPhase2D_blip.applyAmplitude();
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
        getParam(ECHO_TIME_EFFECTIVE).setValue(te + acqMatrixDimension2D / 2 * TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.LoopEndEcho.ID)); //TODO:if we improve the partial fft, one need to consider it here as well.
        // set calculated the time delays to get the proper TE
        double delay1 = te - time1;

        Table time_TE_delay1 = setSequenceTableValues(Time_TE_delay1, Order.FourLoop);
        time_TE_delay1.add(delay1);

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
                echo_spacing_min = ceilToSubDecimal(echo_spacing_min, 5);
                notifyOutOfRangeParam(ECHO_SPACING, echo_spacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo spacing too short for the User Mx1D and SW");
                echo_spacing = echo_spacing_min;
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

        delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1)
                + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID - 1)
                + models.get("ExtTrig").getDuration();
        delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.LoopSatBandStart.ID - 1)
                + delay_sat_band + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.LoopStartEcho.ID - 1);

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
        return "BOLD";
    }

    public String getVersion() {
        return "master";
    }
    //</editor-fold>
}