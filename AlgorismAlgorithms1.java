package com.shpp.p2p.cs.ydanylenko.assignment5.draft;
/*
 * File: AlgorismAlgorithms1.java
 * ========================================================
 * A console program that takes from a console 2 strings with written in them
 * numbers and then returns its arithmetic sum as a string.
 * If a digit set representing a number fits the following rules:
 * 1. String consists only of the digits (0,...,9) and no other symbols are
 * used.
 * 2. The string's first element isn't zero ('0'),then the program converses
 * the given digits set to the array of integers. The adding executes by the
 * column method.
 */

import com.shpp.cs.a.console.TextProgram;

public class AlgorismAlgorithms1 extends TextProgram {
    /* Warning message occurring after user inputs non-digital symbols. */
    public final static String WARNING = "Invalid format. Non digital " +
            "symbols or a '0' as a first digit used in the " +
            "notation";
    /* Strings representing the adding numbers. */
    public String n1;
    public String n2;

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            n1 = readLine("Enter first number:  ");
            n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
        }
    }

    /**
     * Given two string representations of non-negative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        this.n1 = n1;
        this.n2 = n2;
        /* Difference calculated by missed positions of a smaller number in
        comparing to the greater one. */
        int arrDifference;
        /* String length representing a given number. */
        int arrLength;
        /* Arrays of integer numbers that represent the strings digits
         respectively. */
        int[] n1Arr;
        int[] n2Arr;
        /* The array of integers that represent positional digits of adding n1
        and n2 numbers. */
        int[] numsSum;
        /* The string representing the sum of n1 and n2 numbers. */
        String numSumToString = "";
        /* Checks if given n1 and n2 strings consist only of digits. */
        if (!isStringOnlyOfDigits(n1) || !isStringOnlyOfDigits(n2)) {
            return WARNING;
        }
        /* Checks if the first element of number representing string isn't "0". */
        if ((n1.length() > 1 && n1.charAt(0) == '0') || (n2.length() > 1 && n2.charAt(0) == '0')) {
            return WARNING;
        }
        /* Adds this zeros at the beginning of the string for cases when the sum
        of this 2 numbers */
        n1 = "0" + n1;
        n2 = "0" + n2;
        if (is_n1_More_n2()) {
            arrDifference = n1.length() - n2.length();
            arrLength = n1.length();
            /* n2 string extended with 0 on a missed positions; only for
            condition n1 string length is bigger then n2 string length. */
            String n2_Ext = addZerosToString(arrDifference);
            n2 = n2_Ext + n2;
        } else if (is_n2_More_n1()) {
            arrDifference = n2.length() - n1.length();
            arrLength = n2.length();
            /* n1 string extended with 0 on a missed positions; only for
            condition n2 string length is bigger then n1 string length. */
            String n1_Ext = addZerosToString(arrDifference);
            n1 = n1_Ext + n1;
        } else {
            arrLength = n1.length();
        }
        /* The integers that represent positional digits of adding n1
        and n2 numbers arrays lengths. arrLength + 1 used because of the upper
        n1 = "0" + n1, n2 = "0" + n2 conditions */
        n1Arr = new int[arrLength + 1];
        n2Arr = new int[arrLength + 1];
        numsSum = new int[arrLength + 1];
        numsSum[0] = 0;
        n1Arr[0] = 0;
        n2Arr[0] = 0;
        /* The cycle sets element-by-element digits of char arrays. */
        for (int i = 1; i < arrLength; i++) {
            n1Arr[i] = n1.charAt(i) - '0';
            n2Arr[i] = n2.charAt(i) - '0';
        }
        /* The 1 is added to the higher position column if the digits sum of the
         previous position column is bigger than 10. */
        int positionalOne = 0;
        for (int j = arrLength - 1; j >= 0; ) {
            if (n1Arr[j] + n2Arr[j] + positionalOne > 9) {
                int intermediateSum = (n1Arr[j] + n2Arr[j] + positionalOne);
                String intermediateSumToString = "" + intermediateSum;
                int positionalDigit = intermediateSumToString.charAt(1) - '0';
                numsSum[j] = positionalDigit;
                j--;
                positionalOne = 1;
            } else {
                numsSum[j] = n1Arr[j] + n2Arr[j] + positionalOne;
                positionalOne = 0;
                j--;
            }
            numSumToString = numsSum[j + 1] + numSumToString;
        }
        /* Deletes 0 as a first element if it presents in a string notation. */
        if (numSumToString.charAt(0) == '0') {
            numSumToString = numSumToString.substring(1);
        }
        return numSumToString;
    }

    /**
     * Places a zeros for a missed positions of the smaller number comparing to
     * the bigger. E.g. if a 1st number is 12345 and the 2nd is 789 thus
     * smaller number lacks positions on 10000 and 1000 respectively and this
     * positions then are placed with zeros - 12345 and 00789.
     *
     * @param arrDifference - difference calculated by missed positions of a
     *                      smaller number in
     *         comparing to the greater one
     * @return - a string with added zeros on the beginning.
     */
    private String addZerosToString(int arrDifference) {
        String additionOfZeros = "";
        for (int j = 0; j < arrDifference; j++) {
            additionOfZeros = additionOfZeros + "0";
        }
        return additionOfZeros;
    }

    /**
     * Checks if the input string consists only of digits as its elements.
     *
     * @param number - a string representing a number.
     * @return - "true" if all the sting elements are digits only.
     */
    private boolean isStringOnlyOfDigits(String number) {
        String modifiedNumber = number.toLowerCase();
        /* A number of digits {0,1,2,...,9} = 10. */
        int digitsSetCardinality = 10;
        /* A char array of encoded in UTF-16 notation digit symbols. Thus,
        digit "0" is encoded by number 48; digit "1" is encoded by number 49,
        etc. */
        char[] utf16DigitsCodesArray = new char[digitsSetCardinality];
        /* A UTF-16 notation decimal coding number starting the set of digits.
        This number encodes the digit "0". */
        char decimalUTF16CodeOfDigit0 = 48;
        int i = 0;
        /* A loop that sets the char array elements as the encoded in UTF-16
        notation digits sequence starting from digit "0" and ended with "9". */
        while (i < digitsSetCardinality) {
            utf16DigitsCodesArray[i] = decimalUTF16CodeOfDigit0;
            i++;
            decimalUTF16CodeOfDigit0++;
        }
        /* A loop that element-by-element checks if a current string symbol
         * is a digit. */
        for (int k = 0; k < modifiedNumber.length(); ) {
            for (int n = 0; n < digitsSetCardinality; ) {
                if (modifiedNumber.charAt(k) == utf16DigitsCodesArray[n]) {
                    k++;
                    n = 0;
                    if (k == modifiedNumber.length()) {
                        return true;
                    }
                } else {
                    n++;
                    if (n == digitsSetCardinality) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the length of the second number representing string is greater than
     * the first string length.
     *
     * @return "true" if the 2-nd number is greater than the 1st.
     */
    private boolean is_n1_More_n2() {
        return n1.length() > n2.length();
    }

    /**
     * Checks if the length of the first number representing string is greater than
     * the second string length.
     *
     * @return "true" if the 1-st number is greater than the 2nd.
     */
    private boolean is_n2_More_n1() {
        return n2.length() > n1.length();
    }

}