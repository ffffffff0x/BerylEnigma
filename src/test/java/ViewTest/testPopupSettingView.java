package ViewTest;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * @author: RyuZUSUNC
 * @create: 2021/9/7 21:21
 **/
public class testPopupSettingView extends PopupSettingView {

    public testPopupSettingView(Pane pane) {
        super(pane);
    }

    public testPopupSettingView(Pane pane, Double anchorTop, Double anchorRight, Double anchorLeft, Double anchorBottom) {
        super(pane, anchorTop, anchorRight, anchorLeft, anchorBottom);
    }

    public void setSetting(ArrayList<PopupSettingNode> popupSetting) {
        list.getChildren().add(new testPopupSettingNode("测试参数1",new JFXButton("测试参数1"),true));
        list.getChildren().add(new testPopupSettingNode("测试参数2",new JFXComboBox<>(),false));
        list.getChildren().add(new testPopupSettingNode("测试参数3",new JFXToggleButton(),false));
    }
}
