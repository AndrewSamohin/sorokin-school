package org.schoolsorokin.javacore.exception.review;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> catalog = new ArrayList<>();

    //Добавление новой книги в каталог
    public void addBook(Book book) {
        //Провераяем, есть ли уже такая книга
        for (Book b : catalog) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())
                && b.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                int newCopies = b.getAvailableCopies() + book.getAvailableCopies();
                b.setAvailableCopies(newCopies);
                return;
            }
        }

        //Если нет книги, добавляем новую
        Book newBook = new Book(book.getTitle(), book.getAuthor(), book.getAvailableCopies());
        catalog.add(newBook);
    }

    //Выдаем книгу
    public void takeBook(String title) {
        //Поиск книги в каталоге
        for (Book b : catalog) {
            if (b.getTitle().equalsIgnoreCase(title.trim())) {
                if (b.getAvailableCopies() == 0) {
                    throw new NoAvailableCopiesException(title);
                }
                b.setAvailableCopies(b.getAvailableCopies() - 1);
                return;
            }
        }
        throw new IllegalArgumentException("Книга " +  title + " не найдена в каталоге.");
    }

    //Возврат книги
    public void returnBook(String title) {
        for (Book b : catalog) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.setAvailableCopies(b.getAvailableCopies() + 1);
                return;
            }
        }
        throw new IllegalArgumentException("Книга " +  title + " не найдена в каталоге.");
    }

    //Получение всех книг
    public List<Book> getAllBooks() {
        return new ArrayList<>(catalog);
    }
}
