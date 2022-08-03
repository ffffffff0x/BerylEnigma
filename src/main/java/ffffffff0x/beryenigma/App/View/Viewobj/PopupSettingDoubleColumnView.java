package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-21 15:16
 **/

public class PopupSettingDoubleColumnView extends PopupSettingView{
    VBox list2;
    HBox hBox;

    public PopupSettingDoubleColumnView(Pane pane) {
        super(pane);
    }

    public PopupSettingDoubleColumnView(Pane pane, Double anchorTop, Double anchorRight, Double anchorLeft, Double anchorBottom) {
        super(pane, anchorTop, anchorRight, anchorLeft, anchorBottom);
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
        list2 = new VBox();
        hBox = new HBox();

        hBox.getChildren().addAll(popupSettingList,list2);

        popupSettingPopup = new JFXPopup(hBox);
        popupSettingRippler.setOnMouseClicked(e -> popupSettingPopup.show(popupSettingRippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
        pane.getChildren().add(this);
    }

    @Override
    public void setSetting(PopupSettingNode... popupSetting) {
        for (PopupSettingNode popupSettingNode:popupSetting) {
            int count = popupSettingList.getChildren().size() + list2.getChildren().size() + 1;
            if (count % 2 == 1){
                popupSettingList.getChildren().add(popupSettingNode);
            }else {
                list2.getChildren().add(popupSettingNode);
            }
        }
    }
}
