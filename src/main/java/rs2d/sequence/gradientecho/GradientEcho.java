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

    private String sequenceVersion = "Version8.0i";
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

    private int echoTrainLength;
    private double echo_spacing;


    private double spectralWidth;
    private boolean isSW;
    private double tr;
    private double te;

    private double sliceThickness;
    double spacingBetweenSlice;
    private double pixelDimension;
    private double fov;
    private double fovPhase;
    private double fov3d;
    private boolean isFovDoubled;
    private double off_center_distance_1D;
    private double off_center_distance_2D;
    private double off_center_distance_3D;

    private double txLength90;

    private boolean isDynamic;
    private int numberOfDynamicAcquisition;
    private boolean isDynamicMinTime;


    private boolean isTrigger;
    private ListNumberParam triggerTime;
    private int numberOfTrigger;

    boolean is_flyback;
    String kspace_filling;

    boolean is_flowcomp;

    boolean is_fatsat_enabled;

    private int nb_satband;
    boolean is_satband_enabled;
    boolean is_tof_enabled;

    boolean is_interleaved_echo_train;
    private int numberOfnumInterleavedEchoTrain;

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

        List<String> tx_shape = asList("HARD", "GAUSSIAN", "SINC3", "SINC5","SLR_8_5152","SLR_4_2576");
        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        ((TextParam) getParam(TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE)).setRestrictedToSuggested(true);

        ((TextParam) getParam(SATBAND_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(SATBAND_TX_SHAPE)).setRestrictedToSuggested(true);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(FATSAT_TX_SHAPE)).setRestrictedToSuggested(true);
//        ((TextParam) getParam(TOF2D_SB_TX_SHAPE)).setSuggestedValues(tx_shape);
//        ((TextParam) getParam(TOF2D_SB_TX_SHAPE)).setRestrictedToSuggested(true);


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

        number_of_averages = ((NumberParam) getParam(NUMBER_OF_AVERAGES)).getValue().intValue();
        userMatrixDimension1D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_1D)).getValue().intValue();
        userMatrixDimension2D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_2D)).getValue().intValue();
        userMatrixDimension3D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_3D)).getValue().intValue();
        is_partial_oversampling = (((BooleanParam) getParam(PARTIAL_OVERSAMPLING)).getValue());
        is_keyhole_allowed = ((BooleanParam) getParam(KEYHOLE_ALLOWED)).getValue();

        echoTrainLength = ((NumberParam) getParam(ECHO_TRAIN_LENGTH)).getValue().intValue();
        echo_spacing = ((NumberParam) getParam(ECHO_SPACING)).getValue().doubleValue();

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
        isDynamicMinTime = ((BooleanParam) getParam(DYNAMIC_MIN_TIME)).getValue();

        isTrigger = (((BooleanParam) getParam(TRIGGER_EXTERNAL)).getValue());
        triggerTime = (ListNumberParam) getParam(TRIGGER_TIME);
        numberOfTrigger = isTrigger ? triggerTime.getValue().size() : 1;
        isTrigger = isTrigger && (numberOfTrigger > 0);

        is_flyback = (((BooleanParam) getParam(FLYBACK)).getValue());
        kspace_filling = ((String) getParam(KSPACE_FILLING).getValue());

        is_flowcomp = (((BooleanParam) getParam(FLOW_COMPENSATION)).getValue());

        is_fatsat_enabled = (((BooleanParam) getParam(FAT_SATURATION_ENABLED)).getValue());

        is_satband_enabled = (((BooleanParam) getParam(SATBAND_ENABLED)).getValue());
        is_tof_enabled = (((BooleanParam) getParam(TOF2D_ENABLED)).getValue());
        is_rf_spoiling = ((BooleanParam) getParam(RF_SPOILING)).getValue();

        is_interleaved_echo_train = ((BooleanParam) getParam(INTERLEAVED_ECHO_TRAIN)).getValue();
        numberOfnumInterleavedEchoTrain = ((NumberParam) getParam(INTERLEAVED_NUM_OF_ECHO_TRAIN)).getValue().intValue();


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

        nb_scan_1d = number_of_averages;
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

        is_tof_enabled = !isMultiplanar ? false : is_tof_enabled; // TOF not allowed in 3D
        setParamValue(TOF2D_ENABLED, is_tof_enabled);

        is_satband_enabled = is_tof_enabled ? false : is_satband_enabled;
        this.setParamValue(SATBAND_ENABLED, is_satband_enabled);

        // MATRIX

        boolean is_partial_oversampling = (((BooleanParam) getParam(PARTIAL_OVERSAMPLING)).getValue());
        is_partial_oversampling = (isMultiplanar || userMatrixDimension3D < 8) ? false : is_partial_oversampling;
        setParamValue(PARTIAL_OVERSAMPLING, is_partial_oversampling);

        // 3D ZERO FILLING
        double partial_slice;
        if (isMultiplanar || is_partial_oversampling) {
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
        nb_of_shoot_3d = isMultiplanar ? getInferiorDivisorToGetModulusZero(nb_of_shoot_3d, acquisitionMatrixDimension3D) : acquisitionMatrixDimension3D;
        nb_of_shoot_3d = is_tof_enabled ? acquisitionMatrixDimension3D : nb_of_shoot_3d; // TOF does not allow interleaved slice within the TR

        int nb_interleaved_excitation = isMultiplanar ? (int) Math.ceil((acquisitionMatrixDimension3D / nb_of_shoot_3d)) : 1;
        setParamValue(NUMBER_OF_SHOOT_3D, nb_of_shoot_3d);
        setParamValue(NUMBER_OF_INTERLEAVED_SLICE, isMultiplanar ? nb_interleaved_excitation : 0);

        acquisitionMatrixDimension3D = is_partial_oversampling ? (int) Math.round(acquisitionMatrixDimension3D / 0.8 / 2) * 2 : acquisitionMatrixDimension3D;
        userMatrixDimension3D = is_partial_oversampling ? (int) Math.round(userMatrixDimension3D / 0.8 / 2) * 2 : userMatrixDimension3D;

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

        fov3d = sliceThickness * userMatrixDimension3D + spacingBetweenSlice * (userMatrixDimension3D - 1);
        setParamValue(FIELD_OF_VIEW_3D, fov3d);    // FOV ratio for display

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

        // keyhole_allowed only available when dynamic

        is_keyhole_allowed = !isDynamic ? false : is_keyhole_allowed;
        setParamValue(KEYHOLE_ALLOWED, is_keyhole_allowed);

        // ETL only available when not kewholed
        is_interleaved_echo_train = is_keyhole_allowed ? false : is_interleaved_echo_train;
        setParamValue(INTERLEAVED_ECHO_TRAIN, is_interleaved_echo_train);

        echoTrainLength = is_keyhole_allowed ? 1 : echoTrainLength;
        setParamValue(ECHO_TRAIN_LENGTH, echoTrainLength);

        if (is_interleaved_echo_train) { //TODO interleaved echo train
            isDynamic = true;
            setParamValue(DYNAMIC_SEQUENCE, isDynamic);

            isDynamicMinTime = true;
            setParamValue(DYNAMIC_MIN_TIME, isDynamicMinTime);

            numberOfDynamicAcquisition = numberOfnumInterleavedEchoTrain;
            setParamValue(DYN_NUMBER_OF_ACQUISITION, numberOfDynamicAcquisition);

            kspace_filling = "Linear";
            setParamValue(KSPACE_FILLING, kspace_filling);
            //setParamValue("KEYHOLE_ALLOWED", false);
        }

        if (echoTrainLength == 1) {
            echo_spacing = 0;
            setParamValue(ECHO_SPACING, echo_spacing);
            is_flyback = false;
            setParamValue(FLYBACK, is_flyback);
        } else {
            kspace_filling = "Linear";
            setParamValue(KSPACE_FILLING, kspace_filling);
        }

        // Avoid multi trigger time when  Multi echo or dynamic
        if (numberOfTrigger != 1 && ( isDynamic)) {
            double tmp = triggerTime.getValue().get(0).doubleValue();
            triggerTime.getValue().clear();
            triggerTime.getValue().add(tmp);
            numberOfTrigger = 1;
        }

        //Dynamic and multi echo are filled into the 4th Dimension
        nb_scan_4d = numberOfTrigger * numberOfDynamicAcquisition;
        acquisitionMatrixDimension4D = nb_scan_4d * this.echoTrainLength;
        setParamValue(USER_MATRIX_DIMENSION_4D, nb_scan_4d);

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
                kspace_filling = "Linear";
                setParamValue(KSPACE_FILLING, kspace_filling);
                break;
        }
        int[] position_sli_ph_rea = satBandPrep(SATBAND_ORIENTATION.name(), ORIENTATION.name(), IMAGE_ORIENTATION_SUBJECT.name());
        nb_satband = is_satband_enabled ? (int) Arrays.stream(position_sli_ph_rea).filter(item -> item == 1).count() : 1;
        nb_satband = is_tof_enabled ? 1 : nb_satband;

        // -----------------------------------------------
        // set the ACQUISITION_MATRIX and Nb XD
        // -----------------------------------------------        // set the calculated acquisition matrix

        setParamValue(ACQUISITION_MATRIX_DIMENSION_1D, acquisitionMatrixDimension1D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_2D, acquisitionMatrixDimension2D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_3D, isKSCenterMode && !isMultiplanar ? 1 : acquisitionMatrixDimension3D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_4D, isKSCenterMode ? 1 : acquisitionMatrixDimension4D);

        // set the calculated sequence dimensions

        if (isKSCenterMode) {
            nb_scan_1d = 1;
            nb_scan_2d = 2;
            nb_scan_3d = !isMultiplanar ? 1 : nb_scan_3d;
            nb_scan_4d = 1;
        }
        setSequenceParamValue(Pre_scan, preScan); // Do the prescan
        setSequenceParamValue(Nb_point, acquisitionMatrixDimension1D);
        setSequenceParamValue(Nb_1d, nb_scan_1d);
        setSequenceParamValue(Nb_2d, nb_scan_2d);
        setSequenceParamValue(Nb_3d, nb_scan_3d);
        setSequenceParamValue(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions
        setSequenceParamValue(Nb_echo, this.echoTrainLength - 1);
        setSequenceParamValue(Nb_interveaved_slice, nb_interleaved_excitation - 1);
        setSequenceParamValue(Nb_sb_loop, nb_satband - 1);

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

        if (this.echoTrainLength != 1) {
            seqDescription = seqDescription.concat("_ETL=" + String.valueOf(this.echoTrainLength));
        }
        if (isTrigger && numberOfTrigger != 1) {
            seqDescription = seqDescription.concat("_TRIG=" + String.valueOf(numberOfTrigger));
        } else if (isTrigger) {
            seqDescription = seqDescription.concat("_TRIG");
        }
        if (isDynamic) {
            seqDescription = seqDescription.concat("_DYN=" + String.valueOf(numberOfDynamicAcquisition));
        }
        if (is_satband_enabled) {
            seqDescription = seqDescription.concat("_SATBAND");
        }
        if (is_fatsat_enabled) {
            seqDescription = seqDescription.concat("_FATSAT");
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

        setParamValue(OFF_CENTER_FIELD_OF_VIEW_X, roundToDecimal(getOff_center_distance_X_Y_Z(1, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_Y, roundToDecimal(getOff_center_distance_X_Y_Z(2, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_Z, roundToDecimal(getOff_center_distance_X_Y_Z(3, off_center_distance_1D, off_center_distance_2D, off_center_distance_3D), 5));


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
        setSequenceParamValue(Grad_enable_flowcomp, is_flowcomp);

        setSequenceParamValue(Grad_enable_flyback, is_flyback);

        setSequenceParamValue(Enable_fatsat, is_fatsat_enabled);
        setSequenceParamValue(Enable_sb, is_satband_enabled);

        // ------------------------------------------
        // delays for sequence instructions
        // ------------------------------------------
        setSequenceTableSingleValue(Time_min_instruction, minInstructionDelay);

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
        // Calculation RF pulse parameters  1/3 : Create and prepare pulses and set Shape
        // -----------------------------------------------

        // GE RF pulse
        setSequenceTableSingleValue(Time_tx, txLength90);
        RFPulse pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset);

        // Fat SAT RF pulse
        double tx_bandwidth_90_fs = ((NumberParam) getParam(FATSAT_BANDWIDTH)).getValue().doubleValue();
        double tx_bandwidth_factor_90_fs = getTx_bandwidth_factor(FATSAT_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_length_90_fs = is_fatsat_enabled ? tx_bandwidth_factor_90_fs / tx_bandwidth_90_fs : minInstructionDelay;
        setParamValue(FATSAT_TX_LENGTH, tx_length_90_fs);
        setSequenceTableSingleValue(Time_tx_fatsat, tx_length_90_fs);
        //
        double grad_fatsat_application_time = ((NumberParam) getParam(FATSAT_GRAD_APP_TIME)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_grad_fatsat, is_fatsat_enabled ? grad_fatsat_application_time : minInstructionDelay);
        //
        setSequenceTableSingleValue(Time_before_fatsat_pulse, minInstructionDelay);
        setSequenceTableSingleValue(Time_grad_ramp_fatsat, is_fatsat_enabled ? grad_rise_time : minInstructionDelay);
        //
        RFPulse pulseTXFatSat = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_fatsat, Tx_phase_fatsat, Time_tx_fatsat, Tx_shape_fatsat, Tx_shape_phase_fatsat, Freq_offset_tx_fatsat);


        // SAT Band or TOF RF pulse
        setSequenceTableSingleValue(Time_grad_ramp_sb, is_satband_enabled || is_tof_enabled ? grad_rise_time : minInstructionDelay);
        setSequenceTableSingleValue(Time_grad_sb, is_satband_enabled || is_tof_enabled ? 0.0005 : minInstructionDelay);
        double tx_length_sb = is_satband_enabled || is_tof_enabled ? txLength90 : minInstructionDelay;
        setSequenceTableSingleValue(Time_tx_sb, tx_length_sb);
        //
        RFPulse pulseTXSatBandTOF = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp_sb, Tx_phase_sb, Time_tx_sb, Tx_shape_sb, Tx_shape_phase_sb, Freq_offset_tx_sb);
        //
        // Correction of the 90 water saturation RF angle according to the water T1 relaxation.
        // double time_tau_sat = 0.0 / 1000.0; // TODO
        double flip_angle = ((NumberParam) getParam(FLIP_ANGLE)).getValue().doubleValue();
        flip_angle = is_tof_enabled && pulseTXSatBandTOF.isSlr() ? 90 : flip_angle;
        setParamValue(FLIP_ANGLE, flip_angle);
        double flip_angle_satband = 0;
        if (is_satband_enabled || is_tof_enabled) {
            flip_angle_satband = 90;
            double time_tau_sat = getTimeBetweenEvents(Events.FatSatPulse, Events.P90); /////////////////////////////////////////////////////////////////////////////////////////////////////////
            //
            double time_t1_satband = ((NumberParam) getParam(SATBAND_T1)).getValue().doubleValue();
            double t1_relax_time_sat = time_t1_satband / 1000.0;   // T1_tissue = 500ms
            //
            double flip_90_sat = flip_angle == 90 ? Math.acos((1 - Math.exp(time_tau_sat / t1_relax_time_sat)) / (1 - Math.exp((time_tau_sat - tr) / t1_relax_time_sat))) : Math.acos(1 - Math.exp(time_tau_sat / t1_relax_time_sat));
            double flip_90_sat_degree = Math.toDegrees((is_tof_enabled ? 1.5 : 1) * flip_90_sat);
            flip_angle_satband = pulseTXSatBandTOF.isSlr() ? 90 : flip_90_sat_degree;  //ha slr,akkor legyen 90,különben szar a szeletprofil!
        }
        //
        // Calculation RF pulse parameters  1/3 : Shape
        int nb_shape_points = 128;
        pulseTX.setShape(((String) getParam(TX_SHAPE).getValue()), nb_shape_points, "Hamming");
        pulseTXFatSat.setShape(((String) getParam(FATSAT_TX_SHAPE).getValue()), nb_shape_points, "Hamming");
        pulseTXSatBandTOF.setShape(((String) getParam(is_satband_enabled ? SATBAND_TX_SHAPE : TOF2D_SB_TX_SHAPE).getValue()), nb_shape_points, "Hamming");


        // -----------------------------------------------
        // Calculation RF pulse parameters  2/3 : RF pulse & attenuation
        // -----------------------------------------------
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

            if (!pulseTXFatSat.checkPower(is_fatsat_enabled ? 90.0 : 0.0, observeFrequency + tx_frequency_offset_90_fs, nucleus)) {
                tx_length_90_fs = pulseTXFatSat.getPulseDuration();
                System.out.println(" tx_length_90_fs: " + tx_length_90_fs);
                setSequenceTableSingleValue(Time_tx_fatsat, tx_length_90_fs);
                setParamValue(FATSAT_TX_LENGTH, tx_length_90_fs);

//                getUnreachParamExceptionManager().addParam(TX_LENGTH.name(), txLength90, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
            }

            if (!pulseTXSatBandTOF.checkPower(flip_angle_satband, observeFrequency + tx_frequency_offset_90_fs, nucleus)) {
//                double tx_length_sb = pulseTXSatBand.getPulseDuration();
//                getUnreachParamExceptionManager().addParam(TX_LENGTH.name(), txLength90, pulseTXFatSat.getPulseDuration(), ((NumberParam) getParam(TX_LENGTH)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                setSequenceTableSingleValue(Time_tx_sb, pulseTXSatBandTOF.getPulseDuration());
            }
            RFPulse pulseMaxPower = pulseTX.getPower() > pulseTXFatSat.getPower() ? pulseTX : pulseTXFatSat;
            pulseMaxPower = pulseMaxPower.getPower() > pulseTXSatBandTOF.getPower() ? pulseMaxPower : pulseTXSatBandTOF;

            pulseMaxPower.prepAtt(80, (List<Integer>) getParam(TX_ROUTE).getValue());

            pulseTX.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());
            pulseTXFatSat.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());
            pulseTXSatBandTOF.prepTxAmp((List<Integer>) getParam(TX_ROUTE).getValue());

            this.setParamValue(TX_ATT, pulseTX.getAtt());            // display PULSE_ATT
            this.setParamValue(TX_AMP_90, pulseTX.getAmp90());     // display 90° amplitude
            this.setParamValue(TX_AMP_180, pulseTX.getAmp180());   // display 180° amplitude
            this.setParamValue(FATSAT_TX_AMP_90, pulseTXFatSat.getAmp90());

        } else {
            pulseTX.setAtt(((NumberParam) getParam(TX_ATT)));
            pulseTX.setAmp(((NumberParam) getParam(TX_AMP_90)).getValue().doubleValue() * flip_angle / 90);

            pulseTX.setAmp(((NumberParam) getParam(FATSAT_TX_AMP_90)).getValue().doubleValue());
            pulseTX.setAmp(((NumberParam) getParam(FATSAT_TX_AMP_90)).getValue().doubleValue());
        }

        this.setParamValue(FATSAT_FLIP_ANGLE, is_fatsat_enabled ? 90 : 0);
        // -----------------------------------------------
        // Calculation RF pulse parameters  3/3: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor(TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / txLength90;

        double tx_bandwidth_factor_sb = getTx_bandwidth_factor(SATBAND_TX_SHAPE, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_sb = tx_bandwidth_factor_sb / tx_length_sb;

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
        gradReadout.applyReadoutEchoPlanarAmplitude(is_flyback ? 1 : echoTrainLength, Order.LoopB);

        setSequenceParamValue(Spectral_width, spectralWidth);

        // -------------------------------------------------------------------------------------------------
        // calculate READ_PREP  & SLICE_REF/PHASE_3D  &  PHASE_2D
        // -------------------------------------------------------------------------------------------------
        double grad_phase_application_time = ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);
        double readGradientRatio = ((NumberParam) getParam(PREPHASING_READ_GRADIENT_RATIO)).getValue().doubleValue();

        double flowcomp_dur = ((NumberParam) getParam(FLOWCOMP_DURATION)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_grad_ramp_flowcomp, is_flowcomp ? grad_rise_time : minInstructionDelay);
        setSequenceTableSingleValue(Time_grad_top_flowcomp, is_flowcomp ? flowcomp_dur : minInstructionDelay);


        boolean is_k_s_centred = ((BooleanParam) getParam(KS_CENTERED)).getValue();  // symetrique around 0 or go through k0


        // pre-calculate READ_prephasing max area
        Gradient gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradReadPrepFlowComp = Gradient.createGradient(getSequence(), Grad_amp_read_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp);
        if (isEnableRead) {
            if (is_flowcomp) {
                gradReadPrep.refocalizeGradient(gradReadout, readGradientRatio);
            } else {
                gradReadPrep.refocalizeGradientWithFlowComp(gradReadout, readGradientRatio, gradReadPrepFlowComp);
            }
        }

        // pre-calculate SLICE_refocusing  &  PHASE_3D
        double grad_ratio_slice_refoc = isEnableSlice ? ((NumberParam) getParam(SLICE_REFOCUSING_GRADIENT_RATIO)).getValue().doubleValue() : 0.0;   // get slice refocussing ratio
        Gradient gradSliceRefPhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradSliceRefPhase3DFlowComp = Gradient.createGradient(getSequence(), Grad_amp_phase_3D_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp);
        if (isEnableSlice) {
            if (is_flowcomp) {
                gradSliceRefPhase3D.refocalizeGradient(gradSlice, grad_ratio_slice_refoc);
            } else {
                gradSliceRefPhase3D.refocalizeGradientWithFlowComp(gradSlice, grad_ratio_slice_refoc, gradSliceRefPhase3DFlowComp);
            }
        }

        if (!isMultiplanar && isEnablePhase3D) {
            if (is_flowcomp) {
                gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension3D : acquisitionMatrixDimension3D, acquisitionMatrixDimension3D, slice_thickness_excitation, is_k_s_centred);
            } else {
                gradSliceRefPhase3D.preparePhaseEncodingForCheckWithFlowComp(is_keyhole_allowed ? userMatrixDimension3D : acquisitionMatrixDimension3D, acquisitionMatrixDimension3D, slice_thickness_excitation, is_k_s_centred, gradSliceRefPhase3DFlowComp);
            }
            gradSliceRefPhase3D.reoderPhaseEncoding3D(plugin, acquisitionMatrixDimension3D);
        }

        // pre-calculate PHASE_2D
        Gradient gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep, Time_grad_phase_top, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp);
        Gradient gradPhase2DFlowComp = Gradient.createGradient(getSequence(), Grad_amp_phase_2D_prep_flowcomp, Time_grad_top_flowcomp, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_flowcomp);
        if (isEnablePhase) {
            if (is_flowcomp) {
                gradPhase2D.preparePhaseEncodingForCheck(is_keyhole_allowed ? userMatrixDimension2D : acquisitionMatrixDimension2D, acquisitionMatrixDimension2D, fovPhase, is_k_s_centred);
            } else {
                gradPhase2D.preparePhaseEncodingForCheckWithFlowComp(is_keyhole_allowed ? userMatrixDimension2D : acquisitionMatrixDimension2D, acquisitionMatrixDimension2D, fovPhase, is_k_s_centred, gradPhase2DFlowComp);
            }
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

        if (is_flowcomp) {
            gradSliceRefPhase3DFlowComp.applyAmplitude(Order.Three);
            gradPhase2DFlowComp.applyAmplitude(Order.Two);
            gradReadPrepFlowComp.applyAmplitude();
        }


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

        double effectiveEchoSpacing = ((NumberParam) getParam(INTERLEAVED_EFF_ECHO_SPACING)).getValue().doubleValue();
        Table time_TE_delay1 = setSequenceTableValues(Time_TE_delay1, is_interleaved_echo_train ? Order.Four : Order.FourLoop);
        if (is_interleaved_echo_train) {
            if (echoTrainLength != 1) {
                echo_spacing = effectiveEchoSpacing * numberOfnumInterleavedEchoTrain;
                setParamValue(ECHO_SPACING, echo_spacing);
            }

            for (int ind4D = 0; ind4D < numberOfnumInterleavedEchoTrain; ind4D++) {
                time_TE_delay1.add(delay1 + ind4D * effectiveEchoSpacing);
            }

        } else {
            time_TE_delay1.add(delay1);
        }

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
        double delay2;
        double delay2_min = Math.max(min_FIR_4pts_delay - (3 * grad_rise_time + time_flyback), minInstructionDelay);
        delay2_min = Math.max(delay2_min, min_FIR_delay - (4 * grad_rise_time + time_flyback + getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopStartEcho) + getTimeBetweenEvents(Events.LoopEndEcho, Events.LoopEndEcho)));
        if (echoTrainLength > 1) {
            double time2 = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho); // Actual EchoLoop time
            time2 = removeTimeForEvents(time2, Events.Delay2); // Actual EchoLoop time without Delay2
            double echo_spacing_min = time2 + delay2_min;
            if (echo_spacing < echo_spacing_min) {
                if (is_interleaved_echo_train) {
                    double effectiveEchoSpacingMin = echo_spacing_min / numberOfnumInterleavedEchoTrain;
                    getUnreachParamExceptionManager().addParam("INTERLEAVED_EFF_ECHO_SPACING", effectiveEchoSpacing, effectiveEchoSpacingMin, ((NumberParam) getParamFromName("INTERLEAVED_EFF_ECHO_SPACING")).getMaxValue(), "Effective echo spacing too short for interleaved mode.");
                    effectiveEchoSpacing = effectiveEchoSpacingMin;
//                    setParamValue("INTERLEAVED_EFF_ECHO_SPACING", effectiveEchoSpacing);
                } else {
                    echo_spacing_min = ceilToSubDecimal(echo_spacing_min, 5);
                    getUnreachParamExceptionManager().addParam(ECHO_SPACING.name(), echo_spacing, echo_spacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo spacing too short for the User Mx1D and SW");
                    echo_spacing = echo_spacing_min;
                }
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
        //  Sat-Band timing
        //--------------------------------------------------------------------------------------
        double sat_flow_time_corr = minInstructionDelay;
        if (is_tof_enabled) {
            double satband_distance_from_slice = ((NumberParam) getParam(TOF2D_SB_DISTANCE_FROM_SLICE)).getValue().doubleValue();
            double sat_flow_dist = satband_distance_from_slice + slice_thickness_excitation;
            double sat_flow_velocity = ((NumberParam) getParam(TOF2D_FLOW_VELOCITY)).getValue().doubleValue();
            double sat_flow_time = sat_flow_dist / sat_flow_velocity;
            double time = getTimeBetweenEvents(Events.LoopSatBandEnd - 1, Events.P90 - 3) + getTimeBetweenEvents(Events.P90 - 2, Events.Acq);//(real index - 1) in the argument instead of real index
//                System.out.println("time" + time);
//                System.out.println("sat_flow_time" + sat_flow_time);
            sat_flow_time_corr = sat_flow_time - time;
            sat_flow_time_corr = (sat_flow_time_corr < 0) ? 0.0001 : sat_flow_time_corr;
//                System.out.println("sat_flow_time_corr" + sat_flow_time_corr);
        }
        setSequenceTableSingleValue(Time_flow, sat_flow_time_corr);


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
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadoutFlyback, readGradientRatio);
                else
                    gradReadSpoiler.refocalizeReadoutGradient(gradReadout, 1 - (readGradientRatio));
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
        gradReadSpoiler.applyAmplitude();


        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        int nb_of_interleaved_slice = ((NumberParam) getSequence().getParam(Nb_interveaved_slice)).getValue().intValue();
        int nb_planar_excitation = (isMultiplanar ? acquisitionMatrixDimension3D : 1);
        int slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nb_of_interleaved_slice + 1) : 1;
        double delay_before_multi_planar_loop = getTimeBetweenEvents(Events.Start, Events.TriggerDelay - 1) + getTimeBetweenEvents(Events.TriggerDelay + 1, Events.LoopMultiPlanarStart - 1) + time_external_trigger_delay_max;
        double delay_sat_band = getTimeBetweenEvents(Events.LoopSatBandStart, Events.LoopSatBandStart) * nb_satband;
        double delay_before_echo_loop = getTimeBetweenEvents(Events.LoopMultiPlanarStart, Events.LoopSatBandStart - 1) + delay_sat_band + getTimeBetweenEvents(Events.LoopSatBandEnd + 1, Events.LoopStartEcho - 1);
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
            time_tr_delay.add(tr_delay);
        }
        setSequenceTableSingleValue(Time_last_delay, last_delay);
        setSequenceTableSingleValue(Time_flush_delay, min_flush_delay);

        //----------------------------------------------------------------------
        // DYNAMIC SEQUENCE
        // Calculate frame acquisition time
        // Calculate delay between 4D acquisition
        //----------------------------------------------------------------------


        double frame_acquisition_time = nb_scan_1d * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal(frame_acquisition_time + minInstructionDelay + min_flush_delay, 1);
        double time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (isDynamic && (acquisitionMatrixDimension4D > 1)) {
            //Dynamic Sequence
            time_between_frames = ((NumberParam) getParam(DYN_TIME_BTW_FRAMES)).getValue().doubleValue();
            if (isDynamicMinTime) {
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

        //--------------------------------------------------------------------------------------
        //  SAT-BAND & TOF gradient
        //--------------------------------------------------------------------------------------

        double satband_tof_thickness = ((NumberParam) getParam(is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS)).getValue().doubleValue();

        double satband_distance_from_fov = ((NumberParam) getParam(SATBAND_DISTANCE_FROM_FOV)).getValue().doubleValue();

        Gradient gradSatBandSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);
        Gradient gradSatBandPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);
        Gradient gradSatBandRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);

        Gradient gradSatBandSpoilerSlice = Gradient.createGradient(getSequence(), Grad_amp_sb_slice_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);
        Gradient gradSatBandSpoilerPhase = Gradient.createGradient(getSequence(), Grad_amp_sb_phase_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);
        Gradient gradSatBandSpoilerRead = Gradient.createGradient(getSequence(), Grad_amp_sb_read_spoiler, Time_grad_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);

        if (is_satband_enabled || is_tof_enabled) {
            Gradient gradSB = Gradient.createGradient(getSequence(), Grad_amp_sb_read, Time_tx_sb, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_sb);
            if (!gradSB.prepareSliceSelection(tx_bandwidth_sb, satband_tof_thickness)) {
                double satband_thickness_mod = gradSB.getSliceThickness();
                getUnreachParamExceptionManager().addParam((is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS).name(), satband_tof_thickness, satband_thickness_mod, ((NumberParam) getParam(is_satband_enabled ? SATBAND_THICKNESS : TOF2D_SB_THICKNESS)).getMaxValue(), "Pulse length too short to reach this satband slice thickness");
                satband_tof_thickness = satband_thickness_mod;
            }
            double grad_amp_satband = gradSB.getAmplitude();
            double grad_amp_satband_mTpm = gradSB.getAmplitude_mTpm();

            double grad_amp_sat_spoiler = ((NumberParam) getParam(is_satband_enabled ? SATBAND_GRAD_AMP_SPOILER : TOF2D_SB_GRAMP_SP)).getValue().doubleValue();

            double[] gradAmpSBSliceTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] gradAmpSBPhaseTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] gradAmpSBReadTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] gradAmpSBSliceSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] gradAmpSBPhaseSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] gradAmpSBReadSpoilerTable = new double[is_satband_enabled ? nb_satband : 1];
            double[] offsetFreqSBTable = new double[is_satband_enabled ? nb_satband : nb_planar_excitation];
            if (is_satband_enabled) {
                int[] position_sli_ph_rea = satBandPrep(SATBAND_ORIENTATION.name(), ORIENTATION.name(), IMAGE_ORIENTATION_SUBJECT.name());
                int n = 0;
                if (position_sli_ph_rea[0] == 1) {
                    gradAmpSBSliceTable[n] = grad_amp_satband;
                    gradAmpSBPhaseTable[n] = 0;
                    gradAmpSBReadTable[n] = 0;
                    gradAmpSBSliceSpoilerTable[n] = 0;
                    gradAmpSBPhaseSpoilerTable[n] = grad_amp_sat_spoiler;
                    gradAmpSBReadSpoilerTable[n] = grad_amp_sat_spoiler;
                    double off_center_pos = off_center_distance_3D + fov3d / 2.0 + satband_distance_from_fov + satband_tof_thickness / 2.0;
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
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
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
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
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
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
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
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
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_pos);
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
                    offsetFreqSBTable[n] = pulseTXSatBandTOF.calculateOffsetFreq(grad_amp_satband_mTpm, off_center_neg);
                    n += 1;
                }
            } else if (is_tof_enabled) {
                double satband_distance_from_slice = ((NumberParam) getParam(TOF2D_SB_DISTANCE_FROM_SLICE)).getValue().doubleValue();

                double off_center_slice_pos = satband_distance_from_slice + satband_tof_thickness / 2.0; // sat band cranial from voxel
                double off_center_slice_neg = -off_center_slice_pos;  // caudal
                double off_center_slice = 0;
                if ("BELOW THE SLICE".equalsIgnoreCase((String) getParam(TOF2D_SB_POSITION).getValue())) {
                    off_center_slice = off_center_slice_neg;
                } else if ("ABOVE THE SLICE".equalsIgnoreCase((String) getParam(TOF2D_SB_POSITION).getValue())) {
                    off_center_slice = off_center_slice_pos;
                }
                double frequency_offset_sat_slice = -grad_amp_satband_mTpm * off_center_slice * (GradientMath.GAMMA / nucleus.getRatio());

                for (int k = 0; k < nb_planar_excitation; k++) {
                    offsetFreqSBTable[k] = (pulseTX.getFrequencyOffset(k) * grad_amp_satband_mTpm / gradSlice.getAmplitude_mTpm()) + frequency_offset_sat_slice;
                    System.out.println("frequency_offset_tof2d[k]  " + offsetFreqSBTable[k]);
                }
                gradAmpSBSliceTable[0] = grad_amp_satband;
                gradAmpSBPhaseTable[0] = 0;
                gradAmpSBReadTable[0] = 0;
                gradAmpSBSliceSpoilerTable[0] = 0;
                gradAmpSBPhaseSpoilerTable[0] = grad_amp_sat_spoiler;
                gradAmpSBReadSpoilerTable[0] = grad_amp_sat_spoiler;


            }
            gradSatBandSlice.setAmplitude(gradAmpSBSliceTable);
            gradSatBandPhase.setAmplitude(gradAmpSBPhaseTable);
            gradSatBandRead.setAmplitude(gradAmpSBReadTable);
            gradSatBandSpoilerSlice.setAmplitude(gradAmpSBSliceSpoilerTable);
            gradSatBandSpoilerPhase.setAmplitude(gradAmpSBPhaseSpoilerTable);
            gradSatBandSpoilerRead.setAmplitude(gradAmpSBReadSpoilerTable);
            pulseTXSatBandTOF.addFrequencyOffset(offsetFreqSBTable);

        }
        gradSatBandSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        gradSatBandPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        gradSatBandRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        gradSatBandSpoilerSlice.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        gradSatBandSpoilerPhase.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);
        gradSatBandSpoilerRead.applyAmplitude(is_satband_enabled ? Order.LoopB : Order.FourLoop);

        pulseTXSatBandTOF.setFrequencyOffset(is_satband_enabled ? Order.LoopB : is_tof_enabled ? Order.Three : Order.FourLoop);

        RFPulse pulseTXSatBandPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_prep);
        pulseTXSatBandPrep.setCompensationFrequencyOffset(pulseTXSatBandTOF, 0.5);
        RFPulse pulseTXSatBandComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp_sb, Freq_offset_tx_sb_comp);
        pulseTXSatBandComp.setCompensationFrequencyOffset(pulseTXSatBandTOF, 0.5);


        //--------------------------------------------------------------------------------------
        //  Fat-Sat gradient
        //--------------------------------------------------------------------------------------

        Gradient gradFatsatRead = Gradient.createGradient(getSequence(), Grad_amp_fatsat_read, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);
        Gradient gradFatsatPhase = Gradient.createGradient(getSequence(), Grad_amp_fatsat_phase, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);
        Gradient gradFatsatSlice = Gradient.createGradient(getSequence(), Grad_amp_fatsat_slice, Time_grad_fatsat, Grad_shape_rise_up, Grad_shape_rise_down, Time_grad_ramp_fatsat);

        if (is_fatsat_enabled) {
            double pixel_dimension_ph = ((NumberParam) getParam(RESOLUTION_PHASE)).getValue().doubleValue();
            double pixel_dimension_sl = ((NumberParam) getParam(RESOLUTION_SLICE)).getValue().doubleValue();
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

        // ------------------------------------------------------------------
        //calculate TX FREQUENCY FATSAT and compensation
        // ------------------------------------------------------------------

        pulseTXFatSat.setFrequencyOffset(is_fatsat_enabled ? tx_frequency_offset_90_fs : 0.0);
        pulseTXFatSat.setFrequencyOffset(tx_frequency_offset_90_fs);


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
           /* if (tr > fatSat_repetition_Time) {  //   this case is not necessary as merged with higher order will give a better roundning
                secondDim = 1;
                smartTTL_FatSat_table.setOrder(Order.Loop);
            } else*/
            if (number_of_averages > 1) {
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
//            System.out.println(slice_time);
//            System.out.println(tr * secondDim);
//            System.out.println("  / "+ fatSat_repetition_Time);
//            System.out.println(slice_time);
//            System.out.println(nb_ttl);
//            System.out.println(ttl_periode);
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

    private int[] satBandPrep(String satbandOrientation, String orientation, String imageOrientationSubject) {

        int[] position_sli_ph_rea = new int[6];

        boolean cranial = false;
        boolean caudal = false;
        boolean anterior = false;
        boolean posterior = false;
        boolean right = false;
        boolean left = false;


        if ("CRANIAL".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            cranial = true;
        } else if ("CAUDAL".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            caudal = true;
        } else if ("CRANIAL AND CAUDAL".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            cranial = true;
            caudal = true;
        } else if ("ANTERIOR".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            anterior = true;
        } else if ("POSTERIOR".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            posterior = true;
        } else if ("ANTERIOR AND POSTERIOR".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            anterior = true;
            posterior = true;
        } else if ("RIGHT".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            right = true;
        } else if ("LEFT".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            left = true;
        } else if ("RIGHT AND LEFT".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            right = true;
            left = true;
        } else if ("RIGHT AND LEFT".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            right = true;
            left = true;
        } else if ("ALL".equalsIgnoreCase((String) getParamFromName(satbandOrientation).getValue())) {
            cranial = true;
            caudal = true;
            anterior = true;
            posterior = true;
            right = true;
            left = true;
        }


        position_sli_ph_rea[0] = 0;
        position_sli_ph_rea[1] = 0;
        position_sli_ph_rea[2] = 0;
        position_sli_ph_rea[3] = 0;
        position_sli_ph_rea[4] = 0;
        position_sli_ph_rea[5] = 0;
        if ("AXIAL".equalsIgnoreCase((String) getParamFromName(orientation).getValue())) {
            position_sli_ph_rea[0] = cranial ? 1 : 0;
            position_sli_ph_rea[1] = caudal ? 1 : 0;
            position_sli_ph_rea[2] = anterior ? 1 : 0;
            position_sli_ph_rea[3] = posterior ? 1 : 0;
            position_sli_ph_rea[4] = right ? 1 : 0;
            position_sli_ph_rea[5] = left ? 1 : 0;
        } else if ("SAGITTAL".equalsIgnoreCase((String) getParamFromName(orientation).getValue())) {
            position_sli_ph_rea[0] = left ? 1 : 0;
            position_sli_ph_rea[1] = right ? 1 : 0;
            position_sli_ph_rea[2] = anterior ? 1 : 0;
            position_sli_ph_rea[3] = posterior ? 1 : 0;
            position_sli_ph_rea[4] = cranial ? 1 : 0;
            position_sli_ph_rea[5] = caudal ? 1 : 0;
        } else if ("CORONAL".equalsIgnoreCase((String) getParamFromName(orientation).getValue())) {
            position_sli_ph_rea[0] = anterior ? 1 : 0;
            position_sli_ph_rea[1] = posterior ? 1 : 0;
            position_sli_ph_rea[2] = right ? 1 : 0;
            position_sli_ph_rea[3] = left ? 1 : 0;
            position_sli_ph_rea[4] = cranial ? 1 : 0;
            position_sli_ph_rea[5] = caudal ? 1 : 0;
        } else if ("OBLIQUE".equalsIgnoreCase((String) getParamFromName(orientation).getValue())) {
            ListNumberParam image_orientation = (ListNumberParam) getParamFromName(imageOrientationSubject);
            double[][] dir_ind = new double[3][3];
            dir_ind[0][0] = image_orientation.getValue().get(0).doubleValue();
            dir_ind[0][1] = image_orientation.getValue().get(1).doubleValue();
            dir_ind[0][2] = image_orientation.getValue().get(2).doubleValue();
            dir_ind[1][0] = image_orientation.getValue().get(3).doubleValue();
            dir_ind[1][1] = image_orientation.getValue().get(4).doubleValue();
            dir_ind[1][2] = image_orientation.getValue().get(5).doubleValue();
            dir_ind[2][0] = dir_ind[0][1] * dir_ind[1][2] - dir_ind[0][2] * dir_ind[1][1];
            dir_ind[2][1] = dir_ind[0][2] * dir_ind[1][0] - dir_ind[0][0] * dir_ind[1][2];
            dir_ind[2][2] = dir_ind[0][0] * dir_ind[1][1] - dir_ind[0][1] * dir_ind[1][0];
            int i, j;
            int max_index = 0;
            double norm_vector_re = Math.sqrt(Math.pow(dir_ind[0][0], 2) + Math.pow(dir_ind[0][1], 2) + Math.pow(dir_ind[0][2], 2));
            double norm_vector_ph = Math.sqrt(Math.pow(dir_ind[1][0], 2) + Math.pow(dir_ind[1][1], 2) + Math.pow(dir_ind[1][2], 2));
            double norm_vector_sl = Math.sqrt(Math.pow(dir_ind[2][0], 2) + Math.pow(dir_ind[2][1], 2) + Math.pow(dir_ind[2][2], 2));
            //normalizing vectors
            dir_ind[0][0] = dir_ind[0][0] / norm_vector_re;
            dir_ind[0][1] = dir_ind[0][1] / norm_vector_re;
            dir_ind[0][2] = dir_ind[0][2] / norm_vector_re;
            dir_ind[1][0] = dir_ind[1][0] / norm_vector_ph;
            dir_ind[1][1] = dir_ind[1][1] / norm_vector_ph;
            dir_ind[1][2] = dir_ind[1][2] / norm_vector_ph;
            dir_ind[2][0] = dir_ind[2][0] / norm_vector_sl;
            dir_ind[2][1] = dir_ind[2][1] / norm_vector_sl;
            dir_ind[2][2] = dir_ind[2][2] / norm_vector_sl;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    System.out.println("dir_ind[" + i + "][" + j + "]" + dir_ind[i][j]);
                }
            }

            // System.out.println(" direction index and dir ind:  "+direction_index[2]+" "+dir_ind[0][2]);
            int[] max_vector = new int[3];

            // read, phase and slice vector which component has the largest value
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (Math.abs(dir_ind[i][j]) >= Math.abs(dir_ind[i][max_index])) {
                        max_index = j;
                    }
                }
                max_vector[i] = max_index; // storing each vector's maximum value index
                //System.out.println("max_vector["+i+"]"+max_vector[i]);
            }

            boolean[][] anatomy_to_local_mx = new boolean[6][6];

            for (i = 0; i < 6; i++) {
                for (j = 0; j < 6; j++) {
                    anatomy_to_local_mx[i][j] = false;
                }
            }

            for (i = 0; i < 3; i++) {

                if (dir_ind[i][max_vector[i]] < 0) {
                    anatomy_to_local_mx[i][max_vector[i] + 3] = true;
                    anatomy_to_local_mx[i + 3][max_vector[i]] = true;
                } else {
                    anatomy_to_local_mx[i][max_vector[i]] = true;
                    anatomy_to_local_mx[i + 3][max_vector[i] + 3] = true;
                }
            }
            boolean[] local_vector = new boolean[6];

            local_vector[0] = false;
            local_vector[1] = false;
            local_vector[2] = false;
            local_vector[3] = false;
            local_vector[4] = false;
            local_vector[5] = false;

            boolean[] anatomy_vector = new boolean[6];

            anatomy_vector[0] = right;
            anatomy_vector[1] = posterior;
            anatomy_vector[2] = caudal;
            anatomy_vector[3] = left;
            anatomy_vector[4] = anterior;
            anatomy_vector[5] = cranial;

            boolean sum;
            for (i = 0; i < 6; i++) {
                sum = false;
                for (j = 0; j < 6; j++) {
                    sum = sum || (anatomy_to_local_mx[i][j] & anatomy_vector[j]);
                    //	System.out.println("sum= "+sum+" + "+anatomy_to_local_mx[i][j]+"*"+anatomy_vector[j]);
                }
                local_vector[i] = sum;
                // System.out.println("local vector "+local_vector[i]);

            }
            position_sli_ph_rea[4] = local_vector[0] ? 1 : 0;
            position_sli_ph_rea[2] = local_vector[1] ? 1 : 0;
            position_sli_ph_rea[0] = local_vector[2] ? 1 : 0;
            position_sli_ph_rea[5] = local_vector[3] ? 1 : 0;
            position_sli_ph_rea[3] = local_vector[4] ? 1 : 0;
            position_sli_ph_rea[1] = local_vector[5] ? 1 : 0;

            // System.out.println("read+ "+position_sli_ph_rea[4]+" phase+ "+position_sli_ph_rea[2]+" slice+ "+position_sli_ph_rea[0]);
            // System.out.println("read- "+position_sli_ph_rea[5]+" phase- "+position_sli_ph_rea[3]+" slice- "+position_sli_ph_rea[1]);
        }
        boolean is_switch = ((BooleanParam) getParam(SWITCH_READ_PHASE)).getValue();
        boolean phase_pos_temp = position_sli_ph_rea[2] == 1;
        boolean phase_neg_temp = position_sli_ph_rea[3] == 1;
        boolean read_pos_temp = position_sli_ph_rea[4] == 1;
        boolean read_neg_temp = position_sli_ph_rea[5] == 1;
        if (is_switch) {
            position_sli_ph_rea[2] = read_pos_temp ? 1 : 0;
            position_sli_ph_rea[3] = read_neg_temp ? 1 : 0;
            position_sli_ph_rea[4] = phase_pos_temp ? 1 : 0;
            position_sli_ph_rea[5] = phase_neg_temp ? 1 : 0;
        }
        return position_sli_ph_rea;
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


    private double getTx_bandwidth_factor(GradientEchoParams tx_shape, GradientEchoParams tx_bandwith_factor_param, GradientEchoParams tx_bandwith_factor_param3d) {
        double tx_bandwidth_factor;
        ListNumberParam tx_bandwith_factor_table = (ListNumberParam) getParam(tx_bandwith_factor_param);
        ListNumberParam tx_bandwith_factor_3D_table = (ListNumberParam) getParam(tx_bandwith_factor_param3d);

        if (isMultiplanar) {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(3).doubleValue();
            } else if ("RAMP".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(3).doubleValue();
            } else if ("SLR_8_5152".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(4).doubleValue();
            } else if ("SLR_4_2576".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(5).doubleValue();
            } else {
                tx_bandwidth_factor = tx_bandwith_factor_table.getValue().get(0).doubleValue();
            }
        } else {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(3).doubleValue();
            } else if ("RAMP".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(3).doubleValue();
            } else if ("SLR_8_5152".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(4).doubleValue();
            } else if ("SLR_4_2576".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(5).doubleValue();
            } else {
                tx_bandwidth_factor = tx_bandwith_factor_3D_table.getValue().get(0).doubleValue();
            }
        }
        return tx_bandwidth_factor;
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


    private double getOff_center_distance_X_Y_Z(int dim, double off_center_distance_1D, double off_center_distance_2D, double off_center_distance_3D) {
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

        //Offset according to READ PHASE and SLICE
        double off_center_distance;
        switch (dim) {
            case 1:
                off_center_distance = off_center_distance_1D * direction_index[0] / norm_vector_read + off_center_distance_2D * direction_index[3] / norm_vector_phase + off_center_distance_3D * direction_index[6] / norm_vector_slice;
                break;
            case 2:
                off_center_distance = off_center_distance_1D * direction_index[1] / norm_vector_read + off_center_distance_2D * direction_index[4] / norm_vector_phase + off_center_distance_3D * direction_index[7] / norm_vector_slice;
                break;
            case 3:
                off_center_distance = off_center_distance_1D * direction_index[2] / norm_vector_read + off_center_distance_2D * direction_index[5] / norm_vector_phase + off_center_distance_3D * direction_index[8] / norm_vector_slice;
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