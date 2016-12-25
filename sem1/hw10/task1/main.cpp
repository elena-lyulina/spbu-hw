#include <iostream>
#include <fstream>
#include "list.h"
#include "huffman.h"

using namespace std;

int main()
{
    int symbol[256] = {0};
    char string[1000] = {""};
    char codes[256][16] = {""};

    ifstream fin("text.txt");

    if(!fin)
        cout << "File can't be opened";
    else{
        while (!fin.eof())
        {
            fin.getline(string, 1000);
            int j = 0;
            while(string[j] != '\0')
            {
                symbol[string[j]]++;
                j++;
            }
            symbol['\n']++;
        }
    }
    fin.close();

    ofstream fout;
    fout.open("huffmanCode.txt", ios::out);

    makeHuffmanCodes(symbol, codes, fout);
    //printCodes(codes, fout);

    ifstream fin2("text.txt");
    if(!fin2)
        cout << "File can't be opened";
    else{
        while (!fin2.eof())
        {
            fin2.getline(string, 1000);
            convert(string, codes, fout);
        }
    }

    fin2.close();
    fout.close();

    return 0;

}
