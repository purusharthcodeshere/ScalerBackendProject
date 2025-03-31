package com.lld.basics.mergesort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> arrayToSort = List.of(
                10, 2, 8, 11, 4, 3, 9, 29, 24, 4, 7, 13, 17, 23, 15
        );

        ExecutorService executorService = Executors.newCachedThreadPool();
        //Create an object of the task
        Sorter sorter = new Sorter(arrayToSort, executorService);

        Future<List<Integer>> sortedArray = executorService.submit(sorter);

        List<Integer> sortedList = sortedArray.get();

        for (Integer integer : sortedList) {
            System.out.print(integer + " ");
        }

        executorService.shutdown();
    }
}
