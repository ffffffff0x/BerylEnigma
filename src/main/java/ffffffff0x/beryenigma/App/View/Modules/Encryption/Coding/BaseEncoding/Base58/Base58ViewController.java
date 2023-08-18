package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base58;

import com.jfoenix.controls.JFXComboBox;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.BaseEncodingViewController;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/10/19 21:12
 **/
@ViewNode(name = "Base58",folderPath = "Root/Encryption/Coding/BaseEncoding/",fxmlName = "Base58View.fxml")
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
            return Base58Impl.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            return Base58Impl.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
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
                return Base58Impl.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return Base58Impl.encodeChecked(0,JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return Base58Impl.encodeChecked(1,JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
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
                return Base58Impl.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return new String(Base58Impl.decodeChecked(JTA_src.getText()),JCB_charset.getValue().toString());
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return new String(Base58Impl.decodeChecked(JTA_src.getText()),JCB_charset.getValue().toString());
                }
           }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected byte[] encodeToFile() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                return Base58Impl.encodeToString(byteFile).getBytes(JCB_charset.getValue().toString());
            } else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return Base58Impl.encodeChecked(0,JTA_src.getText().getBytes(JCB_charset.getValue().toString())).getBytes(JCB_charset.getValue().toString());
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return Base58Impl.encodeChecked(1,JTA_src.getText().getBytes(JCB_charset.getValue().toString())).getBytes(JCB_charset.getValue().toString());
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return null;
    }

    @Override
    protected byte[] decodeToFile() {
        try {
            if (JCB_modeSelect.getValue().equals("Base58-String")){
                return Base58Impl.decode(byteFile,JCB_charset.getValue().toString());
            } else {
                if (JCB_modeSelect.getValue().equals("Base58-Check(P2PKH)")) {
                    return Base58Impl.decodeChecked(new String(byteFile,JCB_charset.getValue().toString()));
                }else if(JCB_modeSelect.getValue().equals("Base58-Check(P2SH)")) {
                    return Base58Impl.decodeChecked(new String(byteFile,JCB_charset.getValue().toString()));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return null;
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
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("OperateMode"), JCB_modeSelect,true));
    }
}
