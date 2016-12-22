#include <iostream>
#include <string.h>

using namespace std;


String *createString(char *value)
{
    String *string = new String();

    int size = 0;
    while (value[size] != '\0')
        size++;

    string->size = size;
    string->value = new char[size + 1];

    for (int i = 0; i < size; i++)
        string->value[i] = value[i];

    string->value[size] = '\0';
    return string;
}

int size(String *string)
{
    return string->size;
}

String *concatenation(String *string, String *stringToAdd)
{
    int tempSize = size(string) + size(stringToAdd);
    char *temp = new char[tempSize + 1];
    int i = 0;
    while (i < tempSize)
    {
        if (i < size(string))
            temp[i] = string->value[i];
        else
            temp[i] = stringToAdd->value[i - size(string)];
        i++;
    }
    temp[tempSize] = '\0';
    String *tempString = createString(temp);
    delete [] temp;
    return tempString;
}

String *clone(String *stringToClone)
{
    String *result = createString(stringToClone->value);
    return result;

}

bool areEqual(String *string1, String *string2)
{
    if (size(string1) != size(string2))
        return false;

    int i = 0;
    while (i < size(string1))
    {
        if(string1->value[i] != string2->value[i])
            return false;
        i++;
    }
    return true;
}



bool isEmpty(String *string)
{
    return (string->size == 0);
}


char *convertToChar(String *string)
{
    char *result = new char[string->size + 1];
    int i = 0;
    for (i; i < string->size; i++)
        result[i] = string->value[i];

    result[i] = '\0';
    return result;
}

String *substring(String *string, int begin, int end)
{
    if (begin < 0)
    {
        cout << "begin < 0, I'll print your string from 0" << endl;
        begin = 0;
    }

    char *temp = new char[end - begin + 1];

    int i = begin;
    while (i < end && i < size(string))
    {
        temp[i - begin] = string->value[i];
        i++;
        if (i >= size(string))
        {
            cout << "substring: out of range" << endl << "all i can print is: ";
        }
    }

    temp[i - begin] = '\0';
    String *tempString = createString(temp);
    delete [] temp;
    return tempString;
}

void printString(String *string)
{
    if (isEmpty(string))
    {
        cout << "print: string is empty" << endl;
        return;
    }

    int i = 0;
    while (string->value[i] != '\0')
    {
        cout << string->value[i];
        i++;
    }
}


void deleteString(String *string)
{
    if(isEmpty(string))
    {
        delete string;
        return;
    }
    delete [] string->value;
    string->value = nullptr;
    delete string;
}




