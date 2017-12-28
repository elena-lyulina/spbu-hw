package spbu.sem3.hw3.task1;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Player opponent;
    private boolean win;

    public Player(Socket socket, char name) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("MESSAGE Waiting for opponent to connect");
            writer.println("sooqa");

        } catch (IOException e) {
            System.out.println("ERROR : " + e);
        }
        win = false;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void run() {
        try {
            writer.println("MESSAGE All players connected");
            while (true) {
                String command = reader.readLine();
                if (command.startsWith("WIN")){
                    win = true;
                    opponent.setWin(true);
                    System.out.println("win player");
                }
                opponent.getWriter().println(command);
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }
}