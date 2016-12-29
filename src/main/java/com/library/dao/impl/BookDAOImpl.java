package com.library.dao.impl;

import com.library.dao.BookDAO;
import com.library.entities.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> getAllBooks(Connection connection) throws Exception {
        String sql = "SELECT id, author, name FROM books";
        /*ArrayList<Book> books = getRecords(sql, connection);
        if (books.size() > 0) {
            System.out.println(Tips.LIST);
            books.forEach(book -> System.out.println(book.toString()));
        } else {
            System.out.println(Tips.NO_RESULT);
        }*/
        return getRecords(sql, connection);
    }

    @Override
    public List<Book> getBooksByName(Connection connection, String name) throws Exception {
        String sql = "SELECT id, author, name FROM books WHERE name = " + "'" + name + "'";
        return getRecords(sql, connection);
    }

    @Override
    public Book getBook(Connection connection, int id) throws Exception {
        return null;
    }

    @Override
    public void updateBook(Connection connection, String name) throws Exception {
        String sql = "SELECT id, author, name FROM books WHERE name = " + "'" + name + "'";
        ArrayList<Book> books = getRecords(sql, connection);
        /*int size = books.size();
        int id = 0;
        if (size == 0) {
            System.out.println(Tips.NO_RESULT);
        }
        if (size == 1) {
            id = books.get(0).getId();
            System.out.println(Tips.NEW_NAME);
            name = bufferedReader.readLine().trim();
            sql = "UPDATE books SET name = '" + name + "' WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Book " + books.get(0).getAuthor() + " " + name + " was updated");
        }
        if (size != 0 && size != 1) {
            System.out.println(Tips.CHOISE);
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + 1 + ". " + books.get(i).toString());
            }
            int index = 0;
            String choise = bufferedReader.readLine().trim();
            if (!choise.equals("") && !choise.equals(Commands.CANCEL)) {
                index = Integer.parseInt(choise);
                System.out.println(Tips.NEW_NAME);
                name = bufferedReader.readLine().trim();
                if (index >= 1 && index < books.size() + 1) {
                    sql = "UPDATE books SET name = '" + name + "' WHERE id = " + books.get(index - 1).getId();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Book " + books.get(index - 1).getAuthor() + " " + name + " was updated");
                } else {
                    System.out.println(Tips.INCORRECT);
                }
            }
        }*/
    }

    @Override
    public void deleteBook(Connection connection, String name) throws Exception {
        String sql = "SELECT id, author, name FROM books WHERE name = '" + name + "'";
        ArrayList<Book> books = getRecords(sql, connection);
        /*int size = books.size();
        int id = 0;
        if (size == 0) {
            System.out.println(Tips.NO_RESULT);
        }
        if (size == 1) {
            id = books.get(0).getId();
            sql = "DELETE FROM books WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Book " + books.get(0).getAuthor() + " " + name + " was removed");
        }
        if (size != 0 && size != 1) {
            System.out.println(Tips.CHOISE);
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + 1 + ". " + books.get(i).toString());
            }
            int index = 0;
            String choise = bufferedReader.readLine().trim();
            if (!choise.equals("") && !choise.equals(Commands.CANCEL)) {
                index = Integer.parseInt(choise);
                if (index >= 1 && index < books.size() + 1) {
                    sql = "DELETE FROM books WHERE id = " + books.get(index - 1).getId();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    System.out.println("Book " + books.get(index - 1).getAuthor() + " " + name + " was removed");
                } else {
                    System.out.println(Tips.INCORRECT);
                }
            }
        }*/
    }

    @Override
    public void addBook(Connection connection, String name, String author) throws Exception {
        String sql = "INSERT INTO books (name, author) VALUES ('" + name + "','" + author + "')";
        /*if (name.equals("") || author.equals("")) {
            System.out.println(Tips.REQUIRED);
        } else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Book " + author + " " + name + " was added");
        }*/
    }

/*
    @Override
    public void updateBook(Book book) {

    }

    */

    private ArrayList<Book> getRecords(String sql, Connection connection) throws Exception {
        ArrayList<Book> books = new ArrayList<Book>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2)));
        }
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        return books;
    }
}
