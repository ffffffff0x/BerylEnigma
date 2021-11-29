package ffffffff0x.beryenigma.Init;

import com.jfoenix.controls.JFXChip;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.nio.charset.StandardCharsets;

public class ViewInit {
    public static void comboBoxCharset(JFXComboBox JCB_temp) {
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

    public static void textAreaErrorInfoGeneral(JFXTextArea JTA_temp) {
        JTA_temp.setValidators(new RequiredFieldValidator(Init.languageResourceBundle.getString("ErrorMessage")));
    }//textarea添加报错信息

    public static void textAreaErrorInfoNumCheck(JFXTextField JTF_temp) {
        JTF_temp.setValidators(new RequiredFieldValidator(Init.languageResourceBundle.getString("ErrorMessage_isNum")));
    }//textarea添加报错信息

    public static void textAreaContextMenu(JFXTextArea JTA_dst,JFXTextArea JTA_src) {
        // 创建右键菜单
        ContextMenu contextMenu = new ContextMenu();
        // 菜单项
        MenuItem selectAllAndCopy = new MenuItem(Init.languageResourceBundle.getString("SelectAllAndCopy"));
        selectAllAndCopy.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(JTA_dst.getText());
            clipboard.setContent(clipboardContent);
        });
        // 菜单项
        MenuItem outputFillInputBox = new MenuItem(Init.languageResourceBundle.getString("OutputFillInputBox"));
        outputFillInputBox.setOnAction(event -> {
            JTA_src.setText(JTA_dst.getText());
        });
        contextMenu.getItems().addAll(selectAllAndCopy,outputFillInputBox);
        JTA_dst.setContextMenu(contextMenu);
    }
}
