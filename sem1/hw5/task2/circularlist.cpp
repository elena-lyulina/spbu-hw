#include "circularlist.h"
#include <iostream>
using namespace std;

struct ListElement
{
    int value;
    ListElement *next;
};

struct CircularList
{
    ListElement *head;
    int length;
};

CircularList* createList()
{
    CircularList *list = new CircularList;
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

int size(CircularList *l)
{
    return l->length;
}

bool isEmpty(CircularList *l)
{
    return l->length == 0;
}

void add(CircularList *l, int value)
{
    if (isEmpty(l))
    {
        l->head = createListElement(value, l->head);
        l->length++;
        return;
    }

    if (size(l) == 1)
    {
        l->head->next = createListElement(value, l->head);
        l->length++;
        return;
    }

    ListElement *temp = l->head;
    while(temp->next != l->head)
    {
        temp = temp->next;
    }
    temp->next = createListElement(value, l->head);
    l->length++;

}

void print(CircularList *l)
{
    if (isEmpty(l))
    {
        cout << "your list is empty";
        return;
    }

    ListElement *temp = l->head;
    while (temp->next != l->head)
    {
        cout << temp->value << " ";
        temp = temp->next;
    }
    cout << temp->value;
}

void removeElement(CircularList *l, ListElement *temp)
{
    ListElement *toDelete = temp->next;
    int value = temp->next->value;

   // cout << endl << value << endl;

    if (l->head == toDelete)
    {
        l->head = temp->next->next;
    }

    temp->next = temp->next->next;
    delete toDelete;
    l->length--;

}

void remove(CircularList *l, int n)
{
    ListElement *temp = l->head;

    while (size(l) > 1)
    {
        for(int i = 1; i < n - 1; i++)
            temp = temp->next;

        removeElement(l, temp);

        temp = temp->next;

        // print(l);
        // cout << endl;
    }
}



void deleteList(CircularList *l)
{
    if (isEmpty(l))
    {
        delete l;
        return;
    }
    ListElement *temp = l->head;
    while (temp->next != l->head)
    {
        ListElement *temptemp = temp;
        delete temptemp;
        temp = temp->next;
    }
    delete temp;
    delete l;
}
