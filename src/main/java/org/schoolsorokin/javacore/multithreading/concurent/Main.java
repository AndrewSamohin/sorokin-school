package org.schoolsorokin.javacore.multithreading.concurent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ChatRoom chat =  new ChatRoom();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //3 пользователя пишут сообщения
        for(int i = 1; i <= 3; i++) {
            int userId = i;
            executor.submit(() -> {
                for (int j = 1; j <= 5; j++) {
                    chat.postMessage("User " + userId, "Сообщение №" + j);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        //Читатель выводит последние сообщения
        executor.submit(() -> {
            for (int k = 0; k < 10; k++) {
                List<String> recent = chat.getRecentMessages(5);
                System.out.println("Последние сообщения " + recent.size() + " сообщение: ");
                recent.forEach(System.out::println);
                System.out.println("_______");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}
