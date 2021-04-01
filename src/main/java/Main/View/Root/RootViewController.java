package Main.View.Root;

import Init.Init;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class RootViewController {
    RootTreeNode rootTreeNode = new RootTreeNode();

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane indexpane;

    @FXML
    private TreeView<String> RootTree;

    @FXML
    private JFXButton bt_option;

    AnchorPane selectedAnchorPane = null;

    @FXML
    private void initialize() {
        RootTree.setRoot(rootTreeNode.rootItem);
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

    @FXML
    private void test(){
        Image img = new Image("/img/icon_book-2-line.png");
        ImageView view = new ImageView(img);
        bt_option.setGraphic(view);

    }
}