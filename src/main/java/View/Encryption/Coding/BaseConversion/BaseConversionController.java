package View.Encryption.Coding.BaseConversion;

import Controller.Encryption.Coding.BaseConversion.Coding_BaseConversion;
import Init.ViewInit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class BaseConversionController {

    @FXML private JFXButton JBT_confirm;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXTextField JTF_split;
    @FXML private JFXComboBox JCB_srcBase;
    @FXML private JFXComboBox JCB_dstBase;

    @FXML private void initialize(){
        initComboBox();
    }

    @FXML
    public void ONClick_JBT_confirm(){
        JTA_dst.setText(Coding_BaseConversion.conversion(JTA_src.getText(),
                Integer.parseInt(JCB_srcBase.getValue().toString()),
                Integer.parseInt(JCB_dstBase.getValue().toString()),
                JTF_split.getText()));
    }

    @FXML
    public void initComboBox(){
        for (int i = 2; i < 31; i++) {JCB_srcBase.getItems().add(i);}
        JCB_srcBase.setValue(10);
        JCB_srcBase.setVisibleRowCount(6);

        for (int i = 2; i < 31; i++) {JCB_dstBase.getItems().add(i);}
        JCB_dstBase.setValue(16);
        JCB_dstBase.setVisibleRowCount(6);
    }

}
