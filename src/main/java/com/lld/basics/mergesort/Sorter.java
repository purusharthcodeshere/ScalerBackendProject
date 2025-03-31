package com.lld.basics.mergesort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>> {

    private List<Integer> listToSort;
    private ExecutorService executorService;

    public Sorter(List<Integer> arrayToSort, ExecutorService executorService) {
        this.listToSort = arrayToSort;
        this.executorService = executorService;
    }

    @Override
    public List<Integer> call() throws Exception {
        if (listToSort.size() <= 1) {
            return listToSort;
        }

        int mid =  listToSort.size() / 2;

        List<Integer> leftArray =  this.listToSort.subList(0, mid);
        List<Integer> rightArray = this.listToSort.subList(mid, listToSort.size());

        //Create the task for left and right
        //Passing left and right arrays to get them sorted
        Sorter leftSorter = new Sorter(leftArray, this.executorService);
        Sorter rightSorter = new Sorter(rightArray, this.executorService);

        Future<List<Integer>> leftSortedArrayFuture = this.executorService.submit(leftSorter);
        Future<List<Integer>> rightSortedArrayFuture = this.executorService.submit(rightSorter);

        //At this point this thread has to wait for the data in leftSortedArrayFuture
        List<Integer> leftSortedArray = leftSortedArrayFuture.get();
        List<Integer> rightSortedArray = rightSortedArrayFuture.get();

        //Instead of doing merge sort here
        //We used a method to call for merging the arrays
        //And then in-line command to send the sorted array
        return getSortedArray(leftSortedArray, rightSortedArray);
    }

    private List<Integer> getSortedArray(List<Integer> leftSortedArray, List<Integer> rightSortedArray) {
        List<Integer> sortedArray = new ArrayList<>();

        //Implement merging of the data
        int i = 0, j = 0;

        while (i < leftSortedArray.size() && j < rightSortedArray.size()) {
            if (leftSortedArray.get(i) <= rightSortedArray.get(j)) {
                sortedArray.add(leftSortedArray.get(i++));
            } else {
                sortedArray.add(rightSortedArray.get(j++));
            }
        }

        while (i < leftSortedArray.size()) {
            sortedArray.add(leftSortedArray.get(i++));
        }

        while (j < rightSortedArray.size()) {
            sortedArray.add(rightSortedArray.get(j++));
        }
        return sortedArray;
    }
}
