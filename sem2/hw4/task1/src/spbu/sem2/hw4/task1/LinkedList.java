package spbu.sem2.hw4.task1;

/**
 * one-linked list which works with any comparable types.
 *
 * @param <Type> type of list' elements
 */
public class LinkedList<Type extends Comparable> implements List<Type> {

    /** Class that creates a ListElement. */
    class ListElement {
        public Type value;
        public ListElement next;

        public ListElement(Type value, ListElement next) {
            this.next = next;
            this.value = value;
        }
    }

    protected ListElement head = null;
    protected int length = 0;

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(Type value) {
        if (isEmpty()) {
            head = new ListElement(value, head);
            length++;
            return;
        }
        if (head.value.compareTo(value) > 0) {
            ListElement temp = head;
            head = new ListElement(value, temp);
            length++;
            return;
        }

        ListElement temp = head;
        while (temp.next != null && temp.next.value.compareTo(value) < 0) {
            temp = temp.next;
        }
        temp.next = new ListElement(value, temp.next);
        length++;
    }

    public void remove(Type value) {
        if (isEmpty())
            return;

        ListElement temp = head;
        if (temp.next == null && temp.value.compareTo(value) == 0) {
            head = null;
            length--;
            return;
        }

        if (temp.value.compareTo(value) == 0) {
            head = temp.next;
            length--;
            return;
        }

        while (temp.next != null && temp.next.value.compareTo(value) != 0) {
            temp = temp.next;
        }
        if (temp.next == null)
            return;

        ListElement toRemove = temp.next;
        temp.next = temp.next.next;
        length--;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        ListElement temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
