package Main.View.Encryption.Classical.Atbash;


import Main.Controller.Encryption.Classical.Atbash.Classical_Atbash;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class AtbashController {

    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;

    @FXML
    public void AtbashEncode() {
        JTA_dst.setText(Classical_Atbash.encode(JTA_src.getText()));
    }
}
