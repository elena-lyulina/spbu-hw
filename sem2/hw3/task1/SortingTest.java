package spbu.sem2.hw3.task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

    private int size = 10000;
    private int[] array = new int[size];

    @BeforeEach
    public void fillArray() {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
    }

    @AfterEach
    public void clearArray() {
        array = null;
    }

    private boolean checkTestPassing() {
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    @Test
    public void CocktailSortTest() {
        Sorting test = new CocktailSort();
        test.sort(array);
        if (!checkTestPassing()) {
            System.out.println("COCTAIL_SORT: oops, it doesn't work");
        } else {
            System.out.println("COCTAIL_SORT: test passed");
        }
    }

    @Test
    public void GnomeSortTest() {
        Sorting test = new GnomeSort();
        test.sort(array);
        if (!checkTestPassing()) {
            System.out.println("GNOME_SORT: oops, it doesn't work");
        } else {
            System.out.println("GNOME_SORT: test passed");
        }
    }

    @Test
    public void InsertionSortTest() {
        Sorting test = new InsertionSort();
        test.sort(array);
        if (!checkTestPassing()) {
            System.out.println("INSERTION_SORT: oops, it doesn't work");
        } else {
            System.out.println("INSERTION_SORT: test passed");
        }
    }

}