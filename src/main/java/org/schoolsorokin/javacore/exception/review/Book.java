package org.schoolsorokin.javacore.exception.review;

import org.springframework.util.ObjectUtils;

public class Book {
    private final String title;
    private final String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        checkBook();

        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    private void checkBook() {
        //Проверка title
        if (ObjectUtils.isEmpty((title != null) ? title.trim() : null)) {
            throw new IllegalArgumentException("Название не может быть пустым.");
        }

        //Проверка author
        if (ObjectUtils.isEmpty((author != null) ? author.trim() : null)) {
            throw new IllegalArgumentException("Автор не может быть пустым.");
        }

        // Проверка количества копий
        if (availableCopies <= 0) {
            throw new InvalidBookCopiesException(title);
        }
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