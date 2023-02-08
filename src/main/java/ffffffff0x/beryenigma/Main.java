package ffffffff0x.beryenigma;

import ffffffff0x.beryenigma.Init.ConfigListInit;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class Main extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BerylEnigma");
        primaryStage.setMinHeight(Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinheight)));
        primaryStage.setMinWidth(Double.parseDouble(Init.getConfig(ConfigListInit.AppSizeMinwidth)));
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
            loader.setResources(Init.getLanguageResourceBundle());
            rootLayout = loader.load();

            // 设置图标
            primaryStage.getIcons().add(ViewUtils.getImage(ImageListInit.ICON));
//            primaryStage.setOnCloseRequest(event -> System.exit(0));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            Font.loadFont(Objects.requireNonNull(Main.class.getResource("/fonts/JOKERMAN.TTF")).toExternalForm(), 10);
            Font.loadFont(Objects.requireNonNull(Main.class.getResource("/fonts/HyliaSerif.otf")).toExternalForm(), 10);

            // 设置CSS样式
            ViewUtils.setCSSStyle(scene);

            // 窗口关闭事件
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    try {
                        // 关闭所有子窗口
                        Platform.exit();
                        // 保存当前窗口配置
                        ConfigUtils.saveConfigFile(Init.CONFIGFILEPATH_NOW);
                        // 关闭程序
                        System.exit(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
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