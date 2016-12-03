#include <iostream>
using namespace std;


long double recursiveFactorial(int a)
{
    return a * recursiveFactorial(a - 1);
}


long double iterativeFactorial (int a)
{
    int result = 1;
    for (int i = 1; i <= a; i++)
    {
        result = result * i;
    }
    return result;
}

int main()
{
    cout << "a = ";
    int a = 0;
    cin >> a;
    cout << "for recursive calculation enter 1, for iterative calculation enter 2" <<  endl;
    int enter = 0;
    cin >> enter;

    if (a > 0)
    {
        if (enter == 1)
        {
            cout << "a! = " << recursiveFactorial(a);
        }
        if (enter == 2)
        {
            cout << "a! = " << iterativeFactorial(a);
        }
    }
    else
    {
        if (a == 0)
        {
            cout << "a! = 1";
        }
        if (a < 0)
        {
            cout << "a! = 0";
        }
    }
    return 0;

}
