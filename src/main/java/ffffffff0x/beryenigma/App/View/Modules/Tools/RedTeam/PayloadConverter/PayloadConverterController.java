package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.PayloadConverter;

import com.jfoenix.controls.JFXComboBox;
import ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.PayloadConverter.RedTeam_PayloadConverter;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import javafx.fxml.FXML;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-07 10:10
 **/

public class PayloadConverterController extends ViewController {
    private RedTeam_PayloadConverter payloadConverter =  new RedTeam_PayloadConverter();
    @FXML
    JFXComboBox<String> JCB_payloadType;

    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        initCombobox();
    }

    /**
     * 全局输入与控件选取事件
     */
    @Override
    public void ONReleasedOrSelected() {
        super.ONReleasedOrSelected();
        JTA_dst.setText(payloadConverter.converter(JTA_src.getText(),JCB_payloadType.getValue()));
    }

    private void initCombobox() {
        JCB_payloadType.getItems().addAll("Bash","PowerShell","Python","Perl");
        JCB_payloadType.setValue("Bash");
        JCB_payloadType.setVisibleRowCount(6);
    }
}
