#include <iostream>
using namespace std;

void fillArray(int **array, int n)
{
    cout << "enter n x n elements of array" << endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
            cin >> array[i][j];
    }

}

void goUp(int **array, int &x, int &y, int distance)
{
    for (int i = 0; i < distance; i++)
    {
        x = x - 1;
        cout << array[x][y] << " ";
    }
}

void goDown(int **array, int &x, int &y, int distance)
{
    for (int i = 0; i < distance; i++)
    {
        x = x + 1;
        cout << array[x][y] << " ";
    }
}

void goRight(int **array, int &x, int &y, int distance)
{
    for (int i = 0; i < distance; i++)
    {
        y = y + 1;
        cout << array[x][y] << " ";
    }
}

void goLeft(int **array, int &x, int &y, int distance)
{
    for (int i = 0; i < distance; i++)
    {
        y = y - 1;
        cout << array[x][y] << " ";
    }
}

void deleteArray(int **array, int size)
{
    for (int i = 0; i < size; i++)
    {
        delete[] array[i];
    }
    delete[] array;
}

int main()
{
    int n = 0;
    cout << " n = ";
    cin >> n;

    int **array = new int*[n];
    for (int i = 0; i < n; i++)
    {
        array[i] = new int[n];
    }


    fillArray(array, n);

    int x = (n + 1) / 2 - 1;
    int y = ( n + 1) / 2 - 1;

    int level = 2;
    cout << array[x][y] << " ";
    while (x != 0 && y != 0)
    {
        goUp(array, x, y, 1);
        goRight(array, x, y, level - 1);
        goDown(array, x, y, level);
        goLeft(array, x, y, level);
        goUp(array, x, y, level);
        level = level + 2;

    }

    deleteArray(array, n);
    return 0;
}
