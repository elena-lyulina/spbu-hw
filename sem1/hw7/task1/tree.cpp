#include "tree.h"
#include <iostream>

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

Node *createNode(Node *leftChild, Node *rightChild, int value)
{
    Node *temp = new Node;
    temp->leftChild = leftChild;
    temp->rightChild = rightChild;
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

void addToNode(Node *&n, int value)
{
    if (n == nullptr)
    {
        n = createNode(nullptr, nullptr, value);
        return;
    }

    if (n->value == value)
        return;
    if (n->value < value)
        addToNode(n->rightChild, value);
    if (n->value > value)
        addToNode(n->leftChild, value);
}

void addElement(Tree *t, int value)
{
    addToNode(t->root, value);
    t->size++;
}


Node *checkElementNode(Node *n, int value)
{
    if (n == nullptr || n->value == value)
        return n;
    else if (n->value > value)
        return checkElementNode(n->leftChild, value);
    else if (n->value < value)
        return checkElementNode(n->rightChild, value);
    return nullptr;
}

bool checkElement(Tree *t, int value)
{
    return (checkElementNode(t->root, value) != nullptr);
}

bool hasNoChildren(Node *n)
{
    return (n->leftChild == nullptr) && (n->rightChild == nullptr);
}

bool hasLeftChild(Node *n)
{
    return (n->leftChild != nullptr) && (n->rightChild == nullptr);
}

bool hasRightChild(Node *n)
{
    return (n->leftChild == nullptr) && (n->rightChild != nullptr);
}

bool hasTwoChildren(Node *n)
{
    return (n->leftChild != nullptr) && (n->rightChild != nullptr);
}

bool searchToRemove(Node *&n, int value);

void removeNode(Node *&n)
{
    if (hasNoChildren(n))
    {
        delete n;
        n = nullptr;
    }
    else if (hasLeftChild(n))
    {
        Node *toDelete = n;
        n = n->leftChild;
        delete toDelete;
    }
    else if (hasRightChild(n))
    {
        Node *toDelete = n;
        n = n->rightChild;
        delete toDelete;
    }
    else if (hasTwoChildren(n))
    {
        Node *temp = n->rightChild;

        while (temp->leftChild != nullptr)
            temp = temp->leftChild;

        int value = temp->value;
        searchToRemove(n, value);
        n->value = value;
    }
}

bool searchToRemove(Node *&n, int value)
{
    if (n == nullptr)
        return false;
    if (n->value > value)
        return searchToRemove(n->leftChild, value);
    else if (n->value < value)
        return searchToRemove(n->rightChild, value);
    if (n->value == value)
    {
        removeNode(n);
        return true;
    }
    return false;
}

bool removeElement(Tree *t, int value)
{
   return searchToRemove(t->root, value);
}

void printIncreasingNode(Node *n)
{
    if(n == nullptr)
        return;

    printIncreasingNode(n->leftChild);
    cout << n->value << " ";
    printIncreasingNode(n->rightChild);
}


void printIncreasing(Tree *t)
{
    printIncreasingNode(t->root);
    cout << endl;
}

void printDecreasingNode(Node *n)
{
    if(n == nullptr)
        return;

    printDecreasingNode(n->rightChild);
    cout << n->value << " ";
    printDecreasingNode(n->leftChild);
}


void printDecreasing(Tree *t)
{
    printDecreasingNode(t->root);
    cout << endl;
}

void printNode(Node *n)
{
    if (n == nullptr)
    {
        cout << "nullptr";
        return;
    }

    cout << "(" << n->value << " ";
    printNode(n->leftChild);
    cout << " ";
    printNode(n->rightChild);
    cout << ")" << " ";
   }

void printTree(Tree *t)
{
    printNode(t->root);
    cout << endl;
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


