package rs2d.sequence.gradientecho;

// ---------------------------------------------------------------------
//  
//                 GRADIENT_ECHO_dev PSD 
//  
// ---------------------------------------------------------------------
// test Commit
// rename GRADIENT_SPOILER_TIME 
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
//        getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH.name(), spectralWidth,((NumberParam) getParam( SPECTRAL_WIDTH)).getMinValue(),spectral_width_max, "SPECTRAL_WIDTH too high for the readout gradient");
//	SAR deleted
// 24/03/2017   V5.5
//   setParamValue(MODALITY, "MRI");
//   setSequenceParamValue("Phase_reset","USER_TMP_PARAM_BOOL_1");

import java.util.*;

import rs2d.commons.log.Log;
import rs2d.sequence.common.Gradient;
import rs2d.sequence.common.RFPulse;
import rs2d.sequence.common.HardwareShim;
import rs2d.sequence.common.HardwarePreemphasis;
import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.hardware.controller.HardwareHandler;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.SequenceTool;
import rs2d.spinlab.sequence.element.TimeElement;
import rs2d.spinlab.tools.role.*;
import rs2d.spinlab.sequence.table.*;
import rs2d.spinlab.sequenceGenerator.SequenceGeneratorAbstract;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.GradientAxe;
import rs2d.spinlab.tools.utility.Nucleus;

import static java.util.Arrays.asList;
import static rs2d.sequence.gradientecho.GradientEchoParams.*;

import static rs2d.sequence.gradientecho.GradientEchoSequenceParams.*;


// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class GradientEcho extends SequenceGeneratorAbstract {

    private String sequenceVersion = "Version8.0a";
    private boolean CameleonVersion105 = false;
    private double protonFrequency;
    private double observeFrequency;
    private double min_time_per_acq_point;
    private double gMax;
    private TransformPlugin plugin;
    private Nucleus nucleus;

    private boolean isMultiplanar;

    private int acquisitionMatrixDimension1D;
    private int acquisitionMatrixDimension2D;
    private int acquisitionMatrixDimension3D;
    private int acquisitionMatrixDimension4D;
    private int preScan;

    private int userMatrixDimension1D;
    private int userMatrixDimension2D;
    private int userMatrixDimension3D;

    private int nb_scan_2d;
    private int nb_scan_3d;
    private int nb_scan_4d;
    private int echoTrainLength;

    private double spectralWidth;
    private boolean isSW;
    private double tr;
    private double te;

    private double sliceThickness;
    double spacingBetweenSlice;
    private double pixelDimension;
    private double fov;
    private double fovPhase;
    private boolean isFovDoubled;
    private double off_center_distance_1D;
    private double off_center_distance_2D;
    private double off_center_distance_3D;

    private double txLength90;

    private boolean isDynamic;
    private int numberOfDynamicAcquisition;

    private boolean isTrigger;
    private ListNumberParam triggerTime;
    private int numberOfTrigger;

    boolean is_flyback;
    String kspace_filling;

    boolean is_fatsat_enabled;

    private boolean is_rf_spoiling;

    private boolean isKSCenterMode;

    private boolean isEnablePhase;
    private boolean isEnablePhase3D;
    private boolean isEnableSlice;
    private boolean isEnableRead;


    private double observation_time;

    // get hardware memory limit
    private int offset_channel_memory = 512;
    private int phase_channel_memory = 512;
    private int amp_channel_memory = 2048;
    private int loopIndice_memory = 2024;

    private double defaultInstructionDelay = 0.000010;     // single instruction minimal duration
    private double minInstructionDelay = 0.000005;     // single instruction minimal duration

    public GradientEcho() {
        super();
        initParam();
    }

    @Override
    public void init() {
        super.init();

        // Define default, min, max and suggested values regarding the instrument
        getParam(MAGNETIC_FIELD_STRENGTH).setDefaultValue(Instrument.instance().getDevices().getMagnet().getField());
        getParam(DIGITAL_FILTER_SHIFT).setDefaultValue(Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount());
        getParam(DIGITAL_FILTER_REMOVED).setDefaultValue(Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint());

        List<String> tx_shape = asList("HARD", "GAUSSIAN", "SINC3", "SINC5");
        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        ((TextParam) getParam(TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE)).setRestrictedToSuggested(true);

//        ((TextParam) getParamFromName(SATBAND_TX_SHAPE")).setSuggestedValues(tx_shape);
//        ((TextParam) getParamFromName(SATBAND_TX_SHAPE")).setRestrictedToSuggested(true);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setRestrictedToSuggested(true);
//        ((TextParam) getParamFromName("TOF2D_SB_TX_SHAPE")).setSuggestedValues(tx_shape);
//        ((TextParam) getParamFromName("TOF2D_SB_TX_SHAPE")).setRestrictedToSuggested(true);


        //TRANSFORM PLUGIN
        List<String> list = asList("Sequential4D", "Sequential4DBackAndForth", "EPISequential4D", "Centric4D");
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setSuggestedValues(list);
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setRestrictedToSuggested(true);
        List<String> extTrigSource = asList(
                SequenceTool.ExtTrigSource.Ext1.name(),
                SequenceTool.ExtTrigSource.Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_AND_Ext2.name(),
                SequenceTool.ExtTrigSource.Ext1_XOR_Ext2.name());
        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        ((TextParam) getParam(TRIGGER_CHANEL)).setSuggestedValues(extTrigSource);
        ((TextParam) getParam(TRIGGER_CHANEL)).setRestrictedToSuggested(true);
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
        //   if (!((BooleanParam) getParam( SETUP_MODE)).getValue()) {
        this.afterRouting();    //avoid exception during setup
        // }

        this.checkAndFireException();
    }

    private void initUserParam() {
        isMultiplanar = (((BooleanParam) getParam(MULTI_PLANAR_EXCITATION)).getValue());

//        acquisitionMatrixDimension1D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        acquisitionMatrixDimension2D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_2D)).getValue().intValue();
        acquisitionMatrixDimension3D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_3D)).getValue().intValue();
        acquisitionMatrixDimension4D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_4D)).getValue().intValue();
        preScan = ((NumberParam) getParam(DUMMY_SCAN)).getValue().intValue();
        userMatrixDimension1D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_1D)).getValue().intValue();
        userMatrixDimension2D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_2D)).getValue().intValue();
        userMatrixDimension3D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_3D)).getValue().intValue();

        echoTrainLength = ((NumberParam) getParam(ECHO_TRAIN_LENGTH)).getValue().intValue();

        spectralWidth = ((NumberParam) getParam(SPECTRAL_WIDTH)).getValue().doubleValue();            // get user defined spectral width
        isSW = (((BooleanParam) getParam(SPECTRAL_WIDTH_OPT)).getValue());
        tr = ((NumberParam) getParam(REPETITION_TIME)).getValue().doubleValue();
        te = ((NumberParam) getParam(ECHO_TIME)).getValue().doubleValue();

        sliceThickness = ((NumberParam) getParam(SLICE_THICKNESS)).getValue().doubleValue();
        spacingBetweenSlice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        pixelDimension = ((NumberParam) getParam(RESOLUTION_FREQUENCY)).getValue().doubleValue();
        fov = ((NumberParam) getParam(FIELD_OF_VIEW)).getValue().doubleValue();
        fovPhase = ((NumberParam) getParam(FIELD_OF_VIEW_PHASE)).getValue().doubleValue();
        isFovDoubled = ((BooleanParam) getParam(FOV_DOUBLED)).getValue();
        off_center_distance_1D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_1D)).getValue().doubleValue();
        off_center_distance_2D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_2D)).getValue().doubleValue();
        off_center_distance_3D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_3D)).getValue().doubleValue();

        txLength90 = ((NumberParam) getParam(TX_LENGTH)).getValue().doubleValue();


        isDynamic = ((BooleanParam) getParam(DYNAMIC_SEQUENCE)).getValue();
        numberOfDynamicAcquisition = isDynamic ? ((NumberParam) getParam(DYN_NUMBER_OF_ACQUISITION)).getValue().intValue() : 1;
        isDynamic = isDynamic && (numberOfDynamicAcquisition > 1);

        isTrigger = (((BooleanParam) getParam(TRIGGER_EXTERNAL)).getValue());
        triggerTime = (ListNumberParam) getParam(TRIGGER_TIME);
        numberOfTrigger = isTrigger ? triggerTime.getValue().size() : 1;
        isTrigger = isTrigger && (numberOfTrigger > 1);

        is_flyback = (((BooleanParam) getParam(FLYBACK)).getValue());
        kspace_filling = ((String) getParam(KSPACE_FILLING).getValue());

        is_fatsat_enabled = (((BooleanParam) getParam(FAT_SATURATION_ENABLED)).getValue());

        is_rf_spoiling = ((BooleanParam) getParam(RF_SPOILING)).getValue();

        isKSCenterMode = ((BooleanParam) getParam(KS_CENTER_MODE)).getValue();

        isEnablePhase3D = !isKSCenterMode && ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE_3D)).getValue();
        isEnablePhase = !isKSCenterMode && ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE)).getValue();
        isEnableSlice = ((BooleanParam) getParam(GRADIENT_ENABLE_SLICE)).getValue();
        isEnableRead = ((BooleanParam) getParam(GRADIENT_ENABLE_READ)).getValue();
        observation_time = ((NumberParam) getParam(ACQUISITION_TIME_PER_SCAN)).getValue().doubleValue();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING --- INIT AFTER ROUTING -- 
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          INIT AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------
    private void initAfterRouting() {

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
        setParamValue(SEQUENCE_VERSION, sequenceVersion);
        setParamValue(MODALITY, "MRI");

        // -----------------------------------------------
        // RX parameters : nucleus, RX gain & frequencies
        // -----------------------------------------------
        nucleus = Nucleus.getNucleusForName((String) getParam(NUCLEUS_1).getValue());
        protonFrequency = Instrument.instance().getDevices().getMagnet().getProtonFrequency();
        double freq_offset1 = ((NumberParam) getParam(OFFSET_FREQ_1)).getValue().doubleValue();
        observeFrequency = nucleus.getFrequency(protonFrequency) + freq_offset1;
        setParamValue(BASE_FREQ_1, nucleus.getFrequency(protonFrequency));

        min_time_per_acq_point = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getTransfertTimePerDataPt();
        gMax = GradientMath.getMaxGradientStrength();

        setSequenceParamValue(Rx_gain, RECEIVER_GAIN);
        setParamValue(RECEIVER_COUNT, Instrument.instance().getObservableRxs(nucleus).size());

        setSequenceParamValue(Intermediate_frequency, Instrument.instance().getIfFrequency());
        setParamValue(INTERMEDIATE_FREQUENCY, Instrument.instance().getIfFrequency());

        setSequenceParamValue(Tx_frequency, observeFrequency);
        setParamValue(OBSERVED_FREQUENCY, observeFrequency);

        setSequenceParamValue(Tx_nucleus, NUCLEUS_1);
        setParamValue(OBSERVED_NUCLEUS, getParam(NUCLEUS_1).getValue());

        // -----------------------------------------------
        // 1stD managment
        // -----------------------------------------------
        // FOV
        double fov_eff = isFovDoubled ? (fov * 2) : fov;
        setParamValue(FOV_EFF, fov_eff);

        // Pixel dimension calculation
        acquisitionMatrixDimension1D = userMatrixDimension1D * (isFovDoubled ? 2 : 1);
        pixelDimension = fov_eff / acquisitionMatrixDimension1D;
        setParamValue(RESOLUTION_FREQUENCY, pixelDimension); // frequency true resolution for display

        // MATRIX
        double spectralWidthPerPixel = ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getValue().doubleValue();
        spectralWidth = isFovDoubled ? (spectralWidth * 2) : spectralWidth;
        spectralWidth = isSW ? spectralWidth : spectralWidthPerPixel * acquisitionMatrixDimension1D;

        spectralWidth = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getNearestSW(spectralWidth);      // get real spectral width from Chameleon
        double spectralWidthUP = isFovDoubled ? (spectralWidth / 2) : spectralWidth;
        spectralWidthPerPixel = spectralWidth / acquisitionMatrixDimension1D;
        setParamValue(SPECTRAL_WIDTH_PER_PIXEL, spectralWidthPerPixel);
        setParamValue(SPECTRAL_WIDTH, spectralWidthUP);
        observation_time = acquisitionMatrixDimension1D / spectralWidth;
        setParamValue(ACQUISITION_TIME_PER_SCAN, observation_time);   // display observation time

        // -----------------------------------------------
        // 2nd D managment
        // -----------------------------------------------
        // FOV
        prepareFovPhase();

        // MATRIX
        setSquarePixel(((BooleanParam) getParam(SQUARE_PIXEL)).getValue());

        double partial_phase = ((NumberParam) getParam(USER_PARTIAL_PHASE)).getValue().doubleValue();
        double zero_filling_2D = (100 - partial_phase) / 100f;
        setParamValue(USER_ZERO_FILLING_2D, (100 - partial_phase));

        acquisitionMatrixDimension2D = floorEven((1 - zero_filling_2D) * userMatrixDimension2D);
        acquisitionMatrixDimension2D = (acquisitionMatrixDimension2D < 4) && isEnablePhase ? 4 : acquisitionMatrixDimension2D;

        // Pixel dimension calculation
        double pixelDimensionPhase = fovPhase / acquisitionMatrixDimension2D;
        setParamValue(RESOLUTION_PHASE, pixelDimensionPhase); // phase true resolution for display
        nb_scan_2d = acquisitionMatrixDimension2D;

        // -----------------------------------------------
        // 3D managment 1/2: matrix & scan
        // ------------------------------------------------
        // MATRIX
        double partial_slice;
        // 3D ZERO FILLING
        if (isMultiplanar) {
            setParamValue(USER_PARTIAL_SLICE, 100);
            partial_slice = 100;
        } else {
            partial_slice = ((NumberParam) getParam(USER_PARTIAL_SLICE)).getValue().doubleValue();
        }
        double zero_filling_3D = (100 - partial_slice) / 100f;
        setParamValue(USER_ZERO_FILLING_3D, (100 - partial_slice));

        //Calculate the number of k-space lines acquired in the 3rd Dimension : acquisitionMatrixDimension3D
        if (!isMultiplanar) {
            acquisitionMatrixDimension3D = floorEven((1 - zero_filling_3D) * userMatrixDimension3D);
            acquisitionMatrixDimension3D = (acquisitionMatrixDimension3D < 4) && isEnablePhase3D ? 4 : acquisitionMatrixDimension3D;
            userMatrixDimension3D = userMatrixDimension3D < acquisitionMatrixDimension3D ? acquisitionMatrixDimension3D : userMatrixDimension3D;
            setParamValue(USER_MATRIX_DIMENSION_3D, userMatrixDimension3D);
        } else {
            if ((userMatrixDimension3D * 3 + ((is_rf_spoiling) ? 1 : 0) + 3 + 1) >= offset_channel_memory) {
                userMatrixDimension3D = ((int) Math.floor((offset_channel_memory - 4 - ((is_rf_spoiling) ? 1 : 0)) / 3.0));
                setParamValue(USER_MATRIX_DIMENSION_3D, userMatrixDimension3D);
            }
            acquisitionMatrixDimension3D = userMatrixDimension3D;
        }

        int nb_of_shoot_3d = ((NumberParam) getParam(NUMBER_OF_SHOOT_3D)).getValue().intValue();
        nb_of_shoot_3d = isMultiplanar ? getInferiorDivisorToGetModulusZero(nb_of_shoot_3d, userMatrixDimension3D) : 0;
        int nb_interleaved_excitation = isMultiplanar ? (int) Math.ceil((acquisitionMatrixDimension3D / nb_of_shoot_3d)) : 1;
        setParamValue(NUMBER_OF_SHOOT_3D, nb_of_shoot_3d);
        setParamValue(NUMBER_OF_INTERLEAVED_SLICE, isMultiplanar ? nb_interleaved_excitation : 0);

        nb_scan_3d = isMultiplanar ? nb_of_shoot_3d : acquisitionMatrixDimension3D;

        // -----------------------------------------------
        // 3D managment 2/2: dimension, FOV...
        // -----------------------------------------------
        // FOV
        if (isMultiplanar) {
            spacingBetweenSlice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        } else {
            setParamValue(SPACING_BETWEEN_SLICE, 0);
            spacingBetweenSlice = 0;
        }
        double fov_3d = sliceThickness * userMatrixDimension3D + spacingBetweenSlice * (userMatrixDimension3D - 1);
        setParamValue(FIELD_OF_VIEW_3D, fov_3d);    // FOV ratio for display

        // Pixel dimension calculation
        double pixel_dimension_3D;
        if (isMultiplanar) {
            pixel_dimension_3D = sliceThickness;
        } else {
            pixel_dimension_3D = sliceThickness * userMatrixDimension3D / acquisitionMatrixDimension3D; //true resolution
        }
        setParamValue(RESOLUTION_SLICE, pixel_dimension_3D); // phase true resolution for display

        // -----------------------------------------------
        // 4D managment:  Dynamic, MultiEcho, External triggering, Multi Echo
        // -----------------------------------------------

        // Avoid multi trigger time when  Multi echo or dynamic
        if (numberOfTrigger != 1 && (echoTrainLength != 1 || isDynamic)) {
            double tmp = triggerTime.getValue().get(0).doubleValue();
            triggerTime.getValue().clear();
            triggerTime.getValue().add(tmp);
            numberOfTrigger = 1;
        }

        nb_scan_4d = numberOfTrigger * numberOfDynamicAcquisition;
        acquisitionMatrixDimension4D = nb_scan_4d * echoTrainLength;
        setParamValue(USER_MATRIX_DIMENSION_4D, nb_scan_4d);

        //Dynamic and multi echo are filled into the 4th Dimension 
        if (echoTrainLength == 1) {
            setParamValue(ECHO_SPACING, 0);
//            setParamValue(TRANSFORM_PLUGIN, "Sequential4D");
        } //else {
//            setParamValue(TRANSFORM_PLUGIN, "Sequential4DBackAndForth");
//        }

        switch (kspace_filling) {
            case "Linear":
                setParamValue(TRANSFORM_PLUGIN, "Sequential4DBackAndForth");
                if (is_flyback) {
                    setParamValue(TRANSFORM_PLUGIN, "Sequential4D");
                }
                break;
            case "Centric":
                setParamValue(TRANSFORM_PLUGIN, "Centric4D");
                break;
            default:
                setParamValue(KSPACE_FILLING, "Linear");
                break;
        }


        // -----------------------------------------------
        // set the ACQUISITION_MATRIX and Nb XD
        // -----------------------------------------------        // set the calculated acquisition matrix

        setParamValue(ACQUISITION_MATRIX_DIMENSION_1D, acquisitionMatrixDimension1D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_2D, acquisitionMatrixDimension2D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_3D, isKSCenterMode && !isMultiplanar ? 1 : acquisitionMatrixDimension3D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_4D, isKSCenterMode ? 1 : acquisitionMatrixDimension4D);

        // set the calculated sequence dimensions
        setSequenceParamValue(Pre_scan, preScan); // Do the prescan
        setSequenceParamValue(Nb_point, acquisitionMatrixDimension1D);
        setSequenceParamValue(Nb_1d, NUMBER_OF_AVERAGES);
        setSequenceParamValue(Nb_2d, isKSCenterMode ? 2 : nb_scan_2d);
        setSequenceParamValue(Nb_3d, isKSCenterMode && !isMultiplanar ? 1 : nb_scan_3d);
        setSequenceParamValue(Nb_4d, isKSCenterMode ? 1 : nb_scan_4d);
        // set the calculated Loop dimensions
        setSequenceParamValue(Nb_echo, echoTrainLength - 1);
        setSequenceParamValue(Nb_interveaved_slice, nb_interleaved_excitation - 1);

        // -----------------------------------------------
        // SEQ_DESCRIPTION
        // -----------------------------------------------
        String seqDescription = "GE_";
        if (isMultiplanar) {
            seqDescription = seqDescription.concat("2D");
        } else {
            seqDescription = seqDescription.concat("3D");
        }
        String orientation = (String) getParam(ORIENTATION).getValue();
        seqDescription = seqDescription.concat(orientation.substring(0, 3));

        String seqMatrixDescription = "_";
        seqMatrixDescription = seqMatrixDescription.concat(String.valueOf(userMatrixDimension1D) + "x" + String.valueOf(acquisitionMatrixDimension2D) + "x" + String.valueOf(acquisitionMatrixDimension3D));
        if (acquisitionMatrixDimension4D != 1) {
            seqMatrixDescription = seqMatrixDescription.concat("x" + String.valueOf(acquisitionMatrixDimension4D));
        }
        seqDescription = seqDescription.concat(seqMatrixDescription);

        if (echoTrainLength != 1) {
            seqDescription = seqDescription.concat("_ETL=" + String.valueOf(echoTrainLength));
        }
        if (isTrigger && numberOfTrigger != 1) {
            seqDescription = seqDescription.concat("_TRIG=" + String.valueOf(numberOfTrigger));
        } else if (isTrigger) {
            seqDescription = seqDescription.concat("_TRIG");
        }
        if (isDynamic) {
            seqDescription = seqDescription.concat("_DYN=" + String.valueOf(numberOfDynamicAcquisition));
        }
        setParamValue(SEQ_DESCRIPTION, seqDescription);

        // -----------------------------------------------
        // Image Orientation
        // -----------------------------------------------
        //READ PHASE and SLICE matrix
        off_center_distance_1D = getOff_center_distance_1D_2D_3D(1);
        off_center_distance_2D = getOff_center_distance_1D_2D_3D(2);
        off_center_distance_3D = getOff_center_distance_1D_2D_3D(3);

        //Offset according to ENABLE READ PHASE and SLICE
        off_center_distance_1D = !isEnableRead ? 0 : off_center_distance_1D;
        off_center_distance_2D = !isEnablePhase ? 0 : off_center_distance_2D;

        if (!isEnableSlice && (isMultiplanar || (!isMultiplanar && !isEnablePhase3D))) {
            off_center_distance_3D = 0;
        }
        boolean is_read_phase_inverted = ((BooleanParam) getParam(SWITCH_READ_PHASE)).getValue();
        if (is_read_phase_inverted) {
            setSequenceParamValue(Gradient_axe_phase, GradientAxe.R);
            setSequenceParamValue(Gradient_axe_read, GradientAxe.P);
            double off_center_distance_tmp = off_center_distance_2D;
            off_center_distance_2D = off_center_distance_1D;
            off_center_distance_1D = off_center_distance_tmp;
        } else {
            setSequenceParamValue(Gradient_axe_phase, GradientAxe.P);
            setSequenceParamValue(Gradient_axe_read, GradientAxe.R);
        }
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_3D, off_center_distance_3D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_2D, off_center_distance_2D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_1D, off_center_distance_1D);

        // -----------------------------------------------
        // activate gradient rotation matrix
        // -----------------------------------------------
        appliedGradientRotation();

        HardwarePreemphasis hardwarePreemphasis = new HardwarePreemphasis();
        setParamValue(HARDWARE_PREEMPHASIS_A, hardwarePreemphasis.getAmplitude());
        setParamValue(HARDWARE_PREEMPHASIS_T, hardwarePreemphasis.getTime());
        setParamValue(HARDWARE_DC, hardwarePreemphasis.getDC());
        setParamValue(HARDWARE_A0, hardwarePreemphasis.getA0());

        HardwareShim hardwareShim = new HardwareShim();
        setParamValue(HARDWARE_SHIM, hardwareShim.getValue());
        setParamValue(HARDWARE_SHIM_LABEL, hardwareShim.getLabel());

    }

    private int floorEven(double value) {
        return (int) Math.floor(Math.round(value) / 2.0) * 2;
    }

    private void setSquarePixel(boolean square) {
        if (square) {
            userMatrixDimension2D = (int) Math.round(userMatrixDimension1D * fovPhase / fov);
            setParamValue(USER_MATRIX_DIMENSION_2D, userMatrixDimension2D);
        }
    }

    private void prepareFovPhase() {
        fovPhase = (((BooleanParam) getParam(FOV_SQUARE)).getValue()) ? fov : fovPhase;
        fovPhase = fovPhase > fov ? fov : fovPhase;
        setParamValue(FIELD_OF_VIEW_PHASE, fovPhase);
        setParamValue(PHASE_FIELD_OF_VIEW_RATIO, (fovPhase / fov * 100.0));    // FOV ratio for display
        setParamValue(FOV_RATIO_PHASE, Math.round(fovPhase / fov * 100.0));    // FOV ratio for display
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // -- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING --- AFTER ROUTING ---  AFTER ROUTING ---
    // --------------------------------------------------------------------------------------------------------------------------------------------
    //
    //                                                          AFTER ROUTING
    //
    // --------------------------------------------------------------------------------------------------------------------------------------------

    private void afterRouting() throws Exception {
        boolean b_comment = false; // Show the comments
        plugin = getTransformPlugin();
        plugin.setParameters(this.getParams());
        // -----------------------------------------------
        // enable gradient lines
        // -----------------------------------------------
        setSequenceParamValue(Grad_enable_read, isEnableRead);              // pass gradient line status to sequence
        setSequenceParamValue(Grad_enable_phase_2D, isEnablePhase);
        setSequenceParamValue(Grad_enable_phase_3D, ((!isMultiplanar && isEnablePhase3D) || isEnableSlice));
        setSequenceParamValue(Grad_enable_slice, isEnableSlice);

        boolean is_grad_rewinding = ((BooleanParam) getParam(GRADIENT_ENABLE_REWINDING)).getValue();// get slice refocussing ratio
        boolean is_grad_spoiler = ((BooleanParam) getParam(GRADIENT_ENABLE_SPOILER)).getValue();// get slice refocussing ratio

        ListNumberParam grad_amp_spoiler_sl_ph_re = (ListNumberParam) getParam(GRAD_AMP_SPOILER_SL_PH_RE);
        setSequenceParamValue(Grad_enable_spoiler_slice, (((!isMultiplanar && is_grad_rewinding && isEnablePhase3D) || (is_grad_rewinding && isEnableSlice) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.getValue().get(0).doubleValue() != 0)))));
        setSequenceParamValue(Grad_enable_spoiler_phase, (isEnablePhase && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.getValue().get(1).doubleValue() != 0))));
        setSequenceParamValue(Grad_enable_spoiler_read, (isEnableRead && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler_sl_ph_re.getValue().get(2).doubleValue() != 0))));

        setSequenceParamValue(Grad_enable_flyback, is_flyback);

        setSequenceParamValue(Enable_fatsat, is_flyback);

        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        double grad_rise_time = ((NumberParam) getParam(GRADIENT_RISE_TIME)).getValue().doubleValue();
        double min_rise_time_factor = ((NumberParam) getParam(MIN_RISE_TIME_FACTOR)).getValue().doubleValue();

        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            getUnreachParamExceptionManager().addParam(GRADIENT_RISE_TIME.name(), grad_rise_time, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }
        setSequenceTableSingleValue(Time_grad_ramp, grad_rise_time);

        double grad_shape_rise_factor_up = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_rise_up));
        double grad_shape_rise_factor_down = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_rise_down));
        double grad_shape_rise_time = grad_shape_rise_factor_up * grad_rise_time + grad_shape_rise_factor_down * grad_rise_time;        // shape dependant equivalent rise time

        // -----------------------------------------------
        // Calculation RF pulse parameters  1/3 : Shape
        // -----------------------------------------------
        setSequenceTableSingleValue(Time_tx, txLength90);
        RFPulse pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset);

        int nb_shape_points = 128;
        pulseTX.setShape(((String) getParam(TX_SHAPE).getValue()), nb_shape_points, "Hamming");


        double tx_bandwidth_90_fs = ((NumberParam) getParam(FATSAT_BANDWIDTH)).getValue().doubleValue();
        double tx_bandwidth_factor_90_fs = getTx_bandwidth_factor_90(FATSAT_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_length_90_fs = is_fatsat_enabled ? tx_bandwidth_factor_90_fs / tx_bandwidth_90_fs : minInstructionDelay;
        setSequenceTableSingleValue(Time_tx_fatsat, tx_length_90_fs);
        setParamValue(FATSAT_TX_LENGTH, tx_length_90_fs);

        RFPulse pulseTXFatSat = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_fatsat, Tx_phase_fatsat, Time_tx_fatsat, Tx_shape_fatsat, Tx_shape_phase_fatsat, Freq_offset_tx_fatsat);
        pulseTXFatSat.setShape(((String) getParam(TX_SHAPE).getValue()), nb_shape_points, "Hamming");

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/3 : RF pulse & attenuation
        // -----------------------------------------------
        double flip_angle = ((NumberParam) getParam(FLIP_ANGLE)).getValue().doubleValue();
        boolean is_tx_amp_att_auto = ((BooleanParam) getParam(TX_AMP_ATT_AUTO)).getValue();
        double tx_frequency_offset_90_fs = ((NumberParam) getParam(FATSAT_OFFSET_FREQ)).getValue().doubleValue();
        if (is_tx_amp_att_auto) {
//            if (!pulseTX.setAutoCalibFor180(flip_angle, observeFrequency, (List<Integer>) getParam(TX_ROUTE).getValue(), nucleus)) {
//                getUnreachParamExceptionManager().addParam(TX_LENGTH.name(), txLength90, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
//                txLength90 = pulseTX.getPulseDuration();
//            }
            if (!pulseTX.checkPower(flip_angle, observeFrequency, nucleus)) {
                getUnreachParamExceptionManager().addParam(TX_LENGTH.name(), txLength90, pulseTX.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                txLength90 = pulseTX.getPulseDuration();
            }

            if (!pulseTXFatSat.checkPower(is_fatsat_enabled ? flip_angle : 0.0, observeFrequency + tx_frequency_offset_90_fs, nucleus)) {
                tx_length_90_fs = pulseTXFatSat.getPulseDuration();
                System.out.println(" tx_length_90_fs: " + tx_length_90_fs);
//                getUnreachParamExceptionManager().addParam(TX_LENGTH.name(), txLength90, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
            }
            RFPulse pulseMaxPower = pulseTX.getPower() > pulseTXFatSat.getPower() ? pulseTX : pulseTXFatSat;
            pulseMaxPower.prepAtt(80, (List<Integer>) getParam(TX_ROUTE).getValue());

            pulseTX.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());
            pulseTXFatSat.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());

            this.setParamValue(TX_ATT, pulseTX.getAtt());            // display PULSE_ATT
            this.setParamValue(TX_AMP_90, pulseTX.getAmp90());     // display 90° amplitude
            this.setParamValue(TX_AMP_180, pulseTX.getAmp180());   // display 180° amplitude

            this.setParamValue(FATSAT_TX_AMP_90, pulseTXFatSat.getAmp90());

        } else {
            pulseTX.setAtt(((NumberParam) getParam(TX_ATT)));
            pulseTX.setAmp(((NumberParam) getParam(TX_AMP_90)).getValue().doubleValue() * flip_angle / 90);

            pulseTX.setAmp(((NumberParam) getParam(FATSAT_TX_AMP_90)).getValue().doubleValue());
        }

        this.setParamValue(FATSAT_FLIP_ANGLE, is_fatsat_enabled ? 90 : 0);
        // -----------------------------------------------
        // Calculation RF pulse parameters  3/3: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor_90(TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        double slice_thickness_excitation = (isMultiplanar ? sliceThickness : (sliceThickness * userMatrixDimension3D));

        Gradient gradSlice = Gradient.createGradient(getSequence(), Grad_amp_slice, Time_tx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation)) {
            slice_thickness_excitation = gradSlice.getSliceThickness();
            double slice_thickness_min = (isMultiplanar ? slice_thickness_excitation : (slice_thickness_excitation / userMatrixDimension3D));
            getUnreachParamExceptionManager().addParam(SLICE_THICKNESS.name(), sliceThickness, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
            sliceThickness = slice_thickness_min;
        }
        gradSlice.applyAmplitude();

        // -----------------------------------------------
        // calculate ADC observation time
        // -----------------------------------------------
        setSequenceTableSingleValue(Time_rx, observation_time);

        // -----------------------------------------------
        // calculate READ gradient amplitude
        // -----------------------------------------------
        Gradient gradReadout = Gradient.createGradient(getSequence(), Grad_amp_read_read, Time_rx, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableRead && !gradReadout.calculateReadoutGradient(spectralWidth, pixelDimension * acquisitionMatrixDimension1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            if (isSW) {
                getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH.name(), spectralWidth, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), (spectral_width_max / (isFovDoubled ? 2 : 1)), "SPECTRAL_WIDTH too high for the readout gradient");
            } else {
                getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH_PER_PIXEL.name(), (spectralWidth / acquisitionMatrixDimension1D), ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getMinValue(), (spectral_width_max / acquisitionMatrixDimension1D), "SPECTRAL_WIDTH too high for the readout gradient");
            }
            spectralWidth = spectral_width_max;
        }
        gradReadout.applyReadoutEchoPlanarAmplitude(is_flyback ? 1 : echoTrainLength, Order.Loop);
        setSequenceParamValue(Spectral_width, spectralWidth);

        // -------------------------------------------------------------------------------------------------
        // calculate READ_PREP  & SLICE_REF/PHASE_3D  &  PHASE_2D
        // -------------------------------------------------------------------------------------------------
        double grad_phase_application_time = ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getValue().doubleValue();
        boolean is_k_s_centred = ((BooleanParam) getParam(KS_CENTERED)).getValue();  // symetrique around 0 or go through k0
        setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);
        double readGradientRation = ((NumberParam) getParam(PREPHASING_READ_GRADIENT_RATIO)).getValue().doubleValue();

        // pre-calculate READ_prephasing max area
        Gradient gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableRead)
            gradReadPrep.refocalizeGradient(gradReadout, readGradientRation);

        // pre-calculate SLICE_refocusing  &  PHASE_3D
        double grad_ratio_slice_refoc = isEnableSlice ? ((NumberParam) getParam(SLICE_REFOCUSING_GRADIENT_RATIO)).getValue().doubleValue() : 0.0;   // get slice refocussing ratio
        Gradient gradSliceRefPhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnableSlice) {
            gradSliceRefPhase3D.refocalizeGradient(gradSlice, grad_ratio_slice_refoc);
        }
        boolean is_keyhole_allowed = ((BooleanParam) getParam(KEYHOLE_ALLOWED)).getValue();
        if (!isMultiplanar && isEnablePhase3D) {
            gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acquisitionMatrixDimension3D, acquisitionMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin, acquisitionMatrixDimension3D);
        }

        // pre-calculate PHASE_2D
        Gradient gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        if (isEnablePhase) {
            gradPhase2D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension2D : acquisitionMatrixDimension2D, acquisitionMatrixDimension2D, fovPhase, is_k_s_centred);
            gradPhase2D.reoderPhaseEncoding(plugin, 1, acquisitionMatrixDimension2D, acquisitionMatrixDimension1D);
        }


        // Check if enougth time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        double grad_area_sequence_max = 100 * (grad_phase_application_time + grad_shape_rise_time);
        double grad_area_max = Math.max(gradReadPrep.getTotalAbsArea(), Math.max(gradSliceRefPhase3D.getTotalAbsArea(), gradPhase2D.getTotalAbsArea()));            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING
        if (grad_area_max > grad_area_sequence_max) {
            double grad_phase_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 6);
            getUnreachParamExceptionManager().addParam(GRADIENT_PHASE_APPLICATION_TIME.name(), grad_phase_application_time, grad_phase_application_time_min, ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getMaxValue(), "Gradient application time too short to reach this pixel dimension");
            grad_phase_application_time = grad_phase_application_time_min;
            setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);
            gradPhase2D.rePrepare();
            gradSliceRefPhase3D.rePrepare();
            gradReadPrep.rePrepare();
        }
        gradSliceRefPhase3D.applyAmplitude(Order.Three);
        gradPhase2D.applyAmplitude(Order.Two);
        gradReadPrep.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // Flyback init and gradient calculation
        // -------------------------------------------------------------------------------------------------
        double time_flyback = ((NumberParam) getParam(GRADIENT_FLYBACK_TIME)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_flyback, is_flyback ? time_flyback : minInstructionDelay);
        setSequenceTableSingleValue(Time_grad_ramp_flyback, is_flyback ? grad_rise_time : minInstructionDelay);

        Gradient gradReadoutFlyback = Gradient.createGradient(getSequence(), Grad_amp_flyback, Time_flyback, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flyback);
        if (is_flyback) {
            gradReadoutFlyback.refocalizeGradient(gradReadout, 1);
            grad_area_max = gradReadoutFlyback.getTotalAbsArea();
            grad_area_sequence_max = 100 * (time_flyback + grad_shape_rise_time);
            if (grad_area_max > grad_area_sequence_max) {
                double grad_time_flyback_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
                time_flyback = grad_time_flyback_min;
                setParamValue(GRADIENT_FLYBACK_TIME, time_flyback);
                setSequenceTableSingleValue(Time_flyback, time_flyback);
                gradReadoutFlyback.rePrepare();
            }
            gradReadoutFlyback.applyAmplitude();
        }

        // --------------------------------------------------------------------------------------------------------------------------------------------
        // TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING
        // --------------------------------------------------------------------------------------------------------------------------------------------


        // ------------------------------------------
        // delays for sequence instructions
        // ------------------------------------------
        setSequenceTableSingleValue(Time_min_instruction, minInstructionDelay);
        // ------------------------------------------
        // calculate delays adapted to current TE & search for incoherence
        // ------------------------------------------
        // calculate actual delays between Rf-pulses and ADC
        double time1 = getTimeBetweenEvents(Events.P90 + 1, Events.Acq - 1);
        time1 = time1 + txLength90 / 2 + observation_time / 2;// Actual_TE
        time1 = removeTimeForEvents(time1, Events.Delay1); // Actual_TE without delay1

        // get minimal TE value & search for incoherence
        double max_time = ceilToSubDecimal(time1, 5);
        double te_min = max_time + minInstructionDelay;
        if (te < te_min) {
            te_min = ceilToSubDecimal(te_min, 5);
            getUnreachParamExceptionManager().addParam(ECHO_TIME.name(), te, te_min, ((NumberParam) getParam(ECHO_TIME)).getMaxValue(), "TE too short for the User Mx1D and SW");
            te = te_min;//
        }

        // set calculated the time delays to get the proper TE
        double delay1 = te - time1;
        setSequenceTableSingleValue(Time_TE_delay1, delay1);

        // ------------------------------------------
        // delays for FIR
        // ------------------------------------------
        boolean is_FIR = Instrument.instance().getDevices().getCameleon().isRemoveAcquDeadPoint();
        double lo_FIR_dead_point = is_FIR ? Instrument.instance().getDevices().getCameleon().getAcquDeadPointCount() : 0;
        double min_FIR_delay = (lo_FIR_dead_point + 2) / spectralWidth;
        double min_FIR_4pts_delay = 4 / spectralWidth;
        // ------------------------------------------
        // calculate delays adapted to correct spacing in case of ETL & search for incoherence
        // ------------------------------------------
        double echo_spacing = ((NumberParam) getParam(ECHO_SPACING)).getValue().doubleValue();
        double delay2;
        double delay2_min = Math.max(min_FIR_4pts_delay - (grad_rise_time), minInstructionDelay);
        delay2_min = Math.max(delay2_min, min_FIR_delay - (2 * grad_rise_time + getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopStartEcho) + getTimeBetweenEvents(Events.LoopEndEcho, Events.LoopEndEcho)));
        if (echoTrainLength > 1) {
            double time2 = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho); // Actual EchoLoop time
            time2 = removeTimeForEvents(time2, Events.Delay2); // Actual EchoLoop time without Delay2
            double echo_spacing_min = time2 + delay2_min;
            if (echo_spacing < echo_spacing_min) {
                echo_spacing_min = ceilToSubDecimal(echo_spacing_min, 5);
                getUnreachParamExceptionManager().addParam(ECHO_SPACING.name(), echo_spacing, echo_spacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo spacing too short for the User Mx1D and SW");
                echo_spacing = echo_spacing_min;
            }
            delay2 = echo_spacing - time2;
        } else {
            delay2 = delay2_min;
        }
        setSequenceTableSingleValue(Time_TE_delay2, delay2);

        //--------------------------------------------------------------------------------------
        //  External triggering
        //--------------------------------------------------------------------------------------
        getSequence().getPublicParam(Synchro_trigger).setValue(isTrigger ? TimeElement.Trigger.External : TimeElement.Trigger.Timer);
        getSequence().getPublicParam(Synchro_trigger).setLocked(true);
        double time_external_trigger_delay_max = minInstructionDelay;

        Table triggerdelay = setSequenceTableValues(Time_trigger_delay, Order.Four);
        if ((!isTrigger)) {
            triggerdelay.add(minInstructionDelay);
        } else {
            for (int i = 0; i < numberOfTrigger; i++) {
                double time_external_trigger_delay = roundToDecimal(triggerTime.getValue().get(i).doubleValue(), 7);
                time_external_trigger_delay = time_external_trigger_delay < minInstructionDelay ? minInstructionDelay : time_external_trigger_delay;
                triggerdelay.add(time_external_trigger_delay);
                time_external_trigger_delay_max = Math.max(time_external_trigger_delay_max, time_external_trigger_delay);
            }
        }

        setSequenceParamValue(Ext_trig_source, TRIGGER_CHANEL);

        //--------------------------------------------------------------------------------------
        //  Fat-Sat gradient
        //--------------------------------------------------------------------------------------

        double grad_fatsat_application_time = ((NumberParam) getParam(FATSAT_GRAD_APP_TIME)).getValue().doubleValue();
        setSequenceTableFirstValue(Time_grad_fatsat, is_fatsat_enabled ? grad_fatsat_application_time : minInstructionDelay);
        setSequenceTableFirstValue(Time_grad_ramp_fatsat, is_fatsat_enabled ? grad_rise_time : minInstructionDelay);

        Gradient gradFatsatRead = Gradient.createGradient(getSequence(), Grad_amp_fatsat_read, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);
        Gradient gradFatsatPhase = Gradient.createGradient(getSequence(), Grad_amp_fatsat_phase, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);
        Gradient gradFatsatSlice = Gradient.createGradient(getSequence(), Grad_amp_fatsat_slice, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);


        if (is_fatsat_enabled) {
            double pixel_dimension_ph = ((NumberParam) getParamFromName("RESOLUTION_PHASE")).getValue().doubleValue();
            double pixel_dimension_sl = ((NumberParam) getParamFromName("RESOLUTION_SLICE")).getValue().doubleValue();
            boolean test_grad = gradFatsatRead.addSpoiler(pixelDimension, 2);
            test_grad = gradFatsatPhase.addSpoiler(pixel_dimension_ph, 2) && test_grad;
            test_grad = gradFatsatSlice.addSpoiler(pixel_dimension_sl, 2) && test_grad;
//
            if (!test_grad) {
                double min_fatsat_application_time = Math.max(gradFatsatRead.getMinTopTime(), Math.max(gradFatsatPhase.getMinTopTime(), gradFatsatSlice.getMinTopTime()));
                this.getUnreachParamExceptionManager().addParam(FATSAT_GRAD_APP_TIME.name(), grad_fatsat_application_time, min_fatsat_application_time, ((NumberParam) getParam(FATSAT_GRAD_APP_TIME)).getMaxValue(), "FATSAT_GRAD_APP_TIME too short to get correct Spoiling");
                grad_fatsat_application_time = min_fatsat_application_time;
                setSequenceTableSingleValue(Time_grad_fatsat, grad_fatsat_application_time);
                gradFatsatRead.rePrepare();
                gradFatsatPhase.rePrepare();
                gradFatsatSlice.rePrepare();
            }
        }
        gradFatsatRead.applyAmplitude();
        gradFatsatPhase.applyAmplitude();
        gradFatsatSlice.applyAmplitude();

        // -------------------------------------------------------------------------------------------------
        // calculate Phase 2D, 3D and Read REWINDING - SPOILER area, check Grad_Spoil < GMAX
        // -------------------------------------------------------------------------------------------------

        // timing : grad_phase_application_time must be < grad_spoiler_application_time if rewinding
        //  boolean is_grad_rewinding = ((BooleanParam) getParam(GRADIENT_ENABLE_REWINDING)).getValue();// get slice refocussing ratio
        double grad_spoiler_application_time = ((NumberParam) getParam(GRADIENT_SPOILER_TIME)).getValue().doubleValue();
        if (is_grad_rewinding && grad_phase_application_time > grad_spoiler_application_time) {
            getUnreachParamExceptionManager().addParam(GRADIENT_SPOILER_TIME.name(), grad_spoiler_application_time, grad_phase_application_time, ((NumberParam) getParam(GRADIENT_SPOILER_TIME)).getMaxValue(), "Gradient Spoiler top time must be longer than Phase Application Time");
            grad_spoiler_application_time = grad_phase_application_time;
        }
        setSequenceTableSingleValue(Time_grad_spoiler_top, grad_spoiler_application_time);

        Gradient gradSliceSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_slice, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradPhaseSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_phase, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradReadSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_read, Time_grad_spoiler_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);

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
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadoutFlyback, readGradientRation);
                else
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadout, 1 - (readGradientRation));
        }
        // Spoiler :
        //    ListNumberParam grad_amp_spoiler_sl_ph_re = (ListNumberParam) getParam(GRAD_AMP_SPOILER_SL_PH_RE);
        if (((BooleanParam) getParam(GRADIENT_ENABLE_SPOILER)).getValue()) {
            double spoilerAmp = grad_amp_spoiler_sl_ph_re.getValue().get(0).doubleValue();
            if (!gradSliceSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.getValue().set(0, spoilerAmp - gradSliceSpoiler.getSpoilerExcess()).doubleValue();

            spoilerAmp = grad_amp_spoiler_sl_ph_re.getValue().get(1).doubleValue();
            if (!gradPhaseSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.getValue().set(1, spoilerAmp - gradPhaseSpoiler.getSpoilerExcess()).doubleValue();

            spoilerAmp = grad_amp_spoiler_sl_ph_re.getValue().get(2).doubleValue();
            if (!gradReadSpoiler.addSpoiler(spoilerAmp))
                grad_amp_spoiler_sl_ph_re.getValue().set(2, spoilerAmp - gradReadSpoiler.getSpoilerExcess()).doubleValue();
        }
        gradPhaseSpoiler.applyAmplitude();
        gradSliceSpoiler.applyAmplitude();
        gradReadSpoiler.applyAmplitude(Order.Three);


        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        int nb_of_interleaved_slice = ((NumberParam) getSequence().getParam(Nb_interveaved_slice)).getValue().intValue();
        int nb_planar_excitation = (isMultiplanar ? acquisitionMatrixDimension3D : 1);
        int slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nb_of_interleaved_slice + 1) : 1;
        double delay_before_multi_planar_loop = getTimeBetweenEvents(Events.Start, Events.TriggerDelay - 1) + getTimeBetweenEvents(Events.TriggerDelay + 1, Events.LoopMultiPlanarStart - 1) + time_external_trigger_delay_max;
        double delay_before_echo_loop = getTimeBetweenEvents(Events.LoopMultiPlanarStart, Events.LoopStartEcho - 1);
        double delay_echo_loop = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho);
        double delay_spoiler = getTimeBetweenEvents(Events.LoopEndEcho + 1, Events.LoopMultiPlanarEnd - 2);// grad_phase_application_time + grad_rise_time * 2;
        double min_flush_delay = min_time_per_acq_point * acquisitionMatrixDimension1D * echoTrainLength * slices_acquired_in_single_scan * 2;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
        min_flush_delay = Math.max(CameleonVersion105 ? min_flush_delay : 0, minInstructionDelay);

        double time_seq_to_end_spoiler = (delay_before_multi_planar_loop + (delay_before_echo_loop + (echoTrainLength * delay_echo_loop) + delay_spoiler) * slices_acquired_in_single_scan);
        double tr_min = time_seq_to_end_spoiler + minInstructionDelay * (slices_acquired_in_single_scan * 2 + 1) + min_flush_delay;// 2 +( 2 minInstructionDelay: Events. 22 +(20&21
        if (tr < tr_min) {
            tr_min = ceilToSubDecimal(tr_min, 3);
            this.getUnreachParamExceptionManager().addParam(REPETITION_TIME.name(), tr, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (ETL * User Mx3D/Shoot3D) in a singl scan");
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
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler + last_delay + min_flush_delay)) / slices_acquired_in_single_scan - minInstructionDelay;
                time_tr_delay.add(tr_delay);
            }
        } else {
            tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_flush_delay)) / slices_acquired_in_single_scan - minInstructionDelay;
            tr_delay = (tr - last_delay - min_flush_delay - time_seq_to_end_spoiler) / slices_acquired_in_single_scan - minInstructionDelay;
            time_tr_delay.add(tr_delay);
        }
        setSequenceTableSingleValue(Time_last_delay, last_delay);
        setSequenceTableSingleValue(Time_flush_delay, min_flush_delay);


        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCE
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------
        int number_of_averages = ((NumberParam) getParam(NUMBER_OF_AVERAGES)).getValue().intValue();
        boolean is_dynamic_min_time = ((BooleanParam) getParam(DYNAMIC_MIN_TIME)).getValue();

        double frame_acquisition_time = number_of_averages * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal(frame_acquisition_time + minInstructionDelay + min_flush_delay, 1);
        double time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (isDynamic) {
            //Dynamic Sequence
            time_between_frames = ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getValue().doubleValue();
            if (is_dynamic_min_time) {
                time_between_frames = time_between_frames_min;
                setParamValue(DYN_TIME_BTW_FRAMES, time_between_frames_min);
            } else if (time_between_frames < (time_between_frames_min)) {
                this.getUnreachParamExceptionManager().addParam(DYN_TIME_BTW_FRAMES.name(), time_between_frames, time_between_frames_min, ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getMaxValue(), "Minimum frame acquisition time ");
                time_between_frames = time_between_frames_min;
            }
            interval_between_frames_delay = Math.max(time_between_frames - frame_acquisition_time, min_flush_delay);
        }
        setSequenceTableSingleValue(Time_btw_dyn_frames, interval_between_frames_delay);
        // ------------------------------------------------------------------
        // Total Acquisition Time
        // ------------------------------------------------------------------
        double total_acquisition_time = time_between_frames * numberOfDynamicAcquisition + tr * preScan;
        setParamValue(SEQUENCE_TIME, total_acquisition_time);

        // -----------------------------------------------
        // Phase Reset
        // -----------------------------------------------
        setSequenceParamValue(Phase_reset, PHASE_RESET);
        // ----------- init Freq offset---------------------
        setSequenceTableSingleValue(Frequency_offset_init, 0.0);// PSD should start with a zero offset frequency pulse

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY offsets tables for slice positionning
        // ------------------------------------------------------------------
        if (isMultiplanar && nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_planar_excitation, spacingBetweenSlice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acquisitionMatrixDimension1D * echoTrainLength, slices_acquired_in_single_scan);
            pulseTX.setFrequencyOffset(slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
        } else {
            //3D CASE :
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, 1, 0, off_center_distance_3D);
            pulseTX.setFrequencyOffset(Order.Three);
        }

        // ------------------------------------------------------------------
        // calculate TX FREQUENCY offsets compensation
        // ------------------------------------------------------------------
        RFPulse pulseTXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep);
        pulseTXPrep.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        RFPulse pulseTXComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_comp);
        pulseTXComp.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);


        // ------------------------------------------------------------------
        //calculate TX FREQUENCY FATSAT and compensation
        // ------------------------------------------------------------------

        pulseTXFatSat.setFrequencyOffset(is_fatsat_enabled ? tx_frequency_offset_90_fs : 0.0);
        pulseTXFatSat.setFrequencyOffset(tx_frequency_offset_90_fs);

        setSequenceTableFirstValue(Time_before_fatsat_pulse, minInstructionDelay);
        RFPulse pulseTXFatSatPrep = RFPulse.createRFPulse(getSequence(), Time_before_fatsat_pulse, Freq_offset_tx_fatsat_prep);
        pulseTXFatSatPrep.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);
        RFPulse pulseTXFatSatComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_fatsat, Freq_offset_tx_fatsat_comp);
        pulseTXFatSatComp.setCompensationFrequencyOffset(pulseTXFatSat, 0.5);


        // ------------------------------------------------------------------
        //  blanking smartTTL_FatSat_table
        // ------------------------------------------------------------------

        Table smartTTL_FatSat_table = setSequenceTableValues(SmartTTL_FatSat, Order.Four);
        if (is_fatsat_enabled) {
            double slice_time = (delay_before_echo_loop + (echoTrainLength * delay_echo_loop)
                    + getTimeBetweenEvents(Events.LoopEndEcho + 1, Events.LoopMultiPlanarEnd));
            double fatSat_repetition_Time = ((NumberParam) getParam(FATSAT_PERIODE)).getValue().doubleValue();
            double ttl_periode;
            int sliceRep_ttl;
            int secondDim;
            if (tr > fatSat_repetition_Time) {
                secondDim = 1;
                smartTTL_FatSat_table.setOrder(Order.Loop);
            } else if (number_of_averages > 1) {
                secondDim = number_of_averages;
                smartTTL_FatSat_table.setOrder(Order.OneLoop);
            } else {
                secondDim = acquisitionMatrixDimension2D;
                smartTTL_FatSat_table.setOrder(Order.TwoLoop);
            }
            int nb_ttl = Math.max(1, (int) Math.round(tr * secondDim / fatSat_repetition_Time));
            sliceRep_ttl = Math.max(1, (int) Math.floor(slices_acquired_in_single_scan * secondDim / nb_ttl));
            ttl_periode = sliceRep_ttl * slice_time;
            smartTTL_FatSat_table.add(1);
            for (int i = 0; i < sliceRep_ttl - 1; i++) {
                smartTTL_FatSat_table.add(0);
            }
            setParamValue(FATSAT_PERIODE_EFF, ttl_periode);
        } else {
            smartTTL_FatSat_table.add(0);
        }

        //----------------------------------------------------------------------
        // OFF CENTER FIELD OF VIEW 1D
        // modify RX FREQUENCY OFFSET
        //----------------------------------------------------------------------
        RFPulse pulseRX = RFPulse.createRFPulse(getSequence(), Time_rx, Rx_freq_offset, Rx_phase);
        pulseRX.setFrequencyOffsetReadout(gradReadout, off_center_distance_1D);

        //fill the OFF_CENTER_FIELD_OF_VIEW_EFF User Parameter
        ArrayList<Number> off_center_distanceList = new ArrayList<>();
        off_center_distanceList.add(off_center_distance_1D);
        off_center_distanceList.add(0);
        off_center_distanceList.add(0);

        setParamValue(OFF_CENTER_FIELD_OF_VIEW_EFF, off_center_distanceList);

        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        //----------------------------------------------------------------------
        double timeADC1 = getTimeBetweenEvents(Events.Acq - 1, Events.Acq - 1) + observation_time / 2.0;
        double timeADC2 = getTimeBetweenEvents(Events.Acq + 1, Events.Acq + 2) + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_prep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_comp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);

        pulseRX.setPhase(0.0);
        //--------------------------------------------------------------------------------------
        //  calculate RF_SPOILING
        //--------------------------------------------------------------------------------------
        RFPulse pulseRFSpoiler = RFPulse.createRFPulse(getSequence(), Time_rf_spoiling, FreqOffset_RFSpoiling);
        pulseRFSpoiler.setFrequencyOffsetForPhaseShift(is_rf_spoiling ? 117.0 : 0.0);

        // ----------------------------------------------------------------------------------------------
        // modify RX PHASE TABLE to handle OFF CENTER FOV 2D in both cases or PHASE CYCLING
        // ----------------------------------------------------------------------------------------------
        setSequenceTableSingleValue(Rx_phase, 0);

        //--------------------------------------------------------------------------------------
        //Export DICOM
        //--------------------------------------------------------------------------------------
        // Set  TRIGGER_TIME for dynamic or trigger acquisition
        if (isDynamic && (numberOfDynamicAcquisition != 1) && !isTrigger) {
            ArrayList<Number> arrayListTrigger = new ArrayList<>();
            for (int i = 0; i < numberOfDynamicAcquisition; i++) {
                arrayListTrigger.add(i * time_between_frames);
            }
//            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.TRIGGER_TIME.name()), arrayListTrigger);       // associate TE to images for DICOM export
//            putVariableParameter(list, (4));
            setParamValue(TRIGGER_TIME, arrayListTrigger);
        }

        // Set  ECHO_TIME
        if (echoTrainLength != 1) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acquisitionMatrixDimension4D; i++) {
                arrayListEcho.add(echo_spacing * i);
            }
            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.ECHO_TIME.name()), arrayListEcho);       // associate TE to images for DICOM export
            putVariableParameter(list, (4));
        }

        // ------------------------------------------------------------------
        // calculate Acquisition Time Offset and MultiSeries Parameter list
        // ------------------------------------------------------------------
        int number_of_MultiSeries = 1;
        double time_between_MultiSeries = 0;
        ArrayList<Number> multiseries_valuesList = new ArrayList<>();
        String multiseries_parametername = "";

        if (echoTrainLength != 1) {
            number_of_MultiSeries = echoTrainLength;
            time_between_MultiSeries = te;
            multiseries_parametername = "TE";
//            for (int i = 1; i <= number_of_MultiSeries; i++) {
            //double multiseries_value = Math.round(i * te * 1e5) / 1e2;
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round((te + i * echo_spacing) * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        } else if (isTrigger && numberOfTrigger != 1) {
            number_of_MultiSeries = numberOfTrigger;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TRIGGER DELAY";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round(triggerTime.getValue().get(i).doubleValue() * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        }
        setParamValue(MULTISERIES_PARAMETER_VALUE, multiseries_valuesList);
        setParamValue(MULTISERIES_PARAMETER_NAME, multiseries_parametername);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();
        double acqusition_time;
        for (int i = 0; i < numberOfDynamicAcquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acqusition_time = roundToDecimal((i * time_between_frames + j * time_between_MultiSeries), 3);
                acquisition_timesList.add(acqusition_time);
            }
        }
        setParamValue(ACQUISITION_TIME_OFFSET, acquisition_timesList);


    }
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // End After Routine
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //
    //                                                          END OF SEQUENCE GENERATOR
    //
    // *********************************************************************************************************************************************
    // *** END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR ********* END OF SEQUENCE GENERATOR
    // *********************************************************************************************************************************************


    private double getTx_bandwidth_factor_90(GradientEchoParams tx_shape, GradientEchoParams tx_bandwith_factor_param, GradientEchoParams tx_bandwith_factor_param3d) {
        double tx_bandwidth_factor_90;
        ListNumberParam tx_bandwith_factor_table = (ListNumberParam) getParam(tx_bandwith_factor_param);
        ListNumberParam tx_bandwith_factor_3D_table = (ListNumberParam) getParam(tx_bandwith_factor_param3d);

        if (isMultiplanar) {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(3).doubleValue();
            } else if ("RAMP".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(3).doubleValue();
            } else if ("SLR_8_5152".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(4).doubleValue();
            } else if ("SLR_4_2576".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(5).doubleValue();
            } else {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(0).doubleValue();
            }
        } else {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(3).doubleValue();
            } else if ("RAMP".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(3).doubleValue();
            } else if ("SLR_8_5152".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(4).doubleValue();
            } else if ("SLR_4_2576".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(5).doubleValue();
            } else {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(0).doubleValue();
            }
        }
        return tx_bandwidth_factor_90;
    }

    private double ceilToSubDecimal(double numberToBeRounded, double Order) {
        return Math.ceil(numberToBeRounded * Math.pow(10, Order)) / Math.pow(10, Order);
    }

    private double roundToDecimal(double numberToBeRounded, double order) {
        return Math.round(numberToBeRounded * Math.pow(10, order)) / Math.pow(10, order);
    }


    private void setSequenceTableSingleValue(String tableName, double... values) {
        // uses Order.One because there are no tables in this dimension: compilation issue
        setSequenceTableValues(tableName, Order.FourLoop, values);
    }

    private Table setSequenceTableValues(String tableName, Order order, double... values) {
        Table table = getSequence().getPublicTable(tableName);
        table.clear();
        table.setOrder(order);
        table.setLocked(true);

        for (double value : values) {
            table.add(value);
        }
        return table;
    }


    private double getOff_center_distance_1D_2D_3D(int dim) {
        ListNumberParam image_orientation = (ListNumberParam) getParam(IMAGE_ORIENTATION_SUBJECT);
        double[] direction_index = new double[9];
        direction_index[0] = image_orientation.getValue().get(0).doubleValue();
        direction_index[1] = image_orientation.getValue().get(1).doubleValue();
        direction_index[2] = image_orientation.getValue().get(2).doubleValue();
        direction_index[3] = image_orientation.getValue().get(3).doubleValue();
        direction_index[4] = image_orientation.getValue().get(4).doubleValue();
        direction_index[5] = image_orientation.getValue().get(5).doubleValue();
        direction_index[6] = direction_index[1] * direction_index[5] - direction_index[2] * direction_index[4];
        direction_index[7] = direction_index[2] * direction_index[3] - direction_index[0] * direction_index[5];
        direction_index[8] = direction_index[0] * direction_index[4] - direction_index[1] * direction_index[3];

        double norm_vector_read = Math.sqrt(Math.pow(direction_index[0], 2) + Math.pow(direction_index[1], 2) + Math.pow(direction_index[2], 2));
        double norm_vector_phase = Math.sqrt(Math.pow(direction_index[3], 2) + Math.pow(direction_index[4], 2) + Math.pow(direction_index[5], 2));
        double norm_vector_slice = Math.sqrt(Math.pow(direction_index[6], 2) + Math.pow(direction_index[7], 2) + Math.pow(direction_index[8], 2));

        //Offset according to animal position
        double off_center_distance_Z = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_Z)).getValue().doubleValue();
        double off_center_distance_Y = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_Y)).getValue().doubleValue();
        double off_center_distance_X = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_X)).getValue().doubleValue();

        //Offset according to READ PHASE and SLICE
        double off_center_distance;
        switch (dim) {
            case 1:
                off_center_distance = off_center_distance_X * direction_index[0] / norm_vector_read + off_center_distance_Y * direction_index[1] / norm_vector_read + off_center_distance_Z * direction_index[2] / norm_vector_read;
                break;
            case 2:
                off_center_distance = off_center_distance_X * direction_index[3] / norm_vector_phase + off_center_distance_Y * direction_index[4] / norm_vector_phase + off_center_distance_Z * direction_index[5] / norm_vector_phase;
                break;
            case 3:
                off_center_distance = off_center_distance_X * direction_index[6] / norm_vector_slice + off_center_distance_Y * direction_index[7] / norm_vector_slice + off_center_distance_Z * direction_index[8] / norm_vector_slice;
                break;
            default:
                off_center_distance = 0;
                break;
        }
        return off_center_distance;
    }


    /**
     * Find the next inferior integer which can divide the dividend : dividend /
     * -divisor- = integer
     *
     * @param divisor  dividend / DIVISOR = integer
     * @param dividend DIVIDEND / divisor = integer
     * @return Next inferior integer which is a multiple of the dividend
     */
    private int getInferiorDivisorToGetModulusZero(int divisor, int dividend) {
        boolean exit = true;
        int div;
        int new_divisor;
        do {
            div = (int) Math.ceil(dividend / ((double) divisor));
            new_divisor = (int) Math.floor(dividend / ((double) div));
            if (dividend % new_divisor == 0) {
                exit = false;
            } else {
                divisor = new_divisor;
            }
        } while (exit);
        return new_divisor;
    }

    public List<RoleEnum> getPluginAccess() {
        return Collections.singletonList(RoleEnum.User);
    }

    //<editor-fold defaultstate="collapsed" desc="Generated Code (RS2D)">
    public void initParam() {
        for (GradientEchoParams param : GradientEchoParams.values()) {
            addParam(param.build());
        }
    }

    public Param getParam(GradientEchoParams param) {
        return getParamFromName(param.name());
    }

    public void setParamValue(GradientEchoParams param, Object value) {
        setParamValue(param.name(), value);
    }

    public String getName() {
        return "GRADIENT_ECHO";
    }

    public float getVersion() {
        return 0.0f;
    }
    //</editor-fold>
}