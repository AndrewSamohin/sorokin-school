package org.schoolsorokin.javacore.reflectionAPI.annotations;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Author(name = "Виктор Смирнов", date = "2025-14-09")
    public void printInfo() {
        System.out.println("Книга: " + title + ", Автор: " +  author + ", Год: " + year);
    }

    @Author(name = "Мария Воробьева", date = "2025-14-09")
    public void read() {
        System.out.println("Вы читаете книгу \"" + title + "\"");
    }

    @Author(name = "Никита Куприянов", date = "2025-15-09")
    public void archive() {
        System.out.println("Книга \"" + title + "\" добавлена в архив.");
    }
}
