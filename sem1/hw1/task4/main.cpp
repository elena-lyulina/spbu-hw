#include <iostream>
using namespace std;

int main( )
{
    int numberOfLuckyTickets = 0;
    const int numberOfOptions = 28;
    int options[numberOfOptions] = {0,};
    const int largestNumber = 9;
    for (int i = 0; i <= largestNumber; i++)
    {
        for (int j = 0; j <= largestNumber; j++)
        {
            for (int y = 0; y <= largestNumber; y++)
            {
                int figamount = i + j + y;
                options[i + j + y]++;
            }
        }
    }
    for (int i = 0; i < numberOfOptions; i++)
    {
        numberOfLuckyTickets = numberOfLuckyTickets + options[i] * options[i];
    }
    cout << "Number of lucky tickets is " << numberOfLuckyTickets << endl;
    return 0;
}
