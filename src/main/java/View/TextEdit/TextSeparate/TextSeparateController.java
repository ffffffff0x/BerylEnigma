package View.TextEdit.TextSeparate;

import Controller.TextEdit.TextSeparate.TextEditTextSeparate;
import Init.ViewInit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class TextSeparateController {
    @FXML
    private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXTextField JTF_quantity;
    @FXML private JFXButton JBT_confirm;

    @FXML private void initialize(){
        ViewInit.textAreaErrorInfo(JTA_dst);
        ViewInit.textAreaErrorInfo(JTF_quantity);
    }

    @FXML private void isNumber(){
        for (int i = 0; i < JTF_quantity.getText().length(); i++){
            System.out.println(JTF_quantity.getText().charAt(i));
            if (!Character.isDigit(JTF_quantity.getText().charAt(i))){
                JTF_quantity.validate();
            }
        }
    }

    @FXML
    public void ResetValidator(){
        JTA_dst.resetValidation();
        JTF_quantity.resetValidation();
    }

    @FXML
    public void ONClick_JBT_confirm(){
        try {
            JTA_dst.setText(TextEditTextSeparate.TextSeparate(JTA_src.getText(),
                    JTF_split.getText(),
                    Integer.parseInt(JTF_quantity.getText())));
        }catch (Exception e){
            JTA_dst.validate();
        }

    }
}
