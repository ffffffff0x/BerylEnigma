package Main.View.Modules.Encryption.Coding.HTMLCharEntity;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.HTMLCharEntity.Coding_HTMLCharEntity;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
public class HTMLCharEntityController extends ViewControllerObject {
    @FXML private JFXComboBox JCB_reference;

    @Override
    protected void initialize(){
        super.initialize();
        initComboBoxReference();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(Coding_HTMLCharEntity.encode(JTA_src.getText(),JCB_reference.getValue().toString()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try{
            JTA_dst.setText(Coding_HTMLCharEntity.decode(JTA_src.getText(),JCB_reference.getValue().toString()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
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
