package ffffffff0x.beryenigma.App.View.Viewobj;

import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-12 13:48
 **/

public class ViewControllerObject {
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
}
