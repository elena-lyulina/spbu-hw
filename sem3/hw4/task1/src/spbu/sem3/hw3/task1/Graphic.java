package spbu.sem3.hw3.task1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import static java.awt.event.KeyEvent.*;


public class Graphic extends Thread {
    private int windowWidth = 1000;
    private int windowHeight = 500;
    private GraphicsContext gc;
    private Background bg;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private Canon yourCanon;
    private Canon oppCanon;
    private Group root;


    public Graphic(GraphicsContext gc, Group root, PrintWriter writer, BufferedReader reader, Socket socket) {
        this.socket = socket;
        this.writer = writer;
        this.reader = reader;
        this.gc = gc;
        this.root = root;
        yourCanon = new Canon();
        oppCanon = new Canon();
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    private int[][] getCoord(String coord, int n) {
        System.out.println(n + "getCoord n");
        coord = coord.substring(11);
        String[] coordArr = coord.split(" ");
        int[][] arr = new int[2][n];
        for (int i = 0; i < coordArr.length; i+=2){
            arr[0][i/2] = Integer.parseInt(coordArr[i]);
            arr[1][i/2] = Integer.parseInt(coordArr[i + 1]);
        }
        return arr;
    }

    @Override
    public void run() {
        System.out.println("start");
        String response;
        try {
            response = reader.readLine();
            if (response.startsWith("MOUNT COORD")) {
                System.out.println(response);
                bg = new Background(windowWidth, windowHeight);
                int[][] coord = getCoord(response, bg.getN());
                bg.setCoord(coord);
                redraw();
                System.out.println("redraw");
            }
            response = reader.readLine();
            System.out.println(response + " it s response");

            while (true) {
                String command = reader.readLine();
                System.out.println(command);
                if (command.startsWith("LEFT")) {
                    oppCanon.move(bg, "left");
                    System.out.println("move left");
                }
                else if (command.startsWith("RIGHT")) {
                    oppCanon.move(bg, "right");
                    System.out.println("move right");
                }

                else if (command.startsWith("UP")) {
                    oppCanon.rotate("up");
                }
                else if (command.startsWith("DOWN")) {
                    oppCanon.rotate("down");
                }
                else if (command.startsWith("ENTER")) {
                    oppCanon.throwBall(root);
                    System.out.println("turn");
                }
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }

    }



    public void redraw() {
        bg.draw(gc);
        yourCanon.draw(gc);
        oppCanon.draw(gc);
    }

    public void handleKeyEvent(KeyEvent event, Group root) {
        switch(event.getKeyCode()) {
            case VK_ENTER:
                System.out.println("enter");
                yourCanon.throwBall(root);
                writer.println("ENTER");
                break;
            case VK_UP:
                System.out.println("up");
                yourCanon.rotate("up");
                writer.println("UP");
                redraw();
                break;
            case VK_DOWN:
                System.out.println("down");
                yourCanon.rotate("down");
                writer.println("DOWN");
                redraw();
                break;
            case VK_RIGHT:
                System.out.println("right");
                yourCanon.move(bg, "right");
                writer.println("RIGHT");
                redraw();
                break;
            case VK_LEFT:
                System.out.println("left");
                yourCanon.move(bg, "left");
                writer.println("LEFT");
                redraw();
                break;
        }
    }
}




