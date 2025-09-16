package org.schoolsorokin.javacore.multithreading.deadlock;

public class DeadlockExample {
    private static final int num = 100;

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread firstThread = new Thread(()->{
            for (int i = 0; i < num; i++) {
                synchronized (obj1) {
                    System.out.println("1-й поток захватил 1-й монитор");
                    synchronized (obj2) {
                        System.out.println("1-й поток захватил 2-й монитор");
                    }
                }
            }
        });
        Thread secondThread = new Thread(()->{
            for (int i = 0; i < num; i++) {
                synchronized (obj1) {
                    System.out.println("2-й поток захватил 1-й монитор");
                    synchronized (obj2) {
                        System.out.println("2-й поток захватил 2-й монитор");
                    }
                }
            }
        });
        firstThread.start();
        secondThread.start();

    }
}
