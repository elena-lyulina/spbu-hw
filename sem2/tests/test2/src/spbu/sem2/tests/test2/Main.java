package spbu.sem2.tests.test2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.time.Duration;

public class Main extends Application {
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Game.initialize();

        for (int i = 0 ; i < Game.n; i++ ) {
            RowConstraints rc = new RowConstraints();
            gridPane.getRowConstraints().add(rc);
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(cc);
        }

        for (int i = 0 ; i < Game.n ; i++) {
            for (int j = 0; j < Game.n; j++) {
                final int x = i;
                final int y = j;
                Game.buttons[i][j] = new Button();
                Game.buttons[i][j].setMinSize(50, 50);
                Game.buttons[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override public void handle(MouseEvent e) {
                                try {
                                    Controller.pressButton(e, x, y);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                gridPane.add(Game.buttons[i][j], i, j);
            }
        }

        Scene scene = new Scene(gridPane, Game.n * 50, Game.n * 50);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
