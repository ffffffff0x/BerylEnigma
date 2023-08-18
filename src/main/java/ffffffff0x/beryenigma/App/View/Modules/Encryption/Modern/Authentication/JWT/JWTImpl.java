package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Authentication.JWT;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class JWTImpl {
    public static String[] decode(String source){
        String[] sp = source.split("\\.");
        sp[0] = AddCRLF(new String(Base64.decodeBase64 (sp[0]), StandardCharsets.UTF_8));
        sp[1] = AddCRLF(new String(Base64.decodeBase64 (sp[1]), StandardCharsets.UTF_8));
        sp[2] = sp[2];
        return sp;
    }

    public static String encode(String Headers,String Payload,String Signature) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if(StringLength(Signature)==null)
        {
            return "请输入16位以上密钥或留空";
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append(java.util.Base64.getEncoder().encodeToString(DelSpace(Headers).getBytes()));
            sb.append(".");
            sb.append(java.util.Base64.getEncoder().encodeToString(DelSpace(Payload).getBytes()));
            String Hmac256 = HmacSHA256(sb.toString(),Signature);
            sb.append(".");
            sb.append(Hmac256);
            Hmac256 = sb.toString();
            return Hmac256.replace("=","").replace("+","-").replace("/","_");
        }
    }

    private static String DelSpace(String source) {
        return source.replace("\n","").replace(" ","");
    }

    private static byte[] StringLength(String source) {
        byte[] key = new byte[32];
        if(source.equals(""))
        {
            for(int i=0;i<32;i++)
            {
                key[i]=0;
            }
            return key;
        }
        else if(source.length()>=32)
        {
            return source.getBytes();
        }
        else
        {
            return null;}
    }

    private static String AddCRLF(String source) {
        String[] sp = source.split(",");
        StringBuilder sb = new StringBuilder();
        for (String a:sp
        ) {
            sb.append(a + ",\n");
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append(sb.substring(0,sb.length()-2)).insert(1,"\n").insert(sbf.length()-1,"\n");
        return sbf.toString();
    }

    private static String HmacSHA256(String data,String key) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac HMAC_sha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(StringLength(key), "HmacSHA256");
        HMAC_sha256.init(secret_key);
        byte[] array = HMAC_sha256.doFinal(data.getBytes("UTF-8"));
        //StringBuilder sb = new StringBuilder();
        //for (byte item : array) {
        //    sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        //}
        return java.util.Base64.getEncoder().encodeToString(array);
    }
}
