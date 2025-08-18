package org.schoolsorokin.javacore.exception.hotel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("Введите номер комнаты для бронирования (1-1000): ");

            try {
                int roomNumber = sc.nextInt();
                hotel.bookRoom(roomNumber);
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введено некорректное значение. Введите число!");
                sc.nextLine();
            } catch (InvalidRoomNumberException | RoomNotAvailableException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Хотите забронировать еще одну комнату? (да/нет): ");
            String choice = sc.next();
            if (choice.equals("нет")) {
                flag = false;
            }
        }
        System.out.println("Спасибо, что пользуютесь нашем отелем!");
    }
}
