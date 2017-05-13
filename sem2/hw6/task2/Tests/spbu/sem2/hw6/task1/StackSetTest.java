package spbu.sem2.hw6.task1;

import org.junit.Test;

import java.util.Random;
import java.util.Stack;

import org.junit.Assert;

public class StackSetTest {
    StackSet<Integer> stackSet = new StackSet<>();
    Random random = new Random();

    Stack<Integer> stack = new Stack<>();

    public void initializeStack() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            stack.add(random.nextInt());
        }
    }

    public void initializeStackSet() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            stackSet.add(random.nextInt());
        }
    }

    @Test
    public void sizeTest() {
        int n = 1000;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            stackSet.add(random.nextInt());
        }
        Assert.assertTrue(n == stackSet.size());
    }

    @Test
    public void isEmptyTest() {
        Assert.assertTrue(stackSet.isEmpty());
    }

    @Test
    public void addAndContainsTest() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            int toAdd = random.nextInt();
            stackSet.add(toAdd);
            Assert.assertTrue(stackSet.contains(toAdd));
        }
    }

    @Test
    public void addAllAndContainsAllTest() {
        initializeStack();
        stackSet.addAll(stack);
        Assert.assertTrue(stackSet.containsAll(stack));
    }

    @Test
    public void removeTest() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            int toRemove = random.nextInt();
            stackSet.add(toRemove);
            stackSet.remove(toRemove);
            Assert.assertTrue(!stackSet.contains(toRemove));
        }
    }

    @Test
    public void retainAllTest() {
        initializeStack();
        initializeStackSet();

        stackSet.addAll(stack);
        stackSet.retainAll(stack);

        Assert.assertTrue(stackSet.containsAll(stack));
        stackSet.removeAll(stack);
        Assert.assertTrue(stackSet.isEmpty());
    }

    @Test
    public void removeAllTest() {
        initializeStack();
        initializeStackSet();
        stackSet.addAll(stack);
        stackSet.removeAll(stack);
        Assert.assertTrue(!stackSet.containsAll(stack));
    }

    @Test
    public void clearTest() {
        initializeStackSet();
        stackSet.clear();
        Assert.assertTrue(stackSet.isEmpty());
    }
}