package Main.View.Modules.Encryption.Coding.MorseCoder;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.MorseCoder.Coding_MorseCoder;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class MorseCoderController extends ViewControllerObject {

    @FXML private JFXTextField JTF_split;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            if(JTF_split.getText().equals("")){
                JTA_dst.setText(Coding_MorseCoder.encode(JTA_src.getText()," "));
            }else{
                JTA_dst.setText(Coding_MorseCoder.encode(JTA_src.getText(),JTF_split.getText()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            if(JTF_split.getText().equals("")){
                JTA_dst.setText(Coding_MorseCoder.decode(JTA_src.getText()," "));
            }else{
                JTA_dst.setText(Coding_MorseCoder.decode(JTA_src.getText(),JTF_split.getText()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
