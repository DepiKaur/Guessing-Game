package se.iths.java23.logic;

/**
 * @author Sofie van Dingenen
 * created on 2024-01-29
 * This record saves the number of correct and incorrect positions in a guess when compared to the secret/final goal.
 * @param valueCountAtCorrectPlace This saves the number of digits/letters at the correct position in the number/word
 *                                 guessed by the player.
 * @param valueCountAtIncorrectPlace This saves the number of digits/letters at the incorrect position in the number/word
 *                                 guessed by the player.
 */
public record GuessEvaluation (int valueCountAtCorrectPlace, int valueCountAtIncorrectPlace) {
}
