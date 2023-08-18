package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Brainfuck;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author: RyuZUSUNC
 * @create: 2021-09-17 15:29
 **/
@ViewNode(name = "Brainfuck",folderPath = "Root/Encryption/Coding/",fxmlName = "BrainfuckView.fxml")
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
                    result = BrainfuckImpl.BrainfuckDeCode(JTA_src.getText(),JCB_charset.getValue());
                }else if (JCB_modeCheck.getValue().equals("Ook")) {
                    result = BrainfuckImpl.OokDeCode(JTA_src.getText(),JCB_charset.getValue());
                }else if (JCB_modeCheck.getValue().equals("ShortOok")) {
                    result = BrainfuckImpl.shortOokDeCode(JTA_src.getText(),JCB_charset.getValue());
                }else if (JCB_modeCheck.getValue().equals("Trollscript")) {
                    result = BrainfuckImpl.TrollscriptDeCode(JTA_src.getText(),JCB_charset.getValue());
                }
            }catch (Exception e) {
                e.printStackTrace();
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
        JCB_charset.getItems().addAll(
                "UTF-8",
                "GBK",
                "BIG5",
                "UTF-16",
                "UTF-16LE",
                "UTF-16BE",
                "ISO-8859_1",
                "US-ASCII"
        );
        JCB_charset.setValue("UTF-8");
        JCB_charset.setVisibleRowCount(6);

        JCB_modeCheck.getItems().addAll(
                "Brainfuck",
                "Ook",
                "ShortOok",
                "Trollscript"
        );
        JCB_modeCheck.setValue("Brainfuck");
    }
}
