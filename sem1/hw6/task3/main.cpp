#include <iostream>
#include <cstring>

using namespace std;

int powerOfTen(int power)
{
    int ten = 1;
    for (int i = 0; i < power; i++)
        ten = ten * 10;
    return ten;
}

int numberOfNumbers(int a)
{
    int k = 0;
    while (a > 0)
    {
        a = a/10;
        k++;
    }
    return k;
}

int numberOfSpaces(char str[])
{
    int i = 0;
    int number = 0;
    while (str[i] != '\n')
    {
        if(str[i] == ' ')
            number++;
        i++;
    }
    return number;
}


void printNumber(char str1[], char str2[], int &k, int power)
{
    int length = numberOfNumbers(power);
    for (int i = length - 1; i >= 0; i--)
    {
        str1[k + i] = '0' + power % 10;
        str2[k + i] = ' ';
        power /= 10;
    }
    k += length;
}

int main()
{
    char str[1000] = {""};
    char str1[1000] = {""};
    char str2[1000] = {""};

    cout << "Hello! How are you?" << endl;
    cout << "I'm little friendly programm." << endl;
    cout << "I don't have social phobia." << endl;
    cout << "Enter coefficients for your polynomial, please!" << endl;

    fgets(str, 1000, stdin);
    cout << "Nice!" << endl;
    int length = strlen(str);

    int i = 0;
    int k = 0;
    int power = numberOfSpaces(str);

    while (str[i] != '\n')
    {
        if (str[i] != ' ')
        {
            if (!((i == 0 || str[i - 1] == ' ' || str[i - 1] == '-') && str[i] == '1' && str[i + 1] == ' '))
            {
                str1[k] = ' ';
                str2[k] = str[i];
                k++;
            }
            i++;
        }
        else
        {
            str1[k] = ' ';
            str2[k] = 'x';
            k++;

            if (power > 1)
                printNumber(str1, str2, k, power);
            else
            {
                str1[k] = ' ';
                str2[k] = ' ';
                k++;
            }
            power--;

            while (i < length && (str[i] == '0' || str[i] == ' '))
            {
                if (str[i] == '0')
                    power--;
                i++;
            }

            if (i < length - 1)
            {
                str1[k] = ' ';
                if (str[i] == '-')
                {
                    str2[k] = '-';
                    i++;
                }
                else
                    str2[k] = '+';
                k++;
            }

            str1[k] = ' ';
            str2[k] = ' ';
            k++;
        }
    }

    cout << "Your polynomial is ready!" << endl;
    cout << str1 << endl << str2 << endl;
    cout << "Bye!" << endl;
    return 0;
}
