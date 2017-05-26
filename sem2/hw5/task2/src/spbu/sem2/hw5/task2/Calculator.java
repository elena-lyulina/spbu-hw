package spbu.sem2.hw5.task2;

/**
 * Class for calculating process.
 */
public class Calculator {

    /**
     * This function counts the answer of expression.
     *
     * @param operand1  first operand
     * @param operand2  second operand
     * @param operation operation you want to do with operands
     * @return the answer
     * @throws DivisionByZero exception in case of division by zero
     */
    public static String calculate(String operand1, String operand2, String operation) throws DivisionByZero {
        double answer = 0;
        switch (operation) {
            case "+":
                answer = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                break;
            case "-":
                answer = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                break;
            case "*":
                answer = Double.parseDouble(operand1) * Double.parseDouble(operand2);
                break;
            case "/":
                if (operand2 == "0")
                    throw new DivisionByZero();
                else
                    answer = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                break;
            }
            return Double.toString(round(answer));
        }

        /**
         * This function rounds the number.
         * @param toRound the nubmer you want to round
         * @return rounded number
         */

    private static double round(double toRound) {
        toRound *= 10000;
        return (double) Math.round(toRound) / 10000;
    }

    /**
     * Exception in case of division be zero.
     */
    public static class DivisionByZero extends RuntimeException {
        public DivisionByZero() {
            super("Division by zero");
        }
    }
}
