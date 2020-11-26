package View.TextEdit.TextReplace;

import Controller.TextEdit.TextReplace.TextEdit_TextReplace;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class TextReplaceController {
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextArea JTA_oldString;
    @FXML private JFXTextArea JTA_newString;
    @FXML private JFXButton JBT_confirm;

    @FXML private void initialize(){

    }

    @FXML
    public void ONClick_JBT_confirm(){
        JTA_dst.setText(TextEdit_TextReplace.TextReplace(JTA_src.getText(),JTA_oldString.getText(),JTA_newString.getText()));
    }

}
