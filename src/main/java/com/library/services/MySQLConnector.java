package com.library.services;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnector {

    private static MySQLConnector mySQLConnector = null;
    private Connection connection;

    public static MySQLConnector getInstance() {
        if (mySQLConnector == null) {
            return new MySQLConnector();
        } else {
            return mySQLConnector;
        }
    }

    private MySQLConnector() {

    }

    public Connection getConnection() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mysql.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Class.forName(properties.getProperty("mysql.driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("mysql.url"),
                properties.getProperty("mysql.username"),
                properties.getProperty("mysql.password"));
        return connection;
    }

    public Connection closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}