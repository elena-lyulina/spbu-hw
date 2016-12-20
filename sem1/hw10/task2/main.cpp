#include <iostream>
#include <fstream>
#include "huffman.h"

using namespace std;

int main()
{
    ifstream fin("huffmanCode.txt");

    char treeString[10000] = {""};
    char codeString[10000] = {""};

    if(!fin)
        cout << "File can't be opened";
    else
    {
        fin.getline(treeString, 10000);
        fin.getline(codeString, 10000);
    }

    fin.close();


    ofstream fout;
    fout.open("text.txt", ios::out);

    huffmanUncode(treeString, codeString, fout);

    fout.close();

    return 0;

}
