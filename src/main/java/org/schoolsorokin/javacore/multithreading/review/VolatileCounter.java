package org.schoolsorokin.javacore.multithreading.review;

public class VolatileCounter implements SiteVisitCounter {
    private volatile int count = 0;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
    }

    @Override
    public Integer getVisitCount() {
        return count;
    }
}
