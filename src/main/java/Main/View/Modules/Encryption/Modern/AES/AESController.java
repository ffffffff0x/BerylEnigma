package Main.View.Modules.Encryption.Modern.AES;

import Init.Init;
import Init.ViewInit;
import Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author RyuZU
 */
public class AESController {

    private Integer AP_OPTION_STATES = 0;
    private AESControlList aesControlList = new AESControlList();

    @FXML private JFXButton JBT_enCode;
    @FXML private JFXButton JBT_deCode;
    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXButton JBT_option;
    @FXML private GridPane GP_option;

    @FXML private AnchorPane AP_option;
    @FXML private JFXComboBox JCB_encryptMode;
    @FXML private JFXComboBox JCB_paddingMode;
    @FXML private JFXTextArea JTA_AESKey;
    @FXML private JFXTextArea JTA_AESIV;
    @FXML private JFXComboBox JCB_outputFormat;
    @FXML private JFXComboBox JCB_textEncoding;
    @FXML private JFXComboBox JCB_keyFormat;

    @FXML private void initialize(){
        AP_option.setVisible(false);
        initButtonOption();
        initComboBoxes();
    }

    @FXML
    public void ONClick_JBT_enCode(){
        setOptions();
    }

    @FXML
    public void ONClick_JBT_deCode(){
        setOptions();
    }

    @FXML
    public void ONClick_JBT_option(){
        optionPaneAnime(AP_OPTION_STATES);
        
    }

    @FXML
    public void ONCheck_JCB_Item_NoPadding(){
        if(JCB_paddingMode.getValue().equals("NoPadding")){
            ViewUtils.alertPane((Stage) JCB_paddingMode.getScene().getWindow(),Init.languageResourceBundle.getString("Warning"),Init.languageResourceBundle.getString("AES_NOPadding_Waring"));
        }
    }

    public void initButtonOption(){
        JBT_option.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/img/settings-5-fill.png"))));
    }

    public void initComboBoxes(){
        JCB_encryptMode.getItems().addAll("ECB","CBC");
        JCB_encryptMode.setValue("ECB");

        JCB_paddingMode.getItems().addAll("PKCS5","PKCS7","NoPadding");
        JCB_paddingMode.setValue("PKCS5");

        JCB_outputFormat.getItems().addAll("Base64","HEX");
        JCB_outputFormat.setValue("HEX");

        JCB_keyFormat.getItems().addAll("Text","Base64","HEX");
        JCB_keyFormat.setValue("Text");

        ViewInit.comboBoxCharset(JCB_textEncoding);

    }

    public void optionPaneAnime(Integer states) {
        Timeline timeLine = new Timeline();
        KeyFrame kf_AP_Start;
        KeyFrame kf_AP_Stop;
        KeyFrame kf_BT_Start;
        KeyFrame kf_BT_Stop;
        if(states == 0){
            kf_AP_Start = new KeyFrame(Duration.seconds(0), "Start", event -> AP_option.setVisible(true),
                    new KeyValue(AP_option.scaleYProperty(), 610));
            kf_AP_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 455));

            kf_BT_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 570));
            kf_BT_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 410));

            timeLine.getKeyFrames().addAll(kf_AP_Start,kf_AP_Stop,kf_BT_Start,kf_BT_Stop);
            AP_OPTION_STATES = 1;
        }else{
            kf_AP_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 455));
            kf_AP_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> { },
                    new KeyValue(AP_option.layoutXProperty(), 610));

            kf_BT_Start = new KeyFrame(Duration.seconds(0), "Start", event -> { },
                    new KeyValue(JBT_option.layoutXProperty(), 410));
            kf_BT_Stop = new KeyFrame(Duration.seconds(0.4), "Stop", event -> AP_option.setVisible(false),
                    new KeyValue(JBT_option.layoutXProperty(), 570));

            timeLine.getKeyFrames().addAll(kf_AP_Start,kf_AP_Stop,kf_BT_Start,kf_BT_Stop);
            AP_OPTION_STATES = 0;
        }
        timeLine.play();
    }

    public void keyCheck(String key,String charset){
        //// TODO: 2020/11/27  
        if(aesControlList.getKEY_FORMAT().equals("HEX")){
            try {
                Hex.decodeHex(key.toCharArray());
            } catch (DecoderException e) {
                e.printStackTrace();
            }
        } else if(aesControlList.getKEY_FORMAT().equals("Base64")){

        } else{

        }
    }

    public void setOptions(){
        aesControlList.setKEY_FORMAT(JCB_keyFormat.getValue().toString());
        aesControlList.setENCRYPT_MODE(JCB_encryptMode.getValue().toString());
        aesControlList.setPADDING_MODE(JCB_paddingMode.getValue().toString());
        aesControlList.setOUTPUT_FORMAT(JCB_outputFormat.getValue().toString());
        aesControlList.setTEXT_ENCODING(JCB_textEncoding.getValue().toString());
    }

}
