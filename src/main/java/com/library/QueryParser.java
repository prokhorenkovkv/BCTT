package com.library;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class QueryParser {
    public static void parseQuery(String query, Connection connection, BufferedReader bufferedReader) throws Exception {
        String operation = getOperation(query);

        if (operation.toLowerCase().equals(Operations.ALL)) {
            getAllBooks(connection);
            return;
        }
        if (operation.toLowerCase().equals(Operations.ADD)) {
            addBook(query, connection);
            return;
        }
        if (operation.toLowerCase().equals(Operations.UPDATE)) {
            updateBook(query, connection, bufferedReader);
            return;
        }
        if (operation.toLowerCase().equals(Operations.REMOVE)) {
            removeBook(query, connection, bufferedReader);
            return;
        }
        if (!query.equals("")) {
            System.out.println(Tips.NO_COMMAND);
        }
    }

    public static void getAllBooks(Connection connection) throws Exception {
        String sql = "SELECT id, author, name FROM books";
        ArrayList<Book> books = getRecords(sql, connection);
        if (books.size() > 0) {
            System.out.println(Tips.LIST);
            books.forEach(book -> System.out.println(book.toString()));
        } else {
            System.out.println(Tips.NO_RESULT);
        }

    }

    private static void addBook(String query, Connection connection) throws Exception {
        String name = parseName(query);
        String author = parseAuthor(query);
        String sql = "INSERT INTO books (name, author) VALUES ('" + name + "','" + author + "')";
        if (name.equals("") || author.equals("")) {
            System.out.println(Tips.REQUIRED);
        } else {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Book " + author + " " + name + " was added");
        }
    }

    private static void updateBook(String query, Connection connection, BufferedReader bufferedReader) throws Exception {
        String name = parseName(query);
        String sql = "SELECT id, author, name FROM books WHERE name = " + "'" + name + "'";
        ArrayList<Book> books = getRecords(sql, connection);
        int size = books.size();
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
            if (!choise.equals("") && !choise.equals(Operations.CANCEL)) {
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
        }
    }

    private static void removeBook(String query, Connection connection, BufferedReader bufferedReader) throws Exception {
        String name = parseName(query);
        String sql = "SELECT id, author, name FROM books WHERE name = '" + name + "'";
        ArrayList<Book> books = getRecords(sql, connection);
        int size = books.size();
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
            if (!choise.equals("") && !choise.equals(Operations.CANCEL)) {
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
        }
    }

    private static String getOperation(String query) {
        StringBuilder operation = new StringBuilder();
        char[] chars = query.toCharArray();
        for (char c : chars) {
            if (c != ' ') {
                operation.append(c);
            } else {
                break;
            }
        }
        return operation.toString();
    }

    private static String parseName(String query) {
        int start = query.indexOf("\"");
        int end = query.lastIndexOf("\"");
        return start != -1 && end != -1 ? query.substring(start + 1, end).trim() : "";
    }

    private static String parseAuthor(String query) {
        int end = query.indexOf("\"");
        return end != -1 ? query.substring(Operations.ADD.length(), end).trim() : "";
    }

    private static ArrayList<Book> getRecords(String sql, Connection connection) throws Exception {
        ArrayList<Book> books = new ArrayList<Book>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next())
            books.add(new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2)));
        Collections.sort(books, (book1, book2) -> book1.getName().compareTo(book2.getName()));
        return books;
    }
}