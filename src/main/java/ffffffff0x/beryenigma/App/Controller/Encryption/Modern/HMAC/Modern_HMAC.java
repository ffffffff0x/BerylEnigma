package ffffffff0x.beryenigma.App.Controller.Encryption.Modern.HMAC;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base64;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-07-15 14:44
 **/

public class Modern_HMAC {
    /**
     * HMAC加密
     * @param data 需要加密的字节数组
     * @param key 密钥
     * @param keyMAC 加密方式
     * @return 字节数组
     */
    public static byte[] encryptHMAC(byte[] data, String key, String keyMAC) {
        SecretKey secretKey;
        byte[] bytes = null;
        try {
            secretKey = new SecretKeySpec(Base64.decodeBase64(key), keyMAC);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * HMAC加密
     * @param data 需要加密的字符串
     * @param key 密钥
     * @param keyMAC 加密方式
     * @return 字符串
     */
    public static String encryptHMAC(String data, String key, String keyMAC) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        byte[] bytes = encryptHMAC(data.getBytes(), key, keyMAC);
        return Coding_HEXCoder.encodeToString(bytes);
    }

    public static void main(String[] args) throws Exception {
        String key = "123";
        String word = "123";
        System.out.println(encryptHMAC(word, Coding_Base64.encodeToString(key,"UTF-8"), "HMACSHA512/256"));
    }
}
