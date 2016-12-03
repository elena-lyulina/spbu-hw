#include <iostream>

using namespace std;

bool findComment(char str[], int i, bool multilineComm, bool string)
{
    bool isComment = (str[i - 1] == '/') && (str[i] == '/') && (multilineComm == false) && (string == false);
    if (isComment)
    {
        cout << str[i - 1] << str[i];
        i++;
    }
    return isComment;
}

bool begMultilineComm(char str[], int i, bool string)
{
    return (str[i - 1] == '/') && (str[i] == '*') && !string;
}

bool endMultilineComm(char str[], int i, bool string)
{
    return (str[i - 1] == '*') && (str[i] == '/') && !string;
}




int main()
{
    FILE *f = fopen("file.txt" , "r");
    char str[1000];

    if (f == NULL)
        cout << "File can't be opened";
    else
    {
        bool multilineComm = false;
        bool string = false;


        while (!feof(f))
        {
            fgets(str, 1000, f);
            bool comment = false;
            int i = 0;
            while (str[i] != '\n')
            {
                if (comment && !string && !multilineComm)
                    cout << str[i];
                else
                {
                    comment = findComment(str, i, multilineComm, string);

                    if (begMultilineComm(str, i, string))
                        multilineComm = true;

                    if (endMultilineComm(str, i, string))
                        multilineComm = false;

                    if(str[i] == '"' && !multilineComm)
                        string = !string;
                }
                i++;
            }
            if (comment && !string && !multilineComm)
                cout << endl;
        }
    }


    fclose(f);
    return 0;
}
