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
            if(!"".equals(rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem()))){
                ////显示选择的页面
                //FXML布局加载器
                FXMLLoader loader = new FXMLLoader();
                //根据路径加载布局
                loader.setLocation(RootViewController.class.getResource(rootTreeNode.nodeMap.get(RootTree.getSelectionModel().getSelectedItem())));
                //加载语言配置文件
                loader.setResources(Init.languageResourceBundle);
                AnchorPane ap = null;
                try {
                    ap = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                borderPane.setCenter(ap);
                try {
                    RootTree.getSelectionModel().select(RootTree.getSelectionModel().getSelectedItem().getParent());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}