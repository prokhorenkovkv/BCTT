package com.library.dao.impl;

import com.library.dao.BookDAO;
import com.library.entities.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class BookDAOImpl implements BookDAO {
    @Override
    public ArrayList<Book> getAllBooks(Connection connection) throws Exception {
        String sql = "SELECT id, author, name FROM books";
        return executeSelect(connection, sql);
    }

    @Override
    public ArrayList<Book> getBooksByName(Connection connection, String name) throws Exception {
        String sql = "SELECT id, author, name FROM books WHERE name = " + "'" + name + "'";
        return executeSelect(connection, sql);
    }

    @Override
    public int addBook(Connection connection, Book book) throws Exception {
        String sql = "INSERT INTO books (name, author) VALUES ('" + book.getName() + "','" + book.getAuthor() + "')";
        return executeUpdate(connection, sql);
    }

    @Override
    public int updateBook(Connection connection, Book book) throws Exception {
        String sql = "UPDATE books SET name = '" + book.getName() + "' WHERE id = " + book.getId();
        return executeUpdate(connection, sql);
    }

    @Override
    public int deleteBook(Connection connection, Book book) throws Exception {
        String sql = "DELETE FROM books WHERE id = " + book.getId();
        return executeUpdate(connection, sql);
    }

    private ArrayList<Book> executeSelect(Connection connection, String sql) throws Exception {
        ArrayList<Book> books = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2)));
        }
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        return books;
    }

    private int executeUpdate(Connection connection, String sql) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
}