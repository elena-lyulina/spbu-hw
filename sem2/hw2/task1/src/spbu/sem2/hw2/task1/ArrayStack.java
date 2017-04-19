package spbu.sem2.hw2.task1;

/**
 * Array-based Stack.
 *
 * @param <Type> tyoe of Stack' elements
 */
public class ArrayStack<Type> implements Stack<Type> {

    /**
     * Class that creates an array for ArrayStack.
     *
     * @param <T> type of Array's elements
     */
    private class Array<T> {
        private int maxSize = 5;
        private Type[] arr = (Type[]) new Object[maxSize];
        private int size = 0;

        /**
         * this function returns size of Array.
         *
         * @return size of array
         */
        public int size() {
            return size;
        }

        /** this function doubles current array' size. */
        public void doubleArray() {
            Type[] temp = null;
            temp = (Type[]) new Object[maxSize * 2];
            for (int i = 0; i < maxSize; i++) {
                temp[i] = arr[i];
            }
            maxSize *= 2;
            arr = temp;
        }

        /**
         * this function adds an element to array.
         *
         * @param data an element you want to add
         */
        public void add(Type data) {
            if (size == maxSize) {
                doubleArray();
            }
            arr[size] = data;
            size++;
        }

        /**
         * this function removes the last element from array.
         *
         * @return the element that has been removed
         */
        public Type remove() {
            if (isEmpty())
                return null;
            Type top = arr[size - 1];
            arr[size - 1] = null;
            size--;
            return top;
        }
    }

    private Array<Type> array;

    public ArrayStack() {
        array = new Array<>();
    }

    public void push(Type data) {
        array.add(data);
    }

    public Type pop() {
        return array.remove();
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.size == 0;
    }
}


