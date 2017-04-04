package spbu.sem2.hw2.task1;

import org.junit.Test;

import org.junit.Assert;

public class CalculatorTest {
    String expression = "((1 + 1) * 9 - 8) / 5";
    int correctResult = 2;

    @Test
    public void calculatorTest() {
        Assert.assertTrue(correctResult == Calculator.getAnswer(expression));
    }
}