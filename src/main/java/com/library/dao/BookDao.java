package com.library.dao;

import com.library.entities.Book;

import java.sql.Connection;
import java.util.ArrayList;

public interface BookDAO {
    ArrayList<Book> getAllBooks(Connection connection) throws Exception;

    ArrayList<Book> getBooksByName(Connection connection, String name) throws Exception;

    int addBook(Connection connection, Book book) throws Exception;

    int updateBook(Connection connection, Book book) throws Exception;

    int deleteBook(Connection connection, Book book) throws Exception;
}