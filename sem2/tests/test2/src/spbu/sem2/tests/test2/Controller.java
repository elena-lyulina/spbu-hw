package spbu.sem2.tests.test2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import java.time.Duration;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class Controller {
    private static int counter = 0;

    static int alrdOpenX = 0;
    static int alrdOpenY = 0;


    public static void pressButton(MouseEvent e, int x, int y) throws InterruptedException {
        if (Game.open[x][y] != 1) {
            counter++;
            Game.buttons[x][y].setText(Integer.toString(Game.value[x][y]));
            if (counter == 1) {
                alrdOpenX = x;
                alrdOpenY = y;
                Game.open[x][y] = 1;
            }
            if (counter == 2) {
                counter = 0;
                Game.buttons[x][y].setText(Integer.toString(Game.value[x][y]));
                Game.buttons[alrdOpenX][alrdOpenY].setText(Integer.toString(Game.value[x][y]));
                if (Game.value[x][y] == Game.value[alrdOpenX][alrdOpenY]){
                    Game.open[x][y] = 1;
                    Game.open[alrdOpenX][alrdOpenY] = 1;
                } else {
                    TimeUnit.SECONDS.sleep(1);
                    Game.buttons[x][y].setText("");
                    Game.buttons[alrdOpenX][alrdOpenY].setText("");
                    Game.open[x][y] = 0;
                    Game.open[alrdOpenX][alrdOpenY] = 0;
                }
            }
        }
    }
}
