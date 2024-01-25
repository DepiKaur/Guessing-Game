//Depinder Kaur, 2024-01-24, depinder.kaur@iths.se

package se.iths.java23.database;

import se.iths.java23.logic.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseController implements Database {
    private Connection connection;
    private Statement statement;

    public DatabaseController() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","DepiAdmin","Depi1234");
    }

    public int getPlayerIdByName(String name) throws SQLException {
        ResultSet resultSet = getAllByPlayerName(name);
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }

    private ResultSet getAllByPlayerName(String name) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery("select id,name from players where name = '" + name + "'");
    }

    public void setResultForAnPlayer(int numOfGuesses, int playerId) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO results (result, playerid) VALUES (" + numOfGuesses + ", " +playerId + ")" );
    }

    public ResultSet getAllPlayers() throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery("select * from players");
    }

    public ResultSet getResultByPlayerId(int playerId) throws SQLException {
        statement = connection.createStatement();
        return statement.executeQuery("select * from results where playerid = "+ playerId );
    }

    public ArrayList<Player> getTopTen() throws SQLException {
        ArrayList<Player> topTenPlayersList = new ArrayList<>();
        ResultSet allPlayersRS = getAllPlayers();
        ResultSet resultsByPlayerIdRS;
        while(allPlayersRS.next()) {
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
                topTenPlayersList.add(new Player(name, (double)totalGuesses/nGames));
            }
        }
        return topTenPlayersList;
    }
}
