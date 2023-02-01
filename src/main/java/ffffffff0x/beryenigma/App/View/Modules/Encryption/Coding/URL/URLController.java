package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.URL;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Encryption.Coding.URL.Coding_URL;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
@ViewNode(name = "URL",folderPath = "Root/Encryption/Coding/",fxmlName = "URLView.fxml")
public class URLController extends ViewController {
    @FXML
    private JFXComboBox JCB_charset;
    @FXML
    private JFXCheckBox JCB_encodeAll;

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
            if (JCB_encodeAll.isSelected()) {
                JTA_dst.setText(Coding_URL.encodeAll(JTA_src.getText(),JCB_charset.getValue().toString()));
            }else {
                JTA_dst.setText(Coding_URL.encode(JTA_src.getText(),JCB_charset.getValue().toString()));
            }
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
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }
}
