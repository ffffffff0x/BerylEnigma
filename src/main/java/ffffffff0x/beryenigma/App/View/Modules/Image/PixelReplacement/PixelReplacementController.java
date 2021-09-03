package ffffffff0x.beryenigma.App.View.Modules.Image.PixelReplacement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement.Image_PixelReplacement;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.Kit.Utils.ZmFyZXdlbGw;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-20 11:29
 **/

public class PixelReplacementController extends ViewControllerObject {
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

    @Override
    protected void initialize() {
        JTF_key.setValidators(new RequiredFieldValidator(Init.languageResourceBundle.getString("ErrorMessage")));
        IMG_loadImg = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/JBT_loadImg.png"))));
        IMG_loadImg.setFitHeight(165 - margins);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);

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
                    outBufferedImage = Image_PixelReplacement.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,true,"rc");
                    Platform.runLater(() -> {
                        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
                        IMG_outImg.setImage(ViewUtils.convertToFxImage(outBufferedImage));
                        JBT_outImg.setGraphic(IMG_outImg);
                        JSP_running.setVisible(false);
                    });
                }).start();
            }else {
                if (Objects.equals(JTF_key.getText(), "20210902")) {
                    ViewUtils.alertPane((Stage)ACP_backgroundAnchorPane.getScene().getWindow() , "",ZmFyZXdlbGw.ZmFyZXdlbGw_DYY());
                }
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
                    outBufferedImage = Image_PixelReplacement.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,false,"rc");
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
                IMG_loadImg.setImage(new Image(new FileInputStream(ImgFile)));
                JBT_loadImg.setGraphic(IMG_loadImg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ONClickOutImg() {
        try {
            if (ImgFile != null) {
                String formatName = FileUtils.getFileFormatName(ImgFile);
                StringBuilder imgFileName = new StringBuilder(ImgFile.getName());
                imgFileName.insert(ImgFile.getName().lastIndexOf("."),"_PixelReplacement");
                File outFile = ViewUtils.fileChooser(imgFileName.toString());

//                if (FileUtils.getFileFormatName(ImgFile).equals("jpg") || FileUtils.getFileFormatName(ImgFile).equals("jpeg")) {
//                    ColorSpace cpace = new ICC_ColorSpace(ICC_Profile.getInstance(Image_PixelReplacement.class.getClassLoader().getResourceAsStream("icc/cmyk.icc")));
//                    ColorConvertOp op = new ColorConvertOp(outBufferedImage.getColorModel().getColorSpace(), cpace, null);
//                    outBufferedImage = op.filter(outBufferedImage, null);
//                }
                if (outFile != null) {
                    ImageIO.write(outBufferedImage,formatName,outFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//