package com.shpp.p2p.cs.ydanylenko.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Allows Karel to put beepers in chess-alike order all along the maze.
 */
public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        fillRowVertically();
        turn180Degrees();
        fillHorizontalRows();

    }

    /*
    Allows Karel turn left and moves up to the row end. While moving Karel
    puts beepers in each cell through one.
     */
    private void fillRowVertically() throws Exception {
        turnLeft();
        if (frontIsBlocked() && rightIsBlocked()) { //check for 1X1 maze.
            putBeeper();
        }
        while (frontIsClear()) {
            putBeeper();
            if (frontIsClear()) { //move 1-st step.
                move();
                if (frontIsClear()) { //move 2-nd step.
                    move();
                    if (frontIsBlocked()) { //put last beeper in the row's end
                        //for odd cell numbers row.
                        putBeeper();
                    }
                }
            }
        }
    }

    /*
    Allows Karel to turn 180 degrees from its view direction.
     */
    private void turn180Degrees() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
    Allows Karel put beepers in chess order in the horizontal rows. Karel
    stays at the end of vertical row; Karel watches west and moves along its
     view direction.
     */
    private void fillHorizontalRows() throws Exception {
        while (frontIsClear()) {
            moveAndPutBeepersAlongRow();
            goNextRow();
        }
        moveAndPutBeepersAlongRow();
    }

    /*
    Allows Karel put beepers in the horizontal row in a chess order.
     */
    private void moveAndPutBeepersAlongRow() throws Exception {
        if (noBeepersPresent()) {
            while (frontIsClear()) {
                if (frontIsClear()) {
                    move();
                }
                putBeeper();
                if (frontIsClear()) {
                    move();
                }
            }
        } else {
            while (frontIsClear()) {
                if (frontIsClear()) {
                    move();
                }
                if (frontIsClear()) {
                    move();
                    putBeeper();
                }
            }
        }


    }

    /*
    Allows Karel back to the beginning of the current horizontal row and then
    moves on the down row close to the most beginning of the chess board.
     */
    private void goNextRow() throws Exception {
        turnLeft();
        turnLeft();
        while (frontIsClear()) {
            move();
        }
        turnLeft();
        if (frontIsClear()) {
            move();
            turnLeft();
        }
    }
}