package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author: RyuZUSUNC
 * @create: 2023/8/16 20:11
 **/
@ViewNode(name = "FileHeadChecker",folderPath = "Root/Tools/RedTeam/",fxmlName = "FileHeadCheckerView.fxml")
public class FileHeadCheckerController extends ViewController {
    @FXML
    JFXToggleButton JTB_ModeSwitch;
    @FXML
    Label JLB_JSONName;
    @FXML
    Label JLB_JSONNum;
    @FXML
    Label JLB_FileNum;
    @FXML
    JFXTextField JTF_CheckHexNum;
    @FXML
    JFXButton JBT_Check;
    @FXML
    JFXButton JBT_LoadFiles;
    @FXML
    JFXTextArea JTA_FileList;

    // 加载要检测文件头的文件列表
    List<File> files;

    // 缓存文件头信息-文件头信息
    HashMap<String,FileHeaderBean> fileTypes;

    // 默认使用的头文件对照表路径
    final String DefaultFileTypeJsonPath = "/json/redTeam/FileHead.json";

    // JSON序列化工具
    Gson gson = new Gson();

    @Override
    protected void initialize() {
        // 初始化本地文件头信息
        String jsonData = new BufferedReader(new InputStreamReader(Objects.requireNonNull(FileHeadCheckerController.class.getResourceAsStream(DefaultFileTypeJsonPath))))
                .lines().collect(Collectors.joining(System.lineSeparator()));

        // 保留类型信息使用的TypeToken
        Type type = new TypeToken<HashMap<String, FileHeaderBean>>(){}.getType();
        fileTypes = gson.fromJson(jsonData, type);

        JLB_JSONNum.setText("Num: " + fileTypes.size());
    }

    @FXML
    public void ONClickLoadFiles() {
        zoomButton(JBT_LoadFiles);
        files = ViewUtils.getFiles();
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (File file : files) {
            stringJoiner.add(file.getPath());
        }
        JTA_FileList.setText(stringJoiner.toString());
        JLB_FileNum.setText("FileNum: " + files.size());
    }

    public void zoomButton(JFXButton jfxButton) {
        if (AnchorPane.getBottomAnchor(jfxButton) == 5.0) {
//            ViewUtils.setAnchor(jfxButton, 0.0, 0.0, 0.0, 0.0);
        } else {
            AnchorPane.clearConstraints(jfxButton);

            jfxButton.setPrefSize(40.0,40.0);

            AnchorPane.setRightAnchor(jfxButton, 20.0);
            AnchorPane.setBottomAnchor(jfxButton, 5.0);
        }
    }

    public void buttonAnimate() {
        JBT_LoadFiles.setOnAction(actionEvent -> {
            // Create KeyValues for scaleX and scaleY properties
            KeyValue scaleXValue = new KeyValue(JBT_LoadFiles.scaleXProperty(), 0.3);
            KeyValue scaleYValue = new KeyValue(JBT_LoadFiles.scaleYProperty(), 0.3);

            // Create KeyFrame with KeyValues and duration
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.8), scaleXValue, scaleYValue);

            // Create a Timeline with the KeyFrame
            Timeline timeline = new Timeline(keyFrame);

            // Play the animation
            timeline.play();
        });
    }


}
