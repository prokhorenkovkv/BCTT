package com.library.services;

import com.library.constants.Commands;
import com.library.constants.Tips;
import com.library.dao.BookDAO;
import com.library.dao.impl.BookDAOImpl;

import java.io.BufferedReader;
import java.sql.Connection;

public class QueryParser {
    private BookDAO bookDAO = new BookDAOImpl();

    public void parseQuery(String query, Connection connection, BufferedReader bufferedReader) throws Exception {
        String command = parseCommand(query);
        String name = parseName(query);
        String author = parseAuthor(query);

        if (command.toLowerCase().equals(Commands.ALL.toString())) {
            bookDAO.getAllBooks(connection);
            return;
        }
        if (command.toLowerCase().equals(Commands.ADD.toString())) {
            bookDAO.addBook(connection, author, name);
            return;
        }
        if (command.toLowerCase().equals(Commands.UPDATE.toString())) {
            bookDAO.updateBook(connection, name);
            return;
        }
        if (command.toLowerCase().equals(Commands.REMOVE.toString())) {
            bookDAO.deleteBook(connection, name);
            return;
        }
        if (!query.equals("")) {
            System.out.println(Tips.NO_COMMAND);
        }
    }

    private String parseCommand(String query) {
        return query.split(" ")[0];
    }

    private String parseName(String query) {
        int start = query.indexOf("\"");
        int end = query.lastIndexOf("\"");
        return start != -1 && end != -1 ? query.substring(start + 1, end).trim() : "";
    }

    private String parseAuthor(String query) {
        int end = query.indexOf("\"");
        return end != -1 ? query.substring(Commands.ADD.toString().length(), end).trim() : "";
    }
}