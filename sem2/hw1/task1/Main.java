package spbu.sem2.hw1.task1;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> Stack = new Stack<Integer>();
        System.out.println("Enter number to add, to stop pushing enter 0");

        int toAdd = sc.nextInt();
        while (toAdd != 0)  {
            Stack.push(toAdd);
            toAdd = sc.nextInt();
        }

        System.out.println("Stack' size is " + Stack.size());

        System.out.println("How many numbers to remove?");
        int toRemove = sc.nextInt();
        for (int i = 0; i < toRemove; i++)
        {
            if (!Stack.isEmpty())
                System.out.println(Stack.pop() + " just removed");
        }
    }
}
