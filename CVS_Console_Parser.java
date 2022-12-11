package com.shpp.p2p.cs.ydanylenko.assignment5;
/*
 * File: CVS_Console_Parser.java
 * ========================================================
 * A program that reads the content of the csv format file and output the
 * content of the specific column. Column number can be chosen by user in the
 * existing range. Document content in cvs format encoded in column cell
 * content according to the cvs translation rules.
 */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CVS_Console_Parser extends TextProgram {
    /*
    .cvs document file path
     */
    String cvsTableFile = "assets/classification.csv";

    public void run() {
        try {
            extractColumn(cvsTableFile, setColumnIndex());
        } catch (IOException e) {
            if (cvsTableFile == null) {
                println("File missed!");
            }
            e.printStackTrace();
        }
    }

    /**
     * Inputs the column number in the exciting range by analyzing the
     * content of cvs-format file and the value that user inputs.
     *
     * @return - column number chosen by member in the existing range.
     * @throws IOException - exception that's occurred in the case if file is
     *                     missed or path is incorrect.
     */
    private int setColumnIndex() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(cvsTableFile));
        /*
        A content of the first string in the cvs document.
         */
        String firstRow = br.readLine();
        int i = 0;
        /* Columns number that next calculated in the program based on the
        content of the document first row.
         */
        int columnsNum = 0;
        /*
        Quotes symbol number tnhat next calculated in the first string of the
         document.
         */
        int quotesNum = 0;
        while (i < firstRow.length()) {
            if (areQuotes(firstRow, i)) {
                quotesNum++;
            }
            if (isColumn(firstRow, i, quotesNum)) {
                columnsNum++;
            }
            i++;
        }
        int columnIndex = readInt("Please enter the index that is in range " +
                "of 0 to " + columnsNum + " : ");
        if (isColumnNumberEligible(columnIndex, columnsNum)) {
            return columnIndex;
        } else {
            while (isColumnNumberOutRange(columnIndex, columnsNum)) {
                columnIndex = readInt("Please enter the index that is in " +
                        "range of 0 to " + columnsNum + " : ");
                if (isColumnNumberEligible(columnIndex, columnsNum)) {
                    return columnIndex;
                }
            }
        }
        return columnIndex;
    }

    /**
     * Checks if the specific symbol is quotes.
     *
     * @param str - a string that represents a content row.
     * @param i   - a counter of the symbols in the string.
     * @return - true if a specific symbol is quotes.
     */
    private boolean areQuotes(String str, int i) {
        if (str.charAt(i) == '"') {
            return true;
        }
        return false;
    }

    /**
     * Checks in the char ',' symbol occurring in the string is a delimiter
     * between the columns.
     *
     * @param str       - a string that represents a content row.
     * @param i         - a counter of the symbols in the string.
     * @param quotesNum - a counter of quotes symbols occurring in the string.
     * @return - true if a delimiter separates the columns.
     */
    private boolean isColumn(String str, int i, int quotesNum) {
        if (str.charAt(i) == ',' && quotesNum % 2 == 0 && quotesNum != 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a column index that user inputs is in range between 0
     * (that is a number of the first column) and the maximal number of
     * columns in the document.
     *
     * @param columnIndex - column number input by user.
     * @param columnsNum  - maximal number of columns in the document.
     * @return - true if the user's input number is within allowed range.
     */
    private boolean isColumnNumberEligible(int columnIndex, int columnsNum) {
        if (columnIndex >= 0 && columnIndex <= columnsNum) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a column index that user inputs is less than 0 (negative
     * number of columns) or greater than the columns number existing in
     * the document.
     *
     * @param columnIndex - column number input by user.
     * @param columnsNum  - maximal number of columns in the document.
     * @return - true if the user's input number is out allowed range.
     */
    private boolean isColumnNumberOutRange(int columnIndex, int columnsNum) {
        if (columnIndex < 0 && columnIndex > columnsNum) {
            return true;
        }
        return false;
    }

    /**
     * Out prints in the console the data contained in the specific column of
     * the cvs document. The cvs data that's analyzing first
     * represented as an ArrayList<String> object and then the specific data
     * is extracted regarding the column index and is put in the new
     * AttayList<String> object.
     *
     * @param filename    - .cvs document file path.
     * @param columnIndex - column number input by user.
     * @return ArryList<String> of data contained in the specific column.
     * @throws IOException - exception that's occurred in the case if file is
     *                     missed or path is incorrect.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) throws IOException {
        ArrayList<String> columnList = readAndConvertFile(filename, columnIndex);
        String сolumnListToString = columnList.toString();
        String removedComasStr = removeRedundantComa(сolumnListToString);
        ArrayList<Integer> quotesPosition = detectQuotesIndex(removedComasStr);
        String result = removeExtraQuotes(quotesPosition, removedComasStr);
        columnList.removeAll(columnList);
        columnList.add(0, result);
        println(columnList);
        return columnList;
    }

    /**
     * Reads csv file from folder and converts its content to
     * ArrayList<String> object. ArrayList contains only data that relates to
     * the specific column. This column is chosen and input by user.
     *
     * @param filename    - .cvs document file path.
     * @param columnIndex - column number input by user.
     * @return ArrayList<String> object of specific column data extracted
     * from .csv file.
     */
    private ArrayList<String> readAndConvertFile(String filename, int columnIndex) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /* An array of data extracted from the cvs document line by line and
        this data collected from the specific column of this document.
        */
        ArrayList<String> columnList = new ArrayList<>();
        while (true) {
            String row = null;
            try {
                row = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (row == null) {
                break;
            }
            columnList.add(extractColumnData(row, columnIndex));
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnList;
    }

    private String removeRedundantComa(String str) {
        int i = 0;
        ArrayList<Integer> comasPositions = new ArrayList<>();
        while (i < str.length()) {
            if (str.charAt(i) == ' ') {
                if (str.charAt(i + 1) == ',') {
                    comasPositions.add(i);
                }
            }
            i++;
        }
        if (str.charAt(1) == ',') {
            comasPositions.add(0, 0);
        }
        if (str.charAt(str.length() - 1) == ',') {
            comasPositions.add(comasPositions.size() + 1, str.length() - 1);
        }
        int k = 0;
        String result = "";
        while (k < comasPositions.size() - 1) {
            result = result + str.substring(comasPositions.get(k) + 2,
                    comasPositions.get(k + 1));
            k++;
        }
        if (comasPositions.size() != 0) {
            result =
                    result + str.substring(comasPositions.get(comasPositions.size() - 1) + 2,
                            str.length() - 1);
        }
        if (result == "") {
            //removes [...] symbols at the beginning and at the end of string.
            str = str.substring(1,str.length() - 1);
            return str;
        }
        return result;
    }

    /**
     * Converts cvs notated string to regular notated string without service
     * quotes.
     *
     * @param symbolIndex - a quotes symbol position number.
     * @param str         - a string represented column data.
     * @return - string represented column data without service quotes.
     */
    private String removeExtraQuotes(ArrayList<Integer> symbolIndex,
                                     String str) {
        String result = "";
        /*
        In order if the column contains elements with no quotes' notation at
        all.
         */
        if (symbolIndex == null) {
            int i = 0;
            while (i < str.length()) {
                result = result + str.charAt(i);
                i++;
            }
            return result;
        } else {
            int k = 0;
            while (k < symbolIndex.size() - 1) {
                result =
                        result + str.substring(symbolIndex.get(k) + 1,
                                symbolIndex.get(k + 1));
                k++;
            }
            if (str.charAt(0) != '"') {
                result = str.charAt(0) + result;
            }
            result = result.replace("\"\"", "\"");
            if (result.charAt(0) == ',') {
                result = result.substring(1,
                        result.length());
            }
            return result;
        }
    }

    /**
     * Finds all quotes that occur in a string. Quote indexes in ascending order
     * are written as ArrayList<Integer> elements.
     *
     * @param str - a string represented column data.
     * @return - ArrayList<Integer> of quotes position indexes.
     */
    private ArrayList<Integer> detectQuotesIndex(String str) {
        ArrayList<Integer> quotesPosition = new ArrayList<>();
        int j = 0;
        int quotesNum = 0;
        while (j <= str.length() - 1) {
            if (str.charAt(j) != '"') {
                j++;
                if (j >= str.length()) {
                    break;
                }
            }
            if (str.charAt(j) == '"') {
                while (str.charAt(j) == '"') {
                    quotesNum++;
                    j++;
                    if (j >= str.length()) {
                        break;
                    }
                    if (str.charAt(j) != '"') {
                        break;
                    }
                }
                if (j >= str.length()) {
                    break;
                }
                if (quotesNum == 1 || quotesNum % 2 == 1) {
                    quotesPosition.add(j - 1);
                }
            }
            quotesNum = 0;
        }
        /*
        In order if the column contains elements with no quotes' notation at
        all.
         */
        if (quotesPosition.isEmpty() == true) {
            return null;
        }
        if (quotesPosition.indexOf(0) != 0) {
            quotesPosition.add(0, 0);
        }
        quotesPosition.add(quotesPosition.size(),
                str.length() - 1);
        return quotesPosition;
    }

    /**
     * Extracts the data from .cvs file based on the column number.
     *
     * @param str         - a string represented column data.
     * @param columnIndex - column number input by user.
     * @return String that represents column data.
     */
    private String extractColumnData(String str, int columnIndex) {
        /*
        A string that accumulates data from cvs file symbol-by-symbol and put
        this data as an element of ArrayList<String> object.
        */
        String cellEntity = "";
        int i = 0;
        /* Columns number/quotes respectively number that next calculated in
        the program based on the content of the document.
        */
        int columnsNum = 0;
        int quotesNum = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '"') {
                quotesNum++;
            }
            if (str.charAt(i) == ',' && quotesNum % 2 == 0 && quotesNum != 1) {
                columnsNum++;
            }
            if (columnIndex == columnsNum) {
                cellEntity = cellEntity + str.charAt(i);
            }
            i++;
        }
        return cellEntity;
    }

}


