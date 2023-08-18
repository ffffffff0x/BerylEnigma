package ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.ROT;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;

@ViewNode(name = "ROT",folderPath = "Root/Encryption/Classical/",fxmlName = "ROTView.fxml")
public class ROTController extends ViewController {

    @FXML private JFXComboBox JCB_rotNum;

    @FXML private JFXCheckBox JCB_encryptAll;

    @Override
    protected void initialize() {
        super.initialize();
        initComboxSelect();
    }

    @Override
    public void ONReleasedOrSelected() {
        super.ONReleasedOrSelected();
        try {
            if (JCB_encryptAll.isSelected()) {
                JTA_dst.setText(ROTImpl.encodeAll(JTA_src.getText(), JCB_rotNum.getValue().toString()));
            }else {
                JTA_dst.setText(ROTImpl.encode(JTA_src.getText(), JCB_rotNum.getValue().toString()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void ONChangeSelectJCBEncryptAll() {
        try {
            if (JCB_encryptAll.isSelected()) {
                JTA_dst.setText(ROTImpl.encodeAll(JTA_src.getText(), JCB_rotNum.getValue().toString()));
            }else {
                JTA_dst.setText(ROTImpl.encode(JTA_src.getText(), JCB_rotNum.getValue().toString()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    public void initComboxSelect(){
        JCB_rotNum.getItems().addAll("ROT01",
                "ROT02","ROT03","ROT04","ROT05","ROT06","ROT07","ROT08","ROT09","ROT10","ROT11","ROT12","ROT13","ROT14","ROT15","ROT16","ROT17","ROT18","ROT19","ROT20","ROT21",
                "ROT22","ROT23","ROT24","ROT25"
        );
        JCB_rotNum.setValue("ROT13");
        JCB_rotNum.setVisibleRowCount(6);
    }
}

