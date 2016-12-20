#include "huffman.h"
#include "tree.h"
#include "list.h"
#include <iostream>
#include <fstream>


using namespace std;


void makeHuffmanCodes(int symbol[], char codes[256][16], ofstream &fout)
{
    List *list = createList();
    for (int i = 0; i < 256; i++)
    {
        if (symbol[i] != 0)
        {
            Node *node = createNode(nullptr, nullptr, symbol[i], i);
            add(list, node);
        }
    }

    printList(list, fout);
    fout << endl;

    while (size(list) != 1)
    {
        Node *left = removeHead(list);
        Node *right = removeHead(list);
        Node *parent = mergeNodes(left, right);
        add(list, parent);
    }

    Node *root = removeHead(list);
    Tree *tree = createTree(root);

    printTree(tree, fout);
    fout << endl;

    makeCodes(tree, codes);

    deleteTree(tree);
    deleteList(list);
}

void printCodes(char codes[256][16], ofstream &fout)
{
    for (int i = 0; i < 256; i++)
    {
        if (codes[i][0] != '\0')
        {
            char ch = i - '\0';
            fout << ch << " ";
            int j = 0;
            while (codes[i][j] != '\0')
            {
                fout << codes[i][j] << " ";
                j++;
            }
            fout << endl;
        }
    }
}

void convert(char string[], char codes[256][16], ofstream &fout)
{
    int i = 0;
    while (string[i] != '\0')
    {
        int j = 0;
        while (codes[string[i]][j] != '\0')
        {
            fout << codes[string[i]][j];
            j++;
        }
        i++;
    }

    int j = 0;
    while (codes['\n'][j] != '\0')
    {
        fout << codes['\n'][j];
        j++;
    }
}
