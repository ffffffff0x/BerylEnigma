package View.Encryption.Coding.Base64;

import Controller.Encryption.Coding.Base64.CodingBase64;
import Init.Init;
import Init.ViewInit;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.UnsupportedEncodingException;

public class Base64ViewController {
    byte[] file = null;

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXComboBox JCB_modeCheck;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.comboBoxInputCheck(JCB_modeCheck);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            if(JCB_modeCheck.getValue().toString().equals(Init.languageResourceBundle.getString("Text"))){
                JTA_dst.setText(CodingBase64.enCode(JTA_src.getText(),JCB_charset.getValue().toString()));
            }else{
                JTA_dst.setText(CodingBase64.enCode(file,JCB_charset.getValue().toString()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JBT_deCode(){
        try {
            if(JCB_modeCheck.getValue().toString().equals(Init.languageResourceBundle.getString("Text"))) {
                JTA_dst.setText(CodingBase64.deCode(JTA_src.getText(), JCB_charset.getValue().toString()));
            }else{
                JTA_dst.setText(CodingBase64.deCode(file, JCB_charset.getValue().toString()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JCB_modeCheck(){
        file = ViewUtils.comboBoxInputCheck(JCB_modeCheck,JCB_modeCheck.getValue().toString(),JTA_src);
    }
}
