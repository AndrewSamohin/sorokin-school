package org.schoolsorokin.javacore.oop.oopReview;

import java.util.Objects;

public class Magazine extends Publication implements Printable {

    private int issueNumber;

    @Override
    public void printDetails() {
        System.out.print("Type: " + getType()
                + "\nTitle: " + getTitle()
                + "\nAuthor: " + getAuthor()
                + "\nYear: " + getYear()
                + "\nIssue number: " + issueNumber);
    }

    @Override
    public String toString() {
        return  getType() + ", " + "title = " +
                getTitle() + ", author = " +
                getAuthor() + ", year = " +
                getYear() + ", issue number = " +
                issueNumber + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Magazine other = (Magazine) obj;
        return Objects.equals(this.issueNumber, other.issueNumber) &&
                Objects.equals(this.getTitle(), other.getTitle()) &&
                Objects.equals(this.getAuthor(), other.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueNumber, getTitle(), getAuthor(), getYear());
    }

    @Override
    public String getType() {
        return "Type:  Magazine";
    }

    public Magazine(String title, String author, int year, int  issueNumber) {
        super(title, author, year);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

}
