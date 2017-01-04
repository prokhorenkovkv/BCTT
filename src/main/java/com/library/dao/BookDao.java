package com.library.dao;

import com.library.entities.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {
    /**
     * Return list of all books
     * @param connection database connection
     * @return ArrayList of all books
     * @throws SQLException
     */
    ArrayList<Book> getAllBooks(Connection connection) throws SQLException;

    /**
     * Return list of books with specified name
     * @param connection database connection
     * @param name specified name
     * @return ArrayList of books with specified name
     * @throws SQLException
     */
    ArrayList<Book> getBooksByName(Connection connection, String name) throws SQLException;

    /**
     * Add book to database
     * @param connection database connection
     * @param book book that is to be saved
     * @return number of rows that are affected by addition
     * @throws SQLException
     */
    int addBook(Connection connection, Book book) throws SQLException;

    /**
     * Update book in database
     * @param connection database connection
     * @param book book that is to be updated
     * @return number of rows that are affected by updating
     * @throws SQLException
     */
    int updateBook(Connection connection, Book book) throws SQLException;

    /**
     * Delete book from database
     * @param connection database connection
     * @param book book that is to be deleted
     * @return number of rows that are affected by deletion
     * @throws SQLException
     */
    int deleteBook(Connection connection, Book book) throws SQLException;
}