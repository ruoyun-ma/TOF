package rs2d.sequence.gradientecho;

// ---------------------------------------------------------------------
//  
//                 GRADIENT_ECHO_dev PSD 
//  
// ---------------------------------------------------------------------
//
// 30/09/2017    V7.1
//			Replacement with Gradient and RF Pulse Class
// 			OffCenterFOV
// 			bug       delay2_min = ...se_time "+" getTim ... Echo) "+" getT...;
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
//        getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH.name(), spectral_width,((NumberParam) getParam( SPECTRAL_WIDTH)).getMinValue(),spectral_width_max, "SPECTRAL_WIDTH too high for the readout gradient");
//	SAR deleted
// 24/03/2017   V5.5
//   setParamValue(MODALITY, "MRI");
//   setSequenceParamValue("Phase_reset","USER_TMP_PARAM_BOOL_1");

import java.util.*;

import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.hardware.controller.HardwareHandler;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.sequence.element.TimeElement;
import rs2d.spinlab.tools.role.*;
import rs2d.spinlab.sequence.table.*;
import rs2d.spinlab.sequenceGenerator.SequenceGeneratorAbstract;
import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.GradientAxe;
import rs2d.spinlab.tools.utility.Nucleus;

import static rs2d.sequence.gradientecho.GradientEchoParams.*;

import static rs2d.sequence.gradientecho.GradientEchoSequenceParams.*;


// **************************************************************************************************
// *************************************** SEQUENCE GENERATOR ***************************************
// **************************************************************************************************
//
public class GradientEcho extends SequenceGeneratorAbstract {
//    private static final String TX_ATT = "Tx_att";

    private double proton_frequency;
    private double observe_frequency;
    private double min_time_per_acq_point;
    private double gMax;
    private TransformPlugin plugin;
    private Nucleus nucleus;

    private boolean is_multiplanar;
    private boolean is_trigger;
    private boolean is_dynamic;
    private int acquisition_matrix_dimension_1D;
    private int acquisition_matrix_dimension_2D;
    private int acquisition_matrix_dimension_3D;
    private int acquisition_matrix_dimension_4D;
    private int user_matrix_dimension_2D;
    private int user_matrix_dimension_3D;
    private int echo_train_length;
    private double spectral_width;
    private double slice_thickness;
    private double pixelDimension;
    private double tx_length_90;
    private int numberOfDynamicAcquisition;
    private double fov;// get slice refocussing ratio
    private boolean isEnablePhase;
    private boolean isEnablePhase3D;
    private double fovPhase;
    private int userMatrixDimension1D;

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

        List<String> tx_shape = new ArrayList<String>();
        tx_shape.add("HARD");
        tx_shape.add("GAUSSIAN");
        tx_shape.add("SINC3");
        tx_shape.add("SINC5");

        //List<String> tx_shape = Arrays.asList("HARD", "GAUSSIAN", "SIN3", "xSINC5");
        ((TextParam) getParam(TX_SHAPE_90)).setSuggestedValues(tx_shape);
        ((TextParam) getParam(TX_SHAPE_90)).setRestrictedToSuggested(true);

        //TRANSFORM PLUGIN
        List<String> list = new ArrayList<String>();
        list.add("Sequential4D");
        list.add("Sequential4DBackAndForth");
        list.add("EPISequential4D");
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setSuggestedValues(list);
        ((TextParam) this.getParamFromName(MriDefaultParams.TRANSFORM_PLUGIN.name())).setRestrictedToSuggested(true);

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
        is_multiplanar = (((BooleanParam) getParam(MULTI_PLANAR_EXCITATION)).getValue());
        is_trigger = (((BooleanParam) getParam(TRIGGER_EXTERNAL)).getValue());
        is_dynamic = ((BooleanParam) getParam(DYNAMIC_SEQUENCE)).getValue();
        isEnablePhase3D = ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE_3D)).getValue();
        isEnablePhase = ((BooleanParam) getParam(GRADIENT_ENABLE_PHASE)).getValue();

//        acquisition_matrix_dimension_1D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        acquisition_matrix_dimension_2D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        acquisition_matrix_dimension_3D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        acquisition_matrix_dimension_4D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();

        fov = ((NumberParam) getParam(FIELD_OF_VIEW)).getValue().doubleValue();
        fovPhase = ((NumberParam) getParam(FIELD_OF_VIEW_PHASE)).getValue().doubleValue();
        slice_thickness = ((NumberParam) getParam(SLICE_THICKNESS)).getValue().doubleValue();
        pixelDimension = ((NumberParam) getParam(RESOLUTION_FREQUENCY)).getValue().doubleValue();

        userMatrixDimension1D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_1D)).getValue().intValue();
        user_matrix_dimension_2D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();
        user_matrix_dimension_3D = ((NumberParam) getParam(USER_MATRIX_DIMENSION_3D)).getValue().intValue();

        echo_train_length = ((NumberParam) getParam(ECHO_TRAIN_LENGTH)).getValue().intValue();
        spectral_width = ((NumberParam) getParam(SPECTRAL_WIDTH)).getValue().doubleValue();            // get user defined spectral width
        tx_length_90 = ((NumberParam) getParam(TX_LENGTH_90)).getValue().doubleValue();
        numberOfDynamicAcquisition = is_dynamic ? ((NumberParam) getParam(DYN_NUMBER_OF_ACQUISITION)).getValue().intValue() : 1;


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
        setParamValue(SEQUENCE_VERSION, "Version7.1");
        setParamValue(MODALITY, "MRI");
        boolean b_comment = false; // Show the comments
        if (b_comment) {
            System.out.println("------------ BEFORE ROUTING -------------");
        }

        // -----------------------------------------------
        // RX parameters : nucleus, RX gain & frequencies
        // -----------------------------------------------
        nucleus = Nucleus.getNucleusForName((String) getParam(NUCLEUS_1).getValue());
        proton_frequency = Instrument.instance().getDevices().getMagnet().getProtonFrequency();
        double freq_offset1 = ((NumberParam) getParam(OFFSET_FREQ_1)).getValue().doubleValue();
        observe_frequency = nucleus.getFrequency(proton_frequency) + freq_offset1;
        setParamValue(BASE_FREQ_1, nucleus.getFrequency(proton_frequency));

        min_time_per_acq_point = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getTransfertTimePerDataPt();
        gMax = GradientMath.getMaxGradientStrength();

        setSequenceParamValue(Rx_gain, RECEIVER_GAIN);
        setParamValue(RECEIVER_COUNT, Instrument.instance().getObservableRxs(nucleus).size());

        setSequenceParamValue(Intermediate_frequency, Instrument.instance().getIfFrequency());
        setParamValue(INTERMEDIATE_FREQUENCY, Instrument.instance().getIfFrequency());

        setSequenceParamValue(Tx_frequency, observe_frequency);
        setParamValue(OBSERVED_FREQUENCY, observe_frequency);

        setSequenceParamValue(TxNucleus, NUCLEUS_1);
        setParamValue(OBSERVED_NUCLEUS, getParam(NUCLEUS_1).getValue());

        // get hardware memory limit
        int offset_channel_memory = 512;
//        int phase_channel_memory = 512;
//        int amp_channel_memory = 2048;

        // -----------------------------------------------
        // 1stD managment     FSE
        // -----------------------------------------------   
        // FOV 
        boolean is_fov_doubled = ((BooleanParam) getParam(FOV_DOUBLED)).getValue();
        double fov_eff = is_fov_doubled ? (fov * 2) : fov;
        setParamValue(FOV_EFF, fov_eff);

        double sw_eff = is_fov_doubled ? (spectral_width * 2) : spectral_width;
        sw_eff = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getNearestSW(sw_eff);      // get real spectral width from Chameleon
        spectral_width = is_fov_doubled ? (sw_eff / 2) : sw_eff;

        // MATRIX
        acquisition_matrix_dimension_1D = is_fov_doubled ? 2 * userMatrixDimension1D : userMatrixDimension1D;
        // Pixel dimension calculation
        pixelDimension = fov_eff / acquisition_matrix_dimension_1D;
        setParamValue(RESOLUTION_FREQUENCY, pixelDimension); // frequency true resolution for display

        double spectral_width_per_pixel;
        boolean is_SW = (((BooleanParam) getParam(SPECTRAL_WIDTH_OPT)).getValue());
        if (is_SW) {
            sw_eff = is_fov_doubled ? (spectral_width * 2) : spectral_width;
            sw_eff = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getNearestSW(sw_eff);      // get real spectral width from Chameleon
            spectral_width = is_fov_doubled ? (sw_eff / 2) : sw_eff;
            spectral_width_per_pixel = spectral_width / acquisition_matrix_dimension_1D;
        } else {
            spectral_width_per_pixel = ((NumberParam) getParam(SPECTRAL_WIDTH_PER_PIXEL)).getValue().doubleValue();
            spectral_width = (spectral_width_per_pixel * acquisition_matrix_dimension_1D);
            sw_eff = is_fov_doubled ? (spectral_width * 2) : spectral_width;
            sw_eff = HardwareHandler.getInstance().getSequenceHandler().getCompiler().getNearestSW(sw_eff);      // get real spectral width from Chameleon
            spectral_width = is_fov_doubled ? (sw_eff / 2) : sw_eff;
            spectral_width_per_pixel = spectral_width / acquisition_matrix_dimension_1D;
        }
        setParamValue(SPECTRAL_WIDTH_PER_PIXEL, spectral_width_per_pixel);
        setParamValue(SPECTRAL_WIDTH, spectral_width);
        // -----------------------------------------------
        // 2nd D managment 
        // -----------------------------------------------
        // FOV 
        prepareFov_phase();

        // MATRIX
        setSquarePixel(((BooleanParam) getParam(SQUARE_PIXEL)).getValue());

        double partial_phase = ((NumberParam) getParam(USER_PARTIAL_PHASE)).getValue().doubleValue();
        double zero_filling_2D = (100 - partial_phase) / 100f;
        setParamValue(USER_ZERO_FILLING_2D, (100 - partial_phase));

        acquisition_matrix_dimension_2D = floorPowerOfTwo((1 - zero_filling_2D) * user_matrix_dimension_2D);
        acquisition_matrix_dimension_2D = (acquisition_matrix_dimension_2D < 4) && isEnablePhase ? 4 : acquisition_matrix_dimension_2D;
        int nb_scan_2d = acquisition_matrix_dimension_2D;

        // Pixel dimension calculation
        double pixel_dimension_phase = fovPhase / acquisition_matrix_dimension_2D;
        setParamValue(RESOLUTION_PHASE, pixel_dimension_phase); // phase true resolution for display 

        // -----------------------------------------------
        // 3D managment 1/2: matrix & scan
        // ------------------------------------------------
        // MATRIX
        int nb_scan_3d;
        double partial_slice;
        // 3D ZERO FILLING & number_of_shoot_3D
        if (is_multiplanar) {
            // 3D ZERO FILLING only available for NON MULTI-PLANAR SEQUENCES
            setParamValue(USER_PARTIAL_SLICE, 100);
            partial_slice = 100;
        } else {
            partial_slice = ((NumberParam) getParam(USER_PARTIAL_SLICE)).getValue().doubleValue();
        }
        double zero_filling_3D = (100 - partial_slice) / 100f;
        setParamValue(USER_ZERO_FILLING_3D, (100 - partial_slice));

        //Calculate the number of k-space lines acquired in the 3rd Dimension : acquisition_matrix_dimension_3D
        boolean is_rf_spoiling = ((BooleanParam) getParam(RF_SPOILING)).getValue();
        if (!is_multiplanar) {
            acquisition_matrix_dimension_3D = floorPowerOfTwo((1 - zero_filling_3D) * user_matrix_dimension_3D);
            acquisition_matrix_dimension_3D = (acquisition_matrix_dimension_3D < 4) && isEnablePhase3D ? 4 : acquisition_matrix_dimension_3D;
            if (user_matrix_dimension_3D < acquisition_matrix_dimension_3D) {
                user_matrix_dimension_3D = acquisition_matrix_dimension_3D;
                setParamValue(USER_MATRIX_DIMENSION_3D, user_matrix_dimension_3D);
            }
        } else {
            if ((user_matrix_dimension_3D * 3 + ((is_rf_spoiling) ? 1 : 0) + 3 + 1) >= offset_channel_memory) {
                user_matrix_dimension_3D = ((int) Math.floor((offset_channel_memory - 4 - ((is_rf_spoiling) ? 1 : 0)) / 3.0));
                setParamValue(USER_MATRIX_DIMENSION_3D, user_matrix_dimension_3D);
            }
            acquisition_matrix_dimension_3D = user_matrix_dimension_3D;
        }

        int nb_of_shoot_3d = is_multiplanar ? getInferiorDivisorToGetModulusZero(((NumberParam) getParam(NUMBER_OF_SHOOT_3D)).getValue().intValue(), user_matrix_dimension_3D) : 0;
        int nb_interleaved_excitation = is_multiplanar ? (int) Math.ceil((acquisition_matrix_dimension_3D / nb_of_shoot_3d)) : 1;
        nb_scan_3d = is_multiplanar ? nb_of_shoot_3d : acquisition_matrix_dimension_3D;
        setParamValue(NUMBER_OF_SHOOT_3D, nb_of_shoot_3d);
        setParamValue(NUMBER_OF_INTERLEAVED_SLICE, nb_interleaved_excitation);
        setParamValue(NUMBER_OF_INTERLEAVED_SLICE, is_multiplanar ? nb_interleaved_excitation : 0);


        // -----------------------------------------------
        // 3D managment 2/2: dimension, FOV...
        // -----------------------------------------------   
        // FOV 
        double spacing_between_slice;
        if (is_multiplanar) {
            spacing_between_slice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        } else {
            setParamValue(SPACING_BETWEEN_SLICE, 0);
            spacing_between_slice = 0;
        }
        double fov_3d = slice_thickness * user_matrix_dimension_3D + spacing_between_slice * (user_matrix_dimension_3D - 1);
        setParamValue(FIELD_OF_VIEW_3D, fov_3d);    // FOV ratio for display

        // Pixel dimension calculation
        double pixel_dimension_3D;
        if (is_multiplanar) {
            pixel_dimension_3D = slice_thickness;
        } else {
            pixel_dimension_3D = slice_thickness * user_matrix_dimension_3D / acquisition_matrix_dimension_3D; //true resolution
        }
        setParamValue(RESOLUTION_SLICE, pixel_dimension_3D); // phase true resolution for display 

        // -----------------------------------------------
        // 4D managment:  Dynamic, MultiEcho, External triggering
        // -----------------------------------------------   
        ListNumberParam trigger_time = (ListNumberParam) getParam(TRIGGER_TIME);
        int number_of_trigger_acquisition = is_trigger ? trigger_time.getValue().size() : 1;

        // Avoid multi trigger time when  Multi echo or dynamic
        if (number_of_trigger_acquisition != 1 && (echo_train_length != 1 || is_dynamic)) {
            double tmp = trigger_time.getValue().get(0).doubleValue();
            trigger_time.getValue().clear();
            trigger_time.getValue().add(tmp);
            number_of_trigger_acquisition = 1;

        }

        int nb_scan_4d = number_of_trigger_acquisition * numberOfDynamicAcquisition;
        acquisition_matrix_dimension_4D = nb_scan_4d * echo_train_length;//
        setParamValue(USER_MATRIX_DIMENSION_4D, nb_scan_4d);

        //Dynamic and multi echo are filled into the 4th Dimension 
        if (echo_train_length == 1) {
            setParamValue(ECHO_SPACING, 0);
            setParamValue(TRANSFORM_PLUGIN, "Sequential4D");
        } else {
            setParamValue(TRANSFORM_PLUGIN, "Sequential4DBackAndForth");
        }

        // -----------------------------------------------
        // set the ACQUISITION_MATRIX and Nb XD
        // -----------------------------------------------        // set the calculated acquisition matrix
        setParamValue(ACQUISITION_MATRIX_DIMENSION_1D, acquisition_matrix_dimension_1D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_2D, acquisition_matrix_dimension_2D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_3D, acquisition_matrix_dimension_3D);
        setParamValue(ACQUISITION_MATRIX_DIMENSION_4D, acquisition_matrix_dimension_4D);

        // set the calculated sequence dimensions 
        setSequenceParamValue(PS, DUMMY_SCAN); // Do the prescan
        setSequenceParamValue(Nb_point, acquisition_matrix_dimension_1D);
        setSequenceParamValue(Nb_1d, NUMBER_OF_AVERAGES);
        setSequenceParamValue(Nb_2d, nb_scan_2d);
        setSequenceParamValue(Nb_3d, nb_scan_3d);
        setSequenceParamValue(Nb_4d, nb_scan_4d);
        // set the calculated Loop dimensions 
        setSequenceParamValue(Nb_echo, echo_train_length - 1);
        setSequenceParamValue(Nb_interveaved_slice, nb_interleaved_excitation - 1);

        // -----------------------------------------------
        // SEQ_DESCRIPTION
        // -----------------------------------------------      
        String seqDescription = "GE_";
        if (is_multiplanar) {
            seqDescription = seqDescription.concat("2D");
        } else {
            seqDescription = seqDescription.concat("3D");
        }
        String orientation = (String) getParam(ORIENTATION).getValue();
        seqDescription = seqDescription.concat("_" + orientation.substring(0, 2));

        String seqMatrixDescription = "_";
        seqMatrixDescription = seqMatrixDescription.concat(String.valueOf(userMatrixDimension1D) + "x" + String.valueOf(acquisition_matrix_dimension_2D) + "x" + String.valueOf(acquisition_matrix_dimension_3D));
        if (acquisition_matrix_dimension_4D != 1) {
            seqMatrixDescription = seqMatrixDescription.concat("x" + String.valueOf(acquisition_matrix_dimension_4D));
        }
        seqDescription = seqDescription.concat(seqMatrixDescription);

        if (echo_train_length != 1) {
            seqDescription = seqDescription.concat("_ETL=" + String.valueOf(echo_train_length));
        }
        if (is_trigger && number_of_trigger_acquisition != 1) {
            seqDescription = seqDescription.concat("_TRIG=" + String.valueOf(number_of_trigger_acquisition));
        } else if (is_trigger) {
            seqDescription = seqDescription.concat("_TRIG");
        }
        if (is_dynamic) {
            seqDescription = seqDescription.concat("_DYN=" + String.valueOf(numberOfDynamicAcquisition));
        }
        setParamValue(SEQ_DESCRIPTION, seqDescription);
        // -----------------------------------------------
        // Image Orientation
        // -----------------------------------------------
        //READ PHASE and SLICE matrix 
        double off_center_distance_1D = getOff_center_distance_1D_2D_3D(1);
        double off_center_distance_2D = getOff_center_distance_1D_2D_3D(2);
        double off_center_distance_3D = getOff_center_distance_1D_2D_3D(3);

        //Offset according to ENABLE READ PHASE and SLICE
        boolean is_enable_read = ((BooleanParam) getParam(GRADIENT_ENABLE_READ)).getValue();
        off_center_distance_1D = !is_enable_read ? 0 : off_center_distance_1D;
        off_center_distance_2D = !isEnablePhase ? 0 : off_center_distance_2D;

        boolean is_enable_slice = ((BooleanParam) getParam(GRADIENT_ENABLE_SLICE)).getValue();// get slice refocussing ratio            
        if (!is_enable_slice && (is_multiplanar || (!is_multiplanar && !isEnablePhase3D))) {
            off_center_distance_3D = 0;
        }

//        // MEMORY LIMITATION 2D Shift
//        if ((off_center_distance_2D != 0) && acquisition_matrix_dimension_2D >= phase_channel_memory) {
//            off_center_distance_2D = 0;
//        }
//        if ((!is_multiplanar) && (1 + ((is_rf_spoiling) ? 1 : 0) + 3 + acquisition_matrix_dimension_3D * 2 + ((off_center_distance_1D != 0) ? 3 : 0) >= offset_channel_memory)) {
//            off_center_distance_3D = 0;
//        }
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_3D, off_center_distance_3D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_2D, off_center_distance_2D);
        setParamValue(OFF_CENTER_FIELD_OF_VIEW_1D, off_center_distance_1D);

        boolean is_read_phase_inverted = ((BooleanParam) getParam(SWITCH_READ_PHASE)).getValue();
        if (is_read_phase_inverted) {
            setSequenceParamValue(GradientAxePhase, GradientAxe.R);
            setSequenceParamValue(GradientAxeRead, GradientAxe.P);
        } else {
            setSequenceParamValue(GradientAxePhase, GradientAxe.P);
            setSequenceParamValue(GradientAxeRead, GradientAxe.R);
        }

        // -----------------------------------------------
        // spectral width & observation time
        // -----------------------------------------------
        int acq_points = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_1D)).getValue().intValue();     // get user defined number of points to acquired
        setSequenceParamValue(Spectral_width, sw_eff);    // set spectral width to sequence
        double time_rx = acq_points / sw_eff;
        setParamValue(ACQUISITION_TIME_PER_SCAN, time_rx);   // display observation time

        // -----------------------------------------------
        // enable gradient lines
        // -----------------------------------------------
        setSequenceParamValue(Grad_enable_read, GRADIENT_ENABLE_READ);              // pass gradient line status to sequence
        setSequenceParamValue(Grad_enable_phase_2D, isEnablePhase);
        setSequenceParamValue(Grad_enable_phase_3D, ((!is_multiplanar && isEnablePhase3D) || is_enable_slice));
        setSequenceParamValue(Grad_enable_slice, is_enable_slice);

        boolean is_grad_rewinding = ((BooleanParam) getParam(GRADIENT_ENABLE_REWINDING)).getValue();// get slice refocussing ratio
        boolean is_grad_spoiler = ((BooleanParam) getParam(GRADIENT_ENABLE_SPOILER)).getValue();// get slice refocussing ratio

        ListNumberParam grad_amp_spoiler_sl_ph_re = (ListNumberParam) getParam(GRAD_AMP_SPOILER_SL_PH_RE);
        double[] grad_amp_spoiler = new double[3];
        grad_amp_spoiler[0] = grad_amp_spoiler_sl_ph_re.getValue().get(0).doubleValue();
        grad_amp_spoiler[1] = grad_amp_spoiler_sl_ph_re.getValue().get(1).doubleValue();
        grad_amp_spoiler[2] = grad_amp_spoiler_sl_ph_re.getValue().get(2).doubleValue();

        setSequenceParamValue(Grad_enable_spoiler_slice, (((!is_multiplanar && is_grad_rewinding && isEnablePhase3D) || (is_grad_rewinding && is_enable_slice) || (is_grad_spoiler && (grad_amp_spoiler[0] != 0)))));
        setSequenceParamValue(Grad_enable_spoiler_phase, (isEnablePhase && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler[1] != 0))));
        setSequenceParamValue(Grad_enable_spoiler_read, (is_enable_read && (is_grad_rewinding) || (is_grad_spoiler && (grad_amp_spoiler[2] != 0))));

        // -----------------------------------------------
        // activate the external synchronization
        // -----------------------------------------------
        if (is_trigger) {
            getSequence().getPublicParam(Synchro_trigger).setValue(TimeElement.Trigger.External);
        } else {
            getSequence().getPublicParam(Synchro_trigger).setValue(TimeElement.Trigger.Timer);
        }
        getSequence().getPublicParam(Synchro_trigger).setLocked(true);

        // -----------------------------------------------
        // activate gradient rotation matrix
        // -----------------------------------------------
        appliedGradientRotation();
    }

    private int floorPowerOfTwo(double value) {
        return (int) Math.floor(Math.round(value) / 2.0) * 2;
    }

    private void setSquarePixel(boolean square) {
        if (square) {
            user_matrix_dimension_2D = (int) Math.round(userMatrixDimension1D * fovPhase / fov);
            setParamValue(USER_MATRIX_DIMENSION_2D, user_matrix_dimension_2D);
        }
    }

    private void prepareFov_phase() {
        fovPhase = (((BooleanParam) getParam(FOV_SQUARE)).getValue()) ? fov : fovPhase;
        fovPhase = fovPhase > fov ? fov : fovPhase;
        setParamValue(FIELD_OF_VIEW_PHASE, fov);

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
        // Calculation RF pulse parameters  1/3 : Shape
        // -----------------------------------------------
        setSequenceTableFirstValue(Time_tx, TX_LENGTH_90.name());
        RFPulse pulseTX = RFPulse.createRFPulse(getSequence(), Tx_att, Tx_amp, Tx_phase, Time_tx, Tx_shape, Tx_shape_phase, Tx_freq_offset);

        int nb_shape_points = 128;
        pulseTX.setShape(((String) getParam(TX_SHAPE_90).getValue()), nb_shape_points, "Hamming");

        // -----------------------------------------------
        // Calculation RF pulse parameters  2/3 : RF pulse & attenuation
        // -----------------------------------------------
        double flip_angle = ((NumberParam) getParam(FLIP_ANGLE)).getValue().doubleValue();
        boolean is_tx_amp_att_auto = ((BooleanParam) getParam(TX_AMP_ATT_AUTO)).getValue();
        if (is_tx_amp_att_auto) {
            if (!pulseTX.setAutoCalib(flip_angle, observe_frequency, (List<Integer>) getParam(TX_ROUTE).getValue(), nucleus)) {
                getUnreachParamExceptionManager().addParam(TX_LENGTH_90.name(), tx_length_90, pulseTX.getTime(), ((NumberParam) getParam(TX_LENGTH_90)).getMaxValue(), "Pulse length too short to reach RF power with this pulse shape");
                tx_length_90 = pulseTX.getTime();
            }
            this.setParamValue(PULSE_ATT, pulseTX.getAtt());            // display PULSE_ATT
            this.setParamValue(TX_AMP_90, pulseTX.getAmp90());     // display 90° amplitude
            this.setParamValue(TX_AMP_180, pulseTX.getAmp180());   // display 180° amplitude
        } else {
            pulseTX.setAtt(((NumberParam) getParam(PULSE_ATT)));
            pulseTX.setAmp(((NumberParam) getParam(TX_AMP_90)));
        }

        // -----------------------------------------------
        // Calculation RF pulse parameters  3/3: bandwidth
        // -----------------------------------------------
        double tx_bandwidth_factor_90 = getTx_bandwidth_factor_90(TX_SHAPE_90, TX_BANDWIDTH_FACTOR, TX_BANDWIDTH_FACTOR_3D);
        double tx_bandwidth_90 = tx_bandwidth_factor_90 / tx_length_90;

        // ---------------------------------------------------------------------
        // calculate SLICE gradient amplitudes for RF pulses
        // ---------------------------------------------------------------------
        boolean isEnableSlice = ((BooleanParam) getParam(GRADIENT_ENABLE_SLICE)).getValue();// get slice refocussing ratio
        double slice_thickness_excitation = (is_multiplanar ? slice_thickness : (slice_thickness * user_matrix_dimension_3D));

        Gradient gradSlice = Gradient.createGradient(getSequence(), Grad_amp_slice, Time_tx, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        if (isEnableSlice && !gradSlice.prepareSliceSelection(tx_bandwidth_90, slice_thickness_excitation)) {
            slice_thickness_excitation = gradSlice.getSliceThickness();
            double slice_thickness_min = slice_thickness_excitation * slice_thickness / slice_thickness_excitation;
            getUnreachParamExceptionManager().addParam(SLICE_THICKNESS.name(), slice_thickness, slice_thickness_min, ((NumberParam) getParam(SLICE_THICKNESS)).getMaxValue(), "Pulse length too short to reach this slice thickness");
            slice_thickness = slice_thickness_min;
        }
        gradSlice.applyAmplitude();

        // -----------------------------------------------
        // calculate ADC observation time
        // -----------------------------------------------
        double observation_time = ((NumberParam) getParam(ACQUISITION_TIME_PER_SCAN)).getValue().doubleValue();
        setSequenceTableSingleValue(Time_rx, observation_time);

        // -----------------------------------------------
        // calculate READ gradient amplitude
        // -----------------------------------------------
        Gradient gradReadout = Gradient.createGradient(getSequence(), Grad_amp_read_read, Time_rx, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        boolean isEnableReadout = ((BooleanParam) getParam(GRADIENT_ENABLE_READ)).getValue();
        if (isEnableReadout && !gradReadout.prepareReadoutGradient(spectral_width, pixelDimension * acquisition_matrix_dimension_1D)) {
            double spectral_width_max = gradReadout.getSpectralWidth();
            getUnreachParamExceptionManager().addParam(SPECTRAL_WIDTH.name(), spectral_width, ((NumberParam) getParam(SPECTRAL_WIDTH)).getMinValue(), spectral_width_max, "SPECTRAL_WIDTH too high for the readout gradient");
            spectral_width = spectral_width_max;
        }
        gradReadout.setReadoutEchoPlanarAmplitude(echo_train_length, Order.Loop);

        // -----------------------------------------------
        // calculate gradient equivalent rise time
        // -----------------------------------------------
        double grad_rise_time = ((NumberParam) getParam(GRADIENT_RISE_TIME)).getValue().doubleValue();
        double min_rise_time_factor = ((NumberParam) getParam(MIN_RISE_TIME_FACTOR)).getValue().doubleValue();

        double min_rise_time_sinus = GradientMath.getShortestRiseTime(100.0) * Math.PI / 2 * 100 / min_rise_time_factor;
        double min_time = GradientMath.getShortestRiseTime(100.0);
        if (grad_rise_time < min_rise_time_sinus) {
            double new_grad_rise_time = ceilToSubDecimal(min_rise_time_sinus, 5);
            getUnreachParamExceptionManager().addParam(GRADIENT_RISE_TIME.name(), grad_rise_time, new_grad_rise_time, ((NumberParam) getParam(GRADIENT_RISE_TIME)).getMaxValue(), "Gradient ramp time too short ");
            grad_rise_time = new_grad_rise_time;
        }

        setSequenceTableSingleValue(Time_grad_ramp, grad_rise_time);
        double grad_shape_rise_factor_up = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_up));
        double grad_shape_rise_factor_down = Utility.voltageFillingFactor((Shape) getSequence().getPublicTable(Grad_shape_down));
        double grad_shape_rise_time = grad_shape_rise_factor_up * grad_rise_time + grad_shape_rise_factor_down * grad_rise_time;        // shape dependant equivalent rise time

        // -------------------------------------------------------------------------------------------------
        // pre-calculate  SLICE_REF/3D, PHASE_2D,and READ_PREP max area
        // -------------------------------------------------------------------------------------------------
        double grad_phase_application_time = ((NumberParam) getParam(GRADIENT_PHASE_APPLICATION_TIME)).getValue().doubleValue();
        boolean is_k_s_centred = ((BooleanParam) getParam(KS_CENTERED)).getValue();  // symetrique around 0 or go through k0
        setSequenceTableSingleValue(Time_grad_phase_top, grad_phase_application_time);

        // pre-calculate SLICE_refocusing
        boolean is_keyhole_allowed = ((BooleanParam) getParam(KEYHOLE_ALLOWED)).getValue();
        Gradient gradSliceRefPhase3D = Gradient.createGradient(getSequence(), Grad_amp_phase_3D, Time_grad_phase_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        double grad_ratio_slice_refoc = isEnableSlice ? ((NumberParam) getParam(SLICE_REFOCUSING_GRADIENT_RATIO)).getValue().doubleValue() : 0.0;   // get slice refocussing ratio
        if (isEnableSlice)
            gradSliceRefPhase3D.refocalizeGradient(gradSlice, grad_ratio_slice_refoc);
        if (!is_multiplanar && isEnablePhase3D)
            gradSliceRefPhase3D.preparePhaseEncodingForCheck(is_keyhole_allowed ? user_matrix_dimension_3D : acquisition_matrix_dimension_3D, acquisition_matrix_dimension_3D, slice_thickness_excitation, is_k_s_centred);

        // pre-calculate PHASE 2D ENCODING max area
        Gradient gradPhase2D = Gradient.createGradient(getSequence(), Grad_amp_phase_2D, Time_grad_phase_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        if (isEnablePhase)
            gradPhase2D.preparePhaseEncodingForCheck(is_keyhole_allowed ? user_matrix_dimension_2D : acquisition_matrix_dimension_2D, acquisition_matrix_dimension_2D, fovPhase, is_k_s_centred);

        // pre-calculate READ_prephasing max area 
        Gradient gradReadPrep = Gradient.createGradient(getSequence(), Grad_amp_read_prep, Time_grad_phase_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        if (isEnableReadout)
            gradReadPrep.refocalizeGradient(gradReadout, ((NumberParam) getParam(PREPHASING_READ_GRADIENT_RATIO)).getValue().doubleValue());

        // Check if enougth time for 2D_PHASE, 3D_PHASE SLICE_REF or READ_PREP
        double grad_area_sequence_max = 100 * (grad_phase_application_time + grad_shape_rise_time);
        double grad_area_max = Math.max(gradSliceRefPhase3D.getTotalArea(), Math.max(gradReadPrep.getTotalArea(), gradPhase2D.getTotalArea()));            // calculate the maximum gradient aera between SLICE REFOC & READ PREPHASING
        if (grad_area_max > grad_area_sequence_max) {
            double grad_phase_application_time_min = ceilToSubDecimal(grad_area_max / 100.0 - grad_shape_rise_time, 5);
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


        // --------------------------------------------------------------------------------------------------------------------------------------------
        // TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING --- TIMING
        // --------------------------------------------------------------------------------------------------------------------------------------------

        // ------------------------------------------
        // delays for sequence instructions
        // ------------------------------------------
        double min_instruction_delay = 0.000010;     // single instruction minimal duration

        // ------------------------------------------
        // calculate delays adapted to current TE & search for incoherence
        // ------------------------------------------
        double te = ((NumberParam) getParam(ECHO_TIME)).getValue().doubleValue();
        // calculate actual delays between Rf-pulses and ADC
        double time1 = getTimeBetweenEvents(Events.P90 + 1, Events.Acq - 1);
        time1 = time1 + tx_length_90 / 2 + observation_time / 2;// Actual_TE
        time1 = removeTimeForEvents(time1, Events.Delay1); // Actual_TE without delay1

        // get minimal TE value & search for incoherence
        double max_time = ceilToSubDecimal(time1, 5);
        double te_min = max_time + min_instruction_delay;
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
        double min_FIR_delay = lo_FIR_dead_point / spectral_width;
        double min_FIR_4pts_delay = 4 / spectral_width;

        // ------------------------------------------
        // calculate delays adapted to correct spacing in case of ETL & search for incoherence
        // ------------------------------------------
        double echo_spacing = ((NumberParam) getParam(ECHO_SPACING)).getValue().doubleValue();
        double delay2;
        double delay2_min = Math.max(min_FIR_4pts_delay - (grad_rise_time), min_instruction_delay);
        delay2_min = Math.max(delay2_min, min_FIR_delay - (2 * grad_rise_time + getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopStartEcho) + getTimeBetweenEvents(Events.LoopEndEcho, Events.LoopEndEcho)));
        if (echo_train_length > 1) {
            double time2 = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho); // Actual EchoLoop time
            time2 = removeTimeForEvents(time2, Events.Delay2); // Actual EchoLoop time without Delay2
            double echo_spacing_min = time2 + delay2_min;
            if (echo_spacing < echo_spacing_min) {
                System.out.println(" ");
                System.out.println(" EchoSpacing ");
                System.out.println(echo_spacing + " < " + echo_spacing_min + "  : " + time2 + " +  " + delay2_min);
                System.out.println(" ");
                System.out.println(" ");
                echo_spacing_min = ceilToSubDecimal(echo_spacing_min, 5);
                getUnreachParamExceptionManager().addParam(ECHO_SPACING.name(), echo_spacing, echo_spacing_min, ((NumberParam) getParam(ECHO_SPACING)).getMaxValue(), "Echo spacing too short for the User Mx1D and SW");
                echo_spacing = echo_spacing_min;//
            }
            delay2 = echo_spacing - time2;
        } else {
            delay2 = delay2_min;
        }
        setSequenceTableSingleValue(Time_TE_delay2, delay2);

        // -------------------------------------------------------------------------------------------------
        // calculate Phase 2D, 3D and Read REWINDING - SPOILER area, check Grad_Spoil < GMAX
        // -------------------------------------------------------------------------------------------------

        // timing : grad_phase_application_time must be < grad_spoiler_application_time if rewinding
        boolean is_grad_rewinding = ((BooleanParam) getParam(GRADIENT_ENABLE_REWINDING)).getValue();// get slice refocussing ratio
        double grad_spoiler_application_time = ((NumberParam) getParam(GRADIENT_SPOILER_APPL_TIME)).getValue().doubleValue();
        if (is_grad_rewinding && grad_phase_application_time > grad_spoiler_application_time) {
            getUnreachParamExceptionManager().addParam(GRADIENT_SPOILER_APPL_TIME.name(), grad_spoiler_application_time, grad_phase_application_time, ((NumberParam) getParam(GRADIENT_SPOILER_APPL_TIME)).getMaxValue(), "Gradient Spoiler top time must be longer than Phase Application Time");
            grad_spoiler_application_time = grad_phase_application_time;
        }
        setSequenceTableSingleValue(Time_grad_spoiler_top, grad_spoiler_application_time);

        Gradient gradSliceSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_slice, Time_grad_spoiler_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        Gradient gradPhaseSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_phase, Time_grad_spoiler_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);
        Gradient gradReadSpoiler = Gradient.createGradient(getSequence(), Grad_amp_spoiler_read, Time_grad_spoiler_top, Grad_shape_up, Grad_shape_down, Time_grad_ramp);

        // Rewinding :
        if (is_grad_rewinding) {
            if (isEnablePhase3D)
                gradSliceSpoiler.refocalizePhaseEncodingGradient(gradSliceRefPhase3D);
            if (isEnableSlice)
                gradSliceSpoiler.refocalizeGradient(gradSlice, 1 - grad_ratio_slice_refoc);
            if (isEnablePhase)
                gradPhaseSpoiler.refocalizePhaseEncodingGradient(gradPhase2D);
            if (isEnableReadout)
                gradReadSpoiler.refocalizeReadoutGradient(gradReadout, 1 - ((NumberParam) getParam(PREPHASING_READ_GRADIENT_RATIO)).getValue().doubleValue());
        }
        // Spoiler :
        ListNumberParam grad_amp_spoiler_sl_ph_re = (ListNumberParam) getParam(GRAD_AMP_SPOILER_SL_PH_RE);
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

        //--------------------------------------------------------------------------------------
        //  External triggering
        //--------------------------------------------------------------------------------------
        double time_external_trigger_delay_max = min_instruction_delay;
        ListNumberParam trigger_time = (ListNumberParam) getParam(TRIGGER_TIME);
        int number_of_trigger_acquisition = is_trigger ? trigger_time.getValue().size() : 1;

        Table triggerdelay = setSequenceTableValues(Time_trigger_delay, Order.Four);
        if ((!is_trigger)) {
            triggerdelay.add(min_instruction_delay);
        } else {
            for (int i = 0; i < number_of_trigger_acquisition; i++) {
                double time_external_trigger_delay = roundToSubDecimal(trigger_time.getValue().get(i).doubleValue(), 7);
                time_external_trigger_delay = time_external_trigger_delay < min_instruction_delay ? min_instruction_delay : time_external_trigger_delay;
                triggerdelay.add(time_external_trigger_delay);
                time_external_trigger_delay_max = Math.max(time_external_trigger_delay_max, time_external_trigger_delay);
            }
        }

        // ---------------------------------------------------------------
        // calculate TR , Time_last_delay  Time_TR_delay & search for incoherence
        // ---------------------------------------------------------------
        double tr = ((NumberParam) getParam(REPETITION_TIME)).getValue().doubleValue();
        int nb_of_interleaved_slice = ((NumberParam) getSequence().getParam(Nb_interveaved_slice)).getValue().intValue();
        int nb_planar_excitation = (is_multiplanar ? acquisition_matrix_dimension_3D : 1);
        int slices_acquired_in_single_scan = (nb_planar_excitation > 1) ? (nb_of_interleaved_slice + 1) : 1;

        double delay_before_multi_planar_loop = getTimeBetweenEvents(Events.Start, Events.TriggerDelay - 1) + getTimeBetweenEvents(Events.TriggerDelay + 1, Events.LoopMultiPlanarStart - 1) + time_external_trigger_delay_max;
        double delay_before_echo_loop = getTimeBetweenEvents(Events.LoopMultiPlanarStart, Events.LoopStartEcho - 1);
        double delay_echo_loop = getTimeBetweenEvents(Events.LoopStartEcho, Events.LoopEndEcho);
        double delay_spoiler = getTimeBetweenEvents(Events.LoopEndEcho + 1, Events.LoopMultiPlanarEnd - 2);// grad_phase_application_time + grad_rise_time * 2;
        double time_per_slice = delay_before_echo_loop + echo_train_length * delay_echo_loop + delay_spoiler + 2 * min_instruction_delay;// 2 * min_instruction_delay: Events. 20 21
        double min_flush_delay = 0;   // minimal time to flush Chameleon buffer (this time is doubled to avoid hidden delays);
        min_flush_delay = Math.max(min_flush_delay, min_instruction_delay);

        double time_seq_to_end_spoiler = (delay_before_multi_planar_loop + (delay_before_echo_loop + (echo_train_length * delay_echo_loop) + delay_spoiler) * slices_acquired_in_single_scan);
        double tr_min = time_seq_to_end_spoiler + min_instruction_delay * (slices_acquired_in_single_scan * 2 + 1) + min_flush_delay;// 2 +( 2 min_instruction_delay: Events. 22 +(20&21
        if (tr < tr_min) {
            System.out.println(" ");
            System.out.println(" tr_min ");
            System.out.println(tr + " < " + tr_min + "  : " + (time_seq_to_end_spoiler + min_instruction_delay) + " +  " + ((slices_acquired_in_single_scan * 2 + 1) + min_flush_delay));
            System.out.println(" ");
            System.out.println(" ");
            tr_min = ceilToSubDecimal(tr_min, 3);
            this.getUnreachParamExceptionManager().addParam(REPETITION_TIME.name(), tr, tr_min, ((NumberParam) getParam(REPETITION_TIME)).getMaxValue(), "TR too short to reach (ETL * User Mx3D/Shoot3D) in a singl scan");
            tr = tr_min;
        }

        // ------------------------------------------
        // set calculated TR
        // ------------------------------------------
        // set  TR delay to compensate and trigger delays
        double last_delay = min_instruction_delay;
        double tr_delay;
        Table time_tr_delay = setSequenceTableValues(Time_TR_delay, Order.One);
        if (number_of_trigger_acquisition != 1) {
            for (int i = 0; i < number_of_trigger_acquisition; i++) {
                double tmp_time_seq_to_end_spoiler = time_seq_to_end_spoiler - time_external_trigger_delay_max + triggerdelay.get(i).doubleValue();
                tr_delay = (tr - (tmp_time_seq_to_end_spoiler - +last_delay + min_flush_delay)) / slices_acquired_in_single_scan - min_instruction_delay;
                time_tr_delay.add(tr_delay);
            }
            time_tr_delay.setOrder(Order.Four);
        } else {
            tr_delay = (tr - (time_seq_to_end_spoiler + last_delay + min_flush_delay)) / slices_acquired_in_single_scan - min_instruction_delay;
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
        int nb_scan_2d = ((NumberParam) getSequence().getParam(Nb_2d)).getValue().intValue();
        int nb_scan_3d = ((NumberParam) getSequence().getParam(Nb_3d)).getValue().intValue();
        acquisition_matrix_dimension_4D = ((NumberParam) getParam(ACQUISITION_MATRIX_DIMENSION_4D)).getValue().intValue();

        boolean is_dynamic_min_time = ((BooleanParam) getParam(DYNAMIC_MIN_TIME)).getValue();

        double frame_acquisition_time = number_of_averages * nb_scan_3d * nb_scan_2d * tr;
        double time_between_frames_min = ceilToSubDecimal(frame_acquisition_time + min_instruction_delay + min_flush_delay, 1);
        double time_between_frames = time_between_frames_min;
        double interval_between_frames_delay = min_flush_delay;

        if (is_dynamic) {
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
        setSequenceTableSingleValue(Time_btw_dyn_frames_delay, interval_between_frames_delay);

        double total_acquisition_time = time_between_frames * numberOfDynamicAcquisition;
        setParamValue(SEQUENCE_TIME, total_acquisition_time);

        // -----------------------------------------------
        // Phase Reset
        // -----------------------------------------------
        setSequenceParamValue(Phase_reset, USER_TMP_PARAM_BOOL_1);

        // -----------------------------------------------
        // frequency OFF CENTER offset
        // -----------------------------------------------
        //Offset according to READ PHASE and SLICE
        double off_center_distance_3D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_3D)).getValue().doubleValue();
//        double off_center_distance_2D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_2D)).getValue().doubleValue();
        double off_center_distance_1D = ((NumberParam) getParam(OFF_CENTER_FIELD_OF_VIEW_1D)).getValue().doubleValue();


        // ------------------------------------------------------------------
        // calculate TX FREQUENCY offsets tables for multi-slice acquisitions and 
        // ------------------------------------------------------------------

        double spacing_between_slice = ((NumberParam) getParam(SPACING_BETWEEN_SLICE)).getValue().doubleValue();
        if (is_multiplanar && nb_planar_excitation > 1 && isEnableSlice) {
            //MULTI-PLANAR case : calculation of frequency offset table
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, nb_planar_excitation, spacing_between_slice, off_center_distance_3D);
            pulseTX.reoderOffsetFreq(plugin, acquisition_matrix_dimension_1D * echo_train_length, slices_acquired_in_single_scan);
            pulseTX.setFrequencyOffset(slices_acquired_in_single_scan != 1 ? Order.ThreeLoop : Order.Three);
        } else {
            //3D CASE :
            pulseTX.prepareOffsetFreqMultiSlice(gradSlice, 1, 0, off_center_distance_3D);
            pulseTX.setFrequencyOffset(Order.Three);
        }


        // ------------------------------------------------------------------
        // calculate TX FREQUENCY offsets tables for multi-slice acquisitions and 
        // ------------------------------------------------------------------
        RFPulse pulseTXPrep = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_prep);
        pulseTXPrep.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        RFPulse pulseTXComp = RFPulse.createRFPulse(getSequence(), Time_grad_ramp, FreqOffset_tx_comp);
        pulseTXComp.setCompensationFrequencyOffset(pulseTX, grad_ratio_slice_refoc);

        setSequenceTableSingleValue(Tx_phase, 0);

        //----------------------------------------------------------------------
        // OFF CENTER FIELD OF VIEW 1D
        // modify RX FREQUENCY OFFSET
        //----------------------------------------------------------------------
        RFPulse pulseRX = RFPulse.createRFPulse(getSequence(), Time_rx, Rx_freq_offset);
        pulseRX.setFrequencyOffsetReadout(gradReadout, off_center_distance_1D);

        //fill the OFF_CENTER_FIELD_OF_VIEW_EFF User Parameter
        ArrayList<Number> off_center_distanceList = new ArrayList<>();
        off_center_distanceList.add(off_center_distance_1D);
        off_center_distanceList.add(0);
        off_center_distanceList.add(0);

        setParamValue(OFF_CENTER_FIELD_OF_VIEW_EFF, off_center_distanceList);
        //----------------------------------------------------------------------
        // modify RX FREQUENCY Prep and comp
        double timeADC1 = getTimeBetweenEvents(Events.Acq - 1, Events.Acq - 1) + observation_time / 2.0;
        double timeADC2 = getTimeBetweenEvents(Events.Acq + 1, Events.Acq + 2) + observation_time / 2.0;

        RFPulse pulseRXPrep = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_prep);
        pulseRXPrep.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC1);

        RFPulse pulseRXComp = RFPulse.createRFPulse(getSequence(), Time_min_instruction, FreqOffset_rx_comp);
        pulseRXComp.setCompensationFrequencyOffsetWithTime(pulseRX, timeADC2);

        //--------------------------------------------------------------------------------------
        //  calculate RF_SPOILING 
        //--------------------------------------------------------------------------------------
        RFPulse pulseRFSpoiler = RFPulse.createRFPulse(getSequence(), Time_rf_spoiling, FreqOffset_RFSpoiling);
        pulseRFSpoiler.setFrequencyOffsetForPhaseShift(((BooleanParam) getParam(RF_SPOILING)).getValue() ? 117.0 : 0);

        // ----------------------------------------------------------------------------------------------
        // modify RX PHASE TABLE to handle OFF CENTER FOV 2D in both cases or PHASE CYCLING
        // ----------------------------------------------------------------------------------------------
        setSequenceTableSingleValue(Rx_phase, 0);

        //--------------------------------------------------------------------------------------
        //Export DICOM
        //--------------------------------------------------------------------------------------
        // Set  TRIGGER_TIME for dynamic or trigger acquisition
        if (is_dynamic && (numberOfDynamicAcquisition != 1) && !is_trigger) {
            ArrayList<Number> arrayListTrigger = new ArrayList<>();
            for (int i = 0; i < numberOfDynamicAcquisition; i++) {
                arrayListTrigger.add(i * time_between_frames);
            }
//            ListNumberParam list = new ListNumberParam((NumberParam) getParamFromName(MriDefaultParams.TRIGGER_TIME.name()), arrayListTrigger);       // associate TE to images for DICOM export
//            putVariableParameter(list, (4));
            setParamValue(TRIGGER_TIME, arrayListTrigger);
        }

        // Set  ECHO_TIME
        if (echo_train_length != 1) {
            ArrayList<Number> arrayListEcho = new ArrayList<>();
            for (int i = 0; i < acquisition_matrix_dimension_4D; i++) {
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

        if (echo_train_length != 1) {
            number_of_MultiSeries = echo_train_length;
            time_between_MultiSeries = te;
            multiseries_parametername = "TE";
//            for (int i = 1; i <= number_of_MultiSeries; i++) {
            //double multiseries_value = Math.round(i * te * 1e5) / 1e2;
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round((te + i * echo_spacing) * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        } else if (is_trigger && number_of_trigger_acquisition != 1) {
            number_of_MultiSeries = number_of_trigger_acquisition;
            time_between_MultiSeries = frame_acquisition_time;
            multiseries_parametername = "TRIGGER DELAY";
            for (int i = 0; i < number_of_MultiSeries; i++) {
                double multiseries_value = Math.round(trigger_time.getValue().get(i).doubleValue() * 1e5) / 1e2;
                multiseries_valuesList.add(multiseries_value);
            }
        }
        setParamValue(MULTISERIES_PARAMETER_VALUE, multiseries_valuesList);
        setParamValue(MULTISERIES_PARAMETER_NAME, multiseries_parametername);

        ArrayList<Number> acquisition_timesList = new ArrayList<>();
        double acqusition_time;
        for (int i = 0; i < numberOfDynamicAcquisition; i++) {
            for (int j = 0; j < number_of_MultiSeries; j++) {
                acqusition_time = roundToSubDecimal((i * time_between_frames + j * time_between_MultiSeries), 3);
                acquisition_timesList.add(acqusition_time);
            }
        }
        setParamValue(ACQUISITION_TIME_OFFSET, acquisition_timesList);

    }
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // End After Routine
    // --------------------------------------------------------------------------------------------------------------------------------------------


    private double getTx_bandwidth_factor_90(GradientEchoParams tx_shape, GradientEchoParams tx_bandwith_factor_param, GradientEchoParams tx_bandwith_factor_param3d) {
        double tx_bandwidth_factor_90;
        ListNumberParam tx_bandwith_factor_table = (ListNumberParam) getParam(tx_bandwith_factor_param);
        ListNumberParam tx_bandwith_factor_3D_table = (ListNumberParam) getParam(tx_bandwith_factor_param3d);

        if (is_multiplanar) {
            if ("GAUSSIAN".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(1).doubleValue();
            } else if ("SINC3".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(2).doubleValue();
            } else if ("SINC5".equalsIgnoreCase((String) getParam(tx_shape).getValue())) {
                tx_bandwidth_factor_90 = tx_bandwith_factor_table.getValue().get(3).doubleValue();
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
            } else {
                tx_bandwidth_factor_90 = tx_bandwith_factor_3D_table.getValue().get(0).doubleValue();
            }
        }
        return tx_bandwidth_factor_90;
    }

    private double ceilToSubDecimal(double numberToBeRounded, double Order) {
        return Math.ceil(numberToBeRounded * Math.pow(10, Order)) / Math.pow(10, Order);
    }

    private double roundToSubDecimal(double numberToBeRounded, double order) {
        return Math.round(numberToBeRounded * Math.pow(10, order)) / Math.pow(10, order);
    }


    private double compensateFreqOffset(double compDuration, double pulseFreqOffset, double pulseDuration) {
        return -((pulseFreqOffset * pulseDuration) % 1) / compDuration;
    }


    private Table setSequenceTableSingleValue(String tableName, double... values) {
        // uses Order.One because there are no tables in this dimension: compilation issue
        return setSequenceTableValues(tableName, Order.FourLoop, values);
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

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //
    //                                                          END OF SEQUENCE GENERATOR
    //
    // *********************************************************************************************************************************************
    // *** END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR *********  END OF SEQUENCE GENERATOR ********* END OF SEQUENCE GENERATOR 
    // *********************************************************************************************************************************************

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
        int new_divisor = divisor;
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

    /**
     * Calculate the time during 2 including events correspnding to the index
     *
     * @param indexFirstEvent The index of the first time event
     * @param indexLastEvent  The index of the last time event
     * @return The total time between the 2 events (including)
     */
    public double getTimeBetweenEvents(int indexFirstEvent, int indexLastEvent) {
        double time = 0;
        for (int i = indexFirstEvent; i < indexLastEvent + 1; i++) {
            time += ((TimeElement) getSequence().getTimeChannel().get(i)).getTime().getFirst().doubleValue();
        }
        return time;
    }

    /**
     * Substract the time value of the event corresponding to the "index"
     * parameter from the parameter "time"
     *
     * @param time
     * @param indexEvent The index of the time event to be substract to the
     *                   value of the parameter time
     * @return The calculated time
     */
    public double removeTimeForEvents(double time, int... indexEvent) {
        for (int i = 0; i < indexEvent.length; i++) {
            time -= ((TimeElement) getSequence().getTimeChannel().get(indexEvent[i])).getTime().getFirst().doubleValue();
        }
        return time;
    }


    public ArrayList<RoleEnum> getPluginAccess() {
        ArrayList<RoleEnum> roleEnums = new ArrayList<RoleEnum>();
        roleEnums.add(RoleEnum.User);
        return roleEnums;
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