#pragma once

struct String
{
    char *value;
    int size;
};

String *createString(char *value);
String *clone(String *stringToClone);
String *concatenation(String *string, String *stringToAdd);
bool areEqual(String *string1, String *string2);
int size(String *string);
bool isEmpty(String *string);
char *convertToChar(String *string);
String* substring(String *string, int begin, int end);
void printString(String *string);
void deleteString(String *string);
