//Depinder Kaur, 2024-01-24, depinder.kaur@iths.se

package se.iths.java23.logic;

import se.iths.java23.database.Database;
import se.iths.java23.database.DatabaseController;
import se.iths.java23.io.IO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameController {

    private Game game;
    private IO io;
    private Database dbController;

    private boolean isPlaying = true;

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public GameController(Game game, IO io, Database dbController) {
        this.game = game;
        this.io = io;
        this.dbController = dbController;
    }

    public void run() throws SQLException, InterruptedException {
        io.output("Enter your user name:\n");
        String playerName = io.input();
        int playerId = dbController.getPlayerIdByName(playerName);

        if (playerId == 0) {
            io.output("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }


        while (isPlaying) {
            String goal = game.generateGoal();
            io.clear();
            io.output("New game:\n");
            //comment out or remove next line to play real games!
            io.output("For practice, goal is: " + goal + "\n");
            String guess = io.input();
            io.output(guess +"\n");
            int numOfGuess = 1;
            String result = game.showResult(goal, guess);
            io.output(result + "\n");

            String resultBullsAndCows = "BBBB,";
            String resultScrabble = "Correct Position: 5\nIncorrect Position: 0";

            while (!result.equals(resultBullsAndCows)) {
                numOfGuess++;
                guess = io.input();
                io.output(guess +": ");
                result = game.showResult(goal, guess);
                io.output(result + "\n");
            }
            dbController.setResultForAnPlayer(numOfGuess, playerId);
            showTopTen();
            isPlaying = io.yesNo("Correct, it took " + numOfGuess
                    + " guesses\nContinue?");
        }
        io.exit();
    }

    private void showTopTen() throws SQLException {
        io.output("Top Ten List\n    Player     Average\n");
        int pos = 1;

        ArrayList<Player> topTenPLayersList = dbController.getTopTen();
        topTenPLayersList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : topTenPLayersList) {
            io.output(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }

    }
}
