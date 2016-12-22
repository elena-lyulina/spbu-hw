#pragma once
#include "string.h"

struct List;

List* createList();
void addToList(List *l, String *string);
int sizeOfList(List *l);
bool isListEmpty(List *l);
int amountOfWords(List *l);
void findElement(List *l, String *string);
void printList(List *l);
void deleteList(List *l);
