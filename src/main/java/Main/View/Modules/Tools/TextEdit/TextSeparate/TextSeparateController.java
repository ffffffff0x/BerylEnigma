package Main.View.Modules.Tools.TextEdit.TextSeparate;

import Kit.Utils.ViewUtils;
import Main.Controller.Tools.TextEdit.TextSeparate.TextEdit_TextSeparate;
import Init.ViewInit;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class TextSeparateController extends ViewControllerObject {
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
