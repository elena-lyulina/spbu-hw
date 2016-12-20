#include "huffman.h"
#include "tree.h"

void huffmanUncode(char treeString[], char codeString[], ofstream &fout)
{
    Tree *tree = createTree();
    convertToTree(tree, treeString);

    //printTree(tree, fout);

    printText(codeString, tree, fout);

    deleteTree(tree);
}
