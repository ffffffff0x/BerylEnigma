package View.Encryption.Coding.HEXCoder;

import Controller.Encryption.Coding.HEXCoder.CodingHEXCoder;
import Init.ViewInit;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.UnsupportedEncodingException;

public class HEXCoderController {
    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXComboBox JCB_split;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.comboBoxSplit(JCB_split);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            JTA_dst.setText(HEXCodeEnCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JBT_deCode(){
        try {
            JTA_dst.setText(HEXCodeDeCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String HEXCodeEnCode() throws UnsupportedEncodingException {
        return CodingHEXCoder.enCode(JTA_src.getText(),
                ViewUtils.comboxSplitConvert(JCB_split.getValue().toString()),
                JCB_charset.getValue().toString());
    }

    private String HEXCodeDeCode() throws UnsupportedEncodingException {
        return CodingHEXCoder.deCode(JTA_src.getText(),
                ViewUtils.comboxSplitConvert(JCB_split.getValue().toString()),
                JCB_charset.getValue().toString());
    }
}
