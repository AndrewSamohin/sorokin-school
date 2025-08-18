package org.schoolsorokin.javacore.oop.oopReview;

import java.util.Objects;

public class Book extends Publication implements Printable{

    private String ISBN;

    @Override
    public void printDetails() {
        System.out.print("Type: " + getType()
                + "\nTitle: " + getTitle()
                + "\nAuthor: " + getAuthor()
                + "\nYear: " + getYear()
                + "\nISBN: " + ISBN);
    }

    @Override
    public String toString() {
        return  getType() + ", " + "title = " +
                getTitle() + ", author = " +
                getAuthor() + ", year = " +
                getYear() + ", ISBN = " +
                ISBN + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Book other = (Book) obj;
        return Objects.equals(this.ISBN, other.ISBN) &&
                Objects.equals(getTitle(), other.getTitle()) &&
                Objects.equals(getAuthor(), other.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, getTitle(), getAuthor(), getYear());
    }

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        this.ISBN = ISBN;
    }

    @Override
    public String getType() {
        return "Type: Book";
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
