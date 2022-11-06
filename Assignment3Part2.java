package com.shpp.p2p.cs.ydanylenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/** Allows to proceed a syracuse sequence that relates to Collatz problem.
 * More information see on: https://en.wikipedia.org/wiki/Collatz_conjecture */
public class Assignment3Part2 extends TextProgram {
    public void run() {
        countSyracuseSequence(setNumber());
    }

    /** Input the number from a member.
     * @return - inputted number. */
    private int setNumber() {
        double num = readDouble("Enter the positive integer number:");
        toString();
        int num_cast = (int) num;
        if ((num - num_cast) > 0) {
            println("You entered a positive non-integer number thus it will " +
                    "it will be rounded up to an integer according to the rules of arithmetic");
            if (num - num_cast <= 0.5) {
                num = num_cast;
            } else {
                num = num_cast + 1;
            }
        }

        if (num <= 0) {
            while (num <= 0) {
                num = readInt("Enter next a positive integer number");
            }
        }
        return (int) num;
    }

    /** Reduce the sequence by the Collatz algorithm to the number "1" at the
     *  end of algorithm.
     * @param num - first member of the syracuse sequence. */
    private void countSyracuseSequence(int num) {
        if (num == 1) {
            println("You entered 1, no further proceed is needed");
        }
        while (num != 1) {
            if (isNumberEven(num)) {
                println(num + " is even, thus divide this number on 2, get " +
                        "the " + (num = num / 2));
            } else {
                println(num + " is odd, thus multiply tree times and add 1, " +
                        "get " + (num = 3 * num + 1));
            }
        }
    }

    /** Check if an input number is even.
     * @param num - input number.
     * @return - "true" if numbr is even. */
    private boolean isNumberEven(int num) {
        boolean res = num % 2 == 0;
        return res;
    }
}
