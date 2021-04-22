package ffffffff0x.beryenigma.App.Controller.Encryption.Modern.Authentication.NTLM;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import ffffffff0x.beryenigma.App.Controller.Encryption.Modern.Hash.Modern_Hash;

import java.io.UnsupportedEncodingException;

public class Authentication_NTLM {
    public static byte[] encrypt(String message){
        try {
            String a = Coding_HEXCoder.encode(message," ","UTF-8").replace(" ","00")+"00";
            String b = Coding_HEXCoder.decode(a,"","UTF-8");
            return Modern_Hash.hash(b.getBytes(),"MD4");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
