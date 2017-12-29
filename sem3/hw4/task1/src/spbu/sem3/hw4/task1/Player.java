package spbu.sem3.hw4.task1;

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

    public Player(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("MESSAGE Waiting for opponent to connect");

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
            while (!win) {
                String command = reader.readLine();
                if (command!= null && command.startsWith("WIN")){
                    win = true;
                    opponent.setWin(true);
                    opponent.getWriter().println("WINopp ".concat(command.substring(4)));
                    writer.println("WINyou ".concat(command.substring(4)));
                }
                 else
                     opponent.getWriter().println(command);
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }
}