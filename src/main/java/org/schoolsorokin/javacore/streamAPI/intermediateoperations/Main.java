package org.schoolsorokin.javacore.streamAPI.intermediateoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> line = Arrays.asList("Java is fun",
                "Stream API simplifies data processing",
                "Knowing how to use lists makes life easier",
                "Java can be complicated");

        line.stream()
                .flatMap(lineStr -> Arrays.stream(lineStr.split(" ")))
                .filter(word -> word.length() > 3)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
