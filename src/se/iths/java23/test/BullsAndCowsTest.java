//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BullsAndCowsTest {

    private Game game;

    @BeforeEach
    public void setup() {
        game = new BullsAndCows();
    }

    @Test
    public void generateGoalTest() {
        String randomNum = game.generateGoal();
        assertEquals(4, randomNum.length());     //verifies that generated number has 4 digits
        assertEquals(4, randomNum.chars().distinct().count());     //verifies that generated number has 4 distinct digits
    }

    @Test
    public void showResult() {
        String result1 = game.showResult("1234","2134");
        assertEquals("BB,CC", result1);

        String result2 = game.showResult("1234",null);     //verifies when user enters nothing
        assertEquals(",", result2);

        String result3 = game.showResult("1234", "5671");
        assertEquals(",C", result3);

        String result4 = game.showResult("1234", "abcd");
        assertEquals(",", result4);
    }
}
