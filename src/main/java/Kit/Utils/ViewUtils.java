package Kit.Utils;

import Init.Init;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class ViewUtils {

    public static String comboxSplitConvert(String splitString){
        switch (splitString){
            case "空格分隔" : return " ";
            case "Space_separation" : return " ";
            case "换行分隔" : return "\n";
            case "Line_break" : return "\n";
            case "分号分隔" : return ";";
            case "Semicolon_separated" : return ";";
            case "冒号分隔" : return ":";
            case "Colon_separated" : return ":";
            case "逗号分隔" : return ",";
            case "Comma_separated" : return ",";
            case "0x分隔" : return "0x";
            case "0x_seperated" : return "0x";
            case "%分隔" : return "%";
            case "%_Separation" : return "%";
            case "$分隔" : return "$";
            case "$_separation" : return "$";
            case "双空格分隔" : return "  ";
            case "Double_space_separation" : return "  ";
            case "双换行分隔" : return "\n\n";
            case "Double_line_break" : return "\n\n";
            case "双分号分隔" : return ";;";
            case "Double_semicolon_separated" : return ";;";
            case "双冒号分隔" : return "::";
            case "Double_colon_separated" : return "::";
            case "双逗号分隔" : return ",,";
            case "Double_comma_separated" : return ",,";
            default : return splitString;
        }


    }//combobox根据输入返回分隔符

    public static File getFile(){
        Stage primaryStage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Init.languageResourceBundle.getString("File"));
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        return fileChooser.showOpenDialog(primaryStage);
    } //获取文件类

    public static File saveTextFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = new Stage();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setInitialFileName("Result");
        return fileChooser.showSaveDialog(stage);
    }//保存文件选择器
}
