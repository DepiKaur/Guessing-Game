package se.iths.java23.dao;

import se.iths.java23.io.IO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultDAOImpl implements ResultDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private IO io;
    public ResultDAOImpl() throws SQLException, InterruptedException {

        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://localhost/moo","DepiAdmin","Depi1234");
        statement = connection.createStatement();

    }
}
