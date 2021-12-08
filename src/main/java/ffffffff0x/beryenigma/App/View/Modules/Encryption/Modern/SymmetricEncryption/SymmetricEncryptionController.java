package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingView;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    ArrayList<algName> algs;

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
        algs = new Gson().fromJson(jsonData, ArrayList.class);

        ObservableList<String> algorithms = FXCollections.observableArrayList("AES", "ARIA", "Blowfish", "CAST5", "CAST6", "Camellia", "DES", "DESEDE", "DSTU7624", "IDEA", "RC2", "RC5", "RC6", "SEED", "SM4", "Serpent", "Skipjack", "TEA", "Threefish-1024", "Threefish-256", "Threefish-512", "Twofish", "XTEA");

        JCB_algorithm.getItems().setAll(algorithms);
        JCB_algorithm.setValue("AES");
    }
}
