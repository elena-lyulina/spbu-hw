package spbu.sem3.hw4.task1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/** Class for background. */
public class Background {
    private int MOUNT_LENGTH = 100;
    private int windowWidth;
    private int windowHeight;
    private int n;
    private int[][] coord;

    public Background(int windowWidth, int windowHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        n = (int)Math.floor(windowWidth / MOUNT_LENGTH) + 1;
        coord = new int[2][n];
        initializeMount(coord, n);
    }

    /** Initializes coordinates of mountains with random numbers. */
    public void initializeMount(int[][] coord, int n) {
        coord[0][0] = 0;
        coord[1][0] = 200;
        for (int i = 1; i < n; i++) {
            coord[1][i] = (int) (Math.random() * 350 + 100);
//            coord[1][i] = 200 * (i % 2);
            coord[0][i] = i * MOUNT_LENGTH;
        }
       coord[1][n - 1] = 200;
    }

    public void setCoord(int[][] coord) {
        this.coord = coord;
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
        for (int i = 1; i < n; i++)
            gc.lineTo(coord[0][i], coord[1][i]);
        gc.lineTo(coord[0][n - 1], windowHeight);
        gc.lineTo(coord[0][0], windowHeight);
        gc.lineTo(coord[0][0], coord[1][0]);
        gc.fill();
        gc.setFill(Color.web(Color.BLACK.toString()));
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

        Color color = Color.BLACK;
        gc.setFill(Color.web(color.toString()));
        gc.setLineWidth(10);
        gc.fillText("W - plus speed; S - minus speed; A - plus ball size; D - minus ball size", 20, 20);
    }


    /**
     * getter for length of mountain.
     * @return length of mountain
     */
    public int getMountLength() {
        return MOUNT_LENGTH;
    }

    public int getN() { return  n; }


    /**
     * getter for coordinates of mountains.
     * @return double array with coordinates
     */
    public int[][] getCoord(){
        return coord;
    }

    public int getY_at_X(int x) {
        if (x <= 0 || x >= windowWidth)
            return 200;
        int i = (x) / MOUNT_LENGTH;
        int y = coord[1][i+1] + (((coord[0][i + 1] - x) * (coord[1][i] - coord[1][i+1])) / (coord[0][i + 1] - coord[0][i]));
        return y;
    }


}
