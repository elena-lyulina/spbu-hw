package spbu.sem2.tests.test1;

/**
 * inteface for priority queue.
 *
 * @param <Type> type of queue's elements
 */
public interface Queue<Type> {
    /**
     * This function adds an element to queue depending on its priority.
     *
     * @param value value of element you want to add
     * @param priority priority of element you want to add
     */
    public void enqueue(Type value, int priority);

    /**
     * This function removes the element with the highest priority.
     *
     * @return value of removed element
     * @throws PriorityQueue.QueueIsEmpty exeption in case of removing from empty queue
     */
    public Type dequeue() throws PriorityQueue.QueueIsEmpty;

    /** This function print value and its priority, which are contained in queue.  */
    public void print();

    /**
     * This function check if queue is empty.
     *
     * @return true if queue if empty, false if isnt
     */
    public boolean isEmpty();

    /**
     * This function returns size of queue.
     *
     * @return size of queue
     */
    public int size();
}
