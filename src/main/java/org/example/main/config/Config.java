package org.example.main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: 27.06.2023
//  Если захочешь используй мою конфигурацию, либо напиши свой!!!
public class Config {
    static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String name = "postgres";
    static final String password = "sami";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
