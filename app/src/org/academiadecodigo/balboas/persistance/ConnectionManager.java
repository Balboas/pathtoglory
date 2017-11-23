package org.academiadecodigo.balboas.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 15/11/17.
 */
public class ConnectionManager {
    private String url;
    private String user;
    private  String pass;

    public ConnectionManager(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }



    Connection connection = null;

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url,user,pass);
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

