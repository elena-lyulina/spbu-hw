#include <iostream>
#include "dijkstra.h"

using namespace std;

void findWay(int i, int *way)
{
    cout << way[i] << " ";
    if (way[i] != 1)
        findWay(way[i] - 1, way);
}

int findMinTown(int lengths[], int visit[], int numberOfTowns)
{
    int i = 0;
    while (visit[i] != 0 && i < numberOfTowns)
        i++;

    if (i == numberOfTowns)
        return -1;

    int minLength = lengths[i];
    int minTown = i;

    for (int i = 0; i < numberOfTowns; i++)
    {
        if (visit[i] == 0 && lengths[i] < minLength)
        {
            minLength = lengths[i];
            minTown = i;
        }
    }

    return minTown;

}

void goToNextTown(int number, int **towns, int *&lengths, int *&visit, int *&way, int numberOfTowns)
{
    number = number + 0;
    if (lengths[number] != 10000)
    {
        cout << "town number " << number + 1;
        cout << ", the length is " << lengths[number] << " , the way is: " ;
        findWay(number, way);
        cout << endl;
    }
    else
        cout << "they can't come to town number " << number + 1;

    for (int i = 0; i < numberOfTowns; i++)
    {
        if (towns[number][i] != -1 && visit[i] == 0)
        {
            if (lengths[i] > (lengths[number] + towns[number][i]))
            {
                lengths[i] = (lengths[number] + towns[number][i]);
                way[i] = number + 1;
            }

        }
    }

    visit[number] = 1;

    int nextTown = findMinTown(lengths, visit, numberOfTowns);
    if (nextTown == -1)
        return;

    goToNextTown(nextTown, towns, lengths, visit, way, numberOfTowns);
}


void printTownsAndLengths(int **towns, int numberOfTowns)
{
    int *lengths = new int[numberOfTowns];
    int *visit = new int[numberOfTowns];
    int *way = new int[numberOfTowns];

    lengths[0] = 0;
    visit[0] = 0;
    way[0] = 1;

    for (int i = 1; i < numberOfTowns; i++)
    {
        lengths[i] = 10000;
        visit[i] = 0;
        way[i] = 1;
    }


    goToNextTown(0, towns, lengths, visit, way, numberOfTowns);

    delete []lengths;
    delete []visit;
    delete []way;

}


