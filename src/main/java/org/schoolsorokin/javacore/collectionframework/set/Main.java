package org.schoolsorokin.javacore.collectionframework.set;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Создаем список и заполняем его с дубликатами
        List<String> strList = new ArrayList<>();
        strList.add("Анна");
        strList.add("Маша");
        strList.add("Оля");
        strList.add("Анна");
        strList.add("Оля");
        //Выводим список
        System.out.println("Список: \n" + strList);
        //Создаем HashSet и внедряем список с дубликатами
        Set<String> strSet = new HashSet<>(strList);
        //Выводим список без дубликатов
        System.out.println("Итоговый список без добликатов: \n" + strSet);
        //Создаем LinkedHashSet и отсортировываем список без дубликатов
        Set<String> strSet2 = new LinkedHashSet<>(strList);
        //Выводим LinkedHashSet
        System.out.println("Итоговый список LinkedHashSet: \n" + strSet2);
    }
}
