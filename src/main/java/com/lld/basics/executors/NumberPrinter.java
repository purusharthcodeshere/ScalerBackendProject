package com.lld.basics.executors;

public class NumberPrinter implements Runnable {

    private int currentNumber;

    public NumberPrinter(int  currentNumber) {
        this.currentNumber = currentNumber;
    }

    @Override
    public void run() {
        System.out.println("Printing  " + this.currentNumber +
                ". The current thread is " + Thread.currentThread().getName());
    }
}
