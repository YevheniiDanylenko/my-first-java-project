package com.shpp.p2p.cs.ydanylenko.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Assignment1Part2 class runs Karel to pick a beeper and moves back to
 * the start position. This class works only for the map called
 * "CollectNewspaper".
 */
public class Assignment1Part1 extends KarelTheRobot {
    public void run() throws Exception {
        moveToBeeper();
        pickBeeper();
        moveBackToStart();
    }

    /*
     * Allows Karel moves along walls in the map "CollectNewspaper" in the
     * direction of the beeper.
     */
    private void moveToBeeper() throws Exception {
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
        move();
    }

    /*
     * Allows Karel moves back to the starting position where he stayed at
     * the beginning.
     */
    private void moveBackToStart() throws Exception {
        turnOpposite();
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
        move();
        turnOpposite();
    }

    /*
     * Allows Karel turns 270 degrees along its vertical axis counterclock-wise.
     */
    private void turnRight() throws Exception {
        for (int i = 1; i < 4; i++) {
            turnLeft();
        }
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