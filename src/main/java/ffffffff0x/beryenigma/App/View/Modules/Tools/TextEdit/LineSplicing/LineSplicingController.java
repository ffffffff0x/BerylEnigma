package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.LineSplicing;

import ffffffff0x.beryenigma.App.Controller.Tools.TextEdit.LineSplicing.TextEdit_LineSplicing;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class LineSplicingController extends ViewController {
    @FXML private JFXTextField JTF_split;
    @FXML private JFXButton JBT_loadFile1;
    @FXML private JFXButton JBT_loadFile2;

    private File file1;
    private File file2;
    private ArrayList<String> fileLines1;
    private ArrayList<String> fileLines2;

    @Override
    protected void initialize(){
        super.initialize();
        file1 = null;
        file2 = null;
        fileLines1 = null;
        fileLines2 = null;
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        try {
            FileUtils.outPutFile(TextEdit_LineSplicing.LineSplicing(fileLines1,fileLines2,JTF_split.getText()),"UTF-8");
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void ONClick_JBT_loadFile1(){
        file1 = ViewUtils.getFile();
        if(file1!=null){
            fileLines1 = FileUtils.getFileLines(file1);
            if(fileNullCheck(fileLines1)){
                JTA_src1.setText(file1.getPath());
                preview();
            }
        }
    }

    @FXML
    public void ONClick_JBT_loadFile2(){
        file2 = ViewUtils.getFile();
        if(file2!=null){
            fileLines2 = FileUtils.getFileLines(file2);
            if(fileNullCheck(fileLines2)){
                JTA_src2.setText(file2.getPath());
                preview();
            }
        }
    }

    @FXML
    public void ONKeyPressed_JTF_split(){
        preview();
    }

    private void preview(){
        try {
            int lineNumber = lineNumberCheck();
            if(lineNumber!=0){
                StringBuilder previwstring = new StringBuilder();
                if(JTA_src1.getText().length()!=0 && JTA_src2.getText().length()!=0){
                    for (int i = 0; i < lineNumber; i++) {
                        previwstring.append(fileLines1.get(i) + JTF_split.getText() + fileLines2.get(i) + "\n");
                    }
                }else if(JTA_src1.getText().length()!=0 && JTA_src2.getText().length()==0){
                    for (int i = 0; i < lineNumber; i++) {
                        previwstring.append(fileLines1.get(i) + JTF_split.getText() + "\n");
                    }
                }else if(JTA_src1.getText().length()==0 && JTA_src2.getText().length()!=0){
                    for (int i = 0; i < lineNumber; i++) {
                        previwstring.append(JTF_split.getText() + fileLines2.get(i) + "\n");
                    }
                }
                previwstring.append("..."+JTF_split.getText()+"...");
                JTA_dst.setText(previwstring.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }///预览框设置

    private int lineNumberCheck(){
        if (fileLines1 == null){
            if(fileLines2!=null){
                if(fileLines2.size()<5){
                    return 1;
                }else {
                    return 5;
                }
            }else {
                return 1;
            }
        }else if(fileLines2 == null){
            if(fileLines1.size()<5){
                return 1;
            }else {
                return 5;
            }
        }else {
            if(fileLines1.size()<5||fileLines2.size()<5){
                return 1;
            }else {
                return 5;
            }
        }
    }//检测文件行数

    private Boolean fileNullCheck(ArrayList<String> fileLines){
        if(fileLines.size()==0){
            ViewUtils.alertPane((Stage) JTA_dst.getScene().getWindow(), Init.languageResourceBundle.getString("Warning"),Init.languageResourceBundle.getString("ErrorMessage_notNull"));
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void JTADSTContextMenu() {}
}
