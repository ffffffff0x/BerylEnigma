package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.DomainSplit;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.DomainSplit.RedTeam_DomainSplit;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileModeObject;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 14:13
 **/

public class DomainSplitController extends ViewControllerFileModeObject {
    @FXML private JFXCheckBox JCB_MultipleFile;

    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        file = null;
    }

    /**
     * 全局确定按钮事件
     */
    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try{
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                RedTeam_DomainSplit.domainSplit(JTA_src.getText());
            }else{
                RedTeam_DomainSplit.domainSplit(file);
            }
            fileEncodeEnd();
        }catch (Exception e){
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @Override
    public void getFile(){
        File file_temp = ViewUtils.getFile(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        JTA_src.setText(file_temp.toString());
        file = file_temp;
    }
}
