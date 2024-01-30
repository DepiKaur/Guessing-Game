//Depinder Kaur, 2024-01-27, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;
import se.iths.java23.logic.GuessEvaluation;
import se.iths.java23.logic.GuessingGame;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameControllerWithMockitoTest {

    private GameController gameController;

    @Mock
    private GuessingGame game;

    @Mock
    private PlayerDao playerDao;

    private MockIO mockIO;

    @BeforeEach
    public void setup() {
        mockIO = new MockIO();
        gameController = new GameController(game, mockIO, playerDao);
    }

    class MockIO implements IO {

        private List<String> inputs = new ArrayList<>();
        private List<String> outputs = new ArrayList<>();

        public List<String> getInputs() {
            return inputs;
        }

        public List<String> getOutputs() {
            return outputs;
        }

        public MockIO() {
            inputs.add("TestPlayer");
            inputs.add("2356");
            inputs.add("5678");
        }

        @Override
        public String input() {
            String value = inputs.get(0);
            inputs.remove(0);
            return value;
        }

        @Override
        public void output(String s) {
            outputs.add(s);
            System.out.println(s);
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean yesNo(String prompt) {
            return false;
        }

        @Override
        public void exit() {
            gameController.setPlaying(false);
        }
    }

    @Test
    public void testRunWithValidPlayer() throws InterruptedException {
        Player p1 = new Player("Player1", 3.57);
        Player p2 = new Player("Player2", 4.39);
        Player p3 = new Player("Player3", 3.92);

        when(playerDao.getPlayerIdByName(anyString())).thenReturn(2);
        when(game.generateNumberOrWord()).thenReturn("5678");

        GuessEvaluation guessEv1 = new GuessEvaluation(0,2);
        when(game.checkResult("5678","2356")).thenReturn(guessEv1);
        when(game.showResult(guessEv1)).thenReturn(",CC");
        when(game.isFinished(",CC")).thenReturn(false);

        GuessEvaluation guessEv2 = new GuessEvaluation(4,0);
        when(game.checkResult("5678","5678")).thenReturn(guessEv2);
        when(game.showResult(guessEv2)).thenReturn("BBBB,");
        when(game.isFinished("BBBB,")).thenReturn(true);

        ArrayList<Player> topTenPlayers = new ArrayList<>();
        Collections.addAll(topTenPlayers, p1, p2, p3);
        when(playerDao.getTopTen()).thenReturn(topTenPlayers);

        gameController.play();

        assertEquals(0, mockIO.getInputs().size());
        assertEquals(11, mockIO.getOutputs().size());
        verify(playerDao, atMostOnce()).getPlayerIdByName(anyString());
        verify(playerDao, atLeast(1)).getTopTen();
        verify(game, times(2)).checkResult(anyString(),anyString());
    }
}
