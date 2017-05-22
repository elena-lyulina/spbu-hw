package spbu.sem2.hw6.task1;

/** Class for numbers. */
public class Number implements Operand {
    public int number;
    public Number(int number) {
        this.number = number;
    }
    @Override
    public int calculate() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
