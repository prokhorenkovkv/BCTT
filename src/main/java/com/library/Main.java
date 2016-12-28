package com.library;

import java.io.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        MySQLConnector mySQLConnector = new MySQLConnector();

        /*Connection connection = null;
        try {
            connection = MySQLConnector.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String query = Tips.TIPS;
        System.out.println(query);

        while (true) {
            try {
                query = bufferedReader.readLine().trim();
                if (query.equals(Operations.HELP)) {
                    System.out.println(Tips.TIPS);
                }
                if (!query.equals(Operations.EXIT)) {
                    QueryParser.parseQuery(query, connection, bufferedReader);
                } else {
                    bufferedReader.close();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MySQLConnector.closeConnection(connection);*/
    }
}
