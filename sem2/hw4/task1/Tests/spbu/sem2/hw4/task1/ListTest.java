package spbu.sem2.hw4.task1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class ListTest {
    Random random = new Random();

    private void initialize(int[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt();
    }

    private void initialize(char[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxChar = 256;
            array[i] = (char) (Math.random() * maxChar);
        }
    }

    @Test
    public void LinkedListIntegerTest() {
        int[] elements = new int[10];
        List linkedList = new LinkedList();
        initialize(elements);

        for (int i = 0; i < elements.length; i++) {
            linkedList.add(elements[i]);
        }
        Assert.assertTrue(linkedList.size() == 10);

        for (int i = 0; i < elements.length; i++) {
            linkedList.remove(elements[i]);
        }
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void LinkedListCharTest() {
        char[] elements = new char[10];
        List linkedList = new LinkedList();
        initialize(elements);

        for (int i = 0; i < elements.length; i++) {
            linkedList.add(elements[i]);
        }
        Assert.assertTrue(linkedList.size() == 10);

        for (int i = 0; i < elements.length; i++) {
            linkedList.remove(elements[i]);
        }
        Assert.assertTrue(linkedList.isEmpty());
    }

    @Test
    public void ElementDoesExistTest() {
        boolean thrown = false;

        List<Integer> list = new UniqeList<>();
        int toAdd = random.nextInt();
        list.add(toAdd);

        try {
            list.add(toAdd);
        } catch (Exception e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    @Test
    public void ElementDoesntExistTest() {
        boolean thrown = false;

        List<Integer> list = new UniqeList<>();
        int toRemove = random.nextInt();

        try {
            list.remove(toRemove);
        } catch (Exception e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

}