package org.schoolsorokin.javacore.exception.review;

public class NoAvailableCopiesException extends  RuntimeException {
    public NoAvailableCopiesException(String getTitle) {
        super("Нет доступных копий для " +  getTitle + ".");
    }
}
