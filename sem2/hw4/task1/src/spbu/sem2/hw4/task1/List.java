package spbu.sem2.hw4.task1;

/**
 * interface for structure List which works with any comparable types.
 *
 * @param <Type> type of list' elements
 */
public interface List<Type> {
    /**
     * this function adds a value to List.
     *
     * @param value value you want to add
     */
    public void add(Type value);

    /**
     * this function removes a value from List.
     *
     * @param value value you want to remove
     */
    public void remove(Type value);

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

    /** this function print List. */
    public void print();
}
