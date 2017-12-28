package spbu.sem3.hw3.task1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/** Class for canon. */
public class Canon {
    private int gunX;
    private int gunY;
    private int centerX = 20;
    private int centerY = 200;
    private int canonHeight = 80;
    private int canonWidth = 120;
    private double step = 10;
    private int gunLength = 50;
    private double angle = 3;
    private double angleStep = 0.2;
    private int speed = 8;
    private int windowWidth = 1000;


    /** This function initializes the position of gun. */
    public void initialize(){
        gunX = centerX - (int) (gunLength * Math.cos(angle));
        gunY = centerY - 20 - (int) (gunLength * Math.sin(angle));
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
                if (centerX >= windowWidth)
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

        centerX += cos * step;
        centerY += sin * step;
    }

    /** Function for drawing Canon.
     * @param gc GraphicContext you use for drawing
     */
    public void draw(GraphicsContext gc) {
        Color color = Color.rgb(55, 64, 38);
        gc.setStroke(Color.web(color.toString()));
        gc.setLineWidth(4);
        initialize();
        gc.strokeLine(centerX, centerY - canonHeight / 4, gunX, gunY);
        // Image img = new Image(getClass().getResourceAsStream("canon.png"));
        Image image = new Image("canon.png");
        //Image img = new ImageIcon(getClass().getResource("canon.png")).getImage();
        gc.drawImage(image, centerX - canonWidth / 2,
                centerY - canonHeight / 2, canonWidth, canonHeight);
    }
    /**
     * rotates the gun at an angleStep.
     * @param direction down or up
     */
    public void rotate(String direction){
        if (direction.equals("down"))
            angle += angleStep;
        else if(direction.equals("up"))
            angle -= angleStep;
    }

    /**
     * throws the ball.
     * @param root your Group
     */
    public void throwBall(Group root){
        int ballRadius = 4;
        Circle ball = new Circle(ballRadius, Color.rgb(82, 82, 82));
        root.getChildren().add(ball);
        ball.relocate(gunX - ballRadius, gunY - ballRadius);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40),
                new EventHandler<ActionEvent>() {
                    double g = 0;
                    double cos = Math.cos(angle);
                    double sin = Math.sin(angle);
                    @Override
                    public void handle(ActionEvent t) {
                        ball.setLayoutX(ball.getLayoutX() - speed * cos);
                        ball.setLayoutY(ball.getLayoutY() - speed * sin + g);
                        g += 0.15;
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

