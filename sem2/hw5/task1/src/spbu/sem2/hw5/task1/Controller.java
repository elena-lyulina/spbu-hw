package spbu.sem2.hw5.task1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

/** Controller for JavaFX. */
public class Controller implements Initializable {

    /** Spinner for first operand. */
    @FXML
    private Spinner operand1;

    /** Spinner for second operand. */
    @FXML
    private Spinner operand2;
    @FXML

    /** Label for result. */
    private Label result;

    /** ChoiceBox for choosing operation. */
    @FXML
    private ChoiceBox chooseOperation;

    private String operation = "-";

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        chooseOperation.setItems(FXCollections.observableArrayList("-", "+", "/", "*"));
        chooseOperation.setValue("-");

        operand1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Calculator.min(), Calculator.max()));
        operand2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Calculator.min(), Calculator.max()));

        chooseOperation.getSelectionModel()
                .selectedIndexProperty()
                .addListener((observable, oldValue, newValue) -> {
            operation = (String) chooseOperation.getItems().get(newValue.intValue());
            doMath();
                });
    }

    /** This function calls class Calculator for calculating. */
    public void doMath() {
        int firstOperand = (int) operand1.getValue();
        int secondOperand = (int) operand2.getValue();
        String answer = Calculator.calculate(firstOperand, secondOperand, operation);
        result.setText(answer);
    }
}