package spbu.sem2.hw8.task2;

import java.util.ArrayList;

/** Class for single-thread quick sort. */
public class SingleQSort {
    private int left;
    private int right;

    /** constructor. */
    public SingleQSort(ArrayList array) {
        right = array.size() - 1;
        left = 0;
    }

    /** sorts array. */
    public void sort(ArrayList array) {
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
            sortByPart(array, left, pivot - 1);
        }
        if (right > pivot ) {
            sortByPart(array, pivot, right);
        }
    }
}
