package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.*;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 15:06
 **/

public class PopupSettingView extends StackPane {
    public JFXHamburger hamburger;
    public JFXRippler rippler;
    public AnchorPane anchorPane;
    public JFXPopup popup;
    public VBox list;

    public PopupSettingView(Pane pane) {
        PopupSettingView(pane);
        AnchorPane.setRightAnchor(this,45.0);
        AnchorPane.setTopAnchor(this,5.0);
    }

    public PopupSettingView(Pane pane, Double anchorTop, Double anchorRight, Double anchorLeft, Double anchorBottom) {
        PopupSettingView(pane);
        AnchorPane.setTopAnchor(this,anchorTop);
        AnchorPane.setRightAnchor(this,anchorRight);
        AnchorPane.setLeftAnchor(this,anchorLeft);
        AnchorPane.setBottomAnchor(this, anchorBottom);
    }

    private void PopupSettingView(Pane pane) {
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

    public void setSetting(PopupSettingNode... popupSetting) {
        for (PopupSettingNode popupSettingNode:popupSetting) {
            list.getChildren().add(popupSettingNode);
        }
    }
//        list.getChildren().add(new PopupSettingNodeObject("测试参数1",new Button("测试参数1")));
//        list.getChildren().add(new PopupSettingNodeObject("测试参数2",new JFXComboBox<>()));
//        list.getChildren().add(new PopupSettingNodeObject("测试参数3",new Button()));
}
