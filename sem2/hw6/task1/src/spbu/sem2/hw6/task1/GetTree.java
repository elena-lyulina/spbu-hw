package spbu.sem2.hw6.task1;

/** Class for making tree. */
public class GetTree {
    static Operand root;
    int indexOfExpression = 0;

    public GetTree(String expression) {
        root = getRoot(expression);
    }

    /**
     * This function gets root from expression.
     * @param expression expression from which you get the root
     * @return the root
     */
    private Operand getRoot(String expression) {
        char nextLetter = expression.charAt(indexOfExpression);
        if (nextLetter == '(') {
            indexOfExpression++;
            return getOperator(expression);
        } else {
            return new Number(getNumber(expression));
        }
    }

    /**
     *  This function gets number from expression.
     *  The number can be multidigit;
     * @param expression expression from which you get the number
     * @return int number
     */
    private int getNumber(String expression) {
        int number = expression.charAt(indexOfExpression) - '0';
        indexOfExpression++;
        while(isDigit(expression.charAt(indexOfExpression))) {
            number *= 10;
            number += expression.charAt(indexOfExpression) - '0';
            indexOfExpression++;
        }
        return number;
    }

    /**
     * This function checks if the symbol is digit.
     * @param symbol symbol you want to check
     * @return true or false depends on symbol
     */
    private boolean isDigit(char symbol) {
        return (symbol >= '0' && symbol <= '9');
    }

    /**
     * This function gets operator from expression and initializes first and second operands.
     * @param expression expression you work with
     * @return operator
     */
    private Operator getOperator(String expression) {
        Operator operator = null;
        switch (expression.charAt(indexOfExpression)) {
            case '+':
                operator = new Addition();
                break;
            case '-':
                operator = new Subtraction();
                break;
            case '*':
                operator = new Multiplication();
                break;
            case '/':
                operator = new Division();
                break;
        }

        indexOfExpression += 2;
        operator.setFirstOperand(getRoot(expression));
        indexOfExpression++;
        operator.setSecondOperand(getRoot(expression));
        indexOfExpression++;

        return operator;
    }
}
