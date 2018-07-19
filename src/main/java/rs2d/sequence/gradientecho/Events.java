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
        int Delay1 = 29;

        int LoopStartEcho = 30;
        int Acq = 32;
        int Delay2 = 37;
        int LoopEndEcho = 38;

        int Delay3 = 42;
        int LoopMultiPlanarEnd = 43;

        int Loop4D = 45;

        int End = 46;
}
