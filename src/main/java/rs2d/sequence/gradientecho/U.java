//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.gradientecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
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
            param.setUuid("473dfd9e-3773-4e51-accb-7d145c352f57");
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
            param.setUuid("9aa51594-63f2-4b8a-8fd7-f87ac140067d");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(80);
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
            param.setUuid("ba7d6f05-ce1d-442e-a7d3-660f49d83b8f");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(80);
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
            param.setUuid("7dc0c916-905b-497b-bfc5-1b47b33c0e13");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(24);
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
            param.setUuid("c95d9ac9-4a59-4344-aff5-bcaccaed5e8f");
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
            param.setDescription("ACQUISITION_MODE and DATA_REPRESENTATION are filled according to the phase modulation chosen");
            param.setLocked(true);
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("f6f0bba4-e4a2-49af-a7f9-54a3ca18750e");
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    ACQUISITION_NB_ECHO_TRAIN("ACQUISITION_NB_ECHO_TRAIN") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_NB_ECHO_TRAIN");
            param.setDisplayedName("ACQUISITION_NB_ECHO_TRAIN");
            param.setDescription("Info: Number of echo trains in each slab ");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("ba9107b3-dec8-44fa-b280-534b3cc512e1");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setValue(2848);
            param.setDefaultValue(0);
            return param;
        }
    },

    ACQUISITION_NB_VIEW("ACQUISITION_NB_VIEW") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("ACQUISITION_NB_VIEW");
            param.setDisplayedName("ACQUISITION_NB_VIEW");
            param.setDescription("Info: Number of sampling points in 2D-3D plane");
            param.setLocked(true);
            param.setGroup(EnumGroup.Scan);
            param.setCategory(Category.Acquisition);
            param.setUuid("313e37e2-7961-4f3a-8436-edbe8b3ac560");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setValue(2848);
            param.setDefaultValue(0);
            return param;
        }
    },

    ACQUISITION_TIME_OFFSET("ACQUISITION_TIME_OFFSET") {
        public Param build() {
            ListNumberParam param = new ListNumberParam();
            param.setName("ACQUISITION_TIME_OFFSET");
            param.setDisplayedName("Acquisition time offset");
            param.setDescription("Info: Relative acquisition start times in Dynamic or MultiSeries scans");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("8d549bff-0416-40e2-9e2c-6ba674a88ca9");
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
            param.setDisplayedName("Observation Time");
            param.setDescription("Info: Time during which the signal is sampled by the ADC");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("e0a49139-ddf3-44a3-b636-036e4f2352e8");
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
            param.setUuid("f273a149-341b-461d-89dd-24057ebcf0f4");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.2781254725870714E8);
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
            param.setUuid("67130a54-a433-4de3-b94c-230c67ba1c16");
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
            param.setUuid("a5fb1693-16d9-4558-8f0c-9eeec98b054e");
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
            param.setUuid("74d5a23e-59bc-47b7-87d5-14245c54016f");
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
            param.setUuid("8f2ef739-8e03-46c3-bb3e-11c19661a4ef");
            param.setValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            param.setDefaultValue(asList("COMPLEX", "REAL", "REAL", "REAL"));
            return param;
        }
    },

    DEBUG_MODE("DEBUG_MODE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("DEBUG_MODE");
            param.setDisplayedName("DEBUG_MODE");
            param.setDescription("Switch on debug mode to print out parameters in the Log window");
            param.setCategory(Category.Acquisition);
            param.setUuid("486e2531-3a4e-4739-a253-65d9e7d6bb17");
            param.setValue(true);
            param.setDefaultValue(false);
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
            param.setUuid("482a509c-a299-4d95-b002-d51b0a73ba01");
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
            param.setUuid("9769e871-df83-42c2-a7cf-5fa87dae1116");
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
            param.setUuid("06bbc812-d79a-4467-a361-0933c1725d1e");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(0);
            param.setMaxValue(65536);
            param.setValue(10);
            param.setDefaultValue(128);
            return param;
        }
    },

    DWELL_TIME("DWELL_TIME") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("DWELL_TIME");
            param.setDisplayedName("DW");
            param.setDescription("Reception dwell time");
            param.setGroup(EnumGroup.Reception);
            param.setCategory(Category.Acquisition);
            param.setUuid("6a63b9dd-a532-46cd-a462-8db10811ea9e");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.0E-5);
            param.setDefaultValue(0.0);
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
            param.setUuid("1099b9e7-3d31-4184-a66c-803be5842bd9");
            param.setValue(true);
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
            param.setUuid("45fcf061-e9e6-4cea-95bc-4d394dbb588e");
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
            param.setUuid("4cbfa1d4-7f63-4999-bd3d-70fe46f7c61a");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(3);
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
            param.setUuid("df021729-1e29-4bc5-bfbc-612afdc75b1e");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(12.161);
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
            param.setUuid("3b9375d4-4cfc-456a-a44f-91e816df225c");
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
            param.setUuid("bf36975c-8ef8-4fa7-a9bc-ca2796d0333f");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.00402);
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
            param.setUuid("d1593896-1f42-43ab-b4e9-6ddc341c40e4");
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
            param.setUuid("bfaa0140-9687-41b8-a27a-0ba6115d2f3f");
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
            param.setUuid("36198022-798e-41a7-ac78-504271aa4d0c");
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
            param.setDisplayedName("FatSat - Gradient Duration");
            param.setDescription("Top time of Fat-Sat gradient");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("56dedd5d-c0ec-4159-bd07-1c1575474887");
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
            param.setUuid("2d62085f-f2be-4f9f-9853-72b7f61aaa1c");
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
            param.setUuid("6360d188-42bf-4aef-8423-04e582071613");
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
            param.setUuid("6cd0345c-cbf8-4592-bd3d-5f5e3ef9c009");
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
            param.setUuid("7bd1a4e5-0e24-4640-8ce8-d9fcd3b1fa21");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(0.0);
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
            param.setUuid("bc29b4ac-958c-4aac-b411-89dd67ba4a89");
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
            param.setDisplayedName("FatSat - TX Shape");
            param.setDescription("Fat-Sat Pulse shape");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("878894a0-4575-4f11-af38-0304d06305c3");
            param.setValue("HARD");
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
            param.setUuid("2ad4fa90-f0cf-476e-9640-60d3bf78234f");
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
            param.setUuid("b580dc2d-326d-49a0-953b-d624cd8e97ea");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.001);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.07);
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
            param.setUuid("0a888a27-0fd3-4c90-a9cf-47b947469ba7");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.0);
            param.setValue(0.012);
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
            param.setUuid("2b159029-7c26-45a4-8573-90d5d37cb04f");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.01);
            param.setMaxValue(10.0);
            param.setValue(0.07);
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
            param.setUuid("c4aaa278-e4b3-4915-b4eb-59a176928326");
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
            param.setDisplayedName("Gradient Duration Flow Comp");
            param.setDescription("Plateau duration of the gradient used for flow compensation ");
            param.setCategory(Category.Acquisition);
            param.setUuid("95fda8a3-e8d7-4d20-9e81-9bdb31136cf6");
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
            param.setUuid("1e6af408-b2ba-40d7-898c-fbbb936d7baa");
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
            param.setUuid("77383397-4b0f-4091-970d-d600ce8eac32");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    FOV_MULTISLAB("FOV_MULTISLAB") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("FOV_MULTISLAB");
            param.setDisplayedName("FOV - MultiSlab");
            param.setDescription("Info: FOV in slab direction for multislab acquisition");
            param.setCategory(Category.Acquisition);
            param.setUuid("b90f0851-983b-4b5e-8045-4f57ab05094b");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.012);
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
            param.setUuid("561c6f27-d9b2-4fd1-929c-034b0a304864");
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
            param.setUuid("6d1988a1-6d67-439a-bef1-5fbf5934d9f0");
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
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("35530cb2-7fe1-4780-9e93-43a106415c41");
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
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("e52704e0-b6fa-4db3-9bc3-e564190dafb3");
            param.setValue(true);
            param.setDefaultValue(true);
            return param;
        }
    },

    GRADIENT_ENABLE_READ("GRADIENT_ENABLE_READ") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_READ");
            param.setDisplayedName("Gradient Read");
            param.setDescription("Enable  frequency encoding gradient");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("e8b5622e-c7d7-41ae-ab7a-eb40879cf61e");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    GRADIENT_ENABLE_REWINDING("GRADIENT_ENABLE_REWINDING") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("GRADIENT_ENABLE_REWINDING");
            param.setDisplayedName("Gradient Rewinding");
            param.setDescription("Enable to do gradient rewind instead of spoiling");
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("0913979f-af29-474c-bf6e-3d6e8e40b965");
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
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("a44a45b4-05e8-4f5a-a8d4-431a1f7048e1");
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
            param.setLocked(true);
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("b1537257-7249-42af-a110-224dcde7af48");
            param.setValue(true);
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
            param.setUuid("b1fb19af-50dd-4482-b16e-d2eba7632126");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.67E-4);
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
            param.setUuid("a2e3081b-864c-4d99-a0e5-0e2e3474d823");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(2.3E-4);
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
            param.setUuid("88d5bdce-4690-438a-b244-59ce7136980a");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.67E-4);
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
            param.setUuid("d8fc2a23-08be-4fab-a849-e29a3a482e2e");
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setValue(asListNumber(40.0, 40.0, 40.0));
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
            param.setUuid("c7477a67-e60e-406c-88b1-a33916b646d0");
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
            param.setUuid("24a78e8c-26b2-4347-bac2-6f5000e4dfa1");
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
            param.setUuid("2df2f8b2-e7e2-415c-a28f-76d7e11d6cdf");
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
            param.setLocked(true);
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("6f23e6c4-edba-4dc2-88ef-99e6d69caee0");
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
            param.setLocked(true);
            param.setCategory(Category.Acquisition);
            param.setUuid("ae0670b0-35a8-4bd6-968b-5f324c5f0cfe");
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
            param.setUuid("c97cbd77-d0b0-4d0e-908e-1fc62dfcacb3");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.253E7);
            param.setDefaultValue(1.253E7);
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
            param.setUuid("af80d304-c30e-4983-a07d-aa68994fa821");
            param.setValue("Linear");
            param.setDefaultValue("Linear");
            param.setSuggestedValues(asList("Linear"));
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
            param.setUuid("76fb8195-3f12-4563-a53b-c23b2b7cf3e4");
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
            param.setUuid("ac2b34ac-5f06-451a-9b0d-df8c9e7a1e0a");
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
            param.setUuid("7d65fa51-281e-4383-9a81-13fb98f9e183");
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(1, 127, 127, 2, 3));
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
            param.setUuid("f81ab596-5d6d-4fe2-97b0-b6ac1e0956ab");
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
            param.setUuid("a3022667-fbfb-4680-a900-e9ebfa430731");
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
            param.setUuid("5d62d692-75f7-481e-824a-6d113b3f7543");
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
            param.setUuid("9337d70a-079c-4078-b438-9307b977b175");
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
            param.setUuid("fa221df5-9ab7-4dcc-9d32-0eb49a532826");
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
            param.setUuid("a67d993a-afd6-440b-b1b7-ba441a48ebb3");
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
            param.setUuid("f1fa4c91-bf80-4161-8d7b-50046f97c0e1");
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
            param.setDisplayedName("2D-Imaging");
            param.setDescription("Switch between 2D multislice (enable) or 3D acquisition (disable)");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("0d103275-f00c-459a-9a8f-20da22a63830");
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
            param.setUuid("ed84b6e4-c616-47c5-8848-0c5847319126");
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
            param.setUuid("56498278-4b75-4c88-9420-e6a79853cbe2");
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
            param.setUuid("53234cd6-f0e1-4f2c-8d52-b0cc66468a92");
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
            param.setUuid("1a9b97f4-4648-4145-92cf-587f742ca1c8");
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
            param.setUuid("1aa021a2-286c-466a-8c9f-b2cd509d256d");
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
            param.setUuid("6eb597b1-2d6f-4d11-8dbf-adabeb319802");
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
            param.setUuid("37b6360a-2f9e-40ed-83d7-10708d271efe");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(2147483647);
            param.setValue(24);
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
            param.setUuid("c3b60ab8-60af-4c36-a8e9-ec78cd3b7ade");
            param.setNumberEnum(NumberEnum.Scan);
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
            param.setUuid("3f45d8fb-3979-4c93-8eb6-995eac09198e");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(1.2781254725870714E8);
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
            param.setUuid("caba8e31-e46f-48d4-8383-3243314cfc8a");
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
            param.setUuid("971ae646-4908-4d1b-941b-6f4997bc103a");
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
            param.setUuid("531af524-7e90-42b0-a542-4b622367e4a7");
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
            param.setUuid("158b3fa7-4ddf-4d60-a7f7-a38d90368919");
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
            param.setUuid("fddfd0cc-8184-40e0-934c-7c771571896a");
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
            param.setUuid("79934eaa-a84d-4a2f-bb4d-66c22390d2ed");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.002);
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
            param.setUuid("8888f087-da25-40b1-805b-4286d6b84a7a");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.004);
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
            param.setUuid("2e6bca84-09da-467c-8a64-b00b51210a56");
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
            param.setUuid("001f4c53-a88c-4af2-80c9-636b588fbc4d");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Location);
            param.setValue(asListNumber(-0.002, 0.0, 0.0));
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
            param.setUuid("c13a1410-a118-4a56-b7a9-6ac79cf1945a");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.002);
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
            param.setUuid("53125b9e-94bd-40f5-9498-212b46248e0e");
            param.setNumberEnum(NumberEnum.Location);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.004);
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
            param.setUuid("11d5876d-b47d-49ad-82d0-6a87defbd7e1");
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
            param.setUuid("52e8dc95-2fff-4eed-a947-eba2db2be647");
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
            param.setUuid("3c9d5fce-3f13-48f6-aaba-74ffcc887620");
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
            param.setUuid("d4702f70-7786-44ca-b5ef-f6ce85f0b0be");
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
            param.setUuid("a6e924e0-96e3-42f0-9e35-f5289a401c8b");
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
            param.setUuid("c4df026b-529e-4259-9c25-01da2db66017");
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
            param.setUuid("5a6f5eb4-b5ed-4439-9d91-973582ec0832");
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
            param.setUuid("f1c3afc6-42b9-4322-ae69-988af7b8b3c7");
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
            param.setUuid("26a50f90-f314-4080-be94-2e46d72ce301");
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
            param.setUuid("ec36a947-d208-4422-ba34-2dabb0db7fd7");
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
            param.setUuid("a79bd9a8-5b58-4573-b82c-4416e97baf07");
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
            param.setUuid("7822254a-25e8-4aad-9604-e4dbbb7fac55");
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
            param.setUuid("b34d5aaa-896d-41dd-9d99-290fcef0aaf4");
            param.setNumberEnum(NumberEnum.Integer);
            param.setMinValue(1);
            param.setMaxValue(32);
            param.setValue(4);
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
            param.setUuid("9b427296-be65-4eed-b52b-29e4068a662b");
            param.setNumberEnum(NumberEnum.RxGain);
            param.setMinValue(0.0);
            param.setMaxValue(120.0);
            param.setValue(40.0);
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
            param.setUuid("f4c1958e-1832-45db-bbd1-ce2cd666885c");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.018000000000000002);
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
            param.setUuid("6f4ec98b-f6e6-4ef2-bf15-581f57381566");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(8.750000000000001E-4);
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
            param.setUuid("6efd41b4-1f4c-44a1-a5bd-f33769f92abd");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.01);
            param.setValue(8.750000000000001E-4);
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
            param.setUuid("8f842b7e-89f2-4ec9-b33c-1fe66ea14607");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(0.1);
            param.setValue(5.0E-4);
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
            param.setUuid("08ba1a26-616a-4abe-b5ca-7ef9590ed56f");
            param.setValue(true);
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
            param.setUuid("78bbfc95-ce06-416e-ad83-d2d1bd19dbe7");
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
            param.setDisplayedName("Satband - Enable");
            param.setDescription("Enable Saturation Band");
            param.setGroup(EnumGroup.Miscellaneous);
            param.setCategory(Category.Acquisition);
            param.setUuid("624634ab-51e9-4ed1-ae64-b840c43d5629");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    SATBAND_GRAD_AMP_SPOILER("SATBAND_GRAD_AMP_SPOILER") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_GRAD_AMP_SPOILER");
            param.setDisplayedName("SatBand - Spoiler Grad Amp");
            param.setDescription("Amplitude of the spoiler gradient following the saturation pulse. ");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("a8fda4b1-6054-4b76-9c0b-957dc41c9633");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
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
            param.setUuid("8c421bc6-815c-4486-8ea7-fef9abdb01a7");
            param.setValue("CRANIAL");
            param.setDefaultValue("CRANIAL");
            param.setSuggestedValues(asList("CRANIAL", "CAUDAL", "CRANIAL AND CAUDAL", "ANTERIOR", "POSTERIOR", "ANTERIOR AND POSTERIOR", "RIGHT", "LEFT", "RIGHT AND LEFT", "ALL"));
            return param;
        }
    },

    SATBAND_SPOILER_LENGTH("SATBAND_SPOILER_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_SPOILER_LENGTH");
            param.setDisplayedName("Satband - Spoiler Plateau Duration");
            param.setDescription("Duration of spoiler gradient after saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("90d301c6-e1ba-4786-8925-56c3ba6bb868");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.0E-4);
            param.setDefaultValue(0.0);
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
            param.setUuid("e88e4660-d3ee-42d2-b116-81df859b1673");
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
            param.setUuid("acaf4257-1214-431a-a5e9-b90b72635980");
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
            param.setUuid("53dfdf72-f766-4670-809c-b409c33116ad");
            param.setNumberEnum(NumberEnum.TxAmp);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(75.32704525411751);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SATBAND_TX_LENGTH("SATBAND_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SATBAND_TX_LENGTH");
            param.setDisplayedName("Satband - Tx Duration");
            param.setDescription("");
            param.setCategory(Category.Acquisition);
            param.setUuid("8d918b0c-1cf5-4f2e-80ec-fe65cf3651fd");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.001);
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
            param.setUuid("c30582b5-6bf7-4e3c-b0a0-fda6d360600d");
            param.setValue("GAUSSIAN");
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
            param.setUuid("9a852535-c163-4d4d-a2f0-e75b18817a25");
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
            param.setUuid("26665c25-c3d2-481e-ad83-4dc57f63bf5b");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(34.741);
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
            param.setUuid("00fc2c3e-168b-438a-be54-2e1369a6d45c");
            param.setValue("Version 2.1");
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
            param.setUuid("4724b61e-deb1-4d18-8993-1bba2dd1a640");
            param.setValue("GE_3D_AXI_80x80x24_MT_SATBAND");
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
            param.setUuid("5a6236db-84ad-4e92-a26b-d5cace8b7dc0");
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
            param.setUuid("ce529546-8028-4ff4-83ff-067b20919f35");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(-100.0);
            param.setMaxValue(100.0);
            param.setValue(50.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    SLICE_REFOCUSING_GRADIENT_RATIO("SLICE_REFOCUSING_GRADIENT_RATIO") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("SLICE_REFOCUSING_GRADIENT_RATIO");
            param.setDisplayedName("Gradient Refocusing Ratio Slice");
            param.setDescription("Ratio of the slice refocusing gradient");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("9cd36786-4e69-4ad5-965e-849d1d90dab4");
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
            param.setDisplayedName("SLICE_THICKNESS_INTERMEDIATE");
            param.setDescription("Intermediate Slice Thickness, value changes after slab concatenation");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("af5974d3-d28b-4789-b695-aabe9dc212bd");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(5.0E-4);
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
            param.setUuid("9efb3e96-df7e-4da4-8e41-92334fbb7c2f");
            param.setValue("Software version");
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
            param.setUuid("4f8d2519-6c90-4248-a0c3-53755a84d9c1");
            param.setNumberEnum(NumberEnum.LengthOffset);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(0.0);
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
            param.setUuid("19172d3d-67de-4f5b-8da4-e8998cf59005");
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
            param.setUuid("0d5bfd40-d42b-460e-9bba-f35049ae79bb");
            param.setNumberEnum(NumberEnum.SW);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E8);
            param.setValue(20011.52663934426);
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
            param.setUuid("17b18f4e-1ece-4fd5-894e-478955d0ca0e");
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
            param.setUuid("902f25b2-0199-4a71-a71c-747aca0c1e3a");
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
            param.setUuid("5699593d-bb12-4507-9e4d-9ebed1bde63e");
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
            param.setUuid("81ebb9cc-3cf9-46c4-bf97-a9b7d96d3dbb");
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
            param.setUuid("27f9d68d-3a40-4415-850c-a54a349d966e");
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
            param.setUuid("a3594e8b-be9b-465d-9b89-ea56c6a942e9");
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
            param.setUuid("8a580ad9-5075-494a-b3ca-a4ff28ce8783");
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
            param.setUuid("1f34c748-60d5-4bd9-9356-5489e605a10a");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(4.9999999999999996E-6);
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
            param.setUuid("d2a15eb2-50c6-4812-83a4-b513a583cd22");
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
            param.setUuid("5d8b8f9a-0225-4143-9a35-92a3efdf0f46");
            param.setValue(false);
            param.setDefaultValue(false);
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
            param.setUuid("4dff2c7a-30c8-47f3-9fc9-f4e3f6a157d3");
            param.setValue("ABOVE THE SLICE");
            param.setDefaultValue("BELOW THE SLICE");
            param.setSuggestedValues(asList("ABOVE THE SLICE", "BELOW THE SLICE"));
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
            param.setUuid("7472c394-758b-4a10-bf16-056679061503");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(50.0);
            param.setValue(20.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_MP_SPOILER_AMP("TOF3D_MP_SPOILER_AMP") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MP_SPOILER_AMP");
            param.setDisplayedName("TOF 3D - Spoiler Grad Amp");
            param.setDescription("Amplitude of the spoiler gradient following the TOF satband RF pulse. ");
            param.setGroup(EnumGroup.Gradient);
            param.setCategory(Category.Acquisition);
            param.setUuid("f114c612-594b-4a03-9386-335046d39dc4");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(40.0);
            param.setDefaultValue(40.0);
            return param;
        }
    },

    TOF3D_MT_BANDWIDTH("TOF3D_MT_BANDWIDTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_BANDWIDTH");
            param.setDisplayedName("TOF 3D - MT Sat Bandwidth");
            param.setDescription("Bandwidth of TOF MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("22c03ddf-ee72-45f3-a16e-bae03c512dcc");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(500.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_MT_FLIP_ANGLE("TOF3D_MT_FLIP_ANGLE") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_FLIP_ANGLE");
            param.setDisplayedName("TOF 3D - MT Flip Angle");
            param.setDescription("Nnominal flip angle of TOF 3D MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("4bde5818-ccea-473e-81b2-4492a2262c1d");
            param.setNumberEnum(NumberEnum.RotationAngle);
            param.setMinValue(4.9E-324);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(100.0);
            param.setDefaultValue(360.0);
            return param;
        }
    },

    TOF3D_MT_GAMMA_B1("TOF3D_MT_GAMMA_B1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_GAMMA_B1");
            param.setDisplayedName("TOF 3D - MT Gamma B1");
            param.setDescription("Info: Gamma B1 value of TOF 3D MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("31453ed8-0afb-4feb-8842-b8a0bd8f5500");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(98.41058104731195);
            param.setDefaultValue(50.0);
            return param;
        }
    },

    TOF3D_MT_INDIV("TOF3D_MT_INDIV") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TOF3D_MT_INDIV");
            param.setDisplayedName("TOF 3D - One MT Sat Per Slab");
            param.setDescription("True if running only one saturation pulse per slab");
            param.setCategory(Category.Acquisition);
            param.setUuid("a7570ba7-238d-4f5f-bf88-f51888ded74f");
            param.setValue(false);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF3D_MT_TX_LENGTH("TOF3D_MT_TX_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_MT_TX_LENGTH");
            param.setDisplayedName("TOF 3D - MT Tx Length");
            param.setDescription("Info :uration of TOF 3D MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("134c5884-b71d-4fa0-9111-0438d8b019fe");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(0.0064);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TOF3D_SPOILER_LENGTH("TOF3D_SPOILER_LENGTH") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TOF3D_SPOILER_LENGTH");
            param.setDisplayedName("TOF 3D - Spoiler Grad Plateau Duration");
            param.setDescription("Duration of spoiler gradient after MT saturation pulse");
            param.setCategory(Category.Acquisition);
            param.setUuid("5eadc718-c95b-46a8-b402-f79d466ec5eb");
            param.setNumberEnum(NumberEnum.Time);
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setValue(5.0E-4);
            param.setDefaultValue(5.0E-4);
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
            param.setUuid("12323d21-0fd5-4bfb-8a13-d7144a4ca30f");
            param.setNumberEnum(NumberEnum.Double);
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(-0.0);
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
            param.setUuid("16a8e9c2-619d-4790-a091-50898d1053ae");
            param.setValue(true);
            param.setDefaultValue(false);
            return param;
        }
    },

    TOF_MT_TX_SHAPE("TOF_MT_TX_SHAPE") {
        public Param build() {
            TextParam param = new TextParam();
            param.setName("TOF_MT_TX_SHAPE");
            param.setDisplayedName("TOF 3D - MT TX Shape");
            param.setDescription("Tx shape of MT pulse in TOF 3D");
            param.setCategory(Category.Acquisition);
            param.setUuid("b9658485-d02d-453d-a1b0-f04a94a12b11");
            param.setValue("GAUSSIAN");
            param.setDefaultValue("HARD");
            param.setSuggestedValues(asList("HARD", "GAUSSIAN", "SINC3", "SINC5", "SLR_8_5152", "SLR_4_2576"));
            return param;
        }
    },

    TOF_SB_OFFSET("TOF_SB_OFFSET") {
        public Param build() {
            HzPpmNumberParam param = new HzPpmNumberParam();
            param.setName("TOF_SB_OFFSET");
            param.setDisplayedName("TOF 3D - MT Offset Frequency");
            param.setDescription("Additional global requency offset applied to all TOF satband RF pulses");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("105e9d00-5476-434c-9a21-7bf8d733f9e5");
            param.setNumberEnum(NumberEnum.FrequencyOffset);
            param.setMinValue(-1.5E9);
            param.setMaxValue(1.5E9);
            param.setValue(1000.0);
            param.setDefaultValue(0.0);
            param.setSuggestedValues(asListNumber(-1500.0, -1250.0, -1000.0, -750.0, -500.0, 0.0, 500.0, 750.0, 1000.0, 1250.0, 1500.0));
            param.setInitialNumberEnum(NumberEnum.FrequencyOffset);
            param.setUuidBaseFrequency("3f45d8fb-3979-4c93-8eb6-995eac09198e");
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
            param.setUuid("97cb5733-670e-470a-bafc-fad423059bd3");
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
            param.setUuid("d40c658e-1b4e-4077-93c7-5ad274e2a688");
            param.setMinValue(0);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Scan);
            param.setValue(asListNumber(1, 25, 1, 26, 1, 27, 1, 28, 1, 29));
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
            param.setUuid("f8cdf9da-2f6b-48b1-8ba1-2cd1d5da2e1d");
            param.setValue("Sequential4D");
            param.setDefaultValue("Sequential4D");
            param.setSuggestedValues(asList("Sequential4D"));
            return param;
        }
    },

    TRANSMIT_FREQ_1("TRANSMIT_FREQ_1") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_1");
            param.setDisplayedName("Transmit Freq 1");
            param.setDescription("Transmit frequency of the first sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("0fa73160-74c9-4984-b918-e3c83d4a6d25");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_2("TRANSMIT_FREQ_2") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_2");
            param.setDisplayedName("Transmit Freq 2");
            param.setDescription("Transmit frequency of the second sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("72563d44-a489-4ce0-86f9-d3a6a94f9aa5");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_3("TRANSMIT_FREQ_3") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_3");
            param.setDisplayedName("Transmit Freq 3");
            param.setDescription("Transmit frequency of the third sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("456a77af-bee4-46c7-9cad-235ef5ced4a7");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRANSMIT_FREQ_4("TRANSMIT_FREQ_4") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("TRANSMIT_FREQ_4");
            param.setDisplayedName("Transmit Freq 4");
            param.setDescription("Transmit frequency of the fourth sequence channel");
            param.setLocked(true);
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("1268af26-5440-4b06-837f-77f8003a3643");
            param.setNumberEnum(NumberEnum.Frequency);
            param.setMinValue(0.0);
            param.setMaxValue(3.0E9);
            param.setValue(0.0);
            param.setDefaultValue(0.0);
            return param;
        }
    },

    TRIGGER_EXTERNAL("TRIGGER_EXTERNAL") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TRIGGER_EXTERNAL");
            param.setDisplayedName("Trigger External");
            param.setDescription("Enable to synchronize the acquisition with external trigger");
            param.setGroup(EnumGroup.Delay);
            param.setCategory(Category.Acquisition);
            param.setUuid("b7225008-61a4-434e-bcfa-41e45d622e21");
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
            param.setUuid("8f5fa87b-c81b-4262-94bc-4f74faa590a0");
            param.setMinValue(0.0);
            param.setMaxValue(1.0E9);
            param.setNumberEnum(NumberEnum.Time);
            param.setValue(asListNumber(0.0, 12.161, 24.322));
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
            param.setUuid("6d533b2b-46db-468e-81d9-e86ffe3b2e90");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(43.41717182169603);
            param.setDefaultValue(40.0);
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
            param.setUuid("a53c7df0-f9e1-4b7a-b09d-246bf73716b7");
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
            param.setUuid("13a8651e-9ec7-4485-8162-a7726c6194bb");
            param.setNumberEnum(NumberEnum.TxAtt);
            param.setMinValue(0);
            param.setMaxValue(63);
            param.setValue(10);
            param.setDefaultValue(36);
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
            param.setUuid("b9b88155-f184-4470-afe8-9488b7a4130b");
            param.setMinValue(-1.7976931348623157E308);
            param.setMaxValue(1.7976931348623157E308);
            param.setNumberEnum(NumberEnum.Double);
            param.setValue(asListNumber(0.9500000000000001, 1.35, 2.55, 4.25, 0.0, 0.0, 4.25));
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
            param.setUuid("74f1e335-ecc6-412b-a4a5-a33128535fbd");
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
            param.setUuid("b2c4e5d0-953b-4178-bafe-db217492ca70");
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
            param.setDisplayedName("TX Route");
            param.setDescription("Info: Selected physical transmit channel");
            param.setGroup(EnumGroup.Emission);
            param.setCategory(Category.Acquisition);
            param.setUuid("66671cf4-50af-4f69-89fd-ffbc24081eec");
            param.setMinValue(-2147483648);
            param.setMaxValue(2147483647);
            param.setNumberEnum(NumberEnum.Integer);
            param.setValue(asListNumber(0));
            return param;
        }
    },

    TX_SELECTION_PULSE("TX_SELECTION_PULSE") {
        public Param build() {
            BooleanParam param = new BooleanParam();
            param.setName("TX_SELECTION_PULSE");
            param.setDisplayedName("Tx Selection Pulse");
            param.setDescription("True: use TX_BANDWIDTH_FACTOR for slab selection, False: use TX_BANDWIDTH_FACTOR_3D for slab selection");
            param.setCategory(Category.Acquisition);
            param.setUuid("b3755568-2dd4-415b-babb-64bb9ee18ce7");
            param.setValue(true);
            param.setDefaultValue(false);
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
            param.setUuid("b5f2a190-a8eb-4d22-9905-df295f4c368f");
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
            param.setUuid("0c39cd10-8c2b-4df3-ae75-025ab7e6a807");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(80);
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
            param.setUuid("8ef6d0ad-6cae-448f-af79-cb2ca03126df");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(80);
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
            param.setUuid("13c8c179-b598-4424-a745-4a8aa15f0e0c");
            param.setNumberEnum(NumberEnum.Scan);
            param.setMinValue(1);
            param.setMaxValue(65536);
            param.setValue(24);
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
            param.setUuid("5bf4eb84-6d3d-4c54-a575-b17b9c78940a");
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
            param.setDisplayedName("Zero-filling Phase");
            param.setDescription("Percent of the k-space that is acquired, the rest will be completed by zeros in the phase encoding direction (Zero-filling interpolation)");
            param.setGroup(EnumGroup.Dimension);
            param.setCategory(Category.Acquisition);
            param.setUuid("36b81a97-321b-4fc5-90ff-352c942da842");
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
            param.setUuid("80e729c1-19ba-4e8a-b960-1506fd8f0494");
            param.setNumberEnum(NumberEnum.PERCENT);
            param.setMinValue(0.0);
            param.setMaxValue(100.0);
            param.setValue(100.0);
            param.setDefaultValue(100.0);
            return param;
        }
    },

    USER_SLICE_THICKNESS("USER_SLICE_THICKNESS") {
        public Param build() {
            NumberParam param = new NumberParam();
            param.setName("USER_SLICE_THICKNESS");
            param.setDisplayedName("Slice Thickness");
            param.setDescription("Nominal Slice Thickness (value does not change after SlabConcatenation)");
            param.setCategory(Category.Acquisition);
            param.setUuid("568f039a-a64a-4d5c-9af6-614aa5e6d9a7");
            param.setNumberEnum(NumberEnum.Length);
            param.setMinValue(0.0);
            param.setMaxValue(1.7976931348623157E308);
            param.setValue(5.0E-4);
            param.setDefaultValue(0.0);
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
            param.setUuid("ed5760b7-a263-4e3b-908e-a0503708734c");
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
            param.setUuid("63338c14-6eb5-4a3f-a44e-488a7a536ea9");
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
