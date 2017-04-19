package spbu.sem2.hw3.task2;

import java.io.PrintWriter;
import java.io.*;

/** outputting in file. */
public class FileOutputer extends Outputer {
    @Override
    public void printArray(int[][] array) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File("output.out"));
        int[] result = getResultArray(array);
        for (int i = 0; i < result.length; i++)
            out.print(result[i] + " ");
        out.close();
    }
}