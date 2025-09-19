package org.schoolsorokin.javacore.multithreading.review;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements  SiteVisitCounter {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count.incrementAndGet();
    }

    @Override
    public Integer getVisitCount() {
        return count.get();
    }
}
