#include <iostream>
using namespace std;

int main()
{
    int amount = 0;
    int needlesSize = 0;
    cout << "The size of 'needle' is ";
    cin >> needlesSize;
    char *needle = new char[needlesSize];
    cout << "Enter 'needle'" << endl;
    for (int i = 0; i < needlesSize; i++)
    {
        cin >> needle[i];
    }


    int haystacksSize = 0;
    cout << "The size of 'haystack' is ";
    cin >> haystacksSize;
    char *haystack = new char[haystacksSize];
    cout << "Enter 'haystack'" << endl;
    for (int i = 0; i < haystacksSize; i++)
    {
        cin >> haystack[i];
    }


    for (int i = 0; i < haystacksSize; i++)
    {
        if (haystack[i] == needle[0])
        {
            bool isIn = true;
            for (int j = 1; j < needlesSize; j++)
            {
                i++;
                if (haystack[i] != needle[j])
                {
                    isIn = false;
                }
            }
            if (isIn == true)
            {
                amount++;
            }
        }
    }

    cout << "'haystack' contains " << amount << " 'needle'";

    delete[] haystack;
    delete[] needle;
    return 0;
}
