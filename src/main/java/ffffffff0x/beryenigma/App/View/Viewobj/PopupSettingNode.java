package ffffffff0x.beryenigma.App.View.Viewobj;

import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

/**
 * PopupSettingView中的每一个node
 * @author: RyuZUSUNC
 * @create: 2021-05-27 13:43
 **/

public class PopupSettingNode extends AnchorPane {
    public Label settingNodeTitle = new Label();
    public AnchorPane controlAnchor = new AnchorPane();
    public final Separator separator = new Separator();

    public PopupSettingNode(String titleName, Node controlNode, boolean isFirst) {
        super();
        this.setPrefSize(160,80);
        initAnchor();
        ViewUtils.setAnchor(controlNode,5.0,5.0,5.0,15.0);
        settingNodeTitle.setText(titleName);
        this.getChildren().add(settingNodeTitle);
        this.getChildren().add(controlAnchor);
        if (!isFirst) {
            this.getChildren().add(separator);
        }
        controlAnchor.getChildren().add(controlNode);
    }

    public PopupSettingNode(String titleName, Node controlNode) {
        super();
        this.setPrefSize(160,80);
        initAnchor();
        ViewUtils.setAnchor(controlNode,5.0,5.0,5.0,15.0);
        settingNodeTitle.setText(titleName);
        this.getChildren().add(settingNodeTitle);
        this.getChildren().add(controlAnchor);
        this.getChildren().add(separator);
        controlAnchor.getChildren().add(controlNode);
    }

    public PopupSettingNode(String titleName, Node controlNode,Double prefWidth, boolean isFirst) {
        super();
        this.setPrefSize(prefWidth,80);
        initAnchor();
        ViewUtils.setAnchor(controlNode,5.0,5.0,5.0,15.0);
        settingNodeTitle.setText(titleName);
        this.getChildren().add(settingNodeTitle);
        this.getChildren().add(controlAnchor);
        if (!isFirst) {
            this.getChildren().add(separator);
        }
        controlAnchor.getChildren().add(controlNode);
    }

    public PopupSettingNode(String titleName, Node controlNode,Double prefWidth) {
        super();
        this.setPrefSize(prefWidth,80);
        initAnchor();
        ViewUtils.setAnchor(controlNode,5.0,5.0,5.0,15.0);
        settingNodeTitle.setText(titleName);
        this.getChildren().add(settingNodeTitle);
        this.getChildren().add(controlAnchor);
        this.getChildren().add(separator);
        controlAnchor.getChildren().add(controlNode);
    }


    private void initAnchor() {
        ViewUtils.setAnchor(settingNodeTitle,10.0,10.0,10.0,60.0);
        ViewUtils.setAnchor(controlAnchor,35.0,10.0,10.0,5.0);
        AnchorPane.setLeftAnchor(separator,10.0);
        AnchorPane.setRightAnchor(separator,10.0);
        settingNodeTitle.setStyle("-fx-font-size: 14");
    }
}
