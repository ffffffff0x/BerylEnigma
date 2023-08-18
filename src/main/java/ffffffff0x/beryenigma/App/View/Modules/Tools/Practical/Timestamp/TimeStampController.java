package ffffffff0x.beryenigma.App.View.Modules.Tools.Practical.Timestamp;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.time.ZoneId;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-01-13 10:44
 **/

@ViewNode(name = "Timestamp",folderPath = "Root/Tools/Practical/",fxmlName = "TimeStampView.fxml")
public class TimeStampController extends ViewController {
    @FXML
    ImageView IMG_refresh;
    @FXML
    JFXTextField JTF_nowTimestamp;
    @FXML
    JFXTextField JTF_dateFormatString;
    @FXML
    JFXComboBox<String> JCB_timeZone;
    @FXML
    JFXComboBox<String> JCB_timestampAccuracy;

    @Override
    protected void initialize() {
        JTF_nowTimestamp.setText(TimeStampImpl.getNowTimeStamp().toString());
        JTA_src.setPromptText("1673598230000\r\n" +
                "1773598230000\r\n" +
                "1873598230000\r\n" +
                "1973598230000\r\n" +
                "2073598230000\r\n" +
                "2173598230000");
        initComboBox();
        initButtons();
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        StringBuilder sb = new StringBuilder();
        try {
            for (String s : JTA_src.getText().split("\n")) {
                if (JCB_timestampAccuracy.getValue().equals(Init.getLanguage("Seconds"))) {
                    sb.append(TimeStampImpl.timeStampToString(Long.valueOf(s,10)*1000,JTF_dateFormatString.getText(),ZoneId.of(JCB_timeZone.getValue())));
                } else {
                    sb.append(TimeStampImpl.timeStampToString(Long.valueOf(s,10),JTF_dateFormatString.getText(),ZoneId.of(JCB_timeZone.getValue())));
                }
                sb.append("\n");
            }
            JTA_dst.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }


    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        StringBuilder sb = new StringBuilder();
        try {
            for (String s : JTA_src.getText().split("\n")) {
                if (JCB_timestampAccuracy.getValue().equals(Init.getLanguage("Seconds"))) {
                    sb.append(TimeStampImpl.stringToTimeStamp(s,JTF_dateFormatString.getText(),ZoneId.of(JCB_timeZone.getValue()))/1000);
                } else {
                    sb.append(TimeStampImpl.stringToTimeStamp(s,JTF_dateFormatString.getText(),ZoneId.of(JCB_timeZone.getValue())));
                }
                sb.append("\n");
            }
            JTA_dst.setText(sb.toString());
        }catch (Exception e) {
            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @FXML
    public void ONClickRefresh() {
        JTF_nowTimestamp.setText(TimeStampImpl.getNowTimeStamp().toString());
    }

    public void initComboBox() {
        for (String temp : TimeStampImpl.getAllTimeZone().values()) {
            JCB_timeZone.getItems().add(temp);
        }
        JCB_timeZone.setValue(ZoneId.systemDefault().toString());

        JCB_timestampAccuracy.getItems().add(Init.getLanguage("Seconds"));
        JCB_timestampAccuracy.getItems().add(Init.getLanguage("Milliseconds"));
        JCB_timestampAccuracy.setValue(Init.getLanguage("Milliseconds"));
    }

    public void initButtons() {
        // 加载图像输入框图像
        IMG_refresh.setImage(ViewUtils.getImage(ImageListInit.ICON_JBT_REFRESH));
//        JBT_refresh.setStyle("-fx-background-color: f4f4f4");
    }
}
