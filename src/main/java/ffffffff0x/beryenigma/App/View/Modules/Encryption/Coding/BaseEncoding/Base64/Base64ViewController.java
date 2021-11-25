package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base64;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base64;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

import java.util.Objects;

/**
 * @author RyuZU
 */
public class Base64ViewController extends ViewControllerFileMode {
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXCheckBox JCHB_isBase64URL;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
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
                        JTA_dst.setText(Coding_Base64.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split)));
                    }else{
                        JTA_dst.setText(Coding_Base64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                    //文件模式
                }else{
                    FileUtils.outPutFile(Coding_Base64.encode(byteFile));
                    fileEncodeEnd();
                }
                //URLBase64
            }else{
                //判断是否为文本模式
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    //判断是否使用分隔符
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.urlEncodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split)));
                    }else{
                        JTA_dst.setText(Coding_Base64.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                    //文件模式
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlEncode(byteFile));
                    fileEncodeEnd();
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
                        JTA_dst.setText(Coding_Base64.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split)));
                    }else{
                        JTA_dst.setText(Coding_Base64.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                }else{
                    FileUtils.outPutFile(Coding_Base64.decode(byteFile));
                    fileEncodeEnd();
                }
            }else{
                if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                    if(!Objects.equals(JTF_split.getText(), "")){
                        JTA_dst.setText(Coding_Base64.urlDecodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split)));
                    }else{
                        JTA_dst.setText(Coding_Base64.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString()));
                    }
                }else{
                    FileUtils.outPutFile(Coding_Base64.urlDecode(byteFile));
                    fileEncodeEnd();
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void getFile(){
        super.getFile();
        byteFile = FileUtils.getFilebyte(file);
    }
}
