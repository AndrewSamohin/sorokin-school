package org.schoolsorokin.javacore.multithreading.review;

public class Main {
    public static void main(String[] args) {
        SiteVisitCounter[] counters = {
                new UnsynchronizedCounter(),
                new VolatileCounter(),
                new SynchronizedBlockCounter(),
                new AtomicIntegerCounter(),
                new ReentrantLockCounter()
        };

        String[] names = {
                "UnsynchronizedCounter",
                "VolatileCounter",
                "SynchronizedBlockCounter",
                "AtomicIntegerCounter",
                "ReentrantLockCounter"
        };

        System.out.println("Тест с 10 потоками");
        runTest(counters, names, 10);

        System.out.println("\nТест с 100 потоками");
        runTest(counters, names, 100);
    }

    private static void runTest(SiteVisitCounter[] counters, String[] names, int numThreads) {
        for (int i = 0; i < counters.length; i++) {
            SiteVisitCounter counter = counters[i];
            MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);

            visitor.visitMultithread(numThreads);
            visitor.waitUntilAllVisited();

            System.out.println(names[i] + " ---> Итоговое значение: " + counter.getVisitCount()
                    + " | Время: " + visitor.getTotalTimeOfHandling() + " сек.");
        }
    }
    /*Вывод
    1. UnsynchronizedCounter и VolatileCounter быстрые, но не точные, так как гонка данных
    2. SynchronizedBlockCounter и ReentrantLockCounter выдают верные результаты,
    но медленные из-за блокировки sleep
    3. AtomicIntegerCounter выполняет быстро и верно, так как использует CAS вместо блокировок,
    как у SynchronizedBlockCounter и ReentrantLockCounter*/
}
