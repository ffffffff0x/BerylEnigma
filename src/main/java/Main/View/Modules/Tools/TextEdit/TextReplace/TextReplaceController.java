package Main.View.Modules.Tools.TextEdit.TextReplace;

import Kit.Utils.ViewUtils;
import Main.Controller.Tools.TextEdit.TextReplace.TextEdit_TextReplace;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class TextReplaceController extends ViewControllerObject {
    @FXML private JFXTextArea JTA_oldString;
    @FXML private JFXTextArea JTA_newString;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            JTA_dst.setText(TextEdit_TextReplace.TextReplace(JTA_src.getText(),JTA_oldString.getText(),JTA_newString.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
