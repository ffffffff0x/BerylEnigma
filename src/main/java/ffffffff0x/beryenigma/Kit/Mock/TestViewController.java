package ffffffff0x.beryenigma.Kit.Mock;

import com.jfoenix.controls.JFXButton;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Classical.Atbash.AtbashController;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewController;
import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import java.util.Properties;

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
        JTA_src.setText(tClass.getPackageName());
        System.out.println(tClass.getPackageName());

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
            ViewUtils.alertPane(new Stage(),"test","test");
        }catch (Exception e) {
            e.printStackTrace();
        }

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
}
