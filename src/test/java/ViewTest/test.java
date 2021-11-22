package ViewTest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        AnchorPane TestPane1 = new AnchorPane();
        AnchorPane TestPane2 = new AnchorPane();
        Button btn = new Button();
        btn.setText("'Say 'Hello World'");
        btn.setOnAction(event -> {
            root.getChildren().remove(0);
            root.getChildren().add(TestPane2);
        });

        TestPane1.getChildren().add(btn);
        TestPane2.getChildren().add(new Label("l"));


        root.getChildren().add(TestPane1);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}