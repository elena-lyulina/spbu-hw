package spbu.sem2.hw8.task2;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;

public class QuickSortTest {
    int n = 1000000;
    ArrayList array = new ArrayList();
    Random r = new Random();

    @Before
    public void initialize() {
        for (int i = 0; i < n; i++) {
            array.add(r.nextInt());
        }
    }

    @Test
    public void singleQSortTest() {
        SingleQSort sorter = new SingleQSort(array);

        long start = System.currentTimeMillis();
        sorter.sort(array);
        long finish = System.currentTimeMillis();
        System.out.println("SingleThread running time: " + (finish - start));

        for (int i = 0; i < n - 1; i++) {
            Assert.assertTrue((int)array.get(i) <= (int)array.get(i + 1));
        }
    }

    @Test
    public void multiQSortTest() {
        MultiQSort sorter = new MultiQSort(array);

        long start = System.currentTimeMillis();
        sorter.compute();
        long finish = System.currentTimeMillis();
        System.out.println("MultiThread running time: " + (finish - start));

        for (int i = 0; i < n - 1; i++) {
            Assert.assertTrue((int)array.get(i) <= (int)array.get(i + 1));
        }
    }
}