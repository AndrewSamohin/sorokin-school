package org.schoolsorokin.javacore.multithreading.review2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {
    private final ExecutorService executorService;
    private final AtomicInteger counterTasks;//Счетчик задач
    private final AtomicInteger activeTasks;//Количество активных задач
    private final Map<String, Integer> results;
    //Конструктор для управления количества поток
    public DataProcessor(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.counterTasks = new AtomicInteger(0);
        this.activeTasks = new AtomicInteger(0);
        this.results = new HashMap<>();
    }
    //Добавляем новую задачу
    public String submitTask(List<Integer> numbers) {
        String taskName = "task " + counterTasks.getAndIncrement();

        activeTasks.incrementAndGet();

        executorService.submit(() -> {
            try {
                CalculateSumTask task = new CalculateSumTask(numbers, taskName);
                Integer result = task.call();

                synchronized (results) {
                    results.put(taskName, result); //Сохраняем результат
                }
            } catch (Exception e) {
                System.out.println("Ошибка в задаче: " + e.getMessage());
            } finally {
                activeTasks.decrementAndGet();
            }
        });

        return taskName;
    }
    //Получение кол-ва активных задач
    public int getActiveTasks() {
        return activeTasks.get();
    }
    //Получение результата
    public Optional<Integer> getResult(String taskName) {
        synchronized (results) {
            return Optional.ofNullable(results.get(taskName));
        }
    }
    //Завершение задачи
    public void shutdown() {
        executorService.shutdown();
        try {
            boolean isTerminated = executorService.awaitTermination(10, TimeUnit.SECONDS);
            if (!isTerminated) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            throw new RuntimeException(e);
        }
    }
}
