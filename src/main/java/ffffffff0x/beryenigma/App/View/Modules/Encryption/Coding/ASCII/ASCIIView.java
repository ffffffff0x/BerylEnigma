package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.ASCII;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.Beans.HistoryConfig;
import ffffffff0x.beryenigma.App.View.Viewobj.ControllerView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.OperationTypeEnum;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2025-03-05 11:56
 **/

public class ASCIIView extends ControllerView {

    // 定义额外的组件
    private JFXTextField JTF_split;

    public ASCIIView() {
        super(); // 调用父类构造函数
//        initializeUIComponents(); // 初始化额外的UI组件
    }

    @Override
    protected void initializeUI() {
        super.initializeUI(); // 调用父类的UI初始化方法

        // 设置标题
        JLB_title.setText("ASCII");

        // 初始化源文本区域
        JTA_src = new JFXTextArea();
        JTA_src.setPromptText(Init.getLanguage("DefaultDelimiter"));
        JTA_src.setPrefSize(530, 165);
        AnchorPane.setLeftAnchor(JTA_src, 40.0);
        AnchorPane.setRightAnchor(JTA_src, 40.0);
        AnchorPane.setTopAnchor(JTA_src, 30.0);

        // 初始化目标文本区域
        JTA_dst = new JFXTextArea();
        JTA_dst.setEditable(false);
        JTA_dst.setPrefSize(530, 165);
        AnchorPane.setBottomAnchor(JTA_dst, 37.0);
        AnchorPane.setLeftAnchor(JTA_dst, 40.0);
        AnchorPane.setRightAnchor(JTA_dst, 40.0);
        AnchorPane.setTopAnchor(JTA_dst, 315.0);

        // 初始化按钮和文本框的HBox
        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPrefHeight(70);
        buttonHBox.setSpacing(50);
        AnchorPane.setLeftAnchor(buttonHBox, 40.0);
        AnchorPane.setRightAnchor(buttonHBox, 40.0);
        AnchorPane.setTopAnchor(buttonHBox, 220.0);

        // 初始化编码按钮
        JBT_enCode = new JFXButton(Init.getLanguage("EnCode"));
        JBT_enCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_enCode.setPrefSize(101, 50);
        JBT_enCode.setOnAction(event -> ONClickEncode());

        // 初始化分隔符文本框
        JTF_split = new JFXTextField();
        JTF_split.setAlignment(Pos.CENTER);
        JTF_split.setPrefSize(110, 30);
        JTF_split.setPromptText(Init.getLanguage("Delimiter"));

        // 初始化解码按钮
        JBT_deCode = new JFXButton(Init.getLanguage("DeCode"));
        JBT_deCode.setButtonType(JFXButton.ButtonType.RAISED);
        JBT_deCode.setPrefSize(101, 50);
        JBT_deCode.setOnAction(event -> ONClickDecode());

        // 将按钮和文本框添加到HBox
        buttonHBox.getChildren().addAll(JBT_enCode, JTF_split, JBT_deCode);

        // 将组件添加到控制器面板
        ACP_controllerAnchorPane.getChildren().addAll(JTA_src, JTA_dst, buttonHBox);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode(); // 调用父类的编码按钮事件
        // 实现具体的编码逻辑
        try {
            JTA_dst.setText(ASCIIEnCode());
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
        actionLog(OperationTypeEnum.ENCODE.getName(),
                new HistoryConfig(JTF_split.getPromptText(), JTF_split.getText())); // 记录日志
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode(); // 调用父类的解码按钮事件
        // 实现具体的解码逻辑
        super.ONClickDecode();
        try{
            JTA_dst.setText(ASCIIDeCode());
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
        actionLog(OperationTypeEnum.DECODE.getName(),
                new HistoryConfig(JTF_split.getPromptText(), JTF_split.getText())); // 记录日志
    }

    private String ASCIIEnCode(){
        return ASCIIImpl.encode(
                JTA_src.getText(),
                ViewUtils.getSplit(JTF_split),0);
    }

    private String ASCIIDeCode(){
        return ASCIIImpl.deCode(
                JTA_src.getText(),
                ViewUtils.getSplit(JTF_split));
    }
}