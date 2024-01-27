//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.java23.logic.Game;
import se.iths.java23.logic.Scrabble;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScrabbleTest {

    private Game game;

    @BeforeEach
    public void setup() {
        game = new Scrabble();
    }

    @Test
    public void generateGoalTest() {
        String randomWord = game.generateGoal();
        assertEquals(5, randomWord.length());     //verifies that chosen word has 5 letters
        assertEquals(5, randomWord.chars().distinct().count());     //verifies that selected word has 5 distinct letters
    }

    @Test
    public void showResultTest() {
        String result1 = game.showResult("fairy", null);
        String expectedResult1 = "Correct Position: 0\nIncorrect Position: 0";
        assertEquals(expectedResult1, result1);

        String result2 = game.showResult("fairy", "query");
        String expectedResult2 = "Correct Position: 2\nIncorrect Position: 0";
        assertEquals(expectedResult2, result2);

        String result3 = game.showResult("fairy", "raiyf");
        String expectedResult3 = "Correct Position: 2\nIncorrect Position: 3";
        assertEquals(expectedResult3, result3);

        String result4 = game.showResult("fairy", "fairy");
        String expectedResult4 = "Correct Position: 5\nIncorrect Position: 0";
        assertEquals(expectedResult4, result4);

        String result5 = game.showResult("fairy", "FAIRY");
        String expectedResult5 = "Correct Position: 0\nIncorrect Position: 0";
        assertEquals(expectedResult5, result5);
    }
}
