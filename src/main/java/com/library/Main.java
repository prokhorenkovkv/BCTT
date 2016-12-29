package com.library;

import com.library.constants.Commands;
import com.library.constants.Tips;
import com.library.services.MySQLConnector;
import com.library.services.QueryParser;

import java.io.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        MySQLConnector mySQLConnector = MySQLConnector.getInstance();

        String query = Tips.TIPS.toString();
        System.out.println(query);

        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                Connection connection = mySQLConnector.getConnection();
                query = bufferedReader.readLine().trim();
                if (query.equals(Commands.HELP.toString())) {
                    System.out.println(Tips.TIPS);
                }
                if (!query.equals(Commands.EXIT.toString())) {
                    QueryParser queryParser = new QueryParser();
                    queryParser.parseQuery(query, connection, bufferedReader);
                    mySQLConnector.closeConnection(connection);
                    bufferedReader.close();
                } else {
                    mySQLConnector.closeConnection(connection);
                    bufferedReader.close();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}