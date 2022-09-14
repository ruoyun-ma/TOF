//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.gradientecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.sequenceGenerator.GeneratorParamEnum;

import java.util.List;
import static java.util.Arrays.asList;

public enum U implements GeneratorParamEnum {
    ACCU_DIM("ACCU_DIM") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACCU_DIM");
            param.setDisplayedName("ACCU_DIM");
            param.setDescription("Dimension on which averaging is performed by the Cameleon");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(0);
            param.setMaxValue(3);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_1D("ACQUISITION_MATRIX_DIMENSION_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Acquisition Matrix 1D");
            param.setDescription("Info: Size of the initial dataset (raw data) in first dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_2D("ACQUISITION_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Acquisition Matrix 2D");
            param.setDescription("Info: Size of the initial dataset (raw data) in second dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_3D("ACQUISITION_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Acquisition Matrix 3D");
            param.setDescription("Info: Size of the initial dataset (raw data) in third dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(10);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_4D("ACQUISITION_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDisplayedName("Acquisition Matrix 4D");
            param.setDescription("Info: Size of the initial dataset (raw data) in fourth dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MODE("ACQUISITION_MODE") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("ACQUISITION_MODE");
            param.setDisplayedName("ACQUISITION_MODE");
            param.setDescription("ACQUISITION_MODE and DATA_REPRESENTATION are filled according to the phase modulation chosen");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_NB_ECHO_TRAIN("ACQUISITION_NB_ECHO_TRAIN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_NB_ECHO_TRAIN");
            param.setDisplayedName("Nb. Echo Train");
            param.setDescription("Info: Number of echo trains in each slab ");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setValue(1320);
            param.setDefaultValue(0);
            return param;
        }
    },

    ACQUISITION_NB_VIEW("ACQUISITION_NB_VIEW") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_NB_VIEW");
            param.setDisplayedName("Nb. View");
            param.setDescription("Info: Number of sampling points in 2D-3D plane");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setValue(1320);
            param.setDefaultValue(0);
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET("ACQUISITION_TIME_OFFSET") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("Acquisition time offset ");
            param.setDescription("Info: Relative acquisition start times in Dynamic or MultiSeries scans");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0));
            param.setDefaultValue(asListNumber(0.0));
            return param;
        }
    },

    ACQUISITION_TIME_PER_SCAN("ACQUISITION_TIME_PER_SCAN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_TIME_PER_SCAN");
            param.setDisplayedName("Observation Time ");
            param.setDescription("Info: Time during which the signal is sampled by the ADC");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.003997696);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    BASE_FREQ_1("BASE_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_1");
            param.setDisplayedName("Base Freq 1");
            param.setDescription("The base frequency of the first sequence channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(1.27552944E8);
            return param;
        }
    },

    BASE_FREQ_2("BASE_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_2");
            param.setDisplayedName("Base Freq 2");
            param.setDescription("The base frequency of the second sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_3("BASE_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_3");
            param.setDisplayedName("Base Freq 3");
            param.setDescription("The base frequency of the third sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    BASE_FREQ_4("BASE_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_4");
            param.setDisplayedName("Base Freq 4");
            param.setDescription("The base frequency of the fourth sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    DATA_REPRESENTATION("DATA_REPRESENTATION") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("DATA_REPRESENTATION");
            param.setDisplayedName("DATA_REPRESENTATION");
            param.setDescription("ACQUISITION_MODE and DATA_REPRESENTATION are filled according to the phase modulation chosen");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Miscellaneous);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    DIGITAL_FILTER_REMOVED("DIGITAL_FILTER_REMOVED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DIGITAL_FILTER_REMOVED");
            param.setDisplayedName("DIGITAL_FILTER_REMOVED");
            param.setDescription("Enable to activate the data shift");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    DIGITAL_FILTER_SHIFT("DIGITAL_FILTER_SHIFT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DIGITAL_FILTER_SHIFT");
            param.setDisplayedName("DIGITAL_FILTER_SHIFT");
            param.setDescription("Data shift due to the digital filter");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setValue(19);
            param.setDefaultValue(19);
            return param;
        }
    },

    DUMMY_SCAN("DUMMY_SCAN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DUMMY_SCAN");
            param.setDisplayedName("Dummy Scans");
            param.setDescription("Number of dummy cycles used to reach steady-state");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(0);
            param.setDefaultValue(128);
            return param;
        }
    },

    DYNAMIC_MIN_TIME("DYNAMIC_MIN_TIME") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_MIN_TIME");
            param.setDisplayedName("Dyn - Min Time");
            param.setDescription("Enable to use the minimum time allowed to perform dynamic acquisition ");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYNAMIC_SEQUENCE("DYNAMIC_SEQUENCE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_SEQUENCE");
            param.setDisplayedName("Dynamic");
            param.setDescription("Enable dynamic acquisition measurments");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYN_NUMBER_OF_ACQUISITION("DYN_NUMBER_OF_ACQUISITION") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_NUMBER_OF_ACQUISITION");
            param.setDisplayedName("Dyn - No. Frames");
            param.setDescription("Number of frames acquired in dynamic sequence");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    DYN_TIME_BTW_FRAMES("DYN_TIME_BTW_FRAMES") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_TIME_BTW_FRAMES");
            param.setDisplayedName("Dyn - Time btw Frames");
            param.setDescription("Time interval between frames in dynamic sequence");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(221.185);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_SPACING("ECHO_SPACING") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_SPACING");
            param.setDisplayedName("ECHO_SPACING");
            param.setDescription("Echo spacing : delay between two consecutive echoes of the train (ETL>1), input if MultiEcho - Separated Scans unchecked");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_TIME("ECHO_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME");
            param.setDisplayedName("TE");
            param.setDescription("Echo Time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.00505);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    ECHO_TRAIN_LENGTH("ECHO_TRAIN_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TRAIN_LENGTH");
            param.setDisplayedName("Echo Train Length");
            param.setDescription("Length of echo train after one excitation pulse ( fixed to 1 in TOF)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(1);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    FATSAT_BANDWIDTH("FATSAT_BANDWIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_BANDWIDTH");
            param.setDisplayedName("FatSat - TX BW");
            param.setDescription("Fat-Sat pulse Bandwidth");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(0.0);
            param.setMaxValue(2000.0);
            param.setValue(400.0);
            param.setDefaultValue(448.0);
            return param;
        }
    },

    FATSAT_FLIP_ANGLE("FATSAT_FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_FLIP_ANGLE");
            param.setDisplayedName("FatSat - Flip Angle");
            param.setDescription("Info: Fat-Sat pulse (90°) Flip Angle");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(0.0);
            param.setMaxValue(360.0);
            param.setValue(90.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    FATSAT_GRAD_APP_TIME("FATSAT_GRAD_APP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_GRAD_APP_TIME");
            param.setDisplayedName("FatSat - Gradient Duration");
            param.setDescription("Top time of Fat-Sat gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(6.2E-4);
            param.setDefaultValue(0.001);
            return param;
        }
    },

    FATSAT_OFFSET_FREQ("FATSAT_OFFSET_FREQ") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_OFFSET_FREQ");
            param.setDisplayedName("Fat-Water Offset");
            param.setDescription("Separation between fat and water frequencies (at 3T ~ 420-448Hz ; at 1.5T ~ 210-224Hz): Default = water frequency * 3.5");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(448.0);
            param.setDefaultValue(448.0);
            return param;
        }
    },

    FATSAT_PERIODE("FATSAT_PERIODE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_PERIODE");
            param.setDisplayedName("FATSAT_PERIODE");
            param.setDescription("Not applicable in TOF");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.07);
            param.setDefaultValue(0.05);
            return param;
        }
    },

    FATSAT_PERIODE_EFF("FATSAT_PERIODE_EFF") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_PERIODE_EFF");
            param.setDisplayedName("FATSAT_PERIODE_EFF");
            param.setDescription("Not applicable in TOF");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.06207235200000001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_AMP_90("FATSAT_TX_AMP_90") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_AMP_90");
            param.setDisplayedName("FatSat - TX Amplitude");
            param.setDescription("Info: Fat-Sat Pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(46.31679206846105);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_LENGTH("FATSAT_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_LENGTH");
            param.setDisplayedName("FatSat - TX Length");
            param.setDescription("Info: Fat-Sat Pulse duration");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.008);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_SHAPE("FATSAT_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("FATSAT_TX_SHAPE");
            param.setDisplayedName("FatSat - TX Shape");
            param.setDescription("Fat-Sat Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    FAT_SATURATION_ENABLED("FAT_SATURATION_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FAT_SATURATION_ENABLED");
            param.setDisplayedName("Fat Saturation");
            param.setDescription("Enable Fat-Sat preparation pulse ");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    FAT_WATER_SEP_2DMRSI("FAT_WATER_SEP_2DMRSI") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FAT_WATER_SEP_2DMRSI");
            param.setDisplayedName("FAT_WATER_SEP_2DMRSI");
            param.setDescription("Checkbox parameter for running fat-water separation plugin");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FIELD_OF_VIEW("FIELD_OF_VIEW") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW");
            param.setDisplayedName("FOV Read");
            param.setDescription("Field Of View along the frequency encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.064);
            param.setDefaultValue(0.6);
            return param;
        }
    },

    FIELD_OF_VIEW_3D("FIELD_OF_VIEW_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_3D");
            param.setDisplayedName("FOV Slice/3D");
            param.setDescription(" Field Of View coverage along the direction orthogonal to the phase and frequency encoding directions");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(0.02);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FIELD_OF_VIEW_PHASE("FIELD_OF_VIEW_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_PHASE");
            param.setDisplayedName("FOV Phase");
            param.setDescription("Field Of View along the phase encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.01);
            param.setMaxValue(10.0);
            param.setValue(0.064);
            param.setDefaultValue(0.08);
            return param;
        }
    },

    FLIP_ANGLE("FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLIP_ANGLE");
            param.setDisplayedName("Flip Angle");
            param.setDescription("Excitation flip angle");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(-360.0);
            param.setMaxValue(360.0);
            param.setValue(90.0);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    FLOWCOMP_DURATION("FLOWCOMP_DURATION") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLOWCOMP_DURATION");
            param.setDisplayedName("Gradient Duration Flow Comp");
            param.setDescription("Plateau duration of the gradient used for flow compensation ");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(7.0E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    FLOW_COMPENSATION("FLOW_COMPENSATION") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FLOW_COMPENSATION");
            param.setDisplayedName("Flow Compensation");
            param.setDescription("Enable flow compensation");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_DOUBLED("FOV_DOUBLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_DOUBLED");
            param.setDisplayedName("Frequency Oversampling");
            param.setDescription("Enable double FOV and BW to avoid aliazing");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_RATIO_PHASE("FOV_RATIO_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_RATIO_PHASE");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction (2D/1D)");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100000.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    FOV_SQUARE("FOV_SQUARE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_SQUARE");
            param.setDisplayedName("Square FOV");
            param.setDescription("Enable to enforce a square FOV (Phase FOV ratio = 100%)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE("GRADIENT_ENABLE_PHASE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE");
            param.setDisplayedName("Gradient Phase-2D");
            param.setDescription("Enable 2D phase encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE_3D("GRADIENT_ENABLE_PHASE_3D") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE_3D");
            param.setDisplayedName("Gradient Phase-3D");
            param.setDescription("Enable 3D phase encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_READ("GRADIENT_ENABLE_READ") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_READ");
            param.setDisplayedName("Gradient Read ");
            param.setDescription("Enable  frequency encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_REWINDING("GRADIENT_ENABLE_REWINDING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_REWINDING");
            param.setDisplayedName("Gradient Rewinding ");
            param.setDescription("Enable to do gradient rewind instead of spoiling");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE("GRADIENT_ENABLE_SLICE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE");
            param.setDisplayedName("Gradient Slice");
            param.setDescription("Enable the slice selection gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_SPOILER("GRADIENT_ENABLE_SPOILER") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SPOILER");
            param.setDisplayedName("Gradient Spoiling");
            param.setDescription("Enable gradient spoiler in the three directions");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_PHASE_APPLICATION_TIME("GRADIENT_PHASE_APPLICATION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDisplayedName("Gradient Duration Phase");
            param.setDescription("Phase encoding gradient application time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.3E-4);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_RISE_TIME("GRADIENT_RISE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_RISE_TIME");
            param.setDisplayedName("Gradient Rise Time");
            param.setDescription("Rise time of the gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(1.5E-4);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_SPOILER_TIME("GRADIENT_SPOILER_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_SPOILER_TIME");
            param.setDisplayedName("Gradient Duration Spoiler");
            param.setDescription("Duration of the spoiler gradients");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.3E-4);
            param.setDefaultValue(9.999999999999999E-5);
            return param;
        }
    },

    GRAD_AMP_SPOILER_SL_PH_RE("GRAD_AMP_SPOILER_SL_PH_RE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("GRAD_AMP_SPOILER_SL_PH_RE");
            param.setDisplayedName("Gradient Amplitude Spoilers");
            param.setDescription("List of spoiler gradient amplitudes [Slice, Phase, Read]");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_A0("HARDWARE_A0") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_A0");
            param.setDisplayedName("HARDWARE_A0");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(27.69, 28.58, 22.43));
            return param;
        }
    },

    HARDWARE_DC("HARDWARE_DC") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_DC");
            param.setDisplayedName("HARDWARE_DC");
            param.setDescription("");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(-0.174, 0.2686, -0.1862, 0.0));
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_A("HARDWARE_PREEMPHASIS_A") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_A");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_A");
            param.setDescription("");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_T("HARDWARE_PREEMPHASIS_T") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_T");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_T");
            param.setDescription("");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.02, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_SHIM("HARDWARE_SHIM") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_SHIM");
            param.setDisplayedName("HARDWARE_SHIM");
            param.setDescription("");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_SHIM_LABEL("HARDWARE_SHIM_LABEL") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("HARDWARE_SHIM_LABEL");
            param.setDisplayedName("HARDWARE_SHIM_LABEL");
            param.setDescription("");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setValue("YZ XY XZ X2-Y2 Z0 Z2 X Y Z");
            param.setDefaultValue("");
            return param;
        }
    },

    IMAGE_ORIENTATION_SUBJECT("IMAGE_ORIENTATION_SUBJECT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_ORIENTATION_SUBJECT");
            param.setDisplayedName("Image Orientation");
            param.setDescription("Direction cosines of the first row and the first column with respect to the subject");
            param.setLocked(true);
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(1.0, 0.0, 0.0, 0.0, -1.0, 0.0));
            param.setDefaultValue(asListNumber(1.0, 0.0, 0.0, 0.0, 1.0, 0.0));
            return param;
        }
    },

    IMAGE_POSITION_SUBJECT("IMAGE_POSITION_SUBJECT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_POSITION_SUBJECT");
            param.setDisplayedName("IMAGE_POSITION_SUBJECT");
            param.setDescription("x, y, and z coordinates of the upper left hand corner of the image");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asListNumber(0.0, 0.0, 0.0));
            param.setDefaultValue(asListNumber(0.0, 0.0, 0.0));
            return param;
        }
    },

    INTERLEAVED_ECHO_TRAIN("INTERLEAVED_ECHO_TRAIN") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("INTERLEAVED_ECHO_TRAIN");
            param.setDisplayedName("INTERLEAVED_ECHO_TRAIN");
            param.setDescription("multiple ETL in interleaved mode");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    INTERLEAVED_EFF_ECHO_SPACING("INTERLEAVED_EFF_ECHO_SPACING") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERLEAVED_EFF_ECHO_SPACING");
            param.setDisplayedName("INTERLEAVED_EFF_ECHO_SPACING");
            param.setDescription("Effective echo spacing of interleaved echo trains");
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.0E-4);
            param.setDefaultValue(8.0E-4);
            return param;
        }
    },

    INTERLEAVED_NUM_OF_ECHO_TRAIN("INTERLEAVED_NUM_OF_ECHO_TRAIN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERLEAVED_NUM_OF_ECHO_TRAIN");
            param.setDisplayedName("INTERLEAVED_NUM_OF_ECHO_TRAIN");
            param.setDescription("Number of interleaved echo train");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.UnsignedShort);
            param.setMinValue(1);
            param.setMaxValue(65535);
            param.setValue(1);
            param.setDefaultValue(1);
            param.setSuggestedValues(asListNumber(1));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    INTERMEDIATE_FREQUENCY("INTERMEDIATE_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERMEDIATE_FREQUENCY");
            param.setDisplayedName("INTERMEDIATE_FREQUENCY");
            param.setDescription("Info: Frequency from the Hardware used for the signal demodulation (ADC optimal frequency)");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.251E7);
            param.setDefaultValue(1.251E7);
            return param;
        }
    },

    KSPACE_FILLING("KSPACE_FILLING") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("KSPACE_FILLING");
            param.setDisplayedName("k-space Filling");
            param.setDescription("Choose the way to fill the k-space i.e. the order of line acquisition");
            param.setCategory(Category.Acquisition);
            param.setValue("Linear");
            param.setDefaultValue("Linear");
            param.setSuggestedValues(asList("Linear", "3DElliptic"));
            return param;
        }
    },

    KS_CENTERED("KS_CENTERED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTERED");
            param.setDisplayedName("k-space Centered");
            param.setDescription("Center the k-space around k0 (Checked) or go through k0 (Unchecked)");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KS_CENTER_MODE("KS_CENTER_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTER_MODE");
            param.setDisplayedName("KS_CENTER_MODE");
            param.setDescription("Enable to turn off the phase encoding gradient and acquire two scans only. Used for RG calibration setup ");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    LAST_PUT("LAST_PUT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LAST_PUT");
            param.setDisplayedName("LAST_PUT");
            param.setDescription("LAST_PUT");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(1, 63, 0, 0, 3));
            param.setDefaultValue(asListNumber(0, 0, 0));
            return param;
        }
    },

    MAGNETIC_FIELD_STRENGTH("MAGNETIC_FIELD_STRENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("MAGNETIC_FIELD_STRENGTH");
            param.setDisplayedName("B0 Strength");
            param.setDescription("Info: Magnetic field stength from the hardware");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Field);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(3.0);
            param.setDefaultValue(3.0);
            return param;
        }
    },

    MANUFACTURER("MANUFACTURER") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MANUFACTURER");
            param.setDisplayedName("Manufacturer");
            param.setDescription("Manufacturer");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Manufacturer");
            param.setDefaultValue("Manufacturer");
            return param;
        }
    },

    MIN_RISE_TIME_FACTOR("MIN_RISE_TIME_FACTOR") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("MIN_RISE_TIME_FACTOR");
            param.setDisplayedName("Min Rise Time Factor");
            param.setDescription("Safety parameter applied on maximum gradient slew rate. Fastest is 100%");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    MODALITY("MODALITY") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MODALITY");
            param.setDisplayedName("Modality");
            param.setDescription("The modality for the acquisition");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("MRI");
            param.setDefaultValue("MRI");
            param.setSuggestedValues(asList("NMR", "MRI", "DEFAULT"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    MODEL_NAME("MODEL_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MODEL_NAME");
            param.setDisplayedName("Model Name");
            param.setDescription("Model name");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Model name");
            param.setDefaultValue("Model name");
            return param;
        }
    },

    MULTISERIES_PARAMETER_NAME("MULTISERIES_PARAMETER_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("MULTISERIES_PARAMETER_NAME");
            param.setDisplayedName("Multiseries param name");
            param.setDescription("Info: Name of the multiseries parameter. Can be TE (multi-echoes) or Trigger Delay");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            param.setSuggestedValues(asList("TE", "TI", "TRIGGER DELAY"));
            return param;
        }
    },

    MULTISERIES_PARAMETER_VALUE("MULTISERIES_PARAMETER_VALUE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("MULTISERIES_PARAMETER_VALUE");
            param.setDisplayedName("Multiseries param values");
            param.setDescription("Info: List of valueS of the incremented parameter in multiseries mode. Can be TE (multi-echoes) or Trigger Delay");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    MULTI_PLANAR_EXCITATION("MULTI_PLANAR_EXCITATION") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("MULTI_PLANAR_EXCITATION");
            param.setDisplayedName("2D-Imaging ");
            param.setDescription("Switch between 2D multislice (enable) or 3D acquisition (disable)");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    NUCLEUS_1("NUCLEUS_1") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_1");
            param.setDisplayedName("Nucleus 1");
            param.setDescription("The nucleus used for the first sequence channel");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_2("NUCLEUS_2") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_2");
            param.setDisplayedName("Nucleus 2");
            param.setDescription("The nucleus used for the second sequence channel");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_3("NUCLEUS_3") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_3");
            param.setDisplayedName("Nucleus 3");
            param.setDescription("The nucleus used for the third sequence channel");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_4("NUCLEUS_4") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_4");
            param.setDisplayedName("Nucleus 4");
            param.setDescription("The nucleus used for the fourth sequence channel");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Y", "Other", "X", "1H", "2H", "3H", "3He", "6Li", "7Li", "9Be", "10B", "11B", "13C", "14N", "15N", "17O", "19F", "21Ne", "23Na", "25Mg", "27Al", "29Si", "31P", "33S", "35Cl", "37Cl", "39K", "40K", "41K", "43Ca", "45Sc", "47Ti", "49Ti", "50V", "51V", "53Cr", "55Mn", "57Fe", "59Co", "61Ni", "63Cu", "65Cu", "67Zn", "69Ga", "71Ga", "73Ge", "75As", "77Se", "79Br", "81Br", "83Kr", "85Rb", "87Rb", "87Sr", "89Y", "91Zr", "93Nb", "95Mo", "97Mo", "99Tc", "99Ru", "101Ru", "103Rh", "105Pd", "107Ag", "109Ag", "111Cd", "113Cd", "113In", "115Sn", "115In", "117Sn", "119Sn", "121Sb", "123Te", "123Sb", "125Te", "127I", "129Xe", "131Xe", "133Cs", "135Ba", "137Ba", "138La", "139La", "141Pr", "143Nd", "145Nd", "147Sm", "149Sm", "151Eu", "153Eu", "155Gd", "157Gd", "159Tb", "161Dy", "163Dy", "165Ho", "167Er", "169Tm", "171Yb", "173Yb", "175Lu", "176Lu", "177Hf", "179Hf", "181Ta", "183W", "185Re", "187Re", "187Os", "189Os", "191Ir", "193Ir", "195Pt", "197Au", "199Hg", "201Hg", "203Tl", "205Tl", "207Pb", "209Bi", "235U"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUMBER_OF_AVERAGES("NUMBER_OF_AVERAGES") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_AVERAGES");
            param.setDisplayedName("NEX");
            param.setDescription("Number of averages");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    NUMBER_OF_INTERLEAVED_SLICE("NUMBER_OF_INTERLEAVED_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDisplayedName("No. Slices per TR");
            param.setDescription("Info: Number of interleaved slice acquired during one TR");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    NUMBER_OF_SHOOT_3D("NUMBER_OF_SHOOT_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SHOOT_3D");
            param.setDisplayedName("No. Interleaved Slice Packs");
            param.setDescription("To split the slices in different packs (nb of slices per TR = nb of slices / number of slice packs). Increase the number of packs (must be a divisor of the number of slices) to reach shorter TR");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(10);
            param.setDefaultValue(1);
            return param;
        }
    },

    NUMBER_OF_SLAB("NUMBER_OF_SLAB") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SLAB");
            param.setDisplayedName("No. Slabs");
            param.setDescription("Number of slabs ");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    OBSERVED_FREQUENCY("OBSERVED_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OBSERVED_FREQUENCY");
            param.setDisplayedName("Observed frequency");
            param.setDescription("The frequency of the acquisition");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.28E8);
            param.setDefaultValue(6.3E7);
            return param;
        }
    },

    OBSERVED_NUCLEUS("OBSERVED_NUCLEUS") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("OBSERVED_NUCLEUS");
            param.setDisplayedName("Observed Nucleus");
            param.setDescription("The observed nucleus");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    OFFSET_FREQ_1("OFFSET_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_1");
            param.setDisplayedName("Offset 1");
            param.setDescription("The offset frequency of the first sequence channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_2("OFFSET_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_2");
            param.setDisplayedName("Offset 2");
            param.setDescription("The offset frequency of the second sequence channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_3("OFFSET_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_3");
            param.setDisplayedName("Offset 3");
            param.setDescription("The offset frequency of the third sequence channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFFSET_FREQ_4("OFFSET_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_4");
            param.setDisplayedName("Offset 4");
            param.setDescription("The offset frequency of the fourth sequence channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_1D("OFF_CENTER_FIELD_OF_VIEW_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_1D");
            param.setDisplayedName("Location 1D");
            param.setDescription("Info: Off-center distance in Readout Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_2D("OFF_CENTER_FIELD_OF_VIEW_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_2D");
            param.setDisplayedName("Location 2D");
            param.setDescription("Info: Off-center distance in Phase Encoding Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_3D("OFF_CENTER_FIELD_OF_VIEW_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_3D");
            param.setDisplayedName("Location 3D");
            param.setDescription("Info: Off-center distance in Slice Direction");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_EFF("OFF_CENTER_FIELD_OF_VIEW_EFF") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDisplayedName("Location Eff");
            param.setDescription("Info: Off Center effective handeled by the acquisition in 1D, 2D and 3D (read, phase, slice)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asListNumber(0.0, 0.0, 0.0));
            param.setDefaultValue(asListNumber(0.0, 0.0, 0.0));
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_X("OFF_CENTER_FIELD_OF_VIEW_X") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_X");
            param.setDisplayedName("Location X");
            param.setDescription("Off-center distance  in the R/L direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Y("OFF_CENTER_FIELD_OF_VIEW_Y") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Y");
            param.setDisplayedName("Location Y");
            param.setDescription("Off-center distance in the A/P direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_Z("OFF_CENTER_FIELD_OF_VIEW_Z") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Z");
            param.setDisplayedName("Location Z");
            param.setDescription("Off-center distance in the I/S direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ORIENTATION("ORIENTATION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("ORIENTATION");
            param.setDisplayedName("Orientation");
            param.setDescription("Field of view orientation");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("AXIAL");
            param.setDefaultValue("AXIAL");
            param.setSuggestedValues(asList("AXIAL", "CORONAL", "SAGITTAL", "OBLIQUE"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    PAROPT_PARAM("PAROPT_PARAM") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("PAROPT_PARAM");
            param.setDisplayedName("Parameter optimised");
            param.setDescription("Name of the current optimised parameter");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("PULSE_LENGTH");
            return param;
        }
    },

    PARTIAL_OVERSAMPLING("PARTIAL_OVERSAMPLING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PARTIAL_OVERSAMPLING");
            param.setDisplayedName("PARTIAL_OVERSAMPLING");
            param.setDescription("");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PHASE_0("PHASE_0") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_0");
            param.setDisplayedName("PHASE_0");
            param.setDescription("0th order phase");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_1("PHASE_1") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_1");
            param.setDisplayedName("PHASE_1");
            param.setDescription("1st order phase");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_APPLIED("PHASE_APPLIED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_APPLIED");
            param.setDisplayedName("PHASE_APPLIED");
            param.setDescription("Phase application applied");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PHASE_FIELD_OF_VIEW_RATIO("PHASE_FIELD_OF_VIEW_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("PHASE_FIELD_OF_VIEW_RATIO");
            param.setDisplayedName("Phase FOV Ratio");
            param.setDescription("Info: Ratio between the phase-encoded FOV and the frequency-encoded FOV in % ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    PHASE_RESET("PHASE_RESET") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_RESET");
            param.setDisplayedName("PHASE_RESET");
            param.setDescription("Enable electronic phase reset at the beginning of the scan");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PREPHASING_READ_GRADIENT_RATIO("PREPHASING_READ_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDisplayedName("Gradient Prephasing Ratio Read");
            param.setDescription("Prephasing reading gradient ratio");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    PROBE("PROBE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("PROBE");
            param.setDisplayedName("Probe");
            param.setDescription("The probe used for the mr acquisition");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    PROBES("PROBES") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("PROBES");
            param.setDisplayedName("Probes");
            param.setDescription("Name of probes used for the transmit and reception");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("4CH 18cm"));
            return param;
        }
    },

    RECEIVER_COUNT("RECEIVER_COUNT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_COUNT");
            param.setDisplayedName("No. Receivers");
            param.setDescription("Info: Number of reception channels");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(32);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    RECEIVER_GAIN("RECEIVER_GAIN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_GAIN");
            param.setDisplayedName("RG");
            param.setDescription("Receiver Gain");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.RxGain);
            param.setMinValue(0.0);
            param.setMaxValue(120.0);
            param.setValue(42.0);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    REPETITION_TIME("REPETITION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("REPETITION_TIME");
            param.setDisplayedName("TR");
            param.setDescription("Repetition Time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.126);
            param.setDefaultValue(0.2);
            return param;
        }
    },

    RESOLUTION_FREQUENCY("RESOLUTION_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_FREQUENCY");
            param.setDisplayedName("Voxel Size Read");
            param.setDescription("Info: True voxel size in the frequency encoding direction (FOV/acquisition_matrix_dimension_1D) ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(2.5E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_PHASE("RESOLUTION_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_PHASE");
            param.setDisplayedName("Voxel Size Phase");
            param.setDescription("Info:True voxel in the phase encoding direction (FOV/acquisition_matrix_dimension_2D) ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(2.5E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_SLICE("RESOLUTION_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_SLICE");
            param.setDisplayedName("Voxel Size Slice/3D");
            param.setDescription("Info: True voxel size in the third direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.1);
            param.setValue(0.002);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    RF_SPOILING("RF_SPOILING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("RF_SPOILING");
            param.setDisplayedName("RF-Spoiling");
            param.setDescription("Enable the RF spoiling by cycling RF phase in the sequence");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_DISTANCE_FROM_FOV("SATBAND_DISTANCE_FROM_FOV") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_DISTANCE_FROM_FOV");
            param.setDisplayedName("SatBand - Distance from FOV");
            param.setDescription("Distance of the Saturation Band from the FOV ");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-0.1);
            param.setMaxValue(0.1);
            param.setValue(0.005);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_ENABLED("SATBAND_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SATBAND_ENABLED");
            param.setDisplayedName("Saturation Band");
            param.setDescription("Enable Saturation Band");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_GRAD_AMP_SPOILER("SATBAND_GRAD_AMP_SPOILER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_GRAD_AMP_SPOILER");
            param.setDisplayedName("SatBand - Gradient Amplitude");
            param.setDescription("Amplitude of the spoiler gradient following the saturation pulse. ");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(30.0);
            param.setMaxValue(100.0);
            param.setValue(40.0);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    SATBAND_ORIENTATION("SATBAND_ORIENTATION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_ORIENTATION");
            param.setDisplayedName("SatBand - Orientation");
            param.setDescription("Orientation of the Saturation Band");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("ALL");
            param.setDefaultValue("CRANIAL");
            param.setSuggestedValues(asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL", "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR", "RIGHT", "LEFT", "RIGHT AND LEFT", "ALL"));
            return param;
        }
    },

    SATBAND_T1("SATBAND_T1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_T1");
            param.setDisplayedName("SatBand - Target T1");
            param.setDescription("T1 of the saturated tissue : the longitudinal magnetization will be nulled when the excitation pulse is applied.");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Millis);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(500.0);
            param.setDefaultValue(500.0);
            return param;
        }
    },

    SATBAND_THICKNESS("SATBAND_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_THICKNESS");
            param.setDisplayedName("SatBand - Thickness");
            param.setDescription("Thickness of the Saturation Band");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(0.1);
            param.setValue(0.01);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    SATBAND_TX_AMP("SATBAND_TX_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_TX_AMP");
            param.setDisplayedName("SatBand - TX Amplitude");
            param.setDescription("Info: Saturation Band Pulse amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(23.73213791818464);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_TX_SHAPE("SATBAND_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_TX_SHAPE");
            param.setDisplayedName("SatBand - TX Shape");
            param.setDescription("Saturation Band Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("SINC3");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576", "RAMP"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SEQUENCE_NAME("SEQUENCE_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_NAME");
            param.setDisplayedName("Sequence Name");
            param.setDescription("Info: Name of the sequence");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("TOF");
            param.setDefaultValue("TOF");
            return param;
        }
    },

    SEQUENCE_TIME("SEQUENCE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SEQUENCE_TIME");
            param.setDisplayedName("Sequence Version");
            param.setDescription("Info: Total acquisition time");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(1290.244);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SEQUENCE_VERSION("SEQUENCE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_VERSION");
            param.setDisplayedName("Sequence Version");
            param.setDescription("Info: Name of the sequence");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setValue("Version x1.7");
            param.setDefaultValue("");
            return param;
        }
    },

    SEQ_DESCRIPTION("SEQ_DESCRIPTION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQ_DESCRIPTION");
            param.setDisplayedName("Sequence Description");
            param.setDescription("Info: Description of the sequence");
            param.setCategory(Category.Acquisition);
            param.setValue("GE_3D_AXI_256x256x10x4_TOFSAT_FATSAT");
            param.setDefaultValue("");
            return param;
        }
    },

    SETUP_MODE("SETUP_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SETUP_MODE");
            param.setDisplayedName("SETUP_MODE");
            param.setDescription("True during setup process");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SLAB_OVERLAP("SLAB_OVERLAP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLAB_OVERLAP");
            param.setDisplayedName("Slab Overlap");
            param.setDescription("Percentage of the slab volume overlapped with one adjacent slab");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(20.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO("SLICE_REFOCUSING_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("Gradient Refocusing Ratio Slice ");
            param.setDescription("Ratio of the slice refocusing gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.5);
            param.setDefaultValue(0.5);
            return param;
        }
    },

    SLICE_THICKNESS("SLICE_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_THICKNESS");
            param.setDisplayedName("Slice Thickness");
            param.setDescription("Slice Thickness");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(5.0E-5);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.002);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    SOFTWARE_VERSION("SOFTWARE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SOFTWARE_VERSION");
            param.setDisplayedName("Software Version");
            param.setDescription("Info: Software version");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("PRim 2021.06.2");
            param.setDefaultValue("Spinlab 2020.06.1");
            return param;
        }
    },

    SPACING_BETWEEN_SLAB("SPACING_BETWEEN_SLAB") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLAB");
            param.setDisplayedName("Slab Spacing");
            param.setDescription("Info: Space between slabs");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.LengthOffset);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.004);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SPACING_BETWEEN_SLICE("SPACING_BETWEEN_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLICE");
            param.setDisplayedName("Slice Spacing");
            param.setDescription("Gap between slice");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.LengthOffset);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    SPECTRAL_WIDTH("SPECTRAL_WIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH");
            param.setDisplayedName("BW");
            param.setDescription("Receiver bandwidth (SW, BW, bandwidth )");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(64036.88524590164);
            param.setDefaultValue(12500.0);
            return param;
        }
    },

    SPECTRAL_WIDTH_OPT("SPECTRAL_WIDTH_OPT") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SPECTRAL_WIDTH_OPT");
            param.setDisplayedName("BW Option");
            param.setDescription("Use BW to calculate BW per pixel (Check) / Use  BW per pixel to calculate BW (Uncheck)");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SPECTRAL_WIDTH_PER_PIXEL("SPECTRAL_WIDTH_PER_PIXEL") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDisplayedName("BW/px");
            param.setDescription("Bandwidth per pixel ");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(250.1440829918033);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SQUARE_PIXEL("SQUARE_PIXEL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SQUARE_PIXEL");
            param.setDisplayedName("Square Pixel");
            param.setDescription("Enable to have same pixel dimension in frequency and phase encoding direction, this will adjust Acquisition matrix 2D size");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    STATION_NAME("STATION_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("STATION_NAME");
            param.setDisplayedName("Station Name");
            param.setDescription("Station name");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Station name");
            param.setDefaultValue("Station name");
            return param;
        }
    },

    SUBJECT_POSITION("SUBJECT_POSITION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SUBJECT_POSITION");
            param.setDisplayedName("Subject Position");
            param.setDescription("Subject position relative to the magnet");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("HeadFirstProne");
            param.setDefaultValue("FeetFirstProne");
            param.setSuggestedValues(asList("HeadFirstProne", "HeadFirstSupine", "FeetFirstProne", "FeetFirstSupine"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SWITCH_READ_PHASE("SWITCH_READ_PHASE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SWITCH_READ_PHASE");
            param.setDisplayedName("Switch Read/Phase");
            param.setDescription("Switch Read and phase encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SWITCH_READ_SLICE("SWITCH_READ_SLICE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SWITCH_READ_SLICE");
            param.setDisplayedName("Switch Read/Slice");
            param.setDescription("Switch the read and slice (partition) encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_FLOW_TAU("TOF2D_FLOW_TAU") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_FLOW_TAU");
            param.setDisplayedName("TOF Flow - Delay");
            param.setDescription("Delay right in front of excitation pulse to allow the blood flow from satband through imaging slice/volume");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.999999999999999E-6);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF2D_FLOW_VELOCITY("TOF2D_FLOW_VELOCITY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_FLOW_VELOCITY");
            param.setDisplayedName("TOF Flow - Velocity");
            param.setDescription("Average velocity of (blood) flow");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.12);
            param.setDefaultValue(0.3);
            return param;
        }
    },

    TOF2D_MT_CALI("TOF2D_MT_CALI") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF2D_MT_CALI");
            param.setDisplayedName("TOF2D_MT_CALI");
            param.setDescription("");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_SB_DISTANCE_FROM_SLICE("TOF2D_SB_DISTANCE_FROM_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_DISTANCE_FROM_SLICE");
            param.setDisplayedName("TOF 2D Satband - Distance From Slice");
            param.setDescription("Distance of the TOF satband from the active imaging slice in 2D acquisition");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.005);
            param.setDefaultValue(0.002);
            return param;
        }
    },

    TOF2D_SB_GRAMP_SP("TOF2D_SB_GRAMP_SP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_GRAMP_SP");
            param.setDisplayedName("TOF Sat - Spoiler Grad Amp");
            param.setDescription("Amplitude of the spoiler gradient following the TOF satband RF pulse. ");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(60.0);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    TOF2D_SB_POSITION("TOF2D_SB_POSITION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TOF2D_SB_POSITION");
            param.setDisplayedName("TOF 2D Satband -  Position");
            param.setDescription("TOF Satband position relative to imaging slices in 2D acquisition");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("ABOVE THE SLICE");
            param.setDefaultValue("BELOW THE SLICE");
            param.setSuggestedValues(asList("ABOVE THE SLICE", "BELOW THE SLICE"));
            return param;
        }
    },

    TOF2D_SB_THICKNESS("TOF2D_SB_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_THICKNESS");
            param.setDisplayedName("TOF 2D SatBand - Thickness");
            param.setDescription("Thickness of the TOF saturation band in 2D acquisition");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.001);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    TOF3D_EXT_SHIRNK_FACTOR("TOF3D_EXT_SHIRNK_FACTOR") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_EXT_SHIRNK_FACTOR");
            param.setDisplayedName("TOF Slab Shrink Factor");
            param.setDescription("Shrink factor for TOF slabs");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(50.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_MT_BANDWIDTH("TOF3D_MT_BANDWIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_BANDWIDTH");
            param.setDisplayedName("TOF 3D MT Sat - Bandwidth");
            param.setDescription("Bandwidth of TOF MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(200.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_MT_FLIP_ANGLE("TOF3D_MT_FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_FLIP_ANGLE");
            param.setDisplayedName("TOF3D_MT_FLIP_ANGLE");
            param.setDescription("Info: nominal flip angle of TOF 3D MT saturation pulse");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.RotationAngle);
            param.setMinValue(4.9E-324);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(1152.0);
            param.setDefaultValue(360.0);
            return param;
        }
    },

    TOF3D_MT_GAMMA_B1("TOF3D_MT_GAMMA_B1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_GAMMA_B1");
            param.setDisplayedName("TOF 3D MT Sat - Gamma B1");
            param.setDescription("Gamma B1 value of TOF 3D MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(200.0);
            param.setDefaultValue(50.0);
            return param;
        }
    },

    TOF3D_MT_INDIV("TOF3D_MT_INDIV") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF3D_MT_INDIV");
            param.setDisplayedName("TOF 3D MT Sat - One Sat Per Slab");
            param.setDescription("True if running only one saturation pulse per slab");
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF3D_MT_TX_LENGTH("TOF3D_MT_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_TX_LENGTH");
            param.setDisplayedName("TOF 3D MT Sat - Tx Length");
            param.setDescription("Info :uration of TOF 3D MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.016);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_TX_RAMP_SLOPE("TOF3D_TX_RAMP_SLOPE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_TX_RAMP_SLOPE");
            param.setDisplayedName("TOF TX Ramp Slope");
            param.setDescription("Ramp slope of TONE pulse used in TOF");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(2.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF_ENABLED("TOF_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF_ENABLED");
            param.setDisplayedName("TOF Enable");
            param.setDescription("Enable TOF acquisition with stationary tissue saturation");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF_SB_OFFSET("TOF_SB_OFFSET") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF_SB_OFFSET");
            param.setDisplayedName("TOF Sat - Offset Frequency");
            param.setDescription("Additional global requency offset applied to all TOF satband RF pulses");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            param.setSuggestedValues(asListNumber(-1500.0, -1250.0, -1000.0, -750.0, -500.0, 0.0, 500.0, 750.0, 1000.0, 1250.0, 1500.0));
            return param;
        }
    },

    TOF_SB_TX_SHAPE("TOF_SB_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TOF_SB_TX_SHAPE");
            param.setDisplayedName("TOF Sat - TX Shape");
            param.setDescription("TOF satband RF pulse shape");
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576", "RAMP"));
            return param;
        }
    },

    TRAJ_MATRIX("TRAJ_MATRIX") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRAJ_MATRIX");
            param.setDisplayedName("TRAJ_MATRIX");
            param.setDescription(" acquisition dimension");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Scan);
            param.setValue(asListNumber(64, 64, 64, 1));
            return param;
        }
    },

    TRAJ_POSITION("TRAJ_POSITION") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRAJ_POSITION");
            param.setDisplayedName("TRAJ_POSITION");
            param.setDescription("2D 3D position");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Scan);
            param.setValue(asListNumber(1, 25, 1, 26, 1, 27, 1, 28, 1, 29, 1, 30, 1, 31, 1, 32, 1, 33, 1, 34, 1, 35, 1, 36, 1, 37, 1, 38, 1, 39, 1, 40, 2, 22, 2, 23, 2, 24, 2, 25, 2, 26, 2, 27, 2, 28, 2, 29, 2, 30, 2, 31, 2, 32, 2, 33, 2, 34, 2, 35, 2, 36, 2, 37, 2, 38, 2, 39, 2, 40, 2, 41, 2, 42, 2, 43, 3, 19, 3, 20, 3, 21, 3, 22, 3, 23, 3, 24, 3, 25, 3, 26, 3, 27, 3, 28, 3, 29, 3, 30, 3, 31, 3, 32, 3, 33, 3, 34, 3, 35, 3, 36, 3, 37, 3, 38, 3, 39, 3, 40, 3, 41, 3, 42, 3, 43, 3, 44, 3, 45, 3, 46, 4, 17, 4, 18, 4, 19, 4, 20, 4, 21, 4, 22, 4, 23, 4, 24, 4, 25, 4, 26, 4, 27, 4, 28, 4, 29, 4, 30, 4, 31, 4, 32, 4, 33, 4, 34, 4, 35, 4, 36, 4, 37, 4, 38, 4, 39, 4, 40, 4, 41, 4, 42, 4, 43, 4, 44, 4, 45, 4, 46, 4, 47, 4, 48, 5, 16, 5, 17, 5, 18, 5, 19, 5, 20, 5, 21, 5, 22, 5, 23, 5, 24, 5, 25, 5, 26, 5, 27, 5, 28, 5, 29, 5, 30, 5, 31, 5, 32, 5, 33, 5, 34, 5, 35, 5, 36, 5, 37, 5, 38, 5, 39, 5, 40, 5, 41, 5, 42, 5, 43, 5, 44, 5, 45, 5, 46, 5, 47, 5, 48, 5, 49, 6, 14, 6, 15, 6, 16, 6, 17, 6, 18, 6, 19, 6, 20, 6, 21, 6, 22, 6, 23, 6, 24, 6, 25, 6, 26, 6, 27, 6, 28, 6, 29, 6, 30, 6, 31, 6, 32, 6, 33, 6, 34, 6, 35, 6, 36, 6, 37, 6, 38, 6, 39, 6, 40, 6, 41, 6, 42, 6, 43, 6, 44, 6, 45, 6, 46, 6, 47, 6, 48, 6, 49, 6, 50, 6, 51, 7, 13, 7, 14, 7, 15, 7, 16, 7, 17, 7, 18, 7, 19, 7, 20, 7, 21, 7, 22, 7, 23, 7, 24, 7, 25, 7, 26, 7, 27, 7, 28, 7, 29, 7, 30, 7, 31, 7, 32, 7, 33, 7, 34, 7, 35, 7, 36, 7, 37, 7, 38, 7, 39, 7, 40, 7, 41, 7, 42, 7, 43, 7, 44, 7, 45, 7, 46, 7, 47, 7, 48, 7, 49, 7, 50, 7, 51, 7, 52, 8, 12, 8, 13, 8, 14, 8, 15, 8, 16, 8, 17, 8, 18, 8, 19, 8, 20, 8, 21, 8, 22, 8, 23, 8, 24, 8, 25, 8, 26, 8, 27, 8, 28, 8, 29, 8, 30, 8, 31, 8, 32, 8, 33, 8, 34, 8, 35, 8, 36, 8, 37, 8, 38, 8, 39, 8, 40, 8, 41, 8, 42, 8, 43, 8, 44, 8, 45, 8, 46, 8, 47, 8, 48, 8, 49, 8, 50, 8, 51, 8, 52, 8, 53, 9, 11, 9, 12, 9, 13, 9, 14, 9, 15, 9, 16, 9, 17, 9, 18, 9, 19, 9, 20, 9, 21, 9, 22, 9, 23, 9, 24, 9, 25, 9, 26, 9, 27, 9, 28, 9, 29, 9, 30, 9, 31, 9, 32, 9, 33, 9, 34, 9, 35, 9, 36, 9, 37, 9, 38, 9, 39, 9, 40, 9, 41, 9, 42, 9, 43, 9, 44, 9, 45, 9, 46, 9, 47, 9, 48, 9, 49, 9, 50, 9, 51, 9, 52, 9, 53, 9, 54, 10, 10, 10, 11, 10, 12, 10, 13, 10, 14, 10, 15, 10, 16, 10, 17, 10, 18, 10, 19, 10, 20, 10, 21, 10, 22, 10, 23, 10, 24, 10, 25, 10, 26, 10, 27, 10, 28, 10, 29, 10, 30, 10, 31, 10, 32, 10, 33, 10, 34, 10, 35, 10, 36, 10, 37, 10, 38, 10, 39, 10, 40, 10, 41, 10, 42, 10, 43, 10, 44, 10, 45, 10, 46, 10, 47, 10, 48, 10, 49, 10, 50, 10, 51, 10, 52, 10, 53, 10, 54, 10, 55, 11, 9, 11, 10, 11, 11, 11, 12, 11, 13, 11, 14, 11, 15, 11, 16, 11, 17, 11, 18, 11, 19, 11, 20, 11, 21, 11, 22, 11, 23, 11, 24, 11, 25, 11, 26, 11, 27, 11, 28, 11, 29, 11, 30, 11, 31, 11, 32, 11, 33, 11, 34, 11, 35, 11, 36, 11, 37, 11, 38, 11, 39, 11, 40, 11, 41, 11, 42, 11, 43, 11, 44, 11, 45, 11, 46, 11, 47, 11, 48, 11, 49, 11, 50, 11, 51, 11, 52, 11, 53, 11, 54, 11, 55, 11, 56, 12, 8, 12, 9, 12, 10, 12, 11, 12, 12, 12, 13, 12, 14, 12, 15, 12, 16, 12, 17, 12, 18, 12, 19, 12, 20, 12, 21, 12, 22, 12, 23, 12, 24, 12, 25, 12, 26, 12, 27, 12, 28, 12, 29, 12, 30, 12, 31, 12, 32, 12, 33, 12, 34, 12, 35, 12, 36, 12, 37, 12, 38, 12, 39, 12, 40, 12, 41, 12, 42, 12, 43, 12, 44, 12, 45, 12, 46, 12, 47, 12, 48, 12, 49, 12, 50, 12, 51, 12, 52, 12, 53, 12, 54, 12, 55, 12, 56, 12, 57, 13, 7, 13, 8, 13, 9, 13, 10, 13, 11, 13, 12, 13, 13, 13, 14, 13, 15, 13, 16, 13, 17, 13, 18, 13, 19, 13, 20, 13, 21, 13, 22, 13, 23, 13, 24, 13, 25, 13, 26, 13, 27, 13, 28, 13, 29, 13, 30, 13, 31, 13, 32, 13, 33, 13, 34, 13, 35, 13, 36, 13, 37, 13, 38, 13, 39, 13, 40, 13, 41, 13, 42, 13, 43, 13, 44, 13, 45, 13, 46, 13, 47, 13, 48, 13, 49, 13, 50, 13, 51, 13, 52, 13, 53, 13, 54, 13, 55, 13, 56, 13, 57, 13, 58, 14, 6, 14, 7, 14, 8, 14, 9, 14, 10, 14, 11, 14, 12, 14, 13, 14, 14, 14, 15, 14, 16, 14, 17, 14, 18, 14, 19, 14, 20, 14, 21, 14, 22, 14, 23, 14, 24, 14, 25, 14, 26, 14, 27, 14, 28, 14, 29, 14, 30, 14, 31, 14, 32, 14, 33, 14, 34, 14, 35, 14, 36, 14, 37, 14, 38, 14, 39, 14, 40, 14, 41, 14, 42, 14, 43, 14, 44, 14, 45, 14, 46, 14, 47, 14, 48, 14, 49, 14, 50, 14, 51, 14, 52, 14, 53, 14, 54, 14, 55, 14, 56, 14, 57, 14, 58, 14, 59, 15, 6, 15, 7, 15, 8, 15, 9, 15, 10, 15, 11, 15, 12, 15, 13, 15, 14, 15, 15, 15, 16, 15, 17, 15, 18, 15, 19, 15, 20, 15, 21, 15, 22, 15, 23, 15, 24, 15, 25, 15, 26, 15, 27, 15, 28, 15, 29, 15, 30, 15, 31, 15, 32, 15, 33, 15, 34, 15, 35, 15, 36, 15, 37, 15, 38, 15, 39, 15, 40, 15, 41, 15, 42, 15, 43, 15, 44, 15, 45, 15, 46, 15, 47, 15, 48, 15, 49, 15, 50, 15, 51, 15, 52, 15, 53, 15, 54, 15, 55, 15, 56, 15, 57, 15, 58, 15, 59, 16, 5, 16, 6, 16, 7, 16, 8, 16, 9, 16, 10, 16, 11, 16, 12, 16, 13, 16, 14, 16, 15, 16, 16, 16, 17, 16, 18, 16, 19, 16, 20, 16, 21, 16, 22, 16, 23, 16, 24, 16, 25, 16, 26, 16, 27, 16, 28, 16, 29, 16, 30, 16, 31, 16, 32, 16, 33, 16, 34, 16, 35, 16, 36, 16, 37, 16, 38, 16, 39, 16, 40, 16, 41, 16, 42, 16, 43, 16, 44, 16, 45, 16, 46, 16, 47, 16, 48, 16, 49, 16, 50, 16, 51, 16, 52, 16, 53, 16, 54, 16, 55, 16, 56, 16, 57, 16, 58, 16, 59, 16, 60, 17, 4, 17, 5, 17, 6, 17, 7, 17, 8, 17, 9, 17, 10, 17, 11, 17, 12, 17, 13, 17, 14, 17, 15, 17, 16, 17, 17, 17, 18, 17, 19, 17, 20, 17, 21, 17, 22, 17, 23, 17, 24, 17, 25, 17, 26, 17, 27, 17, 28, 17, 29, 17, 30, 17, 31, 17, 32, 17, 33, 17, 34, 17, 35, 17, 36, 17, 37, 17, 38, 17, 39, 17, 40, 17, 41, 17, 42, 17, 43, 17, 44, 17, 45, 17, 46, 17, 47, 17, 48, 17, 49, 17, 50, 17, 51, 17, 52, 17, 53, 17, 54, 17, 55, 17, 56, 17, 57, 17, 58, 17, 59, 17, 60, 17, 61, 18, 4, 18, 5, 18, 6, 18, 7, 18, 8, 18, 9, 18, 10, 18, 11, 18, 12, 18, 13, 18, 14, 18, 15, 18, 16, 18, 17, 18, 18, 18, 19, 18, 20, 18, 21, 18, 22, 18, 23, 18, 24, 18, 25, 18, 26, 18, 27, 18, 28, 18, 29, 18, 30, 18, 31, 18, 32, 18, 33, 18, 34, 18, 35, 18, 36, 18, 37, 18, 38, 18, 39, 18, 40, 18, 41, 18, 42, 18, 43, 18, 44, 18, 45, 18, 46, 18, 47, 18, 48, 18, 49, 18, 50, 18, 51, 18, 52, 18, 53, 18, 54, 18, 55, 18, 56, 18, 57, 18, 58, 18, 59, 18, 60, 18, 61, 19, 3, 19, 4, 19, 5, 19, 6, 19, 7, 19, 8, 19, 9, 19, 10, 19, 11, 19, 12, 19, 13, 19, 14, 19, 15, 19, 16, 19, 17, 19, 18, 19, 19, 19, 20, 19, 21, 19, 22, 19, 23, 19, 24, 19, 25, 19, 26, 19, 27, 19, 28, 19, 29, 19, 30, 19, 31, 19, 32, 19, 33, 19, 34, 19, 35, 19, 36, 19, 37, 19, 38, 19, 39, 19, 40, 19, 41, 19, 42, 19, 43, 19, 44, 19, 45, 19, 46, 19, 47, 19, 48, 19, 49, 19, 50, 19, 51, 19, 52, 19, 53, 19, 54, 19, 55, 19, 56, 19, 57, 19, 58, 19, 59, 19, 60, 19, 61, 19, 62, 20, 3, 20, 4, 20, 5, 20, 6, 20, 7, 20, 8, 20, 9, 20, 10, 20, 11, 20, 12, 20, 13, 20, 14, 20, 15, 20, 16, 20, 17, 20, 18, 20, 19, 20, 20, 20, 21, 20, 22, 20, 23, 20, 24, 20, 25, 20, 26, 20, 27, 20, 28, 20, 29, 20, 30, 20, 31, 20, 32, 20, 33, 20, 34, 20, 35, 20, 36, 20, 37, 20, 38, 20, 39, 20, 40, 20, 41, 20, 42, 20, 43, 20, 44, 20, 45, 20, 46, 20, 47, 20, 48, 20, 49, 20, 50, 20, 51, 20, 52, 20, 53, 20, 54, 20, 55, 20, 56, 20, 57, 20, 58, 20, 59, 20, 60, 20, 61, 20, 62, 21, 3, 21, 4, 21, 5, 21, 6, 21, 7, 21, 8, 21, 9, 21, 10, 21, 11, 21, 12, 21, 13, 21, 14, 21, 15, 21, 16, 21, 17, 21, 18, 21, 19, 21, 20, 21, 21, 21, 22, 21, 23, 21, 24, 21, 25, 21, 26, 21, 27, 21, 28, 21, 29, 21, 30, 21, 31, 21, 32, 21, 33, 21, 34, 21, 35, 21, 36, 21, 37, 21, 38, 21, 39, 21, 40, 21, 41, 21, 42, 21, 43, 21, 44, 21, 45, 21, 46, 21, 47, 21, 48, 21, 49, 21, 50, 21, 51, 21, 52, 21, 53, 21, 54, 21, 55, 21, 56, 21, 57, 21, 58, 21, 59, 21, 60, 21, 61, 21, 62, 22, 2, 22, 3, 22, 4, 22, 5, 22, 6, 22, 7, 22, 8, 22, 9, 22, 10, 22, 11, 22, 12, 22, 13, 22, 14, 22, 15, 22, 16, 22, 17, 22, 18, 22, 19, 22, 20, 22, 21, 22, 22, 22, 23, 22, 24, 22, 25, 22, 26, 22, 27, 22, 28, 22, 29, 22, 30, 22, 31, 22, 32, 22, 33, 22, 34, 22, 35, 22, 36, 22, 37, 22, 38, 22, 39, 22, 40, 22, 41, 22, 42, 22, 43, 22, 44, 22, 45, 22, 46, 22, 47, 22, 48, 22, 49, 22, 50, 22, 51, 22, 52, 22, 53, 22, 54, 22, 55, 22, 56, 22, 57, 22, 58, 22, 59, 22, 60, 22, 61, 22, 62, 22, 63, 23, 2, 23, 3, 23, 4, 23, 5, 23, 6, 23, 7, 23, 8, 23, 9, 23, 10, 23, 11, 23, 12, 23, 13, 23, 14, 23, 15, 23, 16, 23, 17, 23, 18, 23, 19, 23, 20, 23, 21, 23, 22, 23, 23, 23, 24, 23, 25, 23, 26, 23, 27, 23, 28, 23, 29, 23, 30, 23, 31, 23, 32, 23, 33, 23, 34, 23, 35, 23, 36, 23, 37, 23, 38, 23, 39, 23, 40, 23, 41, 23, 42, 23, 43, 23, 44, 23, 45, 23, 46, 23, 47, 23, 48, 23, 49, 23, 50, 23, 51, 23, 52, 23, 53, 23, 54, 23, 55, 23, 56, 23, 57, 23, 58, 23, 59, 23, 60, 23, 61, 23, 62, 23, 63, 24, 2, 24, 3, 24, 4, 24, 5, 24, 6, 24, 7, 24, 8, 24, 9, 24, 10, 24, 11, 24, 12, 24, 13, 24, 14, 24, 15, 24, 16, 24, 17, 24, 18, 24, 19, 24, 20, 24, 21, 24, 22, 24, 23, 24, 24, 24, 25, 24, 26, 24, 27, 24, 28, 24, 29, 24, 30, 24, 31, 24, 32, 24, 33, 24, 34, 24, 35, 24, 36, 24, 37, 24, 38, 24, 39, 24, 40, 24, 41, 24, 42, 24, 43, 24, 44, 24, 45, 24, 46, 24, 47, 24, 48, 24, 49, 24, 50, 24, 51, 24, 52, 24, 53, 24, 54, 24, 55, 24, 56, 24, 57, 24, 58, 24, 59, 24, 60, 24, 61, 24, 62, 24, 63, 25, 1, 25, 2, 25, 3, 25, 4, 25, 5, 25, 6, 25, 7, 25, 8, 25, 9, 25, 10, 25, 11, 25, 12, 25, 13, 25, 14, 25, 15, 25, 16, 25, 17, 25, 18, 25, 19, 25, 20, 25, 21, 25, 22, 25, 23, 25, 24, 25, 25, 25, 26, 25, 27, 25, 28, 25, 29, 25, 30, 25, 31, 25, 32, 25, 33, 25, 34, 25, 35, 25, 36, 25, 37, 25, 38, 25, 39, 25, 40, 25, 41, 25, 42, 25, 43, 25, 44, 25, 45, 25, 46, 25, 47, 25, 48, 25, 49, 25, 50, 25, 51, 25, 52, 25, 53, 25, 54, 25, 55, 25, 56, 25, 57, 25, 58, 25, 59, 25, 60, 25, 61, 25, 62, 25, 63, 26, 1, 26, 2, 26, 3, 26, 4, 26, 5, 26, 6, 26, 7, 26, 8, 26, 9, 26, 10, 26, 11, 26, 12, 26, 13, 26, 14, 26, 15, 26, 16, 26, 17, 26, 18, 26, 19, 26, 20, 26, 21, 26, 22, 26, 23, 26, 24, 26, 25, 26, 26, 26, 27, 26, 28, 26, 29, 26, 30, 26, 31, 26, 32, 26, 33, 26, 34, 26, 35, 26, 36, 26, 37, 26, 38, 26, 39, 26, 40, 26, 41, 26, 42, 26, 43, 26, 44, 26, 45, 26, 46, 26, 47, 26, 48, 26, 49, 26, 50, 26, 51, 26, 52, 26, 53, 26, 54, 26, 55, 26, 56, 26, 57, 26, 58, 26, 59, 26, 60, 26, 61, 26, 62, 26, 63, 27, 1, 27, 2, 27, 3, 27, 4, 27, 5, 27, 6, 27, 7, 27, 8, 27, 9, 27, 10, 27, 11, 27, 12, 27, 13, 27, 14, 27, 15, 27, 16, 27, 17, 27, 18, 27, 19, 27, 20, 27, 21, 27, 22, 27, 23, 27, 24, 27, 25, 27, 26, 27, 27, 27, 28, 27, 29, 27, 30, 27, 31, 27, 32, 27, 33, 27, 34, 27, 35, 27, 36, 27, 37, 27, 38, 27, 39, 27, 40, 27, 41, 27, 42, 27, 43, 27, 44, 27, 45, 27, 46, 27, 47, 27, 48, 27, 49, 27, 50, 27, 51, 27, 52, 27, 53, 27, 54, 27, 55, 27, 56, 27, 57, 27, 58, 27, 59, 27, 60, 27, 61, 27, 62, 27, 63, 28, 1, 28, 2, 28, 3, 28, 4, 28, 5, 28, 6, 28, 7, 28, 8, 28, 9, 28, 10, 28, 11, 28, 12, 28, 13, 28, 14, 28, 15, 28, 16, 28, 17, 28, 18, 28, 19, 28, 20, 28, 21, 28, 22, 28, 23, 28, 24, 28, 25, 28, 26, 28, 27, 28, 28, 28, 29, 28, 30, 28, 31, 28, 32, 28, 33, 28, 34, 28, 35, 28, 36, 28, 37, 28, 38, 28, 39, 28, 40, 28, 41, 28, 42, 28, 43, 28, 44, 28, 45, 28, 46, 28, 47, 28, 48, 28, 49, 28, 50, 28, 51, 28, 52, 28, 53, 28, 54, 28, 55, 28, 56, 28, 57, 28, 58, 28, 59, 28, 60, 28, 61, 28, 62, 28, 63, 29, 1, 29, 2, 29, 3, 29, 4, 29, 5, 29, 6, 29, 7, 29, 8, 29, 9, 29, 10, 29, 11, 29, 12, 29, 13, 29, 14, 29, 15, 29, 16, 29, 17, 29, 18, 29, 19, 29, 20, 29, 21, 29, 22, 29, 23, 29, 24, 29, 25, 29, 26, 29, 27, 29, 28, 29, 29, 29, 30, 29, 31, 29, 32, 29, 33, 29, 34, 29, 35, 29, 36, 29, 37, 29, 38, 29, 39, 29, 40, 29, 41, 29, 42, 29, 43, 29, 44, 29, 45, 29, 46, 29, 47, 29, 48, 29, 49, 29, 50, 29, 51, 29, 52, 29, 53, 29, 54, 29, 55, 29, 56, 29, 57, 29, 58, 29, 59, 29, 60, 29, 61, 29, 62, 29, 63, 30, 1, 30, 2, 30, 3, 30, 4, 30, 5, 30, 6, 30, 7, 30, 8, 30, 9, 30, 10, 30, 11, 30, 12, 30, 13, 30, 14, 30, 15, 30, 16, 30, 17, 30, 18, 30, 19, 30, 20, 30, 21, 30, 22, 30, 23, 30, 24, 30, 25, 30, 26, 30, 27, 30, 28, 30, 29, 30, 30, 30, 31, 30, 32, 30, 33, 30, 34, 30, 35, 30, 36, 30, 37, 30, 38, 30, 39, 30, 40, 30, 41, 30, 42, 30, 43, 30, 44, 30, 45, 30, 46, 30, 47, 30, 48, 30, 49, 30, 50, 30, 51, 30, 52, 30, 53, 30, 54, 30, 55, 30, 56, 30, 57, 30, 58, 30, 59, 30, 60, 30, 61, 30, 62, 30, 63, 31, 1, 31, 2, 31, 3, 31, 4, 31, 5, 31, 6, 31, 7, 31, 8, 31, 9, 31, 10, 31, 11, 31, 12, 31, 13, 31, 14, 31, 15, 31, 16, 31, 17, 31, 18, 31, 19, 31, 20, 31, 21, 31, 22, 31, 23, 31, 24, 31, 25, 31, 26, 31, 27, 31, 28, 31, 29, 31, 30, 31, 31, 31, 32, 31, 33, 31, 34, 31, 35, 31, 36, 31, 37, 31, 38, 31, 39, 31, 40, 31, 41, 31, 42, 31, 43, 31, 44, 31, 45, 31, 46, 31, 47, 31, 48, 31, 49, 31, 50, 31, 51, 31, 52, 31, 53, 31, 54, 31, 55, 31, 56, 31, 57, 31, 58, 31, 59, 31, 60, 31, 61, 31, 62, 31, 63, 32, 1, 32, 2, 32, 3, 32, 4, 32, 5, 32, 6, 32, 7, 32, 8, 32, 9, 32, 10, 32, 11, 32, 12, 32, 13, 32, 14, 32, 15, 32, 16, 32, 17, 32, 18, 32, 19, 32, 20, 32, 21, 32, 22, 32, 23, 32, 24, 32, 25, 32, 26, 32, 27, 32, 28, 32, 29, 32, 30, 32, 31, 32, 32, 32, 33, 32, 34, 32, 35, 32, 36, 32, 37, 32, 38, 32, 39, 32, 40, 32, 41, 32, 42, 32, 43, 32, 44, 32, 45, 32, 46, 32, 47, 32, 48, 32, 49, 32, 50, 32, 51, 32, 52, 32, 53, 32, 54, 32, 55, 32, 56, 32, 57, 32, 58, 32, 59, 32, 60, 32, 61, 32, 62, 32, 63, 33, 1, 33, 2, 33, 3, 33, 4, 33, 5, 33, 6, 33, 7, 33, 8, 33, 9, 33, 10, 33, 11, 33, 12, 33, 13, 33, 14, 33, 15, 33, 16, 33, 17, 33, 18, 33, 19, 33, 20, 33, 21, 33, 22, 33, 23, 33, 24, 33, 25, 33, 26, 33, 27, 33, 28, 33, 29, 33, 30, 33, 31, 33, 32, 33, 33, 33, 34, 33, 35, 33, 36, 33, 37, 33, 38, 33, 39, 33, 40, 33, 41, 33, 42, 33, 43, 33, 44, 33, 45, 33, 46, 33, 47, 33, 48, 33, 49, 33, 50, 33, 51, 33, 52, 33, 53, 33, 54, 33, 55, 33, 56, 33, 57, 33, 58, 33, 59, 33, 60, 33, 61, 33, 62, 33, 63, 34, 1, 34, 2, 34, 3, 34, 4, 34, 5, 34, 6, 34, 7, 34, 8, 34, 9, 34, 10, 34, 11, 34, 12, 34, 13, 34, 14, 34, 15, 34, 16, 34, 17, 34, 18, 34, 19, 34, 20, 34, 21, 34, 22, 34, 23, 34, 24, 34, 25, 34, 26, 34, 27, 34, 28, 34, 29, 34, 30, 34, 31, 34, 32, 34, 33, 34, 34, 34, 35, 34, 36, 34, 37, 34, 38, 34, 39, 34, 40, 34, 41, 34, 42, 34, 43, 34, 44, 34, 45, 34, 46, 34, 47, 34, 48, 34, 49, 34, 50, 34, 51, 34, 52, 34, 53, 34, 54, 34, 55, 34, 56, 34, 57, 34, 58, 34, 59, 34, 60, 34, 61, 34, 62, 34, 63, 35, 1, 35, 2, 35, 3, 35, 4, 35, 5, 35, 6, 35, 7, 35, 8, 35, 9, 35, 10, 35, 11, 35, 12, 35, 13, 35, 14, 35, 15, 35, 16, 35, 17, 35, 18, 35, 19, 35, 20, 35, 21, 35, 22, 35, 23, 35, 24, 35, 25, 35, 26, 35, 27, 35, 28, 35, 29, 35, 30, 35, 31, 35, 32, 35, 33, 35, 34, 35, 35, 35, 36, 35, 37, 35, 38, 35, 39, 35, 40, 35, 41, 35, 42, 35, 43, 35, 44, 35, 45, 35, 46, 35, 47, 35, 48, 35, 49, 35, 50, 35, 51, 35, 52, 35, 53, 35, 54, 35, 55, 35, 56, 35, 57, 35, 58, 35, 59, 35, 60, 35, 61, 35, 62, 35, 63, 36, 1, 36, 2, 36, 3, 36, 4, 36, 5, 36, 6, 36, 7, 36, 8, 36, 9, 36, 10, 36, 11, 36, 12, 36, 13, 36, 14, 36, 15, 36, 16, 36, 17, 36, 18, 36, 19, 36, 20, 36, 21, 36, 22, 36, 23, 36, 24, 36, 25, 36, 26, 36, 27, 36, 28, 36, 29, 36, 30, 36, 31, 36, 32, 36, 33, 36, 34, 36, 35, 36, 36, 36, 37, 36, 38, 36, 39, 36, 40, 36, 41, 36, 42, 36, 43, 36, 44, 36, 45, 36, 46, 36, 47, 36, 48, 36, 49, 36, 50, 36, 51, 36, 52, 36, 53, 36, 54, 36, 55, 36, 56, 36, 57, 36, 58, 36, 59, 36, 60, 36, 61, 36, 62, 36, 63, 37, 1, 37, 2, 37, 3, 37, 4, 37, 5, 37, 6, 37, 7, 37, 8, 37, 9, 37, 10, 37, 11, 37, 12, 37, 13, 37, 14, 37, 15, 37, 16, 37, 17, 37, 18, 37, 19, 37, 20, 37, 21, 37, 22, 37, 23, 37, 24, 37, 25, 37, 26, 37, 27, 37, 28, 37, 29, 37, 30, 37, 31, 37, 32, 37, 33, 37, 34, 37, 35, 37, 36, 37, 37, 37, 38, 37, 39, 37, 40, 37, 41, 37, 42, 37, 43, 37, 44, 37, 45, 37, 46, 37, 47, 37, 48, 37, 49, 37, 50, 37, 51, 37, 52, 37, 53, 37, 54, 37, 55, 37, 56, 37, 57, 37, 58, 37, 59, 37, 60, 37, 61, 37, 62, 37, 63, 38, 1, 38, 2, 38, 3, 38, 4, 38, 5, 38, 6, 38, 7, 38, 8, 38, 9, 38, 10, 38, 11, 38, 12, 38, 13, 38, 14, 38, 15, 38, 16, 38, 17, 38, 18, 38, 19, 38, 20, 38, 21, 38, 22, 38, 23, 38, 24, 38, 25, 38, 26, 38, 27, 38, 28, 38, 29, 38, 30, 38, 31, 38, 32, 38, 33, 38, 34, 38, 35, 38, 36, 38, 37, 38, 38, 38, 39, 38, 40, 38, 41, 38, 42, 38, 43, 38, 44, 38, 45, 38, 46, 38, 47, 38, 48, 38, 49, 38, 50, 38, 51, 38, 52, 38, 53, 38, 54, 38, 55, 38, 56, 38, 57, 38, 58, 38, 59, 38, 60, 38, 61, 38, 62, 38, 63, 39, 1, 39, 2, 39, 3, 39, 4, 39, 5, 39, 6, 39, 7, 39, 8, 39, 9, 39, 10, 39, 11, 39, 12, 39, 13, 39, 14, 39, 15, 39, 16, 39, 17, 39, 18, 39, 19, 39, 20, 39, 21, 39, 22, 39, 23, 39, 24, 39, 25, 39, 26, 39, 27, 39, 28, 39, 29, 39, 30, 39, 31, 39, 32, 39, 33, 39, 34, 39, 35, 39, 36, 39, 37, 39, 38, 39, 39, 39, 40, 39, 41, 39, 42, 39, 43, 39, 44, 39, 45, 39, 46, 39, 47, 39, 48, 39, 49, 39, 50, 39, 51, 39, 52, 39, 53, 39, 54, 39, 55, 39, 56, 39, 57, 39, 58, 39, 59, 39, 60, 39, 61, 39, 62, 39, 63, 40, 1, 40, 2, 40, 3, 40, 4, 40, 5, 40, 6, 40, 7, 40, 8, 40, 9, 40, 10, 40, 11, 40, 12, 40, 13, 40, 14, 40, 15, 40, 16, 40, 17, 40, 18, 40, 19, 40, 20, 40, 21, 40, 22, 40, 23, 40, 24, 40, 25, 40, 26, 40, 27, 40, 28, 40, 29, 40, 30, 40, 31, 40, 32, 40, 33, 40, 34, 40, 35, 40, 36, 40, 37, 40, 38, 40, 39, 40, 40, 40, 41, 40, 42, 40, 43, 40, 44, 40, 45, 40, 46, 40, 47, 40, 48, 40, 49, 40, 50, 40, 51, 40, 52, 40, 53, 40, 54, 40, 55, 40, 56, 40, 57, 40, 58, 40, 59, 40, 60, 40, 61, 40, 62, 40, 63, 41, 2, 41, 3, 41, 4, 41, 5, 41, 6, 41, 7, 41, 8, 41, 9, 41, 10, 41, 11, 41, 12, 41, 13, 41, 14, 41, 15, 41, 16, 41, 17, 41, 18, 41, 19, 41, 20, 41, 21, 41, 22, 41, 23, 41, 24, 41, 25, 41, 26, 41, 27, 41, 28, 41, 29, 41, 30, 41, 31, 41, 32, 41, 33, 41, 34, 41, 35, 41, 36, 41, 37, 41, 38, 41, 39, 41, 40, 41, 41, 41, 42, 41, 43, 41, 44, 41, 45, 41, 46, 41, 47, 41, 48, 41, 49, 41, 50, 41, 51, 41, 52, 41, 53, 41, 54, 41, 55, 41, 56, 41, 57, 41, 58, 41, 59, 41, 60, 41, 61, 41, 62, 41, 63, 42, 2, 42, 3, 42, 4, 42, 5, 42, 6, 42, 7, 42, 8, 42, 9, 42, 10, 42, 11, 42, 12, 42, 13, 42, 14, 42, 15, 42, 16, 42, 17, 42, 18, 42, 19, 42, 20, 42, 21, 42, 22, 42, 23, 42, 24, 42, 25, 42, 26, 42, 27, 42, 28, 42, 29, 42, 30, 42, 31, 42, 32, 42, 33, 42, 34, 42, 35, 42, 36, 42, 37, 42, 38, 42, 39, 42, 40, 42, 41, 42, 42, 42, 43, 42, 44, 42, 45, 42, 46, 42, 47, 42, 48, 42, 49, 42, 50, 42, 51, 42, 52, 42, 53, 42, 54, 42, 55, 42, 56, 42, 57, 42, 58, 42, 59, 42, 60, 42, 61, 42, 62, 42, 63, 43, 2, 43, 3, 43, 4, 43, 5, 43, 6, 43, 7, 43, 8, 43, 9, 43, 10, 43, 11, 43, 12, 43, 13, 43, 14, 43, 15, 43, 16, 43, 17, 43, 18, 43, 19, 43, 20, 43, 21, 43, 22, 43, 23, 43, 24, 43, 25, 43, 26, 43, 27, 43, 28, 43, 29, 43, 30, 43, 31, 43, 32, 43, 33, 43, 34, 43, 35, 43, 36, 43, 37, 43, 38, 43, 39, 43, 40, 43, 41, 43, 42, 43, 43, 43, 44, 43, 45, 43, 46, 43, 47, 43, 48, 43, 49, 43, 50, 43, 51, 43, 52, 43, 53, 43, 54, 43, 55, 43, 56, 43, 57, 43, 58, 43, 59, 43, 60, 43, 61, 43, 62, 43, 63, 44, 3, 44, 4, 44, 5, 44, 6, 44, 7, 44, 8, 44, 9, 44, 10, 44, 11, 44, 12, 44, 13, 44, 14, 44, 15, 44, 16, 44, 17, 44, 18, 44, 19, 44, 20, 44, 21, 44, 22, 44, 23, 44, 24, 44, 25, 44, 26, 44, 27, 44, 28, 44, 29, 44, 30, 44, 31, 44, 32, 44, 33, 44, 34, 44, 35, 44, 36, 44, 37, 44, 38, 44, 39, 44, 40, 44, 41, 44, 42, 44, 43, 44, 44, 44, 45, 44, 46, 44, 47, 44, 48, 44, 49, 44, 50, 44, 51, 44, 52, 44, 53, 44, 54, 44, 55, 44, 56, 44, 57, 44, 58, 44, 59, 44, 60, 44, 61, 44, 62, 45, 3, 45, 4, 45, 5, 45, 6, 45, 7, 45, 8, 45, 9, 45, 10, 45, 11, 45, 12, 45, 13, 45, 14, 45, 15, 45, 16, 45, 17, 45, 18, 45, 19, 45, 20, 45, 21, 45, 22, 45, 23, 45, 24, 45, 25, 45, 26, 45, 27, 45, 28, 45, 29, 45, 30, 45, 31, 45, 32, 45, 33, 45, 34, 45, 35, 45, 36, 45, 37, 45, 38, 45, 39, 45, 40, 45, 41, 45, 42, 45, 43, 45, 44, 45, 45, 45, 46, 45, 47, 45, 48, 45, 49, 45, 50, 45, 51, 45, 52, 45, 53, 45, 54, 45, 55, 45, 56, 45, 57, 45, 58, 45, 59, 45, 60, 45, 61, 45, 62, 46, 3, 46, 4, 46, 5, 46, 6, 46, 7, 46, 8, 46, 9, 46, 10, 46, 11, 46, 12, 46, 13, 46, 14, 46, 15, 46, 16, 46, 17, 46, 18, 46, 19, 46, 20, 46, 21, 46, 22, 46, 23, 46, 24, 46, 25, 46, 26, 46, 27, 46, 28, 46, 29, 46, 30, 46, 31, 46, 32, 46, 33, 46, 34, 46, 35, 46, 36, 46, 37, 46, 38, 46, 39, 46, 40, 46, 41, 46, 42, 46, 43, 46, 44, 46, 45, 46, 46, 46, 47, 46, 48, 46, 49, 46, 50, 46, 51, 46, 52, 46, 53, 46, 54, 46, 55, 46, 56, 46, 57, 46, 58, 46, 59, 46, 60, 46, 61, 46, 62, 47, 4, 47, 5, 47, 6, 47, 7, 47, 8, 47, 9, 47, 10, 47, 11, 47, 12, 47, 13, 47, 14, 47, 15, 47, 16, 47, 17, 47, 18, 47, 19, 47, 20, 47, 21, 47, 22, 47, 23, 47, 24, 47, 25, 47, 26, 47, 27, 47, 28, 47, 29, 47, 30, 47, 31, 47, 32, 47, 33, 47, 34, 47, 35, 47, 36, 47, 37, 47, 38, 47, 39, 47, 40, 47, 41, 47, 42, 47, 43, 47, 44, 47, 45, 47, 46, 47, 47, 47, 48, 47, 49, 47, 50, 47, 51, 47, 52, 47, 53, 47, 54, 47, 55, 47, 56, 47, 57, 47, 58, 47, 59, 47, 60, 47, 61, 48, 4, 48, 5, 48, 6, 48, 7, 48, 8, 48, 9, 48, 10, 48, 11, 48, 12, 48, 13, 48, 14, 48, 15, 48, 16, 48, 17, 48, 18, 48, 19, 48, 20, 48, 21, 48, 22, 48, 23, 48, 24, 48, 25, 48, 26, 48, 27, 48, 28, 48, 29, 48, 30, 48, 31, 48, 32, 48, 33, 48, 34, 48, 35, 48, 36, 48, 37, 48, 38, 48, 39, 48, 40, 48, 41, 48, 42, 48, 43, 48, 44, 48, 45, 48, 46, 48, 47, 48, 48, 48, 49, 48, 50, 48, 51, 48, 52, 48, 53, 48, 54, 48, 55, 48, 56, 48, 57, 48, 58, 48, 59, 48, 60, 48, 61, 49, 5, 49, 6, 49, 7, 49, 8, 49, 9, 49, 10, 49, 11, 49, 12, 49, 13, 49, 14, 49, 15, 49, 16, 49, 17, 49, 18, 49, 19, 49, 20, 49, 21, 49, 22, 49, 23, 49, 24, 49, 25, 49, 26, 49, 27, 49, 28, 49, 29, 49, 30, 49, 31, 49, 32, 49, 33, 49, 34, 49, 35, 49, 36, 49, 37, 49, 38, 49, 39, 49, 40, 49, 41, 49, 42, 49, 43, 49, 44, 49, 45, 49, 46, 49, 47, 49, 48, 49, 49, 49, 50, 49, 51, 49, 52, 49, 53, 49, 54, 49, 55, 49, 56, 49, 57, 49, 58, 49, 59, 49, 60, 50, 6, 50, 7, 50, 8, 50, 9, 50, 10, 50, 11, 50, 12, 50, 13, 50, 14, 50, 15, 50, 16, 50, 17, 50, 18, 50, 19, 50, 20, 50, 21, 50, 22, 50, 23, 50, 24, 50, 25, 50, 26, 50, 27, 50, 28, 50, 29, 50, 30, 50, 31, 50, 32, 50, 33, 50, 34, 50, 35, 50, 36, 50, 37, 50, 38, 50, 39, 50, 40, 50, 41, 50, 42, 50, 43, 50, 44, 50, 45, 50, 46, 50, 47, 50, 48, 50, 49, 50, 50, 50, 51, 50, 52, 50, 53, 50, 54, 50, 55, 50, 56, 50, 57, 50, 58, 50, 59, 51, 6, 51, 7, 51, 8, 51, 9, 51, 10, 51, 11, 51, 12, 51, 13, 51, 14, 51, 15, 51, 16, 51, 17, 51, 18, 51, 19, 51, 20, 51, 21, 51, 22, 51, 23, 51, 24, 51, 25, 51, 26, 51, 27, 51, 28, 51, 29, 51, 30, 51, 31, 51, 32, 51, 33, 51, 34, 51, 35, 51, 36, 51, 37, 51, 38, 51, 39, 51, 40, 51, 41, 51, 42, 51, 43, 51, 44, 51, 45, 51, 46, 51, 47, 51, 48, 51, 49, 51, 50, 51, 51, 51, 52, 51, 53, 51, 54, 51, 55, 51, 56, 51, 57, 51, 58, 51, 59, 52, 7, 52, 8, 52, 9, 52, 10, 52, 11, 52, 12, 52, 13, 52, 14, 52, 15, 52, 16, 52, 17, 52, 18, 52, 19, 52, 20, 52, 21, 52, 22, 52, 23, 52, 24, 52, 25, 52, 26, 52, 27, 52, 28, 52, 29, 52, 30, 52, 31, 52, 32, 52, 33, 52, 34, 52, 35, 52, 36, 52, 37, 52, 38, 52, 39, 52, 40, 52, 41, 52, 42, 52, 43, 52, 44, 52, 45, 52, 46, 52, 47, 52, 48, 52, 49, 52, 50, 52, 51, 52, 52, 52, 53, 52, 54, 52, 55, 52, 56, 52, 57, 52, 58, 53, 8, 53, 9, 53, 10, 53, 11, 53, 12, 53, 13, 53, 14, 53, 15, 53, 16, 53, 17, 53, 18, 53, 19, 53, 20, 53, 21, 53, 22, 53, 23, 53, 24, 53, 25, 53, 26, 53, 27, 53, 28, 53, 29, 53, 30, 53, 31, 53, 32, 53, 33, 53, 34, 53, 35, 53, 36, 53, 37, 53, 38, 53, 39, 53, 40, 53, 41, 53, 42, 53, 43, 53, 44, 53, 45, 53, 46, 53, 47, 53, 48, 53, 49, 53, 50, 53, 51, 53, 52, 53, 53, 53, 54, 53, 55, 53, 56, 53, 57, 54, 9, 54, 10, 54, 11, 54, 12, 54, 13, 54, 14, 54, 15, 54, 16, 54, 17, 54, 18, 54, 19, 54, 20, 54, 21, 54, 22, 54, 23, 54, 24, 54, 25, 54, 26, 54, 27, 54, 28, 54, 29, 54, 30, 54, 31, 54, 32, 54, 33, 54, 34, 54, 35, 54, 36, 54, 37, 54, 38, 54, 39, 54, 40, 54, 41, 54, 42, 54, 43, 54, 44, 54, 45, 54, 46, 54, 47, 54, 48, 54, 49, 54, 50, 54, 51, 54, 52, 54, 53, 54, 54, 54, 55, 54, 56, 55, 10, 55, 11, 55, 12, 55, 13, 55, 14, 55, 15, 55, 16, 55, 17, 55, 18, 55, 19, 55, 20, 55, 21, 55, 22, 55, 23, 55, 24, 55, 25, 55, 26, 55, 27, 55, 28, 55, 29, 55, 30, 55, 31, 55, 32, 55, 33, 55, 34, 55, 35, 55, 36, 55, 37, 55, 38, 55, 39, 55, 40, 55, 41, 55, 42, 55, 43, 55, 44, 55, 45, 55, 46, 55, 47, 55, 48, 55, 49, 55, 50, 55, 51, 55, 52, 55, 53, 55, 54, 55, 55, 56, 11, 56, 12, 56, 13, 56, 14, 56, 15, 56, 16, 56, 17, 56, 18, 56, 19, 56, 20, 56, 21, 56, 22, 56, 23, 56, 24, 56, 25, 56, 26, 56, 27, 56, 28, 56, 29, 56, 30, 56, 31, 56, 32, 56, 33, 56, 34, 56, 35, 56, 36, 56, 37, 56, 38, 56, 39, 56, 40, 56, 41, 56, 42, 56, 43, 56, 44, 56, 45, 56, 46, 56, 47, 56, 48, 56, 49, 56, 50, 56, 51, 56, 52, 56, 53, 56, 54, 57, 12, 57, 13, 57, 14, 57, 15, 57, 16, 57, 17, 57, 18, 57, 19, 57, 20, 57, 21, 57, 22, 57, 23, 57, 24, 57, 25, 57, 26, 57, 27, 57, 28, 57, 29, 57, 30, 57, 31, 57, 32, 57, 33, 57, 34, 57, 35, 57, 36, 57, 37, 57, 38, 57, 39, 57, 40, 57, 41, 57, 42, 57, 43, 57, 44, 57, 45, 57, 46, 57, 47, 57, 48, 57, 49, 57, 50, 57, 51, 57, 52, 57, 53, 58, 13, 58, 14, 58, 15, 58, 16, 58, 17, 58, 18, 58, 19, 58, 20, 58, 21, 58, 22, 58, 23, 58, 24, 58, 25, 58, 26, 58, 27, 58, 28, 58, 29, 58, 30, 58, 31, 58, 32, 58, 33, 58, 34, 58, 35, 58, 36, 58, 37, 58, 38, 58, 39, 58, 40, 58, 41, 58, 42, 58, 43, 58, 44, 58, 45, 58, 46, 58, 47, 58, 48, 58, 49, 58, 50, 58, 51, 58, 52, 59, 14, 59, 15, 59, 16, 59, 17, 59, 18, 59, 19, 59, 20, 59, 21, 59, 22, 59, 23, 59, 24, 59, 25, 59, 26, 59, 27, 59, 28, 59, 29, 59, 30, 59, 31, 59, 32, 59, 33, 59, 34, 59, 35, 59, 36, 59, 37, 59, 38, 59, 39, 59, 40, 59, 41, 59, 42, 59, 43, 59, 44, 59, 45, 59, 46, 59, 47, 59, 48, 59, 49, 59, 50, 59, 51, 60, 16, 60, 17, 60, 18, 60, 19, 60, 20, 60, 21, 60, 22, 60, 23, 60, 24, 60, 25, 60, 26, 60, 27, 60, 28, 60, 29, 60, 30, 60, 31, 60, 32, 60, 33, 60, 34, 60, 35, 60, 36, 60, 37, 60, 38, 60, 39, 60, 40, 60, 41, 60, 42, 60, 43, 60, 44, 60, 45, 60, 46, 60, 47, 60, 48, 60, 49, 61, 17, 61, 18, 61, 19, 61, 20, 61, 21, 61, 22, 61, 23, 61, 24, 61, 25, 61, 26, 61, 27, 61, 28, 61, 29, 61, 30, 61, 31, 61, 32, 61, 33, 61, 34, 61, 35, 61, 36, 61, 37, 61, 38, 61, 39, 61, 40, 61, 41, 61, 42, 61, 43, 61, 44, 61, 45, 61, 46, 61, 47, 61, 48, 62, 19, 62, 20, 62, 21, 62, 22, 62, 23, 62, 24, 62, 25, 62, 26, 62, 27, 62, 28, 62, 29, 62, 30, 62, 31, 62, 32, 62, 33, 62, 34, 62, 35, 62, 36, 62, 37, 62, 38, 62, 39, 62, 40, 62, 41, 62, 42, 62, 43, 62, 44, 62, 45, 62, 46, 63, 22, 63, 23, 63, 24, 63, 25, 63, 26, 63, 27, 63, 28, 63, 29, 63, 30, 63, 31, 63, 32, 63, 33, 63, 34, 63, 35, 63, 36, 63, 37, 63, 38, 63, 39, 63, 40, 63, 41, 63, 42, 63, 43));
            return param;
        }
    },

    TRANSFORM_PLUGIN("TRANSFORM_PLUGIN") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TRANSFORM_PLUGIN");
            param.setDisplayedName("Transform Plugin");
            param.setDescription("Info :Transform the acquisition space to the k-space. ");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("Sequential4D");
            param.setDefaultValue("Sequential4D");
            param.setSuggestedValues(asList("Sequential4D", "Elliptical3D", "Sequential4D_TOF"));
            return param;
        }
    },

    TRIGGER_EXTERNAL("TRIGGER_EXTERNAL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("Trigger External ");
            param.setDescription("Enable to synchronize the acquisition with external trigger");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRIGGER_TIME("TRIGGER_TIME") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRIGGER_TIME");
            param.setDisplayedName("Trigger Delay");
            param.setDescription("Delays between trigger and acquisition start. (Multiple values)");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0, 0.05, 0.09));
            param.setDefaultValue(asListNumber(0.01));
            return param;
        }
    },

    TX_AMP("TX_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP");
            param.setDisplayedName("TX Amplitude");
            param.setDescription("Amplitude of the excitation RF pulse ");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(82.75962538077337);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    TX_AMP_180("TX_AMP_180") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_180");
            param.setDisplayedName("TX_AMP_180");
            param.setDescription("The magnitude of the RF pulse 180");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_90("TX_AMP_90") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_90");
            param.setDisplayedName("TX_AMP_90");
            param.setDescription("Amplitude of the transmitter");
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(82.75962538077337);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_ATT_AUTO("TX_AMP_ATT_AUTO") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_AMP_ATT_AUTO");
            param.setDisplayedName("TX Att/Amp Auto");
            param.setDescription("Enable to automatically set attenuation and amplitude for the RF according to the calibration");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    TX_ATT("TX_ATT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_ATT");
            param.setDisplayedName("TX Attenuation");
            param.setDescription("RF pulse attenuation");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0.0);
            param.setMaxValue(63.0);
            param.setValue(29.0);
            param.setDefaultValue(36.0);
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR("TX_BANDWIDTH_FACTOR") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR");
            param.setDisplayedName("TX BW Factor");
            param.setDescription("Bandwidth factor of the RF pulse for 4 shapes: 1-HARD, 2-GAUSSIAN, 3-SINC3 and 4-SINC5");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(0.95, 1.35, 2.55, 4.25, 0.0, 0.0, 4.25));
            param.setDefaultValue(asListNumber(0.95, 1.35, 2.55, 4.25, 0.0, 0.0, 4.25));
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR_3D("TX_BANDWIDTH_FACTOR_3D") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR_3D");
            param.setDisplayedName("TX BW Factor 3D");
            param.setDescription("Bandwidth factor 3D of the RF pulse for 4 shapes: 1-HARD, 2-GAUSSIAN, 3-SINC3 and 4-SINC5");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(1.1, 3.2, 5.0, 7.3, 0.0, 0.0, 7.3));
            param.setDefaultValue(asListNumber(1.1, 3.2, 5.0, 7.3, 0.0, 0.0, 7.3));
            return param;
        }
    },

    TX_LENGTH("TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH");
            param.setDisplayedName("TX Length");
            param.setDescription("RF pulse length");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(7.999999999999999E-4);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_ROUTE("TX_ROUTE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_ROUTE");
            param.setDisplayedName("TX Route");
            param.setDescription("Info: Selected physical transmit channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0));
            return param;
        }
    },

    TX_SHAPE("TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE");
            param.setDisplayedName("TX Shape");
            param.setDescription("RF Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("RAMP");
            param.setDefaultValue("GAUSSIAN");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576", "RAMP"));
            return param;
        }
    },

    USER_MATRIX_DIMENSION_1D("USER_MATRIX_DIMENSION_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Matrix 1D Read");
            param.setDescription("Image size in readout direction (user matrix dimension 1D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_2D("USER_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Matrix 2D Phase");
            param.setDescription("Image size in phase encoding direction  (user matrix dimension 2D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(256);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_3D("USER_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Matrix 3D / No. Slices");
            param.setDescription("Image size in the third dimension: slice or 3D PE  (user matrix dimension 3D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(10);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_4D("USER_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_4D");
            param.setDisplayedName("No. Data Sets");
            param.setDescription("Info: Number of image data set (user maxtrix 4D)");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_PARTIAL_PHASE("USER_PARTIAL_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_PHASE");
            param.setDisplayedName("Zero-filling Phase");
            param.setDescription("Percent of the k-space that is acquired, the rest will be completed by zeros in the phase encoding direction (Zero-filling interpolation)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_PARTIAL_SLICE("USER_PARTIAL_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_SLICE");
            param.setDisplayedName("Zero-filling 3D");
            param.setDescription("Percent of the k-space that is acquired, the rest will be completed by zeros in the third encoding direction (only for 3D acquisition mode)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_ZERO_FILLING_2D("USER_ZERO_FILLING_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_2D");
            param.setDisplayedName("ZERO_FILING_2D");
            param.setDescription("Info: Percentage of zero filling (=1 - Zero-filling Phase)");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_ZERO_FILLING_3D("USER_ZERO_FILLING_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_3D");
            param.setDisplayedName("ZERO_FILING_3D");
            param.setDescription("Info: Percentage of zero filling (=1 - Zero-filling 3D)");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    };

    //--

    private final String name;

    private U(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    //--

    private static List<Number> asListNumber(Number ... numbers) {
        return asList(numbers);
    }
}