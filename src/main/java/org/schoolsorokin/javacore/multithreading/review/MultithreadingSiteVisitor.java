package org.schoolsorokin.javacore.multithreading.review;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {
    private final SiteVisitCounter counter;
    private final List<Thread> threads = new ArrayList<>();
    private long startTime;
    private long endTime;


    public MultithreadingSiteVisitor(SiteVisitCounter counter) {
        this.counter = counter;
    }
    //Запуск потоков
    public void visitMultithread(int numOfThreads) {
        startTime = System.currentTimeMillis();

        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(counter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }
    //Завершение всех потоков
    public void waitUntilAllVisited() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();
    }
    //
    public double getTotalTimeOfHandling() {
        return (endTime - startTime) / 1000.0;
    }
}