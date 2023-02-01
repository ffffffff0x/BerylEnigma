package ffffffff0x.beryenigma.App.View.Modules.Tools.Practical.MoneyConvert;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Tools.Practical.MoneyConvert.Practical_MoneyConvert;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;

import java.math.BigDecimal;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-01 14:33
 **/

@ViewNode(name = "MoneyConvert",folderPath = "Root/Tools/Practical/",fxmlName = "MoneyConvertView.fxml")
public class MoneyConvertController extends ViewController {
    private final BigDecimal MAX_VALUE = new BigDecimal("9999999999999999.99");

    @FXML private JFXComboBox JCB_language;

    @Override
    protected void initialize(){
        super.initialize();
        initComboBox();
        JTA_src.setPromptText("MAX:9999999999999999.99");
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            if(MAX_VALUE.compareTo(new BigDecimal(JTA_src.getText())) ==-1 ){
                JTA_src.setText("");
                JTA_dst.setText(Init.getLanguage("ErrorMessage"));
            }else{
                JTA_dst.setText(Practical_MoneyConvert.convert(new BigDecimal(JTA_src.getText())));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    private void initComboBox(){
        JCB_language.getItems().addAll(Init.getLanguage("Chinese"));
        JCB_language.setValue(Init.getLanguage("Chinese"));
        JCB_language.setVisibleRowCount(6);
    }
}
