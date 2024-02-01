package se.iths.java23.logic;

import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;

import java.util.ArrayList;

/**
 * @author Depinder Kaur
 * @date 2024-01-24
 * @version 1.0
 * <p>
 * <h2>GameController</h2>
 * <p>
 * This controls the flow of the game and even shows the top ten players when the game is finished.
 */

public class GameController {

    private GuessingGame game;
    private IO io;
    private PlayerDao playerDao;

    private boolean isPlaying = true;

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public GameController(GuessingGame game, IO io, PlayerDao playerDao) {
        this.game = game;
        this.io = io;
        this.playerDao = playerDao;
    }

    /**
     * This method starts the game with a player who is already present in the database.
     * A secret number/word gets generated which the player needs to guess.
     * Note that there is no restriction on the number of guesses.
     * When guessed correctly, the number of guesses is shown & the player is asked if he wants to continue or not.
     * @throws InterruptedException
     */
    public void play(IO io, PlayerDao playerDao) throws InterruptedException {
        int playerId = getIdOfValidPlayer(io, playerDao);

        while (isPlaying) {
            String secretNumber = game.generateNumberOrWord();
            String guess, result;
            io.clear();
            io.output("New game:\n");

            //comment out or remove next line to play real games!
            io.output("For practice, goal is: " + secretNumber + "\n");

            boolean isGuessed = false;

            while(!isGuessed){
                guess = io.input();
                io.output(guess + ": ");
                result = game.showResult(game.checkResult(secretNumber, guess));
                io.output(result + "\n");
                if (game.isFinished(result)) {
                    isGuessed = true;
                }
            }

            playerDao.setResultForAPlayer(game.getNumOfGuesses(), playerId);
            showTopTen();
            isPlaying = io.yesNo("Correct, it took " + game.getNumOfGuesses()
                    + " guesses\nContinue?");
        }
        io.exit();
    }

    /**
     * This method returns the id of the player already present in the database.
     * If the playerId is zero, the application ends.
     * @param io To get input from player.
     * @param playerDao To get info from the database.
     * @return The id of the player in the database.
     * @throws InterruptedException when player is not found in the database and the sleep method on thread is called.
     */
    private int getIdOfValidPlayer(IO io, PlayerDao playerDao) throws InterruptedException {
        io.output("Enter your user name:\n");
        String playerName = io.input();
        int playerId = playerDao.getPlayerIdByName(playerName);

        if (playerId == 0) {
            io.output("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }
        return playerId;
    }

    /**
     * Sorts the list of players according to player average in the ascending order and then shows the first 10 players.
     */
    private void showTopTen() {
        io.output("Top Ten List\n    Player     Average\n");
        int position = 1;

        ArrayList<Player> allPlayers = playerDao.getAllPlayersAverage();
        allPlayers.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : allPlayers) {
            io.output(String.format("%3d %-10s%5.2f%n", position, p.getName(), p.getAverage()));
            if (position++ == 10) break;
        }
    }
}
