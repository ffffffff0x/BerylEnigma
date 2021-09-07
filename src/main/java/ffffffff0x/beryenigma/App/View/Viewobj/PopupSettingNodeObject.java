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

public abstract class PopupSettingNodeObject extends AnchorPane {
    public Label title = new Label();
    public AnchorPane controlAnchor = new AnchorPane();
    public final Separator separator = new Separator();

    public PopupSettingNodeObject(String titleName, Node controlNode) {
        super();
        this.setPrefSize(160,80);
        initAnchor();
        title.setText(titleName);
        this.getChildren().add(title);
        this.getChildren().add(controlAnchor);
        this.getChildren().add(separator);
        ViewUtils.setAnchor(controlNode,5.0,20.0,20.0,15.0);
        controlAnchor.getChildren().add(controlNode);
    }

    private void initAnchor() {
        ViewUtils.setAnchor(title,10.0,10.0,10.0,60.0);
        ViewUtils.setAnchor(controlAnchor,35.0,10.0,10.0,5.0);
        AnchorPane.setLeftAnchor(separator,10.0);
        AnchorPane.setRightAnchor(separator,10.0);
        title.setStyle("-fx-font-size: 14");
    }
}
