package spbu.sem2.hw3.task2;

import java.io.PrintWriter;
import java.io.*;

public class FileVyvodilka implements Vyvodilka {
    @Override
    public void printArray(int[][] array) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File("output.out"));

        int CENTER_COORD = (array.length - 1) / 2;

        out.print(array[CENTER_COORD][CENTER_COORD] + " ");

        for (int i = CENTER_COORD; i > 0; i--) {
            printCircle(out, array, i, i, (CENTER_COORD - i + 1) * 2);
        }
        out.close();
    }


    private void printCircle(PrintWriter out, int[][] array, int x, int y, int size) {
        y--;
        out.print(array[y][x] + " ");

        for (int i = 0; i < size - 1; i++) {
            x++;
            out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            y++;
            out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            x--;
            out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            y--;
            out.print(array[y][x] + " ");
        }
    }
}



