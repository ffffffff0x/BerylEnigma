package ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.Vigenere;


import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

@ViewNode(name = "Vigenere",folderPath = "Root/Encryption/Classical/",fxmlName = "VigenereView.fxml")
public class VigenereController extends ViewController {

    @FXML private JFXTextArea JTA_vigenereKey;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(VigenereImpl.encrypt(JTA_src.getText(),JTA_vigenereKey.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(VigenereImpl.decrypt(JTA_src.getText(),JTA_vigenereKey.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
