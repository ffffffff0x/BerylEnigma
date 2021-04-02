package Main.View.Modules.Encryption.Classical.Atbash;


import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Classical.Atbash.Classical_Atbash;
import Main.View.Viewobj.ViewControllerObject;

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
