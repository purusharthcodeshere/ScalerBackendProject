package com.lld.basics.AdderSubtractorMutex;

import java.util.concurrent.locks.Lock;

public class Subtractor implements Runnable {
    private Count count;
    private Lock lock;

    public Subtractor(Count count, Lock lock) {
        this.count = count;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1_000_000; i++) {
            this.lock.lock();
            this.count.value -= 1;
            this.lock.unlock();
        }
    }
}