package spbu.sem3.hw3.task1;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.awt.*;
import java.security.Key;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        GraphicsContext gc;
        Group root = new Group();
        primaryStage.setTitle("The Canon");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        Canvas canvas = new Canvas(1000, 500);
        root.getChildren().add(canvas);
        primaryStage.sizeToScene();
        gc = canvas.getGraphicsContext2D();

        Game game = new Game(1000, 500, gc);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                game.handleKeyEvent(event, root);
            }
        });
    }

    public static void main(String[] args) { launch(args); }
}
