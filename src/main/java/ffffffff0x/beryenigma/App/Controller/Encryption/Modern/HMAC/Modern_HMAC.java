package ffffffff0x.beryenigma.App.Controller.Encryption.Modern.HMAC;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.Coding_Base64;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.HEXCoder.Coding_HEXCoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

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
    public static byte[] encryptHMAC(byte[] data, byte[] key, String keyMAC) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey;
        byte[] bytes = null;
        secretKey = new SecretKeySpec(key, keyMAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        bytes = mac.doFinal(data);
        return bytes;
    }
}
