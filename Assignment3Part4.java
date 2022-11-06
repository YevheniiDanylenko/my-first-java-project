package com.shpp.p2p.cs.ydanylenko.assignment3;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment3Part4 class allows to draw a pyramid consisting of white brick
 * rows. The pyramid lies on the canvas's down border and is centered in the
 * middle of the canvas.*/
public class Assignment3Part4 extends WindowProgram {
    //Constants of the brick's side sizes.
    private static final double BRICK_HEIGHT = 50;
    private static final double BRICK_WIDTH = 100;
    //The number of bricks on the pyramid's down.
    private static final int BRICKS_IN_BASE = 8;

    public void run() {
        drawPyramid();
    }

    /* Draws a pyramid from down to up in a y counter cycle and from right side side
    of canvas to the left in the storey_num cycle. */
    private void drawPyramid() {
        for (int y = 1; y <= BRICKS_IN_BASE; y++)
            for (int storey_num = BRICKS_IN_BASE - (y - 1); storey_num >= 1; storey_num--)
                drawRow(storey_num, y);
    }

    /** Draws the similar rectangles row from the right to the left side of the canvas.
     * @param storey - defines a number of bricks in a row and current upper
     * left x coordinate of every brick in the row;
     * @param y - defines the same for every in the row brick upper left y;
     * @return - the defined by coordinates rectangle.
     */
    private GRect drawRow(int storey, int y) {
        double pyramid_base_width = (BRICKS_IN_BASE - (y - 1)) * BRICK_WIDTH;
        GRect brick =
                new GRect(getWidth() / 2 + pyramid_base_width / 2 - storey * BRICK_WIDTH, getHeight() - BRICK_HEIGHT * y
                        , BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(false);
        brick.setColor(Color.BLACK);
        add(brick);
        return brick;
    }
}
