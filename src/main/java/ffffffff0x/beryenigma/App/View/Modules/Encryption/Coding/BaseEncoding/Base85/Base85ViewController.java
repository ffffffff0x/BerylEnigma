package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base85;

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
@ViewNode(name = "Base85",folderPath = "Root/Encryption/Coding/BaseEncoding/",fxmlName = "Base85View.fxml")
public class Base85ViewController extends BaseEncodingViewController {
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
            if (JCB_modeSelect.getValue().equals("Ascii85")){
                return Base85Impl.encodeAscii85SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
                return Base85Impl.encodeRfc1924SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
                return Base85Impl.encodeZ85SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            if (JCB_modeSelect.getValue().equals("Ascii85")){
                return Base85Impl.decodeAscii85SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
                return Base85Impl.decodeRfc1924SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
                return Base85Impl.decodeZ85SplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String encodeToString() {
        try {
            if (JCB_modeSelect.getValue().equals("Ascii85")){
                return Base85Impl.encodeAscii85ToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
                return Base85Impl.encodeRfc1924ToString(JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
            }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
                return Base85Impl.encodeZ85ToString(JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
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
            if (JCB_modeSelect.getValue().equals("Ascii85")){
                return Base85Impl.decodeAscii85ToString(JTA_src.getText(),JCB_charset.getValue().toString());
            }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
                return Base85Impl.decodeRfc1924ToString(JTA_src.getText().getBytes(JCB_charset.getValue().toString()),JCB_charset.getValue().toString());
            }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
                return Base85Impl.decodeZ85ToString(JTA_src.getText().getBytes(JCB_charset.getValue().toString()),JCB_charset.getValue().toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected byte[] encodeToFile() {
        if (JCB_modeSelect.getValue().equals("Ascii85")){
            return Base85Impl.encodeAscii85(byteFile);
        }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
            return Base85Impl.encodeRfc1924(byteFile);
        }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
            return Base85Impl.encodeZ85(byteFile);
        }
        return null;
    }

    @Override
    protected byte[] decodeToFile() {
        if (JCB_modeSelect.getValue().equals("Ascii85")){
            return Base85Impl.decodeAscii85(byteFile);
        }else if (JCB_modeSelect.getValue().equals("Base85-RFC1924")){
            return Base85Impl.decodeRfc1924(byteFile);
        }else if(JCB_modeSelect.getValue().equals("Base85-Z85")) {
            return Base85Impl.decodeZ85(byteFile);
        }
        return null;
    }

    @Override
    protected void LoadPopupSettingNode() {
        super.LoadPopupSettingNode();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Ascii85",
                        "Base85-RFC1924",
                        "Base85-Z85"
                );
        JCB_modeSelect = new JFXComboBox<>(options);
        JCB_modeSelect.setValue("Ascii85");
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("OperateMode"), JCB_modeSelect,true));
    }
}
