package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.HMAC;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import javafx.fxml.FXML;

public class HMACController extends ViewControllerFileMode {
    /**
     * JTA_dst1 :HEX result
     * JTA_dst :base64 result
     */

    @FXML private JFXComboBox JCB_charset;
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
//        super.ONClickConfirm();
//        JTA_dst.setStyle("-fx-text-fill: black");
//        JTA_dst1.setStyle("-fx-text-fill: black");
//        try{
//            String[] dst = new String[0];
//            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
//                try {
//                    dst = hash(JTA_src.getText().getBytes(JCB_charset.getValue().toString()));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                dst = hash(byteFile);
//                fileEncodeEnd();
//            }
//            JTA_dst1.setText(dst[0]);
//            JTA_dst.setText(dst[1]);
//        }catch (Exception e){
//            JTA_dst.setStyle("-fx-text-fill: red");
//            JTA_dst1.setStyle("-fx-text-fill: red");
//            JTA_dst.setText(e.getMessage());
//            JTA_dst1.setText(e.getMessage());
//        }
    }

    private void initComboBox(){
        ViewInit.comboBoxCharset(JCB_charset);


    }

    private String[] hash(byte[] message){
        return null;
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
}
