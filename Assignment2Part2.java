package com.shpp.p2p.cs.ydanylenko.assignment2;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment2Part2 class allows to create an optical illusion consisting of 4
 * in the screen corner black circles that are overwhelmed by the white
 * rectangle in that way that this white rectangle is looked convex. */
public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;
    /* The number of circles of the same diameter that completely fall
    within the applications. This value can be chosen randomly.
    CIRCLES_IN_ROW = N * 2 * r, where r - radius of circle, thus 2 * r -
    diameter of circle; N - coefficient that chose to place circles visually fit for this
    optical illusion and show how many circles can be placed along canvas
    sides.*/
    public static final double CIRCLES_IN_ROW = 2.7;

    public void run() {
        double diameter = countDiameter();
        createIllusion(diameter);
    }

    /* Allows to create an optic illusion consisted of 4 in the corner of the
    screen black circles and white rectangle between these circles. */
    private void createIllusion(double diameter) {
        drawFigures(diameter);
    }

    /** Allows adding figures that are centred relative to each other on
     * the canvas.
     * @param diameter - a diameter of circle. */
    private void drawFigures(double diameter) {
        //Radius of the circle.
        double radius = diameter / 2;
        //Draw circles clockwise.
        drawCircle(0, 0, diameter, diameter); //1st circle.
        drawCircle(APPLICATION_WIDTH - diameter, 0, diameter, diameter);//2nd
        // circle.
        drawCircle(0, getHeight() - diameter, diameter,
                diameter);//3rd circle.
        drawCircle(APPLICATION_WIDTH - diameter,
                getHeight() - diameter,
                diameter, diameter);//4th circle.
        drawRectangle(radius, radius, getWidth() - diameter,
                getHeight() - diameter);
    }

    /** Allows to select the circles' diameter value depending on the
     * application width and height, and thereby center the figures position.
     * @return diameter of circle. */
    private double countDiameter() {
        //Diameter of a circle that depends on the application height and width.
        double diameter;
        /* The number of circles of the same diameter that completely fall
        within the applications. This value can be chosen randomly. */
        if (APPLICATION_HEIGHT > APPLICATION_WIDTH) {
            diameter = APPLICATION_WIDTH / CIRCLES_IN_ROW;
        } else if (APPLICATION_WIDTH > APPLICATION_HEIGHT) {
            diameter = APPLICATION_HEIGHT / CIRCLES_IN_ROW;
        } else {
            diameter = APPLICATION_WIDTH / CIRCLES_IN_ROW;
        }
        //Radius of the circle.
        return diameter;
    }

    /** Allows drawing circle by the settled coordinates.
     * @param x      - upper right x-coordinate of a rectangle that bounds a circle.
     * @param y      - upper right y-coordinate of a rectangle that bounds a circle.
     * @param height - the height of rectangle that bounds a circle, same as
     *               diameter of circle.
     * @param width  - the width of rectangle that bounds a circle, same as
     *               diameter of circle. */
    private void drawCircle(double x, double y, double width, double height) {
        GOval circle = new GOval(x, y, width, height);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
    }

    /** Allows drawing rectangle by the settled coordinates.
     * @param x      - upper right x-coordinate of a rectangle.
     * @param y      - upper right y-coordinate of a rectangle.
     * @param height - the height of rectangle.
     * @param width  - the width of rectangle. */
    private void drawRectangle(double x, double y, double width,
                               double height) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}
