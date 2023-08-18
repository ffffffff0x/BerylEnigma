package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.PayloadConverter;

import com.jfoenix.controls.JFXComboBox;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import javafx.fxml.FXML;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-07 10:10
 **/

@ViewNode(name = "PayloadConverter",folderPath = "Root/Tools/RedTeam/",fxmlName = "PayloadConverterView.fxml")
public class PayloadConverterController extends ViewController {
    private PayloadConverterImpl payloadConverter =  new PayloadConverterImpl();
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
