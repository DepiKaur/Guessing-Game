//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23;

import se.iths.java23.database.DAO;
import se.iths.java23.database.DAOController;
import se.iths.java23.io.IO;
import se.iths.java23.io.WindowIO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.Game;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new BullsAndCows();
        //Game game = new Scrabble();

        IO io = new WindowIO(game);
        //IO io = new SystemIO();

        DAO dbController = new DAOController();
        GameController controller = new GameController(game, io, dbController);
        controller.run();
    }
}