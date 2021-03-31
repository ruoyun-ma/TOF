package rs2d.sequence.gradientecho;

// ---------------------------------------------------------------------
//  
//                 GRADIENT_ECHO_dev PSD 
//  
// ---------------------------------------------------------------------
// test Commit
// rename GRADIENT_SPOILER_TIME
// 02/03/2021   V11 XG: from now on, we use SeqPrep/Gradient/RF library
// 17/11/2017   V7.4
//      - fovPhase  TX_LENGTH   TX_SHAPE
// 17/11/2017   V7.3
// 25/10/2017   V7.2
//		bug and other
//            KS_CENTER_MODE
// 30/09/2017    V7.1
//			Replacement with Gradient and RF Pulse Class
// 			OffCenterFOV
// 			bug       delay2_min = ...se_time "+" getTim ... Echo) "+" getT...;
//
// 20/06/2017   V7.0
//     rename all parameters
//     fir
// 20/06/2017   V6.1
// 	 GRADIENT_SPOILER_APPL_TIME added to get the timing independeant of GRADIENT_PHASE_APPLICATION_TIME
// 20/06/2017   V6
// 	IMAGE_ORIENTATION_SPECIMEN /  IMAGE_POSITION_SPECIMEN / IMAGE_POSITION_SPECIMEN  - > SUBJECT
// 15/06/2017   V5.6
// 	delet "/ nucleus.getRatio() "
// 24/03/2017   V5.6
//        notifyOutOfRangeParam(SPECTRAL_WIDTH,((NumberParam) getParam( SPECTRAL_WIDTH)).getMinValue(),spectral_width_max, "SPECTRAL_WIDTH too high for the readout gradient");
//	SAR deleted
// 24/03/2017   V5.5
//   setParamValue(MODALITY, "MRI");
//   setSequenceParamValue("Phase_reset","USER_TMP_PARAM_BOOL_1");

import common.Gradient;
import rs2d.commons.log.Log;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.SequenceTool;
import rs2d.spinlab.sequence.element.Opcode;
import rs2d.spinlab.sequence.element.TimeElement;
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
public class GradientEcho extends SeqPrep/*BaseSequenceGenerator*/ {
    private String sequenceVersion = "Version x1.1";
    private boolean CameleonVersion105 = false;

    private boolean is_keyhole_allowed;

    private String kspace_filling;
    private boolean isElliptical;

    private double min_flush_delay;

    private double slice_thickness_excitation;

    private double txLength90;
    private List<Double> grad_amp_spoiler_sl_ph_re;

    //Dynamic
    private boolean isDynamic;
    private int nb_dynamic_acquisition;
    private boolean isDynamicMinTime;
    private double time_between_frames;

    //Fly Back
    private boolean is_flyback;
    private double timeGradTopFlyback;
    private double time_flyback_ramp;

    private boolean is_interleaved_echo_train;
    private int nb_InterleavedEchoTrain;

    private boolean is_rf_spoiling;

    boolean is_grad_rewinding;
    boolean is_grad_spoiler;
    boolean is_k_s_centred;

    private double grad_shape_rise_time;

    private Gradient gradPhase2D;
    private Gradient gradReadPrep;
    private Gradient gradReadout;
    private Gradient gradReadoutFlyback;
    private Gradient gradSliceRefPhase3D;
    private RFPulse pulseRX;

    public GradientEcho() {
        super();
        addUserParams();
    }

    @Override
    public void init() {
        super.init();
        setSuggestedValFromListString(tx_shape, true, TX_SHAPE);

        //TRANSFORM PLUGIN
        TextParam transformPlugin = getParam(TRANSFORM_PLUGIN);
        transformPlugin.setSuggestedValues(asList("Sequential4D", "Sequential4DBackAndForth", "EPISequential4D", "Elliptical3D", "Sequential4D_TOF"));
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

        is_keyhole_allowed = getBoolean(KEYHOLE_ALLOWED);
        kspace_filling = getText(KSPACE_FILLING);
        isElliptical = kspace_filling.equalsIgnoreCase("3DElliptic");
        txLength90 = getDouble(TX_LENGTH);

        isDynamic = getBoolean(DYNAMIC_SEQUENCE);
        nb_dynamic_acquisition = isDynamic ? getInt(DYN_NUMBER_OF_ACQUISITION) : 1;
        isDynamic = isDynamic && (nb_dynamic_acquisition > 1);
        isDynamicMinTime = getBoolean(DYNAMIC_MIN_TIME);


        is_flyback = getBoolean(FLYBACK);
        is_rf_spoiling = getBoolean(RF_SPOILING);

        is_interleaved_echo_train = getBoolean(INTERLEAVED_ECHO_TRAIN);
        nb_InterleavedEchoTrain = getInt(INTERLEAVED_NUM_OF_ECHO_TRAIN);


        is_grad_rewinding = getBoolean(GRADIENT_ENABLE_REWINDING);// get slice refocussing ratio
        is_grad_spoiler = getBoolean(GRADIENT_ENABLE_SPOILER);// get slice refocussing ratio
        grad_amp_spoiler_sl_ph_re = getListDouble(GRAD_AMP_SPOILER_SL_PH_RE);
        is_k_s_centred = getBoolean(KS_CENTERED);
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
            getParam(TRANSFORM_PLUGIN).setValue("Sequential4DBackAndForth");
            if (is_flyback) {
                getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
            }
        } else {
            switch (kspace_filling) {
                case "Linear":
                    getParam(TRANSFORM_PLUGIN).setValue("Sequential4DBackAndForth");
                    if (is_flyback) {
                        getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
                    }
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
                    getParam(TRANSFORM_PLUGIN).setValue("Sequential4DBackAndForth");
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
        nb_scan_1d = nb_averages;
        String updateDimension = SequenceTool.UpdateDimensionEnum.Disable.getText();
        if (!isMultiplanar) {
            System.out.println(" kspace_filling " + kspace_filling);
            if (!isElliptical) {
                System.out.println(" non 3DElliptic " + acqMatrixDimension2D);
                nb_scan_2d = acqMatrixDimension2D;
                //nb_scan_3d = acqMatrixDimension3D;
                nb_scan_3d = nb_shoot_3d;
            } else {
                System.out.println(" 3DElliptic");
                //CAMELEON3
//                int[] nb2D_3D_Dummy;
//                if (Hardware.getSequenceCompiler().getVersionID() < 1303) {
//                    //        split the PE in 2D 3D with 200 < 2D < 500
//                    nb2D_3D_Dummy = getNbScans2D3DForUpdateDimension(200, 500, zTraj);
//                    updateDimension = ( nb2D_3D_Dummy[1] > 1) ? SequenceTool.UpdateDimensionEnum.ThreeD.getText() : updateDimension;
//                } else {
//                    nb2D_3D_Dummy = getNbScans2D3DForUpdateDimension(200, (int) (Math.pow(2, 20) - 100 / 2), zTraj);
//                }
//                nb_scan_3d = nb2D_3D_Dummy[1];

                nb_scan_2d = getInt(ACQUISITION_NB_ECHO_TRAIN);
                nb_scan_3d = 1;
            }
        } else {
            nb_scan_2d = acqMatrixDimension2D;
            nb_scan_3d = nb_shoot_3d;
        }

        //Dynamic and multi echo are filled into the 4th Dimension
        if (models.get("ExtTrig").isEnabled()) {
            nb_scan_4d = ((ExtTrig) models.get("ExtTrig")).nb_trigger * nb_dynamic_acquisition;
        } else {
            nb_scan_4d = Math.max(acqMatrixDimension4D / nb_interleaved_slice, 1);
        }

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

        nb_planar_excitation = (isMultiplanar ? acqMatrixDimension3D : 1); //XG:for 3D, we still use 1, to trick phaseoffset of MT pulse
        //nb_slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nb_interleaved_slice) : 1;

        if (isKSCenterMode) { // Do only the center of the k-space for auto RG
            nb_scan_1d = 1;
            nb_scan_2d = 2;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            nb_scan_4d = 1;
            getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(!isMultiplanar ? 1 : acqMatrixDimension3D);
            getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(1);
        }
        // set Nb_scan  Values
        set(Pre_scan, nb_preScan); // Do the prescan
        set(Nb_point, acqMatrixDimension1D);
        set(Nb_1d, nb_scan_1d);
        set(Nb_2d, nb_scan_2d);
        set(Nb_3d, nb_scan_3d);
        set(Update_dimension, updateDimension);
        set(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions
        set(Nb_echo, echoTrainLength - 1);
        set(Nb_interleaved_slice, nb_interleaved_slice - 1);
        set(Nb_sb, ((SatBand) models.get("SatBand")).nb_satband - 1);
    }

    @Override
    protected void iniSeqDisp() {
        String seqDescription = "GE_";
        if (isMultiplanar) {
            seqDescription += "2D_";
        } else {
            seqDescription += "3D_";
        }
        String orientation = getText(ORIENTATION);
        seqDescription += orientation.substring(0, 3);

        String seqMatrixDescription = "_";
        seqMatrixDescription += userMatrixDimension1D + "x" + acqMatrixDimension2D + "x" + acqMatrixDimension3D;
        if (acqMatrixDimension4D != 1) {
            seqMatrixDescription += "x" + acqMatrixDimension4D;
        }
        seqDescription += seqMatrixDescription;

        if (echoTrainLength != 1) {
            seqDescription += "_ETL=" + echoTrainLength;
        }
        if (isDynamic) {
            seqDescription += "_DYN=" + nb_dynamic_acquisition;
        }
        getParam(SEQ_DESCRIPTION).setValue(seqDescription);
    }

    @Override
    protected void iniSeqParamBasics() {
        set(Time_min_instruction, minInstructionDelay);
        set(Phase_reset, PHASE_RESET);
        set(Frequency_offset_init, 0.0);// PSD should start with a zero offset frequency pulse
    }

    @Override
    protected void iniSeqParamEnabled() {
        set(Grad_enable_read, isEnableRead);              // pass gradient line status to sequence
        set(Grad_enable_phase_2D, isEnablePhase);
        set(Grad_enable_phase_3D, (!isMultiplanar && isEnablePhase3D));
        set(Grad_enable_slice, isEnableSlice);

        set(Grad_enable_spoiler_slice, (((!isMultiplanar && is_grad_rewinding && isEnablePhase3D) || (is_grad_rewinding && isEnableSlice) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(0) != 0)))));
        set(Grad_enable_spoiler_phase, (isEnablePhase && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(1) != 0))));
        set(Grad_enable_spoiler_read, (isEnableRead && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(2) != 0))));
        set(Grad_enable_flyback, is_flyback);
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
        //if (isMultiplanar && nb_planar_excitation > 1 && isEnableSlice) {
        if (isMultiplanar && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            //pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            //pulseTX.reoderOffsetFreq(plugin, acqMatrixDimension1D * echoTrainLength, nb_slices_acquired_in_single_scan);
            //pulseTX.setFrequencyOffset(nb_slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);

            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, acqMatrixDimension3D, spacingBetweenSlice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acqMatrixDimension1D * echoTrainLength, nb_interleaved_slice);
            pulseTX.setFrequencyOffset(nb_interleaved_slice != 1 ? Order.ThreeLoop : Order.Three);

        } else {
            //3D CASE :
            //pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_interleaved_slice, 0, off_center_distance_3D);
            //pulseTX.setFrequencyOffset(Order.Three);
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, acqMatrixDimension4D, spacingBetweenSlice + (slice_thickness_excitation - gradSlice.getSliceThickness()), off_center_distance_3D);
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

    @Override
    protected void prepDicom() {
        // Set  TRIGGER_TIME for dynamic or trigger acquisition
        if (isDynamic && (nb_dynamic_acquisition != 1) && !models.get("ExtTrig").isEnabled()) {
            ArrayList<Number> arrayListTrigger = new ArrayList<>();
            for (int i = 0; i < nb_dynamic_acquisition; i++) {
                arrayListTrigger.add(i * time_between_frames);
            }
//            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.TRIGGER_TIME.name()), arrayListTrigger);       // associate TE to images for DICOM export
//            putVariableParameter(list, (4));
            getParam(TRIGGER_TIME).setValue(arrayListTrigger);
        }

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

    @Override
    protected void prepComments() {
        if (false) { // Show the comments
            System.out.println("");
            System.out.println(((NumberParam) getSequenceParam(Nb_1d)).intValue());
            System.out.println((((NumberParam) getSequenceParam(Nb_2d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequenceParam(Nb_3d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequenceParam(Nb_4d)).getValue().intValue()));
            System.out.println((((NumberParam) getSequenceParam(Nb_echo)).getValue().intValue()));
            System.out.println((((NumberParam) getSequenceParam(Nb_interleaved_slice)).getValue().intValue()));
            System.out.println("");

            for (int i = 0; i < Events.End.ID; i++) {
                System.out.println((((TimeElement) getSequence().getTimeChannel().get(i)).getTime().getFirst().doubleValue() * 1000000));
            }
        }
    }

    //--------------------------------------------------------------------------------------
    // get functions
    //--------------------------------------------------------------------------------------
    @Override
    protected void getAcq3D() {
        // MATRIX
        boolean is_partial_oversampling = getBoolean(PARTIAL_OVERSAMPLING);
        is_partial_oversampling = !isMultiplanar && userMatrixDimension3D >= 8 && is_partial_oversampling;
        getParam(PARTIAL_OVERSAMPLING).setValue(is_partial_oversampling);

        // 3D ZERO FILLING
        double partial_slice;
        if (isMultiplanar || is_partial_oversampling) {
            getParam(USER_PARTIAL_SLICE).setValue(100);
            partial_slice = 100;
        } else {
            partial_slice = getDouble(USER_PARTIAL_SLICE);
        }
        double zero_filling_3D = (100 - partial_slice) / 100f;
        getParam(USER_ZERO_FILLING_3D).setValue((100 - partial_slice));

        //Calculate the number of k-space lines acquired in the 3rd Dimension : acqMatrixDimension3D
        if (!isMultiplanar) {
            acqMatrixDimension3D = floorEven((1 - zero_filling_3D) * userMatrixDimension3D);
            acqMatrixDimension3D = (acqMatrixDimension3D < 4) && isEnablePhase3D ? 4 : acqMatrixDimension3D;
            userMatrixDimension3D = Math.max(userMatrixDimension3D, acqMatrixDimension3D);
        } else {
            if ((userMatrixDimension3D * 3 + ((is_rf_spoiling) ? 1 : 0) + 3 + 1) >= offset_channel_memory) {
                userMatrixDimension3D = ((int) Math.floor((offset_channel_memory - 4 - ((is_rf_spoiling) ? 1 : 0)) / 3.0));
            }
            acqMatrixDimension3D = userMatrixDimension3D;
        }

// support multi-slab in 3D acq
        //nb_shoot_3d = isMultiplanar ? getInferiorDivisorToGetModulusZero(nb_shoot_3d, acqMatrixDimension3D) : acqMatrixDimension3D;
        //nb_shoot_3d = models.get("TofSat").isEnabled() ? acqMatrixDimension3D : nb_shoot_3d; // TOF does not allow interleaved slice within the TR
        //nb_interleaved_slice = isMultiplanar ? (int) Math.ceil((acqMatrixDimension3D / (double) nb_shoot_3d)) : 1;

        if (models.get("TofSat").isEnabled() && isMultiplanar) {
            nb_shoot_3d = acqMatrixDimension3D; // TOF does not allow interleaved slice within the TR
        } else if (/*models.get("TofSat").isEnabled() &&*/ !isMultiplanar) {  //in 3DTOF, we nb_shoot_3d depends on nb_interleaved_slice;
            nb_interleaved_slice = getInt(NUMBER_OF_INTERLEAVED_SLICE);
//            nb_interleaved_slice = getInferiorDivisorToGetModulusZero(nb_interleaved_slice, acqMatrixDimension3D * acqMatrixDimension4D);
            nb_interleaved_slice = getInferiorDivisorToGetModulusZero(nb_interleaved_slice, acqMatrixDimension4D);
        } else {
            nb_shoot_3d = getInferiorDivisorToGetModulusZero(nb_shoot_3d, acqMatrixDimension3D);
        }

        if (!isMultiplanar /*&& models.get("TofSat").isEnabled()*/) {
//            nb_shoot_3d = (int) Math.ceil((acqMatrixDimension3D * acqMatrixDimension4D / (double) nb_interleaved_slice));
            nb_shoot_3d = acqMatrixDimension3D;
        } else {
            nb_interleaved_slice = (int) Math.ceil((acqMatrixDimension3D / (double) nb_shoot_3d));
        }

        getParam(NUMBER_OF_SHOOT_3D).setValue(nb_shoot_3d);
        //getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(isMultiplanar ? nb_interleaved_slice : 0);
        getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(nb_interleaved_slice);

        acqMatrixDimension3D = is_partial_oversampling ? (int) Math.round(acqMatrixDimension3D / 0.8 / 2) * 2 : acqMatrixDimension3D;
        userMatrixDimension3D = is_partial_oversampling ? (int) Math.round(userMatrixDimension3D / 0.8 / 2) * 2 : userMatrixDimension3D;
        getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(acqMatrixDimension3D);
        getParam(USER_MATRIX_DIMENSION_3D).setValue(userMatrixDimension3D);

        if (isMultiplanar) {
            spacingBetweenSlice = getDouble(SPACING_BETWEEN_SLICE);
            fov3d = sliceThickness * userMatrixDimension3D + spacingBetweenSlice * (userMatrixDimension3D - 1);
            getParam(FIELD_OF_VIEW_3D).setValue(fov3d);    // FOV ratio for display
        } else {
            sliceThickness = fov3d / userMatrixDimension3D;
            spacingBetweenSlice = userMatrixDimension4D > 1 ? -getDouble(TOF3D_MOTSA_OVERLAP) / 100 * sliceThickness * userMatrixDimension3D : 0;
            fov3d = sliceThickness * userMatrixDimension3D;

            getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(acqMatrixDimension3D);
            getParam(USER_MATRIX_DIMENSION_3D).setValue(userMatrixDimension3D);
            getParam(FIELD_OF_VIEW_3D).setValue(fov3d);    // FOV ratio for display
            getParam(NUMBER_OF_SHOOT_3D).setValue(nb_shoot_3d);
            getParam(SPACING_BETWEEN_SLICE).setValue(spacingBetweenSlice);

            sliceThickness = fov3d / userMatrixDimension3D;
            getParam(SLICE_THICKNESS).setValue(sliceThickness);
        }
    }

    @Override
    protected void getAcq4D() {
        // keyhole_allowed only available when dynamic
        is_keyhole_allowed = isDynamic && is_keyhole_allowed;
        getParam(KEYHOLE_ALLOWED).setValue(is_keyhole_allowed);

        // ETL only available when not kewholed
        is_interleaved_echo_train = !is_keyhole_allowed && is_interleaved_echo_train;
        getParam(INTERLEAVED_ECHO_TRAIN).setValue(is_interleaved_echo_train);

        echoTrainLength = is_keyhole_allowed ? 1 : echoTrainLength;
        getParam(ECHO_TRAIN_LENGTH).setValue(echoTrainLength);

        if (is_interleaved_echo_train) { //TODO interleaved echo train
            isDynamic = true;
            getParam(DYNAMIC_SEQUENCE).setValue(isDynamic);

            isDynamicMinTime = true;
            getParam(DYNAMIC_MIN_TIME).setValue(isDynamicMinTime);

            nb_dynamic_acquisition = nb_InterleavedEchoTrain;
            getParam(DYN_NUMBER_OF_ACQUISITION).setValue(nb_dynamic_acquisition);

            kspace_filling = "Linear";
            getParam(KSPACE_FILLING).setValue(kspace_filling);
            //getParam("KEYHOLE_ALLOWED").setValue( false);
        }

        if (echoTrainLength == 1) {
            echo_spacing = 0;
            getParam(ECHO_SPACING).setValue(echo_spacing);
            is_flyback = false;
            getParam(FLYBACK).setValue(is_flyback);
        } else {
            kspace_filling = "Linear";
            getParam(KSPACE_FILLING).setValue(kspace_filling);
        }

        // Avoid multi trigger time when  Multi echo or dynamic
        if (((ExtTrig) models.get("ExtTrig")).nb_trigger != 1 && (isDynamic)) {
            double tmp = ((ExtTrig) models.get("ExtTrig")).triggerTime.get(0);
            ((ExtTrig) models.get("ExtTrig")).triggerTime.clear();
            ((ExtTrig) models.get("ExtTrig")).triggerTime.add(tmp);
            ((ExtTrig) models.get("ExtTrig")).nb_trigger = 1;
        }
        if (models.get("ExtTrig").isEnabled()) {
            acqMatrixDimension4D = ((ExtTrig) models.get("ExtTrig")).nb_trigger * nb_dynamic_acquisition * echoTrainLength;
            userMatrixDimension4D = ((ExtTrig) models.get("ExtTrig")).nb_trigger * nb_dynamic_acquisition;
        } else {
            acqMatrixDimension4D = userMatrixDimension4D;
        }

        getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(isKSCenterMode ? 1 : acqMatrixDimension4D);
        getParam(USER_MATRIX_DIMENSION_4D).setValue(userMatrixDimension4D);
    }

    @Override
    protected void getROGrad() throws Exception {
        gradReadout = Gradient.createGradient(getSequence(), Grad_amp_read_read, Time_rx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableRead && !gradReadout.calculateReadoutGradient(spectralWidth, getDouble(RESOLUTION_FREQUENCY) * acqMatrixDimension1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            if (getBoolean(SPECTRAL_WIDTH_OPT)) {
                notifyOutOfRangeParam(SPECTRAL_WIDTH, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), (spectral_width_max / (isFovDoubled ? 2 : 1)), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                notifyOutOfRangeParam(SPECTRAL_WIDTH_PER_PIXEL, ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), (spectral_width_max / acqMatrixDimension1D), "SPECTRAL_WIDTH too high for the readout gradient");
            }
            spectralWidth = spectral_width_max;
        }
        gradReadout.applyReadoutEchoPlanarAmplitude(is_flyback ? 1 : echoTrainLength, Order.LoopB);
        set(Spectral_width, spectralWidth);

        //--------------------------
        // Fly Back
        //--------------------------
        getFlybackGrad();
    }

    @Override
    protected void getRx() {
        //----------------------------------------------------------------------
        // OFF CENTER FIELD OF VIEW 1D
        // modify RX FREQUENCY OFFSET
        //----------------------------------------------------------------------
        pulseRX = RFPulse.createRFPulse(getSequence(), Time_rx, Rx_freq_offset, Rx_phase);
//        pulseRX.setFrequencyOffsetReadout(gradReadout, off_center_distance_1D);
        pulseRX.setFrequencyOffsetReadoutEchoPlanar(gradReadout, off_center_distance_1D, is_flyback ? 1 : echoTrainLength, Order.LoopB);

        //fill the OFF_CENTER_FIELD_OF_VIEW_EFF User Parameter
        ArrayList<Number> off_center_distanceList = new ArrayList<>();
        off_center_distanceList.add(off_center_distance_1D);
        off_center_distanceList.add(0);
        off_center_distanceList.add(0);

        getParam(OFF_CENTER_FIELD_OF_VIEW_EFF).setValue(off_center_distanceList);

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
    protected void getPhaseCyc() {
        //--------------------------------------------------------------------------------------
        //  calculate RF_SPOILING
        //--------------------------------------------------------------------------------------
        pulseRX.setPhase(0.0);
        RFPulse pulseRFSpoiler = RFPulse.createRFPulse(getSequence(), Time_rf_spoiling, FreqOffset_RFSpoiling);
        pulseRFSpoiler.setFrequencyOffsetForPhaseShift(is_rf_spoiling ? 117.0 : 0.0);

        // ----------------------------------------------------------------------------------------------
        // modify RX PHASE TABLE to handle OFF CENTER FOV 2D in both cases or PHASE CYCLING
        // ----------------------------------------------------------------------------------------------
        set(Rx_phase, 0);
    }

    @Override
    protected void getPrephaseGrad() {
        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        grad_shape_rise_time = clcGradEqRiseTime(Grad_shape_rise_up, Grad_shape_rise_down, grad_rise_time);

        double grad_phase_application_time = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
        set(Time_grad_phase_top, grad_phase_application_time);
        double readGradientRatio = getDouble(PREPHASING_READ_GRADIENT_RATIO);

        // pre-calculate READ_prephasing max area
        gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableRead) {
            if (!models.get("FlowComp").isEnabled()) {
                gradReadPrep.refocalizeGradient(gradReadout, readGradientRatio);
            } else {
                gradReadPrep.refocalizeGradientWithFlowComp(gradReadout, readGradientRatio, ((FlowComp) models.get("FlowComp")).gradReadPrepFlowComp);
            }
            System.out.println("  gradReadout " + gradReadout.getAmplitude());
            System.out.println("  gradReadout 0  " + gradReadout.getAmplitudeArray(0));
            System.out.println("  gradReadPrep " + gradReadPrep.getAmplitude());
            System.out.println("  gradReadPrep 0  " + gradReadPrep.getAmplitudeArray(0));
        }

        // pre-calculate SLICE_refocusing  &  PHASE_3D
        double grad_ratio_slice_refoc = isEnableSlice ? getDouble(SLICE_REFOCUSING_GRADIENT_RATIO) : 0.0;   // get slice refocussing ratio
        gradSliceRefPhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableSlice) {
            if (!models.get("FlowComp").isEnabled()) {
                gradSliceRefPhase3D.refocalizeGradient(gradSlice, grad_ratio_slice_refoc);
            } else {
                gradSliceRefPhase3D.refocalizeGradientWithFlowComp(gradSlice, grad_ratio_slice_refoc, ((FlowComp) models.get("FlowComp")).gradSliceRefPhase3DFlowComp);
            }
        }

        if (!isMultiplanar && isEnablePhase3D) {
            if (!models.get("FlowComp").isEnabled()) {
                gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            } else {
                gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
                System.out.println("flow comp not supported for PE dir");
                Log.info(getClass(), " flow comp not supported for PE dir");

//                double delta;
//                delta = to do calculate the time from the PE to the Echo
//               gradSliceRefPhase3D.preparePhaseEncodingForCheckWithFlowComp(is_keyhole_allowed ? userMatrixDimension3D : acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred, gradSliceRefPhase3DFlowComp);

            }

            if (isElliptical) {
                System.out.println("gradSliceRefPhase3D " + gradSliceRefPhase3D.getAmplitudeArray(0));
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin);
                System.out.println("gradSliceRefPhase3D " + gradSliceRefPhase3D.getAmplitudeArray(0));
            } else {
                System.out.println("gradSliceRefPhase3D " + gradSliceRefPhase3D.getAmplitudeArray(0));
                gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin, acqMatrixDimension3D);
                System.out.println("gradSliceRefPhase3D " + gradSliceRefPhase3D.getAmplitudeArray(0));
            }
        }
    }

    @Override
    protected void getPEGrad() {
        // pre-calculate PHASE_2D
        gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnablePhase) {
            if (!models.get("FlowComp").isEnabled()) {
                gradPhase2D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension2D : acqMatrixDimension2D, acqMatrixDimension2D, fovPhase, is_k_s_centred);
            } else {
                gradPhase2D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension2D : acqMatrixDimension2D, acqMatrixDimension2D, fovPhase, is_k_s_centred);
                System.out.println("flow comp not suported");
                Log.info(getClass(), " flow comp not suported ");
                //                double delta;
//                delta = to do calculate the time from the PE to the Echo
//                gradPhase2D.preparePhaseEncodingForCheckWithFlowComp(is_keyhole_allowed ? userMatrixDimension2D : acqMatrixDimension2D, acqMatrixDimension2D, fovPhase, is_k_s_centred, gradPhase2DFlowComp, delta);
            }
            if (isElliptical) {
                gradPhase2D.reoderPhaseEncoding(plugin);
            } else {
                System.out.println();
                System.out.println("acquisitionMatrixDimension2D  " + getInt(ACQUISITION_MATRIX_DIMENSION_2D));
                gradPhase2D.reoderPhaseEncoding(plugin, 1, getInt(ACQUISITION_MATRIX_DIMENSION_2D), acqMatrixDimension1D);
            }
        }
    }

    @Override
    protected void getGradOpt() {
        double grad_phase_application_time = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
        double grad_area_sequence_max = 100 * (grad_phase_application_time + grad_shape_rise_time);
        double grad_area_max = Math.max(gradReadPrep.getTotalAbsArea(), Math.max(gradSliceRefPhase3D.getTotalAbsArea(), gradPhase2D.getTotalAbsArea()));            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING
        if (grad_area_max > grad_area_sequence_max) {
            double grad_phase_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 6);
            notifyOutOfRangeParam(GRADIENT_PHASE_APPLICATION_TIME, grad_phase_application_time_min, ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_phase_application_time = grad_phase_application_time_min;
            set(Time_grad_phase_top, grad_phase_application_time);
            gradPhase2D.rePrepare();
            gradSliceRefPhase3D.rePrepare();
            gradReadPrep.rePrepare();
        }

        if (isElliptical) {
            gradSliceRefPhase3D.applyAmplitude(Order.Two);
        } else {
            gradSliceRefPhase3D.applyAmplitude(Order.Three);
        }

        gradPhase2D.applyAmplitude(Order.Two);
        gradReadPrep.applyAmplitude();
    }

    @Override
    protected void getSpoilerGrad() {
        double readGradientRatio = getDouble(PREPHASING_READ_GRADIENT_RATIO);
        double grad_ratio_slice_refoc = isEnableSlice ? getDouble(SLICE_REFOCUSING_GRADIENT_RATIO) : 0.0;   // get slice refocussing ratio
        // -------------------------------------------------------------------------------------------------
        // calculate Phase 2D, 3D and Read REWINDING - SPOILER area, check Grad_Spoil < GMAX
        // -------------------------------------------------------------------------------------------------

        // timing : grad_phase_application_time must be < grad_spoiler_application_time if rewinding
        //  boolean is_grad_rewinding = getBoolean(GRADIENT_ENABLE_REWINDING);// get slice refocussing ratio
        double grad_phase_application_time = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
        double grad_spoiler_application_time = getDouble(GRADIENT_SPOILER_TIME);
        if (is_grad_rewinding && grad_phase_application_time > grad_spoiler_application_time) {
            notifyOutOfRangeParam(GRADIENT_SPOILER_TIME, grad_phase_application_time, ((NumberParam) getParam(GRADIENT_SPOILER_TIME)).getMaxValue(), "Gradient Spoiler top time must be longer than Phase Application Time");
            grad_spoiler_application_time = grad_phase_application_time;
        }
        set(Time_grad_spoiler_top, grad_spoiler_application_time);

        Gradient gradSliceSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_slice, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        Gradient gradPhaseSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_phase, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        Gradient gradReadSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_read, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);

        // Rewinding :
        if (is_grad_rewinding) {
            if (isEnablePhase3D)
                gradSliceSpoiler.refocalizePhaseEncodingGradient(gradSliceRefPhase3D);
            if (isEnableSlice)
                gradSliceSpoiler.refocalizeGradient(gradSlice, 1 - grad_ratio_slice_refoc);
            if (isEnablePhase)
                gradPhaseSpoiler.refocalizePhaseEncodingGradient(gradPhase2D);
            if (isEnableRead)
                if (is_flyback)
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadoutFlyback, readGradientRatio);
                else
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadout, 1 - (readGradientRatio));
        }
        // Spoiler :
        //    List<Double> grad_amp_spoiler_sl_ph_re = getListDouble(grad_amp_spoiler_sl_ph_re);
        if (getBoolean(GRADIENT_ENABLE_SPOILER)) {
            double spoilerAmp = grad_amp_spoiler_sl_ph_re.get(0);
            if (!gradSliceSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.set(0, spoilerAmp - gradSliceSpoiler.getSpoilerExcess());

            spoilerAmp = grad_amp_spoiler_sl_ph_re.get(1);
            if (!gradPhaseSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.set(1, spoilerAmp - gradPhaseSpoiler.getSpoilerExcess());

            spoilerAmp = grad_amp_spoiler_sl_ph_re.get(2);
            if (!gradReadSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.set(2, spoilerAmp - gradReadSpoiler.getSpoilerExcess());
        }
        gradPhaseSpoiler.applyAmplitude();
        gradSliceSpoiler.applyAmplitude();
        gradReadSpoiler.applyAmplitude();
    }

    @Override
    protected void getUPDisp() {
        this.getParam(TX_ATT).setValue(pulseTX.getAttParamValue());            // display PULSE_ATT
        this.getParam(TX_AMP).setValue(pulseTX.getAmp());     // display 90° amplitude
        this.getParam(TX_AMP_90).setValue(pulseTX.getAmp90());     // display 90° amplitude
        this.getParam(TX_AMP_180).setValue(pulseTX.getAmp180());   // display 180° amplitude
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
//                    effectiveEchoSpacing = effectiveEchoSpacingMin;
//                    getParam("INTERLEAVED_EFF_ECHO_SPACING").setValue( effectiveEchoSpacing);
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
//        min_flush_delay = min_time_per_acq_point * acqMatrixDimension1D * echoTrainLength * nb_slices_acquired_in_single_scan * 2;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
//        min_flush_delay = Math.max(CameleonVersion105 ? min_flush_delay : 0, minInstructionDelay);
        min_flush_delay = minInstructionDelay;

//        double time_seq_to_end_spoiler = delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler) * nb_slices_acquired_in_single_scan;
//        double tr_min = time_seq_to_end_spoiler + minInstructionDelay * (nb_slices_acquired_in_single_scan * 2 + 1) + min_flush_delay;// 2 +( 2 minInstructionDelay: Events. 22 +(20&21

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
                //tr_delay = (tr - (tmp_time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_slices_acquired_in_single_scan - minInstructionDelay;
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

    @Override
    protected void getAcqTime() {
        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCEprepSeqTiming
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------
        double frame_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal(frame_acquisition_time + minInstructionDelay + min_flush_delay, 1);
        time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (isDynamic && (acqMatrixDimension4D > 1)) {
            //Dynamic Sequence
            time_between_frames = getDouble(DYN_TIME_BTW_FRAMES);
            if (isDynamicMinTime) {
                time_between_frames = time_between_frames_min;
                getParam(DYN_TIME_BTW_FRAMES).setValue(time_between_frames_min);
            } else if (time_between_frames < (time_between_frames_min)) {
                notifyOutOfRangeParam(DYN_TIME_BTW_FRAMES, time_between_frames_min, ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getMaxValue(), "Minimum frame acquisition time ");
                time_between_frames = time_between_frames_min;
            }
            interval_between_frames_delay = Math.max(time_between_frames - frame_acquisition_time, min_flush_delay);
        }

        set(Time_btw_dyn_frames, interval_between_frames_delay);
        // ------------------------------------------------------------------
        // Total Acquisition Time
        // ------------------------------------------------------------------
        double total_acquisition_time;
        if (!isMultiplanar) {
            total_acquisition_time = time_between_frames * Math.ceil(acqMatrixDimension4D / nb_interleaved_slice) + tr * nb_preScan;
        } else {
            total_acquisition_time = time_between_frames * nb_dynamic_acquisition + tr * nb_preScan;
        }
        getParam(SEQUENCE_TIME).setValue(total_acquisition_time);

    }

    @Override
    protected void getMultiParaList() {
        double frame_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;

        int number_of_MultiSeries = 1;
        double time_between_MultiSeries = 0;
        ArrayList<Number> multiseries_valuesList = new ArrayList<>();
        String multiseries_parametername = "";

        if (echoTrainLength != 1) {
            number_of_MultiSeries = echoTrainLength;
            time_between_MultiSeries = te;
            multiseries_parametername = "TE";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = roundToDecimal((te + i * echo_spacing), 5) * 1e3;
                multiseries_valuesList.add(multiseries_value);
            }
        } else if (models.get("ExtTrig").isEnabled() && ((ExtTrig) models.get("ExtTrig")).nb_trigger != 1) {
            number_of_MultiSeries = ((ExtTrig) models.get("ExtTrig")).nb_trigger;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TRIGGER DELAY";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round(((ExtTrig) models.get("ExtTrig")).triggerTime.get(i) * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        }
        getParam(MULTISERIES_PARAMETER_VALUE).setValue(multiseries_valuesList);
        getParam(MULTISERIES_PARAMETER_NAME).setValue(multiseries_parametername);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();
        double acqusition_time;
        for (int i = 0; i < nb_dynamic_acquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acqusition_time = roundToDecimal((i * time_between_frames + j * time_between_MultiSeries), 3);
                acquisition_timesList.add(acqusition_time);
            }
        }
        getParam(ACQUISITION_TIME_OFFSET).setValue(acquisition_timesList);
    }

    private void getGradRiseTime() {
        double min_rise_time_factor = getDouble(MIN_RISE_TIME_FACTOR);
        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            notifyOutOfRangeParam(GRADIENT_RISE_TIME, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }
        set(Time_grad_ramp, grad_rise_time);
    }

    private void getFlybackGrad() {
        timeGradTopFlyback = is_flyback ? getDouble(GRADIENT_FLYBACK_TIME) : minInstructionDelay;
        set(Time_flyback, timeGradTopFlyback);

        time_flyback_ramp = is_flyback ? grad_rise_time : minInstructionDelay;
        set(Time_grad_ramp_flyback, time_flyback_ramp);

        gradReadoutFlyback = Gradient.createGradient(getSequence(), Grad_amp_flyback, Time_flyback, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flyback, nucleus);
        if (is_flyback) {
            gradReadoutFlyback.refocalizeTotalGradient(gradReadout);
            double grad_area_max = gradReadoutFlyback.getTotalAbsArea();
            double grad_area_sequence_max = 100 * (timeGradTopFlyback + grad_shape_rise_time);
            if (grad_area_max > grad_area_sequence_max) {
                double grad_time_flyback_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
                timeGradTopFlyback = grad_time_flyback_min;
                getParam(GRADIENT_FLYBACK_TIME).setValue(timeGradTopFlyback);
                set(Time_flyback, timeGradTopFlyback);
                gradReadoutFlyback.rePrepare();
            }
            gradReadoutFlyback.applyAmplitude();
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    protected void addUserParams() {
        addMissingUserParams(U.values());
    }

    public String getName() {
        return "GRADIENT_ECHO";
    }

    public String getVersion() {
        return "develop_xg";
    }
    //</editor-fold>
}