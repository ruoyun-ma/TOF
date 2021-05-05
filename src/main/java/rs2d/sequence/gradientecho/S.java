//-- generated code, will be overwritten at each recompilation

package rs2d.sequence.gradientecho;

import rs2d.spinlab.tools.param.*;
import rs2d.spinlab.tools.table.*;
import rs2d.spinlab.tools.role.RoleEnum;
import rs2d.spinlab.sequenceGenerator.GeneratorSequenceParamEnum;

import java.util.List;
import static java.util.Arrays.asList;

public enum S implements GeneratorSequenceParamEnum {
    Enable_fs("Enable_fs"),
    Enable_sb("Enable_sb"),
    Ext_trig_source("Ext_trig_source"),
    Grad_enable_phase_2D("Grad_enable_phase_2D"),
    Grad_enable_phase_3D("Grad_enable_phase_3D"),
    Grad_enable_read("Grad_enable_read"),
    Grad_enable_slice("Grad_enable_slice"),
    Grad_enable_spoiler_phase("Grad_enable_spoiler_phase"),
    Grad_enable_spoiler_read("Grad_enable_spoiler_read"),
    Grad_enable_spoiler_slice("Grad_enable_spoiler_slice"),
    Gradient_Angle_Phi("Gradient Angle Phi"),
    Gradient_Angle_Psi("Gradient Angle Psi"),
    Gradient_Angle_Theta("Gradient Angle Theta"),
    Gradient_axe_phase("Gradient_axe_phase"),
    Gradient_axe_read("Gradient_axe_read"),
    Gradient_axe_slice("Gradient_axe_slice"),
    Intermediate_frequency("Intermediate_frequency"),
    LO_att("LO_att"),
    Loop_long("Loop_long"),
    Loop_short("Loop_short"),
    Nb_1d("Nb_1d"),
    Nb_2d("Nb_2d"),
    Nb_3d("Nb_3d"),
    Nb_4d("Nb_4d"),
    Nb_point("Nb_point"),
    Phase_reset("Phase_reset"),
    Pre_scan("Pre_scan"),
    Rx_gain("Rx_gain"),
    Spectral_width("Spectral_width"),
    Synchro_trigger("Synchro_trigger"),
    Tx_att("Tx_att"),
    Tx_frequency("Tx_frequency"),
    Tx_nucleus("Tx_nucleus"),
    Update_dimension("Update_dimension"),
    Freq_offset_tx_fs("Freq_offset_tx_fs"),
    Freq_offset_tx_fs_comp("Freq_offset_tx_fs_comp"),
    Freq_offset_tx_fs_prep("Freq_offset_tx_fs_prep"),
    Freq_offset_tx_sb("Freq_offset_tx_sb"),
    Freq_offset_tx_sb_comp("Freq_offset_tx_sb_comp"),
    Freq_offset_tx_sb_prep("Freq_offset_tx_sb_prep"),
    FreqOffset_RFSpoiling("FreqOffset_RFSpoiling"),
    FreqOffset_rx_3Dcomp("FreqOffset_rx_3Dcomp"),
    FreqOffset_rx_comp("FreqOffset_rx_comp"),
    FreqOffset_rx_prep("FreqOffset_rx_prep"),
    FreqOffset_tx_comp("FreqOffset_tx_comp"),
    FreqOffset_tx_prep("FreqOffset_tx_prep"),
    Frequency_offset_init("Frequency_offset_init"),
    Grad_amp_fs_phase("Grad_amp_fs_phase"),
    Grad_amp_fs_read("Grad_amp_fs_read"),
    Grad_amp_fs_slice("Grad_amp_fs_slice"),
    Grad_amp_phase_2D_prep("Grad_amp_phase_2D_prep"),
    Grad_amp_phase_3D_prep("Grad_amp_phase_3D_prep"),
    Grad_amp_phase_EPI("Grad_amp_phase_EPI"),
    Grad_amp_read_prep("Grad_amp_read_prep"),
    Grad_amp_read_read("Grad_amp_read_read"),
    Grad_amp_sb_phase("Grad_amp_sb_phase"),
    Grad_amp_sb_phase_spoiler("Grad_amp_sb_phase_spoiler"),
    Grad_amp_sb_read("Grad_amp_sb_read"),
    Grad_amp_sb_read_spoiler("Grad_amp_sb_read_spoiler"),
    Grad_amp_sb_slice("Grad_amp_sb_slice"),
    Grad_amp_sb_slice_spoiler("Grad_amp_sb_slice_spoiler"),
    Grad_amp_slice("Grad_amp_slice"),
    Grad_amp_spoiler_phase("Grad_amp_spoiler_phase"),
    Grad_amp_spoiler_read("Grad_amp_spoiler_read"),
    Grad_amp_spoiler_slice("Grad_amp_spoiler_slice"),
    Grad_shape_rise_down("Grad_shape_rise_down"),
    Grad_shape_rise_up("Grad_shape_rise_up"),
    Nb_echo("Nb_echo"),
    Nb_interleaved_slice("Nb_interleaved_slice"),
    Nb_sb("Nb_sb"),
    Rx_freq_offset("Rx_freq_offset"),
    Rx_phase("Rx_phase"),
    Time_before_fs_pulse("Time_before_fs_pulse"),
    Time_btw_dyn_frames("Time_btw_dyn_frames"),
    Time_delay_fs("Time_delay_fs"),
    Time_delay_sb("Time_delay_sb"),
    Time_flow("Time_flow"),
    Time_flush_delay("Time_flush_delay"),
    Time_grad_fs("Time_grad_fs"),
    Time_grad_phase_blipTop("Time_grad_phase_blipTop"),
    Time_grad_phase_top("Time_grad_phase_top"),
    Time_grad_ramp("Time_grad_ramp"),
    Time_grad_ramp_epi("Time_grad_ramp_epi"),
    Time_grad_ramp_fs("Time_grad_ramp_fs"),
    Time_grad_ramp_sb("Time_grad_ramp_sb"),
    Time_grad_sb("Time_grad_sb"),
    Time_grad_spoiler_top("Time_grad_spoiler_top"),
    Time_last_delay("Time_last_delay"),
    Time_min_instruction("Time_min_instruction"),
    Time_rf_spoiling("Time_rf_spoiling"),
    Time_rx("Time_rx"),
    Time_TE_delay1("Time_TE_delay1"),
    Time_TE_delay2("Time_TE_delay2"),
    Time_TR_delay("Time_TR_delay"),
    Time_trigger_delay("Time_trigger_delay"),
    Time_tx("Time_tx"),
    Time_tx_fs("Time_tx_fs"),
    Time_tx_sb("Time_tx_sb"),
    Tx_amp("Tx_amp"),
    Tx_amp_fs("Tx_amp_fs"),
    Tx_amp_sb("Tx_amp_sb"),
    Tx_freq_offset("Tx_freq_offset"),
    Tx_phase("Tx_phase"),
    Tx_phase_fs("Tx_phase_fs"),
    Tx_phase_sb("Tx_phase_sb"),
    Tx_shape("Tx_shape"),
    Tx_shape_fs("Tx_shape_fs"),
    Tx_shape_phase("Tx_shape_phase"),
    Tx_shape_phase_fs("Tx_shape_phase_fs"),
    Tx_shape_phase_sb("Tx_shape_phase_sb"),
    Tx_shape_sb("Tx_shape_sb");

    //--

    private final String name;

    private S(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}