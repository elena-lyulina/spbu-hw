package spbu.sem2.hw4.task2;

/**Interface for hash-functions. */
public interface HashFunction {
    /**
     * This function gets hash of string.
     * @param string string you want to hash
     * @param tableSize size of hashtable
     * @return hash of this string
     */
    int hash(String string, int tableSize);
}
