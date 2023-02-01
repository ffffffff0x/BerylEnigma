package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.TargetClassification;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.TargetClassification.TargetFinishingBean;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.TargetClassification.RedTeam_TargetFinishing;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import javafx.fxml.FXML;

@ViewNode(name = "TargetFinishing",folderPath = "Root/Tools/RedTeam/",fxmlName = "TargetFinishingView.fxml")
public class TargetFinishingController extends ViewControllerFileMode {

    @FXML private JFXCheckBox JCB_MultipleFile;

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
            if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                TargetClassificationBean = RedTeam_TargetFinishing.TargetClassification(JTA_src.getText());
            }else{
                TargetClassificationBean = RedTeam_TargetFinishing.TargetClassification(file);
            }

            if(!JCB_MultipleFile.isSelected()){
                FileUtils.outPutFile(TargetClassificationBean.getAll(),"UTF-8");
            }else{
                FileUtils.outPutFile(TargetClassificationBean.getAllmap(),"UTF-8");
            }
            count();
            FileEncodeEnd();
        }catch (Exception e){
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    private void count(){
        String outCount = Init.getLanguage("AfterDe-duplication") + " : " + TargetClassificationBean.getNumduplication() + "\nIP : " + TargetClassificationBean.getNumIP() + "\nURL&Other : " + TargetClassificationBean.getNumUrl() + "\nIP:PORT : " + TargetClassificationBean.getNumIPPort();
        JTA_dst.setText(outCount);
    }

    public void FileEncodeEnd(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.getLanguage("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
    }
}
