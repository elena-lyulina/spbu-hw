#include <iostream>
using namespace std;



bool isCancellable(int a, int b)
{
    if (a != 1){
        if (b % a == 0)
            return true;
        if (b % a != 0)
            return false;
    }
    else
        return false;
}

void fillArrays(double numerator[], double denominator[], int& size, int n)
{
    for (int i = 2; i <= n; i++)
    {
        for (int j = 1; j < i; j++)
        {
            if (isCancellable(j, i) == false)
            {
                numerator[size] = (double) j;
                denominator[size] = (double) i;
                size++;

            }
        }
    }
}

void swap(int *a, int *b)
{
    *a = *a ^ *b;
    *b = *b ^ *a;
    *a = *a ^ *b;
}

void sort(double p[], double q[], int size)
{
    for(int i = 0; i < size - 1; i++)
    {
        for(int j = 0; j < size - i - 1; j++)
        {
            if((p[j] / q[j]) > (p[j + 1] / q[j + 1]))
            {
                swap(p[j], p[j + 1]);
                swap(q[j], q[j + 1]);
            }
        }
    }
}

void output(double p[], double q[], int size)
{
    for (int i = 0; i < size; i++)
        cout << p[i] << "/" << q[i] << " ";
}


int main()
{
    cout << "n = ";
    int n = 0;
    cin >> n;
    int maxSize = ((1 + n) * n) / 2;
    double *numerator = new double[maxSize];
    double *denominator = new double[maxSize];
    int size = 0;

    fillArrays(numerator, denominator, size, n);
    sort(numerator, denominator, size);
    output(numerator, denominator, size);

    delete[] numerator;
    delete[] denominator;
    return 0;
}
