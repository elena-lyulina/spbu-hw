package spbu.sem2.hw7.task2;

/**
 * Class for Game.
 */
public class Game {
    public static int n = 3;
    public static int buttons[][] = new int[n][n];

    /**
     * initializes array of button with different values from 3 to 11.
     * value 1 will be mean X, value 2 will be mean O
     */
    public static void initialize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = i * n + j + n;
            }
        }
    }

    /**
     * checks the win.
     */
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
        boolean result = true;
        for (int i = 0; i < n; i++) {
            int button = buttons[i][0];
            for (int j = 0; j < n; j++) {
                if (result)
                result = buttons[i][j] == button;
            }
            if (result) return buttons[i][0];
            result = true;
        }
        return -1;
    }

    /* checks win in vertical lines. */
    public static int verticalLine() {
        boolean result = true;
        for (int i = 0; i < n; i++) {
            int button = buttons[0][i];
            for (int j = 0; j < n; j++) {
                if (result)
                result = buttons[j][i] == button;
            }
            if (result) return buttons[0][i];
            result = true;
        }
        return -1;
    }

    /* checks win in diagonals. */
    public static int diagonal() {
        boolean result1 = true;
        boolean result2 = true;
        int button1 = buttons[0][0];
        int button2 = buttons[0][n - 1];
        for (int i = 0; i < n; i++) {
            if (result1)
            result1 = buttons[i][i] == button1;
            if (result2)
            result2 = buttons[i][n - 1 - i] == button2;
        }
        if (result1) return buttons[0][0];
        if (result2) return buttons[0][n - 1];
        return -1;
    }

    /**
     * checks if there are no win and no empty buttons.
     */
    public static boolean noResults() {
        if (checkWin() == -1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (buttons[i][j] != 2 && buttons[i][j] != 1)
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}
