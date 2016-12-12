#include "phonebook.h"
#include <iostream>
#include <string.h>
#include <fstream>

using namespace std;

struct PhoneBookElement
{
    PhoneBookElement *next;
    char name[100];
    char number[12];
};

struct PhoneBook
{
    PhoneBookElement *head;
    int length;
};

PhoneBook* createPhoneBook()
{
    PhoneBook *ph = new PhoneBook;
    ph->head = nullptr;
    ph->length = 0;
    return ph;
}

PhoneBookElement* createPhoneBookElement(char name[], char number[], PhoneBookElement* next)
{
    PhoneBookElement *temp = new PhoneBookElement();
    temp->next = next;
    strcpy(temp->name, name);
    strcpy(temp->number, number);
    return temp;
}

void print(PhoneBook *pb)
{
    if(pb->length == 0)
    {
        cout << "Your book is empty!" << endl;
        return;
    }
    PhoneBookElement *temp = pb->head;
    while (temp != nullptr)
    {
        cout << temp->name << " ";
        cout << temp->number << endl;
        temp = temp->next;
    }
}

void add(PhoneBook *pb, char name[], char number[])
{
    if(pb->length == 0)
    {
        pb->head = createPhoneBookElement(name, number, nullptr);
        pb->length = 1;
        return;
    }
    PhoneBookElement *temp = pb->head;
    while (temp->next != nullptr)
    {
        temp = temp->next;
    }
    temp->next = createPhoneBookElement(name, number, nullptr);
    pb->length++;

}

void findName(PhoneBook *pb, char number[])
{
    if(pb->length == 0)
    {
        cout << "Your book is empty!" << endl;
        return;
    }
    PhoneBookElement *temp = pb->head;
    bool find = false;
    while (temp->next != nullptr)
    {
        if (strcmp(temp->number, number) != 0)
        {
            temp = temp->next;
        }
        else
        {
            find = true;
            cout << temp->name << endl;
            return;
        }

    }
    if (strcmp(temp->number, number) == 0)
    {
        cout << temp->name << endl;
        find = false;
    }
    if (!find)
        cout << "Not found" << endl;
}



void findNumber(PhoneBook *pb, char name[])
{
    if(pb->length == 0)
    {
        cout << "Your book is empty!" << endl;
        return;
    }
    PhoneBookElement *temp = pb->head;
    bool find = false;
    while (temp->next != nullptr)
    {
        if (strcmp(temp->name, name) != 0)
            temp = temp->next;
        else
        {
            find = true;
            cout << temp->number << endl;
            return;
        }
    }
    if (strcmp(temp->name, name) == 0)
    {
        find = true;
        cout << temp->number << endl;
    }
    if (!find)
        cout << "Not found" << endl;
}



void save(PhoneBook *pb)
{
    ofstream fout("file.txt");
    PhoneBookElement *temp = pb->head;
    while (temp->next != nullptr)
    {
        fout << temp->name << endl << temp->number << endl;
        temp = temp->next;
    }
    fout << temp->name << endl << temp->number << endl;
    temp = temp->next;
    fout.close();
}

void deleteLast(PhoneBook *pb)
{
    if(pb->length == 0)
        return;
    if (pb->length == 1)
    {
        delete pb->head;
        pb->length = 0;
        return;
    }

    if (pb->length > 0)
    {
        PhoneBookElement *temp = pb->head;
        while (temp->next->next != nullptr)
        {
            temp = temp->next;
        }
        delete temp->next;
        temp->next = nullptr;
        pb->length--;

    }

}

void deletePhoneBook(PhoneBook *pb)
{
    if (pb->length == 0)
    {
        delete pb;
        return;
    }
    PhoneBookElement *temp = pb->head;
    while (temp->next != nullptr)
    {
        PhoneBookElement *toDelete = temp;
        temp = temp->next;
        delete toDelete;
    }
    delete temp;
    delete pb;
}








