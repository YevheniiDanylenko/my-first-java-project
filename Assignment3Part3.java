package com.shpp.p2p.cs.ydanylenko.assignment3;
import com.shpp.cs.a.console.TextProgram;

/** Assignment3Part3 class calculates the result of the exponentiation of
 * numbers: base - a number rising to power. An exponent - is a value of the
 * numeric power. */
public class Assignment3Part3 extends TextProgram {
    public void run() {
        raiseToPower(readDouble("Enter a base (any real number)"), readInt(
                "Enter an " +
                "exponent (only integer number!)"));
    }

    /** Output on the console the result of the raising to the numeric power.
     * @param base     - a number rising to power;
     * @param exponent - a value of the numeric power.*/
    private void raiseToPower(double base, int exponent) {
        /* An intermediate variable that holds the value when raised to a power
        during the loop. */
        double res = 1;
        if (isExponentPositiveNum(exponent)) {
            for (int i = 1; i <= exponent; i++) {
                res = res * base;
            }
            println("The result is " + res);
        } else {
            base = 1 / base;
            exponent = -exponent;
            for (int i = 1; i <= exponent; i++) {
                res = res * base;
            }
            println("The result is " + res);
        }
    }

    /** Checking if an exponent value is a positive integer number.
     * @param exponent - output number.
     * @return - "true" if the output number is positive. */
    private boolean isExponentPositiveNum(int exponent) {
        boolean exponent_is_positive = exponent > 0;
        return exponent_is_positive;
    }
}
