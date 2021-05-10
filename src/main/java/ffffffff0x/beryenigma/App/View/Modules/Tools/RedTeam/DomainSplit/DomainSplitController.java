package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.DomainSplit;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 14:13
 **/

public class DomainSplitController extends ViewControllerObject {
    @FXML JFXToggleButton JTB_modeSelect;
    @FXML private JFXCheckBox JCB_MultipleFile;

    File file;

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
    }

    @FXML
    public void ONClickModeSelect(){
        if (JTB_modeSelect.isSelected()){
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("FileMode"));
            JTA_src.setEditable(false);
            try {
                File file_temp = ViewUtils.getFile(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
                JTA_src.setText(file_temp.toString());
                file = file_temp;
            }catch (Exception e){
                e.printStackTrace();
                JTB_modeSelect.selectedProperty().setValue(false);
                JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
                JTA_src.setText("");
                JTA_src.setEditable(true);
            }
        }else {
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
            JTA_src.setText("");
            JTA_src.setEditable(true);
        }
    }

    private void count(){

    }

    public void FileEncodeEnd(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
    }
}
