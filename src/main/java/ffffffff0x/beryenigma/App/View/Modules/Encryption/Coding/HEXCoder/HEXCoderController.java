package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.HEXCoder;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class HEXCoderController extends ViewControllerFileMode {

    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXTextField JTF_split;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
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
                FileUtils.outPutFile(Coding_HEXCoder.encode(byteFile));
                fileEncodeEnd();
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
                FileUtils.outPutFile(Coding_HEXCoder.decode(byteFile));
                fileEncodeEnd();
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
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


    @Override
    public void getFile(){
        super.getFile();
        byteFile = FileUtils.getFilebyte(file);
    }
}
