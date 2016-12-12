#include <iostream>
#include "stdio.h"
#include <fstream>

using namespace std;

int size(char str[])
{
    int size = 0;
    while (str[size] != '\0')
        size++;
    return size;
}

bool isSymbol(char ch)
{
    return !(ch == ' ' || ch == '\t');

}

bool isEmpty(char str[])
{
    if (size(str) == 0)
        return true;
    for (int i = 0; i < size(str); i++)
    {
        if (isSymbol(str[i]))
            return false;
    }
    return true;
}

int main()
{
    ifstream f("file.txt");
    char str[1000] = {""};
    int number = 0;

    if (!f.is_open())
        cout << "File can't be opened";
    else
    {
        while (!f.eof())
        {
            f.getline(str, 1000);

            if (!isEmpty(str))
                number++;
        }
    }

    cout << number;

    f.close();
    return 0;

}
