#include <iostream>
#include <fstream>
#include "stdio.h"
using namespace std;

bool isLetter(char ch)
{
    return (ch >= 'a') && (ch <= 'z');
}

void clean(int arr[], int size)
{
    for (int i = 0; i < size; i++)
        arr[i] = 0;
}

int main()
{    
    ifstream f("file.txt");
    char str[10000] = {""};

    if (!f.is_open())
        cout << "File can't be opened";
    else
    {
        int t = 0;
        while (!f.eof())
        {
            f.get(str[t]);
            t++;
        }
    }

    int i = 0;
    int numberOfLetters = 26;
    int letters[numberOfLetters] = {0};
    int asciiNumber = 'a';

    while (str[i] != '\0')
    {
        if (str[i] == ' ' || str[i] == '\n')
        {
            clean(letters, numberOfLetters);
            cout << str[i];
        }
        else
        {
            if (isLetter(str[i]))
            {
                int number = str[i] - asciiNumber;

                if(letters[number] != 1)
                    cout << str[i];

                letters[number] = 1;
            }
            else
            {
                cout << str[i];
            }
        }

        i++;
    }

    f.close();
    return 0;
}
