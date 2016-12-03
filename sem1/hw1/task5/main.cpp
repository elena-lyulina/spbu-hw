#include <iostream>
using namespace std;

int main()
{
    int size = 0;
    cout << "The size of string is ";
    cin >> size;
    char *string = new char[size];
    cout << "Enter the string" << endl;
    for (int i = 0; i < size; i++)
    {
        cin >> string[i];
    }


    int amount = 0;
    bool isCorrect = true;
    for (int i = 0; i < size; i++)
    {
        if (string[i] == '(')
        {
            amount++;
        }
        if (string[i] == ')')
        {
            if (amount == 0)
            {
                isCorrect = false;
            }
            else {
                amount--;
            }
        }
    }
    if (amount == 0 && isCorrect == true)
    {
        cout << "Okay :)";
    }
    else
    {
        cout << "Error";
    }

    delete[] string;
    return 0;
}
