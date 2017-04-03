package spbu.sem2.hw1.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List List = new List();
        System.out.println("Enter element to add, to stop adding enter 0");

        int toAdd = sc.nextInt();
        while (toAdd != 0) {
            List.add(toAdd);
            List.print();
            toAdd = sc.nextInt();
        }

        System.out.println("Enter element to remove, to stop removing enter 0");

        int toRemove = sc.nextInt();
        while (toRemove != 0) {
            List.remove(toRemove);
            List.print();
            toRemove = sc.nextInt();
        }
    }
}
