package spbu.sem2.hw3.task2;

import java.io.FileNotFoundException;

/**
 * interface that describes structure for spiral outputting.
 */
public interface Outputer {
    /**
     * this function outputs binary array spirally.
     *
     * @param array an array you want to output
     */
    public void printArray(int[][] array) throws FileNotFoundException;
}
