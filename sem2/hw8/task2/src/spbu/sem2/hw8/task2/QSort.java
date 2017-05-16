package spbu.sem2.hw8.task2;

import java.util.ArrayList;

public class QSort {
    /**
     * finds index of partition.
     * @param left the beginning of the part
     * @param right the end of the part
     * @return index of partition
     */
    public static int partition(ArrayList array, int left, int right) {
        int i = left;
        int j = right;
        int pivot = (int) array.get((left + right) / 2);
        while (i <= j) {
            while ((int) array.get(i) < pivot) {
                i++;
            }
            while ((int) array.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    /**
     * swaps two elements.
     * @param i index of the first element
     * @param j index of the second element
     */
    private static void swap(ArrayList array, int i, int j) {
        int temp = (int)array.get(i);
        array.set(i, (int)array.get(j));
        array.set(j, temp);
    }
}
