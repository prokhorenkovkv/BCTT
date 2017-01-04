package com.library.services;

import com.library.constants.Commands;
import com.library.dao.BookDAO;
import com.library.dao.impl.BookDAOImpl;
import com.library.entities.Book;

import java.sql.Connection;

/**
 * Parse queries
 */
public class QueryParser {

    private static QueryParser queryParser = null;
    private BookDAO bookDAO = new BookDAOImpl();
    private ConsoleDialog consoleDialog = ConsoleDialog.getInstance();

    /**
     * @return instance of QueryParser
     */
    public static QueryParser getInstance() {
        if (queryParser == null) {
            return new QueryParser();
        } else {
            return queryParser;
        }
    }

    private QueryParser() {

    }

    /**
     * Parse queries. Call specified methods
     * Invoke {@link #parseCommand(String)}, {@link #parseAuthor(String)}, {@link #parseName(String)}
     *
     * @param query      user's query
     * @param connection database connection
     * @throws Exception
     */
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
            return;
        }
        if (command.toLowerCase().equals(Commands.UPDATE.toString())) {
            Book book = consoleDialog.getSpecification(bookDAO.getBooksByName(connection, name));
            if (book != null) {
                book.setName(consoleDialog.getNewName());
                if (bookDAO.updateBook(connection, book) > 0) {
                    consoleDialog.writeUpdate(book);
                }
            } else {
                consoleDialog.writeFail();
            }
            return;
        }
        if (command.toLowerCase().equals(Commands.REMOVE.toString())) {
            Book book = consoleDialog.getSpecification(bookDAO.getBooksByName(connection, name));
            if (book != null) {
                if (bookDAO.deleteBook(connection, book) > 0) {
                    consoleDialog.writeDeletion(book);
                }
            } else {
                consoleDialog.writeFail();
            }
            return;
        }
        if (!query.equals("")) {
            consoleDialog.writeNoCommand();
        }
    }

    /**
     * Parse command value from user's query
     *
     * @param query user's query
     * @return parsed command value
     */
    private String parseCommand(String query) {
        return query.split(" ")[0];
    }

    /**
     * Parse name value from user's query
     *
     * @param query user's query
     * @return parsed name value
     */
    private String parseName(String query) {
        int start = query.indexOf("\"");
        int end = query.lastIndexOf("\"");
        return start != -1 && end != -1 ? query.substring(start + 1, end).trim() : "";
    }

    /**
     * Parse author value from user's query
     *
     * @param query user's query
     * @return parsed author value
     */
    private String parseAuthor(String query) {
        int end = query.indexOf("\"");
        return end != -1 ? query.substring(Commands.ADD.toString().length(), end).trim() : "";
    }
}