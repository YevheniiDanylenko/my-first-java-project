package com.shpp.p2p.cs.ydanylenko.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1Part2 class allows Karel fills with beepers rows numbered with
 * numbers 1,5,9,1 + 4*n (n - positive integer number).
 **/
public class Assignment1Part2 extends KarelTheRobot {
    public void run() throws Exception {
        while (frontIsClear()) {
            fillColumnVertically();
            goToNextColumn();
        }
        fillColumnVertically();
    }

    /*
     * Allows Karel filled with missed beepers every single sell of a
     * vertically long row.
     */
    private void fillColumnVertically() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        if (noBeepersPresent()) {
            putBeeper();
        }
        turnOpposite();
        while (frontIsClear()) {
            move();
        }
        turnLeft();

    }

    /*
     * Allows Karel moves through 4 columns that no needed to be filled.
     */
    private void goToNextColumn() throws Exception {
        move();
        move();
        move();
        move();
    }

    /*
     * Allows Karel turns 180 degrees along its vertical axis
     * counterclock-wise.
     */
    private void turnOpposite() throws Exception {
        turnLeft();
        turnLeft();
    }
}
