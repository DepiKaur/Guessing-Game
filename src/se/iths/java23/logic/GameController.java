//Depinder Kaur, 2024-01-24, depinder.kaur@iths.se

package se.iths.java23.logic;

import se.iths.java23.database.DAO;
import se.iths.java23.io.IO;

import java.util.ArrayList;

public class GameController {

    private Game game;
    private IO io;
    private DAO daoController;

    private boolean isPlaying = true;

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public GameController(Game game, IO io, DAO daoController) {
        this.game = game;
        this.io = io;
        this.daoController = daoController;
    }

    public void run() throws InterruptedException {
        io.output("Enter your user name:\n");
        String playerName = io.input();
        int playerId = daoController.getPlayerIdByName(playerName);                 //login

        if (playerId == 0) {
            io.output("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }

        while (isPlaying) {
            String goal = game.generateGoal();
            String guess, result;
            io.clear();
            io.output("New game:\n");
            //comment out or remove next line to play real games!
            io.output("For practice, goal is: " + goal + "\n");

            do {
                guess = io.input();
                io.output(guess +": ");
                result = game.getResult(goal, guess);
                io.output(result + "\n");
            } while (!game.isFinished(result));

            daoController.setResultForAnPlayer(game.getNumOfGuesses(), playerId);
            showTopTen();
            isPlaying = io.yesNo("Correct, it took " + game.getNumOfGuesses()
                    + " guesses\nContinue?");
        }
        io.exit();
    }

    private void showTopTen() {
        io.output("Top Ten List\n    Player     Average\n");
        int pos = 1;

        ArrayList<Player> topTenPLayersList = daoController.getTopTen();
        topTenPLayersList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : topTenPLayersList) {
            io.output(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }
    }
}
