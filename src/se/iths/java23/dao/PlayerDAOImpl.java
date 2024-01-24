package se.iths.java23.dao;

import se.iths.java23.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private Connection connection;
    private PreparedStatement allPlayersPS, playerByNamePS;
    private ResultDAO resultDAO;

    public PlayerDAOImpl() throws InterruptedException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moo", "DepiAdmin", "Depi1234");
            allPlayersPS = connection.prepareStatement("select * from players");
            playerByNamePS = connection.prepareStatement("select id,name from players where name = ?");
        } catch (SQLException e) {
            throw new RuntimeException("PlayerDAO constructor error: " + e);
        }
    }


    public List<Player> getAll() {
        List<Player> allPlayers = new ArrayList<>();
        try {
            ResultSet rs = allPlayersPS.executeQuery();
            while (rs.next()) {
                allPlayers.add(new Player(
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("PlayerDAO getAll error: " + e);
        }

        return allPlayers;
    }

    public Player getPlayerByName(String name) {
        int playerId = 0;
        try {
            playerByNamePS.setString(1, name);
            ResultSet rs = playerByNamePS.executeQuery();
            while (rs.next()) {
                playerId = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Player(playerId, name);
    }
}
