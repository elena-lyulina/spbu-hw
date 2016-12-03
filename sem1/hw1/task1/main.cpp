#include <iostream>

using namespace std;

int main()
{
    int x = 0;
    cout << "x = ";
    cin >> x;
    int squareX = x * x;
    int firstMultiplier = squareX + x;
    int secondMultiplier = squareX + 1;
    int result = firstMultiplier * secondMultiplier + 1;
    cout << "x^4 + x^3 + x^2 + x + 1 = " << result << endl;
    return 0;
}

