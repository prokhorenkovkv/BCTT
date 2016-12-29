package com.library.dao;

import com.library.entities.Book;

import java.sql.Connection;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks(Connection connection) throws Exception;

    List<Book> getBooksByName(Connection connection, String name) throws Exception;

    Book getBook(Connection connection, int id) throws Exception;

    void updateBook(Connection connection, String name) throws Exception;

    void deleteBook(Connection connection, String name) throws Exception;

    void addBook(Connection connection, String name, String author) throws Exception;
}
