#include <iostream>
#include <fstream>
#include "string.h"
#include "hashtable.h"

using namespace std;

bool isLetter(char ch)
{
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
}

void clean(char string[], int size)
{
    for (int i = 0; i < size; i++)
        string[i] = '\0';
}

int main()
{
    HashTable *ht = createHT();
    char nextWord[100] = {""};
    ifstream fin("text.txt");

    if(!fin)
        cout << "File can't be opened";
    else{
        char ch = fin.get();
        while (!fin.eof())
        {
            int i = 0;
            while (isLetter(ch))
            {
                nextWord[i] = ch;
                i++;
                ch = fin.get();
            }

            String *string = createString(nextWord);


            addToHT(ht, string);

            clean(nextWord, i);

            while (!isLetter(ch) && !fin.eof() )
                ch = fin.get();

        }

    }

    printInformation(ht);

    cout << "enter word to find, to exit enter 0" << endl;
    char wordToFind[100] = {""};
    cin >> wordToFind;

    while (wordToFind[0] != '0')
    {
         String* word = createString(wordToFind);
         findWord(ht, word);
         deleteString(word);
         cout << "enter word to find: " << endl;
         cin >> wordToFind;
    }



    deleteHT(ht);


    fin.close();
    return 0;
}
