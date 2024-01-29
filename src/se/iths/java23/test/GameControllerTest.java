//Depinder Kaur, 2024-01-25, depinder.kaur@iths.se

package se.iths.java23.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.iths.java23.database.DAO;
import se.iths.java23.io.IO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.Game;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameControllerTest {

    private GameController gameController;
    private Game game ;
    private MockDB mockDB;
    private MockIO mockIO;

    @BeforeEach
    public void setup(){
        game = Mockito.mock(BullsAndCows.class);
        mockDB = new MockDB();
        mockIO = new MockIO();
        gameController = new GameController(game, mockIO, mockDB);
    }

    @Test
    public void testRunWithValidPlayer() throws SQLException, InterruptedException {
        when(game.generateGoal()).thenReturn("5678");
        when(game.getResult("5678","2356")).thenReturn(",CC");
        when(game.getResult("5678","5678")).thenReturn("BBBB,");
        gameController.run();

        assertEquals(11, mockIO.getOutputs().size());
    }

    class MockDB implements DAO {

        @Override
        public int getPlayerIdByName(String name) {
            return 2;
        }

        @Override
        public void setResultForAnPlayer(int numOfGuesses, int playerId) {

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

            Player p1 = new Player("testPlayer",3.5);
            Player p2 = new Player("testPlayer2",3.9);
            Player p3 = new Player("testPlayer3",1.9);

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
}
