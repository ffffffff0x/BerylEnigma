package ffffffff0x.beryenigma.App.View.Viewobj;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.Beans.HistoryInfo;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.LogUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.StringJoiner;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-12 13:48
 *
 * 控制器视图的抽象基类，继承自AnchorPane，用于定义界面和控制逻辑的基本结构。
 **/

public abstract class ControllerView extends AnchorPane {
    // 标题标签
    public Label JLB_title;
    // 控制器面板
    public AnchorPane ACP_controllerAnchorPane;
    // 加密按钮
    public JFXButton JBT_enCode;
    // 解密按钮
    public JFXButton JBT_deCode;
    // 确定按钮
    public JFXButton JBT_confirm;
    // 源文本区域
    public JFXTextArea JTA_src;
    public JFXTextArea JTA_src1;
    public JFXTextArea JTA_src2;
    public JFXTextArea JTA_src3;
    public JFXTextArea JTA_src4;
    public JFXTextArea JTA_src5;
    // 目标文本区域
    public JFXTextArea JTA_dst;
    public JFXTextArea JTA_dst1;
    public JFXTextArea JTA_dst2;
    public JFXTextArea JTA_dst3;
    public JFXTextArea JTA_dst4;
    public JFXTextArea JTA_dst5;

    // 构造函数，初始化界面
    public ControllerView() {
        initializeUI();
        initialize();
    }

    /**
     * 全局界面初始化
     */
    protected void initialize() {
        // 设置目标文本区域的错误信息
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);
        // 加载弹出式设置框
        LoadPopupSettingNode();
        // 设置输出框的右键菜单
        JTADSTContextMenu();
//        System.out.println("Father init");
    }

    /**
     * 初始化UI组件
 * 该方法用于初始化用户界面中的各个组件
     */
    protected void initializeUI() {
    // 初始化标题组件
        titleInit();
    // 初始化控制器面板组件
        controllerPaneInit();
    // 初始化背景锚点面板组件
        backgroundAnchorPaneInit();
    }

    protected void titleInit() {
        // 创建一个新的Label对象，用于显示标题
        JLB_title = new Label();
        // 设置标题标签的ID，便于在CSS或其他地方引用
        JLB_title.setId("JLB_title");
        // 设置标题标签的对齐方式为居中
        JLB_title.setAlignment(Pos.CENTER);
        // 设置标题标签的推荐大小为610x70像素
        JLB_title.setPrefSize(610, 70);
        // 设置标题标签的字体为Jokerman，字号为45
        JLB_title.setFont(Font.font("Jokerman", 45));
        AnchorPane.setLeftAnchor(JLB_title, 10.0);
        AnchorPane.setRightAnchor(JLB_title, 10.0);
        AnchorPane.setTopAnchor(JLB_title, 10.0);
    }

// 定义一个受保护的控制器面板初始化方法
    protected void controllerPaneInit() {
    // 创建一个新的AnchorPane对象，命名为ACP_controllerAnchorPane
        ACP_controllerAnchorPane = new AnchorPane();
    // 设置ACP_controllerAnchorPane的推荐大小为200x200像素
        ACP_controllerAnchorPane.setPrefSize(200, 200);
        AnchorPane.setBottomAnchor(ACP_controllerAnchorPane, 10.0);
        AnchorPane.setLeftAnchor(ACP_controllerAnchorPane, 10.0);
        AnchorPane.setRightAnchor(ACP_controllerAnchorPane, 10.0);
        AnchorPane.setTopAnchor(ACP_controllerAnchorPane, 80.0);
    }

    protected void backgroundAnchorPaneInit() {
        // 设置背景面板的ID
        setId("ACP_backgroundAnchorPane");
        setPrefSize(630, 600);
        getChildren().addAll(JLB_title, ACP_controllerAnchorPane);
    }

    /**
     * 全局加密按钮事件
     */
    public void ONClickEncode() {
        // 重置目标文本区域的验证信息
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_enCode");
    }

    /**
     * 全局解密按钮事件
     */
    public void ONClickDecode() {
        // 重置目标文本区域的验证信息
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_deCode");
    }

    /**
     * 全局确定按钮事件
     */
    public void ONClickConfirm() {
        // 重置目标文本区域的验证信息
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_confirm");
    }

    /**
     * 全局输入与控件选取事件
     */
    public void ONReleasedOrSelected() {
        // 重置目标文本区域的验证信息
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father InputSelect");
    }

    /**
     * 加载时的弹出式设置框初始化
     */
    protected void LoadPopupSettingNode() { }

    /**
     * 输出框右键菜单
     */
    protected void JTADSTContextMenu() {
        // 设置输出框的右键菜单
        ViewInit.textAreaContextMenu(JTA_dst, JTA_src);
    }

    /**
     * 构造LOG信息
     * 使用运行此方法时的JTA_SRC与JTA_DST的内容，模组名称为JLB_TITLE内容
     *
     * @return HistoryInfo
     */
    protected HistoryInfo buildLogMessage() {
        // 返回包含源文本、目标文本和标题的日志信息
        return new HistoryInfo(JTA_src.getText(), checkDstJTAText(), JLB_title.getText());
    }

    /**
     * 将log写入存储与内存
     */
    protected void actionLog() {
        // 添加日志信息
        LogUtils.addLog(buildLogMessage());
    }

    /**
     * 检查输出框内是否存在值，若有则返回，返回值使用" ; " 分隔数据
     *
     * @return 使用" ; " 分隔的string
     */
    protected String checkDstJTAText() {
        // 字符串拼接器
        StringJoiner stringJoiner = new StringJoiner(" ; ");

        // 检查并添加各个目标文本区域的内容
        checkTextAreaText(stringJoiner, JTA_dst);
        checkTextAreaText(stringJoiner, JTA_dst1);
        checkTextAreaText(stringJoiner, JTA_dst2);
        checkTextAreaText(stringJoiner, JTA_dst3);
        checkTextAreaText(stringJoiner, JTA_dst4);
        checkTextAreaText(stringJoiner, JTA_dst5);

        return stringJoiner.toString();
    }

    /**
     * 检查传入的textarea是否非空，如果有值则添加至传入的stringjoiner中
     *
     * @param stringJoiner 文本拼接器
     * @param textArea 需要检测的textarea控件
     */
    protected void checkTextAreaText(StringJoiner stringJoiner, TextArea textArea) {
        // 如果文本区域非空且内容非空，则添加至拼接器
        if (textArea != null && !textArea.getText().isEmpty()) {
            stringJoiner.add(textArea.getText());
        }
    }
}
