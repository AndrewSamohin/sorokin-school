package org.schoolsorokin.javacore.reflectionAPI.finaltask;

import static org.schoolsorokin.javacore.reflectionAPI.finaltask.Validator.validate;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Amanda", 20, 4);
        User user2 = new User("Amanda", 16, 0);
        User user3 = new User("Amanda", 31, 12);

        try {
            System.out.println("Проверка user1: ");
            validate(user1);
            System.out.println("Проверка user2: ");
            validate(user2);
            System.out.println("Проверка user3: ");
            validate(user3);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}