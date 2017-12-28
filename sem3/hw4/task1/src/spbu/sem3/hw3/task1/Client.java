package spbu.sem3.hw3.task1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private static int PORT = 2000;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private JFXPanel jfx;
    private JPanel panel;
    private JFrame frame = new JFrame("Tic Tac Toe");


    public Client(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, PORT);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        jfx = new JFXPanel();
        jfx.setSize(1000, 500);
        frame.add(jfx);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
//        jfx.setLocation(10, 40);
//        start();
    }

    public void start() throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 500);
        Canvas canvas = new Canvas(1000, 500);
        root.getChildren().add(canvas);

        jfx.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Graphic graphic = new Graphic(gc, root, writer, reader, socket);
//        Runnable graphic = new Graphic(gc, root, writer, reader, socket); // создаём класс и передаём в него параметр
        graphic.start();
        writer.println("privet");
        System.out.println("privet1");

        jfx.addKeyListener(new KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
//                graphic.handleKeyEvent(e, root);
                writer.println("key pressed");
                System.out.println("aaa");
            }
        });


    }

        public static void main(String[] args) throws Exception {
        while(true) {
            Client client = new Client("localhost");
            client.start();
            break;
        }
        }



}
