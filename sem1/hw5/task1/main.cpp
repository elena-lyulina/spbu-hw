#include <iostream>
#include "list.h"

using namespace std;

int main()
{
    cout << "hello!" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add a value to sorted list" << endl;
    cout << "2 - remove a value from sorted list" << endl;
    cout << "3 - print list" << endl;
    List *List = createList();

    int in = -1;
    cin >> in;

    while (in != 0)
    {
        if (in == 1)
        {
            int value = 0;
            cout << "value to add is ";
            cin >> value;
            add(List, value);
            cin >> in;
        }
        if (in == 2)
        {
            int value = 0;
            cout << "value to remove is ";
            cin >> value;
            remove(List, value);
            cin >> in;
        }
        if (in == 3)
        {
            print(List);
            cin >> in;
        }
    }

    cout << "the end";
    deleteList(List);
    return 0;
}
