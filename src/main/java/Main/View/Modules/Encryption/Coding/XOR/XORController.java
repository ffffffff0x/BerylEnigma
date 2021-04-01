package Main.View.Modules.Encryption.Coding.XOR;

import App.View.Viewobj.ViewControllerObject;
import App.View.Viewobj.ViewSettingItemObject;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-18 13:20
 **/

public class XORController extends ViewControllerObject {
    @FXML
    protected AnchorPane AP_settings;

    @Override
    protected void initialize() {
        super.initialize();
        initSettings(AP_settings);
    }

    @Override
    public void ONClickConfirm() {
        super.ONClickConfirm();
    }

    private void initSettings(AnchorPane settings){
        JFXHamburger show = new JFXHamburger();
        JFXRippler rippler = new JFXRippler(show, JFXRippler.RipplerMask.CIRCLE, JFXRippler.RipplerPos.BACK);


        settings.getChildren().add(rippler);

        VBox popupPane = new VBox();
        popupPane.setPrefWidth(160);
        popupPane.setPrefHeight(400);

        popupPane.setPadding(new Insets(10));
        popupPane.setSpacing(10);

        popupPane.getChildren().add(new ViewSettingItemObject(new Label("密钥"),new JFXTextField()));
        popupPane.getChildren().add(new ViewSettingItemObject(new Label("选项"),new JFXComboBox()));
        popupPane.getChildren().add(new ViewSettingItemObject(new Label("测试"),new JFXTextArea()));

        JFXPopup popup = new JFXPopup(popupPane);
        rippler.setOnMouseClicked(e -> popup.show(rippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
    }
}
