package View.Encryption.Modern.Authentication.NTLM;

import Controller.Encryption.Modern.Authentication.NTLM.Authentication_NTLM;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import org.apache.commons.codec.binary.Hex;

public class NTLMController {
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;

    @FXML private void initialize(){

    }

    @FXML public void NTLMEncrypt(){
        JTA_dst.setText(Hex.encodeHexString(Authentication_NTLM.encrypt(JTA_src.getText())));
    }
}
