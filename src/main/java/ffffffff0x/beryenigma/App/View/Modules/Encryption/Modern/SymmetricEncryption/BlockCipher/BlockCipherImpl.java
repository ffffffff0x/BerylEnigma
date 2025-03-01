package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption.BlockCipher;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/12/23 20:11
 **/
public class BlockCipherImpl {
    public static BlockCipherParameters encrypt(BlockCipherParameters params) throws Exception {
        String cipherInstance = generateCipherInstance(params);
        validateIVForNonECB(params);

        byte[] output = processCipher(
                params.getKey(),
                params.getIv(),
                params.getMessageInput(),
                cipherInstance,
                Cipher.ENCRYPT_MODE
        );

        params.setMessageOutput(output);
        return params;
    }

    public static BlockCipherParameters decrypt(BlockCipherParameters params) throws Exception {
        String cipherInstance = generateCipherInstance(params);
        validateIVForNonECB(params);

        byte[] output = processCipher(
                params.getKey(),
                params.getIv(),
                params.getMessageInput(),
                cipherInstance,
                Cipher.DECRYPT_MODE
        );

        params.setMessageOutput(output);
        return params;
    }

    /**
     * 生成完整的Cipher实例字符串
     */
    private static String generateCipherInstance(BlockCipherParameters params) {
        return String.format("%s/%s/%s",
                params.getAlgorithm(),
                params.getEncryptionMode(),
                params.getPaddingMode());
    }

    /**
     * 校验非ECB模式必须提供IV
     */
    private static void validateIVForNonECB(BlockCipherParameters params) {
        if (!"ECB".equals(params.getEncryptionMode())) {
            if (params.getIv() == null) {
                throw new IllegalArgumentException("IV parameter is required for non-ECB modes");
            }
        }
    }

    /**
     * 通用加解密处理方法
     */
    private static byte[] processCipher(byte[] key, byte[] iv, byte[] input, String cipherInstance, int cipherMode)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException,
            javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException {
        Cipher cipher = Cipher.getInstance(cipherInstance);
        SecretKeySpec keySpec = new SecretKeySpec(key, cipherInstance.split("/")[0]);

        if ("ECB".equals(cipherInstance.split("/")[1])) {
            cipher.init(cipherMode, keySpec);
        } else {
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(cipherMode, keySpec, ivSpec);
        }

        return cipher.doFinal(input);
    }
}
