//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GuessingGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BullsAndCowsTest {

    private GuessingGame game;

    @BeforeEach
    public void setup() {
        game = new BullsAndCows();
    }

    @Test
    public void generateGoalTest() {
        String randomNum = game.generateNumberOrWord();
        assertEquals(4, randomNum.length());     //verifies that generated number has 4 digits
        assertEquals(4, randomNum.chars().distinct().count());     //verifies that generated number has 4 distinct digits
    }

    @RepeatedTest(100)
    public void generateARandomNumberWithDistinctDigits() {
        String randomNum = game.generateNumberOrWord();
        assertNotEquals("1111", randomNum);
    }

    @Test
    public void showResult() {
        String result1 = game.showResult(game.checkResult("1234","2134"));
        assertEquals("BB,CC", result1);

        String result2 = game.showResult(game.checkResult("1234"," "));     //verifies when user enters nothing
        assertEquals(",", result2);

        String result3 = game.showResult(game.checkResult("1234", "5671"));
        assertEquals(",C", result3);

        String result4 = game.showResult(game.checkResult("1234", "abcd"));
        assertEquals(",", result4);

//        String result5 = game.showResult(game.checkResult("1234", null));
//        assertEquals(",", result5);
    }
}
