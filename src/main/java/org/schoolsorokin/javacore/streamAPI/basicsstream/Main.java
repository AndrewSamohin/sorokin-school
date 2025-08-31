package org.schoolsorokin.javacore.streamAPI.basicsstream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Стрим из коллекции
        List<String> names = Arrays.asList("anna", "masha", "oleg");
        List<String> namesStream = names
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("\nСтрим из коллекции: ");
        namesStream.forEach(System.out::println);

        //Стрим из массива
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Stream<Integer> stream = Arrays
                .stream(numbers)
                .filter(i -> i % 2 == 0)
                .limit(4)
                .collect(Collectors
                        .toList())
                .stream();
        System.out.println("\nСтрим из массива: ");
        stream.forEach(System.out::println);

        //Стрим из отдельных элементов
        Stream<Double> streamNumbers = Stream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
        System.out.println("\nСтрим из отдельных элементов: ");
        streamNumbers.filter(i -> i % 2.0 == 0.0)
                .limit(4)
                .forEach(System.out::println);

        //Стрим из генераторов
        Stream<Double> strDoubleStream = Stream.generate(Math::random);
        System.out.println("\nСтрим из генераторов: ");
        strDoubleStream.limit(5)
                .forEach(System.out::println);
    }
}
