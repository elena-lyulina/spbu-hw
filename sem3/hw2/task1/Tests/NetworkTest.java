import org.junit.Test;
import org.junit.Assert;
import spbu.sem3.hw2.task1.Computer;
import spbu.sem3.hw2.task1.Network;
import java.util.Arrays;

public class NetworkTest {

    /* multiplication of matrix' elements */
    public int multi(int[] matrix) {
        int multi = 1;
        for (int i = 1; i < matrix.length; i++){
            multi *= matrix[i];
        }
        return multi;
    }

    /* fills array with number of iteration, on which each computer got infected */
    public void checkInfection(int[]isInfected, Computer[] comps, int iteration){
        for (int i = 1; i < comps.length; i++){
    /* if this computer got infected on this iteration */
            if (isInfected[i] == 0 && comps[i].isInfected() )
                isInfected[i] = iteration;
        }
    }


    /*
    if random number (=probability of 'not'-infection) is always 0, computers have to get infected one-by-one
                [infected] - W - M - L - W - M - L
     */
    @Test
    public void chainOfInfection() throws Computer.WrongOSType {
        int n = 7;
        Computer[] comps = new Computer[]{new Computer(true, 'W'),
                new Computer(false, 'W'),
                new Computer(false, 'M'),
                new Computer(false, 'L'),
                new Computer(false, 'W'),
                new Computer(false, 'M'),
                new Computer(false, 'L')};

        int[][] connectedComps = {{0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0}};
        Network network = new Network(comps, connectedComps);

        /* isInfected[i] = n  means that i-th computer got infected on n-th iteration, isInfected[0] = 0 */
        int isInfected[] = new int[n];

        int i = 0;
        /* while there is at least one uninfected computer so at least one of isInfected' elements is 0
         (ofc excluding isInfected[0]) */
        while (multi(isInfected) == 0) {
            i++;
            network.doIteration(0);
            checkInfection(isInfected, comps, i);
        }

        int [] expectedAnswer = {0, 1, 2, 3, 4, 5, 6};
        Assert.assertTrue(Arrays.equals(expectedAnswer, isInfected));
    }

    /* compares with inaccuracy*/
    public boolean areEqual(double a, double b, double error) {
        return Math.abs(a - b) <= error;
    }

    /* infects only the first computer */
    public void initialize(Computer[] comps) {
        comps[0].setInfected(true);
        for (int i = 1; i < comps.length; i++) {
            comps[i].setInfected(false);
        }
    }

/*
one of each OS type is connected with infected computer,
counting number of iteration required to infect each OS type
                    M
                    |
            W - [infected] - L
*/
    @Test
    public void singleProbabilityCheck() throws Computer.WrongOSType {
        int n = 10000;
        int sumInfNumberW = 0;
        int sumInfNumberM = 0;
        int sumInfNumberL = 0;
        Computer[] comps = new Computer[]{new Computer(true, 'W'),
                                        new Computer(false, 'W'),
                                        new Computer(false, 'M'),
                                        new Computer(false, 'L')};
        int[][] connectedComps = {{0, 1, 1, 1},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0}};

        for (int j = 0; j < n; j++) {
            initialize(comps);
            Network network = new Network(comps, connectedComps);
            int infNumberW = 0;
            int infNumberM = 0;
            int infNumberL = 0;
            int i = 0;
            while (infNumberW == 0 || infNumberM == 0 || infNumberL == 0) {
                i++;
                double p = Math.random();
//                System.out.println(network.state());
                network.doIteration(p);
                if (comps[1].isInfected() & infNumberW == 0)
                    infNumberW = i;
                if (comps[2].isInfected() & infNumberM == 0)
                    infNumberM = i;
                if (comps[3].isInfected() & infNumberL == 0)
                    infNumberL = i;
            }

            sumInfNumberW += infNumberW;
            sumInfNumberM += infNumberM;
            sumInfNumberL += infNumberL;
        }

        Assert.assertTrue(areEqual((double)n / sumInfNumberW, comps[1].getVirusProbability(), 0.01));
        Assert.assertTrue(areEqual((double)n / sumInfNumberM, comps[2].getVirusProbability(), 0.01));
        Assert.assertTrue(areEqual((double)n / sumInfNumberL, comps[3].getVirusProbability(), 0.01));
    }


    public boolean isOneInfected(Computer[] comps){
        boolean isOneInfected = false;
        int i = 0;
        while (isOneInfected == false && i < comps.length){
            isOneInfected = comps[i].isInfected();
            i++;
        }
        return isOneInfected;
    }

    /*
        if there is no infected computers, all computers will be uninfected
                                L
                              /:)\
                            W --- M

     */
    @Test
    public void noInfectionTest() throws Computer.WrongOSType {
        Computer[] comps = new Computer[]{new Computer(false, 'W'),
                                        new Computer(false, 'L'),
                                        new Computer(false, 'M')};
        int[][] connectedComps = {{0, 1, 1},
                                {1, 0, 1},
                                {1, 1, 0}};
        Network network = new Network(comps, connectedComps);
        Assert.assertTrue(network.virusProbability(0) == 0
                                && network.virusProbability(0) == 0
                                && network.virusProbability(0) == 0);

        int i = 0;
        while (i < 10000) {
            network.doIteration(Math.random());
            Assert.assertTrue(!isOneInfected(comps));
            i++;
        }

        Assert.assertTrue(network.virusProbability(0) == 0
                                && network.virusProbability(0) == 0
                                && network.virusProbability(0) == 0);
    }

}