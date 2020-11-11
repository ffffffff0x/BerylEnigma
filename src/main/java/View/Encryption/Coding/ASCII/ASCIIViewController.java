package View.Encryption.Coding.ASCII;

import Controller.Encryption.Coding.ASCII.CodingASCII;
import Init.ViewInit;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

public class ASCIIViewController {
    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_split;

    @FXML private void initialize(){
        ViewInit.comboBoxSplit(JCB_split);
    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(ASCIIEnCode());
    }

    @FXML
    public void ONClick_JBT_deCode(){
        JTA_dst.setText(ASCIIDeCode());
    }

    private String ASCIIEnCode(){
        return CodingASCII.enCode(
                JTA_src.getText(),
                ViewUtils.comboxSplitConvert(JCB_split.getValue().toString()),
                0);
    }

    private String ASCIIDeCode(){
        return CodingASCII.deCode(
                JTA_src.getText(),
                ViewUtils.comboxSplitConvert(JCB_split.getValue().toString()));
    }
}
