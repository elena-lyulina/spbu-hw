package spbu.sem2.hw3.task2;

import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;


public class OutputerTest {
    int[][] array = {{1, 1, 1, 1, 1}, {1, 2, 2, 2, 1}, {1, 2, 3, 2, 1}, {1, 2, 2, 2, 1}, {1, 1, 1, 1, 1}};
    String correctResult = "3 2 2 2 2 2 2 2 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 ";

    @Test
    public void FileOutputerTest() throws Exception {
        Outputer fileOutputer = new FileOutputer();
        fileOutputer.printArray(array);
        Scanner in = new Scanner(new File("output.out"));
        String result = in.nextLine();
        Assert.assertTrue(result.equals(correctResult));
    }

    @Test
    public void testConsoleOutput() throws FileNotFoundException {
        Outputer concoleOutputer = new ConsoleOutputer();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream console = System.out;

        System.setOut(printStream);

        concoleOutputer.printArray(array);

        System.out.flush();
        System.setOut(console);

        Assert.assertTrue(baos.toString().equals(correctResult));
    }
}