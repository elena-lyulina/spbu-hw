package spbu.sem2.hw5.task2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static spbu.sem2.hw5.task2.Controller.*;

public class Main extends Application {
    GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();

        GridPane gridLabels = createGridPane(1, 2);
        expression.setPadding(new Insets(15, 15, 0, 50));
        gridLabels.add(expression, 0, 0);

        number.setPadding(new Insets(15, 15, 0, 50));
        gridLabels.add(number, 0, 1);
        border.setTop(gridLabels);

        grid = createGridPane(5, 5);
        grid.setPadding(new Insets(25, 25, 25, 25));
        border.setCenter(grid);

        Button button1 = createNumberButton("1", 0, 1);
        Button button2 = createNumberButton("2", 1, 1);
        Button button3 = createNumberButton("3", 2, 1);
        Button button4 = createNumberButton("4", 0, 2);
        Button button5 = createNumberButton("5", 1, 2);
        Button button6 = createNumberButton("6", 2, 2);
        Button button7 = createNumberButton("7", 0, 3);
        Button button8 = createNumberButton("8", 1, 3);
        Button button9 = createNumberButton("9", 2, 3);
        Button button0 = createNumberButton("0", 1, 4);

        Button buttonAdd = createOperationButton("+", 4, 1);
        Button buttonSub = createOperationButton("-", 4, 2);
        Button buttonMulti = createOperationButton("*", 4, 3);
        Button buttonDiv = createOperationButton("/", 4, 4);

        Button buttonResult = createButton("=", 4, 0);
        buttonResult.setOnAction(e -> pressResult(e));

        Button buttonBS = createButton("<-", 2, 0);
        buttonBS.setOnAction(e -> pressBackspace(e));

        Button buttonC = createButton("C", 1, 0);
        buttonC.setOnAction(e -> pressC(e));

        Button buttonCE = createButton("CE", 0, 0);
        buttonCE.setOnAction(e -> pressCE(e));

        Scene scene = new Scene(border, 350, 400);
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(300);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Calculator");
    }

    /**
     * creates new GridPane.
     * @param colAmount amount of columns
     * @param rowAmount amount if rows
     * @return GridPane has been created
     */
    public GridPane createGridPane(int colAmount, int rowAmount) {
        GridPane grid = new GridPane();
        for (int row = 0 ; row < rowAmount; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < colAmount; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        return grid;
    }

    /**
     * creates new number button, adds to GridPane "grid" and sets on action function "pressNumber".
     * @param text text on button
     * @param colInd index of grid column where you want to add button
     * @param rowInd index of grid row where you want to add button
     * @return button
     */
    public Button createNumberButton(String text, int colInd, int rowInd) {
        Button button = createButton(text, colInd, rowInd);
        button.setOnAction(e -> pressNumber(e));
        return button;
    }

    /**
     * creates new operation button, adds to GridPane "grid" and sets on action function "pressOperation".
     * @param text text on button
     * @param colInd index of grid column where you want to add button
     * @param rowInd index of grid row where you want to add button
     * @return button
     */
    public Button createOperationButton(String text, int colInd, int rowInd) {
        Button button = createButton(text, colInd, rowInd);
        button.setOnAction(e -> pressOperation(e));
        return button;
    }

    /**
     * creates new button and adds to GridPane "grid".
     * @param text text on button
     * @param colInd index of grid column where you want to add button
     * @param rowInd index of grid row where you want to add button
     * @return button
     */
    public Button createButton(String text, int colInd, int rowInd) {
        Button button = new Button();
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setText(text);
        grid.add(button, colInd, rowInd);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
