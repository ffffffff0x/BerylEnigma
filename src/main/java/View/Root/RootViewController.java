package View.Root;

import Init.Init;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    }

    @FXML
    private void test(){
        Image img = new Image("/img/icon_book-2-line.png");
        ImageView view = new ImageView(img);
        bt_option.setGraphic(view);

        selectedAnchorPane.getChildren().forEach(ndoe ->ergodicNode(selectedAnchorPane));
    }

    private void ergodicNode(Node node){
        if (node instanceof AnchorPane){
            ((AnchorPane)node).getChildren().forEach(node1 -> {
//                System.out.println(node1);
                ergodicNode(node1);
            });
        }else if(node instanceof HBox){
            ((HBox)node).getChildren().forEach(node2 -> {
//                System.out.println(node2);
                ergodicNode(node2);
            });
        }else if(node instanceof VBox){
            ((VBox)node).getChildren().forEach(node3 -> {
//                System.out.println(node3);
                ergodicNode(node3);
            });
        }else if(node instanceof GridPane){
            ((GridPane)node).getChildren().forEach(node4 -> {
//                System.out.println(node4);
                ergodicNode(node4);
            });
        }else if(node instanceof JFXTextArea && "JTA_src".equals(node.getId())){
            System.out.println(node);
        }
    }
}