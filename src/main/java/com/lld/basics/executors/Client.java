package com.lld.basics.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        //the newFixedThreadPool will create
        // the number of threads that we pass as an argument
        //Similarly, newCachedThreadPoll will create the threads by itself,
        // depending upon the task and the power of the machine
        Executor executor = Executors.newFixedThreadPool(20);
        //If you are not sure about how many threads to create
        //Then go with newCachedThreadPool
        //Executor executor = Executors.newCachedThreadPool();

        for (int i  = 0; i <= 1000; i++) {
            if (i == 5 || i == 10 || i == 15 || i ==  20 || i == 25) {
                 System.out.println("Stop here");
            }
            NumberPrinter numberPrinter = new NumberPrinter(i);
            executor.execute(numberPrinter);
        }

    }
}
