//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23;

import se.iths.java23.database.DatabaseController;
import se.iths.java23.io.IO;
import se.iths.java23.io.SystemIO;
import se.iths.java23.io.WindowIO;
import se.iths.java23.logic.BullsAndCows;
import se.iths.java23.logic.GameController;
import se.iths.java23.logic.Playable;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

        IO io = new WindowIO();
        //IO io = new SystemIO();
        Playable game = new BullsAndCows();
        DatabaseController dbController = new DatabaseController();
        GameController controller = new GameController(game, io, dbController);
        controller.run();
    }
}