#include <iostream>
#include "dijkstra.h"
#include <fstream>

using namespace std;

int main()
{
    ifstream fin("file.txt");
    int numberOfTowns = 0;
    fin >> numberOfTowns;
    int **towns = new int*[numberOfTowns];

    for (int i = 0; i < numberOfTowns; i++)
    {
        towns[i] = new int[numberOfTowns];
        for (int j = 0; j < numberOfTowns; j++)
            towns[i][j] = -1;
    }


    int numberOfRoads = 0;
    fin >> numberOfRoads;

    for (int i = 0; i < numberOfRoads; i++)
    {
        int firstTown = 0;
        int secondTown = 0;
        int length = 0;
        fin >> firstTown;
        fin >> secondTown;
        fin >> length;
        towns[firstTown - 1][secondTown - 1] = length;
        towns[secondTown - 1][firstTown - 1] = length;
    }


    printTownsAndLengths(towns, numberOfTowns);

    for (int i = 0; i < numberOfTowns; i++)
        delete [] towns[i];

}
