package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Depinder Kaur
 * @date 2024-01-24
 * @version 1.0
 * <p>
 * <h2>DatabasePlayerDao</h2>
 * <p>
 * DatabasePlayerDao implements all the methods in the <i>PlayerDao</i> interface.
 * It makes the connection to the database everytime the constructor is called.
 */

public class DatabasePlayerDao implements PlayerDao {
    private Connection connection;
    private Statement statement;

    //write your username and password to connect with your database
    public DatabasePlayerDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/schemaName","username","password");
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao Constructor error: " + e);
        }
    }

    /**
     * This method returns player's id if the player exists in database, otherwise returns 0.
     * @param name This is the player's name who wants to play the guessing game.
     * @return Player's id if player exists in database, otherwise zero.
     */
    public int getPlayerIdByName(String name) {
        try {
            ResultSet resultSet = getAllByPlayerName(name);
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao getPlayerIdByName error: " + e);
        }
    }

    /**
     * This method takes name of the player and returns his/her info from the players table in the database.
     * @param name This is the name of the player whose info is desired from the database.
     * @return A player's info from the players table in the database.
     */
    private ResultSet getAllByPlayerName(String name) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select id,name from players where name = '" + name + "'");
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao getAllByPlayerName error: " + e);
        }
    }

    /**
     * This method saves the number of guesses and the player's id in the results table in the database.
     * @param numOfGuesses The number of guesses it takes to guess the secret number/word by the player.
     * @param playerId The id of the player who's playing the guessing game.
     */
    public void setResultForAPlayer(int numOfGuesses, int playerId) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO results (result, playerid) VALUES (" + numOfGuesses + ", " +playerId + ")" );
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao setResultForAnPlayer error: " + e);
        }
    }

    /**
     * @return All info from the players table.
     */
    public ResultSet getAllPlayers() {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select * from players");
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao getAllPlayers error: " + e);
        }
    }

    /**
     * This method returns all info from the results table (in the database) corresponding to the given player's id.
     * @param playerId This is the id of the player whose info is desired.
     * @return Result set of a specific player from the database.
     */
    public ResultSet getResultByPlayerId(int playerId) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select * from results where playerid = " + playerId);
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao getResultByPlayerId error: " + e);
        }
    }

    /**
     * This method calculates average for each player from the database.
     * @return A list which contains the name and average of all players in the database.
     */
    public ArrayList<Player> getAllPlayersAverage() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        try {
            ResultSet allPlayersRS = getAllPlayers();
            ResultSet resultsByPlayerIdRS;
            while (allPlayersRS.next()) {
                int id = allPlayersRS.getInt("id");
                String name = allPlayersRS.getString("name");
                resultsByPlayerIdRS = getResultByPlayerId(id);
                int nGames = 0;
                int totalGuesses = 0;
                while (resultsByPlayerIdRS.next()) {
                    nGames++;
                    totalGuesses += resultsByPlayerIdRS.getInt("result");
                }
                if (nGames > 0) {
                    allPlayers.add(new Player(name, (double) totalGuesses / nGames));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DatabasePlayerDao getAllPlayersAverage error: " + e);
        }
        return allPlayers;
    }
}
