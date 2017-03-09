package spbu.sem2.hw1.task1;

public class Stack<Type> {
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

    public void push (Type data) {
        StackElement toAdd = new StackElement(data, top);
        top = toAdd;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Type pop() {
        if (isEmpty())
            return null;
        size--;
        Type data = top.data;
        top = top.next;
        return data;
    }

   public int size() {
        return size;
   }

}
