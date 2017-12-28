package spbu.sem3.hw3.task1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.PrintWriter;

/** Class for canon. */
public class Canon {
    private int CANON_HEIGHT = 80;
    private int CANON_WIDTH = 120;
    private double STEP = 10;
    private int GUN_LENGTH = 50;
    private double ANGLE_STEP = 0.2;
    private int speed;
    private int SPEED_STEP = 2;
    private int MAX_SPEED = 12;
    private int MIN_SPEED = 6;
    private int WINGOW_WIDTH = 800;

    private Graphic graphic;
    private int ballSize;
    private int SIZE_STEP = 4;
    private int MAX_SIZE = 14;
    private int MIN_SIZE = 2;
    private double angle;
    private int gunX;
    private int gunY;
    private int centerX;
    private int centerY;
    private int targetX;
    private int targetY;
    Timeline timeline;
    private String belongTo;


    public Canon(String belongTo, Graphic graphic) {
        speed = 8;
        ballSize = 6;
        this.graphic = graphic;
        this.belongTo = belongTo;
        if (belongTo.equals("your")) {
            centerX = 20;
            centerY = 200;
            angle = 3;
            targetX = 767;
            targetY = 200;
        }
        else if (belongTo.equals("opp")) {
            centerX = 767;
            centerY = 200;
            angle = 0.2;
            targetX = 20;
            targetY = 200;
        }
    }
    /** This function initializes the position of gun. */
    public void initialize(){
        gunX = centerX - (int) (GUN_LENGTH * Math.cos(angle));
        gunY = centerY - 20 - (int) (GUN_LENGTH * Math.sin(angle));
    }

    public void setTarget(int x, int y) {
        targetX = x;
        targetY = y;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void plusSpeed() {
        if (speed < MAX_SPEED)
            speed += SPEED_STEP;
    }

    public void minusSpeed() {
        if (speed > MIN_SPEED)
            speed -= SPEED_STEP;
    }

    public void plusSize() {
        if (ballSize < MAX_SIZE)
            ballSize += SIZE_STEP;
    }

    public void minusSize() {
        if (ballSize > MIN_SIZE)
            ballSize -= SIZE_STEP;
    }

    /**
     * This function moves the coordinates of canon center to the right or left.
     * @param bg your background
     * @param direction right or left
     */
    public void move(Background bg, String direction) {
        int i = 0;
        switch (direction) {
            case ("right"):
                if (centerX >= WINGOW_WIDTH)
                    return;
                if (centerX % bg.getMountLength() == 0)
                    centerX++;
                i = (centerX / bg.getMountLength()) + 1;
                break;
            case ("left"):
                if (centerX < 0)
                    return;
                if (centerX % bg.getMountLength() == 0)
                    centerX--;
                i = (centerX / bg.getMountLength());
                break;
        }

        double cat1 = bg.getCoord()[0][i] - centerX;
        double cat2 = bg.getCoord()[1][i] - centerY;
        double hypo = Math.sqrt(cat1 * cat1 + cat2  * cat2);
        double cos = cat1 / hypo;
        double sin = cat2 / hypo;

        centerX += cos * STEP;
        centerY += sin * STEP;
    }

    public void drawBelong(GraphicsContext gc) {
        if (belongTo.equals("your")) {
            Color color = Color.rgb(55, 64, 38);
            gc.setStroke(Color.web(color.toString()));
            gc.setLineWidth(4);
            gc.fillText("you", centerX + CANON_WIDTH / 2,
                    centerY - CANON_HEIGHT / 2);
        }
    }

    /** Function for drawing Canon.
     * @param gc GraphicContext you use for drawing
     */
    public void draw(GraphicsContext gc) {
        Color color = Color.rgb(55, 64, 38);
        gc.setStroke(Color.web(color.toString()));
        gc.setLineWidth(4);
        initialize();
        gc.strokeLine(centerX, centerY - CANON_HEIGHT / 4, gunX, gunY);
        Image image = new Image("canon.png");
        gc.drawImage(image, centerX - CANON_WIDTH / 2,
                centerY - CANON_HEIGHT / 2, CANON_WIDTH, CANON_HEIGHT);

    }
    /**
     * rotates the gun at an ANGLE_STEP.
     * @param direction down or up
     */
    public void rotate(String direction){
        if (direction.equals("down"))
            angle += ANGLE_STEP;
        else if(direction.equals("up"))
            angle -= ANGLE_STEP;
    }

    private boolean equalWithError(double a, int b, int error) {
        return Math.abs(a - b) <= error;
    }

    /**
     * throws the ball.
     * @param root your Group
     */
    public void throwBall(Group root, PrintWriter writer, GraphicsContext gc){
        int ballRadius = ballSize;
        Circle ball = new Circle(ballRadius, Color.rgb(82, 82, 82));
        Platform.runLater(() -> {
            root.getChildren().add(ball);
        });
        final int currentSpeed = speed;

        ball.relocate(gunX - ballRadius, gunY - ballRadius);
        timeline = new Timeline(new KeyFrame(Duration.millis(40),
                new EventHandler<ActionEvent>() {
                    double g = 0;
                    double cos = Math.cos(angle);
                    double sin = Math.sin(angle);
                    boolean boom = false;
                    @Override
                    public void handle(ActionEvent t) {
                        if (equalWithError(ball.getLayoutX(), targetX, CANON_WIDTH/2 - ballRadius) &&
                                equalWithError(ball.getLayoutY(), targetY, CANON_HEIGHT/2 - ballRadius)) {
                            writer.println("WIN");
                            if (!boom) {
                                drawBoom(gc, targetX, targetY);
                                boom = true;
                            }
                        }
                        ball.setLayoutX(ball.getLayoutX() - currentSpeed * cos);
                        ball.setLayoutY(ball.getLayoutY() - currentSpeed * sin + g);
                        g += 0.15;
                    }
                }));
        timeline.setCycleCount(200);
        timeline.play();
    }

    public void drawBoom(GraphicsContext gc, int x, int y) {
        Image image = new Image("boom.png");
        gc.drawImage(image, x - 50, y - 50, 100, 100);
    }
}

