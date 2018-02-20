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

        int P90 = 5;
        int Delay1 = 10;

        int LoopStartEcho = 11;
        int Acq = 13;
        int Delay2 = 18;
        int LoopEndEcho = 19;

        int Delay3 = 23;
        int LoopMultiPlanarEnd = 24;

        int Loop4D = 26;

        int End = 27;
}