package com.lld.basics.threads;

public class Client {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
//            System.out.println(i);

            //Step 4 of the SoP
            NumberPrinter numberPrinter = new NumberPrinter(i);
            Thread thread = new Thread(numberPrinter);
            thread.start(); //Runs the task using Threads
        }
    }
}
