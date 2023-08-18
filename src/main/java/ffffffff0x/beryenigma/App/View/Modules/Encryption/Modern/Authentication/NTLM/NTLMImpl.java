package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Authentication.NTLM;

import ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.HEXCoder.HEXCoderImpl;
import ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Hash.HashImpl;

import java.io.UnsupportedEncodingException;

public class NTLMImpl {
    public static byte[] encrypt(String message){
        try {
            String a = HEXCoderImpl.encode(message," ","UTF-8").replace(" ","00")+"00";
            String b = HEXCoderImpl.decode(a,"","UTF-8");
            return HashImpl.hash(b.getBytes(),"MD4");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
