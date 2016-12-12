#include <iostream>
#include "phonebook.h"
#include "fstream"

using namespace std;

void addElement(PhoneBook *pb)
{
    char name[100] = {""};
    char number[12] = {""};
    cout << "Enter the name" << endl;
    cin >> name;
    cout << "Enter the number" << endl;
    cin >> number;
    add(pb, name, number);
    cout << "Contact has been added" << endl;
}

void findName(PhoneBook *pb)
{
    char number[12] = {""};
    cout << "Enter the number to find the name" << endl;
    cin >> number;
    findName(pb, number);
}

void findNumber(PhoneBook *pb)
{
    char name[100] = {""};
    cout << "Enter the name to find the number" << endl;
    cin >> name;
    findNumber(pb, name);
}

void saveFile(PhoneBook *pb)
{
    save(pb);
    cout << "Your phonebook has been saved" << endl;
}

int main()
{
    PhoneBook *pb = createPhoneBook();
    ifstream f;
    f.open("file.txt");
    char name[100] = {""};
    char number[12] = {""};


    if (!f.is_open())
        cout << "File doesnt exist" << endl;
    else
    {
        while(!f.eof())
        {
            f.getline(name, 100);
            f.getline(number, 12);

            add(pb, name, number);
        }

    }

    deleteLast(pb);

    cout << "Hello!" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add name and number" << endl;
    cout << "2 - find phone" << endl;
    cout << "3 - find name" << endl;
    cout << "4 - save" << endl;
    cout << "5 - print your telephone book" << endl;


    enum  WhatToDo {exit, add, Number, Name, File, printBook};
    int doIt = 0;
    cin >> doIt;

    while (doIt){
        switch (doIt)
        {
        case exit:
            cout << "Bye!";
            break;
        case add:
            addElement(pb);
            break;
        case Number:
            findNumber(pb);
            break;
        case Name:
            findName(pb);
            break;
        case File:
            saveFile(pb);
            break;
        case printBook:
            print(pb);
            break;
        }
        cin >> doIt;
    }

    deletePhoneBook(pb);
    f.close();
    return 0;
}
