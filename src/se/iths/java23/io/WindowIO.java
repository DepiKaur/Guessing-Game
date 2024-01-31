package se.iths.java23.io;

import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GuessingGame;
import se.iths.java23.logic.GuessTheWord;

/**
 * @author Depinder Kaur
 * @date 2024-01-23
 * @version 1.0
 * <p>
 * <h2>WindowIO</h2>
 * WindowIO implements the <i>IO</i> interface and interacts with user through UI.
 */

public class WindowIO implements IO {

    private SimpleWindow sw;

    public WindowIO(GuessingGame guessingGame) {
        if (guessingGame instanceof BullsAndCows) {
            sw = new SimpleWindow("Bulls & Cows");
        } else if (guessingGame instanceof GuessTheWord) {
            sw = new SimpleWindow("Guess the Word");
        }
    }

    /**
     * @return User input of type String.
     */
    @Override
    public String input() {
        return sw.getString();
    }

    /**
     * This method prints out the given String in the window.
     * @param s The given String to be shown as output.
     */
    @Override
    public void output(String s) {
        sw.addString(s+"\n");
    }

    /**
     * This method clears the UI window.
     */
    @Override
    public void clear() {
        sw.clear();
    }

    /**
     * This method asks the user if he wants to continue to play the guessing game,
     * after he has correctly guessed the secret number/word.
     * @param prompt This asks the user if he wants to continue to play the guessing game.
     * @return A boolean value which decides if the game will continue or not.
     */
    public boolean yesNo(String prompt) {
        return sw.yesNo(prompt);
    }

    /**
     * This method ends the program if the user does not want to play further.
     */
    @Override
    public void exit() {
       sw.exit();
    }
}
