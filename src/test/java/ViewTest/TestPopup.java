package ViewTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 14:46
 **/

public class TestPopup extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        new testPopupSettingView(anchorPane,20.0,20.0,null,null);
        final Scene scene = new Scene(anchorPane, 300, 400);

        stage.setTitle("JFX Popup Demo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
