package org.schoolsorokin.javacore.multithreading.review2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor(10);

        List<String> taskNames =  new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            List<Integer> numbers = Arrays.asList(i, i + 1, i + 2);
            String taskName = processor.submitTask(numbers);
            taskNames.add(taskName);
        }

        while (processor.getActiveTasks() > 0) {
            System.out.println("Активных задач: " + processor.getActiveTasks());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Все задачи выполнены!");

        for (String taskName : taskNames) {
            Optional<Integer> result = processor.getResult(taskName);
            System.out.println(taskName + " --- " + result.orElse(null));
        }

        processor.shutdown();
    }
}
