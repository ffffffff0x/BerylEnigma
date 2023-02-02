package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.ConfigListInit;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.Init;
import com.jfoenix.controls.JFXButton;
import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import ffffffff0x.beryenigma.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;

public class RootViewController {
//    RootTreeNode rootTreeNode;
    AutoRootTreeNode autoRootTreeNode;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane indexpane;
    @FXML
    private AnchorPane treePane;
    @FXML
    private TreeView<String> RootTree;
    @FXML
    private JFXButton bt_option;
    @FXML
    private ImageView IV_Logo;
    @FXML
    private ImageView IV_Github;
    @FXML
    private JFXButton JBT_GithubLink;
    @FXML
    private JFXButton JBT_StyleChange;
    private int styleMode;

    AnchorPane selectedAnchorPane = null;

    private int testnum;

    @FXML
    private void initialize() {
//        rootTreeNode = new RootTreeNode();
        autoRootTreeNode = new AutoRootTreeNode();
        RootTree.setRoot(autoRootTreeNode.rootItem);
        setImage();
        changeStyle();
    }

    @FXML
    private void checkView() {
        try{
            if ((RootTree.getSelectionModel().getSelectedItem()) != null && RootTree.getSelectionModel().getSelectedItem().getValue().equals(Init.getLanguage("Root"))) {
                borderPane.setCenter(indexpane);
            }
            //如果选中节点是叶子节点才进行pane切换
            if ((RootTree.getSelectionModel().getSelectedItem()) != null && (RootTree.getSelectionModel().getSelectedItem()).isLeaf()) {
                if(!"".equals(autoRootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem()))){
                    ////显示选择的页面
                    //FXML布局加载器
                    FXMLLoader loader = new FXMLLoader();
                    //根据路径加载布局
                    loader.setLocation(RootViewController.class.getResource(autoRootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem())));
                    //加载语言配置文件
                    loader.setResources(Init.getLanguageResourceBundle());
                    try {
                        selectedAnchorPane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    borderPane.setCenter(selectedAnchorPane);
                    try {
                        RootTree.getSelectionModel().select(RootTree.getSelectionModel().getSelectedItem().getParent());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void openURL(){
        ViewUtils.openURLWithBrowser(JBT_GithubLink, "https://github.com/ffffffff0x/BerylEnigma");
    }

    private void setImage() {
        // 设置团队LOGO
        IV_Logo.setImage(ViewUtils.getImage(ImageListInit.LOGO));
        // 设置github图标
        IV_Github.setImage(ViewUtils.getImage(ImageListInit.ICON_GITHUB));
    }

    @FXML
    private void test(){
        testnum++;
//        System.out.println(testnum);
        if (testnum > 10) {
            testnum = 0;
            //FXML布局加载器
            FXMLLoader loader = new FXMLLoader();
            //根据路径加载布局
            loader.setLocation(RootViewController.class.getResource("/ffffffff0x/beryenigma/Kit/Mock/TestView.fxml"));
            //加载语言配置文件
            loader.setResources(Init.getLanguageResourceBundle());
            try {
                selectedAnchorPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            borderPane.setCenter(selectedAnchorPane);
        }
    }

    private void changeStyle() {
        if (Init.getConfig(ConfigListInit.AppStyle).equals("dark")) {
            styleMode = 2;
        } else {
            styleMode = 3;
        }
        JBT_StyleChange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (styleMode == 100) {
                    styleMode = 2;
                }
                if (styleMode % 2 == 0) {
//                    System.out.println( "Stylemode:" + styleMode);
                    borderPane.getScene().getStylesheets().clear();
                    borderPane.getScene().getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_light.css")).toExternalForm());
                    ConfigUtils.editConfigFile(ConfigListInit.AppStyle,"light");
                    setImage();
                    JBT_StyleChange.setText(Init.getLanguage("LightMode"));
                    autoRootTreeNode = new AutoRootTreeNode();
                    RootTree.setRoot(autoRootTreeNode.rootItem);

                    styleMode ++;
                }else {
//                    System.out.println( "Stylemode:" + styleMode);
                    borderPane.getScene().getStylesheets().clear();
                    borderPane.getScene().getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/MainCSS_dark.css")).toExternalForm());
                    ConfigUtils.editConfigFile(ConfigListInit.AppStyle,"dark");
                    if (styleMode % 11 == 0) {
                        IV_Logo.setImage(ViewUtils.getImage(ImageListInit.LOGO_REDEYE));
                        IV_Github.setImage(ViewUtils.getImage(ImageListInit.ICON_GITHUB));
                        JBT_StyleChange.setText("???Mode");
                    } else {
                        setImage();
                        JBT_StyleChange.setText(Init.getLanguage("DarkMode"));
                    }
                    autoRootTreeNode = new AutoRootTreeNode();
                    RootTree.setRoot(autoRootTreeNode.rootItem);
                    styleMode ++;
                }
            }
        });
    }
}