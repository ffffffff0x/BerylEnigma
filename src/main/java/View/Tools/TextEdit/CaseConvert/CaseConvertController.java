package View.Tools.TextEdit.CaseConvert;

import Controller.Tools.TextEdit.CaseConvert.TextEdit_CaseConvert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class CaseConvertController {
    @FXML
    private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;

    @FXML private void initialize(){

    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(TextEdit_CaseConvert.Uppercase(JTA_src.getText()));
    }

    @FXML
    public void ONClick_JBT_deCode(){
        JTA_dst.setText(TextEdit_CaseConvert.Lowercase(JTA_src.getText()));
    }
}
