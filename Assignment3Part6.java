package com.shpp.p2p.cs.ydanylenko.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment 3Part6 class allows creating animation that consists of 2
 * different color circles that move towards each other in parallel. First
 * circle moves from the right to left canvas sides while the second moves in
 * the opposite way.
 */
public class Assignment3Part6 extends WindowProgram {
    //The frame per second constant that shows frame rate.
    private static final int FPS = 24;
    //The time of animation till the objects move.
    private static final long ANIMATION_LENGTH = 5000;
    //The diameter of the circle.
    private static final int DIAMETER = 100;
    //The upper left y-coordinate of the upper (also first) circle bounding box.
    private static final int Y_CIRCLE1 = DIAMETER + 30;
    /*The upper left y-coordinate of the upper (also second) circle bounding
    box. */
    private static final int Y_CIRCLE2 = Y_CIRCLE1 + 2 * DIAMETER;
    //The color of the upper (also first) circle.
    private static final Color COLOR1 = new Color(57, 113, 169);
    //The color of the upper (also second) circle.
    private static final Color COLOR2 = new Color(239, 148, 6);

    public void run() {
        createAnimation();
    }

    /* Creates an animation of the given figure and checks its duration using
     * the system time counter. */
    private void createAnimation() {
        GOval figure1 = createCircle(-DIAMETER, Y_CIRCLE1, COLOR1);
        GOval figure2 = createCircle(getWidth() + DIAMETER, Y_CIRCLE2, COLOR2);
        //The time counter for animation duration.
        double time = 0;
        //The velocity of the object moving (animation) on the canvas.
        double velocity = 1000L * (getWidth() + DIAMETER) / (ANIMATION_LENGTH * FPS);
        //The system time shot at the beginning of the animation.
        long time_at_the_beginning = System.currentTimeMillis();
        while (time <= ANIMATION_LENGTH) {
            /* The hand-picked special coefficient makes animation visually beautiful. */
            double velocity1_coef = 5;
            /* The hand-picked special coefficient makes animation visually
            beautiful and is less than the "velocity1_coef" value because of
            the processor executes this animation second and therefore slower.*/
            double velocity2_coef = 4.5;
            figure1.move(velocity / velocity1_coef, 0);
            figure2.move(-velocity / velocity2_coef, 0);
            pause(velocity);
            time = System.currentTimeMillis() - time_at_the_beginning;
        }
        println("The animation duration is: " + time + " milliseconds");
    }

    /** Create circle as a GOval class object with the given parameters.
     * @param x     - the upper-right x coordinate of the circle bounding box;
     * @param y     - the upper-right y coordinate of the circle bounding box;
     * @param color - the circle's color.
     * @return - the circle as a graphic object that is already added on the
     * canvas. */
    private GOval createCircle(double x, double y, Color color) {
        GOval circ = new GOval(x, y, DIAMETER, DIAMETER);
        circ.setFilled(true);
        circ.setFillColor(color);
        add(circ);
        return circ;
    }
}
