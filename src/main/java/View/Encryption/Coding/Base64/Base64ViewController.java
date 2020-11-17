package View.Encryption.Coding.Base64;

import Controller.Encryption.Coding.Base64.CodingBase64;
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
public class Base64ViewController {
    byte[] file = null;

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXToggleButton JTB_modeCheck;
    @FXML private JFXCheckBox JCHB_isBase64URL;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            if(!JCHB_isBase64URL.isSelected()){
                if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(CodingBase64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FlieUtils.outPutFile(CodingBase64.encode(file));
                    FileEncodeend();
                }
            }else{
                if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(CodingBase64.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FlieUtils.outPutFile(CodingBase64.urlEncode(file));
                    FileEncodeend();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JBT_deCode(){
        try {
            if(!JCHB_isBase64URL.isSelected()){
                if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(CodingBase64.decodetostring(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FlieUtils.outPutFile(CodingBase64.decode(file));
                    FileEncodeend();
                }
            }else{
                if(JTB_modeCheck.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(CodingBase64.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FlieUtils.outPutFile(CodingBase64.urlDecode(file));
                    FileEncodeend();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    public void FileEncodeend(){
        JTB_modeCheck.selectedProperty().set(false);
        JTB_modeCheck.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.languageResourceBundle.getString("Complete"));
    }

}
