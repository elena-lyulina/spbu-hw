#include <iostream>
using namespace std;

void inputArray(int a[], int n)
{
    cout << "enter " << n << " numbers" << endl;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
}

void outputArray(int a[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << " ";
    }
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

void doMinimumNumber(int number[], int size)
{
    quickSort(number, 0, size - 1);
    if (number[0] == 0)
    {
        int k = 0;
        while (number[k] == 0)
            k++;
        swap(number[k], number[0]);
    }
}

int main()
{
    int size = 0;
    cout << "enter size of number" << endl;
    cin >> size;
    int *number = new int[size];
    inputArray(number, size);

    doMinimumNumber(number, size);

    outputArray(number, size);

    delete[] number;
    return 0;
}
