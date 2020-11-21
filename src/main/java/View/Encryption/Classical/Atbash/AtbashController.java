package View.Encryption.Classical.Atbash;


import Controller.Encryption.Classical.Atbash.ClassicalAtbash;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class AtbashController {

    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;

    @FXML
    public void AtbashTest() {
        JTA_dst.setText(ClassicalAtbash.encode(JTA_src.getText()));
    }
}
