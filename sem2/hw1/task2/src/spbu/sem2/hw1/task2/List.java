package spbu.sem2.hw1.task2;

public class List {
    private class ListElement {
        public int value;
        public ListElement next;

        public ListElement(int value, ListElement next) {
            this.next = next;
            this.value = value;
        }
    }

    private ListElement head = null;
    private int length = 0;

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(int value) {
        if (isEmpty()) {
            head = new ListElement(value, head);
            length++;
            return;
        }
        if (head.value > value) {
            ListElement temp = head;
            head = new ListElement(value, temp);
            length++;
            return;
        }

        ListElement temp = head;
        while (temp.next != null && temp.next.value < value) {
            temp = temp.next;
        }
        temp.next = new ListElement(value, temp.next);
        length++;
    }

    public void remove(int value) {
        if (isEmpty())
            return;

        ListElement temp = head;
        if (temp.next == null && temp.value == value) {
            head = null;
            length--;
            return;
        }

        if (temp.value == value) {
            head = temp.next;
            length--;
            return;
        }

        while (temp.next != null && temp.next.value != value) {
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
