package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.TextSeparate;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Tools.TextEdit.TextSeparate.TextEdit_TextSeparate;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

@ViewNode(name = "TextSeparate",folderPath = "Root/Tools/TextModify/",fxmlName = "TextSeparateView.fxml")
public class TextSeparateController extends ViewController {
    @FXML private JFXTextField JTF_split;
    @FXML private JFXTextField JTF_quantity;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            JTA_dst.setText(TextEdit_TextSeparate.TextSeparate(JTA_src.getText(),
                    JTF_split.getText(),
                    Integer.parseInt(JTF_quantity.getText())));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    private void isNumber(){
        for (int i = 0; i < JTF_quantity.getText().length(); i++){
            //System.out.println(JTF_quantity.getText().charAt(i));
            if (!Character.isDigit(JTF_quantity.getText().charAt(i))){
                JTF_quantity.validate();
            }
        }
    }
}
