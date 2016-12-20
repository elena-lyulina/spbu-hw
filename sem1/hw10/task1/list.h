#pragma once
#include "tree.h"
#include <fstream>


struct List;

List* createList();
void add(List *l, Node *node);
Node *removeHead(List *l);
int size(List *l);
bool isEmpty(List *l);
void printList(List *l, ofstream &fout);
void deleteList(List *l);
