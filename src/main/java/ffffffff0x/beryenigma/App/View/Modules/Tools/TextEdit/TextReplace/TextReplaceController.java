package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.TextReplace;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Tools.TextEdit.TextReplace.TextEdit_TextReplace;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

@ViewNode(name = "TextReplace",folderPath = "Root/Tools/TextEdit/",fxmlName = "TextReplaceView.fxml")
public class TextReplaceController extends ViewController {
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
