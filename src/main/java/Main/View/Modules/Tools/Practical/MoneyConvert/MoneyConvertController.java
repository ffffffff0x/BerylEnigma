package Main.View.Modules.Tools.Practical.MoneyConvert;

import Main.Controller.Tools.Practical.MoneyConvert.Practical_MoneyConvert;
import Init.Init;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.math.BigDecimal;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-01 14:33
 **/

public class MoneyConvertController {
    private final BigDecimal MAX_VALUE = new BigDecimal("9999999999999999.99");

    @FXML private JFXButton JBT_confirm;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_language;

    @FXML private void initialize(){
        initComboBox();
        JTA_src.setPromptText("MAX:9999999999999999.99");
    }

    @FXML
    public void ONClick_JBT_confirm(){
        try {
            if(MAX_VALUE.compareTo(new BigDecimal(JTA_src.getText())) ==-1 ){
                JTA_src.setText("");
                JTA_dst.setText(Init.languageResourceBundle.getString("ErrorMessage"));
            }else{
                JTA_dst.setText(Practical_MoneyConvert.convert(new BigDecimal(JTA_src.getText())));
            }
        }catch (NumberFormatException e){
            JTA_dst.setText(Init.languageResourceBundle.getString("ErrorMessage_isNum"));
        }
    }

    private void initComboBox(){
        JCB_language.getItems().addAll(Init.languageResourceBundle.getString("Chinese"));
        JCB_language.setValue(Init.languageResourceBundle.getString("Chinese"));
        JCB_language.setVisibleRowCount(6);
    }
}
