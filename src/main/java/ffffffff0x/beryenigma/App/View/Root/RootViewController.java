package ffffffff0x.beryenigma.App.View.Root;

import ffffffff0x.beryenigma.Init.Init;
import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

public class RootViewController {
    RootTreeNode rootTreeNode = new RootTreeNode();

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

    AnchorPane selectedAnchorPane = null;

    private int testnum;

    @FXML
    private void initialize() {
        RootTree.setRoot(rootTreeNode.rootItem);
        setImage();
    }

    @FXML
    private void checkView() {
        try{
            //如果选中节点是叶子节点才进行pane切换
            if ((RootTree.getSelectionModel().getSelectedItem()).isLeaf()) {
                if(!"".equals(rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem()))){
                    ////显示选择的页面
                    //FXML布局加载器
                    FXMLLoader loader = new FXMLLoader();
                    //根据路径加载布局
                    loader.setLocation(RootViewController.class.getResource(rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem())));
                    //加载语言配置文件
                    loader.setResources(Init.languageResourceBundle);
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
            //e.printStackTrace();
        }
    }

    @FXML
    private void openURL(){
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                // 创建一个URI实例
                java.net.URI uri = java.net.URI.create("https://github.com/ffffffff0x/BerylEnigma");
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    // 获取系统默认浏览器打开链接
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setImage() {
        IV_Logo.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/ffffffff0x_Logo.png"))));
        IV_Github.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/img/github-fill.png"))));
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
            loader.setResources(Init.languageResourceBundle);
            try {
                selectedAnchorPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            borderPane.setCenter(selectedAnchorPane);
        }
    }
}