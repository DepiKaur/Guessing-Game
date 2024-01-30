//Depinder Kaur, 2024-01-24, depinder.kaur@iths.se

package se.iths.java23.logic;

import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;

import java.util.ArrayList;

public class GuessingGameEngine {

    private GuessingGame game;
    private IO io;
    private PlayerDao playerDao;

    private boolean isPlaying = true;

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public GuessingGameEngine(GuessingGame game, IO io, PlayerDao playerDao) {
        this.game = game;
        this.io = io;
        this.playerDao = playerDao;
    }

    public void run() throws InterruptedException {
        io.output("Enter your user name:\n");
        String playerName = io.input();
        int playerId = playerDao.getPlayerIdByName(playerName);              //login

        if (playerId == 0) {
            io.output("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }

        while (isPlaying) {
            String secretNumber = game.generateNumberOrWord();
            String guess, result;
            io.clear();
            io.output("New game:\n");
            //comment out or remove next line to play real games!
            io.output("For practice, goal is: " + secretNumber + "\n");

            boolean guessed = false;

            while(!guessed){
                guess = io.input();
                io.output(guess + ": ");
                result = game.showResult(game.checkResult(secretNumber, guess));
                io.output(result + "\n");
                if (game.isFinished(result)) {
                    guessed = true;
                }
            }

            playerDao.setResultForAnPlayer(game.getNumOfGuesses(), playerId);
            showTopTen();
            isPlaying = io.yesNo("Correct, it took " + game.getNumOfGuesses()
                    + " guesses\nContinue?");
        }
        io.exit();
    }

    private void showTopTen() {
        io.output("Top Ten List\n    Player     Average\n");
        int position = 1;

        ArrayList<Player> topTenPLayersList = playerDao.getTopTen();
        topTenPLayersList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : topTenPLayersList) {
            io.output(String.format("%3d %-10s%5.2f%n", position, p.getName(), p.getAverage()));
            if (position++ == 10) break;
        }
    }
}
