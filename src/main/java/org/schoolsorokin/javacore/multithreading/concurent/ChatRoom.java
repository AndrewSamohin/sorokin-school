package org.schoolsorokin.javacore.multithreading.concurent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom {
    private final CopyOnWriteArrayList<String> messages = new  CopyOnWriteArrayList<>();

    public void postMessage(String user, String text) {
        String message = user + " : " + text;
        messages.add(message);
        System.out.println(user + " опубликовал: " + text);
    }

    public List<String> getRecentMessages(int count) {
        int size = messages.size();
        if (size == 0) {
            return List.of();
        }
        int fromIndex = Math.max(size - count, 0);
        return messages.subList(fromIndex, size);
    }
}
