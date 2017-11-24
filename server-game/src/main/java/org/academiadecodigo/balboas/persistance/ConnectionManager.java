package org.academiadecodigo.balboas.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Daniel Baeta on 15/11/17.
 */
public class ConnectionManager {

    private static Connection connection;
    private static String databaseURL = "jdbc:mysql://192.168.1.16:3306/pathtoglory";

    public static Connection getConnection(){

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("not good");
        }

        if (connection == null){
            try {

                connection = DriverManager.getConnection(databaseURL, "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Can't make a connection");
            }
        }
        return connection;
    }

    public void close(){

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
