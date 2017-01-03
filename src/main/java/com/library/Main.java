package com.library;

import com.library.constants.Commands;
import com.library.services.ConsoleDialog;
import com.library.services.MySQLConnector;
import com.library.services.QueryParser;

import java.io.FileNotFoundException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        MySQLConnector mySQLConnector = MySQLConnector.getInstance();
        QueryParser queryParser = new QueryParser();
        ConsoleDialog consoleDialog = new ConsoleDialog();

        consoleDialog.writeTips();

        while (true) {
            try {
                String query = consoleDialog.getQuery();
                if (query.equals(Commands.HELP.toString())) {
                    consoleDialog.writeTips();
                } else {
                    if (!query.equals(Commands.EXIT.toString())) {
                        Connection connection = mySQLConnector.getConnection();
                        queryParser.parseQuery(query, connection);
                        mySQLConnector.closeConnection();
                    } else {
                        consoleDialog.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}