package com.library.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Manage database connection states
 */
public class MySQLConnector {

    private static MySQLConnector mySQLConnector = null;
    private Connection connection;

    /**
     * @return instance of MySQLConnector
     */
    public static MySQLConnector getInstance() {
        if (mySQLConnector == null) {
            return new MySQLConnector();
        } else {
            return mySQLConnector;
        }
    }

    private MySQLConnector() {

    }

    /**
     * Establish database connection
     * @return database connection
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
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

    /**
     * Close database connection
     * @return database connection
     * @throws SQLException
     */
    public Connection closeConnection() throws SQLException {
        connection.close();
        return connection;
    }
}