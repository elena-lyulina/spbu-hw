package spbu.sem2.hw7.task2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Class for controller. */
public class Controller {
    @FXML
    static
    Label label = new Label();

    private static int counter;

    private static boolean end;

    /** initializes variables. */
    public void initialize() {
        Game.initialize();
        end = false;
        counter = 0;
    }

    /** in case of pressing a button. */
    public static void press(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("   ") && end == false) {
            if (counter % 2 == 0) {
                label.setText("Player O goes");
                button.setText("X");
                setButtons(button, 1);
            } else {
                label.setText("Player X goes");
                button.setText("O");
                setButtons(button, 2);
            }
            counter++;
            checkResults();
        }
    }

    /** checks result of the game. */
    public static void checkResults() {
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

    /**
     * adds the value of button(identify by Id) has been pressing to the buttons array.
     * @param button button has been pressed
     * @param value  1 or 2 depends on player
     */
    public static void setButtons(Button button, int value) {
        int name = Integer.parseInt(button.getId());
        int x = (name) % Game.n;
        int y = (name) / Game.n;
        Game.buttons[x][y] = value;
    }
}
