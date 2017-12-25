package spbu.sem3.hw2.task1;

import spbu.sem3.hw2.task1.Computer;

/** class for network */
public class Network {
    int[][] connectedComps;
    Computer[] computers;

    public Network(Computer[] computers, int[][] connectedComps) {
        this.computers = computers;
        this.connectedComps = connectedComps;
    }

    /**
     * makes infections after one iteration.
     * @param randomProbability probability on which depends computers infection
     */
    public void doIteration(double randomProbability) {
        double[] virusProbability = new double[computers.length];
        for (int i = 0; i < computers.length; i++) {
            virusProbability[i] = virusProbability(i);
        }

        for (int i = 0; i < computers.length; i++) {
            if (randomProbability < virusProbability[i]) {
                computers[i].setInfected(true);
            }
        }
    }

    /**
     * gets virus probability for computers
     * @param number computers' number in computers list
     * @return virus probability from 0.0 to 1.0
     */
    public double virusProbability(int number) {
        if (computers[number].isInfected())
            return 1;
        double notVirusProbability = 1;
        for (int i = 0; i < computers.length; i++) {
            if (connectedComps[number][i] != 0 && computers[i].isInfected()) {
                notVirusProbability *= (1 - computers[number].getVirusProbability());
            }
        }
        return (1 - notVirusProbability);
    }

    /**
     * makes string with networks'state - information about computers and connection between them
     * @return string with state
     */
    public String state() {
        StringBuilder state = new StringBuilder("");
        double[] virusProbability = new double[computers.length];
        for (int i = 0; i < computers.length; i++) {
            virusProbability[i] = virusProbability(i);
        }
        for (int i = 0; i < computers.length; i++) {
            state.append(Integer.toString(i) + ". " +
                    computers[i].state((virusProbability[i])) +
                    " is connected with: ");
            for (int j = 0; j < computers.length; j++) {
                if (connectedComps[i][j] != 0) {
                    state.append(computers[j].state((virusProbability[j])) + " ");
                }
            }
            state = state.append("\n");
        }
        return state.toString();
    }
}
