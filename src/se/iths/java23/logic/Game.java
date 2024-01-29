//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.logic;

public interface Game {

    String generateGoal();
    int getNumOfGuesses();
    String getResult(String numberOrWord, String guess);
    boolean isFinished(String result);
}
