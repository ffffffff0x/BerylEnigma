package Main.View.Encryption.Coding.HTMLCharEntity;

import Main.Controller.Encryption.Coding.HTMLCharEntity.Coding_HTMLCharEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
public class HTMLCharEntityController {
    @FXML
    private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_reference;

    @FXML private void initialize(){
        initComboBoxReference();
    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(Coding_HTMLCharEntity.encode(JTA_src.getText(),JCB_reference.getValue().toString()));
    }

    @FXML
    public void ONClick_JBT_deCode(){
        JTA_dst.setText(Coding_HTMLCharEntity.decode(JTA_src.getText(),JCB_reference.getValue().toString()));
    }

    public void initComboBoxReference(){
        JCB_reference.getItems().addAll(
                "NCR: &#[dec];",
        "NCR: &#x[hex];",
        "CER: &[char];"
        );
        JCB_reference.setValue("NCR: &#[dec];");
        JCB_reference.setVisibleRowCount(3);
    }

}
