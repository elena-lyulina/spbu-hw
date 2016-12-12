#include <iostream>
#include "fstream"

using namespace std;

bool notFoundYet(int students[], int s)
{
    return students[s] != 3 && students[s] != 2 && students[s] != 1;
}

int origin(int students[], int s)
{
    while (notFoundYet(students, s))
    {

        int number = students[s];
        students[s] = students[number - 1];
    }
    return students[s];
}

int main()
{
    cout << "Enter the number of students" << endl << "n = ";
    int n = 0;
    cin >> n;
    cout << "Enter " << n << " couples of numbers" << endl;
    cout << "If the student has not written off the task and its not 1st, 2d or 3d student," << endl;
    cout << "his second number is -1" << endl;

    int *students = new int[n];
    for (int i = 0; i < n; i++)
    {
        int firstNumber = 0;
        cin >> firstNumber;
        int secondNumber = 0;
        cin >> secondNumber;
        students[firstNumber - 1] = secondNumber;
    }

    for (int i = 0; i < n; i++)
    {
        if (i == 0 || i == 1 || i == 2)
            cout << "The " << i + 1 << " student is smart and hardworking" << endl;
        else
            if(students[i] != -1)
                cout << "The " << i + 1 << " student has written off from the " << origin(students, i) << " student" << endl;
            else
                cout << "The " << i + 1 << " student is lazy and stupid" << endl;

    }



    delete [] students;
    return 0;
}
