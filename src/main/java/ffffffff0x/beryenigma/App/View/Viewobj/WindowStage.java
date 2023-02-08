package ffffffff0x.beryenigma.App.View.Viewobj;

import ffffffff0x.beryenigma.Init.ImageListInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-08 11:45
 **/

public class WindowStage extends Stage {
    /**
     * 使用此构造方法新建一个新的窗口并显示
     *
     * @param stageName 窗口名称
     * @param height 窗口高度
     * @param width 窗口宽度
     * @param pane 要载入窗口的pane
     */
    public WindowStage(String stageName, Double height, Double width, Pane pane) {
        this.setTitle(stageName);
        this.getIcons().add(ViewUtils.getImage(ImageListInit.ICON));
        Scene scene = new Scene(pane, width, height);
        // 设置CSS样式
        ViewUtils.setCSSStyle(scene);
        this.setScene(scene);
        this.show();
    }

    /**
     * 使用此构造方法新建一个新的窗口并显示
     *
     * @param stageName 窗口名称
     * @param height 窗口高度
     * @param width 窗口宽度
     * @param pane 要载入窗口的pane
     */
    public WindowStage(String stageName, Double height, Double width, Pane pane, Image icon) {
        this.setTitle(stageName);
        this.getIcons().add(icon);
        Scene scene = new Scene(pane, width, height);
        // 设置CSS样式
        ViewUtils.setCSSStyle(scene);
        this.setScene(scene);
        this.show();
    }
}
