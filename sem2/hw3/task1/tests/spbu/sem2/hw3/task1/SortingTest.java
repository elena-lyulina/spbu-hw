package spbu.sem2.hw3.task1;

import java.util.Random;

import org.junit.Test;
import org.junit.Assert;

public class SortingTest {

    private int size = 10000;
    private int[] array = new int[size];

    private void initialize(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    @Test
    public void InsertionTest() {
        Sorting test = new InsertionSort();
        initialize(array);
        test.sort(array);
        for (int i = 0; i < array.length - 1; i++)
            Assert.assertTrue(array[i] < array[i + 1]);
    }

    @Test
    public void GnomeTest() {
        Sorting test = new GnomeSort();
        initialize(array);
        test.sort(array);
        for (int i = 0; i < array.length - 1; i++)
            Assert.assertTrue(array[i] < array[i + 1]);
    }

    @Test
    public void CocktailTest() {
        Sorting test = new CocktailSort();
        initialize(array);
        test.sort(array);
        for (int i = 0; i < array.length - 1; i++)
            Assert.assertTrue(array[i] < array[i + 1]);
    }
}