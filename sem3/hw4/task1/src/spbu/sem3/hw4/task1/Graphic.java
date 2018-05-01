package spbu.sem3.hw4.task1;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import static java.awt.event.KeyEvent.*;

public class Graphic extends Thread {
    private int WINDOW_WIDTH = 800;
    private int WINDOW_HEIGHT = 500;
    private GraphicsContext gc;
    private Background bg;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private Canon yourCanon;
    private Canon oppCanon;
    private Group root;
    private boolean win;


    public Graphic(GraphicsContext gc, Group root, PrintWriter writer, BufferedReader reader, Socket socket) {
        this.socket = socket;
        this.writer = writer;
        this.reader = reader;
        this.gc = gc;
        this.root = root;
        yourCanon = new Canon("your", this);
        oppCanon = new Canon("opp", this);
        win = false;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    private int[][] getCoord(String coord, int n) {
        coord = coord.substring(11);
        String[] coordArr = coord.split(" ");
        int[][] arr = new int[2][n];
        for (int i = 0; i < coordArr.length; i+=2){
            arr[0][i/2] = Integer.parseInt(coordArr[i]);
            arr[1][i/2] = Integer.parseInt(coordArr[i + 1]);
        }
        return arr;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int[] parseCoord(String command) {
        String[] words = command.split(" ");
        int[] coords = new int[words.length - 1];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = Integer.parseInt(words[i + 1]);

        }
        return coords;
    }

    public void winCommand(String command) {
        if (yourCanon.timeline != null) {
            yourCanon.timeline.stop();
        }
        if (oppCanon.timeline != null) {
            oppCanon.timeline.stop();
        }

        int[] coord = parseCoord(command);
        if (command.startsWith("WINyou")) {
            drawEnd("WINNER!");
            yourCanon.drawBoom(gc, coord[0], coord[1]);
        }
        else if (command.startsWith("WINopp")) {
            drawEnd("LOSER!");
            oppCanon.drawBoom(gc, WINDOW_WIDTH - coord[0], coord[1]);
        }
    }


    public void run () {
        System.out.println("start");
        String response;
        try {
            response = reader.readLine();
            System.out.println(response);
            if (response.startsWith("MOUNT COORD")) {
                bg = new Background(WINDOW_WIDTH, WINDOW_HEIGHT);
                int[][] coord = getCoord(response, bg.getN());
                bg.setCoord(coord);
                redraw();
            }
            while (!win) {
                String command = reader.readLine();

                if (command.startsWith("WIN")) {
                    winCommand(command);
                    win = true;
                }
                else if (command.startsWith("LEFT") || command.startsWith("RIGHT")) {
                    int[] coord = parseCoord(command);
                    oppCanon.moveTo(WINDOW_WIDTH - coord[0], coord[1]);
                    yourCanon.setTarget(oppCanon.getCenterX(), oppCanon.getCenterY());
                    redraw();
                }
                else if (command.startsWith("UP")) {
                    oppCanon.rotate("down");
                    redraw();
                }
                else if (command.startsWith("DOWN")) {
                    oppCanon.rotate("up");
                    redraw();
                }
                else if (command.startsWith("ENTER")) {
                    oppCanon.throwBall(root, writer, gc, bg);
                    redraw();
                }
                else if (command.startsWith("PLUSSPEED")) {
                    oppCanon.plusSpeed();
                }
                else if (command.startsWith("MINUSSPEED")) {
                    oppCanon.minusSpeed();
                }
                else if (command.startsWith("PLUSSIZE")) {
                    oppCanon.plusSize();
                }
                else if (command.startsWith("MINUSSIZE")) {
                    oppCanon.minusSize();
                }
            }
            System.out.println("the end");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                yourCanon.throwBall(root, writer, gc, bg);
                writer.println("ENTER");
                break;
            case VK_UP:
                yourCanon.rotate("up");
                writer.println("UP");
                redraw();
                break;
            case VK_DOWN:
                yourCanon.rotate("down");
                writer.println("DOWN");
                redraw();
                break;
            case VK_RIGHT:
                yourCanon.move(bg, "right");
                oppCanon.setTarget(yourCanon.getCenterX(), yourCanon.getCenterY());
                writer.println("RIGHT"+ " " + yourCanon.getCenterX() + " " +  yourCanon.getCenterY());
                redraw();
                break;
            case VK_LEFT:
                yourCanon.move(bg, "left");
                oppCanon.setTarget(yourCanon.getCenterX(), yourCanon.getCenterY());
                writer.println("LEFT" + " " + yourCanon.getCenterX() + " " +  yourCanon.getCenterY());
                redraw();
                break;
            case VK_W:
                yourCanon.plusSpeed();
                writer.println("PLUSSPEED");
                break;
            case VK_S:
                yourCanon.minusSpeed();
                writer.println("MINUSSPEED");
                break;
            case VK_A:
                yourCanon.plusSize();
                writer.println("PLUSSIZE");
                break;
            case VK_D:
                yourCanon.minusSize();
                writer.println("MINUSSIZE");
                break;
        }
    }

    public void drawEnd(String win) {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(1.1, Color.WHITE),
                new Stop(0.0, Color.PINK)
        ));
        gc.fillRect(0, 0 , WINDOW_WIDTH, WINDOW_HEIGHT);
        gc.setFill(Color.web(Color.BLACK.toString()));
        gc.fillText(win, WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
    }


}