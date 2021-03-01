package Main.View.Encryption.Coding.MorseCoder;

import Main.Controller.Encryption.Coding.MorseCoder.Coding_MorseCoder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class MorseCoderController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextField JTF_split;

    @FXML private void initialize(){

    }

    @FXML
    public void ONClick_JBT_enCode(){
        if(JTF_split.getText().equals("")){
            JTA_dst.setText(Coding_MorseCoder.encode(JTA_src.getText()," "));
        }else{
            JTA_dst.setText(Coding_MorseCoder.encode(JTA_src.getText(),JTF_split.getText()));
        }
    }

    @FXML
    public void ONClick_JBT_deCode(){
        if(JTF_split.getText().equals("")){
            JTA_dst.setText(Coding_MorseCoder.decode(JTA_src.getText()," "));
        }else{
            JTA_dst.setText(Coding_MorseCoder.decode(JTA_src.getText(),JTF_split.getText()));
        }
    }

}
