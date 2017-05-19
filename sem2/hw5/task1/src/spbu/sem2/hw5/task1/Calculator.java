package spbu.sem2.hw5.task1;

/** Class for calculating process. */
public class Calculator {
    private static int min = -100;
    private static int max = 100;

    /**
     * This function counts the answer of expression.
     * @param operand1 first operand
     * @param operand2 second operand
     * @param operation operation you want to do with operands
     * @return the answer
     * @throws DivisionByZero exception in case of division by zero
     */
    public static String calculate(int operand1, int operand2, String operation) throws DivisionByZero{
        double answer = 0;
        if (operation.equals("+"))
            answer = operand1 + operand2;
        else if (operation.equals("-"))
            answer = operand1 - operand2;
        else if (operation.equals("*"))
            answer = operand1 * operand2;
        else if (operation.equals("/")) {
            if (operand2 == 0)
                throw new DivisionByZero();
            else answer = (double) operand1 / (double) operand2;
        }
        return Double.toString(round(answer));
    }

    /**
     * This function rounds the number.
     * @param toRound the nubmer you want to round
     * @return rounded number
     */
    private static double round(double toRound) {
        toRound *= 1000;
        return (double) Math.round(toRound) / 1000;
    }

    /**
     * This function returns minimal of admissible values.
     * @return minimal of admissible values
     */
    public static int min() {
        return min;
    }

    /**
     * This function returns maximal of admissible values.
     * @return maximal of admissible values
     */
    public static int max() {
        return max;
    }

    /** Exception in case of division be zero. */
    public static class DivisionByZero extends RuntimeException {
        public DivisionByZero () { super("Division by zero");}
    }
}
