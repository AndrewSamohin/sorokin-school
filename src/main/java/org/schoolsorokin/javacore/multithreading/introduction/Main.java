package org.schoolsorokin.javacore.multithreading.introduction;

public class Main {
    public static Integer number = 0;
    public static void main(String[] args) throws InterruptedException {
        //Способ 1
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(number += 1);
            }
        });
        //Способ 2
        Runnable runnable1 = () -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(number += 1);
            }
        };
        Thread thread2 = new Thread(runnable1);
        //Способ 3
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println(number += 1);
                }
            }
        };
        Thread thread3 = new Thread(runnable2);
        //Способ 4
        Thread thread4 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println(number += 1);
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}
