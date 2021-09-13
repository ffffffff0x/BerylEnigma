package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.CaseConvert;

import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Controller.Tools.TextEdit.CaseConvert.TextEdit_CaseConvert;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;

public class CaseConvertController extends ViewController {
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
