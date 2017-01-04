package com.library.services;

import com.library.constants.Commands;
import com.library.constants.Tips;
import com.library.entities.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Provide dialog
 */
public class ConsoleDialog {

    private static ConsoleDialog consoleDialog = null;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static ConsoleDialog getInstance() {
        if (consoleDialog == null) {
            return new ConsoleDialog();
        } else {
            return consoleDialog;
        }
    }

    private ConsoleDialog() {

    }

    /**
     * Write tips
     */
    public void writeTips() {
        System.out.println(Tips.TIPS);
    }

    /**
     * Read user's query
     * @return user's query
     * @throws IOException
     */
    public String getQuery() throws IOException {
        return bufferedReader.readLine().trim();
    }

    /**
     * Write "no command" tip
     */
    public void writeNoCommand() {
        System.out.println(Tips.NO_COMMAND);
    }

    /**
     * Write list of all books
     * @param books all books
     */
    public void writeAll(ArrayList<Book> books) {
        if (books.size() > 0) {
            System.out.println(Tips.LIST);
            books.forEach(book -> System.out.println(book.toString()));
        } else {
            System.out.println(Tips.NO_RESULT);
        }
    }

    /**
     * Write result of addition book to database
     * @param book book that is saved
     */
    public void writeAddition(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was added");
    }

    /**
     * Write result of updating book in database
     * @param book book that is updated
     */
    public void writeUpdate(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was updated");
    }

    /**
     * Write result of deletion book from database
     * @param book book that is deleted
     */
    public void writeDeletion(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was removed");
    }

    /**
     * Write "fail" tip
     */
    public void writeFail() {
        System.out.println(Tips.FAIL);
    }

    /**
     * Specify book from books list
     * Invoke {@link #validateSpecificaton(String)}
     * @param books list of books that is being specified
     * @return specified book
     * @throws IOException
     * @throws ParseException
     */
    public Book getSpecificaton(ArrayList<Book> books) throws IOException, ParseException{
        int size = books.size();
        if (size == 0) {
            System.out.println(Tips.NO_RESULT);
        }
        if (size == 1) {
            return books.get(0);
        }
        if (size != 0) {
            System.out.println(Tips.CHOICE);
            for (int i = 0; i < books.size(); i++) {
                System.out.println(i + 1 + ". " + books.get(i).toString());
            }
            int index;
            String choice = bufferedReader.readLine().trim();
            if (validateSpecificaton(choice)) {
                index = Integer.parseInt(choice);
                if (index >= 1 && index < books.size() + 1) {
                    return books.get(index - 1);
                } else {
                    System.out.println(Tips.INCORRECT);
                }
            }
        }
        return null;
    }

    /**
     * Validate new book's fields
     * @param book book that is to be validated
     * @return
     */
    public boolean validateNewBook(Book book) {
        boolean result = book.getName().equals("") || book.getAuthor().equals("");
        if (result) {
            System.out.println(Tips.REQUIRED);
        }
        return !result;
    }

    /**
     * Validate user's input
     * @param choice user's input
     * @return
     */
    private boolean validateSpecificaton(String choice) {
        return !choice.equals("") && !choice.equals(Commands.CANCEL.toString());
    }

    /**
     * Get book's new name
     * @return new name
     * @throws IOException
     */
    public String getNewName() throws IOException{
        System.out.println(Tips.NEW_NAME);
        return bufferedReader.readLine().trim();
    }

    /**
     * Close input stream
     * @throws IOException
     */
    public void close() throws IOException {
        bufferedReader.close();
    }
}