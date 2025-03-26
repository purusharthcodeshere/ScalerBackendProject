package com.lld.basics.threads;

public class NumberPrinter implements Runnable{

    //SOP
    //1. Think the task can be done in parallel or not
    //2. Create a class for the task
    //3. Implement Runnable Interface
    //4. Create an object of the class, and use thread to run it. (In client (main) class)
    private int currentNumber;

    public NumberPrinter(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    @Override
    public void run() {
        System.out.println("We are printing " + this.currentNumber +
                ". The current thread is " + Thread.currentThread().getName());
    }
}
