package com.library.test;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static com.library.main.MySQLConnector.closeConnection;
import static com.library.main.MySQLConnector.getConnection;


public class MySQLConnectorTest {
    public MySQLConnectorTest() throws Exception {
    }

    Connection connection = getConnection();

    @Test
    public void getConnectionTest() throws Exception {
        Assert.assertNotNull(connection);
    }
    @Test
    public void closeConnectionTest() throws Exception {
        Assert.assertNotNull(closeConnection(connection));
    }
}
