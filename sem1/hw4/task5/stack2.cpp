#include "stack2.h"

struct Stack2Element {
    int data;
    Stack2Element *next;
};

struct Stack2 {
    Stack2Element *top;
    int size;
};

Stack2* createStack2()
{
    Stack2 *s = new Stack2;
    s->top = nullptr;
    s->size = 0;
    return s;

}

void push2(Stack2 *s, int value)
{
    Stack2Element *newElement = new Stack2Element;
    newElement->data = value;
    newElement->next = s->top;
    s->top = newElement;
    s->size++;
}

bool isEmpty(const Stack2 *s)
{
    return s->size == 0;
}

int pop2(Stack2 *s)
{
    if (isEmpty(s))
        return 'F';
    int x = s->top->data;
    Stack2Element *toDelete = s->top;
    s->top = s->top->next;
    delete toDelete;
    s->size--;
    return x;
}

void deleteStack2(Stack2 *s)
{
    if (isEmpty(s))
    {
        delete s;
        return;
    }

    while (s->size != 0)
        pop2(s);
    delete s;
}
