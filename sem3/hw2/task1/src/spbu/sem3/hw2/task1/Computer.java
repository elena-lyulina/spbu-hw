package spbu.sem3.hw2.task1;

/* class for computer */
public class Computer {
    private boolean isInfected;
    private char OS;
    private double virusProbability;

    public Computer(boolean isInfected, char OS) throws WrongOSType {
        this.isInfected = isInfected;
        this.OS = OS;
        switch (OS) {
            case ('W') :
                virusProbability = 0.5;
                break;
            case ('M') :
                virusProbability = 0.2;
                break;
            case ('L') :
                virusProbability = 0.1;
                break;
            default:
                throw new WrongOSType();
        }
    }

    /* getters for fields */
    public boolean isInfected() {
        return isInfected;
    }

    public double getVirusProbability(){ return virusProbability; }

    public void setInfected(boolean isInfected) {
        this.isInfected = isInfected;
    }

    /* returns state strings with information about OS type and virus probability */
    public String state(double virusProbability) {
        String isInfected = isInfected() ? "yes" : "no";
        return (isInfected + OS + "[" + virusProbability + "]");
    }

    /* added color just for fun */
    public String colorState(double virusProbability) {
        String state = "";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RED = "\u001B[31m";

        String color = isInfected ? ANSI_RED : ANSI_GREEN;
        state = state.concat(color + OS + "[" + virusProbability + "]" + ANSI_RESET);

        return state;
    }

    /* in case of adding computer not with Windows, Linux or MacOS */
    public static class WrongOSType extends Exception {
        public WrongOSType() {
            super("wrong OS type");
        }
    }
}
