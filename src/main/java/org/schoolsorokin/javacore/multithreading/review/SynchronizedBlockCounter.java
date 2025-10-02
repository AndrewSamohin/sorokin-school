package org.schoolsorokin.javacore.multithreading.review;

public class SynchronizedBlockCounter implements  SiteVisitCounter {
    private Integer count = 0;

    @Override
    public void incrementVisitCount() {
        synchronized (this) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }

    @Override
    public Integer getVisitCount() {
        return count;
    }
}
