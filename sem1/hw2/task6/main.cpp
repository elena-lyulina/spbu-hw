#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

void makeNumber(int size, int answer[])
{
    srand(time(NULL));
    for (int i = 0; i < size; i++)
    {
        bool sameNumbers = true;
        while (sameNumbers != false)
        {
            answer[i] = rand()%9;
            sameNumbers = false;
            for (int j = 0; j < i; j++)
            {
                if (answer[i] == answer[j])
                    sameNumbers = true;
            }
        }
    }

}


bool checkVictory (int answer[], int attempt[], int size)
{
    bool victory = true;
    for (int i = 0; i < size; i++)
    {
        if (answer[i] != attempt[i])
            victory = false;
    }
    return victory;
}


int countBulls(int answer[], int attempt[], int size)
{
    int amount = 0;
    for (int i = 0; i < size; i++)
    {
        if (answer[i] == attempt[i])
            amount++;
    }
    return amount;
}


int countCows(int answer[], int attempt[], int size)
{
    int amount = 0;
    for (int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {
            if (i != j && attempt[i] == answer[j])
            {
                amount++;
            }
        }
    }
    return amount;
}


int main()
{
    const int size = 4;
    int answer[size];
    makeNumber (size, answer);
    int attempt[size];

    while (checkVictory(answer, attempt, size) == false)
    {
        cout << "enter 4 numbers" << endl;
        for (int i = 0; i < size; i++)
        {
            cin >> attempt[i];
        }
        if (checkVictory(answer, attempt, size) == false)
        {
            cout << "bulls: " << countBulls(answer, attempt, size) << endl;
            cout << "cows: " << countCows(answer, attempt, size) << endl;
        }
    }

    cout << "win!";
    return 0;
}
