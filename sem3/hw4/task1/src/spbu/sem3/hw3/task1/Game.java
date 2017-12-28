package spbu.sem3.hw3.task1;

public class Game {
    private Player player1;
    private Player player2;
    private boolean end;
    private int n;
    private int windowsWidth;
    private int windowHeight;

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getN() { return n; }


}
