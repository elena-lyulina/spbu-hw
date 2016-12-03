#include <iostream>
using namespace std;

void inputArray(int a[], int n)
{
    cout << "enter " << n << " letters" << endl;
    for (int i = 0; i < n; i++)
    {
        char ch;
        cin >> ch;
        a[i] = (int)ch;
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

bool areStringsSimilar(int string1[], int size1, int string2[], int size2)
{
    if (size1 != size2)
        return false;
    else
    {
        bool areStringsSimilar = true;
        for (int i = 0; i < size1; i++)
        {
            if (string1[i] != string2[i])
            {
                areStringsSimilar = false;
            }
        }
        return areStringsSimilar;
    }
}

int main()
{
    int size1 = 0;
    cout << "enter size of first string" << endl;
    cin >> size1;
    int *string1 = new int[size1];
    inputArray(string1, size1);

    int size2 = 0;
    cout << "enter size of second string" << endl;
    cin >> size2;
    int *string2 = new int[size2];
    inputArray(string2, size2);

    quickSort(string1, 0, size1 - 1);
    quickSort(string2, 0, size2 - 1);

    if(areStringsSimilar(string1, size1, string2, size2))
        cout << "YES" << endl;
    else
        cout << "NO" << endl;

    delete[] string1;
    delete[] string2;
    return 0;
}
