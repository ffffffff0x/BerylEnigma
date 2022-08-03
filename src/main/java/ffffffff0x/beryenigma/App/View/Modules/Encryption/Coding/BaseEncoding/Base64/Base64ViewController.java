package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base64;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base64;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.application.Platform;
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
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        new Thread(() -> {
            try {
                //判断是否是Base64URL
                if(!JCHB_isBase64URL.isSelected()){
                    //判断是否为文本模式
                    if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                        String tmp;
                        //判断是否使用分隔符
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Coding_Base64.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Coding_Base64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                        //文件模式
                    }else{
                        byte[] tmp = Coding_Base64.encode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                    //URLBase64
                }else{
                    //判断是否为文本模式
                    if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                        String tmp;
                        //判断是否使用分隔符
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Coding_Base64.urlEncodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Coding_Base64.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                        //文件模式
                    }else{
                        byte[] tmp = Coding_Base64.urlEncode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
                ViewUtils.textAreaValidate(JTA_dst);
            }
        }).start();
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        new Thread(() -> {
            try {
                if(!JCHB_isBase64URL.isSelected()){
                    if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                        String tmp;
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Coding_Base64.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Coding_Base64.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                    }else{
                        byte[] tmp = Coding_Base64.decode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                }else{
                    if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                        String tmp;
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Coding_Base64.urlDecodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Coding_Base64.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                    }else{
                        byte[] tmp = Coding_Base64.urlDecode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
                ViewUtils.textAreaValidate(JTA_dst);
            }
        }).start();
    }

    @Override
    public void getFile(){
        super.getFile();
    }
}
