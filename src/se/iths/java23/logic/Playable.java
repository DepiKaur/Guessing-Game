package se.iths.java23.logic;

public interface Playable {

    String generateNumberOrWord();
    String showResult(String numberOrWord, String guess);

    boolean gameHasNotStarted();
}
