#include <iostream>
using namespace std;


int main()
{
    cout << "n = ";
    int n = 0;
    cin >> n;
    int size = n + 1;
    int *allNumbers = new int[size];
    for (int i = 0; i < size; i++)
    {
        allNumbers[i] = 1;
    }
    cout << "prime numbers:" << endl;
    for (int i = 2; i * i <= size; i++)
    {
        if (allNumbers[i] == 1)
        {
            for (int j = i * i; j <= n; j++)
            {
                if (j % i == 0)
                {
                    allNumbers[j] = 0;
                }
            }
        }
    }
    for (int i = 0; i < size; i++){
        if (allNumbers[i] == 1)
        {
            cout << i << endl;
        }
    }
    delete[] allNumbers;
    return 0;
}
