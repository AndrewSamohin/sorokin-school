package org.schoolsorokin.javacore.exception.hotel;

public class InvalidRoomNumberException extends RuntimeException {
    public InvalidRoomNumberException(int roomNumber) {
        super("Номер комнаты " +  roomNumber + " недопустим. Допустимые номера комнат с 1 по 1000.");
    }
}
