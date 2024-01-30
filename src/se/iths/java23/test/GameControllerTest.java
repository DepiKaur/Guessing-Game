//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GuessEvaluation;
import se.iths.java23.logic.GuessingGame;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.Player;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    private GameController gameController;
    private GuessingGame game;
    private MockDao mockDao;
    private MockIO mockIO;

    @BeforeEach
    public void setup(){
        mockIO = new MockIO();
        mockDao = new MockDao();
        game = Mockito.mock(BullsAndCows.class);
        gameController = new GameController(game, mockIO, mockDao);
    }

    @Test
    public void testRunWithValidPlayer() throws InterruptedException {
        when(game.generateNumberOrWord()).thenReturn("5678");

        GuessEvaluation guessEv1 = new GuessEvaluation(0,2);
        when(game.checkResult("5678","2356")).thenReturn(guessEv1);
        when(game.showResult(guessEv1)).thenReturn(",CC");
        when(game.isFinished(",CC")).thenReturn(false);

        GuessEvaluation guessEv2 = new GuessEvaluation(4,0);
        when(game.checkResult("5678","5678")).thenReturn(guessEv2);
        when(game.showResult(guessEv2)).thenReturn("BBBB,");
        when(game.isFinished("BBBB,")).thenReturn(true);
        gameController.play();

        assertEquals(0, mockIO.getInputs().size());
        assertEquals(11, mockIO.getOutputs().size());
    }

    class MockDao implements PlayerDao {

        @Override
        public int getPlayerIdByName(String name) {
            return 2;
        }

        @Override
        public void setResultForAPlayer(int numOfGuesses, int playerId) {

        }

        @Override
        public ResultSet getAllPlayers() {
            return null;
        }

        @Override
        public ResultSet getResultByPlayerId(int playerId) {
            return null;
        }

        @Override
        public ArrayList<Player> getTopTen() {
            ArrayList<Player> players = new ArrayList<>();

            Player p1 = new Player("Ann",3.5);
            Player p2 = new Player("Bob",3.9);
            Player p3 = new Player("Dave",1.9);

            Collections.addAll(players,p1,p2,p3);

            return players;
        }
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
            inputs.add("testPlayer");
            inputs.add("2356");
            inputs.add("5678");
        }

        @Override
        public String input() {
            String value = getInputs().get(0);
            getInputs().remove(0);
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
}
