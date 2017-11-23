package org.academiadecodigo.balboas.services;

import org.academiadecodigo.balboas.model.User;
import org.academiadecodigo.balboas.utils.Security;


import java.sql.*;

/**
 * Created by codecadet on 15/11/17.
 */
public class JdbcUserService implements UserService {

    private Connection dbConnection = null;

    public JdbcUserService(Connection connection) {
        this.dbConnection = connection;
    }

    // get the results
    @Override
    public long count() {
        int result = 0;

        // create a new statement
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();

            // create a query
            String query = "SELECT COUNT(*) FROM user";

            // execute the query
            ResultSet resultSet = null;

            resultSet = statement.executeQuery(query);

            // get the results

            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }


    @Override
    public boolean authenticate(String username, String password) {


        String query = "SELECT * FROM user WHERE user_name=? AND password=?";
        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement(query);
            System.out.println(query);

            // set values for the placeholders
            statement.setString(1, username);
            statement.setString(2, Security.getHash(password));

            // execute the query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




  @Override
    public void addUser(User user) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO user(user_name, password,email) VALUES('" + user.getUserName() + "','" + user.getPassWord() + "','" + user.getEmail() + "');";
        System.out.println(query);
        try {
            preparedStatement = dbConnection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean resultSet;
        try {
            resultSet = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet = false) {
            return;
        }
    }


    @Override
    public User findByName(String username) {
        User user = null;
        String query = "SELECT * FROM user WHERE user_name=?";

        PreparedStatement statement = null;
        try {
            statement = dbConnection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // set values for the placeholders
        try {
            statement.setString(1, username);


            // execute the query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String usernameValue = resultSet.getString("user_name");
                String passwordValue = resultSet.getString("password");
                String emailValue = resultSet.getString("email");

                user = new User(usernameValue, passwordValue, emailValue);
                System.out.println(user);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return user;
    }
}
