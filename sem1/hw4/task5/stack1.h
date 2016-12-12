#pragma once

struct Stack1;

Stack1* createStack1();
void push(Stack1 *s, char value);
char pop(Stack1 *s);
bool isEmpty(Stack1 const *s);
void deleteStack1(Stack1 *s);
