package com.library;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnector {
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        Connection connection = null;
        InputStream inputStream = new FileInputStream("mysql.properties");
        properties.load(inputStream);
        Class.forName(properties.getProperty("mysql.driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("mysql.url") + properties.getProperty("mysql.db"),
                properties.getProperty("mysql.username"),
                properties.getProperty("mysql.password"));
        return connection;
    }

    static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
