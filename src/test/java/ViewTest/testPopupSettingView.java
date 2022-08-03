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
        popupSettingList.getChildren().add(new testPopupSettingNode("测试参数1",new JFXButton("测试参数1")));
        popupSettingList.getChildren().add(new testPopupSettingNode("测试参数2",new JFXComboBox<>()));
        popupSettingList.getChildren().add(new testPopupSettingNode("测试参数3",new JFXToggleButton()));
    }

    @Override
    protected void PopupSettingView(Pane pane) {
        //图标
        this.popupSettingHamburger = new JFXHamburger();
        //设置padding
//        hamburger.setPadding(new Insets(10,10,10,10));
        //弹出器
        popupSettingRippler = new JFXRippler(popupSettingHamburger, JFXRippler.RipplerMask.CIRCLE, JFXRippler.RipplerPos.BACK);

        //给要弹出的Pane设置弹出器
        this.popupSettingAnchorPane = new AnchorPane();
        popupSettingAnchorPane.getChildren().add(popupSettingRippler);

        //StackPane添加可弹出的AnchorPane
        this.getChildren().add(popupSettingAnchorPane);

        //AnchorPane中存放控件的纵列布局器
        popupSettingList = new VBox();

        popupSettingPopup = new JFXPopup(popupSettingList);
        popupSettingRippler.setOnMouseClicked(e -> popupSettingPopup.show(popupSettingRippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
        pane.getChildren().add(this);
    }
}
