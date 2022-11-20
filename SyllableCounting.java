package com.shpp.p2p.cs.ydanylenko.assignment5;
/*
 * File: SyllableCounting.java
 * ========================================================
 * A console program that takes out of the console a string containing an English
 * word and then returns a number of syllables in this word.
 * The syllable counting rules are the next:
 * 1. Words with more than 1 syllable that ended with the letter 'e' don't count
 * this 'e' in a syllable counting.
 * 2. Words with no syllables but that are ended with 'e' are counted as containing
 * 1 syllable.
 * 3. If a consonant is followed by several vowels, then the syllable is considered
 * to contain only one vowel.
 */
import com.shpp.cs.a.console.TextProgram;

public class SyllableCounting extends TextProgram {
    /*
    Counter of vowels that fits the specific counting rules mentioned at the
    class description.
    */
    public int counterOfVowels = 0;
    /*
    Counter of all consonants in the word.
    */
    public int counterOfConsonants = 0;

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
            counterOfVowels = 0;
            counterOfConsonants = 0;
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        if (!checkWord(word)) {
            println("  It's not a word!");
            return 0;
        } else {
            /*
            Coerces a string to a string with all uppercase characters.
            */
            word = word.toLowerCase();
            /*
            Cycle that counts syllables in the specific word.
            */
            for (int i = 0; i < word.length(); ) {
                /* Edge case for words consisting of only 1 vowel. */
                if (isCurrentLetterVowel(word.charAt(i))) {
                    counterOfVowels++;
                    i++;
                    if (i >= word.length()) {
                        return counterOfVowels;
                    }
                    /* Counts vowels for words consisting of 2 and more letters */
                    if (word.length() > 1) {
                        while (isCurrentLetterVowel(word.charAt(i))) {
                            i++;
                            if (i >= word.length()) {
                                return counterOfVowels;
                            }
                        }
                    }
                }
                /* Counts consonants. */
                if (!isCurrentLetterVowel(word.charAt(i))) {
                    counterOfConsonants++;
                    /* Checks the condition if a word has no syllables but
                    ends with 'e'. */
                    if (counterOfConsonants == word.length() - 2 &&
                            word.charAt(word.length() - 1) == 'e') {
                        counterOfVowels = 1;
                        return counterOfVowels;
                    }
                    /* Checks the condition if a word ends with 'e' and has
                    other vowels before the following 'e'. */
                    if (counterOfConsonants < word.length() - 2 &&
                            word.charAt(word.length() - 1) == 'e' &&
                            !isCurrentLetterVowel(word.charAt(word.length() -2))) {
                        /* Trim the last letter 'e'. */
                        word = (String) word.subSequence(0, word.length() - 2);
                    }
                    i++;
                }
            }
            return counterOfVowels;
        }
    }

    /**
     * Checks if the input string consists only of letters of English alphabet.
     * @param word - a string representing a word.
     * @return - "true" if all the sting elements are English letters only.
     */
    protected boolean checkWord(String word) {
        String modifiedWord = word.toLowerCase();
        /* Numbers of letters in the English alphabet. */
        int englishAlphabetLength = 26;
        /* A char array of encoded in UTF-16 notation lowercase English letters
        symbols.
        Thus, letter "a" is encoded by number 97; letter "b" is encoded by
        number 98, etc. */
        char[] utf16EnglishLettersCodesArray = new char[englishAlphabetLength];
        /* A UTF-16 notation decimal number encoding letter "a" starting the. */
        char decimalUTF16CodeOfLetterA = 97;
        int i = 0;
        /* A loop that sets the char array elements as the encoded in UTF-16
        notation upper English letters alphabetic sequence starting from
        letter "a" and ended with letter "z". */
        while (i < englishAlphabetLength) {
            utf16EnglishLettersCodesArray[i] = decimalUTF16CodeOfLetterA;
            i++;
            decimalUTF16CodeOfLetterA++;
        }
        /* A loop that element-by-element checks if a current string symbol
         * is a letter. */
        for (int k = 0; k < modifiedWord.length(); ) {
            for (int n = 0; n < englishAlphabetLength; ) {
                if (modifiedWord.charAt(k) == utf16EnglishLettersCodesArray[n]) {
                    k++;
                    n = 0;
                    if (k == modifiedWord.length()) {
                        return true;
                    }
                } else {
                    n++;
                    if (n == englishAlphabetLength) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given English letter is a vowel.
     * @param currentLetter - the input letter to be checked.
     * @return "true" if the letter is vowel.
     */
    private boolean isCurrentLetterVowel(char currentLetter) {
        char[] vowels = {'e', 'a', 'i', 'o', 'u', 'y'};
        for (int j = 0; j < vowels.length; ) {
            if (currentLetter != vowels[j]) {
                j++;
                if (j >= vowels.length) {
                    break;
                }
            }
            if (currentLetter == vowels[j]) {
                return true;
            }
        }
        return false;
    }
}