package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: RyuZUSUNC
 * @create: 2021/12/6 21:12
 **/
public class SymmetricEncryptionController extends ViewControllerFileMode {
    //加密方式
    @FXML JFXComboBox<String> JCB_algorithm;
    //密钥
    @FXML JFXTextField JTF_key;

    //文本编码
    JFXComboBox<String> JCB_textEncoding = new JFXComboBox<>();
    //加密模式
    JFXComboBox<String> JCB_encryptionMode = new JFXComboBox<>();
    //填充模式
    JFXComboBox<String> JCB_paddingMode = new JFXComboBox<>();
    //输入格式
    JFXComboBox<String> JCB_inputFormat = new JFXComboBox<>();
    //密钥格式
    JFXComboBox<String> JCB_keyFormat = new JFXComboBox<>();
    //iv格式
    JFXComboBox<String> JCB_ivFormat = new JFXComboBox<>();
    //输出格式
    JFXComboBox<String> JCB_outputFormat = new JFXComboBox<>();
    //加密方式的所有组合
    LinkedHashMap algs;

    Gson gson = new Gson();

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
    }

    @Override
    protected void LoadPopupSettingNode() {
        PopupSettingView popupSettingView = new PopupSettingView(ACP_controllerAnchorPane);

        String jsonData = new BufferedReader(new InputStreamReader(Objects.requireNonNull(SymmetricEncryptionController.class.getResourceAsStream("/json/SymmetricEncryptionAlg.json"))))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        algs = new Gson().fromJson(jsonData, LinkedHashMap.class);

        ObservableList<String> algorithms = FXCollections.observableArrayList("AES", "ARIA", "Blowfish", "CAST5", "CAST6", "Camellia", "DES", "DESEDE", "DSTU7624", "IDEA", "RC2", "RC5", "RC6", "SEED", "SM4", "Serpent", "Skipjack", "TEA", "Threefish-1024", "Threefish-256", "Threefish-512", "Twofish", "XTEA");

        JCB_algorithm.getItems().setAll(algorithms);
        JCB_algorithm.setValue("AES");

        JCB_algorithm.setOnAction(actionEvent -> {
            ObservableList<String> temp = getEncryptionMode(JCB_algorithm.getValue(),algs);
            JCB_encryptionMode.getItems().setAll(temp);
            JCB_encryptionMode.setValue(temp.get(0));
//            ObservableList<String> temp2 = getPaddingMode(JCB_algorithm.getValue(),JCB_encryptionMode.getValue(),algs);
//            JCB_paddingMode.getItems().setAll(temp2);
//            JCB_paddingMode.setValue(temp2.get(0));
        });

        JCB_encryptionMode.getItems().setAll(getEncryptionMode("AES",algs));
        JCB_encryptionMode.setValue("CBC");

        JCB_encryptionMode.setOnAction(actionEvent -> {
            ObservableList<String> temp = getPaddingMode(JCB_algorithm.getValue(),JCB_encryptionMode.getValue(),algs);
            JCB_paddingMode.getItems().setAll(temp);
            JCB_paddingMode.setValue(temp.get(0));
            System.out.println(11111);
        });

        JCB_paddingMode.getItems().setAll(getPaddingMode("AES","CBC",algs));
        JCB_paddingMode.setValue("PKCS5Padding");

        popupSettingView.setSetting(new PopupSettingNode("EncryptionMode",JCB_encryptionMode,true));
        popupSettingView.setSetting(new PopupSettingNode("PaddingMode",JCB_paddingMode,false));
    }

    private ObservableList<String> getEncryptionMode(String algorithm,LinkedHashMap<String,LinkedHashMap<String,ArrayList<String>>> algs) {
        LinkedHashMap encryptionModes = gson.fromJson(String.valueOf(algs.get(algorithm)), LinkedHashMap.class);
        return FXCollections.observableArrayList(encryptionModes.keySet());
    }

    private ObservableList<String> getPaddingMode(String algorithm,String encryptionMode,LinkedHashMap<String,LinkedHashMap<String,ArrayList<String>>> algs) {
        LinkedHashMap encryptionModes = gson.fromJson(String.valueOf(algs.get(algorithm)), LinkedHashMap.class);
        ArrayList paddingMode = gson.fromJson(String.valueOf(encryptionModes.get(encryptionMode)),ArrayList.class);
        return FXCollections.observableArrayList(paddingMode);
    }

}
