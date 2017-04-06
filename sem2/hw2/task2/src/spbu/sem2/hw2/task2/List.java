package spbu.sem2.hw2.task2;

/**
 * interface for structure List.
 */
public interface List {
    /**
     * this function adds a value to List.
     *
     * @param value value you want to add
     */
    public void add(int value);

    /**
     * this function removes a value from List.
     *
     * @param value value you want to remove
     */
    public void remove(int value);

    /**
     * this function returns size of List.
     *
     * @return size of List
     */
    public int size();

    /**
     * check whether List is empty.
     *
     * @return true if List is empty, false if isn't
     */
    public boolean isEmpty();

    /**
     * this function print List.
     */
    public void print();
}
