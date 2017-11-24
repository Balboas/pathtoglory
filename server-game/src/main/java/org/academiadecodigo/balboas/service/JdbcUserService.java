package org.academiadecodigo.balboas.service;


import org.academiadecodigo.balboas.game.Player;
import org.academiadecodigo.balboas.persistance.ConnectionManager;
import org.academiadecodigo.balboas.utils.Security;

import java.sql.*;

/**
 * Created by Daniel Baeta on 15/11/17.
 */
public class JdbcUserService  {

    private Connection dbConnection = null;

    // get the results

    public boolean authenticate(String username, String password) {

        System.out.println("Trying to authenticate a user");

        dbConnection = new ConnectionManager().getConnection();
        PreparedStatement statement = null;

        try {

            // create a query
            String query = "SELECT name, password FROM userData WHERE name =? AND password =?";

            // create a new statement
            statement = dbConnection.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, Security.getHash(password));

            // execute the query
            ResultSet answer = statement.executeQuery();

            return answer.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public void receiveLife(Player player, String username) {

        dbConnection = new ConnectionManager().getConnection();
        PreparedStatement statement = null;

        try {

            // create a query
            String query = "SELECT life, strength FROM userData WHERE name =?";

            // create a new statement
            statement = dbConnection.prepareStatement(query);
            statement.setString(1, username);

            // execute the query
            ResultSet anwser = statement.executeQuery();
            player.setHealth(anwser.getInt("life"));
            player.setStrength(anwser.getInt("strength"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
