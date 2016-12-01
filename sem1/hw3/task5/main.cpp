#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

void fillArray(int array[], int size)
{
       for (int i = 0; i < size; i++)
       {
           array[i] = rand();
           cout << array[i] << " ";
       }
       cout << endl;
}

void swap(int *a, int *b)
{
    *a = *a ^ *b;
    *b = *b ^ *a;
    *a = *a ^ *b;
}

void quickSort(int array[], int begin, int end)
{
    int pivot = array[(begin + end) / 2];
    int left = begin;
    int right = end;
    while (left <= right)
    {
        while (array[left] < pivot)
            left++;
        while (array[right] > pivot)
            right--;
        if (left <= right)
        {
            swap(array[left], array[right]);
            left++;
            right--;
        }
    }
    if (left < end)
        quickSort(array, left, end);

    if (begin < right)
        quickSort(array, begin, right);
}

bool binarySearch(int array[], int size, int number)
{
    int left = 0;
    int right = size;
    while (left <= right)
    {
        int mid = (left + right) / 2;
        if (number < array[mid])
            right = mid - 1;
        if (number > array[mid])
            left = mid + 1;
        if (number == array[mid])
            return true;
    }
    return false;
}




int main()
{
    int n = 0;
    cout << "enter n" << endl;
    cin >> n;
    int k = 0;
    cout << "enter k" << endl;
    cin >> k;

    int *array = new int[n];
    int *numbers = new int[k];

    srand(time(NULL));
    fillArray(array, n);
    fillArray(numbers, k);

    quickSort(array, 0, n - 1);

    for (int i = 0; i < k; i++)
    {
        if (binarySearch(array, n, numbers[i]) == true)
            cout << "There is number " << numbers[i] << endl;
        if (binarySearch(array, n, numbers[i]) == false)
            cout << "There isn't number " << numbers[i] << endl;
    }

    delete[] array;
    delete[] numbers;
    return 0;
}
