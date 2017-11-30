package rs2d.sequence.gradientecho;

import rs2d.spinlab.data.transformPlugin.TransformPlugin;
import rs2d.spinlab.instrument.Instrument;
import rs2d.spinlab.instrument.InstrumentTxChannel;
import rs2d.spinlab.instrument.probe.Probe;
import rs2d.spinlab.instrument.probe.ProbeChannelPower;
import rs2d.spinlab.instrument.util.GradientMath;
import rs2d.spinlab.instrument.util.TxMath;
import rs2d.spinlab.plugins.loaders.LoaderFactory;
import rs2d.spinlab.plugins.loaders.PluginLoaderInterface;
import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.table.Shape;
import rs2d.spinlab.sequence.table.Table;
import rs2d.spinlab.sequence.table.Utility;
import rs2d.spinlab.sequence.table.generator.TableGeneratorInterface;
import rs2d.spinlab.tools.param.NumberParam;
import rs2d.spinlab.tools.param.Param;
import rs2d.spinlab.tools.table.Order;
import rs2d.spinlab.tools.utility.Nucleus;

import java.util.List;

public class RFPulse {
    private Table amplitudeTable = null;
    private Param attParam = null;
    private Table phase = null;
    private Table FrequencyOffsetTable = null;

    private Table timeTable = null;
    private Shape shape = null;
    private Shape shapePhase = null;

    int numberOfFreqOffset = -1;
    Order FrequencyOffsetOrder = Order.FourLoopB;

    private double[] txFrequencyOffsetTable = null;
    private double tx_att = Double.NaN;

    private double tx_amp90 = Double.NaN;
    private double tx_amp180 = Double.NaN;
    private double tx_amp = Double.NaN;
    private double flipAngle = Double.NaN;
    //    double[] timeArray = null;
//    double amplitude;
    double time;
    double observeFrequency = Double.NaN;
    private Nucleus nucleus;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  Constructor
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public RFPulse(Param attPar, Table amplitudeTab, Table phaseTab,
                   Table timeTab, Shape shape, Shape shapePhase, Table offsetFreqTab) {
        amplitudeTable = amplitudeTab;
        attParam = attPar;
        phase = phaseTab;

        timeTable = timeTab;
        this.shape = shape;
        this.shapePhase = shapePhase;

        time = timeTable.get(0).doubleValue();
        FrequencyOffsetTable = offsetFreqTab;
//        gMax = GradientMath.getMaxGradientStrength();
//        computeShapeRiseTime(rampTimeUpTable, rampTimeDownTable);
//        prepareEquivalentTime();
    }


    public RFPulse(Table timeTab, Table offsetFreqTab) {
        timeTable = timeTab;

        time = timeTab.get(0).doubleValue();
        FrequencyOffsetTable = offsetFreqTab;
    }


    public static RFPulse createRFPulse(Sequence sequence, String txAttParam, String amplitudeTab, String txPhaseTab,
                                        String timeTab, String shape, String shapePhaseShape, String offsetTab) {
        return new RFPulse(sequence.getPublicParam(txAttParam), sequence.getPublicTable(amplitudeTab), sequence.getPublicTable(txPhaseTab),
                sequence.getPublicTable(timeTab), (Shape) sequence.getPublicTable(shape), (Shape) sequence.getPublicTable(shapePhaseShape), sequence.getPublicTable(offsetTab));
    }

    public static RFPulse createRFPulse(Sequence sequence, String timeTab, String offsetTab) {
        return new RFPulse(sequence.getPublicTable(timeTab), sequence.getPublicTable(offsetTab));
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  general  methodes
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public double getTime() {
        return time;
    }

    public double getAtt() {
        return tx_att;
    }

    public double getAmp() {
        return tx_amp;
    }

    public double getAmp90() {
        return tx_amp90;
    }

    public double getAmp180() {
        return tx_amp180;
    }

    public Order getFrequencyOffsetOrder() {
        return FrequencyOffsetOrder;
    }

    public int getNumberOfFreqOffset() {
        return numberOfFreqOffset;
    }


    public void setAtt(double att) {
        tx_att = att;
        attParam.setValue(tx_att);

    }
    public void setAtt(NumberParam att) {
        tx_att = att.getValue().intValue();
        attParam.setValue(tx_att);

    }

    public void setAmp(double amp) {
        tx_amp = amp;
        setSequenceTableSingleValue(amplitudeTable, tx_amp);
        flipAngle = tx_amp * 90 / tx_amp90;
    }
    public void setAmp(NumberParam ampParam) {
        tx_amp = ampParam.getValue().doubleValue();
        setSequenceTableSingleValue(amplitudeTable, tx_amp);
        flipAngle = tx_amp * 90 / tx_amp90;
    }

    public void setAmp90(double amp90) {
        tx_amp90 = amp90;
    }

    public void setAmp180(double amp180) {
        tx_amp180 = amp180;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  Calculation Amp Att...
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public boolean setAutoCalib(double flipAngle, double observe_frequency, List<Integer> txRoute, Nucleus nucleus) {
        InstrumentTxChannel txCh = Instrument.instance().getTxChannels().get(txRoute.get(0));
        this.flipAngle = flipAngle;
        observeFrequency = observe_frequency;
        this.nucleus = nucleus;
        boolean test_change_time = prepTxAtt(80, txCh);
        // Calculate amp with the new and real Att
        tx_amp90 = prepTxAmp90(time, txCh);
        tx_amp180 = prepTxAmp180(time, txCh);

        setSequenceTableSingleValue(amplitudeTable, tx_amp90 * flipAngle / 90);
        attParam.setValue(tx_att);
        // set calculated parameters to display values & sequence
        return test_change_time;
    }


    private boolean prepTxAtt(double tx_amp, InstrumentTxChannel txCh) {
        boolean test_change_time = true;
        Probe probe = Instrument.instance().getTransmitProbe();
        ProbeChannelPower pulse = TxMath.getProbePower(probe, null, nucleus.name());
        double instrument_length_180 = pulse.getHardPulse180().x;
        double power_factor = Utility.powerFillingFactor(shape);       // get RF pulse power factor from instrument to calculate RF pulse amplitude
        double instrument_power_180 = pulse.getHardPulse180().y / power_factor;
        double power_180 = instrument_power_180 * Math.pow(instrument_length_180 / time, 2);

        if (power_180 > pulse.getMaxRfPowerPulsed()) {  // TX LENGTH 90 MIN
            time = Math.ceil(instrument_length_180 / Math.sqrt(pulse.getMaxRfPowerPulsed() / instrument_power_180) * 10000) / 10000.0;
            setSequenceTableSingleValue(timeTable, time);
            power_180 = instrument_power_180 * Math.pow(instrument_length_180 / time, 2);
            test_change_time = false;
        }
        // Calculate Att to get a 180° RF pulse around 80% amp
        tx_att = 1 + (int) TxMath.getTxAttFor(power_180, txCh, tx_amp, observeFrequency);
        return test_change_time;
    }

    private double prepTxAmp90(double length, InstrumentTxChannel txCh) {
        double tx_amp;
        Probe probe = Instrument.instance().getTransmitProbe();
        ProbeChannelPower pulse = TxMath.getProbePower(probe, null, nucleus.name());
        double power_factor = Utility.powerFillingFactor(shape);       // get RF pulse power factor from instrument to calculate RF pulse amplitude
        double instrument_power_90 = pulse.getHardPulse90().y / power_factor;
        double instrument_length = pulse.getHardPulse90().x;
        double power = instrument_power_90 * Math.pow(instrument_length / length, 2);
        tx_amp = TxMath.getTxAmpFor(power, txCh, tx_att, observeFrequency);
        return tx_amp;
    }

    private double prepTxAmp180(double length, InstrumentTxChannel txCh) {
        double tx_amp;
        Probe probe = Instrument.instance().getTransmitProbe();
        ProbeChannelPower pulse = TxMath.getProbePower(probe, null, nucleus.name());
        double power_factor = Utility.powerFillingFactor(shape);       // get RF pulse power factor from instrument to calculate RF pulse amplitude
        double instrument_power_180 = pulse.getHardPulse180().y / power_factor;
        double instrument_length = pulse.getHardPulse180().x;
        double power = instrument_power_180 * Math.pow(instrument_length / length, 2);
        tx_amp = TxMath.getTxAmpFor(power, txCh, tx_att, observeFrequency);
        return tx_amp;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  Shape
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void setShape(String pulseName, int numberOfPoint, String window) throws Exception {
        shapePhase.clear();
        if ("GAUSSIAN".equalsIgnoreCase(pulseName)) {
            setShapeTableValuesFromGaussGen(shape, numberOfPoint, 0.250, 100, false);
        } else if ("SINC3".equalsIgnoreCase(pulseName)) {
            setShapeTableValuesFromSincGen(shape, numberOfPoint, 2, 100, true, window);
            for (int i = 0; i < (numberOfPoint); i++) {
                shapePhase.add((i < ((int) (numberOfPoint / 4.0)))
                        || (i >= ((int) (numberOfPoint * 3.0 / 4.0))) ? 180 : 0);
            }
        } else if ("SINC5".equalsIgnoreCase(pulseName)) {
            setShapeTableValuesFromSincGen(shape, numberOfPoint, 3, 100, true, window);
            for (int i = 0; i < (numberOfPoint); i++) {
                shapePhase.add(((i > ((int) (numberOfPoint * 1.0 / 6.0)) && (i <= ((int) (numberOfPoint * 2.0 / 6.0))))
                        || (i > ((int) (numberOfPoint * 4.0 / 6.0)) && (i <= ((int) (numberOfPoint * 5.0 / 6.0)))))
                        ? 180 : 0);
            }

        } else if ("HARD".equalsIgnoreCase(pulseName)) {
            shape.clear();    // set to HARD pulse
            shapePhase.clear();    // set to HARD pulse
            shape.setFirst(100);
            shapePhase.setFirst(0);

        }
    }

    /**
     * Generate a table of element with a Sinus Cardinal generator
     *
     * @param table   The table to be set
     * @param nbpoint The number of point of the generated sinus cardinal
     * @param nblobe  The number of lob of the generated sinus cardinal
     * @param amp     The amplitude of the generated sinus cardinal (in %)
     * @param abs     true if you want the absolute values and false otherwise
     */
    private void setShapeTableValuesFromSincGen(Table table, int nbpoint, int nblobe, double amp, Boolean abs, String window) throws Exception {
        String name = "Sinus Cardinal with Apodisation";
        TableGeneratorInterface gen = loadTableGenerator(name);
        if (gen == null) {
            throw new IllegalStateException("Table generator not found: " + name);
        }
        table.setGenerator(gen);
        gen.getParams().get(0).setValue(nbpoint);
        gen.getParams().get(1).setValue(nblobe);
        gen.getParams().get(2).setValue(amp);
        gen.getParams().get(3).setValue(abs);//abs
        gen.getParams().get(4).setValue(window);//abs
        gen.generate();
    }

    /**
     * Generate a table of element with a Gaussian generator
     *
     * @param table   The table to be set
     * @param nbpoint The number of point of the generated gaussian
     * @param width   The width of the generated gaussian
     * @param amp     The amplitude of the generated gaussian (in %)
     * @param abs     true if you want the absolute values and false otherwise
     */
    private void setShapeTableValuesFromGaussGen(Table table, int nbpoint, double width, double amp, Boolean abs) throws Exception {
        TableGeneratorInterface gen = null;
        gen = loadTableGenerator("Gaussian");
        gen.getParams().get(0).setValue(nbpoint);
        gen.getParams().get(1).setValue(width);
        gen.getParams().get(2).setValue(amp);
        gen.getParams().get(3).setValue(abs);//abs

        table.setGenerator(gen);
        if (gen == null) {
            table.clear();
            table.setFirst(100);
        } else {
            gen.generate();
        }
    }

    private TableGeneratorInterface loadTableGenerator(String generatorName) throws Exception {

        TableGeneratorInterface gen = null;

        PluginLoaderInterface loader = LoaderFactory.getTableGeneratorPluginLoader();
        if (loader.containsPlugin(generatorName)) {
            gen = (TableGeneratorInterface) loader.getPluginByName(generatorName);
        }
        return gen;

    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    //                  Offset Fequency
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public void prepareOffsetFreqMultiSlice(Gradient gradSlice, int nbSlice, double spacing_between_slice, double offCenterDistance3D) {
        numberOfFreqOffset = nbSlice;
        double grad_amp_slice_mTpm = gradSlice.getAmplitude_mTpm();
        double frequencyCenter3D90 = -grad_amp_slice_mTpm * offCenterDistance3D * (GradientMath.GAMMA);

        double multi_planar_fov = (numberOfFreqOffset - 1) * (spacing_between_slice + gradSlice.getSliceThickness());
        double multiPlanarFreqOffset = multi_planar_fov * grad_amp_slice_mTpm * (GradientMath.GAMMA);
        txFrequencyOffsetTable = new double[numberOfFreqOffset];
        for (int i = 0; i < numberOfFreqOffset; i++) {
            double tx_frequency_offset = (multiPlanarFreqOffset / 2) - (numberOfFreqOffset == 1 ? 0 : i * multiPlanarFreqOffset / (numberOfFreqOffset - 1)) + frequencyCenter3D90;
            txFrequencyOffsetTable[i] = tx_frequency_offset;
        }
    }

    public void reoderOffsetFreq(TransformPlugin plugin, int acquPerSliceInScan1D, int slices_acquired_in_single_scan) {

        double sliceNumber;
        double[] offset_table = new double[numberOfFreqOffset];
        for (int k = 0; k < numberOfFreqOffset; k++) {
            int[] indexScan = plugin.invTransf(0, 0, k, 0);
            sliceNumber = indexScan[0] / (acquPerSliceInScan1D) + indexScan[2] * slices_acquired_in_single_scan;
            offset_table[(int) sliceNumber] = txFrequencyOffsetTable[k];
        }
        txFrequencyOffsetTable = offset_table;
        //   sliceNumber = plugin.getSlicecanOrder(k);

//
//
//            public int sliceNumber = plugin.getSliceScanOrder(int k){
//                return k;
//            }
//            }
//            @Override
//            public int sliceNumber = plugin.getSlicecanOrder(int k){
//                int[] indexScan = invTransf(0, 0, k, 0);
//                sliceNumber = indexScan[0] / (matrix1D * etl) + indexScan[2] * matrix3D / nbshoot;
//                return k
//            }
//


    }

    public void setFrequencyOffset(Order order) {
        FrequencyOffsetOrder = order;
        setFrequencyOffset();
    }
    public void setFrequencyOffset() {
        setSequenceTableValues(FrequencyOffsetTable, FrequencyOffsetOrder);
        for (int k = 0; k < numberOfFreqOffset; k++) {
            FrequencyOffsetTable.add(txFrequencyOffsetTable[k]);
        }
    }

    public double getFrequencyOffset(int k) {
        return txFrequencyOffsetTable[k];
    }
    public void setCompensationFrequencyOffset(RFPulse pulse , double ratio) {
        FrequencyOffsetOrder = pulse.getFrequencyOffsetOrder();
        numberOfFreqOffset = pulse.getNumberOfFreqOffset();
        txFrequencyOffsetTable = new double[numberOfFreqOffset];
        for (int k = 0; k < numberOfFreqOffset; k++) {
            txFrequencyOffsetTable[k] = -((pulse.getFrequencyOffset(k) * pulse.getTime()*ratio) % 1) / time;
        }
        setFrequencyOffset();
    }
    public void setCompensationFrequencyOffsetWithTime(RFPulse pulse , double time) {
        FrequencyOffsetOrder = pulse.getFrequencyOffsetOrder();
        numberOfFreqOffset = pulse.getNumberOfFreqOffset();
        txFrequencyOffsetTable = new double[numberOfFreqOffset];
        for (int k = 0; k < numberOfFreqOffset; k++) {
            txFrequencyOffsetTable[k] = -((pulse.getFrequencyOffset(k) * time) % 1) / this.time;
        }
        setFrequencyOffset();
    }

    public void setFrequencyOffsetReadout(Gradient grad, double off_center_distance)    {
        numberOfFreqOffset = 1;
        double grad_amp_read_read_mTpm = grad.getAmplitude_mTpm();// amplitude in T/m
        txFrequencyOffsetTable = new double[numberOfFreqOffset];
        txFrequencyOffsetTable[0]= - grad_amp_read_read_mTpm * off_center_distance * (GradientMath.GAMMA);
        setSequenceTableValues(FrequencyOffsetTable, FrequencyOffsetOrder,txFrequencyOffsetTable[0]);
    }

    public void setFrequencyOffsetForPhaseShift(double angleDegree) {
        numberOfFreqOffset = 1;
        txFrequencyOffsetTable = new double[numberOfFreqOffset];
        txFrequencyOffsetTable[0]= - Math.round((((angleDegree / 360.0) % 1.0) / this.time));
        setSequenceTableValues(FrequencyOffsetTable, FrequencyOffsetOrder,txFrequencyOffsetTable[0]);
        return ;
    }
    // ---------------------------------------------------------------
    // ----------------- General Methode----------------------------------------------
    private void setSequenceTableSingleValue(Table table, double... values) {
        // uses Order.One because there are no tables in this dimension: compilation issue
        setSequenceTableValues(table, Order.FourLoop, values);
    }

    private void setSequenceTableValues(Table table, Order order, double... values) {
        table.clear();
        table.setOrder(order);
        table.setLocked(true);
        for (double value : values) {
            table.add(value);
        }
    }

}