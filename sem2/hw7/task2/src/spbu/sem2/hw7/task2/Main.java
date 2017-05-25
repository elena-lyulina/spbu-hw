package spbu.sem2.hw7.task2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();

        Controller.label.setPadding(new Insets(15, 25, 0, 50));
        Controller.label.setText("Player X goes");
        border.setTop(Controller.label);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        border.setCenter(grid);

        for (int row = 0 ; row < Game.n; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < Game.n; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int i = 0 ; i < Game.n ; i++) {
            for (int j = 0; j < Game.n; j++) {
                Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setId(Integer.toString(j * Game.n + i));
                button.setText("   ");
                button.addEventHandler(ActionEvent.ACTION,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                Controller.press(e);
                            }
                        });
                grid.add(button, i, j);
            }
        }

        Game.initialize();

        Scene scene = new Scene(border, 350, 400);
        primaryStage.setMinWidth(Game.n * 50 + 50);
        primaryStage.setMinHeight(Game.n * 50 + 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
