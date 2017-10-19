package spbu.sem3.hw2.task2;

import org.junit.Test;
import org.junit.Assert;

import java.util.Iterator;

public class TreeIteratorTest {
    int[] elements = {5, 8, 3, 2, 1, 4, 9, 10, 7, 6};

    @Test
    public void addingTest() {
        String expectedAnswer = " (5 (3 (2 (1 null null)  null)  (4 null null) )  (8 (7 (6 null null)  null)  (9 null (10 null null) ) ) ) ";

        IteratorTree<Integer> tree = new IteratorTree();
        for (int i = 0; i < elements.length; i++) {
            tree.addElement(elements[i]);
        }
        Assert.assertTrue(expectedAnswer.equals(tree.print()));

        for (int i = 1; i <= elements.length; i++) {
            boolean thrown = false;
            try {
                tree.addElement(i);
            } catch (Exception e) {
                thrown = true;
            }
            Assert.assertTrue(thrown);
        }
    }

    @Test
    public void removingTest() {
        IteratorTree<Integer> tree = new IteratorTree();
        for (int i = 0; i < elements.length; i++) {
            tree.addElement(elements[i]);
        }

        for (int i = 1; i <= elements.length; i++) {
            tree.removeElement(i);
        }
        Assert.assertTrue(tree.isEmpty());
    }

    @Test
    public void findingTest() {
        IteratorTree<Integer> tree = new IteratorTree();
        for (int i = 0; i < elements.length; i++) {
            tree.addElement(elements[i]);
        }

        for (int i = 1; i <= elements.length; i++) {
            Assert.assertTrue(tree.findElement(i));
        }

        for (int i = 0; i < elements.length; i++) {
            boolean thrown = false;
            try {
                tree.findElement(i + 11);
            } catch (Exception e) {
                thrown = true;
            }

            Assert.assertTrue(thrown);
        }
    }

    @Test
    public void forEachTest() {
        IteratorTree<Integer> tree = new IteratorTree();
        for (int i = 0; i < elements.length; i++) {
            tree.addElement(elements[i]);
        }

        String expectedAnswer = "1 2 3 4 5 6 7 8 9 10 ";
        String answer = "";
        Iterator iterator = tree.iterator();
        for (Integer i: tree) {
            answer = answer.concat(iterator.next() + " ");
        }
        Assert.assertTrue(answer.equals(expectedAnswer));
    }

    @Test
    public void removingIteratorTest() {
        IteratorTree<Integer> tree = new IteratorTree();
        for (int i = 0; i < elements.length; i++) {
            tree.addElement(elements[i]);
        }

        Iterator iterator = tree.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
        Assert.assertTrue(tree.isEmpty());
    }
}

