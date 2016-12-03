#include <iostream>
using namespace std;

long recursiveFibonacci(int n)
{
    if (n == 0)
        return 0;
    else
        if  (n == 1)
            return 1;
        else
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
}

long iterativeFibonacci(int n)
{
    int first = 0;
    int second = 1;
    for (int i = 0; i < n; i++)
    {
        int temp = first + second;
        first = second;
        second = temp;
    }
    return first;


}

int main()
{
    cout << "n = ";
    int n = 0;
    cin >> n;

    cout << "for recursive calculation enter 1, for iterative calculation enter 2" <<  endl;
    int enter = 0;
    cin >> enter;


    if (enter == 1)
    {
        cout << "Fibonacci number is " << recursiveFibonacci(n);
    }
    if (enter == 2)
    {
        cout << "Fibonacci number is " << iterativeFibonacci(n);
    }


    return 0;

}
