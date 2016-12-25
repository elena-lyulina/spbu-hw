#include "tree.h"
#include <iostream>
#include <string.h>

using namespace std;


struct Node
{
    int value;
    Node *leftChild;
    Node *rightChild;
};

struct Tree
{
    Node *root;
    int size;
};

Node *createNode(int value)
{
    Node *temp = new Node;
    temp->leftChild = nullptr;
    temp->rightChild = nullptr;
    temp->value = value;
    return temp;
}

Tree *createTree()
{
    Tree* temp = new Tree;
    temp->root = nullptr;
    temp->size = 0;
    return temp;
}

//(0 (0 (32 nullptr nullptr)  (0 (0 (0 (0 (109 nullptr nullptr)  (112 nullptr nullptr) )

int convertToInt(char string[])
{
    int result = 0;
    int size = strlen(string);
    int ten = 1;
    for (int i = size - 1; i >=0; i--)
    {
        result += (string[i] - '0') * ten;
        ten *= 10;
    }
    return result;
}

//(0 (0 (32 nullptr nullptr) (0 (0 (0 (0 (109 nullptr nullptr)  (112 nullptr nullptr) )

void convertToNode(Node *&n, char string[], int &i)
{
    char symbol[16] = {""};
    i++;
    int j = 0;
    while (string[i] != ' ')
    {
        symbol[j] = string[i];
        i++;
        j++;
    }
    int value = convertToInt(symbol);

    n = createNode(value);

    i ++;

    if (string[i] == '(')
        convertToNode(n->leftChild, string, i);
    else
    {
        n->leftChild = nullptr;
        i += 6;
    }

    i += 2;

    if (string[i] == '(')
        convertToNode(n->rightChild, string, i);
    else
    {
        n->rightChild = nullptr;
        i += 6;
    }
    i++;
}

void convertToTree(Tree *tree, char string[])
{
    int i = 0;
    convertToNode(tree->root, string, i);
}


void printNode(Node *n, ofstream &fout)
{
    if (n == nullptr)
    {
        fout << "nullptr";
        return;
    }

    fout << "(" << n->value - 0 << " ";
    printNode(n->leftChild, fout);
    fout << " ";
    printNode(n->rightChild, fout);
    fout << ")";
}

void printTree(Tree *t, ofstream &fout)
{
    printNode(t->root, fout);
}

//1 0 1 0 1 1 1 0 1 0
//0 1 2 3 4 5 6 7 8 9

void printTextNode(Tree *tree, char codeString[], Node *node, ofstream &fout, int &i)
{
    if (i > strlen(codeString))
    {
        return;
    }
    if (node->value != 0)
    {
        fout << (char)node->value;
        printTextNode(tree, codeString, tree->root, fout, i);
        return;
    }

    if (codeString[i] == '0')
    {
        i++;
        printTextNode(tree, codeString, node->leftChild, fout, i);
    }
    else
    {
        i++;
        printTextNode(tree, codeString, node->rightChild, fout, i);
    }
}

void printText(char codeString[], Tree *tree, ofstream &fout)
{
    int i = 0;
    printTextNode(tree, codeString, tree->root, fout, i);
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


