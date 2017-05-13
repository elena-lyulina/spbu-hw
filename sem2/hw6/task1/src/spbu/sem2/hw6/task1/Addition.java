package spbu.sem2.hw6.task1;

/** Class for addition. */
public class Addition extends Operator {
    @Override
    public int calculate() {
        return firstOperand.calculate() + secondOperand.calculate();
    }

    @Override
    char toChar() {
        return '+';
    }
}
