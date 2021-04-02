package Main.View.Modules.Encryption.Coding.Unicode;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.Unicode.Coding_Unicode;
import Main.View.Viewobj.ViewControllerObject;

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
