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

void outputArray(int a[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << " ";
    }
}

void swap(int *a, int *b)
{
    int t = *a;
    *a = *b;
    *b = t;

}

void makeHeap (int array[], int k, int n)
{
    int maxChild = 2 * k + 1;
    while (maxChild < n)
    {
        if (maxChild < n - 1 && array[maxChild + 1] >= array[maxChild])
        {
            maxChild++;
        }
        if (array[k] < array[maxChild])
        {
            swap(array[k], array[maxChild]);
            k = maxChild;
            maxChild = 2 * k + 1;
        }
        else break;
    }
}



void heapSort(int array[], int size)
{
    for (int i = size - 1; i >= 0; i--)
    {
        makeHeap(array, i, size);
    }
        while (size > 0)
        {
            swap(array[0], array[size - 1]);
            size--;
            makeHeap(array, 0, size);
        }

}


int main()
{
    int size = 0;
    cout << "size = ";
    cin >> size;
    int *array = new int[size];

    inputArray(array, size);
    heapSort(array, size);
    outputArray(array, size);

    delete[] array;
    return 0;
}
