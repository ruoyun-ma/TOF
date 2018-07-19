//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.gradientecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.sequenceGenerator.GeneratorParamEnum;

import java.util.List;
import static java.util.Arrays.asList;

public enum GradientEchoParams implements GeneratorParamEnum {
    ACCU_DIM {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACCU_DIM");
            param.setDisplayedName("ACCU_DIM.name");
            param.setDescription("ACCU_DIM.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_MATRIX_DIMENSION_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDisplayedName("Acquisition 1D");
            param.setDescription("The acquisition size of the first dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_MATRIX_DIMENSION_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDisplayedName("Acquisition 2D");
            param.setDescription("The acquisition size of the second dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_MATRIX_DIMENSION_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDisplayedName("Acquisition 3D");
            param.setDescription("The acquisition size of the third dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_MATRIX_DIMENSION_4D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDisplayedName("Acquisition 4D");
            param.setDescription("The acquisition size of the fourth dimension");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_MODE {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("ACQUISITION_MODE");
            param.setDisplayedName("ACQUISITION_MODE");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("ACQUISITION_TIME_OFFSET");
            param.setDescription("Relative acquisition start times in Dynamic or MultiSeries scans");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ACQUISITION_TIME_PER_SCAN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_TIME_PER_SCAN");
            param.setDisplayedName("Acq Time");
            param.setDescription("The acquisition time per scan  ( SPECTRAL_WIDTH )");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.005111821086261981);
            param.setDefaultValue(1.0);
            return param;
        }
    },

    BASE_FREQ_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_1");
            param.setDisplayedName("Base Freq 1");
            param.setDescription("The base frequency of the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    BASE_FREQ_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_2");
            param.setDisplayedName("Base Freq 2");
            param.setDescription("The base frequency of the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    BASE_FREQ_3 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_3");
            param.setDisplayedName("Base Freq 3");
            param.setDescription("The base frequency of the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    BASE_FREQ_4 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("BASE_FREQ_4");
            param.setDisplayedName("Base Freq 4");
            param.setDescription("The base frequency of the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    DATA_REPRESENTATION {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("DATA_REPRESENTATION");
            param.setDisplayedName("DATA_REPRESENTATION");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Miscellaneous);
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    DIGITAL_FILTER_REMOVED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DIGITAL_FILTER_REMOVED");
            param.setDisplayedName("Digital filter removed");
            param.setDescription("Data shift due to the digital filter are removed");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    DIGITAL_FILTER_SHIFT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DIGITAL_FILTER_SHIFT");
            param.setDisplayedName("Digital filter shift");
            param.setDescription("Data shift due to the digital filter");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    DUMMY_SCAN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DUMMY_SCAN");
            param.setDisplayedName("DS");
            param.setDescription("Dummy Scan");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    DYN_NUMBER_OF_ACQUISITION {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_NUMBER_OF_ACQUISITION");
            param.setDisplayedName("DYN_NUMBER_OF_ACQUISITION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    DYN_TIME_BTW_FRAMES {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("DYN_TIME_BTW_FRAMES");
            param.setDisplayedName("TIME_BETWEEN_FRAMES");
            param.setDescription("The time between 4D acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    DYNAMIC_MIN_TIME {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_MIN_TIME");
            param.setDisplayedName("DYNAMIC_MIN_TIME");
            param.setDescription("Execute the dynamic scan without delay between repetition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    DYNAMIC_SEQUENCE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DYNAMIC_SEQUENCE");
            param.setDisplayedName("DYNAMIC_SEQUENCE");
            param.setDescription("Sequence used for dynamic acquisitions");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    ECHO_SPACING {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_SPACING");
            param.setDisplayedName("ECHO_SPACING");
            param.setDescription("The delay between echo");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ECHO_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TIME");
            param.setDisplayedName("TE");
            param.setDescription("The echo time ( TE )");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ECHO_TRAIN_LENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("ECHO_TRAIN_LENGTH");
            param.setDisplayedName("ETL");
            param.setDescription("The echo train lentgth");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FAT_SATURATION_ENABLED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FAT_SATURATION_ENABLED");
            param.setDisplayedName("FAT_SATURATION_ENABLED");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FAT_WATER_SEP_2DMRSI {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FAT_WATER_SEP_2DMRSI");
            param.setDisplayedName("FAT_WATER_SEP_2DMRSI");
            param.setDescription("Checkbox parameter for running fat-water separation plugin");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FATSAT_BANDWIDTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_BANDWIDTH");
            param.setDisplayedName("FATSAT_BANDWIDTH");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(0.0);
            param.setMaxValue(2000.0);
            param.setValue(300.0);
            param.setDefaultValue(250.0);
            return param;
        }
    },

    FATSAT_FLIP_ANGLE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_FLIP_ANGLE");
            param.setDisplayedName("FATSAT_FLIP_ANGLE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FATSAT_GRAD_APP_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_GRAD_APP_TIME");
            param.setDisplayedName("FATSAT_GRAD_APP_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(3.6E-4);
            param.setDefaultValue(0.001);
            return param;
        }
    },

    FATSAT_OFFSET_FREQ {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_OFFSET_FREQ");
            param.setDisplayedName("FATSAT_OFFSET_FREQ");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1500.0);
            param.setMaxValue(1500.0);
            param.setValue(-440.0);
            param.setDefaultValue(-440.0);
            return param;
        }
    },

    FATSAT_PERIODE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_PERIODE");
            param.setDisplayedName("FATSAT_PERIODE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FATSAT_PERIODE_EFF {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_PERIODE_EFF");
            param.setDisplayedName("FATSAT_PERIODE_EFF");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FATSAT_TX_AMP_90 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_AMP_90");
            param.setDisplayedName("FATSAT_TX_AMP_90");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FATSAT_TX_LENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FATSAT_TX_LENGTH");
            param.setDisplayedName("FATSAT_TX_LENGTH");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FATSAT_TX_SHAPE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("FATSAT_TX_SHAPE");
            param.setDisplayedName("FATSAT_TX_SHAPE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    FIELD_OF_VIEW {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW");
            param.setDisplayedName("FOV");
            param.setDescription("The field of view");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FIELD_OF_VIEW_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_3D");
            param.setDisplayedName("FIELD_OF_VIEW_3D");
            param.setDescription("Field of View in the 3rd direction : total coverage");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FIELD_OF_VIEW_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FIELD_OF_VIEW_PHASE");
            param.setDisplayedName("FIELD_OF_VIEW_PHASE");
            param.setDescription("Field of View in the phase encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FLIP_ANGLE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLIP_ANGLE");
            param.setDisplayedName("Flip angle");
            param.setDescription("The flip angle for the excitation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FLOW_COMPENSATION {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FLOW_COMPENSATION");
            param.setDisplayedName("Flow compensation");
            param.setDescription("Use flow compensation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FLOWCOMP_DURATION {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FLOWCOMP_DURATION");
            param.setDisplayedName("FLOWCOMP_DURATION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(7.0E-4);
            param.setDefaultValue(5.0E-4);
            return param;
        }
    },

    FLYBACK {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FLYBACK");
            param.setDisplayedName("FLYBACK");
            param.setDescription("Aquisition in the same direction in k space, fly back after every k space line");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_DOUBLED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_DOUBLED");
            param.setDisplayedName("FOV_DOUBLED");
            param.setDescription("Double the FOV and SW");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_EFF {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_EFF");
            param.setDisplayedName("FOV_EFF");
            param.setDescription("Show the acquierd FOV when FOV doubled");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FOV_RATIO_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_RATIO_PHASE");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction (2D/1D)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    FOV_SQUARE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("FOV_SQUARE");
            param.setDisplayedName("FOV_SQUARE");
            param.setDescription("Impose FOV_PHASE= FOV");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRAD_AMP_SPOILER_SL_PH_RE {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("GRAD_AMP_SPOILER_SL_PH_RE");
            param.setDisplayedName("GRAD_AMP_SPOILER_SL_PH_SL");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(10.0, 0.0, 0.0));
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE");
            param.setDisplayedName("GRADIENT_ENABLE_PHASE");
            param.setDescription("enable the phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_PHASE_3D {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_PHASE_3D");
            param.setDisplayedName("GRADIENT_ENABLE_PHASE_3D");
            param.setDescription("enable the 3D phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_READ {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_READ");
            param.setDisplayedName("GRADIENT_ENABLE_READ");
            param.setDescription("enable the read encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_REWINDING {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_REWINDING");
            param.setDisplayedName("GRADIENT_ENABLE_REWINDING");
            param.setDescription("Do Gradient Rewind instead of Spoiling");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_SLICE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SLICE");
            param.setDisplayedName("GRADIENT_ENABLE_SLICE");
            param.setDescription("enable the slice encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_SPOILER {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_SPOILER");
            param.setDisplayedName("GRADIENT_ENABLE_SPOILER");
            param.setDescription("Enable gradient spoiler on the three direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_FLYBACK_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_FLYBACK_TIME");
            param.setDisplayedName("GRADIENT_FLYBACK_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(4.9999999999999996E-6);
            param.setMaxValue(1.0E9);
            param.setValue(1.9999999999999998E-4);
            param.setDefaultValue(4.9999999999999996E-6);
            return param;
        }
    },

    GRADIENT_PHASE_APPLICATION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDisplayedName("GRADIENT_PHASE_APPLICATION_TIME");
            param.setDescription("the application time of the phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    GRADIENT_RISE_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_RISE_TIME");
            param.setDisplayedName("GRADIENT_RISE_TIME");
            param.setDescription("The rise time of the gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    GRADIENT_SPOILER_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("GRADIENT_SPOILER_TIME");
            param.setDisplayedName("Gradient spoiler top time");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    HARDWARE_A0 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_A0");
            param.setDisplayedName("HARDWARE_A0");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_DC {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_DC");
            param.setDisplayedName("HARDWARE_DC");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_A {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_A");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_A");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_PREEMPHASIS_T {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_PREEMPHASIS_T");
            param.setDisplayedName("HARDWARE_PREEMPHASIS_T");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
            return param;
        }
    },

    HARDWARE_SHIM {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("HARDWARE_SHIM");
            param.setDisplayedName("HARDWARE_SHIM");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2.147483648E9);
            param.setMaxValue(2.147483647E9);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(0.0));
            return param;
        }
    },

    HARDWARE_SHIM_LABEL {
        Param build() {
            TextParam param = new TextParam();
            param.setName("HARDWARE_SHIM_LABEL");
            param.setDisplayedName("HARDWARE_SHIM_LABEL");
            param.setDescription("");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("NotConnected");
            param.setDefaultValue("");
            return param;
        }
    },

    IMAGE_ORIENTATION_SUBJECT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_ORIENTATION_SUBJECT");
            param.setDisplayedName("IMAGE_ORIENTATION_SUBJECT");
            param.setDescription("Direction cosines of the first row and the first column with respect to the subject");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    IMAGE_POSITION_SUBJECT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("IMAGE_POSITION_SUBJECT");
            param.setDisplayedName("IMAGE_POSITION_SUBJECT");
            param.setDescription("x, y, and z coordinates of the upper left hand corner of the image");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    INTERLEAVED_ECHO_TRAIN {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("INTERLEAVED_ECHO_TRAIN");
            param.setDisplayedName("INTERLEAVED_ECHO_TRAIN");
            param.setDescription("multiple ETL in interleaved mode");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    INTERLEAVED_EFF_ECHO_SPACING {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERLEAVED_EFF_ECHO_SPACING");
            param.setDisplayedName("INTERLEAVED_EFF_ECHO_SPACING");
            param.setDescription("Effective echo spacing of interleaved echo trains");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(9.589760000000001E-4);
            param.setDefaultValue(8.0E-4);
            return param;
        }
    },

    INTERLEAVED_NUM_OF_ECHO_TRAIN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERLEAVED_NUM_OF_ECHO_TRAIN");
            param.setDisplayedName("INTERLEAVED_NUM_OF_ECHO_TRAIN");
            param.setDescription("Number of interleaved echo train");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.UnsignedShort);
            param.setMinValue(1);
            param.setMaxValue(65535);
            param.setValue(4);
            param.setDefaultValue(1);
            return param;
        }
    },

    INTERMEDIATE_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("INTERMEDIATE_FREQUENCY");
            param.setDisplayedName("INTERMEDIATE_FREQUENCY.name");
            param.setDescription("INTERMEDIATE_FREQUENCY.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(2.051E7);
            param.setDefaultValue(2.051E7);
            return param;
        }
    },

    KEYHOLE_ALLOWED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KEYHOLE_ALLOWED");
            param.setDisplayedName("KEYHOLE_ALLOWED");
            param.setDescription("Allow keyhole: test the max amp of the phase encoding gradient for partial = 100%");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KS_CENTER_MODE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTER_MODE");
            param.setDisplayedName("KS_CENTER_MODE");
            param.setDescription("Turn off the PE gradient and acquieres two single scan only");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KS_CENTERED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("KS_CENTERED");
            param.setDisplayedName("KS_CENTERED");
            param.setDescription("Center the k-space around k0(check) or go through k0");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    KSPACE_FILLING {
        Param build() {
            TextParam param = new TextParam();
            param.setName("KSPACE_FILLING");
            param.setDisplayedName("KSPACE_FILLING");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("Centric");
            param.setDefaultValue("Linear");
            param.setSuggestedValues(asList("Linear", "Centric"));
            return param;
        }
    },

    LAST_PUT {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("LAST_PUT");
            param.setDisplayedName("LAST_PUT");
            param.setDescription("LAST_PUT.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    MAGNETIC_FIELD_STRENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("MAGNETIC_FIELD_STRENGTH");
            param.setDisplayedName("B0");
            param.setDescription("The magnetic field tregth");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    MANUFACTURER {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MANUFACTURER");
            param.setDisplayedName("MANUFACTURER");
            param.setDescription("Manufacturer");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Manufacturer");
            param.setDefaultValue("Manufacturer");
            return param;
        }
    },

    MIN_RISE_TIME_FACTOR {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("MIN_RISE_TIME_FACTOR");
            param.setDisplayedName("MIN_RISE_TIME_FACTOR");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    MODALITY {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MODALITY");
            param.setDisplayedName("Modality");
            param.setDescription("The modality for the acquisition");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("MRI");
            param.setDefaultValue("MRI");
            param.setSuggestedValues(asList("NMR", "MRI", "DEFAULT"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    MODEL_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MODEL_NAME");
            param.setDisplayedName("MODEL_NAME");
            param.setDescription("Model name");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Model name");
            param.setDefaultValue("Model name");
            return param;
        }
    },

    MULTI_PLANAR_EXCITATION {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("MULTI_PLANAR_EXCITATION");
            param.setDisplayedName("Multi planar");
            param.setDescription("Multi planar excitation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    MULTISERIES_PARAMETER_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("MULTISERIES_PARAMETER_NAME");
            param.setDisplayedName("MULTISERIES_PARAMETER_NAME");
            param.setDescription("Name of MultiSeries Parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            param.setSuggestedValues(asList("TE", "TI", "TRIGGER DELAY"));
            return param;
        }
    },

    MULTISERIES_PARAMETER_VALUE {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("MULTISERIES_PARAMETER_VALUE");
            param.setDisplayedName("MULTISERIES_PARAMETER_VALUE");
            param.setDescription("Values of MultiSeries Parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    NUCLEUS_1 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_1");
            param.setDisplayedName("Nucleus 1");
            param.setDescription("The nucleus used for the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_2 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_2");
            param.setDisplayedName("Nucleus 2");
            param.setDescription("The nucleus used for the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_3 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_3");
            param.setDisplayedName("Nucleus 3");
            param.setDescription("The nucleus used for the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUCLEUS_4 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("NUCLEUS_4");
            param.setDisplayedName("Nucleus 4");
            param.setDescription("The nucleus used for the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    NUMBER_OF_AVERAGES {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_AVERAGES");
            param.setDisplayedName("NEX");
            param.setDescription("The number of average");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    NUMBER_OF_INTERLEAVED_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDisplayedName("NUMBER_OF_INTERLEAVED_SLICE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    NUMBER_OF_SHOOT_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("NUMBER_OF_SHOOT_3D");
            param.setDisplayedName("Number of Shoot 3D");
            param.setDescription("Number of Slice set acquiered sequencially");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OBSERVED_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OBSERVED_FREQUENCY");
            param.setDisplayedName("Observed frequency");
            param.setDescription("The frequency of the acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OBSERVED_NUCLEUS {
        Param build() {
            TextParam param = new TextParam();
            param.setName("OBSERVED_NUCLEUS");
            param.setDisplayedName("Observed Nucleus");
            param.setDescription("The observed nucleus");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue("1H");
            param.setDefaultValue("1H");
            param.setSuggestedValues(asList("Other", "Y", "X", "3H", "1H", "19F", "3He", "205Tl", "203Tl", "31P", "7Li", "119Sn", "117Sn", "87Rb", "115Sn", "11B", "125Te", "141Pr", "71Ga", "65Cu", "129Xe", "81Br", "63Cu", "23Na", "51V", "123Te", "27Al", "13C", "79Br", "151Eu", "55Mn", "93Nb", "45Sc", "159Tb", "69Ga", "121Sb", "59Co", "187Re", "185Re", "99Tc", "113Cd", "115In", "113In", "195Pt", "165Ho", "111Cd", "207Pb", "127I", "29Si", "77Se", "199Hg", "171Yb", "75As", "209Bi", "2H", "6Li", "139La", "9Be", "17O", "138La", "133Cs", "123Sb", "181Ta", "175Lu", "137Ba", "153Eu", "10B", "15N", "50V", "135Ba", "35Cl", "85Rb", "91Zr", "61Ni", "169Tm", "131Xe", "37Cl", "176Lu", "21Ne", "189Os", "33S", "14N", "43Ca", "97Mo", "201Hg", "95Mo", "67Zn", "25Mg", "40K", "53Cr", "49Ti", "47Ti", "143Nd", "101Ru", "89Y", "173Yb", "163Dy", "39K", "109Ag", "99Ru", "105Pd", "87Sr", "147Sm", "183W", "107Ag", "157Gd", "177Hf", "83Kr", "73Ge", "149Sm", "161Dy", "145Nd", "57Fe", "103Rh", "155Gd", "167Er", "41K", "179Hf", "187Os", "193Ir", "235U", "197Au", "191Ir"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    OFF_CENTER_FIELD_OF_VIEW_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_1D");
            param.setDisplayedName("Location 1D");
            param.setDescription("Offset in Readout Direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_2D");
            param.setDisplayedName("Location 2D");
            param.setDescription("Offset in Phase Encoding Direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_3D");
            param.setDisplayedName("Location 3D");
            param.setDescription("Offset in the slice direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_EFF {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDisplayedName("OFF_CENTER_FIELD_OF_VIEW_EFF");
            param.setDescription("Offcenter effective in 1D 2D and 3D (read phase slice)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_X {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_X");
            param.setDisplayedName("Location X");
            param.setDescription("Location in the R/L direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_Y {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Y");
            param.setDisplayedName("Location Y");
            param.setDescription("Location in the A/P direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFF_CENTER_FIELD_OF_VIEW_Z {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFF_CENTER_FIELD_OF_VIEW_Z");
            param.setDisplayedName("Location Z");
            param.setDescription("Location in the I/S direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFFSET_FREQ_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_1");
            param.setDisplayedName("Offset 1");
            param.setDescription("The offset frequency of the first sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFFSET_FREQ_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_2");
            param.setDisplayedName("Offset 2");
            param.setDescription("The offset frequency of the second sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFFSET_FREQ_3 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_3");
            param.setDisplayedName("Offset 3");
            param.setDescription("The offset frequency of the third sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    OFFSET_FREQ_4 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("OFFSET_FREQ_4");
            param.setDisplayedName("Offset 4");
            param.setDescription("The offset frequency of the fourth sequence channel");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    ORIENTATION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("ORIENTATION");
            param.setDisplayedName("Orientation");
            param.setDescription("Field of view orientation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("AXIAL");
            param.setDefaultValue("AXIAL");
            param.setSuggestedValues(asList("AXIAL", "CORONAL", "SAGITTAL", "OBLIQUE"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    PAROPT_PARAM {
        Param build() {
            TextParam param = new TextParam();
            param.setName("PAROPT_PARAM");
            param.setDisplayedName("Parameter optimised");
            param.setDescription("Name of the current optimised parameter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("PULSE_LENGTH");
            return param;
        }
    },

    PARTIAL_OVERSAMPLING {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PARTIAL_OVERSAMPLING");
            param.setDisplayedName("PARTIAL_OVERSAMPLING");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    PHASE_0 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_0");
            param.setDisplayedName("PHASE_0");
            param.setDescription("PHASE_0.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_1 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("PHASE_1");
            param.setDisplayedName("PHASE_1");
            param.setDescription("PHASE_1.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    PHASE_APPLIED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_APPLIED");
            param.setDisplayedName("PHASE_APPLIED");
            param.setDescription("PHASE_APPLIED");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Process);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PHASE_FIELD_OF_VIEW_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("PHASE_FIELD_OF_VIEW_RATIO");
            param.setDisplayedName("Phase FOV");
            param.setDescription("The fov ratio in the phase direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    PHASE_RESET {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("PHASE_RESET");
            param.setDisplayedName("PHASE_RESET");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    PREPHASING_READ_GRADIENT_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDisplayedName("PREPHASING_READ_GRADIENT_RATIO");
            param.setDescription("The prephasing reading gradient ratio");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    PROBE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("PROBE");
            param.setDisplayedName("Probe");
            param.setDescription("The probe used for the mr acquisition");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    PROBES {
        Param build() {
            ListTextParam param = new ListTextParam();
            param.setName("PROBES");
            param.setDisplayedName("Probes");
            param.setDescription("The probes used for the acquisition");
            param.setLocked(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            return param;
        }
    },

    RECEIVER_COUNT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_COUNT");
            param.setDisplayedName("Receiver Count");
            param.setDescription("The number of receivers");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    RECEIVER_GAIN {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RECEIVER_GAIN");
            param.setDisplayedName("RG");
            param.setDescription("The receiver gain");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    REPETITION_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("REPETITION_TIME");
            param.setDisplayedName("TR");
            param.setDescription("The repetition time ( TR )");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    RESOLUTION_FREQUENCY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_FREQUENCY");
            param.setDisplayedName("RESOLUTION_FREQUENCY");
            param.setDescription("True Pixel dimension in the frequency encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    RESOLUTION_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_PHASE");
            param.setDisplayedName("RESOLUTION_PHASE");
            param.setDescription("True pixel dimension in the phase encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    RESOLUTION_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("RESOLUTION_SLICE");
            param.setDisplayedName("RESOLUTION_SLICE");
            param.setDescription("True pixel dimension in the 3rd direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    RF_SPOILING {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("RF_SPOILING");
            param.setDisplayedName("RF_SPOILING");
            param.setDescription("Enable the phase cycling in the sequence");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_DISTANCE_FROM_FOV {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_DISTANCE_FROM_FOV");
            param.setDisplayedName("SATBAND_DISTANCE_FROM_FOV");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-0.1);
            param.setMaxValue(0.1);
            param.setValue(0.01);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_ENABLED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SATBAND_ENABLED");
            param.setDisplayedName("SATBAND_ENABLED");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_GRAD_AMP_SPOILER {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_GRAD_AMP_SPOILER");
            param.setDisplayedName("SATBAND_GRAD_AMP_SPOILER");
            param.setDescription("Amplitude of the spoiler gradient after saturation pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SATBAND_ORIENTATION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_ORIENTATION");
            param.setDisplayedName("SATBAND_ORIENTATION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("ALL");
            param.setDefaultValue("CRANIAL");
            param.setSuggestedValues(asList("CRANIAL", "CAUDAL", "RIGHT", "LEFT", "ANTERIOR", "POSTERIOR", "CRANIAL AND CAUDAL", "RIGHT AND LEFT", "ANTERIOR AND POSTERIOR", "ALL"));
            return param;
        }
    },

    SATBAND_T1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_T1");
            param.setDisplayedName("SATBAND_T1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SATBAND_THICKNESS {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_THICKNESS");
            param.setDisplayedName("SATBAND_THICKNESS");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(0.1);
            param.setValue(0.02);
            param.setDefaultValue(0.01);
            return param;
        }
    },

    SATBAND_TX_SHAPE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SATBAND_TX_SHAPE");
            param.setDisplayedName("SATBAND_TX_SHAPE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SEQ_DESCRIPTION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQ_DESCRIPTION");
            param.setDisplayedName("SEQ_DESCRIPTION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("GE_2DAXI_64x50x20");
            param.setDefaultValue("");
            return param;
        }
    },

    SEQUENCE_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_NAME");
            param.setDisplayedName("Seq");
            param.setDescription("The name of the sequence");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue("GRADIENT_ECHO");
            param.setDefaultValue("GRADIENT_ECHO");
            return param;
        }
    },

    SEQUENCE_TIME {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SEQUENCE_TIME");
            param.setDisplayedName("SEQUENCE_TIME");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SEQUENCE_VERSION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SEQUENCE_VERSION");
            param.setDisplayedName("SEQUENCE_VERSION");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.User);
            param.setCategory(Category.Acquisition);
            param.setValue("Version8.0f");
            param.setDefaultValue("");
            return param;
        }
    },

    SETUP_MODE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SETUP_MODE");
            param.setDisplayedName("Setup");
            param.setDescription("True during setup process");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDescription("The ratio of the slice refocusing gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SLICE_THICKNESS {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_THICKNESS");
            param.setDisplayedName("SL");
            param.setDescription("The slice thickness");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SOFTWARE_VERSION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SOFTWARE_VERSION");
            param.setDisplayedName("SOFTWARE_VERSION");
            param.setDescription("The version of the software");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Software version");
            param.setDefaultValue("Software version");
            return param;
        }
    },

    SPACING_BETWEEN_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPACING_BETWEEN_SLICE");
            param.setDisplayedName("Slice Spacing");
            param.setDescription("Spacing betwin slice");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    SPECTRAL_WIDTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH");
            param.setDisplayedName("SW");
            param.setDescription("The spectral width of the reception ( SW BW, bandwidth )");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(12520.0);
            param.setDefaultValue(12500.0);
            return param;
        }
    },

    SPECTRAL_WIDTH_OPT {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SPECTRAL_WIDTH_OPT");
            param.setDisplayedName("SPECTRAL_WIDTH_OPT");
            param.setDescription("Use SW to calculate SW_PER_PIXEL (true) Use SW_PER_PIXEL to calculate SW (false)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SPECTRAL_WIDTH_PER_PIXEL {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDisplayedName("SPECTRAL_WIDTH_PER_PIXEL");
            param.setDescription("Spectral Width per pixel in Hz / Pix");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(195.625);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SQUARE_PIXEL {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SQUARE_PIXEL");
            param.setDisplayedName("SQUARE_PIXEL");
            param.setDescription("Same pixel dimension in frequency and phase encoding direction, this will change Phase FOV");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(true);
            return param;
        }
    },

    STATION_NAME {
        Param build() {
            TextParam param = new TextParam();
            param.setName("STATION_NAME");
            param.setDisplayedName("STATION_NAME");
            param.setDescription("Station name");
            param.setLockedToDefault(true);
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Miscellaneous);
            param.setValue("Station name");
            param.setDefaultValue("Station name");
            return param;
        }
    },

    SUBJECT_POSITION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("SUBJECT_POSITION");
            param.setDisplayedName("SUBJECT_POSITION");
            param.setDescription("Subject position relative to the magnet.");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("HeadFirstProne");
            param.setDefaultValue("FeetFirstProne");
            param.setSuggestedValues(asList("HeadFirstProne", "HeadFirstSupine", "FeetFirstProne", "FeetFirstSupine"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    SWITCH_READ_PHASE {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("SWITCH_READ_PHASE");
            param.setDisplayedName("Switch Read/Phase");
            param.setDescription("Switch the read and phase encoding gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_ENABLED {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF2D_ENABLED");
            param.setDisplayedName("TOF2D_ENABLED");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF2D_FLOW_VELOCITY {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_FLOW_VELOCITY");
            param.setDisplayedName("TOF2D_FLOW_VELOCITY");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.12);
            param.setDefaultValue(0.3);
            return param;
        }
    },

    TOF2D_SB_DISTANCE_FROM_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_DISTANCE_FROM_SLICE");
            param.setDisplayedName("TOF2D_SB_DISTANCE_FROM_SLICE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TOF2D_SB_GRAMP_SP {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_GRAMP_SP");
            param.setDisplayedName("TOF2D_SB_GRAMP_SP");
            param.setDescription("Spoiler gradient");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TOF2D_SB_POSITION {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TOF2D_SB_POSITION");
            param.setDisplayedName("TOF2D_SB_POSITION");
            param.setDescription("Saturation band position");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setValue("ABOVE THE SLICE");
            param.setDefaultValue("BELOW THE SLICE");
            param.setSuggestedValues(asList("ABOVE THE SLICE", "BELOW THE SLICE"));
            return param;
        }
    },

    TOF2D_SB_THICKNESS {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF2D_SB_THICKNESS");
            param.setDisplayedName("TOF2D_SB_THICKNESS");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TOF2D_SB_TX_SHAPE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TOF2D_SB_TX_SHAPE");
            param.setDisplayedName("TOF2D_SB_TX_SHAPE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("SINC5");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576", "RAMP"));
            return param;
        }
    },

    TRANSFORM_PLUGIN {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TRANSFORM_PLUGIN");
            param.setDisplayedName("Transform plugin");
            param.setDescription("Transform the acquisition space to the k space");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setValue("Centric4D");
            param.setDefaultValue("Sequential4D");
            param.setSuggestedValues(asList("Sequential4D", "Sequential4DBackAndForth", "EPISequential4D", "Centric4D"));
            return param;
        }
    },

    TRIGGER_CHANEL {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TRIGGER_CHANEL");
            param.setDisplayedName("Trigger Channel");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue("Ext1_XOR_Ext2");
            param.setDefaultValue("Ext1_XOR_Ext2");
            param.setSuggestedValues(asList("Ext1", "Ext2", "Ext1_AND_Ext2", "Ext1_XOR_Ext2"));
            param.setRestrictedToSuggested(true);
            return param;
        }
    },

    TRIGGER_EXTERNAL {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("TRIGGER_EXTERNAL");
            param.setDescription("Enable the synchronization of the sequence with the trigger");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TRIGGER_TIME {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TRIGGER_TIME");
            param.setDisplayedName("TRIGGER_TIME");
            param.setDescription("TRIGGER_TIME.description");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TX_AMP_180 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_180");
            param.setDisplayedName("TX_AMP_180");
            param.setDescription("The magnitude of the RF pulse 180");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TX_AMP_90 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_AMP_90");
            param.setDisplayedName("TX_AMP_90");
            param.setDescription("Amplitude of the transmitter");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TX_AMP_ATT_AUTO {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_AMP_ATT_AUTO");
            param.setDisplayedName("TX_AMP_ATT_AUTO");
            param.setDescription("Use the ATT and AMP set from the calibration ");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    TX_ATT {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_ATT");
            param.setDisplayedName("TX_ATT");
            param.setDescription("Emission attenuation");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0.0);
            param.setMaxValue(63.0);
            param.setValue(37.0);
            param.setDefaultValue(36.0);
            return param;
        }
    },

    TX_BANDWIDTH_FACTOR {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR");
            param.setDescription("The bandwidth factor of the RF pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TX_BANDWIDTH_FACTOR_3D {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_BANDWIDTH_FACTOR_3D");
            param.setDisplayedName("TX_BANDWIDTH_FACTOR_3D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TX_LENGTH {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("TX_LENGTH");
            param.setDisplayedName("TX_LENGTH");
            param.setDescription("length of RF pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    TX_ROUTE {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("TX_ROUTE");
            param.setDisplayedName("TX_ROUTE");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0));
            return param;
        }
    },

    TX_SHAPE {
        Param build() {
            TextParam param = new TextParam();
            param.setName("TX_SHAPE");
            param.setDisplayedName("TX_SHAPE");
            param.setDescription("the shape of the rf pulse");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setValue("GAUSSIAN");
            param.setDefaultValue("GAUSSIAN");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            return param;
        }
    },

    USER_MATRIX_DIMENSION_1D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_1D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_1D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_MATRIX_DIMENSION_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_2D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_2D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_MATRIX_DIMENSION_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_3D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_3D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_MATRIX_DIMENSION_4D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_MATRIX_DIMENSION_4D");
            param.setDisplayedName("USER_ACQUISITION_MATRIX_DIMENSION_4D");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_PARTIAL_PHASE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_PHASE");
            param.setDisplayedName("PARTIAL_PHASE");
            param.setDescription("Partial Fourier acquisition in the phase encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_PARTIAL_SLICE {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_PARTIAL_SLICE");
            param.setDisplayedName("PARTIAL_SLICE");
            param.setDescription("Partial Fourier acquisition in the 3D slice encoding direction");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_TMP_PARAM_BOOL_1 {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("USER_TMP_PARAM_BOOL_1");
            param.setDisplayedName("USER_TMP_PARAM_BOOL_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    USER_TMP_PARAM_BOOL_2 {
        Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("USER_TMP_PARAM_BOOL_2");
            param.setDisplayedName("USER_TMP_PARAM_BOOL_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    USER_TMP_PARAM_LIST_1 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("USER_TMP_PARAM_LIST_1");
            param.setDisplayedName("USER_TMP_PARAM_LIST_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    USER_TMP_PARAM_LIST_2 {
        Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("USER_TMP_PARAM_LIST_2");
            param.setDisplayedName("USER_TMP_PARAM_LIST_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            return param;
        }
    },

    USER_TMP_PARAM_NUM_1 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_TMP_PARAM_NUM_1");
            param.setDisplayedName("USER_TMP_PARAM_NUM_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_TMP_PARAM_NUM_2 {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_TMP_PARAM_NUM_2");
            param.setDisplayedName("USER_TMP_PARAM_NUM_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    USER_TMP_PARAM_STR_1 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("USER_TMP_PARAM_STR_1");
            param.setDisplayedName("USER_TMP_PARAM_STR_1");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    USER_TMP_PARAM_STR_2 {
        Param build() {
            TextParam param = new TextParam();
            param.setName("USER_TMP_PARAM_STR_2");
            param.setDisplayedName("USER_TMP_PARAM_STR_2");
            param.setDescription("");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
            param.setCategory(Category.Acquisition);
            param.setValue("");
            param.setDefaultValue("");
            return param;
        }
    },

    USER_ZERO_FILLING_2D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_2D");
            param.setDisplayedName("ZERO_FILING_2D");
            param.setDescription("Percentage of zero filling (=1 - PARTIAL_PHASE)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    USER_ZERO_FILLING_3D {
        Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_ZERO_FILLING_3D");
            param.setDisplayedName("ZERO_FILING_3D");
            param.setDescription("Percentage of zero filing  (=1 - PARTIAL_SLICE)");
            param.setRoles(new RoleEnum[] {RoleEnum.User});
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

    abstract Param build();

    private static List<Number> asListNumber(Number ... numbers) {
        return asList(numbers);
    }
}