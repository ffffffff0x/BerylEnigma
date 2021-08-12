package ffffffff0x.beryenigma.App.View.Modules.Image.PixelReplacement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement.Image_PixelReplacement;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerObject;
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
    private File Img = null;

    @FXML
    private JFXTextField JTF_key;

    @FXML
    private JFXButton JBT_loadImg;

    @FXML
    private JFXButton JBT_outImg;

    @Override
    protected void initialize() {
        IMG_loadImg = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/JBT_loadImg.png"))));
        IMG_loadImg.setFitHeight(166.0);
        IMG_loadImg.setPreserveRatio(true);
        JBT_loadImg.setGraphic(IMG_loadImg);

        IMG_outImg = new ImageView();
        IMG_outImg.setFitWidth(158.0);
        IMG_outImg.setPreserveRatio(true);
    }

    @Override
    public void ONClickEncode() {
        Image image = Image_PixelReplacement.image_transform(Double.parseDouble(JTF_key.getText()),Img,true,"r");
        IMG_outImg.setImage(image);
        JBT_outImg.setGraphic(IMG_outImg);
    }

    @Override
    public void ONClickDecode() {
        IMG_loadImg.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/ffffffff0x_Logo.png"))));
        JBT_loadImg.setGraphic(IMG_loadImg);
    }

    @FXML
    public void ONClickLoadImg() {
        Img = ViewUtils.getFile();
        try {
            if (Img != null) {
                IMG_loadImg.setImage(new Image(new FileInputStream(Img)));
                JBT_loadImg.setGraphic(IMG_loadImg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
