package spbu.sem2.hw3.task1;

public class GnomeSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        int i = 1;
        int h = 2;
        while (i < arr.length) {
            if (arr[i - 1] < arr[i]) {
                i = h;
                h++;
            } else {
                int temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
                i--;
                if (i == 0) {
                    i = h;
                    h++;
                }
            }
        }

    }
}
