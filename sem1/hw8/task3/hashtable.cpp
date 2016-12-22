#include <iostream>
#include "hashtable.h"
#include "string.h"
#include "list.h"
#include <string.h>

using namespace std;

struct HashTable
{
    List* table[tableSize];
};


HashTable *createHT()
{
    HashTable *ht = new HashTable();
    for (int i = 0; i < tableSize; i++)
        ht->table[i] = createList();
}


int hashString(String *stringToHash)
{
    int b = 378551 % tableSize;
    int a = 63689 % tableSize;
    int hash = 0;
    char *string = convertToChar(stringToHash);

    for(int i = 0; i < size(stringToHash); i++)
    {
        hash = (hash * a + string[i]) % tableSize;
        a = (a * b) % tableSize;
    }

    delete [] string;
    return hash;
}

void addToHT(HashTable *ht, String *string)
{
    int index = hashString(string);
    addToList(ht->table[index], string);
}

void printHT(HashTable *ht)
{
    for (int i = 0; i < tableSize; i++)
    {
        cout << i << " ";
        printList(ht->table[i]);
    }
}

void printInformation(HashTable *ht)
{
    int sizeOfHt = 0;
    double loadFactor = 0;
    int emptyLists = 0;
    double averageListSize = 0;
    int maxSize = 0;
    int indexOfMaxList = 0;
    int numberOfWords = 0;

    for (int i = 0; i < tableSize; i++)
    {
        sizeOfHt += sizeOfList(ht->table[i]);
        numberOfWords += amountOfWords(ht->table[i]);
        if (isListEmpty(ht->table[i]))
            emptyLists++;

        if (sizeOfList(ht->table[i]) > maxSize)
        {
            maxSize =  sizeOfList(ht->table[i]);
            indexOfMaxList = i;
        }
    }

    loadFactor = (double) sizeOfHt / tableSize;
    averageListSize = (double) sizeOfHt / (tableSize - emptyLists);

    cout << "number of words: " << numberOfWords << endl;
    cout << "load factor: " << loadFactor << endl;
    cout << "amount of empty lists: " << emptyLists << endl;
    cout << "average list' size: " << averageListSize << endl;
    cout << "max list' size: " << maxSize << endl;
    cout << "max list: ";
    printList(ht->table[indexOfMaxList]);
}

void findWord(HashTable *ht, String *word)
{
    int index = hashString(word);
    findElement(ht->table[index], word);
}


void deleteHT(HashTable *ht)
{
    for (int i = 0; i < tableSize; i++)
        deleteList(ht->table[i]);
    delete [] ht->table;
    delete ht;
}


