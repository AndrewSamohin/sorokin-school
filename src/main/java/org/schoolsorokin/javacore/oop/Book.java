package org.schoolsorokin.javacore.oop;

public class Book {
    public String title;
    public String author;
    public int pages;

    public void read() {
        System.out.println("Я читаю книгу " + title + " авторства " + author + ".");
    }
}
