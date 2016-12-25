#pragma once
#include <fstream>

using namespace std;

struct Node
{
    int priority;
    char symbol;
    Node *leftChild;
    Node *rightChild;
};

struct Tree
{
    Node *root;
    int size;
};


Tree *createTree(Node* root);
Node *createNode(Node *leftChild, Node *rightChild, int priority, char symbol);
Node *mergeNodes(Node *left, Node *right);
void printTree(Tree *t, ofstream &fout);
void makeCodes(Tree *t, char codes[256][16]);
void deleteTree(Tree *t);



