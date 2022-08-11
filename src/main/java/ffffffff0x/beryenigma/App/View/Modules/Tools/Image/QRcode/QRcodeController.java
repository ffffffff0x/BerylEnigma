package ffffffff0x.beryenigma.App.View.Modules.Tools.Image.QRcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.App.Controller.Tools.Image.PixelReplacement.Image_PixelReplacement;
import ffffffff0x.beryenigma.App.Controller.Tools.Image.QRcode.Image_QRcode;
import ffffffff0x.beryenigma.App.Controller.Tools.Image.QRcode.QRcodeParameters;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-11 13:17
 **/

public class QRcodeController extends ViewController {
    private ImageView IMG_loadImg;
    private ImageView IMG_outImg;
    private File ImgFile = null;
    private BufferedImage outBufferedImage;

    private JFXColorPicker JCP_QRColor;
    private JFXColorPicker JCP_BKColor;
    private JFXTextField JTF_outImgWidth;
    private JFXTextField JTF_outImgHeight;

    private QRcodeParameters qRcodeParameters = new QRcodeParameters();

    @FXML
    private JFXToggleButton JTB_modeSelect;

    @FXML
    private JFXComboBox JCB_charset;

    @FXML
    private JFXComboBox<String> JCB_barcodeFormat;

    @FXML
    private JFXSpinner JSP_running;

    @FXML
    private JFXButton JBT_loadImg;

    @FXML
    private JFXButton JBT_outImg;

    @Override
    protected void initialize() {
        super.initialize();
        IMG_loadImg = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_LOADQRIMG));
        IMG_loadImg.setFitHeight(165);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);
        IMG_outImg = new ImageView(ViewUtils.getImage(ImageListInit.ICON_JBT_SAVEIMG));
        IMG_outImg.setFitHeight(165);
        IMG_outImg.setPreserveRatio(true);
        JBT_outImg.setGraphic(IMG_outImg);
        DragClickLoadImg();
        initViews();
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
        if (JTB_modeSelect.isSelected()) {

        } else {
            if (ImgFile != null) {
                JSP_running.setVisible(true);
                new Thread(() -> {
                    try {
                        JTA_dst.setText(Image_QRcode.decode(ImgFile.getPath(), JCB_charset.getValue().toString()));
                    } catch (IOException e) {
                        Platform.runLater(() -> {
                            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.languageResourceBundle.getString("Warning"), e.getMessage());
                        });
                    } catch (NotFoundException e) {
                        Platform.runLater(() -> {
                            ViewUtils.alertPane((Stage) JLB_title.getScene().getWindow(), Init.languageResourceBundle.getString("Warning"), Init.languageResourceBundle.getString("ErrorMessage_noQRcode"));
                        });
                    }
                    Platform.runLater(() -> {
                        JSP_running.setVisible(false);
                    });
                }).start();
            }
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
                    ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.languageResourceBundle.getString("Warning"), Init.languageResourceBundle.getString("ErrotMessage_isImage"));
                    ImgFile = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClickOutImg() {
        //todo
    }

    @FXML
    public void ONClickModeSelect() {
        if (JTB_modeSelect.isSelected()) {
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("EnCodeMode"));
            JBT_confirm.setText(Init.languageResourceBundle.getString("EnCode"));
            JTA_src.setVisible(true);
            JBT_loadImg.setVisible(false);
            JTA_dst.setVisible(false);
            JBT_outImg.setVisible(true);
        }else {
            JTB_modeSelect.setText(Init.languageResourceBundle.getString("DeCodeMode"));
            JBT_confirm.setText(Init.languageResourceBundle.getString("DeCode"));
            JTA_src.setVisible(false);
            JBT_loadImg.setVisible(true);
            JTA_dst.setVisible(true);
            JBT_outImg.setVisible(false);
        }
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
                        ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(), Init.languageResourceBundle.getString("Warning"), Init.languageResourceBundle.getString("ErrotMessage_isImage"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
        ViewInit.comboBoxCharset(JCB_charset);

        JCB_barcodeFormat.getItems().addAll("QR Code",
                "Aztec",
                "PDF 417",
                "MaxiCode");
        JCB_barcodeFormat.setValue("QR Code");

    }

    private BarcodeFormat getBarcodeFormat(String type) {
        return switch (type) {
            case "QR Code" -> BarcodeFormat.QR_CODE;
            case "Aztec" -> BarcodeFormat.AZTEC;
            case "PDF 417" -> BarcodeFormat.PDF_417;
            case "MaxiCode" -> BarcodeFormat.MAXICODE;
            default -> null;
        };
    }
}
