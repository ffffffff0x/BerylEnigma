package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.Init.Init;
import javafx.fxml.FXML;

import java.io.File;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-20 11:29
 **/

public class ViewControllerFileModeObject extends ViewControllerObject{
    public File file;
    @FXML public JFXToggleButton JTB_modeSelect;

    @FXML
    public void ONClickModeSelect(){
        if (JTB_modeSelect.isSelected()){
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("FileMode"));
            JTA_src.setEditable(false);
            try {
                getFile();
            }catch (Exception e){
                e.printStackTrace();
                notSelectedFile();
            }
        }else {
            notSelectedFile();
        }
    }

    public void fileEncodeEnd(){
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.languageResourceBundle.getString("Complete"));
    }

    public void notSelectedFile(){
        JTB_modeSelect.selectedProperty().setValue(false);
        JTB_modeSelect.setText(Init.languageResourceBundle.getString("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
    }

    public void getFile(){
//        File file_temp = ViewUtils.getFile(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
//        JTA_src.setText(file_temp.toString());
//        file = file_temp;
    }
}
