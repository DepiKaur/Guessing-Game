//Depinder Kaur, 2024-01-24, depinder.kaur@iths.se

package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOController implements DAO {
    private Connection connection;
    private Statement statement;

    public DAOController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","DepiAdmin","Depi1234");
        } catch (SQLException e) {
            throw new RuntimeException("DAOController Constructor error: " + e);
        }
    }

    public int getPlayerIdByName(String name) {
        try {
            ResultSet resultSet = getAllByPlayerName(name);
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("DAOController getPlayerIdByName error: " + e);
        }
    }

    private ResultSet getAllByPlayerName(String name) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select id,name from players where name = '" + name + "'");
        } catch (SQLException e) {
            throw new RuntimeException("DAOController getAllByPlayerName error: " + e);
        }
    }

    public void setResultForAnPlayer(int numOfGuesses, int playerId) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO results (result, playerid) VALUES (" + numOfGuesses + ", " +playerId + ")" );
        } catch (SQLException e) {
            throw new RuntimeException("DAOController setResultForAnPlayer error: " + e);
        }
    }

    public ResultSet getAllPlayers() {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select * from players");
        } catch (SQLException e) {
            throw new RuntimeException("DAOController getAllPlayers error: " + e);
        }
    }

    public ResultSet getResultByPlayerId(int playerId) {
        try {
            statement = connection.createStatement();
            return statement.executeQuery("select * from results where playerid = " + playerId);
        } catch (SQLException e) {
            throw new RuntimeException("DAOController getResultByPlayerId error: " + e);
        }
    }

    public ArrayList<Player> getTopTen() {
        ArrayList<Player> topTenPlayersList = new ArrayList<>();
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
                    topTenPlayersList.add(new Player(name, (double) totalGuesses / nGames));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DAOController getTopTen error: " + e);
        }
        return topTenPlayersList;
    }
}
