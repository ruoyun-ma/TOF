//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.gradientecho;


public interface GradientEchoSequenceParams {

    //-- public params

    /**
     * Group: Delay, Type: TextParam
     */
    String Ext_trig_source = "Ext_trig_source";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_flyback = "Grad_enable_flyback";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_phase_2D = "Grad_enable_phase_2D";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_phase_3D = "Grad_enable_phase_3D";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_read = "Grad_enable_read";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_slice = "Grad_enable_slice";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_spoiler_phase = "Grad_enable_spoiler_phase";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_spoiler_read = "Grad_enable_spoiler_read";

    /**
     * Group: Gradient, Type: BooleanParam
     */
    String Grad_enable_spoiler_slice = "Grad_enable_spoiler_slice";

    /**
     * Group: Miscellaneous, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Phi = "Gradient Angle Phi";

    /**
     * Group: Miscellaneous, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Psi = "Gradient Angle Psi";

    /**
     * Group: Miscellaneous, Type: NumberParam - Angle (°)
     */
    String Gradient_Angle_Theta = "Gradient Angle Theta";

    /**
     * Group: Gradient, Type: EnumParam
     */
    String Gradient_axe_phase = "Gradient_axe_phase";

    /**
     * Group: Gradient, Type: EnumParam
     */
    String Gradient_axe_read = "Gradient_axe_read";

    /**
     * Group: Reception, Type: NumberParam - Frequency (Hz)
     */
    String Intermediate_frequency = "Intermediate_frequency";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_1d = "Nb_1d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_2d = "Nb_2d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_3d = "Nb_3d";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Nb_4d = "Nb_4d";

    /**
     * Group: Dimension, Type: NumberParam - Integer
     */
    String Nb_echo = "Nb_echo";

    /**
     * Group: Dimension, Type: NumberParam - Integer
     */
    String Nb_interveaved_slice = "Nb_interveaved_slice";

    /**
     * Group: Dimension, Type: NumberParam - AcquPoint (pts)
     */
    String Nb_point = "Nb_point";

    /**
     * Group: Transmit, Type: BooleanParam
     */
    String Phase_reset = "Phase_reset";

    /**
     * Group: Dimension, Type: NumberParam - Scan
     */
    String Pre_scan = "Pre_scan";

    /**
     * Group: Reception, Type: NumberParam - RxGain (dB)
     */
    String Rx_gain = "Rx_gain";

    /**
     * Group: Reception, Type: NumberParam - SW (Hz)
     */
    String Spectral_width = "Spectral_width";

    /**
     * Group: Delay, Type: EnumParam
     */
    String Synchro_trigger = "Synchro_trigger";

    /**
     * Group: Transmit, Type: NumberParam - TxAtt (dB)
     */
    String Tx_att = "Tx_att";

    /**
     * Group: Transmit, Type: NumberParam - Frequency (Hz)
     */
    String Tx_frequency = "Tx_frequency";

    /**
     * Group: Miscellaneous, Type: TextParam
     */
    String Tx_nucleus = "Tx_nucleus";


    //-- public tables

    /**
     * Group: Transmit, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String FreqOffset_RFSpoiling = "FreqOffset_RFSpoiling";

    /**
     * Group: Reception, Order: 3D - FreqOffset (Hz)
     */
    String FreqOffset_rx_3Dcomp = "FreqOffset_rx_3Dcomp";

    /**
     * Group: Reception, Order: 3D - FreqOffset (Hz)
     */
    String FreqOffset_rx_3Dprep = "FreqOffset_rx_3Dprep";

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String FreqOffset_rx_comp = "FreqOffset_rx_comp";

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String FreqOffset_rx_prep = "FreqOffset_rx_prep";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String FreqOffset_tx_comp = "FreqOffset_tx_comp";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String FreqOffset_tx_prep = "FreqOffset_tx_prep";

    /**
     * Group: Transmit, Order: 4D+Loop - FreqOffset (Hz)
     */
    String Frequency_offset_init = "Frequency_offset_init";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_flyback = "Grad_amp_flyback";

    /**
     * Group: Gradient, Order: 2D - GradAmp (%)
     */
    String Grad_amp_phase_2D_prep = "Grad_amp_phase_2D_prep";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_phase_3D_prep = "Grad_amp_phase_3D_prep";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_read_prep = "Grad_amp_read_prep";

    /**
     * Group: Gradient, Order: Loop - GradAmp (%)
     */
    String Grad_amp_read_read = "Grad_amp_read_read";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_slice = "Grad_amp_slice";

    /**
     * Group: Gradient, Order: 2D - GradAmp (%)
     */
    String Grad_amp_spoiler_phase = "Grad_amp_spoiler_phase";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_spoiler_read = "Grad_amp_spoiler_read";

    /**
     * Group: Gradient, Order: 4D+Loop - GradAmp (%)
     */
    String Grad_amp_spoiler_slice = "Grad_amp_spoiler_slice";

    /**
     * Group: Gradient, Order: 1D - GradShape (%)
     */
    String Grad_shape_rise_down = "Grad_shape_rise_down";

    /**
     * Group: Gradient, Order: 1D - GradShape (%)
     */
    String Grad_shape_rise_up = "Grad_shape_rise_up";

    /**
     * Group: Reception, Order: 4D+LoopB - FreqOffset (Hz)
     */
    String Rx_freq_offset = "Rx_freq_offset";

    /**
     * Group: Reception, Order: 4D+Loop - Phase (°)
     */
    String Rx_phase = "Rx_phase";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_btw_dyn_frames = "Time_btw_dyn_frames";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_flush_delay = "Time_flush_delay";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_flyback = "Time_flyback";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_flyback_ramp = "Time_flyback_ramp";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_phase_top = "Time_grad_phase_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_ramp = "Time_grad_ramp";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_grad_spoiler_top = "Time_grad_spoiler_top";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_last_delay = "Time_last_delay";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_min_instruction = "Time_min_instruction";

    /**
     * Group: Delay, Order: 1D - Time (s)
     */
    String Time_rf_spoiling = "Time_rf_spoiling";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_rx = "Time_rx";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_TE_delay1 = "Time_TE_delay1";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_TE_delay2 = "Time_TE_delay2";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_TR_delay = "Time_TR_delay";

    /**
     * Group: Delay, Order: 4D - Time (s)
     */
    String Time_trigger_delay = "Time_trigger_delay";

    /**
     * Group: Delay, Order: 4D+Loop - Time (s)
     */
    String Time_tx = "Time_tx";

    /**
     * Group: Transmit, Order: 4D+Loop - TxAmp (%)
     */
    String Tx_amp = "Tx_amp";

    /**
     * Group: Transmit, Order: 3D+Loop - FreqOffset (Hz)
     */
    String Tx_freq_offset = "Tx_freq_offset";

    /**
     * Group: Transmit, Order: 4D+Loop - Phase (°)
     */
    String Tx_phase = "Tx_phase";

    /**
     * Group: Transmit, Order: 1D - TxShape (%)
     */
    String Tx_shape = "Tx_shape";

    /**
     * Group: Transmit, Order: 1D - Phase (°)
     */
    String Tx_shape_phase = "Tx_shape_phase";

}