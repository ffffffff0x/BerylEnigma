package Main.View.Encryption.Classical.RailFence;


import Init.Init;
import Main.Controller.Encryption.Classical.RailFence.Classical_RailFence;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.SimpleTimeZone;

public class RailFenceController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextField JTF_quantity;

    @FXML
    public void ONClick_JBT_enCode(){
        try{
            JTA_dst.setText(Classical_RailFence.encode(JTA_src.getText(),Integer.valueOf(JTF_quantity.getText())));
        }catch (Exception e){
            JTA_dst.setText(Init.languageResourceBundle.getString("ErrorMessage"));
        }

    }

    @FXML
    public void ONClick_JBT_deCode(){
        try{
            JTA_dst.setText(Classical_RailFence.decode(JTA_src.getText(),Integer.valueOf(JTF_quantity.getText())));
        }catch (Exception e){
            JTA_dst.setText(Init.languageResourceBundle.getString("ErrorMessage"));
        }
    }
}
