package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.security.cert.PKIXParameters;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 15:06
 **/

public abstract class PopupSettingViewObject extends StackPane {
    public JFXHamburger hamburger;
    public JFXRippler rippler;
    public AnchorPane anchorPane;
    public JFXPopup popup;
    public VBox list;

    public PopupSettingViewObject(Pane pane) {
        //图标
        this.hamburger = new JFXHamburger();
        //设置padding
        hamburger.setPadding(new Insets(10,10,10,10));
        //弹出器
        rippler = new JFXRippler(hamburger, JFXRippler.RipplerMask.CIRCLE, JFXRippler.RipplerPos.BACK);

        //给要弹出的Pane设置弹出器
        this.anchorPane = new AnchorPane();
        anchorPane.getChildren().add(rippler);

        //StackPane添加可弹出的AnchorPane
        this.getChildren().add(anchorPane);

        //AnchorPane中存放控件的纵列布局器
        list = new VBox();
        //添加操作
        setSetting();

        popup = new JFXPopup(list);
        rippler.setOnMouseClicked(e -> popup.show(rippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));

        AnchorPane.setRightAnchor(this,20.0);
        AnchorPane.setTopAnchor(this,10.0);
        pane.getChildren().add(this);
    }

    public abstract void setSetting();
//        list.getChildren().add(new PopupSettingNodeObject("测试参数1",new Button("测试参数1")));
//        list.getChildren().add(new PopupSettingNodeObject("测试参数2",new JFXComboBox<>()));
//        list.getChildren().add(new PopupSettingNodeObject("测试参数3",new Button()));
}
