package Main.View.Modules.Encryption.Coding.BaseConversion;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.BaseConversion.Coding_BaseConversion;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class BaseConversionController extends ViewControllerObject {

    @FXML private JFXTextField JTF_split;
    @FXML private JFXComboBox<Integer> JCB_srcBase;
    @FXML private JFXComboBox<Integer> JCB_dstBase;


    @Override
    protected void initialize(){
        super.initialize();
        initComboBox();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            JTA_dst.setText(Coding_BaseConversion.conversion(JTA_src.getText(),
                    Integer.parseInt(JCB_srcBase.getValue().toString()),
                    Integer.parseInt(JCB_dstBase.getValue().toString()),
                    JTF_split.getText()));
        }catch (NumberFormatException e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void initComboBox(){
        JCB_srcBase.getItems().add(2);
        JCB_srcBase.getItems().add(8);
        JCB_srcBase.getItems().add(10);
        JCB_srcBase.getItems().add(16);
        for (int i = 3; i < 8; i++) { JCB_srcBase.getItems().add(i); }
        for (int i = 9; i < 10; i++) { JCB_srcBase.getItems().add(i); }
        for (int i = 11; i < 16; i++) { JCB_srcBase.getItems().add(i); }
        for (int i = 17; i < 31; i++) { JCB_srcBase.getItems().add(i); }
        JCB_srcBase.setValue(10);
        JCB_srcBase.setVisibleRowCount(6);

        JCB_dstBase.getItems().add(2);
        JCB_dstBase.getItems().add(8);
        JCB_dstBase.getItems().add(10);
        JCB_dstBase.getItems().add(16);
        for (int i = 3; i < 8; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 9; i < 10; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 11; i < 16; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 17; i < 31; i++) { JCB_dstBase.getItems().add(i); }
        JCB_dstBase.setValue(16);
        JCB_dstBase.setVisibleRowCount(6);
    }

}
