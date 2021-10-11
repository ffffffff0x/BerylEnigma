package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.XOR;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Base64.Coding_Base64;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.XOR.Coding_XOR;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 13:20
 **/

public class XORController extends ViewControllerFileMode {
    @FXML
    JFXTextField JTF_key;
    @FXML
    JFXComboBox JCB_charset;

    private JFXComboBox<String> JCB_outPutMode;
    private JFXToggleButton JTB_keyMode;

    @Override
    protected void initialize() {
        super.initialize();
        setFileSelectButton();
        initCharsetComboBox();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    JTA_dst.setText(OutputEncode(TextModeEnCode(),JCB_outPutMode.getValue()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                JTA_dst.setText(OutputEncode(FileModeEnCode(),JCB_outPutMode.getValue()));
//                fileEncodeEnd();
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void getFile() {
        File file_temp = ViewUtils.getFile();
        JTA_src.setText(file_temp.toString());
        file = file_temp;
    }

    @Override
    protected void LoadPopupSettingNode() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Base64",
                        "HEX"
                );
        JCB_outPutMode = new JFXComboBox<>(options);
        JCB_outPutMode.setValue(Init.languageResourceBundle.getString("HEX"));
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("OutputEncoding"), JCB_outPutMode,true));
    }

    private void initCharsetComboBox() {
        ViewInit.comboBoxCharset(JCB_charset);
    }

    private void initJTBKeyMode() {

    }

    private byte[] TextModeEnCode() throws UnsupportedEncodingException {
        return Coding_XOR.encrypt(JTA_src.getText().getBytes(JCB_charset.getValue().toString()),JTF_key.getText().getBytes(JCB_charset.getValue().toString()));
    }

    private byte[] FileModeEnCode() throws UnsupportedEncodingException {
        return Coding_XOR.encrypt(FileUtils.getFilebyte(file),JTF_key.getText().getBytes(JCB_charset.getValue().toString()));
    }

    private String OutputEncode(byte[] msg,String mode) {
        if (mode.equals("Base64")) {
            return Coding_Base64.encodeToString(msg);
        } else if (mode.equals("HEX")) {
            return Coding_HEXCoder.encodeToString(msg);
        }
        return "";
    }

}
