package ffffffff0x.beryenigma.App.View.Modules.Image.PixelReplacement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement.Image_PixelReplacement;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private Image outImage;

    @FXML
    private JFXTextField JTF_key;

    @FXML
    private JFXButton JBT_loadImg;

    @FXML
    private JFXButton JBT_outImg;

    @Override
    protected void initialize() {
        IMG_loadImg = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/JBT_loadImg.png"))));
        IMG_loadImg.setFitHeight(165 - margins);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);

        IMG_outImg = new ImageView();
        IMG_outImg.setPreserveRatio(true);
    }

    @Override
    public void ONClickEncode() {
        outImage = Image_PixelReplacement.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,true,"rc");
        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
        IMG_outImg.setImage(outImage);
        JBT_outImg.setGraphic(IMG_outImg);
    }

    @Override
    public void ONClickDecode() {
        outImage = Image_PixelReplacement.image_transform(Double.parseDouble(JTF_key.getText()) / 100, ImgFile,false,"rc");
        IMG_outImg.setFitHeight(JBT_outImg.getHeight() - margins);
        IMG_outImg.setImage(outImage);
        JBT_outImg.setGraphic(IMG_outImg);
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
        FileUtils.outPutImgFile(IMG_outImg.getImage());
    }
}
