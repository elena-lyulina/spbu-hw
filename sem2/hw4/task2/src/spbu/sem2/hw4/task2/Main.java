package spbu.sem2.hw4.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static HashTable ht;
    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
       // Scanner fileScanner = new Scanner(new File("input.txt"));
        sc = new Scanner(System.in);

        System.out.println("Hello!");
        System.out.println("choose hash function:");
        System.out.println("press 1 to choose rs-function");
        System.out.println("press 2 to choose FAQ6-function");

        int hashFunction = sc.nextInt();
        ht = new HashTable(hashFunction);

        System.out.println("press 'a' to add word to hashtable");
        System.out.println("press 'r' to remove word from hashtable");
        System.out.println("press 'f' to find word in hashtable");
        System.out.println("press 'i' to get information about hashtable");
        System.out.println("press 'e' to exit");

        String next = sc.next();
        while (!next.equals("e")) {
            switch (next) {
                case "a":
                    addToHT();
                    break;
                case "r":
                    removeFromHT();
                    break;
                case "f":
                    findWord();
                    break;
                case "i":
                    ht.getInformation();
                    break;
            }
            next = sc.next();
        }

        System.out.println("Bye!");
    }

    public static void addToHT() {
        System.out.print("word to add: ");
        String wordToAdd = sc.next();
        ht.add(wordToAdd);
    }

    public static void removeFromHT() {
        System.out.print("word to remove: ");
        String wordToRemove = sc.next();
        ht.remove(wordToRemove);
    }

    public static void findWord() {
        System.out.print("word to find: ");
        String wordToFind = sc.next();
        ht.findWord(wordToFind);
    }
}
