#include "tree.h"
#include <iostream>

using namespace std;


//struct Node
//{
//    int priority;
//    char symbol;
//    Node *leftChild;
//    Node *rightChild;
//};

//struct Tree
//{
//    Node *root;
//    int size;
//};

Node *createNode(Node *leftChild, Node *rightChild, int priority, char symbol)
{
    Node *temp = new Node;
    temp->leftChild = leftChild;
    temp->rightChild = rightChild;
    temp->priority = priority;
    temp->symbol = symbol;
    return temp;
}

Tree *createTree(Node* root)
{
    Tree* temp = new Tree;
    temp->root = root;
    temp->size = 0;
    return temp;
}

Node *mergeNodes(Node *left, Node *right)
{
    Node *temp = createNode(left, right, left->priority + right->priority, '\0');
    return temp;
}


void printNode(Node *n, ofstream &fout)
{
    if (n == nullptr)
    {
        fout << "nullptr";
        return;
    }

    fout << "(" << n->symbol - 0 << " ";
    printNode(n->leftChild, fout);
    fout << " ";
    printNode(n->rightChild, fout);
    fout << ")";
   }

void printTree(Tree *t, ofstream &fout)
{
    printNode(t->root, fout);
}

void addToString(char string[], char toAdd)
{
    int i = 0;
    while (string[i] != '\0')
        i++;
    string[i] = toAdd;
}

void cloneString(char original[], char copy[])
{
    int i = 0;
    while (original[i] != '\0')
    {
        copy[i] = original[i];
        i++;
    }
}


void makeCodesNode(Node *node, char codes[256][16], char actualCode[])
{
    if (node->symbol != '\0')
    {
        int j = node->symbol;
        cloneString(actualCode, codes[j]);
        return;
    }

    char leftCode[16] = {""};
    cloneString(actualCode, leftCode);
    addToString(leftCode, '0');
    makeCodesNode(node->leftChild, codes, leftCode);

    char rightCode[16] = {""};
    cloneString(actualCode, rightCode);
    addToString(rightCode, '1');
    makeCodesNode(node->rightChild, codes, rightCode);
}


void makeCodes(Tree *t, char codes[256][16])
{
    char actualCode[16] = {""};
    makeCodesNode(t->root, codes, actualCode);
}


void deleteNode(Node *n)
{
    if (n == nullptr)
        return;
    deleteNode(n->leftChild);
    deleteNode(n->rightChild);
    delete n;
}

void deleteTree(Tree *t)
{
    deleteNode(t->root);
    delete t;
}


