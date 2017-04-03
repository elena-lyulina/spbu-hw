package spbu.sem2.hw3.task2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class VyvodilkaTest {
    private int size = 5;
    private int[][] array = {{1, 1, 1, 1, 1}, {1, 2, 2, 2, 1}, {1, 2, 3, 2, 1}, {1, 2, 2, 2, 1}, {1, 1, 1, 1, 1}};
    private String  correctResult = "3 2 2 2 2 2 2 2 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ";


    @Test
    public void FileVyvodilkaTest() throws FileNotFoundException {
        Vyvodilka test = new FileVyvodilka();
        test.printArray(array);

        if (!checkFile()) {
            System.out.println("it doesn't work");
        } else {
            System.out.println("test passed");
        }
    }

    private boolean checkFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("output.out"));
        String result = sc.nextLine();
        return result.equals(correctResult);
    }

}