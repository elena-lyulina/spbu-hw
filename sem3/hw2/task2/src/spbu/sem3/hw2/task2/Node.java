package spbu.sem3.hw2.task2;

/**
 * Class for Node of Iterator tree.
 * @param <T> type of data
 */
public class Node<T extends Comparable> {
    private T data = null;
    private Node<T> leftChild = null;
    private Node<T> rightChild = null;
    private Node<T> parent = null;

    /**
     * Constructor for Node.
     * @param data value of node
     * @param parent parent of node
     */
    public Node(T data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    /** Get the data of this node. */
    public T getData() {
        return data;
    }

    /** Get the left child of this node. */
    public Node<T> getLeftChild() {
        return leftChild;
    }

    /** Get the parent of this node. */
    public Node<T> getParent() {
        return parent;
    }

    /** Get the right child of this node. */
    public Node<T> getRightChild() {
        return rightChild;
    }

    /**
     * Set the right child of this node.
     * @param node node that you want to be the new right child
     */
    public void setRightChild(Node<T> node) {
        rightChild = node;
        if (node != null)
            node.parent = this;
    }

    /**
     * Set the left child of this node.
     * @param node node that you want to be the new left child
     */
    public void setLeftChild(Node<T> node) {
        leftChild = node;
        if (node != null)
            node.parent = this;
    }


    /**
     * Adding element to this node.
     * @param element value of element you ant to add
     * @throws ElementDoesExist if this element already exists
     */
    public void add(T element) throws ElementDoesExist {
        if (data.compareTo(element) == 0) {
            throw new ElementDoesExist();
        }
        if (data.compareTo(element) < 0) {
            if (rightChild == null) {
                rightChild = new Node<T>(element, this);
                return;
            }
            rightChild.add(element);
            return;
        }

        if (data.compareTo(element) > 0) {
            if (leftChild == null) {
                leftChild = new Node<T>(element, this);
                return;
            }
            leftChild.add(element);
            return;
        }
    }

    /**
     * Finding an element from this node.
     * @param element value of element you want to find
     * @return finding element
     */
    public Node find(T element) {
        if (data == null) {
            return null;
        }
        if (data.compareTo(element) == 0) {
            return this;
        }
        if (data.compareTo(element) < 0) {
            return rightChild.find(element);
        }
        if (data.compareTo(element) > 0) {
            return leftChild.find(element);
        }
        return null;
    }

    /**
     * Removing an element from this node.
     * @throws ElementDoesntExist if thi element doesn't exist
     */
    public void remove() throws ElementDoesntExist {
        if (this == null) {
            throw new ElementDoesntExist();
        }
        if (leftChild != null && rightChild != null) {
            Node<T> temp = rightChild;
            while (temp.leftChild != null) {
                data = temp.data;
                temp = temp.leftChild;
            }
            rightChild.remove();
        } else if (parent.leftChild == this) {
            parent.setLeftChild((leftChild != null) ? leftChild : rightChild);
        } else if (parent.rightChild == this) {
            parent.setRightChild((leftChild != null) ? leftChild : rightChild);
        }
    }

    /**
     * Printing Node with its children to String.
     * @param out String to which you want to add this node
     * @return the final result of printing
     */
    public StringBuilder print(StringBuilder out) {
        if (isItNull(this)) {
            out.append(" null");
            return out;
        }
        out.append(" (" + data);
        out = isItNull(leftChild) ? out.append(" null") : out.append(leftChild.print(new StringBuilder()));
        out = isItNull(rightChild) ? out.append(" null") : out.append(rightChild.print(new StringBuilder()));
        out.append(") ");
        return out;
    }

    /**
     * Check if this node or its data is null.
     * @param node node you want to check
     * @return true or false depends on result of checking
     */
    private boolean isItNull(Node<T> node) {
        return (node == null || node.data == null);
    }

    /** Exception in case of "this element doesn't exist"-situation while finding or removing. */
    public static class ElementDoesExist extends RuntimeException {
        public ElementDoesExist() {
            super("This element does exist");
        }
    }

    /** Exception in caseof adding element that already exists.*/
    public static class ElementDoesntExist extends RuntimeException {
        public ElementDoesntExist() {
            super("This element doesnt exist");
        }
    }
}