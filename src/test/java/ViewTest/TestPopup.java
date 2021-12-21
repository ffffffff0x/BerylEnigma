package ViewTest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 14:46
 **/

public class TestPopup extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        testPopupSettingView t = new testPopupSettingView(anchorPane,20.0,20.0,null,null);
        JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
        jfxMasonryPane.setCellWidth(100);
        jfxMasonryPane.getChildren().setAll(new Pane(new JFXButton("test")));

        final Scene scene = new Scene(jfxMasonryPane, 300, 400);

        stage.setTitle("JFX Popup Demo");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
