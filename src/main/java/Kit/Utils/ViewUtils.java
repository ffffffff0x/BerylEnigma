package Kit.Utils;

import Init.Init;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import javax.xml.validation.Validator;
import java.io.File;
import java.time.temporal.ValueRange;

public class ViewUtils {
    /**
     * 返回文件获取窗口
     * @return
     */
    public static File getFile(){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Init.languageResourceBundle.getString("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * 带有文件名后缀过滤器的文件获取窗口
     * @param extFilter
     * @return
     */
    public static File getFile(FileChooser.ExtensionFilter[] extFilter){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        for (FileChooser.ExtensionFilter filter:extFilter) {
            fileChooser.getExtensionFilters().add(filter);
        }
        fileChooser.setTitle(Init.languageResourceBundle.getString("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    }

    /**
     * 保存文件时的文件选择器
     * @return
     */
    public static File saveFileFilter(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        Stage stage = new Stage();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName("Result");
        return fileChooser.showSaveDialog(stage);
    }

    /**
     * alert弹窗，用于报错，提示等功能
     * @param stage
     * @param heading
     * @param body
     */
    public static void alertPane(Stage stage, String heading, String body){
        JFXAlert alert = new JFXAlert(stage);
        alert.initModality(Modality.NONE);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        Label HeadingLable = new Label(heading);
        HeadingLable.setStyle("-fx-font-size: 20.0px;");
        layout.setHeading(HeadingLable);
        layout.setBody(new Label(body));
        JFXButton closeButton = new JFXButton(Init.languageResourceBundle.getString("Accept"));
        closeButton.setPrefSize(120,60);
        closeButton.setStyle("    -fx-background-color: WHITE;\n" +
                "    -fx-font-size: 14.0px;");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }

    /**
     * 清空文本域控件的文字并显示过滤器报错
     * @param jta
     */
    public static void textAreaValidate(JFXTextArea jta){
        jta.setText("");
        jta.validate();
    }

    /**
     * 重置文本域控件的过滤器报错
     * @param jta
     */
    public static void textAreaValidateResrt(JFXTextArea jta){
        jta.resetValidation();
    }
}
