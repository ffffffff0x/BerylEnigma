package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author: RyuZUSUNC
 * @create: 2021/9/27 21:51
 **/
public class PopupSettingImageView extends StackPane {
    protected JFXRippler rippler;
    protected AnchorPane popupAnchorPane;
    protected JFXPopup popup;
    protected VBox list;
    protected VBox vBoximageView;
    protected ImageView imageView;

    public PopupSettingImageView(Pane pane,Image image) {
        PopupSettingView(pane,image);
        AnchorPane.setRightAnchor(this,45.0);
        AnchorPane.setTopAnchor(this,5.0);
    }

    public PopupSettingImageView(Pane pane,Image image, Double anchorTop, Double anchorRight, Double anchorLeft, Double anchorBottom) {
        PopupSettingView(pane,image);
        AnchorPane.setTopAnchor(this,anchorTop);
        AnchorPane.setRightAnchor(this,anchorRight);
        AnchorPane.setLeftAnchor(this,anchorLeft);
        AnchorPane.setBottomAnchor(this, anchorBottom);
    }

    protected void PopupSettingView(Pane pane,Image image) {
        //图标
        this.imageView = new ImageView(image);
        imageView.setFitWidth(24.0);
        imageView.setFitHeight(24.0);
        this.vBoximageView = new VBox(this.imageView);

        //设置padding
//        hamburger.setPadding(new Insets(10,10,10,10));
        //弹出器
        rippler = new JFXRippler(vBoximageView, JFXRippler.RipplerMask.CIRCLE, JFXRippler.RipplerPos.BACK);

        //给要弹出的Pane设置弹出器
        this.popupAnchorPane = new AnchorPane();
        popupAnchorPane.getChildren().add(rippler);

        //StackPane添加可弹出的AnchorPane
        this.getChildren().add(popupAnchorPane);

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
}
