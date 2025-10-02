package org.schoolsorokin.javacore.multithreading.review2;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {
    private final List<Integer> numbers;
    private final String taskName;

    public CalculateSumTask(List<Integer> numbers, String taskName) {
        this.numbers = numbers;
        this.taskName = taskName;
    }

    public Integer call() {
        //Выводим имя задачи и потока
        System.out.println("Выполняется " + taskName + " в потоке " +
                Thread.currentThread().getName());
        //Делаем задержку
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Вычисляем сумму
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return sum;
    }
}
