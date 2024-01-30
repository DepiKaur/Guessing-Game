//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23;

import se.iths.java23.database.DatabasePlayerDao;
import se.iths.java23.database.PlayerDao;
import se.iths.java23.io.IO;
import se.iths.java23.io.WindowIO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.GuessingGame;

public class Main {

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