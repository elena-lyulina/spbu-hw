#include <iostream>
#include <string.h>

using namespace std;

void initialize(long long power[], int size, int powerNumber, int mod)
{
    power[0] = 1;
    for (int i = 0; i < size - 1; i++)
        power[i + 1] = (power[i] * powerNumber) % mod;
}

long int hashString(char string[], int size, long long power[], int mod)
{
    long int result = 0;
    for (int i = 0; i < size; i++)
    {
        result += (string[i] * power[size - 1 - i]) % mod;
    }
    return result % mod;
}

void checkNeedle(char haystack[], char needle[], int begin)
{
    bool findNeedle = true;

    for(int i = 0; i < strlen(needle); i++)
    {
        if (haystack[i + begin] != needle[i])
            findNeedle = false;
    }

    if (findNeedle)
        cout << "Needle has been found, index is " << begin << endl;
}



int main()
{
    char haystack[1000];
    cout << "enter haystack: " << endl;
    cin >> haystack;
    cout << "enter needle: " << endl;
    char needle[1000];
    cin >> needle;

    int sizeNeedle = strlen(needle);
    int sizeHS = strlen(haystack);

    int powerNumber = 37;
    int mod = 9999901;
    long long power[sizeNeedle] = {0};
    initialize(power, sizeNeedle, powerNumber, mod);


    long int hashNeedle =  hashString(needle, sizeNeedle, power, mod);
    long int hashHS[sizeHS - sizeNeedle + 1] = {0};
    hashHS[0] = hashString(haystack, sizeNeedle, power, mod);

    if(hashHS[0] == hashNeedle)
        checkNeedle(haystack, needle, 0);

    for(int i = 0; i < sizeHS - sizeNeedle; i++)
    {
        hashHS[i + 1] = (powerNumber * (hashHS[i] - power[sizeNeedle - 1] * haystack[i]) + haystack[i + sizeNeedle]) % mod;
        while (hashHS[i + 1] < 0)
            hashHS[i + 1] += mod;

        if(hashHS[i + 1] == hashNeedle)
            checkNeedle(haystack, needle, i + 1);
    }


    return 0;
}
