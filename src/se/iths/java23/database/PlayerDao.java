package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface PlayerDao {

    int getPlayerIdByName(String name);
    void setResultForAPlayer(int numOfGuesses, int playerId);
    ResultSet getAllPlayers();
    ResultSet getResultByPlayerId(int playerId);
    ArrayList<Player> getAllPlayersAverage();
}
