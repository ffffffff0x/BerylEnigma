package Main.View.Modules.Encryption.Coding.ASCII;

import Kit.Utils.ViewUtils;
import Main.Controller.Encryption.Coding.ASCII.Coding_ASCII;
import Init.ViewInit;
import Main.View.Viewobj.ViewControllerObject;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
public class ASCIIViewController extends ViewControllerObject {
    @FXML private JFXTextField JTF_split;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode(){
        super.ONClickEncode();
        try {
            JTA_dst.setText(ASCIIEnCode());
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }

    }

    @Override
    public void ONClickDecode(){
        super.ONClickDecode();
        try{
            JTA_dst.setText(ASCIIDeCode());
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }


    private String ASCIIEnCode(){
        return Coding_ASCII.encode(
                JTA_src.getText(),
                JTF_split.getText(),0);
    }

    private String ASCIIDeCode(){
        return Coding_ASCII.deCode(
                JTA_src.getText(),
                JTF_split.getText());
    }
}
