package rs2d.sequence.gradientecho;

import rs2d.spinlab.sequence.Sequence;
import rs2d.spinlab.sequence.element.TimeElement;

public enum Events {
         Start (0, "Time1"),
         TriggerDelay (1, S.Time_trigger_delay.name()),

         LoopMultiPlanarStart (3, S.Time_min_instruction.name()),

         LoopSatBandStart (3, S.Time_min_instruction.name()),
         SatBandpulse (5, S.Time_tx_sb.name()),
         LoopSatBandEnd (10, S.Time_delay_sb.name()),

         FatSatPulse (12, S.Time_tx_fs.name()),


         P90 (19, S.Time_tx.name()),
         Delay1 (27, S.Time_TE_delay1.name()),

         LoopStartEcho (28, S.Time_min_instruction.name()),
         Acq (30, S.Time_rx.name()),
         Delay2 (35, S.Time_TE_delay2.name()),
         LoopEndEcho (36, S.Time_min_instruction.name()),

         Delay3 (40, S.Time_TR_delay.name()),
         LoopMultiPlanarEnd (41, S.Time_min_instruction.name()),

         Loop4D (43, S.Time_flush_delay.name()),
         End (44, S.Time_btw_dyn_frames.name());

        public final int ID;
        public final String shortcutName;

        Events(int id, String sname) {
                this.shortcutName = sname;
                ID = id;
        }

        static boolean  checkEventShortcut(Sequence sequence)throws Exception{
                Events[] interfaceFields = Events.values();
                for (Events f : interfaceFields) {

                        if ( !f.shortcutName.equals(((TimeElement) sequence.getTimeChannel().get(f.ID)).getTime().getName()) ){

                                String message = "PSD Event Error\n" +
                                        " Shortcut of time ID#" + f.ID + " is not " + f.shortcutName+ "   but is "+((TimeElement) sequence.getTimeChannel().get(f.ID)).getTime().getName()
                                        + " \n Check PSD Events and Events-Class";
                                System.out.println(message);
                                throw new Exception( message);
                        }
                }
                return false;
        }


}
