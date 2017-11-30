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
        int Delay2 = 15;
        int LoopEndEcho = 16;

        int Delay3 = 20;
        int LoopMultiPlanarEnd = 21;

        int Loop4D = 23;

        int End = 24;
}