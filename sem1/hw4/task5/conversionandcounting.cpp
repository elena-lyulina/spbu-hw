#include <iostream>
#include "stack1.h"
#include "stack2.h"
#include "conversionandcounting.h"

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



int doMath(char ch, int a, int b)
{
    if (ch == '+')
        return a + b;
    if (ch == '-')
        return a - b;
    if (ch == '*')
        return a * b;
    if (ch == '/')
        return a / b;
    else
        return -1;
}


void conversion(char inString[], char outString[]){
    Stack1 *stack = createStack1();
    int in = 0;
    int out = 0;
    while (inString[in] != '\n')
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
        outString[out] = ' ';
        out++;
    }
    outString[out] = '\n';

    deleteStack1(stack);
}




int counting(char outString[])
{
    Stack2 *stack2 = createStack2();
    int i = 0;

    while (outString[i] != '\n')
    {
        if (isNumber(outString[i]))
        {
            int value = (int)outString[i] - '0';
            while (isNumber(outString[i + 1]))
            {
                value = value * 10 + (int)outString[i + 1] - '0';
                i++;
            }
            push2(stack2, value);
        }

        if (isOperator(outString[i]))
        {
            int operator2 = pop2(stack2);
            int operator1 = pop2(stack2);
            int toAdd = doMath(outString[i], operator1, operator2);
            push2(stack2, toAdd);
        }

        i++;
    }
    int answer = pop2(stack2);
    deleteStack2(stack2);
    return answer;

}
