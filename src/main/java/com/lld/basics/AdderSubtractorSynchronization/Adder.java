package com.lld.basics.AdderSubtractorSynchronization;

public class Adder implements Runnable {
    private Count count;

    public Adder(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1_000_000; i++) {
            synchronized (count) {
                //Internally it is calling, lock.lock() on count
                count.value += 1;
            }   //Here it is unlocking it
        }
    }
}
