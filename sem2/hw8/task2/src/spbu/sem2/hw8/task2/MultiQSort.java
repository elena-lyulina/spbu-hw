package spbu.sem2.hw8.task2;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

/** Class for multi-thread quick sort. */
public class MultiQSort extends RecursiveAction {
    private ArrayList array;
    private int left;
    private int right;

    /** constructor. */
    public MultiQSort(ArrayList array) {
        this.array = array;
        right = array.size() - 1;
        left = 0;
    }

    /** constructor for part of array.
     * @param array array you want to sort
     * @param left the beginning of the part
     * @param right the end of the part
     */
    public MultiQSort(ArrayList array, int left, int right){
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    public void compute() {
        sortByPart(array, left, right);
    }

    /**
     * sorts the part of array.
     * @param left the beginning of the part
     * @param right the end of the part
     */
    private void sortByPart(ArrayList array, int left, int right) {
        int pivot = QSort.partition(array, left, right);
        if (pivot > left + 1) {
            invokeAll(new MultiQSort(array, left, pivot - 1));
        }
        if (right > pivot ) {
            invokeAll(new MultiQSort(array, pivot, right));
        }
    }
}
