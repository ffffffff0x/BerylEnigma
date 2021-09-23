package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.PayloadGeneration;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;

/**
 * @author: RyuZUSUNC
 * @create: 2021/9/14 22:05
 **/
public class ReverseShellGeneratorController extends ViewController {
    @FXML
    JFXTextField JTF_IP;
    @FXML
    JFXTextField JTF_Port;
    @FXML
    JFXComboBox<String> JCB_OS;
    @FXML
    JFXComboBox<String> JCB_Shell;
    @FXML
    JFXComboBox<String> JCB_ListenerType;
    @FXML
    TreeTableColumn JTTV_PayloadTableList;
    @FXML
    JFXTextArea JTA_Listener;
    @FXML
    JFXTextArea JTA_Payload;


    @Override
    protected void initialize() {

    }



}
