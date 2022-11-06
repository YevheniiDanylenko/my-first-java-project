package com.shpp.p2p.cs.ydanylenko.assignment2;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Assignment2Part4 class allows drawing centered along the canvas flag of
 * Italy and put a label "Flag of Italy" in the canvas right-down corner. */
public class Assignment2Part4 extends WindowProgram {

    //The constants that set the flag stripe RGB system parameters color.
    private static final Color LEFT_STRIPE_COLOR = new Color(41, 197, 110, 255);
    private static final Color MIDDLE_STRIPE_COLOR = new Color(255, 255, 255);
    private static final Color RIGHT_STRIPE_COLOR = new Color(245, 18, 18);
    /* A constant that sets the label's signature. */
    private static final String FLAG_LABEL = "The flag of Italy";
    /* A constant that sets the font style, and it's size in the string format
    "Family_font_style-font_size" */
    private static final String FONT_STYLE = "Times-36";
    /** The constant that sets the height of flag stripes. According
     * to the heraldic rules and standards the height to width ratio is 2:3
     * for the whole flag. */
    private static final double STRIPE_HEIGHT = 300;
    /* A variable that sets stripe width and is counted according to the
    heraldic
    rules and standards of the italian flag. Let height of the whole flag is H
    and whole flag width is W, than H : W = 2:3 => W = (3 / 2) * H, but the
    width of the whole flag consist of three same sized stripes. And if a stripe width
    is w, then W = 3 * w. And then 3 * w= 3 * (H / 2) => w = H / 2. */
    double stripe_width = STRIPE_HEIGHT / 2.0;

    public void run() {
        drawFlagOfItaly();
        drawSignature();
    }

    /** Allows drawing of Italy flag that consists of three vertical stripes of
     * green, white, and red colors. */
    private void drawFlagOfItaly() {
        //The canvas center x coordinate.
        double canvas_center_x = getWidth() / 2.0;
        //The canvas center y coordinate.
        double canvas_center_y = getHeight() / 2.0;
        //The number of stripes.
        double stripes_number = 3;
        //The width value of the half of the flag.
        double flag_half_width = stripes_number * stripe_width / 2;
        //The width value of the half of the flag stripe.
        double stripe_half_width = stripe_width / 2;
        //The height value of the half of the flag stripe.
        double stripe_half_height = STRIPE_HEIGHT / 2;
        //The upper x coordinate of the left stripe.
        double left_stripe_x0 =
                canvas_center_x - flag_half_width;
        //The upper x coordinate of the middle stripe.
        double middle_stripe_x0 =
                canvas_center_x - stripe_half_width;
        //The upper x coordinate of the right stripe.
        double right_stripe_x0 =
                canvas_center_x + stripe_half_width;
        //The upper y coordinate of the every stripe.
        double stripe_y0 = canvas_center_y - stripe_half_height;

        drawStripe(LEFT_STRIPE_COLOR, left_stripe_x0,
                stripe_y0);
        drawStripe(MIDDLE_STRIPE_COLOR, middle_stripe_x0,
                stripe_y0);
        drawStripe(RIGHT_STRIPE_COLOR, right_stripe_x0,
                stripe_y0);


    }

    /** Allows drawing a stripe in a rectangle form with the set parameters.
     * @param stripe_color - the color of the rectangle.
     * @param x_stripe     - the upper x coordinate of the rectangle.
     * @param y_stripe     - the upper y - coordinate of the rectangle. */
    private void drawStripe(Color stripe_color, double x_stripe,
                            double y_stripe) {
        GRect stripe = new GRect(x_stripe, y_stripe, stripe_width, STRIPE_HEIGHT);
        stripe.setFilled(true);
        stripe.setColor(stripe_color);
        stripe.setFillColor(stripe_color);
        add(stripe);
    }
    /* Allows drawing a label with string signature. */
    private void drawSignature() {
        GLabel flag_name = new GLabel(FLAG_LABEL, 0
                , 0);
        flag_name.setFont(FONT_STYLE);
        /* Sets the most right-down corner position for label bounded box upper
        x coordinate. */
        double x_label = getWidth() - flag_name.getWidth();
        /* Sets the most right-down corner position for label bounded box upper
        y coordinate. */
        double y_label = getHeight() - flag_name.getDescent();
        flag_name.setLocation(x_label,y_label);
        add(flag_name);
    }
}
