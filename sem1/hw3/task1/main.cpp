#include <iostream>
using namespace std;


long involute(int a, int power)
{
    long result = 1;
    if (power == 0)
    {
        return result;
    }
    else
    {
        if (power % 2 == 0)
        {
            result = involute(a * a, power / 2);
        }
        else
        {
            result = a * involute(a, power - 1);
        }
    }
    return result;
}

int main()
{
    int a = 0;
    int power = 0;
    cout << "a = ";
    cin >> a;
    cout << "power = ";
    cin >> power;

    cout << a << "^" << power << " = " << involute(a, power);

    return 0;
}
