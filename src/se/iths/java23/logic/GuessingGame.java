//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.logic;

public interface GuessingGame {

    String generateNumberOrWord();
    String showResult(GuessEvaluation guessEvaluation);
    GuessEvaluation checkResult(String number, String guess);
    boolean isFinished(String result);
    int getNumOfGuesses();
}
