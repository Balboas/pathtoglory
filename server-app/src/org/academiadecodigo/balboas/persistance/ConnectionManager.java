package org.academiadecodigo.balboas.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 15/11/17.
 */
public class ConnectionManager {

    private Connection connection = null;
    private String dbUrl = "jdbc:mysql://localhost:3306/pathtoglory";

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, "root", "");
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}

