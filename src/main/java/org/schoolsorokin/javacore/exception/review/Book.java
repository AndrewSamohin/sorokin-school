package org.schoolsorokin.javacore.exception.review;

public class Book {
    private final String title;
    private final String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        //Не нравится участок с проверками с помощью if,
        //но я не знаю, как можно сделать по-другому

        //Проверка title
        if (title == null) {
            throw new IllegalArgumentException("Название не может быть нулевым.");
        }
        //Убераем лишние пробелы для проверки на пустоту
        String t = title.trim();
        if (t.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым.");
        }
        //Проверка author
        if (author == null) {
            throw new IllegalArgumentException("Автор не может быть нулевым.");
        }
        //Убераем пробелы и проверяем на пустоту
        String a = author.trim();
        if (a.isEmpty()) {
            throw new IllegalArgumentException("Автор не может быть пустым.");
        }
        //Проверка количества
        if (availableCopies <= 0) {
            if (availableCopies < 0) {
                throw new IllegalArgumentException("Количество копий не может быть отрицательным.");
            } else {
                throw new NoAvailableCopiesException(title);
            }
        }

        this.title = t;
        this.author = a;
        this.availableCopies = availableCopies;
    }

    //Геттеры
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    //Сеттер для копий с проверкой на отрицание
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    //Переопределил toString для удобного вывода
    @Override
    public String toString() {
        return "Название: " + title + "; Автор: "
                + author + "; Количество копий: " + availableCopies + ".\n";
    }
}