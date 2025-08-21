package org.schoolsorokin.javacore.exception.hotel;

public class Hotel {
    private boolean[] rooms =  new boolean[1000];

    public Hotel() {
        //Занятые номера
        rooms[100] = true;
        rooms[101] = true;
        rooms[102] = true;
        rooms[103] = true;
    }

    public void bookRoom(int roomNumber) {
        if (roomNumber < 1 || roomNumber > 1000) {
            throw  new InvalidRoomNumberException(roomNumber);
        }

        int index = roomNumber - 1;
        if (rooms[index]) {
            throw new RoomNotAvailableException(roomNumber);
        }

        rooms[index] = true;
        System.out.println("Вы забронировали комнату №" + roomNumber + ".");
    }
}
