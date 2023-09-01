package ffffffff0x.beryenigma.App.View.Viewobj;

import ffffffff0x.beryenigma.App.Beans.HistoryInfo;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.LogUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.StringJoiner;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-12 13:48
 **/

public abstract class ViewController {
    @FXML
    public Label JLB_title;
    @FXML
    public AnchorPane ACP_backgroundAnchorPane;
    @FXML
    public AnchorPane ACP_controllerAnchorPane;
    @FXML
    public JFXButton JBT_enCode;
    @FXML
    public JFXButton JBT_deCode;
    @FXML
    public JFXButton JBT_confirm;
    @FXML
    public JFXTextArea JTA_src;
    @FXML
    public JFXTextArea JTA_src1;
    @FXML
    public JFXTextArea JTA_src2;
    @FXML
    public JFXTextArea JTA_src3;
    @FXML
    public JFXTextArea JTA_src4;
    @FXML
    public JFXTextArea JTA_src5;
    @FXML
    public JFXTextArea JTA_dst;
    @FXML
    public JFXTextArea JTA_dst1;
    @FXML
    public JFXTextArea JTA_dst2;
    @FXML
    public JFXTextArea JTA_dst3;
    @FXML
    public JFXTextArea JTA_dst4;
    @FXML
    public JFXTextArea JTA_dst5;

    /**
     * 全局界面初始化
     */
    @FXML
    protected void initialize() {
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);
        LoadPopupSettingNode();
        JTADSTContextMenu();
//        System.out.println("Father init");
    }

    /**
     * 全局加密按钮事件
     */
    @FXML
    public void ONClickEncode() {
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_enCode");
    }

    /**
     * 全局解密按钮事件
     */
    @FXML
    public void ONClickDecode() {
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_deCode");
    }

    /**
     * 全局确定按钮事件
     */
    @FXML
    public void ONClickConfirm() {
        ViewUtils.textAreaValidateReset(JTA_dst);
//        System.out.println("Father JBT_confirm");
    }

    /**
     * 全局输入与控件选取事件
     */
    @FXML
    public void ONReleasedOrSelected() {
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
        ViewInit.textAreaContextMenu(JTA_dst, JTA_src);
    }

    /**
     * 构造LOG信息
     * 使用运行此方法时的JTA_SRC与JTA_DST的内容，模组名称为JLB_TITLE内容
     *
     * @return HistoryInfo
     */
    protected HistoryInfo buildLogMessage() {
        return new HistoryInfo(JTA_src.getText(), checkDstJTAText(), JLB_title.getText());
    }

    /**
     * 将log写入存储与内存
     */
    protected void actionLog() {
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
        if (textArea != null && !textArea.getText().equals("")) {
            stringJoiner.add(textArea.getText());
        }
    }
}
