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

        int P90 = 11;
        int Delay1 = 16;

        int LoopStartEcho = 17;
        int Acq = 19;
        int Delay2 = 24;
        int LoopEndEcho = 25;

        int Delay3 = 29;
        int LoopMultiPlanarEnd = 30;

        int Loop4D = 32;

        int End = 33;
}
