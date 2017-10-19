package spbu.sem3.hw2.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Class that describes IteratorTree. */
public class IteratorTree<T extends Comparable> implements Iterable<T> {
    private Node<T> root;
    private int size;

    /** Constructor for IteratorTree. */
    public IteratorTree(){
        root = null;
        size = 0;
    }

    /**
     *  Adding element to tree.
     * @param data value you want to add
     */
    public void addElement(T data) {
        size++;
        if (root == null) {
            root = new Node(data, null);
            return;
        }
        root.add(data);
    }

    /**
     * Finding element in the tree.
     * @param data value you want to find
     * @return false or true depends on existing element in the tree
     */
    public boolean findElement(T data) {
        if (root == null || root.find(data) == null) {
            return false;
        }
        return true;
    }

    /**
     * Removing element from the tree.
     * @param data value you want to remove
     * @throws Node.ElementDoesntExist in case of "element doesn't exist"-situation
     */
    public void removeElement(T data) throws Node.ElementDoesntExist{
      if (root == null) {
          throw new Node.ElementDoesntExist();
      }
      size--;
      if (root.data == data) {
          Node<T> rootParent = new Node(null, null);
          rootParent.setRightChild(root);
          root.remove();
          root = rootParent.getRightChild();
          return;
      }
      else {
          root.find(data).remove();
      }
    }

    /**
     * Printing the tree in "root - leftChild - rightChild" - construction.
     * @return String with printing tree
     */
    public String print() {
        if (root == null) {
            return "null";
        }
        return root.print("");
    }

    /**
     * Check if the tree ie empty.
     * @return true or false depends on emptiness of tree.
     */
    public boolean isEmpty() {
        return root == null && size == 0;
    }


    /**
     * Constructor for tree iterator.
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator(root);
    }


    /** Class that describes tree iterator.  */
    public class TreeIterator implements Iterator<T> {
        private Node<T> next;

        /**
         * Constructor for tree iterator.
         * @param root root of the tree
         */
        public TreeIterator(Node root) {
            next = root;
            if(next == null)
                return;

            while (next.leftChild != null)
                next = next.leftChild;
        }

        /**
         * Check if there is next element.
         * @return true or false depends on existing next element
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Make an iteration.
         * @return value of actual element
         */
        @Override
        public T next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            Node<T> toReturn = next;

            if (next.rightChild != null) {
                next = next.rightChild;
                while (next.leftChild != null) {
                    next = next.leftChild;
                }
                return toReturn.data;
            }

            while (next.parent != null) {
                if (next == next.parent.leftChild) {
                    next = next.parent;
                    return toReturn.data;
                }
                next = next.parent;
            }

            next = null;
            return toReturn.data;
        }

        /** Remove actual element and changes number of next element */
        @Override
        public void remove() {
            removeElement(this.next());
        }
    }
}