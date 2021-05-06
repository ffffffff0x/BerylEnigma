package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.TargetClassification;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.App.Controller.Tools.TextEdit.TargetClassification.TargetFinishingBean;
import ffffffff0x.beryenigma.App.Controller.Tools.TextEdit.TargetClassification.TextEdit_TargetFinishing;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class TargetFinishingController extends ViewControllerObject {

    @FXML private JFXCheckBox JCB_MultipleFile;
    @FXML private JFXToggleButton JTB_modeSelect;

    File file;
    TargetFinishingBean TargetClassificationBean;
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
                TargetClassificationBean = TextEdit_TargetFinishing.TargetClassification(JTA_src.getText());
            }else{
                TargetClassificationBean = TextEdit_TargetFinishing.TargetClassification(file);
            }

            if(!JCB_MultipleFile.isSelected()){
                FileUtils.outPutFile(TargetClassificationBean.getAll(),"UTF-8");
                count();
            }else{
                FileUtils.outPutFile(TargetClassificationBean.getAllmap(),"UTF-8");
                count();
            }
            FileEncodeEnd();
        }catch (Exception e){
            e.printStackTrace();
            JTA_dst.validate();
        }
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
        String outCount = "IP : " + TargetClassificationBean.getNumIP() + "\nURL : " + TargetClassificationBean.getNumUrl() + "\nIP:PORT : " + TargetClassificationBean.getNumIPPort();
        JTA_dst.setText(outCount);
    }

    public void FileEncodeEnd(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
    }
}
