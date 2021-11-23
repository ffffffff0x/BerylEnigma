package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Brainfuck;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.Coding_Brainfuck;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-17 15:29
 **/

public class BrainfuckController extends ViewController {
    @FXML private JFXComboBox<String> JCB_modeCheck;
    @FXML private JFXComboBox<String> JCB_charset;

    @FXML
    private JFXSpinner JSP_running;
    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        ComboBoxInit();
    }

    /**
     * 全局解密按钮事件
     */
    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
            JSP_running.setVisible(true);
            new Thread(() -> {
                String result = "";
                try {
                    if (JCB_modeCheck.getValue().equals("Brainfuck")) {
                        result = Coding_Brainfuck.BrainfuckDeCode(new String(JTA_src.getText().getBytes(JCB_charset.getValue())));
                    }else if (JCB_modeCheck.getValue().equals("Ook")) {
                        result = Coding_Brainfuck.OokDeCode(new String(JTA_src.getText().getBytes(JCB_charset.getValue())));
                    }else if (JCB_modeCheck.getValue().equals("Trollscript")) {
                        result = Coding_Brainfuck.TrollscriptDeCode(new String(JTA_src.getText().getBytes(JCB_charset.getValue())));
                    }
                }catch (Exception e) {
                    ViewUtils.textAreaValidate(JTA_dst);
                }
                String finalResult = result;
                Platform.runLater(() -> {
                    JTA_dst.setText(finalResult);
                    JSP_running.setVisible(false);
                });
            }).start();
    }

    private void ComboBoxInit() {
        ViewInit.comboBoxCharset(JCB_charset);
        JCB_charset.setValue("GBK");

        JCB_modeCheck.getItems().addAll(
                "Brainfuck",
                "Ook",
                "Trollscript"
        );
        JCB_modeCheck.setValue("Brainfuck");
    }
}
