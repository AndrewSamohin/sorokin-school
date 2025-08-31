package org.schoolsorokin.javacore.streamAPI.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Анна", 25),
                new User("Маша", 19),
                new User("Анна", 25),
                new User("Рома", 33),
                new User("Никита", 22),
                new User("Сергей", 41),
                new User("Оля", 39),
                new User("Рома", 33)
        );

        List<String> result = users.stream()
                .filter(user -> user.getAge() > 25)
                .map(User::getName)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Отфильтрованные имена: " + result);

        int sumAge = users.stream()
                .map(User::getAge)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Общий возраст: " + sumAge);

        int minAge = users.stream()
                .map(User::getAge)
                .min(Integer::compare)
                .get();

        System.out.println("Минимальный возраст: " + minAge);
    }
}
