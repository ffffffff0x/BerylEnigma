package ffffffff0x.beryenigma.Kit.Utils;

import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.Init.ConfigListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ViewUtils {
    /**
     * 返回文件获取窗口
     * @return 返回文件获取窗口
     */
    public static File getFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Init.getLanguage("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(new Stage());
    }

    /**
     * 返回文件获取窗口（多选）
     * @return 返回文件获取窗口
     */
    public static List<File> getFiles() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Init.getLanguage("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenMultipleDialog(new Stage());
    }

    /**
     * 带有单个文件名后缀过滤器的文件获取窗口
     * @param extFilter 后缀过滤器
     * @return 带有单个文件名后缀过滤器的文件获取窗口
     */
    public static File getFile(FileChooser.ExtensionFilter extFilter){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle(Init.getLanguage("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    }


    /**
     * 带有文件名后缀过滤器的文件获取窗口
     * @param extFilter 后缀过滤器
     * @return 带有文件名后缀过滤器的文件获取窗口
     */
    public static File getFile(FileChooser.ExtensionFilter[] extFilter){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        for (FileChooser.ExtensionFilter filter:extFilter) {
            fileChooser.getExtensionFilters().add(filter);
        }
        fileChooser.setTitle(Init.getLanguage("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * 保存文件时的文件选择器
     * @return 保存文件时的文件选择器
     */
    public static File fileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName("Result");
        return fileChooser.showSaveDialog(new Stage());
    }

    /**
     * 保存文件时的文件选择器(自定义格式筛选器)
     *
     * @param fitter 格式筛选器
     * @return 文件
     */
    public static File fileChooser(FileChooser.ExtensionFilter... fitter) {
        FileChooser fileChooser = new FileChooser();
        for (FileChooser.ExtensionFilter extensionFilter : fitter) {
            fileChooser.getExtensionFilters().add(extensionFilter);
        }
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName("Result");
        return fileChooser.showSaveDialog(new Stage());
    }

    /**
     * 保存文件时的文件目录选择器
     * @return 保存文件时的文件目录选择器
     */
    public static File directoryChooser(){
        DirectoryChooser directorychooser = new DirectoryChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        directorychooser.setInitialDirectory(fsv.getHomeDirectory());
        return directorychooser.showDialog(new Stage());
    }

    /**
     * 保存文件时的文件选择器
     * @return 保存文件时的文件选择器
     */
    public static File fileChooser(String filename){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName(filename);
        return fileChooser.showSaveDialog(new Stage());
    }

    /**
     * alert弹窗，用于报错，提示等功能
     * @param stage 目标stage
     * @param heading 标题
     * @param body 正文
     */
    public static void alertPane(Stage stage, String heading, String body){
        JFXAlert alert = new JFXAlert(stage);
        alert.initModality(Modality.NONE);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        Label HeadingLable = new Label(heading);
        layout.setHeading(HeadingLable);
        layout.setBody(new Label(body));
        JFXButton closeButton = new JFXButton(Init.getLanguage("Accept"));
        closeButton.setPrefSize(120,60);
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }

    /**
     * 清空文本域控件的文字并显示过滤器报错
     * @param jta 目标文本域node
     */
    public static void textAreaValidate(JFXTextArea jta){
        jta.setText("");
        jta.validate();
    }

    /**
     * 重置文本域控件的过滤器报错
     * @param jta 目标文本域node
     */
    public static void textAreaValidateReset(JFXTextArea jta){
        jta.resetValidation();
    }

    /**
     * 用于设置在Anchor中node对象的Anchor
     * @param node 要设置Anchor的目标node
     * @param TopAnchor 上
     * @param LeftAnchor 左
     * @param RightAnchor 右
     * @param BottomAnchor 下
     */
    public static void setAnchor(Node node, Double TopAnchor, Double LeftAnchor, Double RightAnchor, Double BottomAnchor){
        if (TopAnchor != null) {
            AnchorPane.setTopAnchor(node,TopAnchor);
        }
        if (LeftAnchor != null) {
            AnchorPane.setRightAnchor(node,RightAnchor);
        }
        if (RightAnchor != null) {
            AnchorPane.setLeftAnchor(node,LeftAnchor);
        }
        if (BottomAnchor != null) {
            AnchorPane.setBottomAnchor(node,BottomAnchor);
        }
    }

    /**
     * 将swing的BufferedImage对象转换为JAVAFX的Image对象
     * @param image BufferedImage对象
     * @return JAVAFX的Image对象
     */
    public static javafx.scene.image.Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    /**
     * 用于获取文本分隔符
     * @param jtaSplit 传入的JFXTextField对象
     * @return String
     */
    public static String getSplit(JFXTextField jtaSplit) {
        return Util.splitStringReplace(jtaSplit.getText());
    }

    /**
     * 获取一个圆形假进度条
     * @return JFXSpinner
     */
    public static JFXSpinner getRunningSpinner() {
        JFXSpinner jfxSpinner = new JFXSpinner();
        jfxSpinner.setPrefSize(36.0,36.0);
        jfxSpinner.setVisible(false);
        setAnchor(jfxSpinner,null,null,50.0,47.0);
        return jfxSpinner;
    }

    /**
     * 根据程序使用样式，获取图片
     * @return Image
     */
    public static Image getImage(String imgName) {
        imgName = imgName.replace("{$}", Init.getConfig(ConfigListInit.AppStyle));
        return new Image(Objects.requireNonNull(ViewUtils.class.getResourceAsStream(imgName)));
    }

    /**
     * 设置文本域控件文字颜色为红色
     *
     * @param isRed
     * @param textAreas
     */
    public static void setTextAreaTextRed(Boolean isRed, JFXTextArea... textAreas) {
        if (Init.getConfig(ConfigListInit.AppStyle).equals("light")) {
            if (isRed) {
                for (JFXTextArea jfxTextArea : textAreas) {
                    jfxTextArea.setStyle("-fx-text-fill: red");
                }
            } else {
                for (JFXTextArea jfxTextArea : textAreas) {
                    jfxTextArea.setStyle("-fx-text-fill: black");
                }
            }
        } else {
            if (isRed) {
                for (JFXTextArea jfxTextArea : textAreas) {
                    jfxTextArea.setStyle("-fx-text-fill: red");
                }
            } else {
                for (JFXTextArea jfxTextArea : textAreas) {
                    jfxTextArea.setStyle("-fx-text-fill: lightgray");
                }
            }
        }
    }

    /**
     * 获取一个输入框右边的小按钮Vbox
     *
     * @param anchorPane controlPane
     * @return VBox
     */
    public static VBox getLittleButtonVBoxSrc(AnchorPane anchorPane) {
        VBox vBox = getLittleButtonVBox();
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 30.0);
        vBox.setPrefSize(30.0, 170.0);
        anchorPane.getChildren().add(vBox);
        return vBox;
    }

    /**
     * 获取一个输出框右边的小按钮Vbox
     *
     * @param anchorPane controlPane
     * @return VBox
     */
    public static VBox getLittleButtonVBoxDst(AnchorPane anchorPane) {
        VBox vBox = getLittleButtonVBox();
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 315.0);
        vBox.setPrefSize(30.0, 170.0);
        anchorPane.getChildren().add(vBox);
        return vBox;
    }

    /**
     * 获取一个Vbox，node间隔为5
     *
     * @return VBox
     */
    public static VBox getLittleButtonVBox() {
        VBox vBox = new VBox();
        vBox.setSpacing(5.0);
        return vBox;
    }

    /**
     * 将小按钮放入Vbox
     *
     * @param vBox VBox
     * @param jfxButton littlebutton
     * @param iamgePath iamgePath
     */
    public static void setLittleButtonToVBox(VBox vBox, JFXButton jfxButton, String iamgePath) {
        ImageView imageView = new ImageView(getImage(iamgePath));
        jfxButton.setGraphic(imageView);
        vBox.getChildren().add(jfxButton);
    }

    /**
     * 使用浏览器打开链接
     *
     * @param node 调用此方法的控件
     * @param url 目标链接
     */
    public static void openURLWithBrowser(Node node, String url) {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                // 创建一个URI实例
                java.net.URI uri = java.net.URI.create(url);
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    // 获取系统默认浏览器打开链接
                    dp.browse(uri);
                }
            } catch (Exception e) {
                alertPane((Stage) node.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("CanTOpenWithBrowser"));
                e.printStackTrace();
            }
        } else {
            alertPane((Stage) node.getScene().getWindow(), Init.getLanguage("Warning"), "System not Suppot");
        }
    }

    /**
     * 从剪贴板获取BufferedImage
     *
     * @return BufferedImage
     */
    public static BufferedImage getBufferedImageFromClipboard() throws IOException, UnsupportedFlavorException {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if ( t != null && t.isDataFlavorSupported( DataFlavor.imageFlavor ) ) {
            return (java.awt.image.BufferedImage) t.getTransferData(DataFlavor.imageFlavor);
        }
        return null;
    }

    public static Scene setCSSStyle(Scene scene) {
        if (Init.getConfig(ConfigListInit.AppStyle).equals("dark")) {
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_dark.css")).toExternalForm());
        } else {
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_light.css")).toExternalForm());
        }
        return scene;
    }
}
