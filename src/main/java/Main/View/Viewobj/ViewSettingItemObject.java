package Main.View.Viewobj;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


/**
 * @author: RyuZUSUNC
 * @create: 2021-03-23 20:45
 **/

public class ViewSettingItemObject extends AnchorPane {
    public ViewSettingItemObject(Label titleLabel, Node controller) {
        this.setPrefSize(160,60);

        titleLabel.setStyle("-fx-font-size: 15");
        AnchorPane.setTopAnchor(titleLabel,5.0);
        AnchorPane.setLeftAnchor(titleLabel,0.0);
        AnchorPane.setRightAnchor(titleLabel,0.0);


        AnchorPane.setRightAnchor(controller,0.0);
        AnchorPane.setLeftAnchor(controller,15.0);
        AnchorPane.setBottomAnchor(controller,0.0);
        AnchorPane.setTopAnchor(controller,30.0);

        this.getChildren().add(titleLabel);
        this.getChildren().add(controller);

    }
}
