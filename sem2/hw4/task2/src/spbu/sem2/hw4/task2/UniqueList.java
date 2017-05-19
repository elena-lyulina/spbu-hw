package spbu.sem2.hw4.task2;

/**
 * List which doesn't contain the same elements.
 *
 * @param <Type> type(comparable) of list' elements
 */
public class UniqueList<Type extends Comparable> {

    class ListElement {
        public Type value;
        public ListElement next;
        public int amount;

        public ListElement(Type value, ListElement next) {
            this.next = next;
            this.value = value;
            amount = 1;
        }
    }

    private ListElement head = null;
    private int length = 0;

    /**
     * This function returns size of list.
     * @return size
     */
    public int size() {
        return length;
    }

    /**
     * This function checks whether list is empty.
     * @return true or false depends on list' emptiness
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * This function adds an element to UniqeList
     * @param value value you want to add
     */
    public void add(Type value) {
        ListElement doesExist = findElement(value);
        if (doesExist != null) {
            doesExist.amount++;
            return;
        }

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

    /**
     * This function removes an element from UniqueList.
     *
     * @param value value you want to remove
     * @throws ElementDoesntExist exception in case of removing element which doesn't exist
     */
    public void remove(Type value) throws ElementDoesntExist {
        ListElement doesExist = findElement(value);

        if (doesExist == null) {
            throw new ElementDoesntExist();
        } else if (doesExist.amount >= 2) {
            doesExist.amount--;
            return;
        } else {

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
    }

    /** This function prints list */
    public void printList() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        ListElement temp = head;
        while (temp != null) {
            System.out.print(temp.value + "(" + temp.amount + ") ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * This function returns amount of elements(including the same elements)
     * @return amount of elements
     */
    public int amountOfWords() {
        int amount = 0;
        ListElement temp = head;
        while (temp != null) {
            amount += temp.amount;
            temp = temp.next;
        }
        return amount;
    }

    /**
     * Thos function finds an element with specific value
     * @param value value you want to find element with
     * @return found element
     */
    public ListElement findElement(Type value) {
        ListElement temp = head;
        while (temp != null)
        {
            if (temp.value.compareTo(value) == 0)
            {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * exception in case of removing element which doesn't exist
     */
    public static class ElementDoesntExist extends RuntimeException {
        public ElementDoesntExist() {
            super("This element isn't in the UniqeList");
        }
    }
}


