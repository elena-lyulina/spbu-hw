package spbu.sem3.hw3.task1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Game {
    private int windowWidth;
    private int windowHeight;
    private GraphicsContext gc;
    private Background bg;
    private Canon canon;

    public Game(int windowWidth, int windowHeight, GraphicsContext gc) {
        this.gc = gc;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gc = gc;

        bg = new Background(windowWidth, windowHeight);
        canon = new Canon();

        redraw();
    }

    public void redraw() {
        bg.draw(gc);
        canon.draw(gc);
    }

    public void handleKeyEvent(KeyEvent event, Group root){
        switch(event.getCode()) {
            case ENTER:
                canon.throwBall(root);
                break;
            case UP:
                canon.rotate("up");
                redraw();
                break;
            case DOWN:
                canon.rotate("down");
                redraw();
                break;
            case RIGHT:
                canon.move(bg, "right", windowWidth);
                redraw();
                break;
            case LEFT:
                canon.move(bg, "left", windowWidth);
                redraw();
                break;
        }
    }
}




