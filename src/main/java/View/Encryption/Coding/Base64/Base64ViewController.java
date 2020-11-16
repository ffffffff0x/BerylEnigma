package View.Encryption.Coding.Base64;

import Controller.Encryption.Coding.Base64.CodingBase64;
import Init.Init;
import Init.ViewInit;
import Kit.Utils.FlieUtils;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Base64ViewController {
    byte[] file = null;

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXToggleButton JTB_modeCheck;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("Text"))){
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
            if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("Text"))) {
                JTA_dst.setText(CodingBase64.deCode(JTA_src.getText(), JCB_charset.getValue().toString()));
            }else{
                JTA_dst.setText(CodingBase64.deCode(file, JCB_charset.getValue().toString()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JCB_modeSelect(){
        if (JTB_modeCheck.isSelected()){
            JTB_modeCheck.setText(Init.languageResourceBundle.getString("File"));
            JTA_src.setEditable(false);
            try {
                File file_temp = ViewUtils.getFile();
                JTA_src.setText(file_temp.toString());
                file = FlieUtils.getFile(file_temp);
            }catch (Exception e){
                e.printStackTrace();
                JTB_modeCheck.selectedProperty().setValue(false);
                JTB_modeCheck.setText(Init.languageResourceBundle.getString("Text"));
                JTA_src.setText("");
                JTA_src.setEditable(true);
            }

        }else {
            JTB_modeCheck.setText(Init.languageResourceBundle.getString("Text"));
            JTA_src.setText("");
            JTA_src.setEditable(true);
        }
    }
}
