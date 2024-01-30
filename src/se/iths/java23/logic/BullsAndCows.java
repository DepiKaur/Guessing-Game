//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BullsAndCows implements GuessingGame {

    private int numOfGuesses = 1;

    public String generateNumberOrWord() {
        List<Integer> digits = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Collections.shuffle(digits);
        // pick four unique digits
        return "" + digits.get(0) + digits.get(1) + digits.get(2) + digits.get(3);
    }


    public GuessEvaluation checkResult(String number, String guess) {
        int cows = 0, bulls = 0;
        for (int i = 0; i < number.length(); i++) {
            char digitInNumber = number.charAt(i);

            int positionInGuess = guess.indexOf(digitInNumber);
            boolean digitPresentInGuess = (positionInGuess != -1);

            if (i == positionInGuess) {
                bulls++;
            } else if (digitPresentInGuess) {
                cows++;
            }
        }
        return new GuessEvaluation(bulls, cows);
    }

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

    @Override
    public boolean isFinished(String guessResult) {
        if (guessResult.equals("BBBB,")) {
            return true;
        }
        numOfGuesses++;
        return false;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }
}
