package com.shpp.p2p.cs.ydanylenko.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.ConsoleProgram;

/**
 * Assignment3Part5 class allows imitating a Saint-Petersburg paradox playing a
 * game with 2 players. Class RandomGenerator used inside this class simulates
 * coin flipping and is the part game scenario depends on the flipped coin side.
 */
public class Assignment3Part5 extends ConsoleProgram {
    //The amount money won by the Lucky to end the game.
    private static final int WIN_MONEY = 20;
    /* The specific color effects from:
    https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */
    public static final String ANSI_RESET = "\u001B[0m";   //Reset the color.
    // effects execute to the specific string.
    public static final String ANSI_CYAN = "\u001B[36m";   //Cyan regular font.
    public static final String RED_BOLD = "\033[1;31m";    //Bold red font.
    public static final String BLUE_BOLD = "\033[1;34m";   //Bold blue font.
    //Reset the effect of string coloring.
    public static final String RESET = "\033[0m";
    //Set yellow background to the string.
    public static final String YELLOW_BACKGROUND = "\033[43m";

    public void run() {
        println();
        playGame();

    }

    /* Imitate a game following the next rules:
     1. Game is played by 2 players - Lucky and Sweaty.
     2. First bet that Sweaty puts on the table is $1.
     3. If a head flipped the bet double twice otherwise Lucky wins 1$ and adds
     this amount to the "total".
     4. Game ended after Lucky collected 20$ in total. */
    private void playGame() {
        //The counted on every game round won total money.
        int total = 0;
        //To initiate the fame bet.
        int bet = 1;
        //The game rounds counter.
        int game_counter = 0;
        //The head flipping counter.
        int head_counter = 0;

        while (total <= WIN_MONEY) {
            /* Get the boolean "true" value when the head is flipped and the
            "false" value when the tail is flipped.
             */
            flipCoin();
            if (!flipCoin()) {//tail fall.
                bet = 1;
            } else {//head fall.
                bet = 2 * bet; //increase the bet to twice every time when
                // head is flipped.
                head_counter++;
            }
            println(showGameNum(game_counter));
            total = total + bet;
            game_counter++;
            if (head_counter > 0) {
                printHeadCounter(bet);
                head_counter = 0;
            } else {
                println(showTailFallingEarn(bet));
            }
            println(showTotal(total));
            if (total <= WIN_MONEY) {
                println(showMotivateMessage());
                println();
            }
        }
        println("GAME OVER!");
        println();
        println(showGameCounter(game_counter, total));
    }

    /* Imitates coin flipping by using random boolean value.*/
    private boolean flipCoin() {
        return RandomGenerator.getInstance().nextBoolean(0.5);
    }

    /** Prints the game round number on console.
     * @param game_num - input current game counter value;
     * @return - returns a console message containing the round number.
     */
    private String showGameNum(int game_num) {
        return BLUE_BOLD + "This is game number " + (game_num + 1) + ANSI_RESET;
    }

    /** Prints the console message with the won money in the current round if
     * the tails fall after the first coin flipping.
     * @param bet - a bet that doubled every time after the head fell. */
    private void printHeadCounter(int bet) {
            println(showHeadCounter(bet));
    }

    /** Prints console message with in the round earning money after the tail
     *  was fallen.
     * @param bet - a bet that Sweaty puts on the table at the game beginning.
     * @return - a console message containing bet amount.
     */
    private String showTailFallingEarn(int bet) {
        return "This game you earned " + bet + " $ because tails flipped from " +
                "the beginning of the game";
    }

    /** Prints the console message showing the current total won money amount.
     * @param total - the current won money amount;
     * @return - the console message with the total money.
     */
    private String showTotal(int total) {
        return "YOUR TOTAL IS " + total + " $";
    }

    /** Prints the console message with the game results.
     * @param game_counter - the number of played game rounds;
     * @param total - the total money won on the all game rounds;
     * @return - a console message with information on total money amount won
     * and game rounds number.
     */
    private String showGameCounter(int game_counter, int total) {
        return YELLOW_BACKGROUND + "It took " + game_counter + " games to win" +
                " " + total + " $" + RESET;
    }

    /** Prints the number of the sequenced head falls to make more clear the
     * game algorithm.
     * @param bet - a bet that doubled every time after the head fell.
     * @return a console message with the number of fallen heads in a row.
     */
    private String showHeadCounter(int bet) {
        return ANSI_CYAN + "This game you earned " + bet + " $ because of " + (int) (Math.log10(bet) / Math.log10(2)) + " head(s) flipped in a row" + ANSI_RESET;
    }

    /* Prints a motivating message that also indicates the game's continuing process.*/
    private String showMotivateMessage() {
        return RED_BOLD + "Let's flip a coin again. Good luck!" + ANSI_RESET;
    }
}

