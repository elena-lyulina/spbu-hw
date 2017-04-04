package spbu.sem2.hw2.task2;

/**
 * two-linked list.
 */
public class TwoLinkedList implements List {
    /**
     * Class that creates List' element
     */
    private class ListElement {
        public int value;
        public ListElement prev;
        public ListElement next;

        public ListElement(int value, ListElement previous, ListElement next) {
            this.next = next;
            this.prev = previous;
            this.value = value;
        }
    }

    private ListElement prev = null;
    private ListElement next = null;
    private int length = 0;

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(int value) {
        if (isEmpty()) {
            prev = new ListElement(value, null, null);
            next = prev;
            length++;
            return;
        }
        if (prev.value > value) {
            ListElement toAdd = new ListElement(value, null, prev);
            prev.prev = toAdd;
            prev = toAdd;
            length++;
            return;
        }

        ListElement temp = prev;
        if (temp.value == value)
            return;
        while (temp.next != null && temp.next.value < value) {
            temp = temp.next;
        }
        if (temp.next != null && temp.next.value == value)
            return;

        temp.next = new ListElement(value, temp, temp.next);
        if (temp.next.next != null)
            temp.next.next.prev = temp.next;
        else
            next = temp.next;
        length++;
    }

    public void remove(int value) {
        if (isEmpty())
            return;

        ListElement temp = prev;
        if (temp.next == null && temp.value == value) {
            prev = null;
            length--;
            return;
        }

        if (temp.value == value) {
            prev = temp.next;
            temp.next.prev = null;
            length--;
            return;
        }

        while (temp.next != null && temp.next.value != value) {
            temp = temp.next;
        }
        if (temp.next == null)
            return;

        temp.next = temp.next.next;
        if (temp.next != null)
            temp.next.prev = temp.prev;
        else
            next = temp.prev;
        length--;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        ListElement temp = prev;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println("(" + length + ")");
        System.out.println();
    }
}
