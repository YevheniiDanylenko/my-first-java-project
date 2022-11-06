package com.shpp.p2p.cs.ydanylenko.assignment2;

import com.shpp.cs.a.console.TextProgram;

/** Assignment2Part1 class allows to resolve a square equation a * (x^2) + b *
 * (x) + c = 0 by input 3 real coefficients (named a, b, c respectively) and
 * output real numbers roots on console. */
public class Assignment2Part1 extends TextProgram {
    public void run() {
        findSquareEquationRoot();
    }

    /**
     * Allows finding real roots of square equation with real coefficients.
     */
    private void findSquareEquationRoot() {
        double a = readDouble("Enter coefficient A:");//coefficient near
        // x^2! = 0;
        while (a == 0) {
            a = readDouble("Enter A that is not equal to 0");
        }
        //coefficient near x;
        double b = readDouble("Enter coefficient B:");
        //free term
        double c = readDouble("Enter coefficient C:");
        double discriminant = b * b - 4 * a * c;
        if (discriminant == 0) {
            println("The equation has only one root x =" + ((-b / 2 * a)));
        } else if (discriminant < 0) {
            println("The equation has no real number roots");
        } else if (b == 0 && c == 0 && a != 0) {
            println("The equation has only one root:" + 0);
        } else {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            println("The equation has 2 roots respectively x1 = " + x1 + " " +
                    "and" +
                    " " +
                    " " +
                    "x2 = " + x2);
        }
    }
}

