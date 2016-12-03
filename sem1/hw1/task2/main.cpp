#include <iostream>
using namespace std;

int main( )
{
    int a = 0;
    int b = 0;
    cin >> a >> b;
    if (b == 0)
    {
        cout << "Error: b = 0";
        return 1;
    }
    int partialQuotient = 0;
    int sign = a * b;
    a = abs(a);
    b = abs(b);
    while (a >= b)
    {
        a = a - b;
        partialQuotient++;
    }
    cout << partialQuotient * sign / abs(sign) << endl;
    return 0;
}
