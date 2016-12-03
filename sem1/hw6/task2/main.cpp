#include <iostream>

using namespace std;


int module(int a)
{
    if (a > 0)
        return a;
    else
        return -a;
}

void invert(int number[], int size)
{
    for (int i = 0; i < size; i++)
    {
        if (number[i] == 0)
            number[i] = 1;
        else
            number[i] = 0;
    }
}

void convertToBinary(int number[], int size, int a)
{
    int modA = module(a);
    int i = size - 1;
    while (modA > 0)
    {
        number[i] = modA % 2;
        modA = modA / 2;
        i--;
    }

    if (a < 0)
        invert(number, size);
}

int binarySum(int a, int b, int &carry)
{
    int sum = a + b + carry;
    if (sum == 0)
    {
        carry = 0;
        return 0;
    }
    if (sum == 1)
    {
        carry = 0;
        return 1;
    }
    if (sum == 2)
    {
        carry = 1;
        return 0;
    }
    if (sum == 3)
    {
        carry = 1;
        return 1;
    }
    else {
        return -1;
    }

}

void sum(int number1[], int number2[], int sumOfNumbers[], int size, int carry)
{
    if (carry == 0)
    {
        for (int i = size - 1; i >= 0; i--)
        {
            int bSum = binarySum(number1[i], number2[i], carry);
            sumOfNumbers[i] = bSum;
        }
    }

    if (carry == 1)
    {
        int temp[size] = {0};
        temp[size - 1] = 1;
        sum(temp, sumOfNumbers, sumOfNumbers, size, 0);
    }
}


int main()
{
    int size = 8;

    int number1[size] = {0};
    int number2[size] = {0};
    int sumOfNumbers[size] = {0};

    int a = 0;
    int b = 0;

    cout << "a = ";
    cin >> a;
    cout << "b = ";
    cin >> b;

    convertToBinary(number1, size, a);
    convertToBinary(number2, size, b);

    cout << "a in the binary system:" << endl;

    for (int i = 0; i < size; i++)
        cout << number1[i] << " ";
    cout << endl;

    cout << "b in the binary system:" << endl;

    for (int i = 0; i < size; i++)
        cout << number2[i] << " ";
    cout << endl;

    sum(number1, number2, sumOfNumbers, size, 0);

    cout << "a + b in the binary system:" << endl;

    for (int i = 0; i < size; i++)
        cout << sumOfNumbers[i] << " ";
    cout << endl;

    cout << "a + b = " << a + b;

    return 0;
    
}
