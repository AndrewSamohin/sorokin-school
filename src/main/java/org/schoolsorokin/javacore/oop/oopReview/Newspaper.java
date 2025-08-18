package org.schoolsorokin.javacore.oop.oopReview;

import java.util.Objects;

public class Newspaper extends Publication implements Printable {

    private  String publicationDay;

    @Override
    public void printDetails() {
        System.out.print("Type: " + getType()
                + "\nTitle: " + getTitle()
                + "\nAuthor: " + getAuthor()
                + "\nYear: " + getYear()
                + "\nPublication Day: " + publicationDay);
    }

    @Override
    public String toString() {
        return  getType() + ", " + "title = " +
                getTitle() + ", author = " +
                getAuthor() + ", year = " +
                getYear() + ", publication day = " +
                publicationDay + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Newspaper other = (Newspaper) obj;
        return Objects.equals(this.publicationDay, other.publicationDay) &&
                Objects.equals(this.getTitle(), other.getTitle()) &&
                Objects.equals(this.getAuthor(), other.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationDay, getTitle(), getAuthor(), getYear());
    }

    @Override
    public String getType() {
        return "Type: Newspaper";
    }

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

}
