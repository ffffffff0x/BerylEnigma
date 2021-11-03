package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base58;

import com.jfoenix.controls.JFXComboBox;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base58;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.BaseEncodingViewController;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/10/19 21:12
 **/
public class Base58ViewController extends BaseEncodingViewController {
    private JFXComboBox<String> JCB_modeSelect;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void onClickEncodeImpl() {
        super.onClickEncodeImpl();
    }

    @Override
    protected void onClickDecodeImpl() {
        super.onClickDecodeImpl();
    }

    @Override
    public void getFile() {
        super.getFile();
    }

    @Override
    protected String encodeSplitToString() {
        try {
            return Coding_Base58.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            return Coding_Base58.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String encodeToString() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                return Coding_Base58.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return Coding_Base58.encodeChecked(0,JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return Coding_Base58.encodeChecked(1,JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeToString() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                return Coding_Base58.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return new String(Coding_Base58.decodeChecked(JTA_src.getText()),JCB_charset.getValue().toString());
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return new String(Coding_Base58.decodeChecked(JTA_src.getText()),JCB_charset.getValue().toString());
                }
           }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected void encodeToFile() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                FileUtils.outPutFile(Coding_Base58.encodeToString(file).getBytes(JCB_charset.getValue().toString()));
            } else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    FileUtils.outPutFile(Coding_Base58.encodeChecked(0,JTA_src.getText().getBytes(JCB_charset.getValue().toString())).getBytes(JCB_charset.getValue().toString()));
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    FileUtils.outPutFile(Coding_Base58.encodeChecked(1,JTA_src.getText().getBytes(JCB_charset.getValue().toString())).getBytes(JCB_charset.getValue().toString()));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @Override
    protected void decodeToFile() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                FileUtils.outPutFile(Coding_Base58.decode(file,JCB_charset.getValue().toString()));
            } else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    FileUtils.outPutFile(Coding_Base58.decodeChecked(new String(file,JCB_charset.getValue().toString())));
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    FileUtils.outPutFile(Coding_Base58.decodeChecked(new String(file,JCB_charset.getValue().toString())));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @Override
    protected void LoadPopupSettingNode() {
        super.LoadPopupSettingNode();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Base58-String",
                        "Base58-Check(P2PKH)",
                        "Base58-Check(P2SH)"
                );
        JCB_modeSelect = new JFXComboBox<>(options);
        JCB_modeSelect.setValue("Base58-String");
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("OperateMode"), JCB_modeSelect,true));
    }
}
