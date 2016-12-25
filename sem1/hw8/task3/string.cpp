#include <iostream>
#include "string.h"

using namespace std;


String *createString(char *value)
{
  //  cout << "new string" << endl;
    String *string = new String();

    int size = 0;
    while (value[size] != '\0')
        size++;

    string->size = size;
  //  cout << "new char" << endl;
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
  //  cout << "new char" << endl;
    char *result = new char[string->size + 1];

    for (int i = 0; i < string->size; i++)
        result[i] = string->value[i];

    result[string->size] = '\0';
    return result;
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
      //  cout << "delete string" << endl;
        return;
    }
    delete [] string->value;
  //  cout << "delete char" << endl;
    string->value = nullptr;
    delete string;
  //  cout << "delete string" << endl;
}
