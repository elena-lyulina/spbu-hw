package spbu.sem2.hw5.task1;

import org.junit.Test;
import org.junit.Assert;

import java.util.Random;

public class CalculatorTest {
    private int n = 100;
    private int[][] expressions = new int[n][3];

    private void initialize(int[][] expressions) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            expressions[i][0] = (int) (Math.random()*(Calculator.max() - Calculator.min()) + Calculator.min());
            expressions[i][1] = (int) (Math.random()*(Calculator.max() - Calculator.min()) + Calculator.min());
            expressions[i][2] = Math.abs(r.nextInt() % 4);
        }
    }

    @Test
    public void calculateTest() {
        initialize(expressions);
        for (int i = 0; i < n; i++) {
            String operation = "";
            double answer = 0;
            switch (expressions[i][2]) {
                case 0:
                    operation = "+";
                    answer = expressions[i][0] + expressions[i][1];
                    break;
                case 1:
                    operation = "-";
                    answer = expressions[i][0] - expressions[i][1];
                    break;
                case 2:
                    operation = "*";
                    answer = expressions[i][0] * expressions[i][1];
                    break;
                case 3:
                    operation = "/";
                    if (expressions[i][1] == 0) {
                        boolean thrown = false;
                        try {
                            Calculator.calculate(expressions[i][0], expressions[i][1], operation);
                        } catch (Exception e){
                            thrown = true;
                        }
                        Assert.assertTrue(thrown == true);
                        expressions[i][1]++;
                    }
                    answer = (double) expressions[i][0] / (double) expressions[i][1];
                    break;
            }

            String correctResult = Double.toString(round(answer));
            String calculatorResult = Calculator.calculate(expressions[i][0], expressions[i][1], operation);

            Assert.assertTrue(calculatorResult.equals(correctResult));
        }
    }

    private double round(double toRound) {
        toRound *= 1000;
        return (double) Math.round(toRound) / 1000;
    }
}