package spbu.sem2.hw2.task1;

/**
 * Dynamic-based Stack.
 *
 * @param <Type> type of Stack' elements
 */
public class DynamicStack<Type> implements Stack<Type> {
    /** Class that creates a StackElement */
    private class StackElement {
        public Type data;
        public StackElement next;

        public StackElement(Type data, StackElement next) {
            this.data = data;
            this.next = next;
        }
    }

    private StackElement top = null;
    private int size = 0;

    public void push(Type data) {
        StackElement toAdd = new StackElement(data, top);
        top = toAdd;
        size++;
    }

    public Type pop() {
        if (size == 0)
            return null;
        size--;
        Type data = top.data;
        top = top.next;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}