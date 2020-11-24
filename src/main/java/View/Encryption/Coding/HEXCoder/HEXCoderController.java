package View.Encryption.Coding.HEXCoder;

import Controller.Encryption.Coding.Base64.CodingBase64;
import Controller.Encryption.Coding.HEXCoder.CodingHEXCoder;
import Init.Init;
import Init.ViewInit;
import Kit.Utils.FlieUtils;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class HEXCoderController {
    byte[] file = null;

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXToggleButton JTB_modeCheck;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.textAreaErrorInfo(JTA_dst);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    JTA_dst.setText(HEXCodeEnCode());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                FlieUtils.outPutFile(CodingHEXCoder.encode(file));
                FileEncodeend();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void ONClick_JBT_deCode(){
        try{
            if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    JTA_dst.setText(HEXCodeDeCode());
                } catch (UnsupportedEncodingException e) {
                    JTA_dst.validate();
                }
            }else{
                FlieUtils.outPutFile(CodingHEXCoder.decode(file));
                FileEncodeend();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void ResetValidator(){
        JTA_dst.resetValidation();
    }

    @FXML
    public void ONClick_JCB_modeSelect(){
        if (JTB_modeCheck.isSelected()){
            JTB_modeCheck.setText(Init.languageResourceBundle.getString("FileMode"));
            JTA_src.setEditable(false);
            try {
                File file_temp = ViewUtils.getFile();
                JTA_src.setText(file_temp.toString());
                file = FlieUtils.getFile(file_temp);
            }catch (Exception e){
                e.printStackTrace();
                JTB_modeCheck.selectedProperty().setValue(false);
                JTB_modeCheck.setText(Init.languageResourceBundle.getString("TextMode"));
                JTA_src.setText("");
                JTA_src.setEditable(true);
            }
        }else {
            JTB_modeCheck.setText(Init.languageResourceBundle.getString("TextMode"));
            JTA_src.setText("");
            JTA_src.setEditable(true);
        }
    }

    private String HEXCodeEnCode() throws UnsupportedEncodingException {
        return CodingHEXCoder.encode(JTA_src.getText(),
                JTF_split.getText(),
                JCB_charset.getValue().toString());
    }

    private String HEXCodeDeCode() throws UnsupportedEncodingException {
        return CodingHEXCoder.decode(JTA_src.getText(),
                JTF_split.getText(),
                JCB_charset.getValue().toString());
    }

    public void FileEncodeend(){
        JTB_modeCheck.selectedProperty().set(false);
        JTB_modeCheck.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.languageResourceBundle.getString("Complete"));
    }
}
