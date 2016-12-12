#include <iostream>
#include "stack1.h"
#include "stack2.h"
#include "conversionandcounting.h"

using namespace std;


int main()
{
    char inString[500] = {""};
    char outString[500] = {""};

    cout << "Hello!" << endl << "Enter string:" << endl;

    fgets(inString, 500, stdin);

    conversion(inString, outString);
    int answer = counting(outString);

    cout << "answer: " <<  answer;

    return 0;
}
