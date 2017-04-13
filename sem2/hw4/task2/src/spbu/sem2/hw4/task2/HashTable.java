package spbu.sem2.hw4.task2;

/** Class that describes the structure of HashTable. */
public class HashTable implements HashTableInterface {
    private static int tableSize = 1000;
    private UniqueList<String>[] list;
    private int hash;

    public HashTable(int hashFunction) {
        hash = hashFunction;
        list = (UniqueList<String>[]) new UniqueList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            list[i] = new UniqueList<>();
        }
    }

    private int hash(String string) {
        if (hash == 1)
            return hashRS(string);
        else
            return hashFAQ6(string);
    }

    /**
     * HashFunction number one.
     * @param stringToHash string you want to hash
     * @return hash of this string
     */
    private int hashRS(String stringToHash) {
        int b = 378551;
        int a = 63689;
        int hash = 0;

        for (int i = 0; i < stringToHash.length(); i++) {
            hash = (hash * a + stringToHash.charAt(i)) % tableSize;
            a = (a * b) % tableSize;
        }
        return hash % tableSize;
    }

    /**
     * HashFunction number two.
     * @param stringToHash string you want to hash
     * @return hash of this string
     */
    private int hashFAQ6(String stringToHash) {
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

    public void add(String string) {
        int index = hash(string);
        list[index].add(string);
    }

    public void remove(String string) {
        int index = hash(string);
        if (list[index].findElement(string) != null)
            list[index].remove(string);
        else {
            System.out.println("remove element: not found");
        }
    }

    public void getInformation() {
        int sizeOfHt = 0;
        double loadFactor = 0;
        int emptyLists = 0;
        double averageListSize = 0;
        int maxSize = 0;
        int indexOfMaxList = 0;
        int numberOfWords = 0;

        for (int i = 0; i < tableSize; i++) {
            sizeOfHt += list[i].size();
            numberOfWords += list[i].amountOfWords();
            if (list[i].isEmpty())
                emptyLists++;

            if (list[i].size() > maxSize) {
                maxSize = list[i].size();
                indexOfMaxList = i;
            }
        }

        loadFactor = Math.round((double) sizeOfHt * 100.0 / tableSize ) / 100.0;
        averageListSize = Math.round((double) sizeOfHt * 100.0  / (tableSize - emptyLists)) / 100.0;

        System.out.println("number of words: " + numberOfWords);
        System.out.println("load factor: " + loadFactor);
        System.out.println("amount of empty lists: " + emptyLists);
        System.out.println("average list' size: " + averageListSize);
        System.out.println("max list' size: " + maxSize);
        System.out.println("max list: ");
        list[indexOfMaxList].printList();
    }

    public boolean findWord(String word) {
        int index = hash(word);
        UniqueList.ListElement temp = list[index].findElement(word);
        if (temp == null) {
            System.out.println("not found: " + word);
            return false;
        }
        else {
            System.out.println("found: " + temp.value + " (" + temp.amount + ")");
            return true;
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < tableSize; i++) {
            if (!list[i].isEmpty()) return false;
        }
        return true;
    }
}