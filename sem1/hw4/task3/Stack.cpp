#include "Stack.h"

struct StackElement {
    char data;
    StackElement *next;
};

struct Stack {
    StackElement *top;
    int size;
};

Stack* createStack()
{
    Stack *s = new Stack;
    s->top = nullptr;
    s->size = 0;
    return s;

}

void push(Stack *s, char value)
{
    StackElement *newElement = new StackElement;
    newElement->data = value;
    newElement->next = s->top;
    s->top = newElement;
    s->size++;
}

bool isEmpty(Stack const *s)
{
    return s->size == 0;
}

char pop(Stack *s)
{
    if (isEmpty(s))
        return 'F';
    char x = s->top->data;
    StackElement *toDelete = s->top;
    s->top = s->top->next;
    delete toDelete;
    s->size--;
    return x;
}

void deleteStack(Stack *s)
{
    if (isEmpty(s))
    {
        delete s;
        return;
    }

    while (s->size != 0)
        pop(s);
    delete s;
}
