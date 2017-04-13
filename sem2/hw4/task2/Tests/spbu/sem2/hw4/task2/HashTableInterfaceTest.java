package spbu.sem2.hw4.task2;

import org.junit.Test;
import org.junit.Assert;

public class HashTableInterfaceTest {
    int size = 100;
    HashTable ht1 = new HashTable(1);
    HashTable ht2 = new HashTable(2);

    private void initialize(String[] array) {
        for (int i = 0; i < array.length; i++) {
            int maxChar = 256;
            array[i] = Character.toString((char) (Math.random() * maxChar));
        }
    }

    @Test
    public void AddToHtTest() {
        String elements[] = new String[size];
        initialize(elements);
        for(int i = 0; i < size; i++) {
            ht1.add(elements[i]);
            ht2.add(elements[i]);
        }
        for (int i = 0; i < size; i++) {
            Assert.assertTrue(ht1.findWord(elements[i]));
            Assert.assertTrue(ht2.findWord(elements[i]));
        }
    }

    @Test
    public void RemoveFromHtTest() {
        String elements[] = new String[size];
        initialize(elements);
        for(int i = 0; i < size; i++) {
            ht1.add(elements[i]);
            ht2.add(elements[i]);
        }

        for (int i = 0; i < size; i++) {
            ht1.remove(elements[i]);
            ht2.remove(elements[i]);
        }

        Assert.assertTrue(ht1.isEmpty());
        Assert.assertTrue(ht2.isEmpty());
    }
}