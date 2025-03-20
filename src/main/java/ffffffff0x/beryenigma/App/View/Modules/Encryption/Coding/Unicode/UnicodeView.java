package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.Unicode;

import com.jfoenix.controls.JFXTextArea;
import ffffffff0x.beryenigma.App.View.Viewobj.ControllerView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2025-03-20 15:04
 **/

public class UnicodeView extends ControllerView {
    private HBox hBox;

    public UnicodeView() {
        super();
    }

    @Override
    protected void initializeUI() {
        super.initializeUI();

        JLB_title.setText("Unicode");

        JTA_src = new JFXTextArea();
        JTA_src.setPrefSize(530,165);
        AnchorPane.setTopAnchor(JTA_src, 30.0);
        AnchorPane.setLeftAnchor(JTA_src, 30.0);
        AnchorPane.setRightAnchor(JTA_src, 30.0);

        JTA_dst = new JFXTextArea();
        JTA_dst.setPrefSize(530,165);
        AnchorPane.setTopAnchor(JTA_dst, 315.0);
        AnchorPane.setLeftAnchor(JTA_dst, 40.0);
        AnchorPane.setRightAnchor(JTA_dst, 40.0);
        AnchorPane.setBottomAnchor(JTA_dst, 37.0);

        JBT_enCode = new JFXButton(Init.getLanguage("Encode"));
        JBT_deCode = new JFXButton(Init.getLanguage("Decode"));
        JBT_enCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_deCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_enCode.setPrefSize(101, 50);
        JBT_deCode.setPrefSize(101, 50);

        JBT_enCode.setOnAction(event -> onClickEncode());
        JBT_deCode.setOnAction(event -> onClickDecode());

        hBox = new HBox();
        hBox.setSpacing(140);
        hBox.setPrefHeight(70);
        hBox.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(hBox, 40.0);
        AnchorPane.setRightAnchor(hBox, 40.0);
        AnchorPane.setTopAnchor(hBox, 220.0);

        hBox.getChildren().addAll(JBT_enCode, JBT_deCode);

        ACP_controllerAnchorPane.getChildren().addAll(JTA_src, JTA_dst);

        ACP_controllerAnchorPane.getChildren().add(hBox);

    }

    private void onClickEncode() {
        try {
            JTA_dst.setText(UnicodeImpl.encode(JTA_src.getText()));
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    private void onClickDecode() {
        try {
            JTA_dst.setText(UnicodeImpl.decode(JTA_src.getText()));
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

}
