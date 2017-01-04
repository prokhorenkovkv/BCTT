package com.library.dao.impl;

import com.library.dao.BookDAO;
import com.library.entities.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class BookDAOImpl implements BookDAO {

    /**
     * Return list of all books
     * Invoke {@link #executeSelect(Connection, String)}
     * @param connection database connection
     * @return ArrayList of all books
     * @throws SQLException
     */
    @Override
    public ArrayList<Book> getAllBooks(Connection connection) throws SQLException {
        String sql = "SELECT id, author, name FROM books";
        return executeSelect(connection, sql);
    }

    /**
     * Return list of books with specified name
     * Invoke {@link #executeSelect(Connection, String)}
     * @param connection database connection
     * @param name specified name
     * @return ArrayList of books with specified name
     * @throws SQLException
     */
    @Override
    public ArrayList<Book> getBooksByName(Connection connection, String name) throws SQLException {
        String sql = "SELECT id, author, name FROM books WHERE name = " + "'" + name + "'";
        return executeSelect(connection, sql);
    }

    /**
     * Add book to database
     * Invoke {@link #executeUpdate(Connection, String)}
     * @param connection database connection
     * @param book book that is to be saved
     * @return number of rows that are affected by addition
     * @throws SQLException
     */
    @Override
    public int addBook(Connection connection, Book book) throws SQLException {
        String sql = "INSERT INTO books (name, author) VALUES ('" + book.getName() + "','" + book.getAuthor() + "')";
        return executeUpdate(connection, sql);
    }

    /**
     * Update book in database
     * Invoke {@link #executeUpdate(Connection, String)}
     * @param connection database connection
     * @param book book that is to be updated
     * @return number of rows that are affected by updating
     * @throws SQLException
     */
    @Override
    public int updateBook(Connection connection, Book book) throws SQLException {
        String sql = "UPDATE books SET name = '" + book.getName() + "' WHERE id = " + book.getId();
        return executeUpdate(connection, sql);
    }

    /**
     * Delete book from database
     * Invoke {@link #executeUpdate(Connection, String)}
     * @param connection database connection
     * @param book book that is to be deleted
     * @return number of rows that are affected by deletion
     * @throws SQLException
     */
    @Override
    public int deleteBook(Connection connection, Book book) throws SQLException {
        String sql = "DELETE FROM books WHERE id = " + book.getId();
        return executeUpdate(connection, sql);
    }

    /**
     * Execute select queries. Sort selection result by book name
     * @param connection database connection
     * @param sql SQL query
     * @return ArrayList as a result of selection
     * @throws SQLException
     */
    private ArrayList<Book> executeSelect(Connection connection, String sql) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2)));
        }
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        return books;
    }

    /**
     * Execute update queries
     * @param connection database connection
     * @param sql SQL query
     * @return number of rows that are affected by database updating
     * @throws SQLException
     */
    private int executeUpdate(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
}