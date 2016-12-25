#include "list.h"
#include <iostream>
#include "string.h"
using namespace std;

struct ListElement
{
    String *string;
    int amount;
    ListElement *next;
};

struct List
{
    ListElement *head;
    int length;
    int amountOfWords;
};

List* createList()
{
//    cout << "new list" << endl;
    List *list = new List;
    list->head = nullptr;
    list->length = 0;
    list->amountOfWords = 0;
    return list;
}


ListElement *createListElement(String *string, ListElement *next)
{
    ListElement *newElement = new ListElement;
    newElement->string = string;
    newElement->next = next;
    newElement->amount = 1;
    return newElement;
}

bool isListEmpty(List *l)
{
    return l->length == 0;
}

int sizeOfList(List *l)
{
    return l->length;
}

int amountOfWords(List *l)
{
    return l->amountOfWords;
}

void addToList(List *l, String *string)
{

    if (isListEmpty(l))
    {
        l->head = createListElement(string, nullptr);
        l->length++;
        l->amountOfWords++;
        return;
    }

    if (areEqual(l->head->string, string))
    {
        l->head->amount++;
        l->amountOfWords++;
        return;
    }

    ListElement *temp = l->head;
    while (temp->next != nullptr)
    {
        temp = temp->next;
        if (areEqual(temp->string, string))
        {
            temp->amount++;
            l->amountOfWords++;

            return;
        }
    }

    temp->next = createListElement(string, nullptr);
    l->length++;
    l->amountOfWords++;
}

void findElement(List *l, String *string)
{
    ListElement *temp = l->head;
    while (temp != nullptr)
    {
        if (areEqual(temp->string, string))
        {
            printString(temp->string);
            cout << "(" << temp->amount << ")" << endl;
            return;
        }
        temp = temp->next;
    }
    cout << "not found" << endl;
}

void printList(List *l)
{
    if(isListEmpty(l))
    {
        cout << "-" << endl;
        return;
    }
    ListElement *temp = l->head;
    while (temp != nullptr)
    {
        printString(temp->string);
        cout << "(" << temp->amount << ") ";
        temp = temp->next;
    }

    cout << endl;
}


void deleteList(List *l)
{
    if (isListEmpty(l))
    {
        delete l;
       // cout << "delete list" << endl;
        return;
    }
    ListElement *temp = l->head;
    while (temp != nullptr)
    {
        ListElement *toDelete = temp;
        temp = temp->next;
        deleteString(toDelete->string);
        delete toDelete;
    }
    delete l;
  //  cout << "delete list" << endl;
}
