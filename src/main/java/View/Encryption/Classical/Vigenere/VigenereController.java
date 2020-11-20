package View.Encryption.Classical.Vigenere;


import Controller.Encryption.Classical.Vigenere.ClassicalVigenere;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class VigenereController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextArea JTA_dwt;

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(ClassicalVigenere.encrypt(JTA_src.getText(),JTA_dwt.getText()));
    }

    @FXML
    public void ONClick_JBT_deCode(){
        JTA_dst.setText(ClassicalVigenere.decrypt(JTA_src.getText(),JTA_dwt.getText()));
    }
}
