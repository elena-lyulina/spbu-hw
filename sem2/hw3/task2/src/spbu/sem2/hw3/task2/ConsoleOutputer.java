package spbu.sem2.hw3.task2;

/**
 * outputting on console.
 */
public class ConsoleOutputer implements Outputer {
    @Override
    public void printArray(int[][] array) {
        GoRound goRound = new GoRound();
        int[] result = goRound.getResultArray(array);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
    }
}