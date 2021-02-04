package Kit.Mock;

import Init.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MockView extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BEMock");
        // initRootLayout("/Kit/TempView/TempView_00.fxml");
         initRootLayout("/View/Encryption/Modern/AES/AESView.fxml");
        // initRootLayout("/View/Encryption/Modern/Hash/HashView.fxml");
//        initRootLayout("/View/Encryption/Coding/MorseCoder/MorseCoderView.fxml");
        // initRootLayout("/View/Root/RootView.fxml");
    }

    public void initRootLayout(String FXMLPath) {
        try {
            // Locale local = Locale.getDefault();
            // System.out.println(local.getCountry());
            // System.out.println(local.getLanguage());
            // Locale.setDefault(new Locale("en","US"));//英文版本测试

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MockView.class.getResource(FXMLPath));
            loader.setResources(Init.languageResourceBundle);
            rootLayout = loader.load();

            primaryStage.getIcons().add(new Image(MockView.class.getResourceAsStream("../../img/ffffffff0x_ico.png")));
            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(MockView.class.getResource("/css/MainCSS.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
