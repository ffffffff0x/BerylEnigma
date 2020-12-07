package Kit.TempView;

import Init.ViewInit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import javax.swing.*;

public class TempViewController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JTextField JTF_split;

    @FXML private void initialize(){

    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(JTA_src.getText());
    }

    @FXML
    public void ONClick_JBT_deCode(){

    }

}
