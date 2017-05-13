package spbu.sem2.hw6.task1;

/** Class for division. */
public class Division extends Operator {
    /**
     * This function do division.
     * @return the result of division
     * @throws DivisionByZero exception in case of division by zero
     */
    @Override
    public int calculate() throws DivisionByZero {
        if (secondOperand.calculate() == 0) {
            throw new DivisionByZero();
        }
        else
            return firstOperand.calculate() / secondOperand.calculate();
    }

    @Override
    char toChar() {
        return '/';
    }

    /** Exception in case of division by zero. */
    public static class DivisionByZero extends RuntimeException {
        public DivisionByZero() {
            super("Division by zero");
        }
    }
}
