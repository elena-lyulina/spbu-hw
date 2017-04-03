package spbu.sem2.hw3.task2;


public class ConsoleVyvodilka implements Vyvodilka {
    @Override
    public void printArray(int[][] array) {
        int CENTER_COORD = (array.length - 1)/ 2;

        System.out.print(array[CENTER_COORD][CENTER_COORD] + " ");

        for (int i = CENTER_COORD; i > 0; i--) {
            printCircle(array, i, i, (CENTER_COORD - i + 1) * 2);
        }
    }


    private void printCircle(int[][] array, int x, int y, int size) {
         y--;
        System.out.print(array[y][x] + " ");

        for (int i = 0; i < size - 1; i++) {
            x++;
            System.out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            y++;
            System.out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            x--;
            System.out.print(array[y][x] + " ");
        }

        for (int i = 0; i < size; i++) {
            y--;
            System.out.print(array[y][x] + " ");
        }
    }
}
