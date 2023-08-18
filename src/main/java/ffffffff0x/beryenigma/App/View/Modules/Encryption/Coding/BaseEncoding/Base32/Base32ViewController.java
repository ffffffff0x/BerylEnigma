package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base32;

import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.BaseEncodingViewController;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/11/22 21:38
 **/
@ViewNode(name = "Base32",folderPath = "Root/Encryption/Coding/BaseEncoding/",fxmlName = "Base32View.fxml")
public class Base32ViewController extends BaseEncodingViewController {
    @Override
    protected String encodeSplitToString() {
        try {
            return Base32Impl.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(), ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            return Base32Impl.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String encodeToString() {
        try {
            return Base32Impl.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeToString() {
        try {
            return Base32Impl.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected byte[] encodeToFile() {
        return Base32Impl.encode(byteFile);
    }

    @Override
    protected byte[] decodeToFile() {
        return Base32Impl.decode(byteFile);
    }
}
