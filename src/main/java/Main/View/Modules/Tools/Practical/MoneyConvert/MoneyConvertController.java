package Main.View.Modules.Tools.Practical.MoneyConvert;

import Kit.Utils.ViewUtils;
import Main.Controller.Tools.Practical.MoneyConvert.Practical_MoneyConvert;
import Init.Init;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.math.BigDecimal;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-01 14:33
 **/

public class MoneyConvertController extends ViewControllerObject {
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
                JTA_dst.setText(Init.languageResourceBundle.getString("ErrorMessage"));
            }else{
                JTA_dst.setText(Practical_MoneyConvert.convert(new BigDecimal(JTA_src.getText())));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    private void initComboBox(){
        JCB_language.getItems().addAll(Init.languageResourceBundle.getString("Chinese"));
        JCB_language.setValue(Init.languageResourceBundle.getString("Chinese"));
        JCB_language.setVisibleRowCount(6);
    }
}
