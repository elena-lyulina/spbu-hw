package spbu.sem2.hw3.task2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter array' size");
        int n = sc.nextInt();
        int array[][] = new int[n][n];

        System.out.println("enter array");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = sc.nextInt();
            }
        }

        System.out.println("enter 0 for using console, 1 for using file");
        int fileOrConsole = sc.nextInt();

        if (fileOrConsole == 0) {
            Outputer outputer = new ConsoleOutputer();
            outputer.printArray(array);
        } else {
            Outputer outputer = new FileOutputer();
            outputer.printArray(array);
        }
    }
}
