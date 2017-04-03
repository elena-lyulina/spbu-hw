package spbu.sem2.hw3.task1;

public class CocktailSort implements Sorting {
    @Override
    public void sort(int[] arr) {
        for (int j = 0; j < (arr.length / 2) - 1; j++) {
            int flag = 0;
            for (int w = 0; w < arr.length - j - 1; w++) {
                if (arr[w] > arr[w + 1]) {
                    int d = arr[w];
                    arr[w] = arr[w + 1];
                    arr[w + 1] = d;
                    flag = 1;
                }
            }
            for (int w = arr.length - j - 1; w > 0; w--) {
                if (arr[w] < arr[w - 1]) {
                    int d = arr[w];
                    arr[w] = arr[w - 1];
                    arr[w - 1] = d;
                    flag = 1;
                }
                if (flag == 0) {
                    break;
                }
            }
        }

    }
}
