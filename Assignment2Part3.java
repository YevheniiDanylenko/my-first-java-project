package com.shpp.p2p.cs.ydanylenko.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment2Part3 class allows drawing a pawprint that consists of 3
 * smaller oval pads and one bigger oval pad in the center of the composition.
 */
public class Assignment2Part3 extends WindowProgram {

    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint. */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;
    /* The position of the heel relative to the upper-left
     * corner of the pawprint. */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;
    // Each toe is an oval with this width and height.
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;
    // The heel is an oval with this width and height.
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;
    // The default width and height of the window.
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /** Draws a pawprint. The parameters should specify the upper-left corner
     * of the bounding box containing that pawprint.
     * @param x - the x coordinate of the upper-left corner of the bounding
     * box for the pawprint.
     * @param y - the y coordinate of the upper-left corner of the bounding
     * box for the pawprint. */
    private void drawPawprint(double x, double y) {

        drawSmallPad(x, y, FIRST_TOE_OFFSET_X, FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        drawSmallPad(x, y, SECOND_TOE_OFFSET_X, SECOND_TOE_OFFSET_Y, TOE_WIDTH,
                TOE_HEIGHT);
        drawSmallPad(x, y, THIRD_TOE_OFFSET_X, THIRD_TOE_OFFSET_Y, TOE_WIDTH,
                TOE_HEIGHT);
        drawBigPad(x, y, HEEL_OFFSET_X, HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);

    }

    /** Allows drawing 3 small oval pawprint pads.
     * @param x - the x coordinate of the upper-left corner of the bounding
     * box for the pawprint.
     * @param y - the y coordinate of the upper-left corner of the bounding
     * box for the pawprint.
     * @param x0 - the x coordinate of the upper-left corner of the bounding
     * box for the small pad.
     * @param y0 - the y coordinate of the upper-left corner of the bounding
     * box for the small pad.
     * @param width - the width of the bounding box of the small pad.
     * @param height - the height of the bounding box of the small pad. */
    private void drawSmallPad(double x, double y, double x0, double y0,
                              double width, double height) {
        GOval small_pad = new GOval(x0 + x, y0 + y, width, height);
        small_pad.setFilled(true);
        add(small_pad);
    }

    /** Allows drawing big oval pawprint pad.
     * @param x - the x coordinate of the upper-left corner of the bounding
     * box for the pawprint.
     * @param y - the y coordinate of the upper-left corner of the bounding
     * box for the pawprint.
     * @param x0 - the x coordinate of the upper-left corner of the bounding
     * box for the big pad.
     * @param y0 - the y coordinate of the upper-left corner of the bounding
     * box for the big pad.
     * @param width - the width of the bounding box of the big pad.
     * @param height - the height of the bounding box of the big pad. */
    private void drawBigPad(double x, double y, double x0, double y0,
                            double width, double height) {
        GOval big_pad = new GOval(x0+x,y0+y,width,height);
        big_pad.setFilled(true);
        add(big_pad);
    }
}

