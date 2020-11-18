package View.Encryption.Modern.AES;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AESController {

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_charset;
    @FXML private JFXComboBox JCB_split;
    @FXML private JFXButton JBT_option;

    @FXML private void initialize(){
        initButtonOption();
    }

    @FXML
    public void ONClick_JBT_enCode(){
        JTA_dst.setText(JTA_src.getText());
    }

    @FXML
    public void ONClick_JBT_deCode(){

    }

    public void initButtonOption(){
        JBT_option.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/img/settings-5-fill.png"))));
    }
}
