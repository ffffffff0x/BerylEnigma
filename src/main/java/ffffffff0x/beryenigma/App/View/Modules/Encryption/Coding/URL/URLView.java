package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.URL;

import ffffffff0x.beryenigma.App.View.Viewobj.ControllerView;
import com.jfoenix.controls.*;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;


/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2025-03-11 13:57
 **/

public class URLView extends ControllerView {
    private JFXComboBox<String> JCB_charset;
    private JFXCheckBox JCB_encodeAll;

    @Override
    protected void initializeUI() {
        super.initializeUI();

        JLB_title.setText("URL");

        JTA_src = new JFXTextArea();
        JTA_src.setPrefSize(530, 165);
        AnchorPane.setTopAnchor(JTA_src, 30.0);
        AnchorPane.setLeftAnchor(JTA_src, 40.0);
        AnchorPane.setRightAnchor(JTA_src, 40.0);

        JTA_dst = new JFXTextArea();
        JTA_dst.setEditable(false);
        JTA_dst.setPrefSize(530, 165);
        AnchorPane.setTopAnchor(JTA_dst, 315.0);
        AnchorPane.setLeftAnchor(JTA_dst, 40.0);
        AnchorPane.setRightAnchor(JTA_dst, 40.0);
        AnchorPane.setBottomAnchor(JTA_dst, 37.0);

        JBT_enCode = new JFXButton(Init.getLanguage("EnCode"));
        JBT_enCode.setPrefSize(101,50);

        JBT_deCode = new JFXButton(Init.getLanguage("DeCode"));
        JBT_deCode.setPrefSize(101,50);

        JCB_charset = new JFXComboBox<>();
        JCB_charset.setPrefSize(100,30);

        JCB_encodeAll = new JFXCheckBox(Init.getLanguage("EnCodeAll"));

        HBox hbox = new HBox(30, JBT_enCode, JCB_charset, JCB_encodeAll, JBT_deCode);
        hbox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(hbox, 230.0);
        AnchorPane.setLeftAnchor(hbox, 40.0);
        AnchorPane.setRightAnchor(hbox, 40.0);

        ACP_controllerAnchorPane.getChildren().addAll(JTA_src, JTA_dst, hbox);

        ViewInit.comboBoxCharset(JCB_charset);
        ViewInit.textAreaErrorInfoGeneral(JTA_dst);

        JBT_enCode.setOnAction(e -> ONClickEncode());
        JBT_deCode.setOnAction(e -> ONClickDecode());
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        try {
            if (JCB_encodeAll.isSelected()) {
                JTA_dst.setText(URLImpl.encodeAll(JTA_src.getText(),JCB_charset.getValue().toString()));
            }else {
                JTA_dst.setText(URLImpl.encode(JTA_src.getText(),JCB_charset.getValue().toString()));
            }
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        try {
            JTA_dst.setText(URLImpl.decode(JTA_src.getText(),JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }
}
