#include <iostream>
#include <string.h>


using namespace std;

bool isNumber(char ch)
{
    return (ch >= '0') && (ch <= '9');
}

bool areSignAndNumber(int i, char string[], int size)
{
    return ((i + 1) < size) && ((string[i] == '+') || (string[i] == '-')) && isNumber(string[i + 1]);
}

bool areDotAndNumber(int i, char string[], int size)
{
    return ((i + 1) < size) && (string[i] == '.') && isNumber(string[i + 1]);
}

bool isE(char ch)
{
    return ch == 'E';
}

// (+ | -)? digit+ (. digit+)? (E(+ | -)? digit+)?, где digit -- [0..9])


bool isRealNumber(char string[])
{
    int state = 0;
    int size = strlen(string);
    int i = 0;
    while (i < size)
    {
        char next = string[i];
        switch (state)
        {
        case 0:
            if (areSignAndNumber(i, string, size))
            {
                i++;
                state = 1;
            }
            else if (isNumber(next))
                state = 1;
            else
                return false;
            break;
        case 1:
            if (isNumber(next))
                state = 1;
            else if (areDotAndNumber(i, string, size))
            {
                i++;
                state = 2;
            }
            else if (isE(next))
                state = 3;
            else
                return false;
            break;
        case 2:
            if (isNumber(next))
                state = 2;
            else if (isE(next))
                state = 3;
            else
                return false;
            break;
        case 3:
            if (areSignAndNumber(i, string, size))
            {
                i++;
                state = 4;
            }
            else if (isNumber(next))
                state = 4;
            else
                return false;
            break;
        case 4:
            if (isNumber(next))
                state = 4;
            else
                return false;
            break;

        }
        i++;
    }

    return (state == 1)|| (state == 2) || (state == 4);
}

int main()
{
    cout << "enter string:" <<  endl;
    char string[1000] = {""};
    cin >> string;

    if (isRealNumber(string))
        cout << "it's a real number" ;
    else
        cout << "it isn't a real number";

    return 0;
}
