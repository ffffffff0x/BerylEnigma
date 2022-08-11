package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.DomainSplit;

import com.jfoenix.controls.JFXCheckBox;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.DomainSplit.RedTeam_DomainSplit;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 14:13
 **/

public class DomainSplitController extends ViewControllerFileMode {
    private String allResult;
    private Map<String,String> multipleResult = new HashMap<>();
    private Map<Integer,HashSet<String>> originalResult;

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
            if(JTB_modeSelect.getText().equals(Init.getLanguage("TextMode"))){
                originalResult = RedTeam_DomainSplit.domainSplit(JTA_src.getText());
            }else{
                originalResult = RedTeam_DomainSplit.domainSplit(file);
            }

            //将处理完的数据格式处理为可以输出文本文件的格式
            paraPocessing(originalResult,JCB_MultipleFile.isSelected());

            //输出单个或多个文件
            if(JCB_MultipleFile.isSelected()){
                FileUtils.outPutFile(multipleResult,"UTF-8");
            }else{
                FileUtils.outPutFile(allResult,"UTF-8");
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
        if (file_temp != null){
            JTA_src.setText(file_temp.toString());
            file = file_temp;
            byteFile = FileUtils.getFilebyte(file);
        }else {
            notSelectedFile();
        }
    }

    private void paraPocessing(Map<Integer, HashSet<String>> result,boolean multipleFile){
        int k = 0;
        if(!result.get(-1).isEmpty()){
            k = -1;
        }

        if(multipleFile){
            for (int i = k; i < result.size()-1; i++) {
                StringBuilder sb =new StringBuilder();
                for (String a:result.get(i)) {
                    sb.append(a).append("\n");
                }
                sb.append("\n");
                if (i==-1){
                    multipleResult.put("fileURLs.txt",sb.toString());
                }else {
                    multipleResult.put("level-" + i +".txt",sb.toString());
                }
            }
        }else {
            StringBuilder sb =new StringBuilder();
            for (int i = k; i < result.size()-1; i++) {
                if(i!=-1){
                    sb.append("level ").append(i).append("\n");
                }else{
                    sb.append("fileURLs\n");
                }
                for (String a:result.get(i)) {
                    sb.append(a).append("\n");
                }
                sb.append("\n");
            }
            allResult = sb.toString();
        }
    }


}
