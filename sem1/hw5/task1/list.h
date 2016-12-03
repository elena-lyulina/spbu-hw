#pragma once

struct List;

List* createList();
void add(List *l, int value);
void remove(List *l, int value);
int size(List *l);
bool isEmpty(List *l);
void print(List *l);
void deleteList(List *l);
