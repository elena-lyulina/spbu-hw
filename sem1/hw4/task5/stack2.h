#pragma once

struct Stack2;

Stack2* createStack2();
void push2(Stack2 *s, int value);
int pop2(Stack2 *s);
bool isEmpty(Stack2 const *s);
void deleteStack2(Stack2 *s);
