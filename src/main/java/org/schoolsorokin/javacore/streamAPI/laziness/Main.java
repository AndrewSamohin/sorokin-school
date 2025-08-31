package org.schoolsorokin.javacore.streamAPI.laziness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        long startStream = System.nanoTime();
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());

        result.forEach(System.out::println);
        long endStream = System.nanoTime();
        System.out.println("Скорость выполнения stream: " +  (endStream - startStream));

        long startFor = System.nanoTime();
        List<Integer> resultFor = new ArrayList<>();
        for (int n : numbers) {
            if (n % 2 == 0) {
                resultFor.add(n * n);
            }
        }
        for (int n : resultFor) {
            System.out.println(n);
        }
        long endFor = System.nanoTime();
        System.out.println("Скорость выполнения for: " +  (endFor - startFor));
    }
}
