package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.MorseCoder;

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

    @Override
    protected void initializeUI() {
        super.initializeUI();

        setLabelTitle("MorseCoder");
        setSrcTextArea();
        setDstTextArea();
        setEnCodeButton();
        setDeCodeButton();

        JTF_split = new JFXTextField();
        JTF_split.setPromptText(Init.getLanguage("Delimiter"));
        JTF_split.setAlignment(Pos.CENTER);
        JTF_split.setPrefSize(110, 30);

        setButtonHbox();
        setControllerAnchorPane();
    }

    @Override
    protected void setButtonHbox() {
        HBox_Button = new HBox(JBT_enCode, JTF_split, JBT_deCode);
        HBox_Button.setAlignment(Pos.CENTER);
        HBox_Button.setPrefHeight(70);
        HBox_Button.setSpacing(50);
        AnchorPane.setTopAnchor(HBox_Button, 220.0);
        AnchorPane.setLeftAnchor(HBox_Button, 40.0);
        AnchorPane.setRightAnchor(HBox_Button, 40.0);
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
