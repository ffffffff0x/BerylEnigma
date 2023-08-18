package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseConversion;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

@ViewNode(name = "BaseConversion",folderPath = "Root/Encryption/Coding/",fxmlName = "BaseConversionView.fxml")
public class BaseConversionController extends ViewController {

    @FXML private JFXTextField JTF_split;
    @FXML private JFXCheckBox JCB_SignBit;
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
            if(JCB_SignBit.isSelected()&&JTA_src.getText().split("")[0].equals("1")){
                JTA_dst.setText("-"+ BaseConversionImpl.conversion((JTA_src.getText().subSequence(1,JTA_src.getText().length())).toString(),
                        Integer.parseInt(JCB_srcBase.getValue().toString()),
                        Integer.parseInt(JCB_dstBase.getValue().toString()),
                        ViewUtils.getSplit(JTF_split)));
            }else{
                JTA_dst.setText(BaseConversionImpl.conversion(JTA_src.getText(),
                        Integer.parseInt(JCB_srcBase.getValue().toString()),
                        Integer.parseInt(JCB_dstBase.getValue().toString()),
                        ViewUtils.getSplit(JTF_split)));
            }
        }catch (NumberFormatException e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    // 判断是否是二进制输入，展示符号位选择框
    @FXML
    public void ONCheckComboBox(){
        JCB_SignBit.setVisible(JCB_srcBase.getValue().toString().equals("2"));
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
        for (int i = 17; i < 37; i++) { JCB_srcBase.getItems().add(i); }
        JCB_srcBase.setValue(10);
        JCB_srcBase.setVisibleRowCount(6);

        JCB_dstBase.getItems().add(2);
        JCB_dstBase.getItems().add(8);
        JCB_dstBase.getItems().add(10);
        JCB_dstBase.getItems().add(16);
        for (int i = 3; i < 8; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 9; i < 10; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 11; i < 16; i++) { JCB_dstBase.getItems().add(i); }
        for (int i = 17; i < 37; i++) { JCB_dstBase.getItems().add(i); }
        JCB_dstBase.setValue(16);
        JCB_dstBase.setVisibleRowCount(6);
    }
}
