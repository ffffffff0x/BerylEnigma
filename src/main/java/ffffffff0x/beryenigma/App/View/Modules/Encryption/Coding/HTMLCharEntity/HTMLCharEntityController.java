package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.HTMLCharEntity;

import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Encryption.Coding.HTMLCharEntity.Coding_HTMLCharEntity;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
public class HTMLCharEntityController extends ViewController {
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
            e.printStackTrace();
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
                "CER: &[char];",
                "NCR: &#[dec];",
        "NCR: &#x[hex];"
        );
        JCB_reference.setValue("CER: &[char];");
        JCB_reference.setVisibleRowCount(3);
    }

}
