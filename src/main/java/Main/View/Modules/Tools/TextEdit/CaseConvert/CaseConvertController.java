package Main.View.Modules.Tools.TextEdit.CaseConvert;

import Kit.Utils.ViewUtils;
import Main.Controller.Tools.TextEdit.CaseConvert.TextEdit_CaseConvert;
import Main.View.Viewobj.ViewControllerObject;

public class CaseConvertController extends ViewControllerObject {
    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            JTA_dst.setText(TextEdit_CaseConvert.Uppercase(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(TextEdit_CaseConvert.Lowercase(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
