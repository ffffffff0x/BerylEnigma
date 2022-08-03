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

import java.io.UnsupportedEncodingException;
import java.util.*;

public class HashController extends ViewControllerFileMode {
    /**
     * JTA_dst1 :HEX result
     * JTA_dst :base64 result
     */

    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXComboBox<String> JCB_hashMode;
    @FXML private JFXComboBox<String> JCB_hashBit;

    @Override
    protected void initialize() {
        super.initialize();
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
            JTA_dst.setStyle("-fx-text-fill: red");
            JTA_dst1.setStyle("-fx-text-fill: red");
            JTA_dst.setText(e.getMessage());
            JTA_dst1.setText(e.getMessage());
        }
    }

    private void initComboBox(){
        HashInstanceList hashInstanceList = new HashInstanceList();

        new HashInstanceList().getInstanceList();
        JCB_hashMode.getItems().addAll(
                hashInstanceList.getInstanceNameList());
        JCB_hashMode.setValue("MD5");
        JCB_hashMode.setVisibleRowCount(6);

        JCB_hashMode.setOnAction(actionEvent -> {
            List<String> bitList = hashInstanceList.getInstanceList().get(JCB_hashMode.getValue());
            JCB_hashBit.getItems().setAll(bitList);
            JCB_hashBit.setValue(bitList.get(0));
        });

        JCB_hashBit.setEditable(false);
        JCB_hashBit.getItems().add("128");
        JCB_hashBit.setValue("128");
        JCB_hashBit.setVisibleRowCount(6);

        ViewInit.comboBoxCharset(JCB_charset);
    }

    private String[] hash(byte[] message){
        String[] out = new String[2];
        if(JCB_hashMode.getValue().equals("MD5-16")){
            byte[] md5_16;
            md5_16 = Arrays.copyOfRange(Modern_Hash.hash(message,JCB_hashMode.getValue(),JCB_hashBit.getValue()),4,12);
            out[0] = Hex.encodeHexString(md5_16);
            out[1] = Base64.encodeBase64String(md5_16);
        }else{
            out[0] = Hex.encodeHexString(Modern_Hash.hash(message,JCB_hashMode.getValue(),JCB_hashBit.getValue()));
            out[1] = Base64.encodeBase64String(Modern_Hash.hash(message,JCB_hashMode.getValue(),JCB_hashBit.getValue()));
        }
        return out;
    }

    @Override
    public void getFile(){
        super.getFile();
//        byteFile = FileUtils.getFilebyte(file);
    }

    @Override
    protected void JTADSTContextMenu() {
        super.JTADSTContextMenu();
        ViewInit.textAreaContextMenu(JTA_dst1,JTA_src);
    }
}
