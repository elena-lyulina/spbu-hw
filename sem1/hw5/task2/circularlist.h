#pragma once

struct CircularList;

CircularList* createList();
void add(CircularList *l, int value);
void remove(CircularList *l, int n);
int size(CircularList *l);
bool isEmpty(CircularList *l);
void print(CircularList *l);
void deleteList(CircularList *l);
