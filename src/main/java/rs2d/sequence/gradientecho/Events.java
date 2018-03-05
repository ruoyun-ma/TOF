/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs2d.sequence.gradientecho;

/**
 *
 * @author jrivoire
 */
public interface Events {
        int Start = 0;
        int TriggerDelay = 1;

        int LoopMultiPlanarStart = 3;

        int LoopSatBandStart = 4;
        int SatBandpulse = 6;
        int LoopSatBandEnd = 12;

        int FatSatPulse = 14;


        int P90 = 21;
        int Delay1 = 26;

        int LoopStartEcho = 27;
        int Acq = 29;
        int Delay2 = 34;
        int LoopEndEcho = 35;

        int Delay3 = 29;
        int LoopMultiPlanarEnd = 30;

        int Loop4D = 42;

        int End = 43;
}
