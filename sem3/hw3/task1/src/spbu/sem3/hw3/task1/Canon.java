package spbu.sem3.hw3.task1;

/** Class for canon. */
public class Canon {
    public int gunX;
    public int gunY;
    public int centerX = 20;
    public int centerY = 200;
    public int canonHeight = 80;
    public int canonWidth = 120;
    public double step = 10;
    private int gunLength = 50;
    public double angle = 3;
    public double angleStep = 0.2;
    public int speed = 8;

    /** This function initializes the position of gun. */
    public void initialize(){
        gunX = centerX - (int) (gunLength * Math.cos(angle));
        gunY = centerY - 20 - (int) (gunLength * Math.sin(angle));
    }

    /**
     * This function moves the coordinates of canon center to the right or left.
     * @param mount mountains on which the canon moves
     * @param direction right or left
     */
    public void move(Mountains mount, String direction) {
        int i = 0;
        switch (direction) {
            case ("right"):
                if (centerX % mount.mountLength == 0) centerX++;
                i = (centerX / mount.mountLength) + 1;
                break;
            case ("left"):
                if (centerX % mount.mountLength == 0) centerX--;
                i = (centerX / mount.mountLength);
        }

        double cat1 = mount.coord[0][i] - centerX;
        double cat2 = mount.coord[1][i] - centerY;
        double hypo = Math.sqrt(cat1 * cat1 + cat2  * cat2);
        double cos = cat1 / hypo;
        double sin = cat2 / hypo;

        centerX += cos * step;
        centerY += sin * step;
    }
}
