#pragma once

struct Tree;

Tree* createTree();
void addElement(Tree *t, int value);
bool checkElement(Tree *t, int value);
bool removeElement(Tree *t, int value);
void printIncreasing(Tree *t);
void printDecreasing(Tree *t);
void printTree(Tree *t);
void deleteTree(Tree *t);



