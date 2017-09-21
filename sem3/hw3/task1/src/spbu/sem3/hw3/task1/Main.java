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


public class Main extends Application {
    Mountains mount = new Mountains();
    Canon canon = new Canon();
    GraphicsContext gc;
    Group root = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The Canon");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Canvas canvas = new Canvas(1000, 500);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        drawSky();
        mount.initialize();
        drawMountains();
        drawCanon();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case ENTER:
                        throwBall();
                        break;
                    case W:
                        canon.angle -= canon.angleStep;
                        redraw();
                        break;
                    case S:
                        canon.angle += canon.angleStep;
                        redraw();
                        break;
                    case D:
                        canon.move(mount, "right");
                        redraw();
                        break;
                    case A:
                        canon.move(mount, "left");
                        redraw();
                        break;
                }
            }
        });
    }

    /** Function for drawing Mountains. */
    public void drawMountains() {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.2, Color.WHITE),
                new Stop(1.0, Color.GREY)
        ));
        gc.beginPath();
        gc.moveTo(mount.coord[0][0], mount.coord[1][0]);
        for (int i = 1; i < mount.n - 1; i++)
            gc.lineTo(mount.coord[0][i], mount.coord[1][i]);
        gc.lineTo(mount.coord[0][mount.n - 1], 500);
        gc.lineTo(mount.coord[0][0], 500);
        gc.lineTo(mount.coord[0][0], mount.coord[1][0]);
        gc.fill();
    }

    /** Function for drawing Canon. */
    public void drawCanon() {
        gc.setStroke(Color.rgb(55, 64, 38));
        gc.setLineWidth(4);
        canon.initialize();
        gc.strokeLine(canon.centerX, canon.centerY - canon.canonHeight / 4, canon.gunX, canon.gunY);
        Image img = new Image(getClass().getResourceAsStream("canon1.png"));
        gc.drawImage(img, canon.centerX - canon.canonWidth / 2,
                canon.centerY - canon.canonHeight / 2, canon.canonWidth, canon.canonHeight);
    }

    /** Function for drawing Sky. */
    public void drawSky() {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(1.1, Color.WHITE),
                new Stop(0.0, Color.rgb(108, 166, 205))
        ));
        gc.fillRect(0, 0 , 1000, 500);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("default", 22));
        gc.fillText("Use WASD like true gamer!", 10, 30);
    }

    /* Function for redrawing everything. */
    public void redraw() {
        drawSky();
        drawMountains();
        drawCanon();
    }

    /* Function for drawing flying ball. */
    public void throwBall() {
        int ballRadius = 4;
        Circle ball = new Circle(ballRadius, Color.rgb(82, 82, 82));
        root.getChildren().add(ball);
        ball.relocate(canon.gunX - ballRadius, canon.gunY - ballRadius);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40),
                new EventHandler<ActionEvent>() {
                    double g = 0;
                    double cos = Math.cos(canon.angle);
                    double sin = Math.sin(canon.angle);
                    @Override
                    public void handle(ActionEvent t) {
                        ball.setLayoutX(ball.getLayoutX() - canon.speed * cos);
                        ball.setLayoutY(ball.getLayoutY() - canon.speed * sin + g);
                        g += 0.15;
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) { launch(args); }
}
