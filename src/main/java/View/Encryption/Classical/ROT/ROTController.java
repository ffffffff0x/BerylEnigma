package View.Encryption.Classical.ROT;

import Controller.Encryption.Classical.ROT.ClassicalROT;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

public class ROTController {

    @FXML private JFXTextArea JTA_src;
    @FXML private JFXTextArea JTA_dst;
    @FXML private JFXComboBox JCB_rotNum;

    @FXML private void initialize(){
        initComboxSelect();
    };
    @FXML
    public void ONInputAndSelect(){
        JTA_dst.setText(ClassicalROT.encode(JTA_src.getText(), JCB_rotNum.getValue().toString()));
    }

    public void initComboxSelect(){
        JCB_rotNum.getItems().addAll("ROT01",
                "ROT02","ROT03","ROT04","ROT05","ROT06","ROT07","ROT08","ROT09","ROT10","ROT11","ROT12","ROT13","ROT14","ROT15","ROT16","ROT17","ROT18","ROT19","ROT20","ROT21",
                "ROT22","ROT23","ROT24","ROT25"
        );
        JCB_rotNum.setValue("ROT13");
        JCB_rotNum.setVisibleRowCount(6);
    }
}

