package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.FileHeadChecker;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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

    @Override
    protected void initialize() {
        zoomButton(JBT_LoadFiles);
    }

    @FXML
    public void ONClickLoadFiles() {
        JTA_FileList.setText("123123\n123123\n123123\n123123\n123123\n123123\n123123\n123123\n123123\n123123\n123123\n123123\n");
//        zoomButton(JBT_LoadFiles);
    }

    public void zoomButton(JFXButton jfxButton) {
        JBT_LoadFiles.setOnAction(actionEvent -> {
            if (AnchorPane.getBottomAnchor(jfxButton) == 5.0) {
                ViewUtils.setAnchor(jfxButton, 0.0, 0.0, 0.0, 0.0);
            } else {
                AnchorPane.clearConstraints(jfxButton);

                AnchorPane.setRightAnchor(jfxButton, 20.0);
                AnchorPane.setBottomAnchor(jfxButton, 5.0);
            }

            double targetScale = 0.3;

            // Calculate the target size based on the original size and the scale factor
            double targetWidth = jfxButton.getWidth() * targetScale;
            double targetHeight = jfxButton.getHeight() * targetScale;

            // Create KeyValues for scaleX, scaleY, layoutX, and layoutY properties
            KeyValue scaleXValue = new KeyValue(jfxButton.scaleXProperty(), targetScale);
            KeyValue scaleYValue = new KeyValue(jfxButton.scaleYProperty(), targetScale);
            KeyValue layoutXValue = new KeyValue(jfxButton.layoutXProperty(), jfxButton.getLayoutX() + (jfxButton.getWidth() - targetWidth));
            KeyValue layoutYValue = new KeyValue(jfxButton.layoutYProperty(), jfxButton.getLayoutY() + (jfxButton.getHeight() - targetHeight));

            // Create KeyFrame with KeyValues and duration
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.8), scaleXValue, scaleYValue, layoutXValue, layoutYValue);

            // Create a Timeline with the KeyFrame
            Timeline timeline = new Timeline(keyFrame);

            // Play the animation
            timeline.play();

        });
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
