package spbu.sem2.hw6.task1;

/** Interface for operands. */
public interface Operand {
    /**
     * this function calculates expression.
     * @return the answer
     */
    int calculate();

    /**
     *  This function casts operand to String.
     * @return String-type operand
     */
    String toString();
}
