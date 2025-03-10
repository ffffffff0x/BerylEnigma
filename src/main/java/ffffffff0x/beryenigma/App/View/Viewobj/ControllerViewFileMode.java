package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.util.List;

/**
 * @author: RyuZUSUNC
 * @create: 2025/3/10 19:02
 **/
public class ControllerViewFileMode extends ControllerView {
    public File file;
    public byte[] byteFile = null;
    public JFXToggleButton JTB_modeSelect;

    @Override
    protected void initialize() {
        super.initialize();
        setOnDrage();
        getByteFileOnDrag();
    }

    @Override
    protected void initializeUI() {
        super.initializeUI();
        setFileSelectButton();
        JTB_modeSelect.setOnMouseClicked(mouseEvent -> ONClickModeSelect());
    }

    public void ONClickModeSelect() {
        if (JTB_modeSelect.isSelected()) {
            JTB_modeSelect.setText(Init.getLanguage("FileMode"));
            JTA_src.setEditable(false);
            getFile();
        }else {
            notSelectedFile();
        }
    }

    public void fileEncodeEnd() {
        JTB_modeSelect.selectedProperty().set(false);
        JTB_modeSelect.setText(Init.getLanguage("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
        JTA_dst.setText(Init.getLanguage("Complete"));
        byteFile = null;
    }

    public void notSelectedFile() {
        JTB_modeSelect.selectedProperty().setValue(false);
        JTB_modeSelect.setText(Init.getLanguage("TextMode"));
        JTA_src.setText("");
        JTA_src.setEditable(true);
    }

    public void setFileSelectButton() {
        JTB_modeSelect = new JFXToggleButton();
        JTB_modeSelect.setText(Init.getLanguage("TextMode"));
        JTB_modeSelect.prefHeight(160);
        JTB_modeSelect.prefWidth(53);
        JTB_modeSelect.setSize(9.0);
        ACP_controllerAnchorPane.getChildren().add(JTB_modeSelect);
        JTB_modeSelect.setLayoutX(35);
        JTB_modeSelect.setLayoutY(-15);
        JTB_modeSelect.setOnMouseClicked(mouseEvent -> ONClickModeSelect());
    }

    public void getByteFileOnDrag() {
        JTA_src.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            List<File> files = dragboard.getFiles();
            if(files.size() > 0){
                JTA_src.setEditable(false);
                JTB_modeSelect.setText(Init.getLanguage("FileMode"));
                JTB_modeSelect.setSelected(true);
                file = files.get(0);
                JTA_src.setText(file.toString());
                byteFile = FileUtils.getFilebyte(file);
            }
        });
    }

//    public void getFileOnDrag() {
//        setOnDrage();
//        JTA_src.setOnDragDropped(dragEvent -> {
//            Dragboard dragboard = dragEvent.getDragboard();
//            List<File> files = dragboard.getFiles();
//            if(files.size() > 0){
//                JTA_src.setEditable(false);
//                JTB_modeSelect.setText(Init.getLanguage("FileMode"));
//                JTB_modeSelect.setSelected(true);
//                file = files.get(0);
//                JTA_src.setText(file.toString());
//            }
//        });
//    }

    private void setOnDrage() {
        JTA_src.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != JTA_src) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
    }

    public void getFile() {
        File file_temp = ViewUtils.getFile();
        if (file_temp != null){
            JTA_src.setText(file_temp.toString());
            file = file_temp;
            byteFile = FileUtils.getFilebyte(file);
        }else {
            notSelectedFile();
        }
    }

    /**
     * 输出框右键菜单
     */
    @Override
    protected void JTADSTContextMenu() {
        ViewInit.textAreaContextMenu(JTA_dst,JTA_src,JTB_modeSelect);
    }
}
