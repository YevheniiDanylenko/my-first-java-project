package com.shpp.p2p.cs.ydanylenko.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment2Part6 class allows drawing a caterpillar that consists from
 * the same sized circles. The circles are arranged so that their centers form
 * a jagged broken line. Using constants, the user can adjust the size of the
 * segments. */
public class Assignment2Part6 extends WindowProgram {
    /* The diameter of the circle segment that constitute the body of the
    caterpillar. */
    private static final double SEGMENT_SIZE = 200;
    //The number of segments that constitutes caterpillar.
    private static final int CATERPILLAR_LENGTH = 5;
    //The circle color excepts the circle border.
    private static final Color BODY_COLOR = Color.GREEN;
    //The circle border color.
    private static final Color BODY_EDGING = Color.RED;
    //The upper-right x coordinate of first circle bounding box.
    private static final double STARTING_X = 0;
    /*The upper-right y coordinate of first circle bounding box. It's
    calculated to make upper segments touch the canvas border */
    private static final double STARTING_Y = SEGMENT_SIZE / 2;
    /* The x offset of x upper-right coordinate of every circle's
    bounding box. Thus, if first circle has coordinates (x, y, diameter,
    diameter), the second will have (x + DELTA_X, y, diameter, diameter) and
    so on. */
    private static final double DELTA_X = SEGMENT_SIZE / 1.75;
    /* The y offset of y upper-right coordinate of every circle's
    bounding box. Thus, if first circle has coordinates (x, y, diameter,
    diameter), the second will have (x, y + DELTA_Y, diameter, diameter) and
    so on. */
    private static final double DELTA_Y = SEGMENT_SIZE / 2;

    public void run() {
        drawCaterpillar();
    }

    /* Draws a caterpillar figure consisting of circles. */
    private void drawCaterpillar() {
        for (int iterator = 1; iterator <= CATERPILLAR_LENGTH; ) {
            if (isIteratorOdd(iterator)) {
                drawDownRow(iterator);
            } else if (isIteratorEven(iterator)) {
                drawUpperRow(iterator);
            }
            iterator++;
        }
    }

    /** Check if iterator number is odd.
     * @param iterator - input iterator from for-cycle.
     * @return "true" if number is odd. */
    private boolean isIteratorOdd(int iterator) {
        boolean iterator_is_odd = false;
        if (iterator % 2 == 1) {
            iterator_is_odd = true;
        }
        return iterator_is_odd;
    }

    /** Draws 1,3,5,..., odd-number circles in the down row.
     * @param iterator - input iterator from for-cycle. */
    private void drawDownRow(int iterator) {
        GOval body = new GOval(STARTING_X + (iterator - 1) * DELTA_X,
                STARTING_Y,
                SEGMENT_SIZE, SEGMENT_SIZE);
        body.setFilled(true);
        body.setColor(BODY_EDGING);
        body.setFillColor(BODY_COLOR);
        add(body);
    }

    /** Check if iterator number is even.
     * @param iterator - input iterator from for-cycle.
     * @return "true" if number is even. */
    private boolean isIteratorEven(int iterator) {
        boolean iterator_is_even = iterator % 2 == 0;
        return iterator_is_even;
    }

    /** raws 2,4,6,..., even-number circles in the upper row.
     * @param iterator - input iterator from for-cycle. */
    private void drawUpperRow(int iterator) {
        GOval body =
                new GOval(STARTING_X + DELTA_X + (iterator - 2) * DELTA_X,
                        STARTING_Y - DELTA_Y,
                        SEGMENT_SIZE,
                        SEGMENT_SIZE);
        body.setFilled(true);
        body.setColor(BODY_EDGING);
        body.setFillColor(BODY_COLOR);
        add(body);
    }

}
