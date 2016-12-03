#include <iostream>
using namespace std;

int main()
{
    int size = 0;
    bool isPalindrome = true;
    cout << "The size of string is ";
    cin >> size;
    char *string = new char[size];
    cout << "Enter the string" << endl;
    for (int i = 0; i < size; i++)
    {
        cin >> string[i];
    }


    for (int i = 0; i < size / 2; i++)
    {
        if (string[i] != string[size - 1 - i])
        {
            isPalindrome = false;
        }
    }


    if (isPalindrome == true)
    {
        cout << "The string is palindrome";
    }
    else
    {
        cout << "The string isn't palindrome";
    }
    delete[] string;
    return 0;
}
