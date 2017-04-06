package spbu.sem2.hw4.task1;

/**
 *List which doesn't contain the same elements.
 *
 * @param <Type> type(comparable) of list' elements
 */
public class UniqeList<Type extends Comparable> extends LinkedList<Type> implements List<Type> {

    /** UniqeList' Constructor. */
    public UniqeList() {
        super();
    }

    /**
     * This function add an element to UniqeList.
     *
     * @param value value you want to add
     * @throws ElementDoesExist exception in case of adding element which already exists
     */
   @Override
    public void add(Type value) throws ElementDoesExist {
        if (doesExist(value)) {
            throw new ElementDoesExist();
        }
        super.add(value);
    }

    /**
     * This function remove an element to UniqeList.
     * @param value value you want to remove
     * @throws ElementDoesntExist exception in case of removing element which doesn't exist
     */
    @Override
    public void remove(Type value) throws ElementDoesntExist {
       if (!doesExist(value)) {
            throw new ElementDoesntExist();
       }
       super.remove(value);
    }

    /**
     * This function check existence of element in UniqeList.
     *
     * @param value element you want to check
     * @return true if element does exist, false if doesn't
     */
    private boolean doesExist(Type value) {
       if (super.isEmpty())
           return false;
       ListElement temp = super.head;
       while (temp != null) {
           if (temp.value.compareTo(value) == 0)
               return true;
           temp = temp.next;
       }
       return false;
    }

    /** exception in case of adding element which already exists. */
    public static class ElementDoesExist extends RuntimeException {
        public ElementDoesExist() {
            super("This element is in the UniqeList");
        }
    }
    /**exception in case of removing element which doesn't exist */
    public static class ElementDoesntExist extends RuntimeException {
        public ElementDoesntExist() {
            super("This element isn't in the UniqeList");
        }
    }
}
