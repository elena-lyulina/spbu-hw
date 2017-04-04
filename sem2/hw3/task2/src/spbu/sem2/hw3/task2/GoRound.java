package spbu.sem2.hw3.task2;

/**
 * class that goes an array round and creates array with answer.
 */
public class GoRound {
    private int counter = 0;

    /**
     * this function goes an array round and creates array with answer.
     *
     * @param array array you want to go round
     * @return array with array's elements in correct older
     */
    public int[] getResultArray(int[][] array) {
        int size = array.length * array.length;
        int[] result = new int[size];
        int centerCoord = (array.length - 1) / 2;
        result[counter] = array[centerCoord][centerCoord];
        counter++;

        for (int i = centerCoord; i > 0; i--)
            goCircle(array, i, result);

        return result;
    }

    /**
     * this function does one sircle in array and adds elements in result array.
     *
     * @param array  array you want to go round
     * @param i      coordinate of initial element
     * @param result array with elements in correct order
     */
    private void goCircle(int[][] array, int i, int[] result) {
        int centerCoord = (array.length - 1) / 2;
        int size = (centerCoord - i + 1) * 2;
        int x = i;
        int y = i;
        y--;
        result[counter] = array[y][x];
        counter++;

        for (int j = 0; j < size - 1; j++) {
            x++;
            result[counter] = array[y][x];
            counter++;
        }

        for (int j = 0; j < size; j++) {
            y++;
            result[counter] = array[y][x];
            counter++;
        }

        for (int j = 0; j < size; j++) {
            x--;
            result[counter] = array[y][x];
            counter++;
        }

        for (int j = 0; j < size; j++) {
            y--;
            result[counter] = array[y][x];
            counter++;
        }
    }
}
