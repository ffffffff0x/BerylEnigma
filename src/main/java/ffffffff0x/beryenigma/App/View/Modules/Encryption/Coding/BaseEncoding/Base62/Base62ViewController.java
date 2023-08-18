package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base62;

import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.BaseEncodingViewController;
import ffffffff0x.beryenigma.Kit.Utils.ViewNode;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/11/22 21:19
 **/
@ViewNode(name = "Base62",folderPath = "Root/Encryption/Coding/BaseEncoding/",fxmlName = "Base62View.fxml")
public class Base62ViewController extends BaseEncodingViewController {
    @Override
    protected String encodeSplitToString() {
        try {
            return Base62Impl.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            return Base62Impl.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String encodeToString() {
        try {
            return Base62Impl.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeToString() {
        try {
            return Base62Impl.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected byte[] encodeToFile() {
        return Base62Impl.encode(byteFile);
    }

    @Override
    protected byte[] decodeToFile() {
        return Base62Impl.decode(byteFile);
    }
}
