package spbu.sem2.hw7.task2;

import org.junit.Test;
import org.junit.Assert;

public class GameTest {

    @Test
    public void horizontalLine() throws Exception {
        int[][] array1 = {{1, 1, 1},
                          {2, 2, 8},
                          {2, 1, 2}};
        int[][] array2 = {{1, 1, 5},
                          {2, 2, 2},
                          {1, 2, 1}};
        int[][] array3 = {{1, 2, 5},
                          {2, 2, 8},
                          {1, 1, 1}};
        Game.buttons = array1;
        Assert.assertTrue(Game.horizontalLine() == 1);
        Game.buttons = array2;
        Assert.assertTrue(Game.horizontalLine() == 2);
        Game.buttons = array3;
        Assert.assertTrue(Game.horizontalLine() == 1);
    }

    @Test
    public void verticalLine() throws Exception {
        int[][] array1 = {{1, 2, 5},
                          {1, 2, 8},
                          {1, 1, 2}};
        int[][] array2 = {{1, 2, 5},
                          {6, 2, 8},
                          {1, 2, 1}};
        int[][] array3 = {{1, 2, 5},
                          {1, 2, 8},
                          {1, 1, 2}};
        Game.buttons = array1;
        Assert.assertTrue(Game.verticalLine() == 1);
        Game.buttons = array2;
        Assert.assertTrue(Game.verticalLine() == 2);
        Game.buttons = array3;
        Assert.assertTrue(Game.verticalLine() == 1);
    }

    @Test
    public void diagonal() throws Exception {
        int[][] array1 = {{2, 1, 5},
                          {6, 2, 8},
                          {1, 1, 2}};
        int[][] array2 = {{1, 2, 1},
                          {2, 1, 8},
                          {1, 2, 2}};
        Game.buttons = array1;
        Assert.assertTrue(Game.diagonal() == 2);
        Game.buttons = array2;
        Assert.assertTrue(Game.diagonal() == 1);
    }

    @Test
    public void noResults() throws Exception {
        int[][] array1 = {{1, 1, 2},
                          {2, 2, 1},
                          {1, 1, 2}};
        Game.buttons = array1;
        Assert.assertTrue(Game.noResults());
    }
}