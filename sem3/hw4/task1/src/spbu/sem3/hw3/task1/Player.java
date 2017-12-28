package spbu.sem3.hw3.task1;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private Game game;
    private char mark;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Canon canon;

    public Canon getCanon() {
        return canon;
    }

    public Player(Socket socket, char name, Game game) {
        this.canon = new Canon();
        this.game = game;
        this.socket = socket;
        this.mark = name;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("MESSAGE Waiting for opponent to connect");
            writer.println("sooqa");

        } catch (IOException e) {
            System.out.println("ERROR : " + e);
        }
    }

    public void run() {
        writer.println("MESSAGE All players connected");


//        крч тут надо все время проверять конец игры или нет
        writer.println("MESSAGE All players connected");

    }
}