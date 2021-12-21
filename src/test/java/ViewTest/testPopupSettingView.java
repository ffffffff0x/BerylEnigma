package ViewTest;

import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        list.getChildren().add(new testPopupSettingNode("测试参数1",new JFXButton("测试参数1")));
        list.getChildren().add(new testPopupSettingNode("测试参数2",new JFXComboBox<>()));
        list.getChildren().add(new testPopupSettingNode("测试参数3",new JFXToggleButton()));
    }

    @Override
    protected void PopupSettingView(Pane pane) {
        //图标
        this.hamburger = new JFXHamburger();
        //设置padding
//        hamburger.setPadding(new Insets(10,10,10,10));
        //弹出器
        rippler = new JFXRippler(hamburger, JFXRippler.RipplerMask.CIRCLE, JFXRippler.RipplerPos.BACK);

        //给要弹出的Pane设置弹出器
        this.anchorPane = new AnchorPane();
        anchorPane.getChildren().add(rippler);

        //StackPane添加可弹出的AnchorPane
        this.getChildren().add(anchorPane);

        //AnchorPane中存放控件的纵列布局器
        list = new VBox();

        popup = new JFXPopup(list);
        rippler.setOnMouseClicked(e -> popup.show(rippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
        pane.getChildren().add(this);
    }
}
