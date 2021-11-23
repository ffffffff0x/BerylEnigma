package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base16;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base16;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base58;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.BaseEncodingViewController;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.ViewUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/11/22 21:19
 **/
public class Base16ViewController extends BaseEncodingViewController {
    @Override
    protected String encodeSplitToString() {
        try {
            return Coding_Base16.encodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeSplitToString() {
        try {
            return Coding_Base16.decodeSplitToString(JTA_src.getText(),JCB_charset.getValue().toString(),ViewUtils.getSplit(JTF_split));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String encodeToString() {
        try {
            return Coding_Base16.encodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected String decodeToString() {
        try {
            return Coding_Base16.decodeToString(JTA_src.getText(),JCB_charset.getValue().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
        return "";
    }

    @Override
    protected void encodeToFile() {
        try {
            FileUtils.outPutFile(Coding_Base16.encodeToString(file).getBytes(JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }

    @Override
    protected void decodeToFile() {
        try {
            FileUtils.outPutFile((Coding_Base16.decodeToString(file,JCB_charset.getValue().toString())).getBytes(JCB_charset.getValue().toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            JTA_dst.validate();
        }
    }
}
