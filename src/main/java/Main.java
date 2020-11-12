import Init.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BerylEnigma");
        initRootLayout();
    }

    public void initRootLayout() {
        try {
            // Locale local = Locale.getDefault();
            // System.out.println(local.getCountry());
            // System.out.println(local.getLanguage());
            // Locale.setDefault(new Locale("en","US"));//英文版本测试

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/View/Root/RootView.fxml"));
            loader.setResources(Init.languageResourceBundle);
            rootLayout = loader.load();

            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/img/ffffffff0x_ico.png")));
            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Main.class.getResource("/css/TreeList.css").toExternalForm());
            primaryStage.setScene(scene);
            // primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}