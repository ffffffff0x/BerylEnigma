package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Authentication.NTLM;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import org.apache.commons.codec.binary.Hex;

@ViewNode(name = "NTLM_Hash",folderPath = "Root/Encryption/Modern/Authentication/",fxmlName = "NTLMView.fxml")
public class NTLMController extends ViewController {
    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            JTA_dst.setText(Hex.encodeHexString(NTLMImpl.encrypt(JTA_src.getText())));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
