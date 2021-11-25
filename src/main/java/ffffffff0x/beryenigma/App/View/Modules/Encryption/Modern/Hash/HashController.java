package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Hash;

import ffffffff0x.beryenigma.App.Controller.Encryption.Modern.Hash.Modern_Hash;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class HashController extends ViewControllerFileMode {
    /**
     * JTA_dst1 :HEX result
     * JTA_dst :base64 result
     */

    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXComboBox JCB_hashMode;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
        initComboBox();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try{
            String[] dst = new String[0];
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    dst = hash(JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                dst = hash(byteFile);
                fileEncodeEnd();
            }
            JTA_dst1.setText(dst[0]);
            JTA_dst.setText(dst[1]);
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    private void initComboBox(){
        JCB_hashMode.getItems().addAll(
                "MD5",
                "MD5-16",
                "MD2",
                "MD4",
                "SHA-1",
                "SHA-224",
                "SHA-256",
                "SHA-384",
                "SHA-512",
                "SHA-512/224",
                "SHA-512/256",
                "SHA3-224",
                "SHA3-256",
                "SHA3-384",
                "SHA3-512");
        JCB_hashMode.setValue("MD5");
        JCB_hashMode.setVisibleRowCount(6);
        ViewInit.comboBoxCharset(JCB_charset);
    }

    private String[] hash(byte[] message){
        String[] out = new String[2];
        if(JCB_hashMode.getValue().equals("MD5-16")){
            byte[] md5_16;
            md5_16 = Arrays.copyOfRange(Modern_Hash.hash(message,JCB_hashMode.getValue().toString()),4,12);
            out[0] = Hex.encodeHexString(md5_16);
            out[1] = Base64.encodeBase64String(md5_16);
        }else{
            out[0] = Hex.encodeHexString(Modern_Hash.hash(message,JCB_hashMode.getValue().toString()));
            out[1] = Base64.encodeBase64String(Modern_Hash.hash(message,JCB_hashMode.getValue().toString()));
        }
        return out;
    }

    @Override
    public void getFile(){
        super.getFile();
        byteFile = FileUtils.getFilebyte(file);
    }
}
