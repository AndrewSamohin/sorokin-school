package org.schoolsorokin.javacore.oop.oopReview;

import java.util.Objects;

public abstract class Publication implements Printable {

    private String title;
    private String author;
    private int year;

    public abstract String getType();

    private static int getPublicationCount = 0;

    public static int getPublicationCount() {
        return getPublicationCount;
    }

    public static void increasePublicationCount() {
        getPublicationCount++;
    }

    @Override
    public void printDetails() {
        System.out.println("Publication: title = " + title +
                ", author = " + author +
                ", year = " + year + ".");
    }

    @Override
    public String toString() {
        return  "Publication: title = " +
                title + ", author = " +
                author + ", year = " +
                year + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null)  return false;
        if (getClass() != obj.getClass()) return false;

        Publication other = (Publication) obj;
        return Objects.equals(this.title, other.title)
                && Objects.equals(this.author, other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    protected Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //Геттеры
    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    //Сеттеры
    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
