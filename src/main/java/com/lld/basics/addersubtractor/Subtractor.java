package com.lld.basics.addersubtractor;

public class Subtractor implements Runnable {
    private Count count;

    public Subtractor(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1_000_000; i++) {
            count.value -= 1;
        }
    }
}
