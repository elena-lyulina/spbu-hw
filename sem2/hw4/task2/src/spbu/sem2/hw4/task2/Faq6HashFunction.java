package spbu.sem2.hw4.task2;

/** FAQ6 hash-function. */
public class Faq6HashFunction implements HashFunction {
    @Override
    public int hash(String stringToHash, int tableSize) {
        int hash = 0;
        for (int i = 0; i < stringToHash.length(); i++) {
            hash += stringToHash.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return Math.abs(hash) % tableSize;
    }
}
