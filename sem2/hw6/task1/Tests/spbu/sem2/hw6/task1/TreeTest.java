package spbu.sem2.hw6.task1;

import org.junit.Test;

import org.junit.Assert;

public class TreeTest {
    String expression = "(+ (* 4 (- 13 10)) (/ 21 3))";
    int correctResult = 19;
    Tree tree = new Tree(expression);

    @Test
    public void calculateTest() {
        Assert.assertTrue(tree.calculate() == correctResult);
    }

    @Test
    public void TreeToStringTest() {
        Assert.assertTrue(Tree.TreeToString().equals(expression));
    }

    @Test
    public void DivisionByZeroTest() {
        boolean thrown = false;

        String expression2 = "(/ 8 0)";
        Tree tree2 = new Tree(expression2);

        try {
            tree2.calculate();
        } catch (Exception e) {
            thrown = true;
        }
        Assert.assertTrue(thrown == true);
    }
}