package spbu.sem2.hw2.task2;

import org.junit.Test;

import java.util.Random;

import org.junit.Assert;

public class ListTest {
    int[] elements = new int[10];

    private void initialize(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    @Test
    public void oneLinkedListTest() {
        List oneLinkedListList = new OneLinkedList();
        initialize(elements);

        for (int i = 0; i < elements.length; i++) {
            oneLinkedListList.add(elements[i]);
        }

        for (int i = 0; i < elements.length; i++) {
            oneLinkedListList.remove(elements[i]);
        }
        Assert.assertTrue(oneLinkedListList.isEmpty());
    }

    @Test
    public void twoLinkedListTest() {
        List twoLinkedList = new TwoLinkedList();
        initialize(elements);

        for (int i = 0; i < elements.length; i++) {
            twoLinkedList.add(elements[i]);
        }

        for (int i = 0; i < elements.length; i++) {
            twoLinkedList.remove(elements[i]);
        }
        Assert.assertTrue(twoLinkedList.isEmpty());
    }
}