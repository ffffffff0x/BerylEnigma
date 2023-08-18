package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.HMAC;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-07-15 14:44
 **/

public class HMACImpl {
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
