package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.HMAC;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import ffffffff0x.beryenigma.App.Controller.Encryption.Modern.HMAC.Modern_HMAC;
import ffffffff0x.beryenigma.App.Controller.Encryption.Modern.Hash.Modern_Hash;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingDoubleColumnView;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import javafx.fxml.FXML;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HMACController extends ViewControllerFileMode {
    /**
     * JTA_dst1 :HEX result
     * JTA_dst :base64 result
     */

    @FXML private JFXComboBox JCB_charset = new JFXComboBox();
    @FXML private JFXComboBox<String> JCB_HMACMode;
    @FXML private JFXTextArea JTA_HMACKey;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
        initComboBox();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        JTA_dst.setStyle("-fx-text-fill: black");
        JTA_dst1.setStyle("-fx-text-fill: black");
        try{
            String[] dst = new String[0];
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    dst = hmac(JTA_src.getText().getBytes(JCB_charset.getValue().toString()),
                            JTA_HMACKey.getText().getBytes(JCB_charset.getValue().toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                dst = hmac(byteFile, JTA_HMACKey.getText().getBytes(JCB_charset.getValue().toString()));
                fileEncodeEnd();
            }
            JTA_dst1.setText(dst[0]);
            JTA_dst.setText(dst[1]);
        }catch (Exception e){
            JTA_dst.setStyle("-fx-text-fill: red");
            JTA_dst1.setStyle("-fx-text-fill: red");
            JTA_dst.setText(e.getMessage());
            JTA_dst1.setText(e.getMessage());
        }
    }

    private void initComboBox(){
        ViewInit.comboBoxCharset(JCB_charset);
        JCB_HMACMode.getItems().addAll(getInstanceNameList());
        JCB_HMACMode.setValue("HMACMD5");
        JCB_HMACMode.setVisibleRowCount(6);

    }

    private String[] hmac(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] out = new String[2];
        out[0] = Hex.encodeHexString(Objects.requireNonNull(Modern_HMAC.encryptHMAC(data,
                key, JCB_HMACMode.getValue())));
        out[1] = Base64.encodeBase64String(Objects.requireNonNull(Modern_HMAC.encryptHMAC(data,
                key, JCB_HMACMode.getValue())));
        return out;
    }
    @Override
    public void getFile(){
        super.getFile();
        byteFile = FileUtils.getFilebyte(file);
    }

    @Override
    protected void JTADSTContextMenu() {
        super.JTADSTContextMenu();
        ViewInit.textAreaContextMenu(JTA_dst1,JTA_src);
    }

    public List<String> getInstanceNameList() {
        List<String> result = Arrays.asList("HMACMD2",
                "HMACMD4",
                "HMACMD5",
                "HMACSM3",
                "HMACSHA1",
                "HMACSHA224",
                "HMACSHA256",
                "HMACSHA3-224",
                "HMACSHA3-256",
                "HMACSHA3-384",
                "HMACSHA3-512",
                "HMACSHA384",
                "HMACSHA512",
                "HMACSHA512/224",
                "HMACSHA512/256",
                "HMACPBESHA1",
                "HMACPBESHA224",
                "HMACPBESHA256",
                "HMACPBESHA384",
                "HMACPBESHA512",
                "HMACPBESHA512/224",
                "HMACPBESHA512/256",
                "HMACRIPEMD128",
                "HMACRIPEMD160",
                "HMACRIPEMD256",
                "HMACRIPEMD320",
                "HMACSKEIN-1024-1024",
                "HMACSKEIN-1024-384",
                "HMACSKEIN-1024-512",
                "HMACSKEIN-256-128",
                "HMACSKEIN-256-160",
                "HMACSKEIN-256-224",
                "HMACSKEIN-256-256",
                "HMACSKEIN-512-128",
                "HMACSKEIN-512-160",
                "HMACSKEIN-512-224",
                "HMACSKEIN-512-256",
                "HMACSKEIN-512-384",
                "HMACSKEIN-512-512",
                "HMACDSTU7564-256",
                "HMACDSTU7564-384",
                "HMACDSTU7564-512",
                "HMACGOST3411",
                "HMACGOST3411-2012-256",
                "HMACGOST3411-2012-512",
                "HMACKECCAK224",
                "HMACKECCAK256",
                "HMACKECCAK288",
                "HMACKECCAK384",
                "HMACKECCAK512",
                "HMACTIGER",
                "HMACWHIRLPOOL",
                "OLDHMACSHA384",
                "OLDHMACSHA512");
        return result;
    }

    @Override
    protected void LoadPopupSettingNode() {
        super.LoadPopupSettingNode();

        //弹出式控件框
        PopupSettingDoubleColumnView popupSettingView = new PopupSettingDoubleColumnView(ACP_controllerAnchorPane);

        //弹出式控件框中添加初始化的控件
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("Charset"),JCB_charset,true));
    }
}
