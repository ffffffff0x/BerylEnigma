package Main.View.Modules.Encryption.Classical.Vigenere;


import Main.Controller.Encryption.Classical.Vigenere.Classical_Vigenere;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class VigenereController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextArea JTA_key;

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(Classical_Vigenere.encrypt(JTA_src.getText(),JTA_key.getText()));
    }

    @FXML
    public void ONClick_JBT_deCode(){
        JTA_dst.setText(Classical_Vigenere.decrypt(JTA_src.getText(),JTA_key.getText()));
    }
}
