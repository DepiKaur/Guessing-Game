package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Depinder Kaur
 * @date 2024-01-25
 * @version 1.0
 * <p>
 * <h2>PlayerDao</h2>
 * PlayerDao is the interface which provides some methods to get a player's info
 * using player's id, name and even the number of guesses it takes to guess the
 * secret number/word.
 */

public interface PlayerDao {

    int getPlayerIdByName(String name);
    void setResultForAPlayer(int numOfGuesses, int playerId);
    ResultSet getAllPlayers();
    ResultSet getResultByPlayerId(int playerId);
    ArrayList<Player> getAllPlayersAverage();
}
