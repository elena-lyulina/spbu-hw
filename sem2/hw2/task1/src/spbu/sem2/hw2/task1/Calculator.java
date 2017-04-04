package spbu.sem2.hw2.task1;

/**
 * Class that finds the answer of expression.
 */
public class Calculator {
    /**  Stack for numbers in expression. */
    private static ArrayStack<Integer> numbers = new ArrayStack<>();
    /**  Stack for operators in expression. */
    private static DynamicStack<Character> operators = new DynamicStack<>();

    /**
     * this function finds the answer of expression.
     *
     * @param string expression you want to count
     * @return the answer of expression
     */
    public static int getAnswer(String string) {
        return counting(toPostfix(string));
    }

    /**
     * this function translates infix form to postfix.
     *
     * @param inString expression you want to translate
     * @return post-fix form of expression
     */
    private static String toPostfix(String inString) {
        String outString = "";
        int i = 0;
        while (i < inString.length()) {
            char c = inString.charAt(i);
            if (isNumber(c))
                outString = outString.concat(Character.toString(c));
            else if (isOperator(c)) {
                while (getStackPriority() >= priority(c)) {
                    outString = outString.concat(Character.toString(operators.pop()));
                }
                operators.push(c);
            }
            else if (isOpeningBrace(c))
                operators.push(c);
            else if (isClosingBrace(c)) {
                char top = operators.pop();
                while (top != '(') {
                    outString = outString.concat(Character.toString(top));
                    top = operators.pop();
                }
            }
            i++;
        }

        while (!operators.isEmpty()) {
            outString = outString.concat(Character.toString(operators.pop()));
        }
        return outString;
    }

    /**
     * this function finds the answer using postfox form.
     *
     * @param string expression in postfix form
     * @return the answer
     */
    private static int counting(String string) {
        int i = 0;
        while (i < string.length()) {
            char c = string.charAt(i);
            switch (c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    numbers.push(c - '0');
                    break;
                case '/':
                case '*':
                case '+':
                case '-':
                    numbers.push(doMath(c));
                    break;
            }
            i++;
        }
        return numbers.pop();
    }

    /**
     * check whether that sumbol is opening brace.
     *
     * @param ch symbol you want to check
     * @return true if symbol is opening brace, false if isn't
     */
    private static boolean isOpeningBrace(char ch) {
        return ch == '(';
    }
    /**
     * check whether that symbol is closing brace.
     *
     * @param ch symbol you want to check
     * @return true if symbol is closing brace, false if isn't
     */
    private static boolean isClosingBrace(char ch) {
        return ch == ')';
    }
    /**
     * check whether that symbol is number.
     *
     * @param ch symbol you want to check
     * @return true if symbol is number, false if isn't
     */
    private static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
    /**
     * check whether that symbol is operator.
     *
     * @param ch symbol you want to check
     * @return true if symbol is operator, false if isn't
     */
    private static boolean isOperator(char ch) {
        return (ch == '+') || (ch == '-') || (ch == '/') || (ch == '*');
    }

    /**
     * this function finds the priority of symbol.
     * @param ch
     * @return
     */
    private static int priority(char ch) {
        if (ch == '(')
            return 1;
        if (ch == '+' || ch == '-')
            return 2;
        if (ch == '/' || ch == '*')
            return 3;
        else
            return -1;
    }

    /**
     * this function finds priority of stack' last element.
     *
     * @return priority
     */
    private static int getStackPriority() {
        if (operators.isEmpty())
            return 0;
        char c = operators.pop();
        int priority = priority(c);
        operators.push(c);
        return priority;
    }

    /**
     * this function does mathematical operations with two last elements of stack.
     *
     * @param ch operation you want to do
     * @return the answer
     */
    private static int doMath(char ch) {
        int a = numbers.pop();
        int b = numbers.pop();
        if (ch == '+')
            return a + b;
        if (ch == '-')
            return b - a;
        if (ch == '*')
            return a * b;
        if (ch == '/')
            return b / a;
        else
            return -1;
    }
}
