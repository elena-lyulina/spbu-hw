#include "stack1.h"

struct Stack1Element {
    char data;
    Stack1Element *next;
};

struct Stack1 {
    Stack1Element *top;
    int size;
};

Stack1* createStack1()
{
    Stack1 *s = new Stack1;
    s->top = nullptr;
    s->size = 0;
    return s;

}

void push(Stack1 *s, char value)
{
    Stack1Element *newElement = new Stack1Element;
    newElement->data = value;
    newElement->next = s->top;
    s->top = newElement;
    s->size++;
}

bool isEmpty(Stack1 const *s)
{
    return s->size == 0;
}

char pop(Stack1 *s)
{
    if (isEmpty(s))
        return 'F';
    char x = s->top->data;
    Stack1Element *toDelete = s->top;
    s->top = s->top->next;
    delete toDelete;
    s->size--;
    return x;
}

void deleteStack1(Stack1 *s)
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
