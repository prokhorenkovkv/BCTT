package com.library.services;

import com.library.constants.Commands;
import com.library.dao.BookDAO;
import com.library.dao.impl.BookDAOImpl;
import com.library.entities.Book;

import java.sql.Connection;

public class QueryParser {

    private BookDAO bookDAO = new BookDAOImpl();
    private ConsoleDialog consoleDialog = new ConsoleDialog();

    public void parseQuery(String query, Connection connection) throws Exception {

        String command = parseCommand(query);
        String name = parseName(query);
        String author = parseAuthor(query);

        if (command.toLowerCase().equals(Commands.ALL.toString())) {
            consoleDialog.writeAll(bookDAO.getAllBooks(connection));
            return;
        }
        if (command.toLowerCase().equals(Commands.ADD.toString())) {
            if (consoleDialog.validateNewBook(new Book(name, author))) {
                Book book = new Book(name, author);
                if (bookDAO.addBook(connection, book) > 0) {
                    consoleDialog.writeAddition(book);
                } else {
                    consoleDialog.writeFail();
                }
            }
        }
        if (command.toLowerCase().equals(Commands.UPDATE.toString())) {
            Book book = consoleDialog.getElaboration(bookDAO.getBooksByName(connection, name));
            book.setName(consoleDialog.getNewName());
            if (bookDAO.updateBook(connection, book) > 0) {
                consoleDialog.writeUpdate(book);
            } else {
                consoleDialog.writeFail();
            }
        }
        if (command.toLowerCase().equals(Commands.REMOVE.toString())) {
            Book book = consoleDialog.getElaboration(bookDAO.getBooksByName(connection, name));
            if (bookDAO.deleteBook(connection, book) > 0) {
                consoleDialog.writeDeletion(book);
            } else {
                consoleDialog.writeFail();
            }
        }
        if (!query.equals("")) {
            consoleDialog.writeNoCommand();
        }
    }

    private String parseCommand(String query) {
        return query.split(" ")[0];
    }

    private String parseName(String query) {
        int start = query.indexOf("\"");
        int end = query.lastIndexOf("\"");
        //return start != -1 && end != -1 ? query.substring(start + 1, end).trim() : "";
        throw new IndexOutOfBoundsException();
    }

    private String parseAuthor(String query) {
        int end = query.indexOf("\"");
        return end != -1 ? query.substring(Commands.ADD.toString().length(), end).trim() : "";
    }
}