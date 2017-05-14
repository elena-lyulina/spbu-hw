package spbu.sem2.hw5.task2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller class for Calculator. */
public class Controller implements Initializable {
    /** Label for number. */
    @FXML
    public Label number;
    /** Label for expression.*/
    @FXML
    private Label expression;

    /** String for left operand. */
    private String operand1;
    /** String for right operand. */
    private String operand2;
    /** String for operation. */
    private String operation;

    /** monitors math process. */
    private boolean mathDone;
    /** monitors operation/ */
    private boolean operationDone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearOperands();
        mathDone = false;
        operationDone = false;
    }

    /** clears all operands. */
    private void clearOperands() {
        operation = "";
        operand1 = "";
        operand2 = "";
    }

    /**
     * function in case of pressing button with number.
     * changes label "number"
     */
    public void pressNumber(ActionEvent actionEvent) {
        if (!mathDone) {
            Button button = (Button) actionEvent.getSource();
            String str = button.getText();
            number.setText(number.getText() + button.getText());
        }
    }

    /**
     *function in case of pressing button with operation.
     * sets operand1 and operation, changes labels
     */
    public void pressOperation(ActionEvent actionEvent) {
        if (number.getText() != "" && !operationDone && !mathDone) {
            Button button = (Button) actionEvent.getSource();
            operand1 = number.getText();
            operation = button.getText();
            operationDone = true;
            expression.setText(operand1 + " " + operation + " ");
            number.setText("");
        }
    }

    /**
     * function in case of pressing result button.
     * sets operand2, calls class Calculator for calculating, prints result in label "number"
     */
    public void pressResult(ActionEvent actionEvent) {
        if (operand1 != "" && operation != "" & !number.getText().equals("")) {
            operand2 = number.getText();
            expression.setText(expression.getText() + operand2 + " =");
            String result = Calculator.calculate(operand1, operand2, operation);
            number.setText(result);
            clearOperands();
            mathDone = true;
        }
    }

    /** function in case of pressing CE button.
     * clears label "number"
     */
    public void pressCE(ActionEvent actionEvent) {
        if (!mathDone)
            number.setText("");
    }

    /** function in case of pressing C button.
     * clears all labels
     */
    public void pressC(ActionEvent actionEvent) {
        mathDone = false;
        operationDone = false;
        expression.setText("");
        number.setText("");
        clearOperands();
    }

    /**
     * function in case of pressing backspace.
     * delete the last digit in label "number"
     */
    public void pressBackspace(ActionEvent actionEvent) {
        if (!number.getText().equals("") && mathDone == false) {
            String value = number.getText();
            number.setText(value.substring(0, value.length() - 1));
        }
    }
}
