package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Unicode;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;

/**
 * @author RyuZU
 */
@ViewNode(name = "Unicode",folderPath = "Root/Encryption/Coding/",fxmlName = "UnicodeView.fxml")
public class UnicodeController extends ViewController {
    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(UnicodeImpl.encode(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(UnicodeImpl.decode(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
