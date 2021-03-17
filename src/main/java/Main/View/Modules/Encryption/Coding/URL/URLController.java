package Main.View.Modules.Encryption.Coding.URL;

import Main.Controller.Encryption.Coding.URL.Coding_URL;
import Init.ViewInit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class URLController {
    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;

    @FXML private void initialize(){
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        try {
            JTA_dst.setText(Coding_URL.encode(JTA_src.getText(),JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @FXML
    public void ONClick_JBT_deCode(){
        try {
            JTA_dst.setText(Coding_URL.decode(JTA_src.getText(),JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @FXML
    public void ResetValidator(){
        JTA_dst.resetValidation();
    }
}
