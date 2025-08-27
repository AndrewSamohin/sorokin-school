package org.schoolsorokin.javacore.exception.review;

public class InvalidBookCopiesException extends RuntimeException {
    public InvalidBookCopiesException(String title) {
        super("Недопустимое количество копий для книги: \"" + title + "\". Количество должно быть больше 0.");
    }
}
