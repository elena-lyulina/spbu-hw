#include <iostream>
#include "circularlist.h"

using namespace std;

int main()
{
    int n = 0;
    cout << "n = ";
    cin >> n;

    int m = 0;
    cout << "m = ";
    cin >> m;

    CircularList *list = createList();

    for (int i = 1; i <= n; i++)
    {
        add(list, i);
    }

   // print(list);
    cout << endl;

    remove(list, m);
    cout << "the last one was at place ";
    print(list);

    deleteList(list);
    return 0;
}
