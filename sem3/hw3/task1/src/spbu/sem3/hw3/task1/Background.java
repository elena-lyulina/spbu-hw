package spbu.sem3.hw3.task1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

/** Class for background. */
public class Background {

    private int windowWidth;
    private int windowHeight;
    private int n = 12;
    private int[][] coord = new int[2][n];
    private int mountLength = 100;

    public Background(int windowWidth, int windowHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initializeMount();
    }

    /** Initialization coordinates of mountains with random numbers. */
    public void initializeMount() {
        coord[0][0] = 0;
        coord[1][0] = 200;
        for (int i = 1; i < n; i++) {
            coord[1][i] = (int) (Math.random() * 350 + 100);
            coord[0][i] = i * mountLength;
        }
    }

    /**
     * drawing mountains and sky.
     * @param gc GraphicContext which you use for drawing
     */
    public  void draw(GraphicsContext gc) {
        drawSky(gc);
        drawMount(gc);
    }


    /**
     * drawing mountains.
     * @param gc GraphicContext which you use for drawing
     */
    private void drawMount(GraphicsContext gc) {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.2, Color.WHITE),
                new Stop(1.0, Color.GREY)
        ));
        gc.beginPath();
        gc.moveTo(coord[0][0], coord[1][0]);
        for (int i = 1; i < n - 1; i++)
            gc.lineTo(coord[0][i], coord[1][i]);
        gc.lineTo(coord[0][n - 1], windowHeight);
        gc.lineTo(coord[0][0], windowHeight);
        gc.lineTo(coord[0][0], coord[1][0]);
        gc.fill();
    }

    /**
     * drawing mountains and sky.
     * @param gc GraphicContext which you use for drawing
     */
    private void drawSky(GraphicsContext gc) {
        gc.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(1.1, Color.WHITE),
                new Stop(0.0, Color.rgb(108, 166, 205))
        ));
        gc.fillRect(0, 0 , windowWidth, windowHeight);
    }


    /**
     * getter for length of mountain.
     * @return length of mountain
     */
    public int getMountLength() {
        return mountLength;
    }


    /**
     * getter for coordinates of mountains.
     * @return double array with coordinates
     */
    public int[][] getCoord(){
        return coord;
    }
}
