#include "list.h"
#include <iostream>
using namespace std;

struct ListElement
{
    int value;
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


ListElement *createListElement(int value, ListElement *next)
{
    ListElement *newElement = new ListElement;
    newElement->value = value;
    newElement->next = next;
    return newElement;
}

bool isEmpty(List *l)
{
    return l->length == 0;
}

void add(List *l, int value)
{

    if (isEmpty(l))
    {
        l->head = createListElement(value, nullptr);
        l->length++;
        return;
    }
    if (l->head->value > value)
    {
        ListElement *temp = l->head;
        l->head = createListElement(value, temp);
        l->length++;
        return;
    }

    ListElement *temp = l->head;
    while (temp->next != nullptr && temp->next->value < value)
    {
        temp = temp->next;
    }
    temp->next = createListElement(value, temp->next);
    l->length++;
}

void remove(List *l, int value)
{
    if (isEmpty(l))
        return;

    ListElement *temp = l->head;
    if (temp->next == nullptr && temp->value == value)
    {
        delete temp;
        l->head = nullptr;
        l->length--;
        return;
    }
    if (temp->value == value)
    {
        l->head = temp->next;
        delete temp;
        l->length--;
        return;
    }
    while (temp->next != nullptr && temp->next->value != value)
    {
        temp = temp->next;
    }
    if (temp->next == nullptr)
        return;

    ListElement *toDelete = temp->next;
    temp->next = temp->next->next;
    delete toDelete;
    l->length--;
}

void print(List *l)
{
    if (isEmpty(l))
        cout << "your list is empty";
    else
    {
        ListElement *temp = l->head;
        while (temp != nullptr)
        {
            cout << temp->value << " ";
            temp = temp->next;
        }
    }
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
        ListElement *temptemp = temp;
        delete temptemp;
        temp = temp->next;
    }
    delete temp;
    delete l;
}
