#include <iostream>
using namespace std;

void turn( int a[], int begin, int end)
{
    for (int i = begin; i < (end + begin) / 2; i++)
    {
        int temp = a[i];
        a[i] = a[end - i + begin - 1];
        a[end - i + begin - 1] = temp;
    }
}

int main()
{
    int m = 0;
    int n = 0;
    cout << "m = ";
    cin >> m;
    cout << "n = ";
    cin >> n;
    int *x = new int[n + m];
    cout << "enter the beginning of the array " << endl;
    for (int i = 0; i < m; i++)
    {
        cin >> x[i];
    }
    cout << "enter the end of the array " << endl;
    for (int i = m; i < m + n; i++)
    {
        cin >> x[i];
    }
    turn(x, 0, m);
    turn(x, m, n + m);
    turn(x, 0, m + n);
    for(int i = 0; i < m + n; i++){
        cout << x[i] << " ";
    }
    delete [] x;
    return 0;
}
