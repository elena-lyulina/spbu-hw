package spbu.sem2.hw4.task2;

/** Class that describes the structure of HashTable. */
public class HashTable implements HashTableInterface {
    private static int tableSize = 1000;
    private UniqueList<String>[] list;
    private HashFunction hashFunction;

    public HashTable(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        list = (UniqueList<String>[]) new UniqueList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            list[i] = new UniqueList<>();
        }
    }

    /**
     * This function gets hash of string
     * @param string string you want to hash
     * @return hash of this string
     */
    private int hash(String string) {
        return hashFunction.hash(string, tableSize);
    }

    public void add(String string) {
        int index = hash(string);
        list[index].add(string);
    }

    public void remove(String string) throws ElementDidntFind {
        int index = hash(string);
        if (list[index].findElement(string) == null)
            throw new ElementDidntFind();
        else
            list[index].remove(string);
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

    public boolean findWord(String word) throws ElementDidntFind{
        int index = hash(word);
        UniqueList.ListElement temp = list[index].findElement(word);
        if (temp == null) throw new ElementDidntFind();
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

    /**
     * exception in case of trying to find an element which doesn't exist
     */
    public static class ElementDidntFind extends RuntimeException {
        public ElementDidntFind() {
            super("This element didn't find");
        }
    }
}