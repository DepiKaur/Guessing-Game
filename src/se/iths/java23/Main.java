package se.iths.java23;

import se.iths.java23.database.DatabasePlayerDao;
import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;
import se.iths.java23.io.SystemIO;
import se.iths.java23.io.WindowIO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.GuessTheWord;
import se.iths.java23.logic.GuessingGame;

/**
 * @author Depinder Kaur
 * @date 2024-01-23
 * @version 1.0
 * <p>
 * <h2>Main</h2>
 * <p>
 * Main class contains the main method where the program starts.
 */

public class Main {

    /**
     * This method is the application's entry point.
     * The desired guessing game can be chosen here, similarly like the desired input-output method.
     * @param args Supplies command-line arguments as an array of String objects.
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        GuessingGame game = new BullsAndCows();
        //GuessingGame game = new GuessTheWord();

        IO io = new WindowIO(game);
        //IO io = new SystemIO();

        PlayerDao playerDao = new DatabasePlayerDao();
        GameController controller = new GameController(game, io, playerDao);
        controller.play();
    }
}