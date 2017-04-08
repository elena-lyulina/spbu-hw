package spbu.sem2.tests.test1;

/**
 * Priority queue.
 *
 * @param <Type> type of queue's elements
 */
public class PriorityQueue<Type> implements Queue<Type> {
    /** Class that creates a QueueElement. */
    class QueueElement {
        public Type value;
        public QueueElement next;
        public int priority;

        public QueueElement(Type value, QueueElement next, int priority) {
            this.next = next;
            this.value = value;
            this.priority = priority;
        }
    }

    private QueueElement head = null;
    private int length = 0;


    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void enqueue(Type value, int priority) {
        if (isEmpty()) {
            head = new QueueElement(value, head, priority);
            length++;
            return;
        }
        if (head.priority > priority) {
            QueueElement temp = head;
            head = new QueueElement(value, temp, priority);
            length++;
            return;
        }

        QueueElement temp = head;
        while (temp.next != null && temp.next.priority < priority) {
            temp = temp.next;
        }
        temp.next = new QueueElement(value, temp.next, priority);
        length++;
    }

    public Type dequeue() throws QueueIsEmpty {
        if(isEmpty()) {
            throw new QueueIsEmpty();
        }
        if(length == 1)
        {
            Type value = head.value;
            head = null;
            length--;
            return value;
        }
        QueueElement temp = head;
        while (temp.next.next != null)
            temp = temp.next;
        Type value = temp.next.value;
        temp.next = null;
        length--;
        return value;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        QueueElement temp = head;
        while (temp != null) {
            System.out.print(temp.value + " (" + temp.priority + ") " );
            temp = temp.next;
        }
        System.out.println();
    }

    /**exception in case of removing element from empty queue */
    public static class QueueIsEmpty extends RuntimeException {
        public QueueIsEmpty() {
            super("Queue is empty");
        }
    }
}

