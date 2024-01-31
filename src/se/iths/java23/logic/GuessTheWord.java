package se.iths.java23.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Depinder Kaur
 * created on 2024-01-25
 * @version 1.0
 * <p>
 * GuessTheWord
 * <p>
 * GuessTheWord implements all the methods in the GuessingGame interface.
 * In this game, the user should guess a 5-letter word with distinct letters.
 */

public class GuessTheWord implements GuessingGame {

    private int numOfGuesses;

    /**
     * In this method, a list of 5-letter words is created. Then a random number is generated with the size of
     * this list as its upper bound and a random word is selected from the given list.
     * @return A randomly selected word from a list of words.
     */
    @Override
    public String generateNumberOrWord() {
        List<String> allWords = new ArrayList<>();
        Collections.addAll(allWords, "sharp","shelf","shine","slice","solid","space",
                "stand","stone","earth","ebony","eight","entry","extra","towel","cloth","clown",
                "owlet","fairy","faith","lunch","other","their","those","about","jumpy","quick",
                "quack","flirt","jumbo","juicy","melon","crazy","field","glaze","joked","jinks",
                "quake","quark","unzip","blaze","equip","fjord","freak","jerks","judge","mucky",
                "squab","waltz","cloth","zincs","grain","bumpy","champ","craze","float","fishy",
                "picky","unbox","froze","topaz","wacky","zebra","black","brown","white","chunk",
                "choke","comfy","dozen","equal","mixed","fixed","gazes","graze","shirt","inbox",
                "lucky","lymph","pixel","quint","quids","squad","squid","waxed","zesty","spain",
                "fruit","glare","zoned","zonal","aside","brick","boxes","boxer","curvy","dozes",
                "frock","frame","flaky","exams","exact","light","might","fight","right","mixer",
                "lumpy","bumpy","munch","packs","pecks","pinky","prick","mocks","ducks","frown",
                "grate","notes","sport","mayor","flare","dirty","timer","tired","tried","fried");

        Random random = new Random();
        int randomNum = random.nextInt(allWords.size());
        return allWords.get(randomNum);
    }

    /**
     * This method returns a new Object of type GuessEvaluation which saves the number of correct positions and
     * number of incorrect positions in the player's guess.
     * @param numberOrWord This is the secret word chosen from the list of words.
     * @param guess This is the word guessed by the player.
     * @return A new object of type GuessEvaluation.
     */
    public GuessEvaluation checkResult(String numberOrWord, String guess) {
        guess += "     ";
        int letterAtIncorrectPosition = 0, letterAtCorrectPosition = 0;
        for (int i = 0; i < numberOrWord.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (numberOrWord.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        letterAtCorrectPosition++;
                    } else {
                        letterAtIncorrectPosition++;
                    }
                }
            }
        }

        return new GuessEvaluation(letterAtCorrectPosition, letterAtIncorrectPosition);
    }

    /**
     * This method shows the number of the correct positions and incorrect positions of letters in
     * the player's guessed word.
     * @param guessEvaluation This contains the integer values of the number of correct positions and incorrect positions.
     * @return A String which shows the result of the player's guess.
     */
    @Override
    public String showResult(GuessEvaluation guessEvaluation) {
        return "Correct Position: " + guessEvaluation.valueCountAtCorrectPlace() +
                "\nIncorrect Position: " + guessEvaluation.valueCountAtIncorrectPlace();
    }

    /**
     * Returns a boolean depending upon whether the given argument matches the final goal.
     * Note that if the argument does not match the final goal, the number of guesses increase by 1.
     * @param guessResult It is a String of Bs and Cs which gets generated depending upon the player's input.
     * @return True only if the given argument matches the final goal.
     */
    @Override
    public boolean isFinished(String guessResult) {
        if (guessResult.equals("Correct Position: 5\nIncorrect Position: 0")) {
            return true;
        }
        numOfGuesses++;
        return false;
    }

    /**
     * Returns the number of guesses the player takes to guess the secret word.
     * @return The number of guesses.
     */
    @Override
    public int getNumOfGuesses() {
        return numOfGuesses;
    }
}
