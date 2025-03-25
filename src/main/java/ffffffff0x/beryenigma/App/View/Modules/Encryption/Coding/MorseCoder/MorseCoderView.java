package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.MorseCoder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.ControllerView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2025-03-25 16:50
 **/

public class MorseCoderView extends ControllerView {
    JFXTextField JTF_split;
    HBox hBox;

    @Override
    protected void initializeUI() {
        super.initializeUI();

        JLB_title.setText("MorseCoder");

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

        JBT_enCode.setOnAction(event -> ONClickEncode());
        JBT_deCode.setOnAction(event -> ONClickDecode());

        JTF_split = new JFXTextField();
        JTF_split.setPromptText(Init.getLanguage("Delimiter"));
        JTF_split.setAlignment(Pos.CENTER);
        JTF_split.setPrefSize(110, 30);

        hBox = new HBox(JBT_enCode, JTF_split, JBT_deCode);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(70);
        hBox.setSpacing(50);
        hBox.setLayoutX(40);
        hBox.setLayoutY(220);
        AnchorPane.setLeftAnchor(hBox, 40.0);
        AnchorPane.setRightAnchor(hBox, 40.0);

        ACP_controllerAnchorPane.getChildren().addAll(JTA_src, hBox, JTA_dst);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            if(JTF_split.getText().isEmpty()){
                JTA_dst.setText(MorseCoderImpl.encode(JTA_src.getText()," "));
            }else{
                JTA_dst.setText(MorseCoderImpl.encode(JTA_src.getText(),JTF_split.getText()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            if(JTF_split.getText().isEmpty()){
                JTA_dst.setText(MorseCoderImpl.decode(JTA_src.getText()," "));
            }else{
                JTA_dst.setText(MorseCoderImpl.decode(JTA_src.getText(),JTF_split.getText()));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}
