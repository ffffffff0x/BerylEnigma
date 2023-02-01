package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.ASCII;

import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Encryption.Coding.ASCII.Coding_ASCII;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

/**
 * @author RyuZU
 */
@ViewNode(name = "ASCII",folderPath = "Root/Encryption/Coding/",fxmlName = "ASCIIView.fxml")
public class ASCIIViewController extends ViewController {
    @FXML
    private JFXTextField JTF_split;

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
                ViewUtils.getSplit(JTF_split),0);
    }

    private String ASCIIDeCode(){
        return Coding_ASCII.deCode(
                JTA_src.getText(),
                ViewUtils.getSplit(JTF_split));
    }
}
