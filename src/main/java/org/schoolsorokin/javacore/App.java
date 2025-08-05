package org.schoolsorokin.javacore;

import org.schoolsorokin.javacore.oop.Book;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Book newBook = new Book(); //Создание объекта Book
        newBook.author = "Федор Достоевский";
        newBook.title = "Преступление и наказание";
        newBook.pages = 672;
        newBook.read();
    }
}
