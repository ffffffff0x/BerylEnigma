package ffffffff0x.beryenigma.Kit.Mock;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.Atbash.AtbashController;
import ffffffff0x.beryenigma.App.View.Modules.Tools.RedTeam.ReverseShellGenerator.ReverseShellGeneratorController;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.App.View.Viewobj.WindowStage;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.Provider;
import java.security.Security;

public class TestViewController extends ViewController {

    private VBox vBoxDst;

    /**
     * 全局界面初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        setTextareaOnDrag();

        vBoxDst = ViewUtils.getLittleButtonVBoxDst(ACP_controllerAnchorPane);
        JFXButton t = new JFXButton();
        ViewUtils.setLittleButtonToVBox(vBoxDst, t, ImageListInit.ICON_LJBT_IE);

        JTA_src.setText(System.getProperty("user.home") + "\n" + System.getProperty("user.dir") + "\n"
        + TestViewController.class.getPackageName());

        Class tClass = AtbashController.class;
//        JTA_src.setText(tClass.getPackageName());
//        System.out.println(tClass.getPackageName());

        StringBuilder a = new StringBuilder();
        for (Provider o : Security.getProviders()) {
            a.append(o.getName()).append("\n");
        }
        JTA_dst.setText(a.toString());
        ViewInit.textAreaErrorInfoGeneral(JTA_src);
        JTA_src.setTextFormatter(new TextFormatter<String>(change -> {
            System.out.println(change.getText());//获取文本
            String value = change.getText();
            if (value.matches("[A-Z]") || value.length() == 0){//限制用户输入，使用正则表达式很重要
                return change;
            }
            ViewUtils.alertPane(new Stage(),"test","test");
            return null;
        }));
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        System.out.println("Test");
        ViewUtils.alertPane((Stage)JLB_title.getScene().getWindow(),"test","test");
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            System.out.println("123123");
//            ViewUtils.alertPane(new Stage(),"test","test");
        }catch (Exception e) {
            e.printStackTrace();
        }
        newViewTest();
    }

    protected void setTextareaOnDrag() {
//        JTA_src.setOnDragOver(dragEvent -> {
//            if (dragEvent.getGestureSource() != JTA_src) {
//                dragEvent.acceptTransferModes(TransferMode.ANY);
//            }
//        });

//        JTA_src.setOnDragDropped(dragEvent -> {
//            Dragboard dragboard = dragEvent.getDragboard();
//            List<File> files = dragboard.getFiles();
//            if(files.size() > 0){
//                JTA_dst.setText(files.get(0).getPath());
//            }
//        });
    }

    protected void newViewTest() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("ACP_backgroundAnchorPane");

//        TreeTableView<test> treeTableView = new TreeTableView<>();
//
//        TreeTableColumn<test, String> treeTableColumn1 = new TreeTableColumn<>("TE1");
//        TreeTableColumn<test, String> treeTableColumn2 = new TreeTableColumn<>("TE2");
//
//        treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("te1"));
//        treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("te2"));
//
//        treeTableView.getColumns().add(treeTableColumn1);
//        treeTableView.getColumns().add(treeTableColumn2);

        JFXTreeTableView<test> treeTableView;
        ObservableList<test> tol = FXCollections.observableArrayList();
        tol.add(new test("t1","t1"));
        tol.add(new test("t2","t2"));
        tol.add(new test("t3","t3"));
        tol.add(new test("t4","t4"));
        TreeItem<test> root = new RecursiveTreeItem<>(tol, RecursiveTreeObject::getChildren);
        treeTableView = new JFXTreeTableView<>(root);
        treeTableView.setShowRoot(false);
        JFXTreeTableColumn<test, String> testName = new JFXTreeTableColumn<>("Test");

        treeTableView.getColumns().setAll(testName);

        anchorPane.getChildren().add(treeTableView);
        AnchorPane.setLeftAnchor(treeTableView,0.0);
        AnchorPane.setRightAnchor(treeTableView,0.0);
        AnchorPane.setTopAnchor(treeTableView,0.0);
        AnchorPane.setBottomAnchor(treeTableView,0.0);

        new WindowStage("Test",800.0,600.0,anchorPane);
    }
}
