#include "list.h"
#include <iostream>
#include "tree.h"
using namespace std;

struct ListElement
{
    Node *node;
    ListElement *next;
};

struct List
{
    ListElement *head;
    int length;
};

List* createList()
{
    List *list = new List;
    list->head = nullptr;
    list->length = 0;
    return list;
}


ListElement *createListElement(Node *node, ListElement *next)
{
    ListElement *newElement = new ListElement;
    newElement->node = node;
    newElement->next = next;
    return newElement;
}

bool isEmpty(List *l)
{
    return l->length == 0;
}

int size(List *l)
{
    return l->length;
}

void add(List *l, Node *node)
{

    if (isEmpty(l))
    {
        l->head = createListElement(node, nullptr);
        l->length++;
        return;
    }
    if (l->head->node->priority > node->priority)
    {
        ListElement *temp = l->head;
        l->head = createListElement(node, temp);
        l->length++;
        return;
    }

    ListElement *temp = l->head;
    while (temp->next != nullptr && temp->next->node->priority < node->priority)
    {
        temp = temp->next;
    }
    temp->next = createListElement(node, temp->next);
    l->length++;
}

void printList(List *l, ofstream &fout)
{
    if(isEmpty(l))
    {
        fout << "print: list is empty" << endl;
        return;
    }
    ListElement *temp = l->head;
    while (temp != nullptr)
    {
        fout << temp->node->symbol << "(" << temp->node->priority << ") ";
        temp = temp->next;
    }

    fout << endl;
}

Node *removeHead(List *l)
{
    if (isEmpty(l))
    {
        cout << "removeHead: list is empty" << endl;
        return 0;
    }

    ListElement *temp = l->head;

    Node *node = temp->node;
    l->head = temp->next;
    delete temp;
    l->length--;
    return node;
}

void deleteList(List *l)
{
    if (isEmpty(l))
    {
        delete l;
        return;
    }
    ListElement *temp = l->head;
    while (temp->next != nullptr)
    {
        ListElement *toDelete = temp;
        temp = temp->next;
        delete toDelete;
    }
    delete temp;
    delete l;
}
