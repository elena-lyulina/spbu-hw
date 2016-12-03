#include <iostream>
using namespace std;

void fillArray(int array[], int size, int content)
{
    for (int i = 0; i < size; i++)
        array[i] = content;
}

void outputArray(int array[], int size)
{
    for (int i = 0; i < size; i++)
        cout << array[i] << " ";
    cout << endl;
}


void decomposedIntoTerms(int n, int k, int terms[], int termsNumber)
{
    if (n >= 0)
    {
        if (n == 0)
        {
            outputArray(terms, termsNumber);
        }
        else {
           if (n - k >= 0)
           {
               terms[termsNumber] = k;
               decomposedIntoTerms(n - k, k, terms, termsNumber + 1);
           }
           if (k > 1)
           {
               decomposedIntoTerms(n, k - 1, terms, termsNumber);
           }
        }

    }
}

int main()
{
    cout << "enter n" << endl;
    int n = 0;
    cin >> n;
    int *terms = new int[n];
    fillArray(terms, n, 0);

    decomposedIntoTerms(n, n - 1, terms, 0);

    delete[] terms;
    return 0;
}
