//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GuessEvaluation;
import se.iths.java23.logic.GuessingGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BullsAndCowsTest {

    private GuessingGame game;

    @BeforeEach
    public void setup() {
        game = new BullsAndCows();
    }

    @Test
    public void generateNumberOrWordTest() {
        String generatedNum = game.generateNumberOrWord();
        assertEquals(4, generatedNum.length());
    }

    @RepeatedTest(100)
    public void generateARandomNumberWithDistinctDigits() {
        String generatedNum = game.generateNumberOrWord();
        assertNotEquals("1111", generatedNum);
    }

    @Test
    public void checkResultTest() {
        GuessEvaluation guessEv1, guessEv2, guessEv3, guessEv4;
        guessEv1 = game.checkResult("1234","2134");
        assertEquals(new GuessEvaluation(2,2), guessEv1);

        //verifies when user enters nothing
        guessEv2 = game.checkResult("1234","");
        assertEquals(new GuessEvaluation(0,0), guessEv2);

        guessEv3 = game.checkResult("1234", "5671");
        assertEquals(new GuessEvaluation(0,1), guessEv3);

        guessEv4 = game.checkResult("1234", "abcd");
        assertEquals(new GuessEvaluation(0,0), guessEv4);
    }

    @Test
    public void showResult() {
        String result1 = game.showResult(new GuessEvaluation(2,2));
        assertEquals("BB,CC", result1);

        //verifies when user enters nothing
        String result2 = game.showResult(new GuessEvaluation(0,0));
        assertEquals(",", result2);

        String result3 = game.showResult(new GuessEvaluation(0,1));
        assertEquals(",C", result3);

        String result4 = game.showResult(new GuessEvaluation(0,0));
        assertEquals(",", result4);
    }

    @Test
    public void isFinishedTest() {
        assertTrue(game.isFinished("BBBB,"));
        assertFalse(game.isFinished(","));
        assertFalse(game.isFinished("BB,CC"));
    }
}
