#pragma once
#include "string.h"

const int tableSize = 1500;

struct HashTable;

HashTable *createHT();
void addToHT(HashTable *ht, String *string);
void printHT(HashTable *ht);
void printInformation(HashTable *ht);
void findWord(HashTable *ht, String *word);
void deleteHT(HashTable *ht);
