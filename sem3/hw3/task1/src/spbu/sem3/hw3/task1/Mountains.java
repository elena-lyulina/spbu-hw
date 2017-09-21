package spbu.sem3.hw3.task1;

/** Class for mountains. */
public class Mountains {
    public int n = 12;
    public int[][] coord = new int[2][n];
    public int mountLength = 100;

/** This function initializes coordinates of mountains with random numbers. */
    public void initialize() {
        coord[0][0] = 0;
        coord[1][0] = 200;
        for (int i = 1; i < n; i++) {
            coord[1][i] = (int) (Math.random() * 350 + 100);
            coord[0][i] = i * mountLength;
        }
    }
}
