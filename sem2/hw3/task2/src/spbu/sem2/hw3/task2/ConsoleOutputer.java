package spbu.sem2.hw3.task2;

/** outputting on console. */
public class ConsoleOutputer extends Outputer {
    @Override
    public void printArray(int[][] array) {
        int[] result = getResultArray(array);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
    }
}