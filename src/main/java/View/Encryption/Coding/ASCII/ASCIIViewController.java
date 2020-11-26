package View.Encryption.Coding.ASCII;

import Controller.Encryption.Coding.ASCII.Coding_ASCII;
import Init.ViewInit;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
public class ASCIIViewController {
    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextField JTF_split;

    @FXML private void initialize(){
        ViewInit.textAreaErrorInfo(JTA_dst);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(ASCIIEnCode());
    }

    @FXML
    public void ONClick_JBT_deCode(){
        try{
            JTA_dst.setText(ASCIIDeCode());
        }catch (Exception e){
            JTA_dst.validate();
        }
    }

    @FXML
    public void ResetValidator(){
        JTA_dst.resetValidation();
    }

    private String ASCIIEnCode(){
        return Coding_ASCII.encode(
                JTA_src.getText(),
                JTF_split.getText(),0);
    }

    private String ASCIIDeCode(){
        return Coding_ASCII.deCode(
                JTA_src.getText(),
                JTF_split.getText());
    }
}
