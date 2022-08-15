package MockViewTest;

import ffffffff0x.beryenigma.Init.Init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class MockViewTest extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BEMock");
        initRootLayout("./TempView_00.fxml");
    }

    public void initRootLayout(String FXMLPath) {
        try {
            // 英文版本测试
//             englishMode();

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(MockViewTest.class.getResource(FXMLPath));
            loader.setLocation(MockViewTest.class.getResource(FXMLPath));
            loader.setResources(Init.getLanguageResourceBundle());
            rootLayout = loader.load();

            primaryStage.getIcons().add(new Image(MockViewTest.class.getResourceAsStream("/img/ffffffff0x_ico.png")));
            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(MockViewTest.class.getResource("/css/MainCSS_light.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void englishMode(){
        Locale local = Locale.getDefault();
        System.out.println(local.getCountry());
        System.out.println(local.getLanguage());
        Locale.setDefault(new Locale("en","US"));//英文版本测试
    }

    public static void main(String[] args) {
        launch(args);
    }
}
