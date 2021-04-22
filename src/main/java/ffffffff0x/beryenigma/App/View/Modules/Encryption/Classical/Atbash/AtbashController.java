package ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.Atbash;


import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Controller.Encryption.Classical.Atbash.Classical_Atbash;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;

public class AtbashController extends ViewControllerObject {

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONReleasedOrSelected() {
        super.ONReleasedOrSelected();
        try {
            JTA_dst.setText(Classical_Atbash.encode(JTA_src.getText()));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
