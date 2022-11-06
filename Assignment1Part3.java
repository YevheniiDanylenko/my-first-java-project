package com.shpp.p2p.cs.ydanylenko.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Public class Assignment1Part3 extends KarelTheRobot nd allows Karel finds
 * a middle of the horizontal strip he moves and marks it with a beeper.
 */
public class Assignment1Part3 extends KarelTheRobot {
    public void run() throws Exception {
        findMiddle();
        pickBeeper();
        backToStart();
    }

    /*Allows Karel to find a middle of the stripe by step-by-step shifting of
    the extreme beepers towards each other
     */
    private void findMiddle() throws Exception {
        markLine();
        turnBack();
        if (frontIsClear()) {//this statements check if a maze is longer then
                             // 1 cell logs
            move();
        } else {//this statement is needed to mark with beeper 1x1 maze.
            putBeeper();
        }
        moveBeepers();
    }

    /*
     * Allows Karel backs to the starting position when the stripe middle is
     * already found.
     */
    private void backToStart() throws Exception {
        if (rightIsBlocked()) {//this statement for odd-length stripe.
            turnBack();
            while (frontIsClear()) {
                move();
            }
            turnBack();
        } else { ////this statement for even-length stripe.
            while (frontIsClear()) {
                move();
            }
            turnBack();
        }
    }

    /*
    Allows Karel marks a stripe boundaries with 2 beepers - one in the 1-st
    cell he stays ath the beginning and 2-nd at the opposite end.
     */
    private void markLine() throws Exception {
        putBeeper(); //put a 1st signal beeper at the start position of Karel;
        while (frontIsClear()) {
            move();
        }
        if (noBeepersPresent()) {
            putBeeper(); //put a 2nd signal beeper at the line's finish
            // position;
        }
    }

    /*
    Allows Karel step-by-step move the extreme beepers from stripe boundaries
     towards each other.
     */
    private void moveBeepers() throws Exception {
        while (noBeepersPresent()) {
            move();
            if (beepersPresent()) {
                turnBack();
                pickBeeper();
                move();
                putBeeper();
                move();
            }
        }

    }

    /*
    Allows Karel turns 180 degrees around the present view.
     */
    private void turnBack() throws Exception {
        turnLeft();
        turnLeft();
    }
}
