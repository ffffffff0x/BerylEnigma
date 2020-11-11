package View.Root;

import Init.Init;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    private void initialize() {
        RootTree.setRoot(rootTreeNode.rootItem);
    }

    @FXML
    private void checkView() {
        //如果选中节点是叶子节点才进行pane切换
        if ((RootTree.getSelectionModel().getSelectedItem()).isLeaf()) {
            if(!rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem()).equals("")){
                Thread checkView = new Thread(() -> {
                    ////显示选择的页面
                    FXMLLoader loader = new FXMLLoader();//FXML布局加载器
                    loader.setLocation(RootViewController.class.getResource(rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem())));//根据路径加载布局
                    loader.setResources(Init.languageResourceBundle);//加载语言配置文件
                    AnchorPane ap = null;
                    try {
                        ap = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    borderPane.setCenter(ap);
                    RootTree.getSelectionModel().select(0);
                });//启动一个新线程切换节点对应的pane
                checkView.run();//线程运行
            }
        }
    }
}