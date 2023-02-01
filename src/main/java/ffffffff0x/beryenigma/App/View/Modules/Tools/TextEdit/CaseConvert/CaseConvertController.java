package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.CaseConvert;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Tools.TextEdit.CaseConvert.TextEdit_CaseConvert;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;

@ViewNode(name = "CaseConvert",folderPath = "Root/Tools/TextEdit/",fxmlName = "CaseConvertView.fxml")
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
