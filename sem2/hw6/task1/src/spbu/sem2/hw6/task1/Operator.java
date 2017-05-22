package spbu.sem2.hw6.task1;

/** Class for operators. */
public abstract class Operator implements Operand {
    /** first operand of expression. */
    protected Operand firstOperand;
    /** second operand of expression. */
    protected Operand secondOperand;

    @Override
    public abstract int calculate();

    @Override
    public String toString() {
        return "(" + toChar() + " " + firstOperand.toString() + " " + secondOperand.toString() + ")";
    }

    /** This function casts operator to char.
     * @return char-type operator
     */
    abstract char toChar();

    /** This function initializes first operand of expression. */
    public void setFirstOperand(Operand firstOperand) {
        this.firstOperand = firstOperand;
    }

    /** This function initializes second operand of expression. */
    public void setSecondOperand(Operand secondOperand) {
        this.secondOperand = secondOperand;
    }
}
