#include <iostream>
#include "Stack.h"

using namespace std;

bool isOpeningBrace(char ch)
{
    return ch == '(';
}

bool isClosingBrace(char ch)
{
    return ch == ')';
}

bool isNumber(char ch)
{
    return ch >= '0' && ch <= '9';
}

bool isOperator(char ch)
{
    return (ch == '+') || (ch == '-') || (ch == '/') || (ch == '*');
}

int priority(char ch)
{
    if (ch == '(')
        return 1;
    if (ch == '+' || ch == '-')
        return 2;
    if (ch == '/' || ch == '*')
        return 3;

    else
        return -1;
}

void addSpace(char str[], int &i)
{
    str[i] = ' ';
    i++;
}


int main()
{
    Stack *stack = createStack();

    char inString[500];
    char outString[500];

    cout << "Hello!" << endl << "Enter string:" << endl;

    fgets(inString, 500, stdin);

    int in = 0;
    int out = 0;

    while (inString[in] != '\0')
    {
        if (isNumber(inString[in]))
        {
            outString[out] = inString[in];
            out++;
            if (!isNumber(inString[in + 1]))
                addSpace(outString, out);
        }

        if (isClosingBrace(inString[in]))
        {
            char toAdd = pop(stack);
            while (toAdd != '(')
            {
                outString[out] = toAdd;
                out++;
                addSpace(outString, out);
                toAdd = pop(stack);
            }
        }

        if (isOpeningBrace(inString[in]))
        {
            push(stack, inString[in]);
        }

        if (isOperator(inString[in]))
        {
            if (!isEmpty(stack))
            {
                char toAdd = pop(stack);
                if (priority(toAdd) >= priority(inString[in]))
                {
                    while (priority(toAdd)  >= priority(inString[in]))
                    {
                        outString[out] = toAdd;
                        out++;
                        addSpace(outString, out);
                        if (isEmpty(stack))
                            break;
                        toAdd = pop(stack);
                    }
                    if (priority(toAdd) < priority(inString[in]))
                        push(stack, toAdd);
                }
                else
                    push(stack, toAdd);
            }
            push(stack, inString[in]);
        }

        in++;
    }


    while(!isEmpty(stack))
    {
        outString[out] = pop(stack);
        out++;
        addSpace(outString, out);
    }

    cout << outString;
    deleteStack(stack);
    return 0;
}
