package se.iths.java23.logic;

import se.iths.java23.database.DatabaseController;
import se.iths.java23.io.IO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameController {

    Playable game;
    IO io;
    DatabaseController dbController;
    public GameController(Playable game, IO io) {
        this.game = game;
        this.io = io;
    }

    public void setDb(DatabaseController dbController) {
        this.dbController = dbController;
    }
    boolean answer = true;

    public void run() throws SQLException, InterruptedException {

        io.output("Enter your user name:\n");
        String name = io.input();
        int id = dbController.login(name);

        while (answer) {
            String goal = game.generateNumberOrWord();
            io.clear();
            io.output("New game:\n");
            //comment out or remove next line to play real games!
            io.output("For practice, number is: " + goal + "\n");
            String guess = io.input();
            io.output(guess +"\n");
            int nGuess = 1;
            String result = game.showResult(goal, guess);
            io.output(result + "\n");

            while ( ! result.equals("BBBB,")) {
                nGuess++;
                guess = io.input();
                io.output(guess +": ");
                result = game.showResult(goal, guess);
                io.output(result + "\n");
            }
            dbController.setResultForAnPlayer(nGuess, id);
            showTopTen();
            answer = io.yesNo("Correct, it took " + nGuess
                    + " guesses\nContinue?");

        }
        io.exit();
    }

    private ArrayList<PlayerAverage> getTopTen() throws SQLException {
        ArrayList<PlayerAverage> topTenPlayersList = new ArrayList<>();
        ResultSet allPlayersRS = dbController.getAllPlayers();
        ResultSet resultsByPlayerIdRS;
        while(allPlayersRS.next()) {
            int id = allPlayersRS.getInt("id");
            String name = allPlayersRS.getString("name");
            resultsByPlayerIdRS = dbController.getResultByPlayerId(id);
            int nGames = 0;
            int totalGuesses = 0;
            while (resultsByPlayerIdRS.next()) {
                nGames++;
                totalGuesses += resultsByPlayerIdRS.getInt("result");
            }
            if (nGames > 0) {
                topTenPlayersList.add(new PlayerAverage(name, (double)totalGuesses/nGames));
            }
        }
        return topTenPlayersList;
    }

    private void showTopTen() throws SQLException {


        io.output("Top Ten List\n    Player     Average\n");
        int pos = 1;

        ArrayList<PlayerAverage> topTenPLayersList = getTopTen();
        topTenPLayersList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (PlayerAverage p : topTenPLayersList) {
            io.output(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }

    }
}
