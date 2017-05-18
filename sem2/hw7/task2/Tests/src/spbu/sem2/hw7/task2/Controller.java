package spbu.sem2.hw7.task2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Class for controller */
public class Controller {

    @FXML
    private Label label;

    private int counter;

    private boolean end;

    /** initializes variables. */
    public void initialize() {
        Game.initialize();
        end = false;
        counter = 0;
    }

    /** in case of pressing a button. */
    public void press(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("") && end == false) {
            double coordX = button.getLayoutX();
            double coordY = button.getLayoutY();
            if (counter % 2 == 0) {
                label.setText("Player O goes");
                button.setText("X");
                setButtons(coordX, coordY, 1);
            }
            else {
                label.setText("Player X goes");
                button.setText("O");
                setButtons(coordX, coordY, 2);
            }
            counter++;
            checkResults();
        }
    }

    /** checks result of the game. */
    public void checkResults() {
        int result = Game.checkWin();
        if (result == 1) {
            label.setText("Player X win!");
            end = true;
        } else if (result == 2) {
            label.setText("Player O win!");
            end = true;
        }
        if (Game.noResults()) {
            label.setText("The end");
            end = true;
        }
    }

    /** adds the value of button(identify by coordinates) has been pressing to the buttons array.
     * @param coordX x-coordinate of button
     * @param coordY y-coordinate of button
     * @param value 1 or 2 depends on player
     */
    public void setButtons(double coordX, double coordY, int value) {
        int x = 0;
        int y = 0;
        switch((int) coordX) {
            case 20:
                x = 0;
                break;
            case 112:
                x = 1;
                break;
            case 203:
                x = 2;
                break;
        }

        switch ((int) coordY) {
            case 16:
                y = 0;
                break;
            case 102:
                y = 1;
                break;
            case 184:
                y = 2;
                break;
        }

        Game.buttons[x][y] = value;
    }
}
