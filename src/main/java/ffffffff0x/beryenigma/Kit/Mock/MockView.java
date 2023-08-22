package ffffffff0x.beryenigma.Kit.Mock;

import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MockView extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BEMock");
//        initRootLayout("/ffffffff0x/beryenigma/App/View/Modules/Encryption/Classical/CaesarCipher/CaesarCipherView.fxml");
//        initRootLayout("/ffffffff0x/beryenigma/App/View/Modules/Tools/Practical/Timestamp/TimestampView.fxml");
        initRootLayout("/ffffffff0x/beryenigma/App/View/Modules/Tools/RedTeam/FileHeadChecker/FileHeadCheckerView.fxml");
//        initRootLayout("/ffffffff0x/beryenigma/Kit/Mock/TestView.fxml");
    }

    public void initRootLayout(String FXMLPath) {
        try {
//             英文版本测试
             englishMode();

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(MockView.class.getResource(FXMLPath));
            loader.setLocation(MockView.class.getResource(FXMLPath));
            loader.setResources(Init.getLanguageResourceBundle());
            rootLayout = loader.load();

            primaryStage.getIcons().add(ViewUtils.getImage(ImageListInit.ICON));
            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(Objects.requireNonNull(MockView.class.getResource("/css/MainCSS_dark.css")).toExternalForm());
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
