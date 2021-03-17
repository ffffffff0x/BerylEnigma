package Main.View.Modules.Encryption.Coding.URL;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.URL.Coding_URL;
import Init.ViewInit;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class URLController extends ViewControllerObject {
    @FXML
    private JFXComboBox JCB_charset;

    @Override
    protected void initialize(){
        super.initialize();
        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(Coding_URL.encode(JTA_src.getText(),JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(Coding_URL.decode(JTA_src.getText(),JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
