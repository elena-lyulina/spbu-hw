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
    public static Label number = new Label();
    /** Label for expression.*/
    @FXML
    public static Label expression = new Label();

    /** String for left operand. */
    private static String operand1 = "";
    /** String for right operand. */
    private static String operand2 = "";
    /** String for operation. */
    private static String operation = "";

    /** monitors math process. */
    public static boolean mathDone;
    /** monitors operation/ */
    public static boolean operationDone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearOperands();
        mathDone = false;
        operationDone = false;
    }

    /** clears all operands. */
    public static void clearOperands() {
        operation = "";
        operand1 = "";
        operand2 = "";
    }

    /**
     * function in case of pressing button with number.
     * changes label "number"
     */
    public static void pressNumber(ActionEvent actionEvent) {
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
    public static void pressOperation(ActionEvent actionEvent) {
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
    public static void pressResult(ActionEvent actionEvent) {
        if (!operand1.equals("") && !operation.equals("") & !operand1.equals("") & !number.getText().equals("")) {
            operand2 = number.getText();
            expression.setText(expression.getText() + operand2 + " =");
            System.out.println(expression.getText());
            String result = Calculator.calculate(operand1, operand2, operation);
            number.setText(result);
            clearOperands();
            operationDone = false;
            operand1 = result;
        }
    }

    /** function in case of pressing CE button.
     * clears label "number"
     */
    public static void pressCE(ActionEvent actionEvent) {
        if (!mathDone)
            number.setText("");
    }

    /** function in case of pressing C button.
     * clears all labels
     */
    public static void pressC(ActionEvent actionEvent) {
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
    public static void pressBackspace(ActionEvent actionEvent) {
        if (!number.getText().equals("") && mathDone == false) {
            String value = number.getText();
            number.setText(value.substring(0, value.length() - 1));
        }
    }
}
