package com.java.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Connection connection = openConnection();


    /**
     * @return
     */
    private static Connection openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/crud", "postgres", "admin");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

        return connection;

    }

    public static Connection getConnection() {
        return connection;
    }
}



