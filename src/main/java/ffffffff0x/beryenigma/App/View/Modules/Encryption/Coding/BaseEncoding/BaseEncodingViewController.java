package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import ffffffff0x.beryenigma.App.View.Viewobj.ViewControllerFileMode;
import ffffffff0x.beryenigma.Init.Init;
import ffffffff0x.beryenigma.Init.ViewInit;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;
import javafx.fxml.FXML;

import java.util.Objects;

/**
 * @author RyuZU
 */
public abstract class BaseEncodingViewController extends ViewControllerFileMode {

    @FXML protected JFXComboBox JCB_charset;
    @FXML protected JFXTextField JTF_split;

    @Override
    protected void initialize() {
        super.initialize();
        super.getByteFileOnDrag();
        ViewInit.comboBoxCharset(JCB_charset);
    }

    @Override
    public void ONClickEncode() {
        super.ONClickEncode();
        onClickEncodeImpl();
    }

    @Override
    public void ONClickDecode() {
        super.ONClickDecode();
        onClickDecodeImpl();
    }

    @Override
    public void getFile(){
        super.getFile();
        byteFile = FileUtils.getFilebyte(file);
    }

    protected void onClickEncodeImpl() {
        try {
            //判断是否为文本模式
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                //判断是否使用分隔符
                if(!Objects.equals(JTF_split.getText(), "")){
                    JTA_dst.setText(encodeSplitToString());
                }else{
                    JTA_dst.setText(encodeToString());
                }
            }else{
                //文件模式
                encodeToFile();
                fileEncodeEnd();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    protected void onClickDecodeImpl() {
        try {
            if(JTB_modeSelect.getText().equals(Init.languageResourceBundle.getString("TextMode"))){
                if(!Objects.equals(JTF_split.getText(), "")){
                    JTA_dst.setText(decodeSplitToString());
                }else{
                    JTA_dst.setText(decodeToString());
                }
            }else{
                decodeToFile();
                fileEncodeEnd();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            ViewUtils.textAreaValidate(JTA_dst);
        }
    }

    protected abstract String encodeSplitToString();
//        return Coding_Base64.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText());

    protected abstract String decodeSplitToString();
//        return Coding_Base64.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),JTF_split.getText());

    protected abstract String encodeToString();
//        return Coding_Base64.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());

    protected abstract String decodeToString();
//        return Coding_Base64.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());

    protected abstract void encodeToFile();
//        FileUtils.outPutFile(Coding_Base64.encode(file));

    protected abstract void decodeToFile();
//        FileUtils.outPutFile(Coding_Base64.decode(file));

}
