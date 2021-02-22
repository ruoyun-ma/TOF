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
            param.setDisplayedName("ACCU_DIM.name");
            param.setDescription("ACCU_DIM.description");
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
            param.setDisplayedName("Acquisition 1D");
            param.setDescription("The acquisition size of the first dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(64);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_2D("ACQUISITION_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Acquisition 2D");
            param.setDescription("The acquisition size of the second dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(50);
            param.setDefaultValue(128);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_3D("ACQUISITION_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Acquisition 3D");
            param.setDescription("The acquisition size of the third dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(20);
            param.setDefaultValue(1);
            return param;
        }
    },

    ACQUISITION_MATRIX_DIMENSION_4D("ACQUISITION_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDisplayedName("Acquisition 4D");
            param.setDescription("The acquisition size of the fourth dimension");
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

    ACQUISITION_MODE("ACQUISITION_MODE") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("ACQUISITION_MODE");
            param.setDisplayedName("ACQUISITION_MODE");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET("ACQUISITION_TIME_OFFSET") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("ACQUISITION_TIME_OFFSET");
            param.setDescription("Relative acquisition start times in Dynamic or MultiSeries scans");
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
            param.setDisplayedName("Acq Time");
            param.setDescription("The acquisition time per scan  ( SPECTRAL_WIDTH )");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.005111808000000001);
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
            param.setValue(1.2818100000000001E8);
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
            param.setDisplayedName("Digital filter removed");
            param.setDescription("Data shift due to the digital filter are removed");
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
            param.setDisplayedName("Digital filter shift");
            param.setDescription("Data shift due to the digital filter");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setValue(20);
            param.setDefaultValue(20);
            return param;
        }
    },

    DUMMY_SCAN("DUMMY_SCAN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DUMMY_SCAN");
            param.setDisplayedName("DS");
            param.setDescription("Dummy Scan");
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
            param.setDisplayedName("DYNAMIC_MIN_TIME");
            param.setDescription("Execute the dynamic scan without delay between repetition");
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
            param.setDisplayedName("DYNAMIC_SEQUENCE");
            param.setDescription("Sequence used for dynamic acquisitions");
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
            param.setDisplayedName("DYN_NUMBER_OF_ACQUISITION");
            param.setDescription("");
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
            param.setDisplayedName("TIME_BETWEEN_FRAMES");
            param.setDescription("The time between 4D acquisition");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(10.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    ECHO_SPACING("ECHO_SPACING") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_SPACING");
            param.setDisplayedName("ECHO_SPACING");
            param.setDescription("The delay between echo");
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
            param.setDescription("The echo time ( TE )");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.00381);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    ECHO_TRAIN_LENGTH("ECHO_TRAIN_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TRAIN_LENGTH");
            param.setDisplayedName("ETL");
            param.setDescription("The echo train lentgth");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    FATSAT_BANDWIDTH("FATSAT_BANDWIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_BANDWIDTH");
            param.setDisplayedName("FATSAT_BANDWIDTH");
            param.setDescription("");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(0.0);
            param.setMaxValue(2000.0);
            param.setValue(180.0);
            param.setDefaultValue(448.0);
            return param;
        }
    },

    FATSAT_FLIP_ANGLE("FATSAT_FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_FLIP_ANGLE");
            param.setDisplayedName("FATSAT_FLIP_ANGLE");
            param.setDescription("");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(0.0);
            param.setMaxValue(360.0);
            param.setValue(0.0);
            param.setDefaultValue(90.0);
            return param;
        }
    },

    FATSAT_GRAD_APP_TIME("FATSAT_GRAD_APP_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_GRAD_APP_TIME");
            param.setDisplayedName("FATSAT_GRAD_APP_TIME");
            param.setDescription("");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(1.0E-5);
            param.setDefaultValue(0.001);
            return param;
        }
    },

    FATSAT_OFFSET_FREQ("FATSAT_OFFSET_FREQ") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_OFFSET_FREQ");
            param.setDisplayedName("FATSAT_OFFSET_FREQ");
            param.setDescription("");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1500.0);
            param.setMaxValue(1500.0);
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
            param.setDescription("");
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
            param.setDescription("");
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
            param.setDisplayedName("FATSAT_TX_AMP_90");
            param.setDescription("");
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

    FATSAT_TX_LENGTH("FATSAT_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_LENGTH");
            param.setDisplayedName("FATSAT_TX_LENGTH");
            param.setDescription("");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.0E-6);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FATSAT_TX_SHAPE("FATSAT_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("FATSAT_TX_SHAPE");
            param.setDisplayedName("FATSAT_TX_SHAPE");
            param.setDescription("");
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
            param.setDisplayedName("FAT_SATURATION_ENABLED");
            param.setDescription("");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
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
            param.setDisplayedName("FOV");
            param.setDescription("The field of view");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.04);
            param.setDefaultValue(0.6);
            return param;
        }
    },

    FIELD_OF_VIEW_3D("FIELD_OF_VIEW_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_3D");
            param.setDisplayedName("FIELD_OF_VIEW_3D");
            param.setDescription("Field of View in the 3rd direction : total coverage");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(0.057999999999999996);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FIELD_OF_VIEW_PHASE("FIELD_OF_VIEW_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_PHASE");
            param.setDisplayedName("FIELD_OF_VIEW_PHASE");
            param.setDescription("Field of View in the phase encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.01);
            param.setMaxValue(10.0);
            param.setValue(0.04);
            param.setDefaultValue(0.08);
            return param;
        }
    },

    FLIP_ANGLE("FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLIP_ANGLE");
            param.setDisplayedName("Flip angle");
            param.setDescription("The flip angle for the excitation");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Angle);
            param.setMinValue(-360.0);
            param.setMaxValue(360.0);
            param.setValue(30.0);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    FLOWCOMP_DURATION("FLOWCOMP_DURATION") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLOWCOMP_DURATION");
            param.setDisplayedName("FLOWCOMP_DURATION");
            param.setDescription("");
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
            param.setDisplayedName("Flow compensation");
            param.setDescription("Use flow compensation");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FLYBACK("FLYBACK") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FLYBACK");
            param.setDisplayedName("FLYBACK");
            param.setDescription("Aquisition in the same direction in k space, fly back after every k space line");
            param.setGroup(EnumGroup.Scan);
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
            param.setDisplayedName("FOV_DOUBLED");
            param.setDescription("Double the FOV and SW");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_EFF("FOV_EFF") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_EFF");
            param.setDisplayedName("FOV_EFF");
            param.setDescription("Show the acquierd FOV when FOV doubled");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.04);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    FOV_RATIO_PHASE("FOV_RATIO_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_RATIO_PHASE");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction (2D/1D)");
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

    FOV_SQUARE("FOV_SQUARE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_SQUARE");
            param.setDisplayedName("FOV_SQUARE");
            param.setDescription("Impose FOV_PHASE= FOV");
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
            param.setDisplayedName("GRADIENT_ENABLE_PHASE");
            param.setDescription("enable the phase encoding gradient");
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
            param.setDisplayedName("GRADIENT_ENABLE_PHASE_3D");
            param.setDescription("enable the 3D phase encoding gradient");
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
            param.setDisplayedName("GRADIENT_ENABLE_READ");
            param.setDescription("enable the read encoding gradient");
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
            param.setDisplayedName("GRADIENT_ENABLE_REWINDING");
            param.setDescription("Do Gradient Rewind instead of Spoiling");
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
            param.setDisplayedName("GRADIENT_ENABLE_SLICE");
            param.setDescription("enable the slice encoding gradient");
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
            param.setDisplayedName("GRADIENT_ENABLE_SPOILER");
            param.setDescription("Enable gradient spoiler on the three direction");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_FLYBACK_TIME("GRADIENT_FLYBACK_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_FLYBACK_TIME");
            param.setDisplayedName("GRADIENT_FLYBACK_TIME");
            param.setDescription("");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(4.9999999999999996E-6);
            param.setMaxValue(1.0E9);
            param.setValue(0.005111808);
            param.setDefaultValue(4.9999999999999996E-6);
            return param;
        }
    },

    GRADIENT_PHASE_APPLICATION_TIME("GRADIENT_PHASE_APPLICATION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDisplayedName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDescription("the application time of the phase encoding gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(3.0E-4);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_RISE_TIME("GRADIENT_RISE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_RISE_TIME");
            param.setDisplayedName("GRADIENT_RISE_TIME");
            param.setDescription("The rise time of the gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.999999999999999E-5);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    GRADIENT_SPOILER_TIME("GRADIENT_SPOILER_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_SPOILER_TIME");
            param.setDisplayedName("Gradient spoiler top time");
            param.setDescription("");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(3.9999999999999996E-4);
            param.setDefaultValue(9.999999999999999E-5);
            return param;
        }
    },

    GRAD_AMP_SPOILER_SL_PH_RE("GRAD_AMP_SPOILER_SL_PH_RE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("GRAD_AMP_SPOILER_SL_PH_RE");
            param.setDisplayedName("GRAD_AMP_SPOILER_SL_PH_SL");
            param.setDescription("");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(10.0, 0.0, 0.0));
            return param;
        }
    },

    IMAGE_ORIENTATION_SUBJECT("IMAGE_ORIENTATION_SUBJECT") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_ORIENTATION_SUBJECT");
            param.setDisplayedName("IMAGE_ORIENTATION_SUBJECT");
            param.setDescription("Direction cosines of the first row and the first column with respect to the subject");
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
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
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
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(8.0E-4);
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
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.UnsignedShort);
            param.setMinValue(1);
            param.setMaxValue(65535);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    INTERMEDIATE_FREQUENCY("INTERMEDIATE_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERMEDIATE_FREQUENCY");
            param.setDisplayedName("INTERMEDIATE_FREQUENCY.name");
            param.setDescription("INTERMEDIATE_FREQUENCY.description");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(2.051E7);
            param.setDefaultValue(1.251E7);
            return param;
        }
    },

    KEYHOLE_ALLOWED("KEYHOLE_ALLOWED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KEYHOLE_ALLOWED");
            param.setDisplayedName("KEYHOLE_ALLOWED");
            param.setDescription("Allow keyhole: test the max amp of the phase encoding gradient for partial = 100%");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KSPACE_FILLING("KSPACE_FILLING") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("KSPACE_FILLING");
            param.setDisplayedName("KSPACE_FILLING");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setValue("Linear");
            param.setDefaultValue("Linear");
            param.setSuggestedValues(asList("Linear", "Centric"));
            return param;
        }
    },

    KS_CENTERED("KS_CENTERED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTERED");
            param.setDisplayedName("KS_CENTERED");
            param.setDescription("Center the k-space around k0(check) or go through k0");
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
            param.setDescription("Turn off the PE gradient and acquieres two single scan only");
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
            param.setDescription("LAST_PUT.description");
            param.setLocked(true);
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0, 0, 0));
            param.setDefaultValue(asListNumber(0, 0, 0));
            return param;
        }
    },

    MAGNETIC_FIELD_STRENGTH("MAGNETIC_FIELD_STRENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("MAGNETIC_FIELD_STRENGTH");
            param.setDisplayedName("B0");
            param.setDescription("The magnetic field tregth");
            param.setLockedToDefault(true);
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
            param.setDisplayedName("MANUFACTURER");
            param.setDescription("Manufacturer");
            param.setLockedToDefault(true);
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
            param.setDisplayedName("MIN_RISE_TIME_FACTOR");
            param.setDescription("");
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
            param.setLocked(true);
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
            param.setDisplayedName("MODEL_NAME");
            param.setDescription("Model name");
            param.setLocked(true);
            param.setLockedToDefault(true);
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
            param.setDisplayedName("MULTISERIES_PARAMETER_NAME");
            param.setDescription("Name of MultiSeries Parameter");
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
            param.setDisplayedName("MULTISERIES_PARAMETER_VALUE");
            param.setDescription("Values of MultiSeries Parameter");
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
            param.setDisplayedName("Multi planar");
            param.setDescription("Multi planar excitation");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
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
            param.setLocked(true);
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
            param.setLocked(true);
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
            param.setLocked(true);
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
            param.setDescription("The number of average");
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
            param.setDisplayedName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDescription("");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(20);
            param.setDefaultValue(0);
            return param;
        }
    },

    NUMBER_OF_SHOOT_3D("NUMBER_OF_SHOOT_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SHOOT_3D");
            param.setDisplayedName("Number of Shoot 3D");
            param.setDescription("Number of Slice set acquiered sequencially");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(1);
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
            param.setValue(1.2818100000000001E8);
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
            param.setLocked(true);
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
            param.setLocked(true);
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
            param.setLocked(true);
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
            param.setDescription("Offset in Readout Direction");
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
            param.setDescription("Offset in Phase Encoding Direction");
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
            param.setDescription("Offset in the slice direction");
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
            param.setDisplayedName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDescription("Offcenter effective in 1D 2D and 3D (read phase slice)");
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
            param.setDescription("Location in the R/L direction");
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
            param.setDescription("Location in the A/P direction");
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
            param.setDescription("Location in the I/S direction");
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
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    PHASE_0("PHASE_0") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_0");
            param.setDisplayedName("PHASE_0");
            param.setDescription("PHASE_0.description");
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
            param.setDescription("PHASE_1.description");
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
            param.setDescription("PHASE_APPLIED");
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
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction");
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

    PHASE_RESET("PHASE_RESET") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_RESET");
            param.setDisplayedName("PHASE_RESET");
            param.setDescription("");
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
            param.setDisplayedName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDescription("The prephasing reading gradient ratio");
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

    PROBES("PROBES") {
        public Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("PROBES");
            param.setDisplayedName("Probes");
            param.setDescription("The probes used for the acquisition");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("Mouse Body Coil"));
            return param;
        }
    },

    RECEIVER_COUNT("RECEIVER_COUNT") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_COUNT");
            param.setDisplayedName("Receiver Count");
            param.setDescription("The number of receivers");
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
            param.setDescription("The receiver gain");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.RxGain);
            param.setMinValue(0.0);
            param.setMaxValue(120.0);
            param.setValue(15.0);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    REPETITION_TIME("REPETITION_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("REPETITION_TIME");
            param.setDisplayedName("TR");
            param.setDescription("The repetition time ( TR )");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.202);
            param.setDefaultValue(0.2);
            return param;
        }
    },

    RESOLUTION_FREQUENCY("RESOLUTION_FREQUENCY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_FREQUENCY");
            param.setDisplayedName("RESOLUTION_FREQUENCY");
            param.setDescription("True Pixel dimension in the frequency encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(6.25E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_PHASE("RESOLUTION_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_PHASE");
            param.setDisplayedName("RESOLUTION_PHASE");
            param.setDescription("True pixel dimension in the phase encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(8.0E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    RESOLUTION_SLICE("RESOLUTION_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_SLICE");
            param.setDisplayedName("RESOLUTION_SLICE");
            param.setDescription("True pixel dimension in the 3rd direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.1);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    RF_SPOILING("RF_SPOILING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("RF_SPOILING");
            param.setDisplayedName("RF_SPOILING");
            param.setDescription("Enable the phase cycling in the sequence");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_DISTANCE_FROM_FOV("SATBAND_DISTANCE_FROM_FOV") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_DISTANCE_FROM_FOV");
            param.setDisplayedName("SATBAND_DISTANCE_FROM_FOV");
            param.setDescription("");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-0.1);
            param.setMaxValue(0.1);
            param.setValue(0.003);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_ENABLED("SATBAND_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SATBAND_ENABLED");
            param.setDisplayedName("SATBAND_ENABLED");
            param.setDescription("");
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
            param.setDisplayedName("SATBAND_GRAD_AMP_SPOILER");
            param.setDescription("Amplitude of the spoiler gradient after saturation pulse");
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
            param.setDisplayedName("SATBAND_ORIENTATION");
            param.setDescription("");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("CRANIAL");
            param.setDefaultValue("CRANIAL");
            param.setSuggestedValues(asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL", "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR", "RIGHT", "LEFT", "RIGHT AND LEFT", "ALL"));
            return param;
        }
    },

    SATBAND_T1("SATBAND_T1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_T1");
            param.setDisplayedName("SATBAND_T1");
            param.setDescription("");
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
            param.setDisplayedName("SATBAND_THICKNESS");
            param.setDescription("");
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
            param.setDisplayedName("SATBAND_TX_AMP");
            param.setDescription("");
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

    SATBAND_TX_SHAPE("SATBAND_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_TX_SHAPE");
            param.setDisplayedName("SATBAND_TX_SHAPE");
            param.setDescription("");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("HARD");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SEQUENCE_NAME("SEQUENCE_NAME") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_NAME");
            param.setDisplayedName("Seq");
            param.setDescription("The name of the sequence");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("GRADIENT_ECHO");
            param.setDefaultValue("GRADIENT_ECHO");
            return param;
        }
    },

    SEQUENCE_TIME("SEQUENCE_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SEQUENCE_TIME");
            param.setDisplayedName("SEQUENCE_TIME");
            param.setDescription("");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(10.2);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SEQUENCE_VERSION("SEQUENCE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_VERSION");
            param.setDisplayedName("SEQUENCE_VERSION");
            param.setDescription("");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setValue("Version7.4");
            param.setDefaultValue("");
            return param;
        }
    },

    SEQ_DESCRIPTION("SEQ_DESCRIPTION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SEQ_DESCRIPTION");
            param.setDisplayedName("SEQ_DESCRIPTION");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setValue("GE_2DAXI_64x50x20");
            param.setDefaultValue("");
            return param;
        }
    },

    SETUP_MODE("SETUP_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SETUP_MODE");
            param.setDisplayedName("Setup");
            param.setDescription("True during setup process");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO("SLICE_REFOCUSING_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDescription("The ratio of the slice refocusing gradient");
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
            param.setDisplayedName("SL");
            param.setDescription("The slice thickness");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(5.0E-5);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.001);
            param.setDefaultValue(0.005);
            return param;
        }
    },

    SOFTWARE_VERSION("SOFTWARE_VERSION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("SOFTWARE_VERSION");
            param.setDisplayedName("SOFTWARE_VERSION");
            param.setDescription("The version of the software");
            param.setLockedToDefault(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Software version");
            param.setDefaultValue("Software version");
            return param;
        }
    },

    SPACING_BETWEEN_SLICE("SPACING_BETWEEN_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLICE");
            param.setDisplayedName("Slice Spacing");
            param.setDescription("Spacing betwin slice");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.002);
            param.setDefaultValue(5.0);
            return param;
        }
    },

    SPECTRAL_WIDTH("SPECTRAL_WIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH");
            param.setDisplayedName("SW");
            param.setDescription("The spectral width of the reception ( SW BW, bandwidth )");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(12520.032051282049);
            param.setDefaultValue(12500.0);
            return param;
        }
    },

    SPECTRAL_WIDTH_OPT("SPECTRAL_WIDTH_OPT") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SPECTRAL_WIDTH_OPT");
            param.setDisplayedName("SPECTRAL_WIDTH_OPT");
            param.setDescription("Use SW to calculate SW_PER_PIXEL (true) Use SW_PER_PIXEL to calculate SW (false)");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SPECTRAL_WIDTH_PER_PIXEL("SPECTRAL_WIDTH_PER_PIXEL") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDisplayedName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDescription("Spectral Width per pixel in Hz / Pix");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(195.62550080128202);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SQUARE_PIXEL("SQUARE_PIXEL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SQUARE_PIXEL");
            param.setDisplayedName("SQUARE_PIXEL");
            param.setDescription("Same pixel dimension in frequency and phase encoding direction, this will change Phase FOV");
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
            param.setDisplayedName("STATION_NAME");
            param.setDescription("Station name");
            param.setLockedToDefault(true);
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
            param.setDisplayedName("SUBJECT_POSITION");
            param.setDescription("Subject position relative to the magnet.");
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
            param.setDescription("Switch the read and phase encoding gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_ENABLED("TOF2D_ENABLED") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF2D_ENABLED");
            param.setDisplayedName("TOF2D_ENABLED");
            param.setDescription("");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_FLOW_VELOCITY("TOF2D_FLOW_VELOCITY") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_FLOW_VELOCITY");
            param.setDisplayedName("TOF2D_FLOW_VELOCITY");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.12);
            param.setDefaultValue(0.3);
            return param;
        }
    },

    TOF2D_SB_DISTANCE_FROM_SLICE("TOF2D_SB_DISTANCE_FROM_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_DISTANCE_FROM_SLICE");
            param.setDisplayedName("TOF2D_SB_DISTANCE_FROM_SLICE");
            param.setDescription("");
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
            param.setDisplayedName("TOF2D_SB_GRAMP_SP");
            param.setDescription("Spoiler gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(40.0);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    TOF2D_SB_POSITION("TOF2D_SB_POSITION") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TOF2D_SB_POSITION");
            param.setDisplayedName("TOF2D_SB_POSITION");
            param.setDescription("Saturation band position");
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
            param.setDisplayedName("TOF2D_SB_THICKNESS");
            param.setDescription("");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.015);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    TOF2D_SB_TX_SHAPE("TOF2D_SB_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TOF2D_SB_TX_SHAPE");
            param.setDisplayedName("TOF2D_SB_TX_SHAPE");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setValue("SINC5");
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
            param.setDescription("show the acquisition dimension");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Scan);
            param.setValue(asListNumber(64, 64, 20, 3));
            return param;
        }
    },

    TRAJ_POSITION("TRAJ_POSITION") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRAJ_POSITION");
            param.setDisplayedName("TRAJ_POSITION");
            param.setDescription("2D 3D position");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Scan);
            return param;
        }
    },

    TRANSFORM_PLUGIN("TRANSFORM_PLUGIN") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TRANSFORM_PLUGIN");
            param.setDisplayedName("Transform plugin");
            param.setDescription("Transform the acquisition space to the k space");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("Sequential4D");
            param.setDefaultValue("Sequential4D");
            param.setSuggestedValues(asList("Sequential4D", "Sequential4DBackAndForth", "EPISequential4D", "Centric4D"));
            return param;
        }
    },

    TRIGGER_CHANEL("TRIGGER_CHANEL") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TRIGGER_CHANEL");
            param.setDisplayedName("Trigger Channel");
            param.setDescription("");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue("Ext1_XOR_Ext2");
            param.setDefaultValue("Ext1_XOR_Ext2");
            param.setSuggestedValues(asList("Ext1", "Ext2", "Ext1_AND_Ext2", "Ext1_XOR_Ext2"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    TRIGGER_EXTERNAL("TRIGGER_EXTERNAL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("TRIGGER_EXTERNAL");
            param.setDescription("Enable the synchronization of the sequence with the trigger");
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
            param.setDisplayedName("TRIGGER_TIME");
            param.setDescription("TRIGGER_TIME.description");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0));
            param.setDefaultValue(asListNumber(0.01));
            return param;
        }
    },

    TX_AMP("TX_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP");
            param.setDisplayedName("TX_AMP");
            param.setDescription("Emission amplitude");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(84.23253493677066);
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
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(80.14368241132927);
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
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(40.052483358517755);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_ATT_AUTO("TX_AMP_ATT_AUTO") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_AMP_ATT_AUTO");
            param.setDisplayedName("TX_AMP_ATT_AUTO");
            param.setDescription("Use the ATT and AMP set from the calibration ");
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
            param.setDisplayedName("TX_ATT");
            param.setDescription("Emission attenuation");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0.0);
            param.setMaxValue(63.0);
            param.setValue(21.0);
            param.setDefaultValue(36.0);
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR("TX_BANDWIDTH_FACTOR") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR");
            param.setDescription("The bandwidth factor of the RF pulse");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(0.9500000000000001, 1.35, 2.55, 4.25));
            param.setDefaultValue(asListNumber(0.95, 1.35, 2.55, 4.25));
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR_3D("TX_BANDWIDTH_FACTOR_3D") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR_3D");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR_3D");
            param.setDescription("");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(1.9, 2.7, 5.1, 8.5));
            param.setDefaultValue(asListNumber(1.1, 3.2, 5.0, 7.3));
            return param;
        }
    },

    TX_LENGTH("TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH");
            param.setDisplayedName("TX_LENGTH");
            param.setDescription("length of RF pulse");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_ROUTE("TX_ROUTE") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_ROUTE");
            param.setDisplayedName("TX_ROUTE");
            param.setDescription("");
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
            param.setDisplayedName("TX_SHAPE");
            param.setDescription("the shape of the rf pulse");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("GAUSSIAN");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            return param;
        }
    },

    USER_MATRIX_DIMENSION_1D("USER_MATRIX_DIMENSION_1D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_1D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDescription("");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(64);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_2D("USER_MATRIX_DIMENSION_2D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_2D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDescription("");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(64);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_3D("USER_MATRIX_DIMENSION_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_3D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDescription("");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(20);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_MATRIX_DIMENSION_4D("USER_MATRIX_DIMENSION_4D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_4D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDescription("");
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(1);
            param.setDefaultValue(1);
            return param;
        }
    },

    USER_PARTIAL_PHASE("USER_PARTIAL_PHASE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_PHASE");
            param.setDisplayedName("PARTIAL_PHASE");
            param.setDescription("Partial Fourier acquisition in the phase encoding direction");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(80.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_PARTIAL_SLICE("USER_PARTIAL_SLICE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_SLICE");
            param.setDisplayedName("PARTIAL_SLICE");
            param.setDescription("Partial Fourier acquisition in the 3D slice encoding direction");
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
            param.setDescription("Percentage of zero filling (=1 - PARTIAL_PHASE)");
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(20.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_ZERO_FILLING_3D("USER_ZERO_FILLING_3D") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_3D");
            param.setDisplayedName("ZERO_FILING_3D");
            param.setDescription("Percentage of zero filing  (=1 - PARTIAL_SLICE)");
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