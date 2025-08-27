package org.schoolsorokin.javacore.collectionframework.duplicate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateChecker {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        // Заполнение списка числами от 0 до 100000 с дублированием одного значения
        for (int i = 0; i <= 100000; i++) {
            numbers.add(i);
        }
        numbers.add(50000); // Вставляем дубликат

        long startTimeList = System.nanoTime(); //Начало

        boolean hasDuplicates = false;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    hasDuplicates = true;
                    break;
                }
            }
            if (hasDuplicates) break;
        }

        long endTimeList = System.nanoTime(); //Конец

        System.out.println("Дубликаты найдены: " + hasDuplicates);
        System.out.println("Время выполнения: " + (endTimeList - startTimeList) / 1_000_000 + " мс");
        //Время выполнения 12430 мс

        //Другой способ поиска дубликата
        long startTimeSet = System.nanoTime();
        Set<Integer> numSet =  new HashSet<>(numbers);
        boolean duplicate = numSet.size() != numbers.size();
        long endTimeSet = System.nanoTime();
        System.out.println("Дубликаты найдены: " + duplicate);
        System.out.println("Время выполения:  " + (endTimeSet - startTimeSet) / 1_000_000 + " мс");
        //Время выполнения 15 мс
    }
}
