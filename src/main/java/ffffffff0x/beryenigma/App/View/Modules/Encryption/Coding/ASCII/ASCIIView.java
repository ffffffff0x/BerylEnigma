package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.ASCII;

import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2025-03-05 11:56
 **/

public class ASCIIView extends AnchorPane {
    private JFXTextField JTF_split;
    private JFXTextArea JTA_src;
    private JFXTextArea JTA_dst;
    private JFXButton JBT_enCode;
    private JFXButton JBT_deCode;
    private Label JLB_title;

    public ASCIIView() {
        createAndLayoutControls();
        setupEventHandlers();
    }

    private void createAndLayoutControls() {
        // Create and layout JLB_title
        JLB_title = new Label("ASCII");
        JLB_title.setFont(new Font("Jokerman", 45));
        JLB_title.setPrefWidth(610);
        JLB_title.setPrefHeight(70);
        JLB_title.setAlignment(javafx.geometry.Pos.CENTER);
        setTopAnchor(JLB_title, 10.0);
        setLeftAnchor(JLB_title, 10.0);
        setRightAnchor(JLB_title, 10.0);

        // Create and layout JTA_src
        JTA_src = new JFXTextArea();
        JTA_src.setPromptText(Init.getLanguage("DefaultDelimiter"));
        JTA_src.setPrefWidth(530);
        JTA_src.setPrefHeight(165);
        setTopAnchor(JTA_src, 30.0);
        setLeftAnchor(JTA_src, 40.0);
        setRightAnchor(JTA_src, 40.0);

        // Create and layout JTA_dst
        JTA_dst = new JFXTextArea();
        JTA_dst.setEditable(false);
        JTA_dst.setPrefWidth(530);
        JTA_dst.setPrefHeight(165);
        setBottomAnchor(JTA_dst, 37.0);
        setLeftAnchor(JTA_dst, 40.0);
        setRightAnchor(JTA_dst, 40.0);
        setTopAnchor(JTA_dst, 315.0);

        // Create and layout JBT_enCode
        JBT_enCode = new JFXButton(Init.getLanguage("EnCode"));
        JBT_enCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_enCode.setPrefWidth(101);
        JBT_enCode.setPrefHeight(50);

        // Create and layout JTF_split
        JTF_split = new JFXTextField();
        JTF_split.setPromptText(Init.getLanguage("Delimiter"));
        JTF_split.setPrefWidth(110);
        JTF_split.setPrefHeight(30);

        // Create and layout JBT_deCode
        JBT_deCode = new JFXButton(Init.getLanguage("DeCode"));
        JBT_deCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_deCode.setPrefWidth(101);
        JBT_deCode.setPrefHeight(50);

        // Create and layout HBox
        HBox hbox = new HBox(50);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.getChildren().addAll(JBT_enCode, JTF_split, JBT_deCode);
        setTopAnchor(hbox, 220.0);
        setLeftAnchor(hbox, 40.0);
        setRightAnchor(hbox, 40.0);

        // Add all components to the AnchorPane
        this.getChildren().addAll(JLB_title, JTA_src, JTA_dst, hbox);
    }

    private void setupEventHandlers() {
        JBT_enCode.setOnAction(event -> ONClickEncode());
        JBT_deCode.setOnAction(event -> ONClickDecode());
    }

    private void ONClickEncode(){
        try {
            JTA_dst.setText(ASCIIImpl.encode(
                    JTA_src.getText(),
                    ViewUtils.getSplit(JTF_split),0));
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    private void ONClickDecode(){
        try{
            JTA_dst.setText(ASCIIImpl.deCode(
                    JTA_src.getText(),
                    ViewUtils.getSplit(JTF_split)));
        } catch (Exception e) {
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }
}