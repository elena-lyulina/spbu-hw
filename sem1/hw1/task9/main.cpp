#include <iostream>
using namespace std;

int main()
{
    int a = 0;
    cout << "a = ";
    cin >> a;

    int n = 0;
    cout << "n = ";
    cin >> n;

    int power = 1;

    for (int i = 0; i < n; i++)
    {
        power = power * a;
    }

    cout << "a^n = " << power;
    return 0;
}
