package Main.View.Encryption.Coding.Base64;

import Main.Controller.Encryption.Coding.Base64.Coding_Base64;
import Init.Init;
import Init.ViewInit;
import Kit.Utils.FileUtils;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

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
    @FXML private JFXToggleButton JTB_modeSelect;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXCheckBox JCHB_isBase64URL;

    @FXML
    private void initialize() {
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            if(!JCHB_isBase64URL.isSelected()){
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText()));
                    }else{
                        JTA_dst.setText(Coding_Base64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                }else{
                    FileUtils.outPutFile(Coding_Base64.encode(file));
                    FileEncodeend();
                }
            }else{
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(Coding_Base64.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlEncode(file));
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
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText()));
                    }else{
                        JTA_dst.setText(Coding_Base64.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                }else{
                    FileUtils.outPutFile(Coding_Base64.decode(file));
                    FileEncodeend();
                }
            }else{
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    JTA_dst.setText(Coding_Base64.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlDecode(file));
                    FileEncodeend();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClick_JTB_modeSelect(){
        if (JTB_modeSelect.isSelected()){
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("FileMode"));
            JTA_src.setEditable(false);
            try {
                File file_temp = ViewUtils.getFile();
                JTA_src.setText(file_temp.toString());
                file = FileUtils.getFilebyte(file_temp);
            }catch (Exception e){
                e.printStackTrace();
                JTB_modeSelect.selectedProperty().setValue(false);
                JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
                JTA_src.setText("");
                JTA_src.setEditable(true);
            }
        }else {
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
            JTA_src.setText("");
            JTA_src.setEditable(true);
        }
    }

    public void FileEncodeend(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.languageResourceBundle.getString("Complete"));
    }

}
