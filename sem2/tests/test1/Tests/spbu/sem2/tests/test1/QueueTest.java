package spbu.sem2.tests.test1;

import org.junit.Test;
import org.junit.Assert;

import java.util.Random;

public class QueueTest {
    int size = 100;
    int[][] array = new int[size][2];
    Queue<Integer> queue = new PriorityQueue<>();

    private void initialize() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i][0] = random.nextInt();
            array[i][1] = random.nextInt();
        }
    }

    private void sort() {
        for (int i = 1; i < size; i++) {
            int value = array[i][0];
            int priority = array[i][1];
            int j = i - 1;
            while (j >= 0 && array[j][1] < priority) {
                array[j + 1][0] = array[j][0];
                array[j + 1][1] = array[j][1];
                j--;
            }
            array[j + 1][0] = value;
            array[j + 1][1] = priority;
        }
    }

    @Test
    public void AddPriorityQueueTest() {
        initialize();
        for (int i = 0; i < size; i++) {
            queue.enqueue(array[i][0], array[i][1]);
        }
        Assert.assertTrue(queue.size() == size);
    }

    @Test
    public void RemovePriorityQueueTest() {
        initialize();
        for (int i = 0; i < size; i++) {
            queue.enqueue(array[i][0], array[i][1]);
        }
        sort();
        for (int i = 0; i < size; i++) {
            Assert.assertTrue(queue.dequeue() == array[i][0]);
        }
    }

    @Test
    public void ExceptionPriorityQueueTest() {
        boolean thrown = false;
        try {
            queue.dequeue();
        } catch (Exception e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }
}