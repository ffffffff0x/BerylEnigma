package ffffffff0x.beryenigma.App.View.Modules.Tools.Image.PixelReplacement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import ffffffff0x.beryenigma.App.View.Modules.Tools.Image.PixelReplacement.Engine.PixelReplacementImpl;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-20 11:29
 **/

@ViewNode(name = "PixelReplacement",folderPath = "Root/Tools/ImageTools/",fxmlName = "PixelReplacementView.fxml")
public class PixelReplacementController extends ViewController {
    private ImageView IMG_loadImg;
    private ImageView IMG_outImg;
    private File ImgFile = null;
    private final double margins = 10.0;
    private BufferedImage outBufferedImage;

    @FXML
    private JFXSpinner JSP_running;

    @FXML
    private JFXTextField JTF_key;

    @FXML
    private JFXButton JBT_loadImg;

    @FXML
    private JFXButton JBT_outImg;

    @FXML
    private AnchorPane ACP_controllerAnchorPane;

    private JFXComboBox<String> JCB_modeSelect;

    @Override
    protected void initialize() {
        LoadPopupSettingNode();
        JTF_key.setValidators(new RequiredFieldValidator(Init.getLanguage("ErrorMessage")));
        IMG_loadImg = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_LOADIMG));
        IMG_loadImg.setFitHeight(165 - margins);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);
        DragClickLoadImg();
        IMG_outImg = new ImageView();
        IMG_outImg.setPreserveRatio(true);

    }

    @Override
    public void ONClickEncode() {
        try {
            JTF_key.resetValidation();
            if (ImgFile != null) {
                JSP_running.setVisible(true);
                new Thread(() -> {
                    outBufferedImage = PixelReplacementImpl.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,true,ModeSelect());
                    Platform.runLater(() -> {
                        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
                        IMG_outImg.setImage(ViewUtils.convertToFxImage(outBufferedImage));
                        JBT_outImg.setGraphic(IMG_outImg);
                        JSP_running.setVisible(false);
                    });
                }).start();
            }
        }catch (Exception e) {
            JTF_key.validate();
        }
    }

    @Override
    public void ONClickDecode() {
        try {
            JTF_key.resetValidation();
            if (ImgFile != null) {
                JSP_running.setVisible(true);
                new Thread(() -> {
                    outBufferedImage = PixelReplacementImpl.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,false,ModeSelect());
                    Platform.runLater(() -> {
                        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
                        IMG_outImg.setImage(ViewUtils.convertToFxImage(outBufferedImage));
                        JBT_outImg.setGraphic(IMG_outImg);
                        JSP_running.setVisible(false);
                    });
                }).start();
            }
        }catch (Exception e) {
            JTF_key.validate();
        }
    }

    @FXML
    public void ONClickLoadImg() {
        ImgFile = ViewUtils.getFile();
        try {
            if (ImgFile != null) {
                if (FileUtils.isImage(ImgFile.getPath())) {
                    IMG_loadImg.setImage(new Image(new FileInputStream(ImgFile)));
                    JBT_loadImg.setGraphic(IMG_loadImg);
                }  else {
                    ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrotMessage_isImage"));
                    ImgFile = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClickOutImg() {
        try {
            if (ImgFile != null) {
                StringBuilder imgFileName = new StringBuilder(ImgFile.getName());
                imgFileName.insert(ImgFile.getName().lastIndexOf("."),"_PixelReplacement");
                File outFile = ViewUtils.fileChooser(imgFileName.toString());
                if (outFile != null) {
                    ImageIO.write(outBufferedImage,"png",outFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ModeSelect() {
        if (JCB_modeSelect.getValue().equals(Init.getLanguage("Row&Colum"))) {
            return "rc";
        }else if(JCB_modeSelect.getValue().equals(Init.getLanguage("Row"))) {
            return "r";
        }
        return null;
    }

    @Override
    protected void LoadPopupSettingNode() {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        Init.getLanguage("Row"),
                        Init.getLanguage("Row&Colum")
                );
        JCB_modeSelect = new JFXComboBox<>(options);
        JCB_modeSelect.setValue(Init.getLanguage("Row&Colum"));
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("OperateMode"), JCB_modeSelect,true));
    }

    private void DragClickLoadImg() {
        JBT_loadImg.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != JBT_loadImg) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        JBT_loadImg.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            List<File> files = dragboard.getFiles();
            if(files.size() > 0){
                try {
                    if (FileUtils.isImage(files.get(0).getPath())) {
                        ImgFile = files.get(0);
                        IMG_loadImg.setImage(new Image(new FileInputStream(ImgFile)));
                        JBT_loadImg.setGraphic(IMG_loadImg);
                    } else {
                        ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.getLanguage("Warning"), Init.getLanguage("ErrotMessage_isImage"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}