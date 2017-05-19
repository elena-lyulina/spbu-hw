package spbu.sem2.tests.test2;

import javafx.scene.control.Button;

import java.util.Random;

public class Game {
    static int N = 2;
    static int n = N * 2;
    public static Button[][] buttons = new Button[n][n];
    public static int[][] open = new int[n][n];
    public static int[][] value = new int[n][n];

    public static void initialize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                value[i][j] = 0;
            }
        }

        int counter = 0;
        while (counter < n * n / 2) {
            int x = getX();
            int y = getX();
            System.out.println(x + " " + y);
            while (value[x][y] != 0) {
                x = getX();
                y = getX();
            }
            value[x][y] = 1;
            counter++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (value[i][j] != 1)
                value[i][j] = 2;
            }
        }
    }

    public static int getX() {
        Random r = new Random();
        int x = (int) (Math.random() * n);
        return x;
    }

    public static int getY() {
        Random r = new Random();
        int y = (int) (Math.random() * ++n);
        return y;
    }
}
