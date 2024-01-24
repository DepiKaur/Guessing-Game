package se.iths.java23.database;

import se.iths.java23.io.IO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    IO io;

    public DatabaseController() {
    }

    public int login(String name) throws SQLException, InterruptedException {
        connection = makeConnection();
        resultSet = getAllByPlayerName(name);
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            io.output("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
            return 0;
        }
    }

    private Connection makeConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/moo","DepiAdmin","Depi1234");
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
}
