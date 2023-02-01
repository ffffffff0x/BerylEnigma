package ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.RailFence;

import com.jfoenix.controls.JFXComboBox;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.App.Implement.Encryption.Classical.RailFence.Classical_RailFence;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

@ViewNode(name = "RailFence",folderPath = "Root/Encryption/Classical/",fxmlName = "RailFenceView.fxml")
public class RailFenceController extends ViewController {

    @FXML private JFXTextField JTF_quantity;
    private JFXComboBox JCB_modeSelect;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try{
            if (JCB_modeSelect.getValue().equals(Init.getLanguage("RailFence"))) {
                JTA_dst.setText(Classical_RailFence.encode(JTA_src.getText(),Integer.parseInt(JTF_quantity.getText())));
            }else {
                JTA_dst.setText(Classical_RailFence.encodeTypeW(JTA_src.getText(),Integer.parseInt(JTF_quantity.getText())));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try{
            if (JCB_modeSelect.getValue().equals(Init.getLanguage("RailFence"))) {
                JTA_dst.setText(Classical_RailFence.decode(JTA_src.getText(),Integer.parseInt(JTF_quantity.getText())));
            }else {
                JTA_dst.setText(Classical_RailFence.decodeTypeW(JTA_src.getText(),Integer.parseInt(JTF_quantity.getText())));
            }
        }catch (Exception e){
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    protected void LoadPopupSettingNode() {
        super.LoadPopupSettingNode();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        Init.getLanguage("RailFence"),
                        Init.getLanguage("RailFence(W)")
                );
        JCB_modeSelect = new JFXComboBox<>(options);
        JCB_modeSelect.setValue(Init.getLanguage("RailFence"));
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);
        popupSettingView.setSetting(new PopupSettingNode(Init.getLanguage("OperateMode"), JCB_modeSelect,true));
    }
}
