package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Database {

    int getPlayerIdByName(String name) throws SQLException;
    void setResultForAnPlayer(int numOfGuesses, int playerId) throws SQLException;
    ResultSet getAllPlayers() throws SQLException;
    ResultSet getResultByPlayerId(int playerId) throws SQLException;
    ArrayList<Player> getTopTen() throws SQLException;
}
