#pragma once
#include "list.h"

void makeHuffmanCodes(int symbol[], char codes[256][16], ofstream &fout);
void printCodes(char codes[256][16], ofstream &fout);
void convert(char string[], char codes[256][16], ofstream &fout);
