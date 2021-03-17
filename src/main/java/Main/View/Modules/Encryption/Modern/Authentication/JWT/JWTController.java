package Main.View.Modules.Encryption.Modern.Authentication.JWT;

import Main.Controller.Encryption.Modern.Authentication.JWT.Authentication_JWT;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JWTController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_header;
    @FXML private JFXTextArea JTA_payload;
    @FXML private JFXTextArea JTA_vs;
    @FXML private JFXTextArea JTA_token;

    @FXML private void initialize(){

    }

    @FXML
    public void ONClick_JBT_enCode() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        JTA_token.setText(Authentication_JWT.encode(JTA_header.getText(),JTA_payload.getText(),JTA_vs.getText()));
    }

    @FXML
    public void ONClick_JBT_deCode(){
        String[] result = Authentication_JWT.decode(JTA_token.getText());
        JTA_header.setText(result[0]);
        JTA_payload.setText(result[1]);
        JTA_vs.setText(result[2]);
    }

}
