package spbu.sem2.hw4.task2;

/** RS hash-function */
public class RsHashFunction implements HashFunction {
    @Override
    public int hash(String stringToHash, int tableSize) {
        int b = 378551;
        int a = 63689;
        int hash = 0;

        for (int i = 0; i < stringToHash.length(); i++) {
            hash = (hash * a + stringToHash.charAt(i)) % tableSize;
            a = (a * b) % tableSize;
        }
        return hash % tableSize;
    }
}
