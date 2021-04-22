package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Unicode;

import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Unicode.Coding_Unicode;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;

/**
 * @author RyuZU
 */
public class UnicodeController extends ViewControllerObject {
    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(Coding_Unicode.encode(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(Coding_Unicode.decode(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
