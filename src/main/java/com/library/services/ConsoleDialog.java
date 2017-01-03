package com.library.services;

import com.library.constants.Commands;
import com.library.constants.Tips;
import com.library.entities.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleDialog {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void writeTips() {
        System.out.println(Tips.TIPS);
    }

    public String getQuery() throws Exception {
        return bufferedReader.readLine().trim();
    }

    public void writeNoCommand() {
        System.out.println(Tips.NO_COMMAND);
    }

    public void writeAll(ArrayList<Book> books) {
        if (books.size() > 0) {
            System.out.println(Tips.LIST);
            books.forEach(book -> System.out.println(book.toString()));
        } else {
            System.out.println(Tips.NO_RESULT);
        }
    }

    public void writeAddition(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was added");
    }

    public void writeUpdate(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was updated");
    }

    public void writeDeletion(Book book) {
        System.out.println("Book " + book.getAuthor() + " \"" + book.getName() + "\" was removed");
    }

    public void writeFail() {
        System.out.println(Tips.FAIL);
    }


    public Book getElaboration(ArrayList<Book> books) throws Exception{
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
            if (!choice.equals("") && !choice.equals(Commands.CANCEL.toString())) {
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

    public boolean validateNewBook(Book book) {
        boolean result = book.getName().equals("") || book.getAuthor().equals("");
        if (result) {
            System.out.println(Tips.REQUIRED);
        }
        return !result;
    }

    public String getNewName() throws Exception{
        System.out.println(Tips.NEW_NAME);
        return bufferedReader.readLine().trim();
    }

    public void close() throws Exception {
        bufferedReader.close();
    }
}