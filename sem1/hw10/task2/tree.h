#pragma once
#include <fstream>

using namespace std;

struct Tree;

Tree* createTree();
void convertToTree(Tree *tree, char string[]);
int count(Tree *tree);
void printIncreasing(Tree *t);
void printTree(Tree *t, ofstream &fout);
void printText(char codeString[], Tree *tree, ofstream &fout);
void deleteTree(Tree *t);



