package spbu.sem2.hw6.task1;

/** Class for tree/ */
public class Tree extends GetTree {

    public Tree(String expression) {
        super(expression);
    }

    /** This function prints tree. */
    static void print() {
        System.out.println(GetTree.root.toString());
    }

    /**
     * This function finds the result of expression.
     * @return the result
     */
    static int calculate() {
        return GetTree.root.calculate();
    }

    /**
     * This function casts tree to string.
     * @return String has been casted
     */
    static String TreeToString() {
        return GetTree.root.toString();
    }
}
