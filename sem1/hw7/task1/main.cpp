#include <iostream>
#include "tree.h"

using namespace std;

void add(Tree *t)
{
    cout << "enter element to add"  << endl;
    int a = 0;
    cin >> a;
    addElement(t, a);
    cout << "your element has been added" << endl;
}

void check(Tree *t)
{
    cout << "enter element to check"  << endl;
    int a = 0;
    cin >> a;
    if (checkElement(t, a))
        cout << "there is your element" << endl;
    else
        cout << "element not found" << endl;
}

void remove(Tree *t)
{
    cout << "enter element to remove"  << endl;
    int a = 0;
    cin >> a;
    if(removeElement(t, a))
    {
        cout << "your element has been removed" << endl;
    }
    else
        cout << "your element not found" << endl;
}

int main()
{
    Tree *t = createTree();


    cout << "Hello!" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add element"  << endl;
    cout << "2 - check element"  << endl;
    cout << "3 - remove element"  << endl;
    cout << "4 - print increasing tree"  << endl;
    cout << "5 - print decreasing tree"  << endl;
    cout << "6 - print tree"  << endl;


    enum  WhatToDo {exit, addElement, checkElement, removeElement, increasingTree, decreasingTree, print};
    int doIt = 0;
    cin >> doIt;

    while (doIt){
        switch (doIt)
        {
        case exit:
            break;
        case addElement:
            add(t);
            break;
        case checkElement:
            check(t);
            break;
        case removeElement:
            remove(t);
            break;
        case increasingTree:
            printIncreasing(t);
            break;
        case decreasingTree:
            printDecreasing(t);
            break;
         case print:
            printTree(t);
        }
        cin >> doIt;
    }

    cout << "Bye!";
    deleteTree(t);
    return 0;
}
