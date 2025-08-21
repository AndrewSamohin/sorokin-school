package org.schoolsorokin.javacore.exception.hotel;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(int roomNumber) {
        super("Комната №" +  roomNumber + " уже забронирована.");
    }
}