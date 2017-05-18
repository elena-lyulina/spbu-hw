package spbu.sem2.hw7.task2;

/** Class for Game. */
public class Game {
    public static int buttons[][] = new int[3][3];

    /** initializes array of button with different values from 3 to 11.
     * value 1 will be mean X, value 2 will be mean O*/
    public static void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = i * 3 + j + 3;
            }
        }
    }

    /** checks the win. */
    public static int checkWin() {
        if (horizontalLine() != -1)
            return horizontalLine();
        if (verticalLine() != -1)
            return verticalLine();
        if (diagonal() != -1)
            return diagonal();
        return -1;
    }

    /* checks win in horizontal lines. */
    public static int horizontalLine() {
        if (buttons[0][0] == buttons[0][1] && buttons[0][1] == buttons[0][2]) {
            return buttons[0][0];
        }
        else if (buttons[1][0] == buttons[1][1] && buttons[1][1] == buttons[1][2]) {
            return buttons[1][0];
        }
         else if  (buttons[2][0] == buttons[2][1] && buttons[2][1] == buttons[2][2]) {
            return buttons[2][0];
        }
        return -1;
    }

    /* checks win in vertical lines. */
    public static int verticalLine() {
        if (buttons[0][0] == buttons[1][0] && buttons[1][0] == buttons[2][0]) {
            return buttons[0][0];
        }
        else if (buttons[0][1] == buttons[1][1] && buttons[1][1] == buttons[2][1]) {
            return buttons[0][1];
        }
        else if (buttons[0][2] == buttons[1][2] && buttons[1][2] == buttons[2][2]) {
            return buttons[0][2];
        }
        return -1;
    }

    /* checks win in diagonals. */
    public static int diagonal() {
        if (buttons[0][0] == buttons[1][1] && buttons[1][1] == buttons[2][2]) {
            return buttons[0][0];
        }
        else if (buttons[0][2] == buttons[1][1] && buttons[1][1] == buttons[2][0]) {
            return buttons[0][2];
        }
        return -1;
    }

    /** checks if there are no win and no empty buttons. */
    public static boolean noResults() {
        if (checkWin() == -1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] != 2 && buttons[i][j] != 1)
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}
