package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SM3;

import ffffffff0x.beryenigma.App.Controller.Encryption.Modern.SM3.Modern_SM3;
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
import java.io.IOException;

public class SM3Controller extends ViewControllerFileMode {
    /**
     * JTA_dst1 :HEX result
     * JTA_dst :base64 result
     */

    @FXML private JFXComboBox JCB_charset;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            String[] dst = new String[0];
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                try {
                    dst = hash(JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
                } catch (IOException e) {
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

    private String[] hash(byte[] message) throws IOException {
        String[] out = new String[2];
        out[0] = Hex.encodeHexString(Modern_SM3.hash(message));
        out[1] = Base64.encodeBase64String(Modern_SM3.hash(message));
        return out;
    }

    @Override
    protected void JTADSTContextMenu() {
        super.JTADSTContextMenu();
        ViewInit.textAreaContextMenu(JTA_dst1,JTA_src);
    }
}
