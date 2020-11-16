package Init;

import Kit.Utils.FlieUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class ViewInit {
    public static void comboBoxCharset(JFXComboBox JCB_temp){
        JCB_temp.getItems().addAll(
                StandardCharsets.UTF_8,
                "GBK",
                "BIG5",
                StandardCharsets.UTF_16,
                StandardCharsets.UTF_16LE,
                StandardCharsets.UTF_16BE,
                StandardCharsets.ISO_8859_1,
                StandardCharsets.US_ASCII
        );
        JCB_temp.setValue(StandardCharsets.UTF_8);
        JCB_temp.setVisibleRowCount(6);
    }//combobox添加字符集选项

    public static void comboBoxSplit(JFXComboBox JCB_temp){
        JCB_temp.getItems().addAll(
                Init.languageResourceBundle.getString("Space_separation"),
                Init.languageResourceBundle.getString("Line_break"),
                Init.languageResourceBundle.getString("Semicolon_separated"),
                Init.languageResourceBundle.getString("Colon_separated"),
                Init.languageResourceBundle.getString("Comma_separated"),
                Init.languageResourceBundle.getString("0x_seperated"),
                Init.languageResourceBundle.getString("%_Separation"),
                Init.languageResourceBundle.getString("$_separation"),
                Init.languageResourceBundle.getString("Double_space_separation"),
                Init.languageResourceBundle.getString("Double_line_break"),
                Init.languageResourceBundle.getString("Double_semicolon_separated"),
                Init.languageResourceBundle.getString("Double_colon_separated"),
                Init.languageResourceBundle.getString("Double_comma_separated")
        );
        JCB_temp.setValue(Init.languageResourceBundle.getString("Space_separation"));
        JCB_temp.setVisibleRowCount(6);
    }//combobox添加分隔符选项

    public static void textAreaErrorInfo(JFXTextArea JTA_temp){
        JTA_temp.setValidators(new RequiredFieldValidator(Init.languageResourceBundle.getString("ErrorMessage")));
    }//textarea添加报错信息

}
