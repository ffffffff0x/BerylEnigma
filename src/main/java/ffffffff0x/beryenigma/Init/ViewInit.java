package ffffffff0x.beryenigma.Init;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.nio.charset.StandardCharsets;

public class ViewInit {
    /**
     * JFXComboBox 添加字符集选项
     * @param JCB_temp JFXComboBox
     */
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
    }

    /**
     * JFXTextArea添加报错信息
     * @param JTA_temp JFXTextArea
     */
    public static void textAreaErrorInfoGeneral(JFXTextArea JTA_temp) {
        JTA_temp.setValidators(new RequiredFieldValidator(Init.getLanguage("ErrorMessage")));
    }

    /** JFXTextField添加报错信息
     * @param JTF_temp JFXTextField
     */
    public static void textAreaErrorInfoNumCheck(JFXTextField JTF_temp) {
        JTF_temp.setValidators(new RequiredFieldValidator(Init.getLanguage("ErrorMessage_isNum")));
    }

    /**
     * 初始化无文件选择器的右键菜单
     *
     * @param JTA_dst 输出框 JFXTextArea
     * @param JTA_src 输入框 JFXTextArea
     */
    public static void textAreaContextMenu(JFXTextArea JTA_dst,JFXTextArea JTA_src) {
        // 创建右键菜单
        ContextMenu contextMenu = new ContextMenu();
        // 菜单项
        MenuItem selectAllAndCopy = new MenuItem(Init.getLanguage("SelectAllAndCopy"));
        selectAllAndCopy.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(JTA_dst.getText());
            clipboard.setContent(clipboardContent);
        });
        // 菜单项
        MenuItem outputFillInputBox = new MenuItem(Init.getLanguage("OutputFillInputBox"));
        outputFillInputBox.setOnAction(event -> {
            JTA_src.setText(JTA_dst.getText());
        });
        contextMenu.getItems().addAll(selectAllAndCopy,outputFillInputBox);
        JTA_dst.setContextMenu(contextMenu);
    }

    /**
     * 初始化有文件选择器的右键菜单
     *
     * @param JTA_dst 输出框 JFXTextArea
     * @param JTA_src 输入框 JFXTextArea
     * @param jfxToggleButton JFXToggleButton
     */
    public static void textAreaContextMenu(JFXTextArea JTA_dst, JFXTextArea JTA_src, JFXToggleButton jfxToggleButton) {
        // 创建右键菜单
        ContextMenu contextMenu = new ContextMenu();
        // 菜单项
        MenuItem selectAllAndCopy = new MenuItem(Init.getLanguage("SelectAllAndCopy"));
        selectAllAndCopy.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(JTA_dst.getText());
            clipboard.setContent(clipboardContent);
        });
        // 菜单项
        MenuItem outputFillInputBox = new MenuItem(Init.getLanguage("OutputFillInputBox"));
        outputFillInputBox.setOnAction(event -> {
            JTA_src.setText(JTA_dst.getText());
            jfxToggleButton.setText(Init.getLanguage("TextMode"));
            jfxToggleButton.setSelected(false);
        });
        contextMenu.getItems().addAll(selectAllAndCopy,outputFillInputBox);
        JTA_dst.setContextMenu(contextMenu);
    }
}
