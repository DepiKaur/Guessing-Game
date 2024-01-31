package se.iths.java23.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Depinder Kaur
 * created on 2024-01-23
 * @version 1.0
 * <p>
 * BullsAndCows
 * <p>
 * BullsAndCows implements all the methods in the GuessingGame interface.
 * In this game, the user should guess a 4-digit number with distinct digits.
 */

public class BullsAndCows implements GuessingGame {

    private int numOfGuesses = 1;

    /**
     * Returns a 4-digit number of type String.
     * An ArrayList containing digits from 0 to 9 is shuffled. The final number is obtained by concatenating
     * an empty String with digits at the first four indices of the shuffled Arraylist.
     * @return a 4-digit number with different digits and of type String
     */
    public String generateNumberOrWord() {
        List<Integer> digits = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Collections.shuffle(digits);
        // pick four unique digits
        return "" + digits.get(0) + digits.get(1) + digits.get(2) + digits.get(3);
    }

    /**
     * This method goes through all the digits in the secret (or generated) number, checks if
     * these digits are present in the guessed number by getting their index.
     * If a digit has the same index as in the secret number, it is counted as a bull,
     * if the digit has a different index, it's a cow.
     * The number of bulls and cows is then saved in the form of a new object of record GuessEvaluation.
     * @param number This is the secret number which is the actual goal for the guessing game.
     * @param guess This is the number (type String) which is guessed by the player.
     * @return a new object of record GuessEvaluation
     */
    public GuessEvaluation checkResult(String number, String guess) {
        int cows = 0, bulls = 0;
        for (int i = 0; i < number.length(); i++) {
            char digitInNumber = number.charAt(i);              //gets digit of the secret number at a specific index

            int positionInGuess = guess.indexOf(digitInNumber);      //gets index of the digit in the guessed number
            boolean digitPresentInGuess = (positionInGuess != -1);     //if digit is present in guess, position >= 0
                                                                       //if digit is absent in guess, position = -1
            if (i == positionInGuess) {
                bulls++;
            } else if (digitPresentInGuess) {
                cows++;
            }
        }
        return new GuessEvaluation(bulls, cows);
    }

    /**
     * Returns a String of B (bulls) and C (cows) using the GuessEvaluation argument
     * which has the info about the number of bulls and number of cows.
     * Note that the Bs are concatenated with Cs using a comma.
     * @param guessEvaluation It has the number of bulls and cows as its parameters.
     * @return A String of Bs (i.e. bulls) and Cs (i.e. cows)
     */
    public String showResult(GuessEvaluation guessEvaluation) {

        String result = "";
        for (int i = 0; i < guessEvaluation.valueCountAtCorrectPlace(); i++) {
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < guessEvaluation.valueCountAtIncorrectPlace(); i++) {
            result = result + "C";
        }
        return result;
    }

    /**
     * Returns a boolean depending upon whether the given argument matches the final goal (i.e. "BBBB,").
     * Note that if the argument does not match the final goal, the number of guesses increase by 1.
     * @param guessResult It is a String of Bs and Cs which gets generated depending upon the player's input.
     * @return True only if the given argument matches the final goal i.e. "BBBB," .
     */
    @Override
    public boolean isFinished(String guessResult) {
        if (guessResult.equals("BBBB,")) {
            return true;
        }
        numOfGuesses++;
        return false;
    }

    /**
     * Returns the number of guesses it takes to guess the generated secret number.
     * @return The number of guesses.
     */
    public int getNumOfGuesses() {
        return numOfGuesses;
    }
}
