package spbu.sem2.hw2.task1;

/**
 * Interface for Stack.
 *
 * @param <Type> type of Stack' elements
 */
public interface Stack<Type> {
    /**
     * this function adds an element to Stack.
     *
     * @param data an element you want to add
     */
    public void push(Type data);

    /**
     * this function removes the last element from Stack.
     *
     * @return the element that has been removed
     */
    public Type pop();

    /**
     * this function returns Stack' size.
     *
     * @return Stack' size
     */
    public int size();

    /**
     * check whether Stack is empty.
     *
     * @return true if Stack is empty, false if isn't
     */
    public boolean isEmpty();
}
