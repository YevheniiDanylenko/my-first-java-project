package com.shpp.p2p.cs.ydanylenko.assignment5;
/*
 * File: Assignment5Part3.java
 * ========================================================
 *A console program that finds all English words (if they exist in a vocabulary .txt file
 * that is connected to the program) that contains a specific number of random
 * English letters in the given order.
 * The user inputs randomly 3 letters (set by constant lettersNum = 3) and the
 * program finds all matched words that contain these letters in the order of input.
 * If no matched combination were found program outputs a message that
 * indicates this case. Also, there is a check on quantity of input symbols,
 * as well as on it's belonging to an English letter symbols.
 */

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Assignment5Part3 extends TextProgram {
    /*
    The quantity of letters allowed to be input.
     */
    private static final int lettersNum = 3;
    /*
    ArrayList of words uploading from the dictionary file.
     */
    ArrayList<String> wordsInDictionary;
    /*
    String line that accepts user's input.
     */
    String consoleInput;
    /*
    A class that allows to check a numbers of syllables in the world.
     */
    SyllableCounting sc = new SyllableCounting();

    public void run() {
        try {
            readDictionary();
            while (true) {
                consoleInput = readLine("Please, enter " + lettersNum + " " +
                        "letters: ");
                consoleInput = consoleInput.toLowerCase();
                println(findMatches(consoleInput));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds all matches of the user's input letters consequences and words from
     * the dictionary file.
     *
     * @param consoleLine - input symbols.
     * @return - array list of matched words.
     */
    private ArrayList<String> findMatches(String consoleLine) {
        /*
        Array list of matched words.
         */
        String letter1 = "";
        String letter2 = "";
        String letter3 = "";
        ArrayList<String> wordsIncludingLettersCombination = new ArrayList<>();
        if (checkSymbols(consoleLine)) {
            /* Strings of input letters respectively.
             */
            letter1 = consoleLine.charAt(0) + ""; //1 letter --> 0
            // symbol of the input letters combination.
            letter2 = consoleLine.charAt(1) + "";
            letter3 = consoleLine.charAt(2) + "";
            println(letter1 + ";" + letter2 + ";" + letter3);
        }
        int i = 0;
        while (i < wordsInDictionary.size()) {
            if (wordsInDictionary.get(i) != null) {
                if (doLettersMetSearchCriteria(wordsInDictionary.get(i), letter1, letter2,
                        letter3)) {
                    wordsIncludingLettersCombination.add(wordsInDictionary.get(i));
                }
            } else {
                break;
            }
            i++;
        }
        return wordsIncludingLettersCombination;
    }

    /**
     * Checks the input symbols if they are English alphabet letters and their
     * amount is strictly the same as it's preset by lettersNum constant.
     *
     * @param consoleLine - input symbols.
     * @return - "true" if all the above conditions met.
     */
    private boolean checkSymbols(String consoleLine) {
        if (sc.checkWord(consoleLine)) {
            if (consoleLine.length() == lettersNum) {
                return true;
            }
        }
        println("Seems you entered non-letter symbols or more then " + lettersNum + " symbols");
        return false;
    }

    /**
     * Checks if the input letters meet the search criteria: the input letters are
     * presented in the words in the given order.
     *
     * @param currentWord - current element of ArrayList<String>
     *                    wordsInDictionary.
     * @param letter1     - first input letter.
     * @param letter2     - second input letter.
     * @param letter3     - third input letter.
     * @return - "true" if the search criteria is met.
     */
    private boolean doLettersMetSearchCriteria(String currentWord,
                                               String letter1, String letter2
            , String letter3) {
        int letter1Index;
        boolean letter2Index = false;
        int letter3Index = 0;
        if (currentWord.length() >= lettersNum) {
            if (currentWord.contains(letter1)) {
                letter1Index = currentWord.indexOf(letter1);
            } else {
                return false;
            }
            if (currentWord.substring(letter1Index + 1).contains(letter3)) {
                letter3Index =
                        currentWord.lastIndexOf(letter3);
                letter3Index = currentWord.substring(0,
                        letter3Index).length();
            }
            if (letter1Index <= letter3Index - 1) {
                if (currentWord.substring((letter1Index + 1), letter3Index).contains(letter2)) {
                    letter2Index = true;
                } else {
                    return false;
                }
            }
            return letter1Index < letter3Index && letter2Index;
        }
        return false;
    }

    /**
     * Inputs a words string-by-string from dictionary .txt file and put
     * these words in the same order to the ArrayList<String>.
     *
     * @throws IOException - exception that occurs if no file found.
     */
    private void readDictionary() throws IOException {
        wordsInDictionary = new ArrayList<>();
        /*
        Pathway to the file with the dictionary.
         */
        String dictionary = "assets/en-dictionary.txt";
        BufferedReader br = new BufferedReader(new FileReader(dictionary));
        /*
        String that accepts string-by-string a word from dictionary.
         */
        String wordFromDictionaryFile;
        try {
            while (true) {
                wordFromDictionaryFile = br.readLine();
                wordsInDictionary.add(wordFromDictionaryFile);
                if (wordFromDictionaryFile == null) {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

