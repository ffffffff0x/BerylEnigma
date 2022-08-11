package ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.ReverseShellGenerator;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.ReverseShellGenerator.RedTeam_ReverseShellGenerator;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.ReverseShellGenerator.ReverseShellBeans;
import ffffffff0x.beryenigma.App.Implement.Tools.RedTeam.ReverseShellGenerator.ReverseShellDataBean;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingImageView;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Arrays;

/**
 * @author: RyuZUSUNC
 * @create: 2021/9/14 22:05
 **/
public class ReverseShellGeneratorController extends ViewController {
    @FXML
    private JFXTextField JTF_IP;
    @FXML
    private JFXTextField JTF_Port;
    @FXML
    private AnchorPane AP_Listener;
    @FXML
    private AnchorPane AP_Reverseshell;
    @FXML
    private JFXTextArea JTA_Listener;
    @FXML
    private JFXTextArea JTA_Reverseshell;
    @FXML
    private GridPane GP_Bottom;

    private JFXComboBox<String> JCB_OS;
    private JFXComboBox<String> JCB_Shell;
    private JFXComboBox<String> JCB_ListenerType;
    private PopupSettingImageView SettingListener;
    private PopupSettingImageView SettingPayload;

    private RedTeam_ReverseShellGenerator reverseShellGenerator;
    private ReverseShellDataBean reverseShellDataBean;

    JFXTreeTableView<shellName> treeView;

    @Override
    protected void initialize() {
        reverseShellGenerator = new RedTeam_ReverseShellGenerator();
        tableViewInit();
        initDataBean();
        initPopupSettings();
    }

    @FXML
    private void ONIPChange() {
        reverseShellDataBean.setIp(JTF_IP.getText());
        setShellAndListener();
    }

    @FXML
    private void ONPortChange() {
        reverseShellDataBean.setPort(JTF_Port.getText());
        setShellAndListener();
    }

    private void ONShellChange() {
        reverseShellDataBean.setShell(JCB_Shell.getValue());
        setShellAndListener();
    }

    private void ONListenerTypeChange() {
        reverseShellDataBean.setListenerType(JCB_ListenerType.getValue());
        setShellAndListener();
    }

    private void initDataBean() {
        reverseShellDataBean = new ReverseShellDataBean("127.0.0.1","8080","Bash -i","sh","nc");
    }

    /**
     * 初始化加载tableview列
     */
    private void tableViewInit() {
        final TreeItem<shellName> root = new RecursiveTreeItem<>(getAllShellName(), RecursiveTreeObject::getChildren);
        treeView = new JFXTreeTableView<>(root);
        treeView.setShowRoot(false);
        JFXTreeTableColumn<shellName, String> shellName = new JFXTreeTableColumn<>("ShellType");
        shellName.setPrefWidth(240);
        shellName.setCellValueFactory((TreeTableColumn.CellDataFeatures<shellName, String> param) -> {
            if (shellName.validateValue(param)) {
                return param.getValue().getValue().shellNames;
            } else {
                return shellName.getComputedValue(param);
            }
        });

        treeView.getColumns().setAll(shellName);

        treeView.setOnMouseClicked(event -> {
            Node node = event.getPickResult().getIntersectedNode();
//            System.out.println(node);
            if (node instanceof Text) {
                reverseShellDataBean.setShellType(((Text) node).getText());
                setShellAndListener();
            }else if(node instanceof TreeTableCell && ((TreeTableCell) node).getText() != null) {
                reverseShellDataBean.setShellType(((TreeTableCell) node).getText());
                setShellAndListener();
            }
        });

        treeView.setStyle("-fx-font-size: 14");

        GP_Bottom.add(treeView,0,0);
    }

    /**
     * 获取包含所有shell名字的实体类
     * @return ObservableList<shellName>
     */
    private ObservableList<shellName> getAllShellName() {
        ObservableList<shellName> shellNames = FXCollections.observableArrayList();
        for (ReverseShellBeans.ReverseShellBean shellBean : reverseShellGenerator.getReverseShellBeans().getData()) {
            shellNames.add(new shellName(shellBean.getName()));
        }
        return shellNames;
    }

    private ObservableList<shellName> getFilterShellName(String fitter) {
        if (fitter.equals("all")) {
            return getAllShellName();
        }
        ObservableList<shellName> shellNames = FXCollections.observableArrayList();
        for (ReverseShellBeans.ReverseShellBean shellBean : reverseShellGenerator.getReverseShellBeans().getData()) {
//            System.out.println(Arrays.toString(shellBean.getMeta()).toLowerCase());
            if (Arrays.toString(shellBean.getMeta()).toLowerCase().contains(fitter.toLowerCase())) {
                shellNames.add(new shellName(shellBean.getName()));
            }
        }
        return shellNames;
    }

    /**
     * shellname实体类
     */
    private static final class shellName extends RecursiveTreeObject<shellName> {
        final StringProperty shellNames;

        shellName(String name) {
            this.shellNames = new SimpleStringProperty(name);
        }
    }

    /**
     * 初始化所有弹出式设置框
     */
    private void initPopupSettings() {
        Image Settingimage = ViewUtils.getImage(ImageListInit.ICON_LIST_SETTING);
        SettingListener = new PopupSettingImageView(AP_Listener, Settingimage,3.0,null,80.0,null);
        SettingPayload = new PopupSettingImageView(AP_Reverseshell, Settingimage,3.0,null,126.0,null);
//        ObservableList<String> OSOptions =
//                FXCollections.observableArrayList(
//                        "all",
//                        "linux" ,
//                        "win" ,
//                        "mac"
//                );
//        JCB_OS = new JFXComboBox<>(OSOptions);
//        JCB_OS.setValue("all");
//        AnchorPane.setTopAnchor(JCB_OS,32.0);
//        AnchorPane.setLeftAnchor(JCB_OS,155.0);
//        ACP_controllerAnchorPane.getChildren().add(JCB_OS);

        ObservableList<String> listenerTypeOptions =
                FXCollections.observableArrayList(
                        "nc" ,"ncat" ,"ncat (TLS)" ,"rlwrap + nc" ,"rc" ,
                        "rc + Command History" ,"pwncat" ,"windows ConPty" ,"socat" ,
                        "socat (TTY)" ,"powercat" ,"msfconsole","Openssl"
                );
        JCB_ListenerType = new JFXComboBox<>(listenerTypeOptions);
        JCB_ListenerType.setValue("nc");
        JCB_ListenerType.setOnAction(actionEvent -> ONListenerTypeChange());
        SettingListener.setSetting(new PopupSettingNode("ListenerType",JCB_ListenerType,210.0,true));

        ObservableList<String> ShellOptions =
                FXCollections.observableArrayList(
                        "sh","/bin/sh","bash","/bin/bash","cmd","powershell",
                        "pwsh","ash","bsh","csh","ksh","zsh","pdksh","tcsh"
                );
        JCB_Shell = new JFXComboBox<>(ShellOptions);
        JCB_Shell.setValue("sh");
        JCB_Shell.setOnAction(actionEvent -> ONShellChange());
        SettingPayload.setSetting(new PopupSettingNode("Shell",JCB_Shell,true));


    }

    /**
     * 当设置被切换时执行的输出修改
     */
    private void setShellAndListener() {
        new Thread(() -> {
            String listener = reverseShellGenerator.getListener(reverseShellDataBean.getListenerType())
                    .replace("{port}",reverseShellDataBean.getPort())
                    .replace("{ip}",reverseShellDataBean.getIp());
            String reverseshell = reverseShellGenerator.getReverseShell(reverseShellDataBean.getShellType())
                    .replace("{port}",reverseShellDataBean.getPort())
                    .replace("{ip}",reverseShellDataBean.getIp())
                    .replace("{shell}",reverseShellDataBean.getShell());

            Platform.runLater(() -> {
                JTA_Listener.setText(listener);
                JTA_Reverseshell.setText(reverseshell);
            });
        }).start();
    }
}
