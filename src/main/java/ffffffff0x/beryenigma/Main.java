package ffffffff0x.beryenigma;

import ffffffff0x.beryenigma.Init.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BerylEnigma");
        primaryStage.setMinHeight(664.0);
        primaryStage.setMinWidth(920.0);
        initRootLayout();
    }

    public void initRootLayout() {
        try {
//             Locale local = Locale.getDefault();
//             System.out.println(local.getCountry());
//             System.out.println(local.getLanguage());
//             Locale.setDefault(new Locale("en","US"));//英文版本测试

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ffffffff0x/beryenigma/App/View/Root/RootView.fxml"));
            loader.setResources(Init.languageResourceBundle);
            rootLayout = loader.load();

            primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/img/ffffffff0x_ico.png"))));
            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            Font.loadFont(Objects.requireNonNull(Main.class.getResource("/fonts/JOKERMAN.TTF")).toExternalForm(), 10);
            Font.loadFont(Objects.requireNonNull(Main.class.getResource("/fonts/HyliaSerif.otf")).toExternalForm(), 10);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_dark.css")).toExternalForm());
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