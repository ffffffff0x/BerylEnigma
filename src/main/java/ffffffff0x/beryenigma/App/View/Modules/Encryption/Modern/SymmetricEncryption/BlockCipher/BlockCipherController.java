package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption.BlockCipher;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base64;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingDoubleColumnView;
import ffffffff0x.beryenigma.App.View.Viewobj.PopupSettingNode;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: RyuZUSUNC
 * @create: 2021/12/6 21:12
 **/
public class BlockCipherController extends ViewControllerFileMode {
    //加密方式
    @FXML JFXComboBox<String> JCB_algorithm;
    //密钥
    @FXML JFXTextField JTF_key;
    //IV
    JFXTextField JTF_iv = new JFXTextField();

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
        //弹出式控件框
        PopupSettingDoubleColumnView popupSettingView = new PopupSettingDoubleColumnView(ACP_controllerAnchorPane);

        //初始化控件
        initControl();

        //弹出式控件框中添加初始化完的控件
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("IV"),JTF_iv,true));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("EncryptionMode"),JCB_encryptionMode,true));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("PaddingMode"),JCB_paddingMode));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("TextEncoding"),JCB_textEncoding));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("InputFormat"),JCB_inputFormat));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("KeyFormat"),JCB_keyFormat));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("IVFormat"),JCB_ivFormat));
        popupSettingView.setSetting(new PopupSettingNode(Init.languageResourceBundle.getString("OutputFormat"),JCB_outputFormat));

    }

    //根据对称加密类型获取加密方式
    private ObservableList<String> getEncryptionMode(String algorithm,LinkedHashMap<String,LinkedHashMap<String,ArrayList<String>>> algs) {
        LinkedHashMap encryptionModes = gson.fromJson(String.valueOf(algs.get(algorithm)), LinkedHashMap.class);
        return FXCollections.observableArrayList(encryptionModes.keySet());
    }

    //根据对称加密类型和加密方式获取填充模式
    private ObservableList<String> getPaddingMode(String algorithm,String encryptionMode,LinkedHashMap<String,LinkedHashMap<String,ArrayList<String>>> algs) {
        LinkedHashMap encryptionModes = gson.fromJson(String.valueOf(algs.get(algorithm)), LinkedHashMap.class);
        ArrayList paddingMode = gson.fromJson(String.valueOf(encryptionModes.get(encryptionMode)),ArrayList.class);
        return FXCollections.observableArrayList(paddingMode);
    }

    //初始化控件
    private void initControl() {
        //反序列化一个所有加密选项的实体
        String jsonData = new BufferedReader(new InputStreamReader(Objects.requireNonNull(BlockCipherController.class.getResourceAsStream("/json/SymmetricEncryptionAlg.json"))))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        algs = new Gson().fromJson(jsonData, LinkedHashMap.class);
        //构建对称加密类型的选项
        ObservableList<String> algorithms = FXCollections.observableArrayList("AES", "ARIA", "Blowfish", "CAST5", "CAST6", "Camellia", "DES", "DESEDE", "DSTU7624", "IDEA", "RC2", "RC5", "RC6", "SEED", "SM4", "Serpent", "Skipjack", "TEA", "Threefish-1024", "Threefish-256", "Threefish-512", "Twofish", "XTEA");
        //设置对称加密类型的单选框
        JCB_algorithm.getItems().setAll(algorithms);
        JCB_algorithm.setValue("AES");

        //设置对称加密类型选中事件
        JCB_algorithm.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            //根据选中的对称加密类型获取加密方式的实体类
            ObservableList<String> temp = getEncryptionMode(t1,algs);
            //设置对称加密方式单选框
            JCB_encryptionMode.getItems().setAll(temp);
            JCB_encryptionMode.setValue(JCB_encryptionMode.getItems().get(0));
        });

        //设置对称加密方式的单选框
        JCB_encryptionMode.getItems().setAll(getEncryptionMode("AES",algs));
        JCB_encryptionMode.setValue("CBC");

        //设置对称加密方式选中事件
        JCB_encryptionMode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null) {
                //根据选中的对称加密方式获取加密填充的实体类
                ObservableList<String> temp = getPaddingMode(JCB_algorithm.getValue(),t1,algs);
                //设置对称加密填充单选框
                JCB_paddingMode.getItems().setAll(temp);
                JCB_paddingMode.setValue(temp.get(0));
            }
        });

        //设置对称加密填充的单选框
        JCB_paddingMode.getItems().setAll(getPaddingMode("AES","CBC",algs));
        JCB_paddingMode.setValue("PKCS5Padding");

        ViewInit.comboBoxCharset(JCB_textEncoding);

        ObservableList<String> textFormat = FXCollections.observableArrayList("Text","Base64","HEX");

        JCB_inputFormat.getItems().setAll(textFormat);
        JCB_keyFormat.getItems().setAll(textFormat);
        JCB_ivFormat.getItems().setAll(textFormat);
        JCB_outputFormat.getItems().setAll(textFormat);

        JCB_inputFormat.setValue("Text");
        JCB_keyFormat.setValue("Text");
        JCB_ivFormat.setValue("Text");
        JCB_outputFormat.setValue("Text");
    }

    //分组密码加密参数
    private BlockCipherParameters getBlockCipherParameters() {
        BlockCipherParameters blockCipherParameters = new BlockCipherParameters();

        blockCipherParameters.setAlgorithm(JCB_algorithm.getValue());
        blockCipherParameters.setEncryptionMode(JCB_encryptionMode.getValue());
        blockCipherParameters.setKey(patameterTransform(JTF_key.getText(),JCB_keyFormat,JCB_textEncoding));
        blockCipherParameters.setIv(patameterTransform(JTF_iv.getText(),JCB_ivFormat,JCB_textEncoding));
        blockCipherParameters.setMessageInput(patameterTransform(JTA_src.getText(),JCB_inputFormat,JCB_textEncoding));
        blockCipherParameters.setPaddingMode(JCB_paddingMode.getValue());
        blockCipherParameters.setTextEncoding(JCB_textEncoding.getValue());

        return blockCipherParameters;
    }

    //输入值转换器获取器
    private byte[] patameterTransform(String msg,JFXComboBox<String> jcb,JFXComboBox<String> textEncode) {
        String msgType = jcb.getValue();
        String msgEncode = textEncode.getValue();
        byte[] msgByte;

        try {
            msgByte = msg.getBytes(msgEncode);
            if (msgType.equals("Text")) {
                return msgByte;
            }else if (msgType.equals("Base64")) {
                return Coding_Base64.decode(msgByte);
            }else if (msgType.equals("HEX")) {
                return Coding_HEXCoder.decode(msgByte);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
