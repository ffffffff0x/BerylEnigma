package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.XOR;

import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base64.Base64Impl;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.HEXCoder.HEXCoderImpl;
import ffffffff0x.beryenigma.App.View.Viewobj.ControllerViewFileMode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2025/3/10 19:00
 **/
public class XORView extends ControllerViewFileMode {

    // 字符集组合框
    private JFXComboBox<String> JCB_charset;
    // 密钥文本框
    private JFXTextField JTF_key;

    private JFXComboBox<String> JCB_outPutMode;
    private JFXToggleButton JTB_keyMode;

    public XORView() {
        super();
    }

    @Override
    protected void initializeUI() {
        super.initializeUI();

        JLB_title.setText(Init.getLanguage("XOR"));

        JTA_src = new JFXTextArea();
        JTA_src.setPrefSize(530, 165);
        AnchorPane.setLeftAnchor(JTA_src, 40.0);
        AnchorPane.setRightAnchor(JTA_src, 40.0);
        AnchorPane.setTopAnchor(JTA_src, 30.0);

        JTA_dst = new JFXTextArea();
        JTA_dst.setPrefSize(530, 165);
        JTA_dst.setEditable(false);
        AnchorPane.setLeftAnchor(JTA_dst, 40.0);
        AnchorPane.setRightAnchor(JTA_dst, 40.0);
        AnchorPane.setTopAnchor(JTA_dst, 315.0);
        AnchorPane.setBottomAnchor(JTA_dst, 37.0);

        JCB_charset = new JFXComboBox<>();
        JCB_charset.setPrefSize(100, 30);

        JTF_key = new JFXTextField();
        JTF_key.setPrefSize(200, 30);
        JTF_key.setPromptText(Init.getLanguage("Key"));

        JBT_confirm = new JFXButton(Init.getLanguage("EnCode"));
        JBT_confirm.setPrefSize(101, 50);
        JBT_confirm.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_confirm.setOnAction(event -> ONClickConfirm());

        ACP_controllerAnchorPane.getChildren().addAll(JTA_src, JTA_dst, JCB_charset, JTF_key, JBT_confirm);

        HBox hbox = new HBox(JCB_charset, JTF_key, JBT_confirm);
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(hbox, 40.0);
        AnchorPane.setRightAnchor(hbox, 40.0);
        AnchorPane.setTopAnchor(hbox, 220.0);
        ACP_controllerAnchorPane.getChildren().add(hbox);

        initCharsetComboBox();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))) {
                try {
                    JTA_dst.setText(OutputEncode(TextModeEnCode(),JCB_outPutMode.getValue()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                FileUtils.outPutFile(FileModeEnCode());
                fileEncodeEnd();
            }
        }catch (Exception e){
            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

//    @Override
//    public void getFile() {
//        File file_temp = ViewUtils.getFile();
//        JTA_src.setText(file_temp.toString());
//        file = file_temp;
//    }

    @Override
    protected void LoadPopupSettingNode() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Base64",
                        "HEX"
                );
        JCB_outPutMode = new JFXComboBox<>(options);
        JCB_outPutMode.setValue(Init.getLanguage("HEX"));
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("OutputEncoding"), JCB_outPutMode,true));
    }

    private void initCharsetComboBox() {
        ViewInit.comboBoxCharset(JCB_charset);
    }

    private void initJTBKeyMode() {

    }

    private byte[] TextModeEnCode() throws UnsupportedEncodingException {
        return XORImpl.encrypt(JTA_src.getText().getBytes(JCB_charset.getValue().toString()),JTF_key.getText().getBytes(JCB_charset.getValue().toString()));
    }

    private byte[] FileModeEnCode() throws UnsupportedEncodingException {
        return XORImpl.encrypt(FileUtils.getFilebyte(file),JTF_key.getText().getBytes(JCB_charset.getValue().toString()));
    }

    private String OutputEncode(byte[] msg,String mode) {
        if (mode.equals("Base64")) {
            return Base64Impl.encodeToString(msg);
        } else if (mode.equals("HEX")) {
            return HEXCoderImpl.encodeToString(msg);
        }
        return "";
    }

}
