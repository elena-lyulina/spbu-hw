package spbu.sem2.hw4.task2;

/** Interface for structure HashTable. */
public interface HashTableInterface {
    /**
     * This function adds string-type element to hashtable.
     * @param word word you want to add
     */
    void add(String word);

    /**
     * This function removes word from hashtable.
     * @param word word you want to remove
     */
    void remove(String word);

    /** This function prints informtion about hashtable. */
    void getInformation();

    /**
     This function finds word in hashtable.
     * @param word word you wsnt to find
     * @return true of false depends on result
     */
    boolean findWord(String word);

    /**
     * This function checks whether hashtable is empty.
     * @return true or false depends in result
     */
    boolean isEmpty();
}
