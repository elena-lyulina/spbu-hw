#include <iostream>
using namespace std;

void inputArray(int a[], int n)
{
    cout << "enter " << n << " elements of array" << endl;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
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

bool doesMaxElementExist(int array[], int size, int &maxElement)
{
    bool doesExist = false;
    for (int i = size - 1; i > 0; i--)
    {
        if (array[i] == array[i - 1])
        {
            doesExist = true;
            maxElement = array[i];
        }
    }
    return doesExist;
}

int main()
{
    int size = 0;
    cout << "size = ";
    cin >> size;
    int *array = new int[size];

    inputArray(array, size);
    quickSort(array, 0, size - 1);
    int maxElement = 0;
    if (doesMaxElementExist(array, size, maxElement))
        cout << maxElement;
    else
        cout << "it doesn't exist";

    delete[] array;
    return 0;
}
