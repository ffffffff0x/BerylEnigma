package Main.View.Modules.Encryption.Coding.HEXCoder;

import App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import Init.Init;
import Init.ViewInit;
import Kit.Utils.FileUtils;
import Kit.Utils.ViewUtils;
import App.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class HEXCoderController extends ViewControllerObject {
    byte[] file = null;

    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXToggleButton JTB_modeSelect;

    @Override
    protected void initialize() {
        super.initialize();
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    JTA_dst.setText(HEXCodeEnCode());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                FileUtils.outPutFile(Coding_HEXCoder.encode(file));
                FileEncodeend();
            }
        }catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try{
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    JTA_dst.setText(HEXCodeDeCode());
                } catch (UnsupportedEncodingException e) {
                    JTA_dst.validate();
                }
            }else{
                FileUtils.outPutFile(Coding_HEXCoder.decode(file));
                FileEncodeend();
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void ONClick_JCB_modeSelect(){
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

    private String HEXCodeEnCode() throws UnsupportedEncodingException {
        return Coding_HEXCoder.encode(JTA_src.getText(),
                JTF_split.getText(),
                JCB_charset.getValue().toString());
    }

    private String HEXCodeDeCode() throws UnsupportedEncodingException {
        return Coding_HEXCoder.decode(JTA_src.getText(),
                JTF_split.getText(),
                JCB_charset.getValue().toString());
    }

    public void FileEncodeend(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.languageResourceBundle.getString("Complete"));
    }
}
