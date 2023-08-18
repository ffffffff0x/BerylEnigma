package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base64;

import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.Objects;

/**
 * @author RyuZU
 */
@ViewNode(name = "Base64",folderPath = "Root/Encryption/Coding/BaseEncoding/",fxmlName = "Base64View.fxml")
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
                    if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                        String tmp;
                        //判断是否使用分隔符
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Base64Impl.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Base64Impl.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                        //文件模式
                    }else{
                        byte[] tmp = Base64Impl.encode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                    //URLBase64
                }else{
                    //判断是否为文本模式
                    if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                        String tmp;
                        //判断是否使用分隔符
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Base64Impl.urlEncodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Base64Impl.urlEncodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                        //文件模式
                    }else{
                        byte[] tmp = Base64Impl.urlEncode(byteFile);
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
                    if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                        String tmp;
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Base64Impl.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Base64Impl.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                    }else{
                        byte[] tmp = Base64Impl.decode(byteFile);
                        Platform.runLater(() -> {
                            FileUtils.outPutFile(tmp);
                            fileEncodeEnd();
                        });
                    }
                }else{
                    if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                        String tmp;
                        if(!Objects.equals(JTF_split.getText(), "")){
                            tmp = Base64Impl.urlDecodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
                        }else{
                            tmp = Base64Impl.urlDecodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
                        }
                        Platform.runLater(() -> JTA_dst.setText(tmp));
                    }else{
                        byte[] tmp = Base64Impl.urlDecode(byteFile);
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
