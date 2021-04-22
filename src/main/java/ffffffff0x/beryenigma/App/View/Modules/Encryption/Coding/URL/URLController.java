package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.URL;

import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.URL.Coding_URL;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXComboBox;
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
