package org.schoolsorokin.javacore.multithreading.executorservice;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Задача 1 заверишлась");
            }  catch (InterruptedException e) {
                System.out.println("Задача 1 прервана");
            }
        });
        //
        Future<?> task2 = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Задача 2 завершилась");
            } catch (InterruptedException e) {
                System.out.println("Задача 2 прервана");
            }
        });
        //
        Future<String> task3 = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "Задача 3 завершилась";
        });
        //
        Future<String> task4 = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(8);
            return "Задача 4 завершилась";
        });
        //
        Future<String> task5 = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "Задача 5 завершилась";
        });

        try {
            System.out.println(task2.get());
            System.out.println(task3.get());

            try {
                System.out.println(task4.get(3, TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                System.out.println("Задача 4 не успела выполниться");
                task4.cancel(true);
            }

            System.out.println(task5.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
