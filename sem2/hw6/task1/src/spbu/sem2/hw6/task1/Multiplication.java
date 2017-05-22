package spbu.sem2.hw6.task1;

/** Class for multiplication. */
public class Multiplication extends Operator {
    @Override
    public int calculate() {
        return firstOperand.calculate() * secondOperand.calculate();
    }

    @Override
    char toChar() {
        return '*';
    }
}
