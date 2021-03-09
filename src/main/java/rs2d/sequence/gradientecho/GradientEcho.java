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

import rs2d.commons.log.Log;
import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.SequenceTool;
import rs2d.spinlab.sequence.element.TimeElement;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequence.table.Utility;
import rs2d.spinlab.sequenceGenerator.util.GradientRotation;
import rs2d.spinlab.sequenceGenerator.util.Hardware;
import rs2d.spinlab.sequenceGenerator.util.TimeEvents;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.GradientAxe;
import rs2d.spinlab.tools.utility.Nucleus;

import java.util.*;

import static java.util.Arrays.asList;

import rs2d.sequence.common.*;

import static rs2d.sequence.gradientecho.S.*;
import static rs2d.sequence.gradientecho.U.*;

// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class GradientEcho extends SeqPrep/*BaseSequenceGenerator*/ {

    private String sequenceVersion = "Version11.1";
    private boolean CameleonVersion105 = false;
    private double protonFrequency;
    private double observeFrequency;
    private double min_time_per_acq_point;
    private double gMax;
    private TransformPlugin plugin;
    private Nucleus nucleus;

    private boolean isMultiplanar;

    private int acquisitionMatrixDimension1D;
    private int acqMatrixDimension2D; // length of the sampled k-space in 2D direction
    private int acqMatrixDimension3D; // length of the sampled k-space in 3D direction
    private int acquisitionMatrixDimension4D;
    private int preScan;

    private int number_of_averages;
    private int userMatrixDimension1D;
    private int userMatrixDimension2D;
    private int userMatrixDimension3D;
    private boolean is_partial_oversampling;
    private boolean is_keyhole_allowed;

    private int nb_scan_1d;
    private int nb_scan_2d;
    private int nb_scan_3d;
    private int nb_scan_4d;
    private int nbOfInterleavedSlice;
    private int nb_of_shoot_3d;
    private int nb_planar_excitation;
    private int nb_slices_acquired_in_single_scan;

    private int nbPE;
    private String kspace_filling;
    private boolean isElliptical;
    private int echoTrainLength;
    private double echo_spacing;


    private double spectralWidth;
    private boolean isSW;
    private double tr;
    private double te;
    private double min_flush_delay;

    private double sliceThickness;
    private double slice_thickness_excitation;
    private double spacingBetweenSlice;
    private double pixelDimension;
    private double fov;
    private double fovPhase;
    private double fov3d;
    private boolean isFovDoubled;
    private double off_center_distance_1D;
    private double off_center_distance_2D;
    private double off_center_distance_3D;

    private double txLength90;
    private List<Double> grad_amp_spoiler_sl_ph_re;

    //Dynamic
    private boolean isDynamic;
    private int numberOfDynamicAcquisition;
    private boolean isDynamicMinTime;
    private double time_between_frames;

    //Ext Trigger
    private boolean isTrigger;
    private List<Double> triggerTime;
    private int numberOfTrigger;
    private double time_external_trigger_delay_max;
    Table triggerdelay;

    private boolean is_flyback;
    private boolean is_flowcomp;

    //Fat Sat
    boolean is_fatsat_enabled;

    //Sat Band
    private int nb_satband;
    private boolean is_satband_enabled;
    private int[] position_sli_ph_rea;
    private boolean is_tof_enabled;

    //Fly Back
    private double timeGradTopFlyback;
    private double time_flyback_ramp;

    private boolean is_interleaved_echo_train;
    private int numberOfnumInterleavedEchoTrain;

    private boolean is_rf_spoiling;

    private boolean isKSCenterMode;

    private boolean isEnablePhase;
    private boolean isEnablePhase3D;
    private boolean isEnableSlice;
    private boolean isEnableRead;

    boolean is_grad_rewinding;
    boolean is_grad_spoiler;
    boolean is_k_s_centred;

    private double observation_time;
    private double grad_shape_rise_time;
    private double grad_rise_time;

    private RFPulse pulseTXFatSat;
    private RFPulse pulseTXSatBandTOF;
    private RFPulse pulseTX;

    private Gradient gradPhase2D;
    private Gradient gradReadPrep;
    private Gradient gradReadout;
    private Gradient gradReadoutFlyback;
    private Gradient gradSlice;
    private Gradient gradSliceRefPhase3D;
    private Gradient gradPhase2DFlowComp;
    private Gradient gradReadPrepFlowComp;
    private Gradient gradSliceRefPhase3DFlowComp;

    // get hardware memory limit
    private int offset_channel_memory = 512;
    private int phase_channel_memory = 512;
    private int amp_channel_memory = 2048;
    private int loopIndice_memory = 2048;

    private double defaultInstructionDelay = 0.000010;     // single instruction minimal duration
    private double minInstructionDelay = 0.000005;     // single instruction minimal duration

    public GradientEcho() {
        super(U.class);
        addUserParams();
    }

    @Override
    public void init() {
        super.init();

        // Define default, min, max and suggested values regarding the instrument
        getParam(MAGNETIC_FIELD_STRENGTH).setDefaultValue(Instrument.instance().getDevices().getMagnet().getField());
        getParam(DIGITAL_FILTER_SHIFT).setDefaultValue(Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount());
        getParam(DIGITAL_FILTER_REMOVED).setDefaultValue(Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint());

        List<String> tx_shape = asList(
                "HARD",
                "GAUSSIAN",
                "SINC3",
                "SINC5",
                "SLR_8_5152",
                "SLR_4_2576");
        ((TextParam) getParam(TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE)).setRestrictedToSuggested(true);

        ((TextParam) getParam(SATBAND_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(SATBAND_TX_SHAPE)).setRestrictedToSuggested(true);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setRestrictedToSuggested(true);

        //TRANSFORM PLUGIN
        TextParam transformPlugin = getParam(TRANSFORM_PLUGIN);
        transformPlugin.setSuggestedValues(asList("Sequential4D", "Sequential4DBackAndForth", "EPISequential4D", "Centric4D", "Elliptical3D"));
        transformPlugin.setRestrictedToSuggested(true);

        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        TextParam triggerChanel = getParam(TRIGGER_CHANEL);
        triggerChanel.setSuggestedValues(asList(
                SequenceTool.ExtTrigSource.Ext1.name(),
                SequenceTool.ExtTrigSource.Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_AND_Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_XOR_Ext2.name()));
        triggerChanel.setRestrictedToSuggested(true);

        // KSPACE_FILLING
        TextParam ksFilling = getParam(KSPACE_FILLING);
        ksFilling.setSuggestedValues(asList("Linear", "Centric", "3DElliptic"));// temporary removed "3DElliptic"
        ksFilling.setRestrictedToSuggested(true);


        List<String> satbandOrientationAllowed = asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL",
                "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR",
                "RIGHT", "LEFT", "RIGHT AND LEFT",
                "ALL");
        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        ((TextParam) getParam(SATBAND_ORIENTATION)).setSuggestedValues(satbandOrientationAllowed);
        ((TextParam) getParam(SATBAND_ORIENTATION)).setRestrictedToSuggested(true);

        protonFrequency = Math.ceil(Instrument.instance().getDevices().getMagnet().getProtonFrequency() / Math.pow(10, 6));
        double fatFreq = protonFrequency * 3.5;
        ((NumberParam) getParam(FATSAT_BANDWIDTH)).setDefaultValue(fatFreq);
        ((NumberParam) getParam(FATSAT_OFFSET_FREQ)).setDefaultValue(fatFreq);
    }

    // ==============================
// -----   GENERATE
// ==============================
    public void generate() throws Exception {
        initUserParam();
        this.beforeRouting();
        if (!this.isRouted()) {
            this.route();
            this.initAfterRouting();//init before setup
        }
        //   if (!getBoolean( SETUP_MODE)) {
        this.afterRouting();    //avoid exception during setup
        // }

        this.checkAndFireException();
    }

    private void initUserParam() {
        isMultiplanar = getBoolean(MULTI_PLANAR_EXCITATION);

//        acquisitionMatrixDimension1D = getInt(ACQUISITION_MATRIX_DIMENSION_1D);
        acqMatrixDimension2D = getInt(ACQUISITION_MATRIX_DIMENSION_2D);
        acqMatrixDimension3D = getInt(ACQUISITION_MATRIX_DIMENSION_3D);
        acquisitionMatrixDimension4D = getInt(ACQUISITION_MATRIX_DIMENSION_4D);
        preScan = getInt(DUMMY_SCAN);

        number_of_averages = getInt(NUMBER_OF_AVERAGES);
        userMatrixDimension1D = getInt(USER_MATRIX_DIMENSION_1D);
        userMatrixDimension2D = getInt(USER_MATRIX_DIMENSION_2D);
        userMatrixDimension3D = getInt(USER_MATRIX_DIMENSION_3D);
        is_partial_oversampling = getBoolean(PARTIAL_OVERSAMPLING);
        is_keyhole_allowed = getBoolean(KEYHOLE_ALLOWED);

        kspace_filling = getText(KSPACE_FILLING);
        isElliptical = kspace_filling.equalsIgnoreCase("3DElliptic");

        echoTrainLength = getInt(ECHO_TRAIN_LENGTH);
        echo_spacing = getDouble(ECHO_SPACING);
        nb_of_shoot_3d = getInt(NUMBER_OF_SHOOT_3D);

        spectralWidth = getDouble(SPECTRAL_WIDTH);            // get user defined spectral width
        isSW = getBoolean(SPECTRAL_WIDTH_OPT);
        tr = getDouble(REPETITION_TIME);
        te = getDouble(ECHO_TIME);

        sliceThickness = getDouble(SLICE_THICKNESS);
        spacingBetweenSlice = getDouble(SPACING_BETWEEN_SLICE);
        pixelDimension = getDouble(RESOLUTION_FREQUENCY);
        fov = getDouble(FIELD_OF_VIEW);
        fovPhase = getDouble(FIELD_OF_VIEW_PHASE);
        isFovDoubled = getBoolean(FOV_DOUBLED);
        off_center_distance_1D = getDouble(OFF_CENTER_FIELD_OF_VIEW_1D);
        off_center_distance_2D = getDouble(OFF_CENTER_FIELD_OF_VIEW_2D);
        off_center_distance_3D = getDouble(OFF_CENTER_FIELD_OF_VIEW_3D);

        txLength90 = getDouble(TX_LENGTH);


        isDynamic = getBoolean(DYNAMIC_SEQUENCE);
        numberOfDynamicAcquisition = isDynamic ? getInt(DYN_NUMBER_OF_ACQUISITION) : 1;
        isDynamic = isDynamic && (numberOfDynamicAcquisition > 1);
        isDynamicMinTime = getBoolean(DYNAMIC_MIN_TIME);

        isTrigger = getBoolean(TRIGGER_EXTERNAL);
        triggerTime = getListDouble(TRIGGER_TIME);
        numberOfTrigger = isTrigger ? triggerTime.size() : 1;
        isTrigger = isTrigger && (numberOfTrigger > 0);

        is_flyback = getBoolean(FLYBACK);

        is_flowcomp = getBoolean(FLOW_COMPENSATION);

        is_fatsat_enabled = getBoolean(FAT_SATURATION_ENABLED);

        is_tof_enabled = getBoolean(TOF2D_ENABLED);
        is_tof_enabled = !isMultiplanar ? false : is_tof_enabled; // TOF not allowed in 3D
        getParam(TOF2D_ENABLED).setValue(is_tof_enabled);


        is_satband_enabled = getBoolean(SATBAND_ENABLED);
        is_satband_enabled = is_tof_enabled ? false : is_satband_enabled;
        getParam(SATBAND_ENABLED).setValue(is_satband_enabled);

        position_sli_ph_rea = satBandPrep(SATBAND_ORIENTATION, ORIENTATION, IMAGE_ORIENTATION_SUBJECT);
        nb_satband = is_satband_enabled ? (int) Arrays.stream(position_sli_ph_rea).filter(item -> item == 1).count() : 1;
        nb_satband = is_tof_enabled ? 1 : nb_satband;

        is_rf_spoiling = getBoolean(RF_SPOILING);

        is_interleaved_echo_train = getBoolean(INTERLEAVED_ECHO_TRAIN);
        numberOfnumInterleavedEchoTrain = getInt(INTERLEAVED_NUM_OF_ECHO_TRAIN);

        isKSCenterMode = getBoolean(KS_CENTER_MODE);

        isEnablePhase3D = !isKSCenterMode && getBoolean(GRADIENT_ENABLE_PHASE_3D);
        isEnablePhase = !isKSCenterMode && getBoolean(GRADIENT_ENABLE_PHASE);
        isEnableSlice = getBoolean(GRADIENT_ENABLE_SLICE);
        isEnableRead = getBoolean(GRADIENT_ENABLE_READ);

        is_grad_rewinding = getBoolean(GRADIENT_ENABLE_REWINDING);// get slice refocussing ratio
        is_grad_spoiler = getBoolean(GRADIENT_ENABLE_SPOILER);// get slice refocussing ratio
        grad_amp_spoiler_sl_ph_re = getListDouble(GRAD_AMP_SPOILER_SL_PH_RE);
        is_k_s_centred = getBoolean(KS_CENTERED);

        observation_time = getDouble(ACQUISITION_TIME_PER_SCAN);
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          INIT AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void initAfterRouting() {
        set(Time_min_instruction, minInstructionDelay);
        // -----------------------------------------------
        // Phase Reset
        // -----------------------------------------------
        set(Phase_reset, PHASE_RESET);
        set(Frequency_offset_init, 0.0);// PSD should start with a zero offset frequency pulse

        // -----------------------------------------------
        // enable gradient lines
        // -----------------------------------------------
        iniEnableSeqParam();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING --- BEFORE ROUTING ---
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          BEFORE ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void beforeRouting() throws Exception {
        Log.debug(getClass(), "------------ BEFORE ROUTING -------------");
        //  System.out.println(" BEFORE ROUTING ");
        getParam(SEQUENCE_VERSION).setValue(sequenceVersion);
        getParam(MODALITY).setValue("MRI");

        // -----------------------------------------------
        // RX parameters : nucleus, RX gain & frequencies
        // -----------------------------------------------
        iniTxRx();

        // -----------------------------------------------
        // Ini Acquisition Matrix
        // -----------------------------------------------
        iniAcqMat();

        // -----------------------------------------------
        // Ini TransformPlugin
        // -----------------------------------------------
        iniTransformPlugin();

        // -----------------------------------------------
        // Ini Nb XD
        // -----------------------------------------------
        iniScanLoop();

        // -----------------------------------------------
        // Ini SEQ_DESCRIPTION
        // -----------------------------------------------
        iniSeqDisp();

        // -----------------------------------------------
        // Ini Image Orientation
        // -----------------------------------------------
        iniImgOrientation();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING ---  AFTER ROUTING ---
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void afterRouting() throws Exception {
        Log.debug(getClass(), "------------ AFTER ROUTING -------------");

        // -----------------------------------------------
        // Calculation RF pulse parameters
        // -----------------------------------------------
        prepRFandSliceGrad();

        // -----------------------------------------------
        // prep Imaging related gradients
        // -----------------------------------------------
        prepImagingGrads();

        //--------------------------------------------------------------------------------------
        // prep Flyback gradient
        //--------------------------------------------------------------------------------------
        prepFlybackGrad();

        //--------------------------------------------------------------------------------------
        // prep External triggering
        //--------------------------------------------------------------------------------------
        prepExtTrig();

        // -------------------------------------------------------------------------------------------------
        // prep Inversion Recovery gradients
        // -------------------------------------------------------------------------------------------------
        prepIR();

        //--------------------------------------------------------------------------------------
        // prep Fat Sat gradients
        //--------------------------------------------------------------------------------------
        prepFatSatGrad();

        // ------------------------------------------------------------------
        // prep blanking smartTTL_FatSat_table
        // ------------------------------------------------------------------
        //prepFatSatSmartTTL();

        //--------------------------------------------------------------------------------------
        // prep Flow
        //--------------------------------------------------------------------------------------
        prepFlow();

        //--------------------------------------------------------------------------------------
        // prep Sat Band gradients
        //--------------------------------------------------------------------------------------
        prepSatBandGrad();

        // --------------------------------------------------------------------------------------------------------------------------------------------
        // TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING
        // --------------------------------------------------------------------------------------------------------------------------------------------
        prepSeqTiming();

        //--------------------------------------------------------------------------------------
        //Export DICOM
        //--------------------------------------------------------------------------------------
        prepDicom();

        //--------------------------------------------------------------------------------------
        // Comments
        //--------------------------------------------------------------------------------------
        //prepComments();
    }

    //--------------------------------------------------------------------------------------
    // prep functions
    //--------------------------------------------------------------------------------------
    private void iniTxRx() throws Exception {
        nucleus = Nucleus.getNucleusForName(getText(NUCLEUS_1));
        protonFrequency = Instrument.instance().getDevices().getMagnet().getProtonFrequency();
        double freq_offset1 = getDouble(OFFSET_FREQ_1);
        observeFrequency = nucleus.getFrequency(protonFrequency) + freq_offset1;
        getParam(BASE_FREQ_1).setValue(nucleus.getFrequency(protonFrequency));

        min_time_per_acq_point = Hardware.getSequenceCompiler().getTransfertTimePerDataPt();
        gMax = GradientMath.getMaxGradientStrength();

        set(Rx_gain, RECEIVER_GAIN);
        getParam(RECEIVER_COUNT).setValue(Instrument.instance().getObservableRxs(nucleus).size());

        set(Intermediate_frequency, Instrument.instance().getIfFrequency());
        getParam(INTERMEDIATE_FREQUENCY).setValue(Instrument.instance().getIfFrequency());

        set(Tx_frequency, observeFrequency);
        getParam(OBSERVED_FREQUENCY).setValue(observeFrequency);

        set(Tx_nucleus, nucleus);
        getParam(OBSERVED_NUCLEUS).setValue(nucleus);
    }

    private void iniAcqMat() throws Exception {
        // -----------------------------------------------
        // 1stD managment
        // -----------------------------------------------
        getAcq1D();

        // -----------------------------------------------
        // 2nd D managment
        // -----------------------------------------------
        getAcq2D();

        // -----------------------------------------------
        // 3nd D managment
        // -----------------------------------------------
        getAcq3D();

//        // -----------------------------------------------
//        // 3D managment 2/2: MEMORY LIMITATION & Manage PE trajectory
//        // -----------------------------------------------
//        reprepAcq3D();

        // -----------------------------------------------
        // 4D managment:  Dynamic, MultiEcho, External triggering, Multi Echo
        // -----------------------------------------------
        getAcq4D();

        // -----------------------------------------------
        // Image Resolution
        // -----------------------------------------------
        getImgRes();
    }

    private void iniTransformPlugin() throws Exception {
        ///// transform plugin
        switch (kspace_filling) {
            case "Linear":
                getParam(TRANSFORM_PLUGIN).setValue("Sequential4DBackAndForth");
                if (is_flyback) {
                    getParam(TRANSFORM_PLUGIN).setValue("Sequential4D");
                }
                break;
            case "Centric":
                getParam(TRANSFORM_PLUGIN).setValue("Centric4D");
                break;
            case "3DElliptic":
                if (isMultiplanar) {
                    kspace_filling = "Linear";
                    getParam(KSPACE_FILLING).setValue(kspace_filling);
                } else {
                    getParam(TRANSFORM_PLUGIN).setValue("Elliptical3D");
                }
                break;
            default:
                kspace_filling = "Linear";
                getParam(KSPACE_FILLING).setValue(kspace_filling);
                break;
        }

        plugin = getTransformPlugin();
        plugin.setParameters(new ArrayList<>(getUserParams()));
        plugin.getScanOrder(); //output traj. graphs for Elliptical3D plugin
    }

    private void iniScanLoop() {
        getParam(ACQUISITION_MATRIX_DIMENSION_1D).setValue(acquisitionMatrixDimension1D);

        nb_planar_excitation = (isMultiplanar ? acqMatrixDimension3D : 1);
        String updateDimension = SequenceTool.UpdateDimensionEnum.Disable.getText();
        if (!isMultiplanar) {
            System.out.println(" kspace_filling " + kspace_filling);
            if (!isElliptical) {
                System.out.println(" non 3DElliptic " + acqMatrixDimension2D);
                nb_scan_2d = acqMatrixDimension2D;
                nb_scan_3d = acqMatrixDimension3D;
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
            nb_scan_3d = nb_of_shoot_3d;
        }

        //Dynamic and multi echo are filled into the 4th Dimension
        nb_scan_4d = numberOfTrigger * numberOfDynamicAcquisition;
        getParam(USER_MATRIX_DIMENSION_4D).setValue(nb_scan_4d);

        nb_planar_excitation = (isMultiplanar ? acqMatrixDimension3D : 1);
        nb_slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nbOfInterleavedSlice) : 1;

        if (isKSCenterMode) { // Do only the center of the k-space for auto RG
            nb_scan_1d = 1;
            nb_scan_2d = 2;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            nb_scan_4d = 1;
            getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(!isMultiplanar ? 1 : acqMatrixDimension3D);
            getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(1);
        }

        // set Nb_scan  Values
        set(Pre_scan, preScan); // Do the prescan
        set(Nb_point, acquisitionMatrixDimension1D);
        set(Nb_1d, nb_scan_1d);
        set(Nb_2d, nb_scan_2d);
        set(Nb_3d, nb_scan_3d);
        set(Update_dimension, updateDimension);
        set(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions
        set(Nb_echo, echoTrainLength - 1);
        set(Nb_interleaved_slice, nbOfInterleavedSlice - 1);
        set(Nb_sb_loop, nb_satband - 1);
    }

    private void iniSeqDisp() {
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
        if (acquisitionMatrixDimension4D != 1) {
            seqMatrixDescription += "x" + acquisitionMatrixDimension4D;
        }
        seqDescription += seqMatrixDescription;

        if (echoTrainLength != 1) {
            seqDescription += "_ETL=" + echoTrainLength;
        }
        if (isTrigger && numberOfTrigger != 1) {
            seqDescription += "_TRIG=" + numberOfTrigger;
        } else if (isTrigger) {
            seqDescription += "_TRIG";
        }
        if (isDynamic) {
            seqDescription += "_DYN=" + numberOfDynamicAcquisition;
        }
        if (is_satband_enabled) {
            seqDescription += "_SATBAND";
        }
        if (is_fatsat_enabled) {
            seqDescription += "_FATSAT";
        }
        getParam(SEQ_DESCRIPTION).setValue(seqDescription);
    }

    private void iniImgOrientation() {
        //READ PHASE and SLICE matrix
        off_center_distance_1D = getOff_center_distance_1D_2D_3D(1);
        off_center_distance_2D = getOff_center_distance_1D_2D_3D(2);
        off_center_distance_3D = getOff_center_distance_1D_2D_3D(3);

        //Offset according to ENABLE READ PHASE and SLICE
        off_center_distance_1D = !isEnableRead ? 0 : off_center_distance_1D;
        off_center_distance_2D = !isEnablePhase ? 0 : off_center_distance_2D;

        if (!isEnableSlice && (isMultiplanar || !isEnablePhase3D)) {
            off_center_distance_3D = 0;
        }

        getParam(OFF_CENTER_FIELD_OF_VIEW_X).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(1, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        getParam(OFF_CENTER_FIELD_OF_VIEW_Y).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(2, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        getParam(OFF_CENTER_FIELD_OF_VIEW_Z).setValue(roundToDecimal(getOff_center_distance_X_Y_Z(3, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));


        boolean is_read_phase_inverted = getBoolean(SWITCH_READ_PHASE);
        if (is_read_phase_inverted) {
            set(Gradient_axe_phase, GradientAxe.R);
            set(Gradient_axe_read, GradientAxe.P);
            double off_center_distance_tmp = off_center_distance_2D;
            off_center_distance_2D = off_center_distance_1D;
            off_center_distance_1D = off_center_distance_tmp;
        } else {
            set(Gradient_axe_phase, GradientAxe.P);
            set(Gradient_axe_read, GradientAxe.R);
        }
        getParam(OFF_CENTER_FIELD_OF_VIEW_3D).setValue(off_center_distance_3D);
        getParam(OFF_CENTER_FIELD_OF_VIEW_2D).setValue(off_center_distance_2D);
        getParam(OFF_CENTER_FIELD_OF_VIEW_1D).setValue(off_center_distance_1D);

        // -----------------------------------------------
        // activate gradient rotation matrix
        // -----------------------------------------------
        GradientRotation.setSequenceGradientRotation(this);
    }

    //--------------------------------------------------------------------------------------
    private void iniEnableSeqParam() {
        set(Grad_enable_read, isEnableRead);              // pass gradient line status to sequence
        set(Grad_enable_phase_2D, isEnablePhase);
        set(Grad_enable_phase_3D, (!isMultiplanar && isEnablePhase3D));
        set(Grad_enable_slice, isEnableSlice);

        set(Grad_enable_spoiler_slice, (((!isMultiplanar && is_grad_rewinding && isEnablePhase3D) || (is_grad_rewinding && isEnableSlice) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(0) != 0)))));
        set(Grad_enable_spoiler_phase, (isEnablePhase && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(1) != 0))));
        set(Grad_enable_spoiler_read, (isEnableRead && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.get(2) != 0))));
        set(Grad_enable_flowcomp, is_flowcomp);
        set(Grad_enable_flyback, is_flyback);

        set(Enable_fatsat, is_fatsat_enabled);
        set(Enable_sb, is_satband_enabled);
    }

    private void prepRFandSliceGrad() throws Exception {
        getGradRiseTime();
        // -----------------------------------------------
        // Calculation RF pulse parameters  1/4 : Pulse declaration & Fatsat Flip angle calculation
        // -----------------------------------------------

        set(Time_tx, txLength90);    // set RF pulse length to sequence
        pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset);

        // Fat SAT RF pulse
        double tx_bandwidth_90_fs = getDouble(FATSAT_BANDWIDTH);
        double tx_bandwidth_factor_90_fs = getTx_bandwidth_factor(FATSAT_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_length_90_fs = is_fatsat_enabled ? tx_bandwidth_factor_90_fs / tx_bandwidth_90_fs : minInstructionDelay;
        getParam(FATSAT_TX_LENGTH).setValue(tx_length_90_fs);
        set(Time_tx_fatsat, tx_length_90_fs);

        // FatSAT timing seting needed for Flip Angle calculation
        set(Time_grad_fatsat, is_fatsat_enabled ? getDouble(FATSAT_GRAD_APP_TIME) : minInstructionDelay);
        set(Time_before_fatsat_pulse, is_fatsat_enabled ? minInstructionDelay : minInstructionDelay); //    <<<<<<<<<<<<<<<<<<<<<<<<<<< blanking ON
        set(Time_grad_ramp_fatsat, is_fatsat_enabled ? grad_rise_time : minInstructionDelay);
        //
        pulseTXFatSat = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_fatsat, Tx_phase_fatsat, Time_tx_fatsat, Tx_shape_fatsat, Tx_shape_phase_fatsat, Freq_offset_tx_fatsat);

        // SAT Band or TOF RF pulse
        set(Time_grad_ramp_sb, is_satband_enabled || is_tof_enabled ? grad_rise_time : minInstructionDelay);
        set(Time_grad_sb, is_satband_enabled || is_tof_enabled ? 0.0005 : minInstructionDelay);
        double tx_length_sb = is_satband_enabled || is_tof_enabled ? txLength90 : minInstructionDelay;
        set(Time_tx_sb, tx_length_sb);
        //
        pulseTXSatBandTOF = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_sb, Tx_phase_sb, Time_tx_sb, Tx_shape_sb, Tx_shape_phase_sb, Freq_offset_tx_sb);
        //
        // Correction of the 90 water saturation RF angle according to the water T1 relaxation.
        // double time_tau_sat = 0.0 / 1000.0; // TODO
        double flip_angle = getDouble(FLIP_ANGLE);
        flip_angle = is_tof_enabled && pulseTXSatBandTOF.isSlr() ? 90 : flip_angle;
        getParam(FLIP_ANGLE).setValue(flip_angle);
        double flip_angle_satband = 0;
        if (is_satband_enabled || is_tof_enabled) {
//            flip_angle_satband = 90;
            double time_tau_sat = TimeEvents.getTimeBetweenEvents(getSequence(), Events.FatSatPulse.ID, Events.P90.ID); /////////////////////////////////////////////////////////////////////////////////////////////////////////
            double time_t1_satband = getDouble(SATBAND_T1);
            double t1_relax_time_sat = time_t1_satband / 1000.0;   // T1_tissue = 500ms
            //
            double flip_90_sat = flip_angle == 90 ? Math.acos((1 - Math.exp(time_tau_sat / t1_relax_time_sat)) / (1 - Math.exp((time_tau_sat - tr) / t1_relax_time_sat))) : Math.acos(1 - Math.exp(time_tau_sat / t1_relax_time_sat));
            double flip_90_sat_degree = Math.toDegrees((is_tof_enabled ? 1.5 : 1) * flip_90_sat);
            flip_angle_satband = pulseTXSatBandTOF.isSlr() ? 90 : flip_90_sat_degree;  //ha slr,akkor legyen 90,különben szar a szeletprofil!
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/4 : Shape
        // -----------------------------------------------
        int nb_shape_points = 128;
        pulseTX.setShape((getText(TX_SHAPE)), nb_shape_points, "Hamming");
        pulseTXFatSat.setShape((getText(FATSAT_TX_SHAPE)), nb_shape_points, "Hamming");
        pulseTXSatBandTOF.setShape((getText(is_satband_enabled ? SATBAND_TX_SHAPE : TOF2D_SB_TX_SHAPE)), nb_shape_points, "Hamming");

        // -----------------------------------------------
        // Calculation RF pulse parameters  3/4 : RF pulse & attenuation
        // -----------------------------------------------
        if (getBoolean(TX_AMP_ATT_AUTO)) {
//            if (!pulseTX.setAutoCalibFor180(flip_angle, observeFrequency, getListInt(TX_ROUTE), nucleus)) {
//                notifyOutOfRangeParam(TX_LENGTH, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
//                txLength90 = pulseTX.getPulseDuration();
//            }
            if (!pulseTX.checkPower(flip_angle, observeFrequency, nucleus)) {
                notifyOutOfRangeParam(TX_LENGTH, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX.getPulseDuration();
            }

            if (!pulseTXFatSat.checkPower(is_fatsat_enabled ? 90.0 : 0.0, observeFrequency + getDouble(FATSAT_OFFSET_FREQ), nucleus)) {
                tx_length_90_fs = pulseTXFatSat.getPulseDuration();
                System.out.println(" tx_length_90_fs: " + tx_length_90_fs);
                set(Time_tx_fatsat, tx_length_90_fs);
                getParam(FATSAT_TX_LENGTH).setValue(tx_length_90_fs);
//                notifyOutOfRangeParam(TX_LENGTH, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
            }

            if (!pulseTXSatBandTOF.checkPower(flip_angle_satband, observeFrequency + getDouble(FATSAT_OFFSET_FREQ), nucleus)) {
//                double tx_length_sb = pulseTXSatBand.getPulseDuration();
//                notifyOutOfRangeParam(TX_LENGTH, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                set(Time_tx_sb, pulseTXSatBandTOF.getPulseDuration());
            }
            RFPulse pulseMaxPower = pulseTX.getPower() > pulseTXFatSat.getPower() ? pulseTX : pulseTXFatSat;
            pulseMaxPower = pulseMaxPower.getPower() > pulseTXSatBandTOF.getPower() ? pulseMaxPower : pulseTXSatBandTOF;

            pulseMaxPower.prepAtt(80, getListInt(TX_ROUTE));

            pulseTX.prepTxAmp(getListInt(TX_ROUTE));
            pulseTXFatSat.prepTxAmp(getListInt(TX_ROUTE));
            pulseTXSatBandTOF.prepTxAmp(getListInt(TX_ROUTE));

            this.getParam(TX_ATT).setValue(pulseTX.getAtt());            // display PULSE_ATT
            this.getParam(TX_AMP).setValue(pulseTX.getAmp());     // display 90° amplitude
            this.getParam(TX_AMP_90).setValue(pulseTX.getAmp90());     // display 90° amplitude
            this.getParam(TX_AMP_180).setValue(pulseTX.getAmp180());   // display 180° amplitude
            this.getParam(FATSAT_TX_AMP_90).setValue(pulseTXFatSat.getAmp90());
            this.getParam(SATBAND_TX_AMP).setValue(pulseTXSatBandTOF.getAmp90());

        } else {
            pulseTX.setAtt(getInt(TX_ATT));
            pulseTX.setAmp(getDouble(TX_AMP));
            this.getParam(TX_AMP_90).setValue(getDouble(TX_AMP) * 90 / flip_angle);     // display 90° amplitude
            this.getParam(TX_AMP_180).setValue(getDouble(TX_AMP) * 90 / flip_angle);   // display 180° amplitude

            pulseTXFatSat.setAmp(getDouble(FATSAT_TX_AMP_90));
            pulseTXSatBandTOF.setAmp(getParam(SATBAND_TX_AMP));
        }
        this.getParam(FATSAT_FLIP_ANGLE).setValue(is_fatsat_enabled ? 90 : 0);

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
        if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation)) {
            slice_thickness_excitation = gradSlice.getSliceThickness();
            double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
            notifyOutOfRangeParam(SLICE_THICKNESS, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
            sliceThickness = slice_thickness_min;
        }
        gradSlice.applyAmplitude();

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        if (isMultiplanar && nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, nb_slices_acquired_in_single_scan);
            pulseTX.setFrequencyOffset(nb_slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
        } else {
            //3D CASE :
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, 1, 0, off_center_distance_3D);
            pulseTX.setFrequencyOffset(Order.Three);
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

    private void prepImagingGrads() throws Exception {
        // -----------------------------------------------
        // calculate ADC observation time
        // -----------------------------------------------
        getADC();

        // -----------------------------------------------
        // calculate READ gradient amplitude
        // -----------------------------------------------
        getROGrad();

        //----------------------------------------------------------------------
        // OFF CENTER FIELD OF VIEW 1D
        // modify RX FREQUENCY OFFSET
        //----------------------------------------------------------------------
        getRx();

        //--------------------------------------------------------------------------------------
        // PHASE CYCLING
        //--------------------------------------------------------------------------------------
        getPhaseCyc();

        // -------------------------------------------------------------------------------------------------
        // calculate READ_PREP  & SLICE_REF
        // -------------------------------------------------------------------------------------------------
        getPrephaseGrad();

        // -------------------------------------------------------------------------------------------------
        // calculate PHASE_3D  & PHASE_2D
        // -------------------------------------------------------------------------------------------------
        getPEGrad();

        // -------------------------------------------------------------------------------------------------
        // calculate time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        // -------------------------------------------------------------------------------------------------
        getGradOpt();

        // -------------------------------------------------------------------------------------------------
        // Calculate Crusher Grad AMPLITUDE
        // -------------------------------------------------------------------------------------------------
        getCrusherGrad();

        // -------------------------------------------------------------------------------------------------
        // Spoiler Gradient
        // -------------------------------------------------------------------------------------------------
        getSpoilerGrad();
    }

    private void prepFlybackGrad(){
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

    private void prepExtTrig() {
        set(Synchro_trigger, isTrigger ? TimeElement.Trigger.External : TimeElement.Trigger.Timer);
        getSequenceParam(Synchro_trigger).setLocked(true);
        time_external_trigger_delay_max = minInstructionDelay;

        triggerdelay = setSequenceTableValues(Time_trigger_delay, Order.Four);
        if ((!isTrigger)) {
            triggerdelay.add(minInstructionDelay);
        } else {
            for (int i = 0; i < numberOfTrigger; i++) {
                double time_external_trigger_delay = roundToDecimal(triggerTime.get(i), 7);
                time_external_trigger_delay = Math.max(time_external_trigger_delay, minInstructionDelay);
                triggerdelay.add(time_external_trigger_delay);
                time_external_trigger_delay_max = Math.max(time_external_trigger_delay_max, time_external_trigger_delay);
            }
        }
        set(Ext_trig_source, TRIGGER_CHANEL);
    }

    private void prepIR() {
    }

    private void prepFatSatGrad() {
        //  Fat-Sat gradient
        Gradient gradFatsatRead = Gradient.createGradient(getSequence(), Grad_amp_fatsat_read, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);
        Gradient gradFatsatPhase = Gradient.createGradient(getSequence(), Grad_amp_fatsat_phase, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);
        Gradient gradFatsatSlice = Gradient.createGradient(getSequence(), Grad_amp_fatsat_slice, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat, nucleus);

        if (is_fatsat_enabled) {
            double pixel_dimension_ph = getDouble(RESOLUTION_PHASE);
            double pixel_dimension_sl = getDouble(RESOLUTION_SLICE);

            int pixmax = (pixelDimension > pixel_dimension_ph) ? 1 : 2;
            pixmax = (Math.max(pixelDimension, pixel_dimension_ph) > pixel_dimension_sl) ? pixmax : 3;
            boolean test_grad;
            double min_fatsat_application_time;
            switch (pixmax) {
                case 1:
                    test_grad = gradFatsatRead.addSpoiler(pixelDimension, 3);
                    min_fatsat_application_time = gradFatsatRead.getMinTopTime();
                    break;
                case 2:
                    test_grad = gradFatsatPhase.addSpoiler(pixel_dimension_ph, 3);
                    min_fatsat_application_time = gradFatsatPhase.getMinTopTime();
                    break;
                default:
                    test_grad = gradFatsatSlice.addSpoiler(pixel_dimension_sl, 3);
                    min_fatsat_application_time = gradFatsatSlice.getMinTopTime();
            }

            if (!test_grad) {
                notifyOutOfRangeParam(FATSAT_GRAD_APP_TIME, min_fatsat_application_time, ((NumberParam) getParam(FATSAT_GRAD_APP_TIME)).getMaxValue(), "FATSAT_GRAD_APP_TIME too short to get correct Spoiling");
                set(Time_grad_fatsat, min_fatsat_application_time);
                gradFatsatRead.rePrepare();
                gradFatsatPhase.rePrepare();
                gradFatsatSlice.rePrepare();
            }
        }
        gradFatsatRead.applyAmplitude();
        gradFatsatPhase.applyAmplitude();
        gradFatsatSlice.applyAmplitude();

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY FATSAT and compensation
        // ------------------------------------------------------------------
        pulseTXFatSat.setFrequencyOffset(is_fatsat_enabled ? getDouble(FATSAT_OFFSET_FREQ) : 0.0);

        RFPulse pulseTXFatSatPrep = RFPulse.createRFPulse(getSequence(), Time_before_fatsat_pulse, Freq_offset_tx_fatsat_prep);
        pulseTXFatSatPrep.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);
        RFPulse pulseTXFatSatComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_fatsat, Freq_offset_tx_fatsat_comp);
        pulseTXFatSatComp.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);
    }

//    private void prepFatSatSmartTTL(){
//        Table smartTTL_FatSat_table = setSequenceTableValues(SmartTTL_FatSat, Order.Four);
//        if (is_fatsat_enabled) {
//            double slice_time = (delay_before_echo_loop + (echoTrainLength * delay_echo_loop)
//                    + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID + 1, Events.LoopMultiPlanarEnd.ID));
//            double fatSat_repetition_Time = getDouble(FATSAT_PERIODE);
//            double ttl_periode;
//            int sliceRep_ttl;
//            int secondDim;
//            if (tr > fatSat_repetition_Time) {  //   this case is not necessary as merged with higher order will give a better roundning
//                secondDim = 1;
//                smartTTL_FatSat_table.setOrder(Order.Loop);
//            } else
//            if (number_of_averages > 1) {
//                secondDim = number_of_averages;
//                smartTTL_FatSat_table.setOrder(Order.OneLoop);
//            } else {
//                secondDim = acqMatrixDimension2D;
//                smartTTL_FatSat_table.setOrder(Order.TwoLoop);
//            }
//            int nb_ttl = Math.max(1, (int) Math.round(tr * secondDim / fatSat_repetition_Time));
//            sliceRep_ttl = Math.max(1, (int) Math.floor(nb_slices_acquired_in_single_scan * secondDim / (double) nb_ttl));
//            ttl_periode = sliceRep_ttl * slice_time;
//            smartTTL_FatSat_table.add(1);
//            for (int i = 0; i < sliceRep_ttl - 1; i++) {
//                smartTTL_FatSat_table.add(0);
//            }
//            getParam(FATSAT_PERIODE_EFF).setValue(ttl_periode);
////            System.out.println(slice_time);
////            System.out.println(tr * secondDim);
////            System.out.println("  / "+ fatSat_repetition_Time);
////            System.out.println(slice_time);
////            System.out.println(nb_ttl);
////            System.out.println(ttl_periode);
//        } else {
//            smartTTL_FatSat_table.add(0);
//        }
//    }

    private void prepSatBandGrad() {
        double tx_length_sb = is_satband_enabled || is_tof_enabled ? txLength90 : minInstructionDelay;
        double tx_bandwidth_factor_sb = getTx_bandwidth_factor(SATBAND_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_sb = tx_bandwidth_factor_sb / tx_length_sb;

        // Calculate gradient Amplitude
        double satband_tof_thickness = getDouble(is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS);
        double grad_amp_satband = 0;
        double grad_amp_satband_mTpm = 0;

        if (is_satband_enabled || is_tof_enabled) {
            Gradient gradSB = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
            if (!gradSB.prepareSliceSelection(tx_bandwidth_sb, satband_tof_thickness)) {
                double satband_thickness_mod = gradSB.getSliceThickness();
                notifyOutOfRangeParam((is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS), satband_thickness_mod, ((NumberParam) getParam(is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS)).getMaxValue(), "Pulse length too short to reach this satband slice thickness");
                satband_tof_thickness = satband_thickness_mod;
            }
            grad_amp_satband = gradSB.getAmplitude();
            grad_amp_satband_mTpm = gradSB.getAmplitude_mTpm();
        }

        // Calculate allocate Gradient amplitude, spoiler, and freq offset
        double satband_distance_from_fov = getDouble(SATBAND_DISTANCE_FROM_FOV);

        double[] gradAmpSBSliceTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] gradAmpSBPhaseTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] gradAmpSBReadTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] gradAmpSBSliceSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] gradAmpSBPhaseSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] gradAmpSBReadSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
        double[] offsetFreqSBTable = new double[is_satband_enabled ? nb_satband : nb_planar_excitation];

        double grad_amp_sat_spoiler = getDouble(is_satband_enabled ? SATBAND_GRAD_AMP_SPOILER : TOF2D_SB_GRAMP_SP);
        if (is_satband_enabled) {
            int n = 0;
            if (position_sli_ph_rea[0] == 1) { // SB  in slice position Sup
                gradAmpSBSliceTable[n] = grad_amp_satband;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = 0;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_pos = off_center_distance_3D + fov3d / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0;
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[1] == 1) {
                gradAmpSBSliceTable[n] = grad_amp_satband;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = 0;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_neg = off_center_distance_3D - (fov3d / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
                n += 1;
            }
            if (position_sli_ph_rea[2] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = grad_amp_satband;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = 0;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_pos = off_center_distance_2D + (fovPhase / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[3] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = grad_amp_satband;
                gradAmpSBReadTable[n] = 0;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = 0;
                gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                double off_center_neg = off_center_distance_2D - (fovPhase / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
                n += 1;
            }
            if (position_sli_ph_rea[4] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = grad_amp_satband;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = 0;
                double off_center_pos = off_center_distance_1D + (fov / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
                n += 1;
            }
            if (position_sli_ph_rea[5] == 1) {
                gradAmpSBSliceTable[n] = 0;
                gradAmpSBPhaseTable[n] = 0;
                gradAmpSBReadTable[n] = grad_amp_satband;
                gradAmpSBSliceSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[n] = 0;
                double off_center_neg = off_center_distance_1D - (fov / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0);
                offsetFreqSBTable[n] = new RFPulse().calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);

            }
        } else if (is_tof_enabled) {
            double satband_distance_from_slice = getDouble(TOF2D_SB_DISTANCE_FROM_SLICE);

            double off_center_slice_pos = satband_distance_from_slice + satband_tof_thickness / 2.0; // sat band cranial from voxel
            double off_center_slice_neg = -off_center_slice_pos;  // caudal
            double off_center_slice = 0;
            if ("BELOW THE SLICE".equalsIgnoreCase(getText(TOF2D_SB_POSITION))) {
                off_center_slice = off_center_slice_neg;
            } else if ("ABOVE THE SLICE".equalsIgnoreCase(getText(TOF2D_SB_POSITION))) {
                off_center_slice = off_center_slice_pos;
            }
            double frequency_offset_sat_slice = -grad_amp_satband_mTpm * off_center_slice * (GradientMath.GAMMA / nucleus.getRatio());

            for (int k = 0; k < nb_planar_excitation; k++) {
                offsetFreqSBTable[k] = (pulseTX.getFrequencyOffset(k) * grad_amp_satband_mTpm / gradSlice.getAmplitude_mTpm()) + frequency_offset_sat_slice;
//                    System.out.println("frequency_offset_tof2d[k]  " + offsetFreqSBTable[k]);
            }
            gradAmpSBSliceTable[0] = grad_amp_satband;
            gradAmpSBPhaseTable[0] = 0;
            gradAmpSBReadTable[0] = 0;
            gradAmpSBSliceSpoilerTable[0] = 0;
            gradAmpSBPhaseSpoilerTable[0] = grad_amp_sat_spoiler;
            gradAmpSBReadSpoilerTable[0] = grad_amp_sat_spoiler;
        }


        // Apply values ot Gradient
        Gradient gradSatBandSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSlice.setAmplitude(gradAmpSBSliceTable);
        gradSatBandSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandPhase.setAmplitude(gradAmpSBPhaseTable);
        gradSatBandPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandRead.setAmplitude(gradAmpSBReadTable);
        gradSatBandRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerSlice.setAmplitude(gradAmpSBSliceSpoilerTable);
        gradSatBandSpoilerSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerPhase.setAmplitude(gradAmpSBPhaseSpoilerTable);
        gradSatBandSpoilerPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        Gradient gradSatBandSpoilerRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb, nucleus);
        gradSatBandSpoilerRead.setAmplitude(gradAmpSBReadSpoilerTable);
        gradSatBandSpoilerRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY SatBand  compensation
        // ------------------------------------------------------------------
        // Apply values ot Tx offset
        pulseTXSatBandTOF.addFrequencyOffset(offsetFreqSBTable);
        pulseTXSatBandTOF.setFrequencyOffset(is_satband_enabled ? Order.LoopB : is_tof_enabled ? Order.Three : Order.FourLoop);
        RFPulse pulseTXSatBandPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_prep);
        pulseTXSatBandPrep.setCompensationFrequencyOffset(pulseTXSatBandTOF, 0.5);
        RFPulse pulseTXSatBandComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_comp);
        pulseTXSatBandComp.setCompensationFrequencyOffset(pulseTXSatBandTOF, 0.5);


    }

    private void prepSeqTiming() throws Exception {
        // ------------------------------------------
        // calculate Time and Delay
        // ------------------------------------------
        getTimeandDelay();

        // ------------------------------------------
        // calculate TR & search for incoherence
        // ------------------------------------------
        getTR();

        // ------------------------------------------------------------------
        // calculate Acquisition Time Offset and MultiSeries Parameter list
        // ------------------------------------------------------------------
        getAcqTime();

        // ------------------------------------------------------------------
        // calculate Acquisition Time Offset and MultiSeries Parameter list
        // ------------------------------------------------------------------
        getMultiParaList();
    }

    private void prepFlow() {
        double sat_flow_time_corr = minInstructionDelay;
        if (is_tof_enabled) {
            double satband_distance_from_slice = getDouble(TOF2D_SB_DISTANCE_FROM_SLICE);
            double sat_flow_dist = satband_distance_from_slice + slice_thickness_excitation;
            double sat_flow_velocity = getDouble(TOF2D_FLOW_VELOCITY);
            double sat_flow_time = sat_flow_dist / sat_flow_velocity;
            double time = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID - 1, Events.P90.ID - 3) + TimeEvents.getTimeBetweenEvents(getSequence(), Events.P90.ID - 2, Events.Acq.ID);//(real index - 1) in the argument instead of real index
//                System.out.println("time" + time);
//                System.out.println("sat_flow_time" + sat_flow_time);
            sat_flow_time_corr = sat_flow_time - time;
            sat_flow_time_corr = (sat_flow_time_corr < 0) ? 0.0001 : sat_flow_time_corr;
//                System.out.println("sat_flow_time_corr" + sat_flow_time_corr);
        }
        set(Time_flow, sat_flow_time_corr);
    }

    private void prepDicom() {
        // Set  TRIGGER_TIME for dynamic or trigger acquisition
        if (isDynamic && (numberOfDynamicAcquisition != 1) && !isTrigger) {
            ArrayList<Number> arrayListTrigger = new ArrayList<>();
            for (int i = 0; i < numberOfDynamicAcquisition; i++) {
                arrayListTrigger.add(i * time_between_frames);
            }
//            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.TRIGGER_TIME.name()), arrayListTrigger);       // associate TE to images for DICOM export
//            putVariableParameter(list, (4));
            getParam(TRIGGER_TIME).setValue(arrayListTrigger);
        }

        // Set  ECHO_TIME
        if (echoTrainLength != 1) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
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
    private void getAcq1D() throws Exception {
        // FOV
        double fov_eff = isFovDoubled ? (fov * 2) : fov;
        getParam(FOV_EFF).setValue(fov_eff);

        // Pixel dimension calculation
        acquisitionMatrixDimension1D = userMatrixDimension1D * (isFovDoubled ? 2 : 1);
        pixelDimension = fov_eff / acquisitionMatrixDimension1D;
        getParam(RESOLUTION_FREQUENCY).setValue(pixelDimension); // frequency true resolution for display

        // MATRIX
        double spectralWidthPerPixel = getDouble(SPECTRAL_WIDTH_PER_PIXEL);
        spectralWidth = isFovDoubled ? (spectralWidth * 2) : spectralWidth;
        spectralWidth = isSW ? spectralWidth : spectralWidthPerPixel * acquisitionMatrixDimension1D;

//        spectralWidth = Hardware.getNearestSpectralWidth(spectralWidth);      // get real spectral width from Chameleon
        spectralWidth = Hardware.getSequenceCompiler().getNearestSW(spectralWidth);      //  to be replaced by above when correctly implemented for Cam4 05/07/2019
        double spectralWidthUP = isFovDoubled ? (spectralWidth / 2) : spectralWidth;
        spectralWidthPerPixel = spectralWidth / acquisitionMatrixDimension1D;
        getParam(SPECTRAL_WIDTH_PER_PIXEL).setValue(spectralWidthPerPixel);
        getParam(SPECTRAL_WIDTH).setValue(spectralWidthUP);
        observation_time = acquisitionMatrixDimension1D / spectralWidth;
        getParam(ACQUISITION_TIME_PER_SCAN).setValue(observation_time);   // display observation time

        nb_scan_1d = number_of_averages;
    }

    private void getAcq2D() {
        // FOV
        prepareFovPhase();

        setSquarePixel(getBoolean(SQUARE_PIXEL));

        // MATRIX 2D
        if ("Elliptical3D".equalsIgnoreCase((String) (getParam(TRANSFORM_PLUGIN).getValue()))) {
            if (getDouble(USER_PARTIAL_PHASE) <= 55)
                getParam(USER_PARTIAL_PHASE).setValue(55.0); // for elliptical3D, minimum of 2D partialFourier set 55%
        }
        double partial_phase = getDouble(USER_PARTIAL_PHASE);
        acqMatrixDimension2D = floorEven(partial_phase / 100f * userMatrixDimension2D);
        acqMatrixDimension2D = (acqMatrixDimension2D < 4) && isEnablePhase ? 4 : acqMatrixDimension2D;
        getParam(USER_ZERO_FILLING_2D).setValue((100 - partial_phase));
        getParam(ACQUISITION_MATRIX_DIMENSION_2D).setValue(acqMatrixDimension2D);
    }

    private void getAcq3D() {
        // MATRIX
        boolean is_partial_oversampling = getBoolean(PARTIAL_OVERSAMPLING);
        is_partial_oversampling = (isMultiplanar || userMatrixDimension3D < 8) ? false : is_partial_oversampling;
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
            getParam(USER_MATRIX_DIMENSION_3D).setValue(userMatrixDimension3D);
        } else {
            if ((userMatrixDimension3D * 3 + ((is_rf_spoiling) ? 1 : 0) + 3 + 1) >= offset_channel_memory) {
                userMatrixDimension3D = ((int) Math.floor((offset_channel_memory - 4 - ((is_rf_spoiling) ? 1 : 0)) / 3.0));
                getParam(USER_MATRIX_DIMENSION_3D).setValue(userMatrixDimension3D);
            }
            acqMatrixDimension3D = userMatrixDimension3D;
        }

        nb_of_shoot_3d = isMultiplanar ? getInferiorDivisorToGetModulusZero(nb_of_shoot_3d, acqMatrixDimension3D) : acqMatrixDimension3D;
        nb_of_shoot_3d = is_tof_enabled ? acqMatrixDimension3D : nb_of_shoot_3d; // TOF does not allow interleaved slice within the TR

        nbOfInterleavedSlice = isMultiplanar ? (int) Math.ceil((acqMatrixDimension3D / (double) nb_of_shoot_3d)) : 1;
        getParam(NUMBER_OF_SHOOT_3D).setValue(nb_of_shoot_3d);
        getParam(NUMBER_OF_INTERLEAVED_SLICE).setValue(isMultiplanar ? nbOfInterleavedSlice : 0);

        acqMatrixDimension3D = is_partial_oversampling ? (int) Math.round(acqMatrixDimension3D / 0.8 / 2) * 2 : acqMatrixDimension3D;
        userMatrixDimension3D = is_partial_oversampling ? (int) Math.round(userMatrixDimension3D / 0.8 / 2) * 2 : userMatrixDimension3D;

        getParam(ACQUISITION_MATRIX_DIMENSION_3D).setValue(acqMatrixDimension3D);

        if (isMultiplanar) {
            spacingBetweenSlice = getDouble(SPACING_BETWEEN_SLICE);
        } else {
            getParam(SPACING_BETWEEN_SLICE).setValue(0);
            spacingBetweenSlice = 0;
        }

        fov3d = sliceThickness * userMatrixDimension3D + spacingBetweenSlice * (userMatrixDimension3D - 1);
        getParam(FIELD_OF_VIEW_3D).setValue(fov3d);    // FOV ratio for display
    }

    private void regetAcq3D() {
    }

    private void getAcq4D() {
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

            numberOfDynamicAcquisition = numberOfnumInterleavedEchoTrain;
            getParam(DYN_NUMBER_OF_ACQUISITION).setValue(numberOfDynamicAcquisition);

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
        if (numberOfTrigger != 1 && (isDynamic)) {
            double tmp = triggerTime.get(0);
            triggerTime.clear();
            triggerTime.add(tmp);
            numberOfTrigger = 1;
        }

        acquisitionMatrixDimension4D = numberOfTrigger * numberOfDynamicAcquisition * echoTrainLength;
        getParam(ACQUISITION_MATRIX_DIMENSION_4D).setValue(isKSCenterMode ? 1 : acquisitionMatrixDimension4D);
    }

    private void getImgRes() {
        // Pixel dimension calculation
        double pixelDimensionPhase = fovPhase / acqMatrixDimension2D;
        getParam(RESOLUTION_PHASE).setValue(pixelDimensionPhase); // phase true resolution for display

        // Pixel dimension calculation
        double pixel_dimension_3D;
        if (isMultiplanar) {
            pixel_dimension_3D = sliceThickness;
        } else {
            pixel_dimension_3D = sliceThickness * userMatrixDimension3D / acqMatrixDimension3D; //true resolution
        }
        getParam(RESOLUTION_SLICE).setValue(pixel_dimension_3D); // phase true resolution for display
    }

    //--------------------------------------------------------------------------------------
    private void getGradRiseTime() {
        grad_rise_time = getDouble(GRADIENT_RISE_TIME);
        double min_rise_time_factor = getDouble(MIN_RISE_TIME_FACTOR);

        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            notifyOutOfRangeParam(GRADIENT_RISE_TIME, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }
        set(Time_grad_ramp, grad_rise_time);
    }

    private void getGradEqRiseTime() {

        double grad_shape_rise_factor_up = Utility.voltageFillingFactor(getSequenceTable(Grad_shape_rise_up));
        double grad_shape_rise_factor_down = Utility.voltageFillingFactor(getSequenceTable(Grad_shape_rise_down));
        grad_shape_rise_time = grad_shape_rise_factor_up * grad_rise_time + grad_shape_rise_factor_down * grad_rise_time;        // shape dependant equivalent rise time
    }

    private void getADC() throws Exception {
        set(Time_rx, observation_time);
        set(LO_att, Instrument.instance().getLoAttenuation());
    }

    private void getROGrad() throws Exception{
        gradReadout = Gradient.createGradient(getSequence(), Grad_amp_read_read, Time_rx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        if (isEnableRead && !gradReadout.calculateReadoutGradient(spectralWidth, pixelDimension * acquisitionMatrixDimension1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            if (isSW) {
                notifyOutOfRangeParam(SPECTRAL_WIDTH, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), (spectral_width_max / (isFovDoubled ? 2 : 1)), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                notifyOutOfRangeParam(SPECTRAL_WIDTH_PER_PIXEL, ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), (spectral_width_max / acquisitionMatrixDimension1D), "SPECTRAL_WIDTH too high for the readout gradient");
            }
            spectralWidth = spectral_width_max;
        }
        gradReadout.applyReadoutEchoPlanarAmplitude(is_flyback ? 1 : echoTrainLength, Order.LoopB);
        set(Spectral_width, spectralWidth);
    }

    private void getRx(){
        RFPulse pulseRX = RFPulse.createRFPulse(getSequence(), Time_rx, Rx_freq_offset, Rx_phase);
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

        pulseRX.setPhase(0.0);
        //--------------------------------------------------------------------------------------
        // PHASE CYCLING
        //--------------------------------------------------------------------------------------
        getPhaseCyc();
    }

    private void getPrephaseGrad() {
        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        getGradEqRiseTime();

        double grad_phase_application_time = getDouble(GRADIENT_PHASE_APPLICATION_TIME);
        set(Time_grad_phase_top, grad_phase_application_time);
        double readGradientRatio = getDouble(PREPHASING_READ_GRADIENT_RATIO);

        double flowcomp_dur = getDouble(FLOWCOMP_DURATION);
        set(Time_grad_ramp_flowcomp, is_flowcomp ? grad_rise_time : minInstructionDelay);
        set(Time_grad_top_flowcomp, is_flowcomp ? flowcomp_dur : minInstructionDelay);

        // pre-calculate READ_prephasing max area
        gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        gradReadPrepFlowComp = Gradient.createGradient(getSequence(), Grad_amp_read_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp, nucleus);
        if (isEnableRead) {
            if (!is_flowcomp) {
                gradReadPrep.refocalizeGradient(gradReadout, readGradientRatio);
            } else {
                gradReadPrep.refocalizeGradientWithFlowComp(gradReadout, readGradientRatio, gradReadPrepFlowComp);
            }
            System.out.println("  gradReadout " + gradReadout.getAmplitude());
            System.out.println("  gradReadout 0  " + gradReadout.getAmplitudeArray(0));
            System.out.println("  gradReadPrep " + gradReadPrep.getAmplitude());
            System.out.println("  gradReadPrep 0  " + gradReadPrep.getAmplitudeArray(0));
        }

        // pre-calculate SLICE_refocusing  &  PHASE_3D
        double grad_ratio_slice_refoc = isEnableSlice ? getDouble(SLICE_REFOCUSING_GRADIENT_RATIO) : 0.0;   // get slice refocussing ratio
        gradSliceRefPhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        gradSliceRefPhase3DFlowComp = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp, nucleus);
        if (isEnableSlice) {
            if (!is_flowcomp) {
                gradSliceRefPhase3D.refocalizeGradient(gradSlice, grad_ratio_slice_refoc);
            } else {
                gradSliceRefPhase3D.refocalizeGradientWithFlowComp(gradSlice, grad_ratio_slice_refoc, gradSliceRefPhase3DFlowComp);
            }
        }

        if (!isMultiplanar && isEnablePhase3D) {
            if (!is_flowcomp) {
                gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            } else {
                gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acqMatrixDimension3D, acqMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
                System.out.println("flow comp not suported");
                Log.info(getClass(), " flow comp not suported ");

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

    private void getPEGrad(){
        // pre-calculate PHASE_2D
        gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp, nucleus);
        gradPhase2DFlowComp = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp, nucleus);
        if (isEnablePhase) {
            if (!is_flowcomp) {
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
                gradPhase2D.reoderPhaseEncoding3D(plugin);
            } else {
                System.out.println();
                System.out.println("acquisitionMatrixDimension2D  " + getInt(ACQUISITION_MATRIX_DIMENSION_2D));
                gradPhase2D.reoderPhaseEncoding(plugin, 1, getInt(ACQUISITION_MATRIX_DIMENSION_2D), acquisitionMatrixDimension1D);
            }
        }
    }

    private void getGradOpt(){
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

        if (is_flowcomp) {
            gradSliceRefPhase3DFlowComp.applyAmplitude(Order.Three);
            gradPhase2DFlowComp.applyAmplitude(Order.Two);
            gradReadPrepFlowComp.applyAmplitude();
        }
    }

    private void getCrusherGrad() {
    }

    private void getSpoilerGrad() {
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

    private void getTimeandDelay() throws Exception {
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
                echo_spacing = effectiveEchoSpacing * numberOfnumInterleavedEchoTrain;
                getParam(ECHO_SPACING).setValue(echo_spacing);
            }

            for (int ind4D = 0; ind4D < numberOfnumInterleavedEchoTrain; ind4D++) {
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
                    double effectiveEchoSpacingMin = echo_spacing_min / numberOfnumInterleavedEchoTrain;
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

    private void getTR() {
        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        double delay_before_multi_planar_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.Start.ID, Events.TriggerDelay.ID - 1) + TimeEvents.getTimeBetweenEvents(getSequence(), Events.TriggerDelay.ID + 1, Events.LoopMultiPlanarStart.ID - 1) + time_external_trigger_delay_max;
        double delay_sat_band = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandStart.ID, Events.LoopSatBandStart.ID) * nb_satband;
        double delay_before_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopMultiPlanarStart.ID, Events.LoopSatBandStart.ID - 1) + delay_sat_band + TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopSatBandEnd.ID + 1, Events.LoopStartEcho.ID - 1);
        double delay_echo_loop = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopStartEcho.ID, Events.LoopEndEcho.ID);
        double delay_spoiler = TimeEvents.getTimeBetweenEvents(getSequence(), Events.LoopEndEcho.ID + 1, Events.LoopMultiPlanarEnd.ID - 2);// grad_phase_application_time + grad_rise_time * 2;
        min_flush_delay = min_time_per_acq_point * acquisitionMatrixDimension1D * echoTrainLength * nb_slices_acquired_in_single_scan * 2;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
        min_flush_delay = Math.max(CameleonVersion105 ? min_flush_delay : 0, minInstructionDelay);

        double time_seq_to_end_spoiler = (delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler) * nb_slices_acquired_in_single_scan);
        double tr_min = time_seq_to_end_spoiler + minInstructionDelay * (nb_slices_acquired_in_single_scan * 2 + 1) + min_flush_delay;// 2 +( 2 minInstructionDelay: Events. 22 +(20&21
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
        if (numberOfTrigger != 1) {
            for (int i = 0; i < numberOfTrigger; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler - time_external_trigger_delay_max + triggerdelay.get(i).doubleValue();
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_slices_acquired_in_single_scan - minInstructionDelay;
                time_tr_delay.add(tr_delay);
            }
        } else {
            tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_flush_delay)) / nb_slices_acquired_in_single_scan - minInstructionDelay;
            time_tr_delay.add(tr_delay);
        }
        set(Time_last_delay, last_delay);
        set(Time_flush_delay, min_flush_delay);
    }

    private void getPhaseCyc() {
        //--------------------------------------------------------------------------------------
        //  calculate RF_SPOILING
        //--------------------------------------------------------------------------------------
        RFPulse pulseRFSpoiler = RFPulse.createRFPulse(getSequence(), Time_rf_spoiling, FreqOffset_RFSpoiling);
        pulseRFSpoiler.setFrequencyOffsetForPhaseShift(is_rf_spoiling ? 117.0 : 0.0);

        // ----------------------------------------------------------------------------------------------
        // modify RX PHASE TABLE to handle OFF CENTER FOV 2D in both cases or PHASE CYCLING
        // ----------------------------------------------------------------------------------------------
        set(Rx_phase, 0);
    }

    private void getAcqTime() {
        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCE
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------
        double frame_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal(frame_acquisition_time + minInstructionDelay + min_flush_delay, 1);
        time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (isDynamic && (acquisitionMatrixDimension4D > 1)) {
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
        double total_acquisition_time = time_between_frames * numberOfDynamicAcquisition + tr * preScan;
        getParam(SEQUENCE_TIME).setValue(total_acquisition_time);

    }

    private void getMultiParaList() {
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
        } else if (isTrigger && numberOfTrigger != 1) {
            number_of_MultiSeries = numberOfTrigger;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TRIGGER DELAY";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round(triggerTime.get(i) * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        }
        getParam(MULTISERIES_PARAMETER_VALUE).setValue(multiseries_valuesList);
        getParam(MULTISERIES_PARAMETER_NAME).setValue(multiseries_parametername);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();
        double acqusition_time;
        for (int i = 0; i < numberOfDynamicAcquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acqusition_time = roundToDecimal((i * time_between_frames + j * time_between_MultiSeries), 3);
                acquisition_timesList.add(acqusition_time);
            }
        }
        getParam(ACQUISITION_TIME_OFFSET).setValue(acquisition_timesList);
    }

    private void prepComments() {
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