package Main.View.Modules.Encryption.Classical.RailFence;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Classical.RailFence.Classical_RailFence;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class RailFenceController extends ViewControllerObject {

    @FXML private JFXTextField JTF_quantity;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try{
            JTA_dst.setText(Classical_RailFence.encode(JTA_src.getText(),Integer.valueOf(JTF_quantity.getText())));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try{
            JTA_dst.setText(Classical_RailFence.decode(JTA_src.getText(),Integer.valueOf(JTF_quantity.getText())));
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
