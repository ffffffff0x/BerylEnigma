package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Base64;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Base64.Coding_Base64;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import java.io.File;
import java.util.Objects;

/**
 * @author RyuZU
 */
public class Base64ViewController extends ViewControllerObject {
    byte[] file = null;

    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXToggleButton JTB_modeSelect;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXCheckBox JCHB_isBase64URL;

    @Override
    protected void initialize() {
        super.initialize();
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            //判断是否是Base64URL
            if(!JCHB_isBase64URL.isSelected()){
                //判断是否为文本模式
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    //判断是否使用分隔符
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText()));
                    }else{
                        JTA_dst.setText(Coding_Base64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                    //文件模式
                }else{
                    FileUtils.outPutFile(Coding_Base64.encode(file));
                    FileEncodeend();
                }
                //URLBase64
            }else{
                //判断是否为文本模式
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    //判断是否使用分隔符
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.urlEncodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText()));
                    }else{
                        JTA_dst.setText(Coding_Base64.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                    //文件模式
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlEncode(file));
                    FileEncodeend();
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
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
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.urlDecodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText()));
                    }else{
                        JTA_dst.setText(Coding_Base64.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlDecode(file));
                    FileEncodeend();
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void ONClickModeSelect(){
        if (JTB_modeSelect.isSelected()){
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("FileMode"));
            JTA_src.setEditable(false);
            try {
                if(Objects.nonNull(ViewUtils.getFile())){
                    File file_temp = ViewUtils.getFile();
                    JTA_src.setText(file_temp.toString());
                    file = FileUtils.getFilebyte(file_temp);
                }else {
                    JTB_modeSelect.selectedProperty().setValue(false);
                    JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
                    JTA_src.setText("");
                    JTA_src.setEditable(true);
                }
            }catch (Exception e){
                e.printStackTrace();
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
