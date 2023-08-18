package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption.BlockCipher;

import ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption.BlockCipher.BlockCipherParameters;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: RyuZUSUNC
 * @create: 2021/12/23 20:11
 **/
public class BlockCipherImpl {
    public static BlockCipherParameters encrypt(BlockCipherParameters blockCipherParameters) throws Exception {
        String cipherInstance = blockCipherParameters.getAlgorithm() + "/" + blockCipherParameters.getEncryptionMode() + "/" + blockCipherParameters.getPaddingMode();
        if (blockCipherParameters.getEncryptionMode().equals("ECB")) {
            blockCipherParameters.setMessageOutput(encrypt(blockCipherParameters.getKey(),
                    null,
                    blockCipherParameters.getMessageInput(),
                    cipherInstance));
        }else {
            blockCipherParameters.setMessageOutput(encrypt(blockCipherParameters.getKey(),
                    blockCipherParameters.getIv(),
                    blockCipherParameters.getMessageInput(),
                    cipherInstance));
        }
        return blockCipherParameters;
    }

    public static BlockCipherParameters decrypt(BlockCipherParameters blockCipherParameters) throws Exception {
        String cipherInstance = blockCipherParameters.getAlgorithm() + "/" + blockCipherParameters.getEncryptionMode() + "/" + blockCipherParameters.getPaddingMode();
        if (blockCipherParameters.getEncryptionMode().equals("ECB")) {
            blockCipherParameters.setMessageOutput(decrypt(blockCipherParameters.getKey(),
                    null,
                    blockCipherParameters.getMessageInput(),
                    cipherInstance));
        }else {
            blockCipherParameters.setMessageOutput(decrypt(blockCipherParameters.getKey(),
                    blockCipherParameters.getIv(),
                    blockCipherParameters.getMessageInput(),
                    cipherInstance));
        }
        return blockCipherParameters;
    }

    // 加密:
    private static byte[] encrypt(byte[] key, byte[] iv, byte[] input, String cipherInstance) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherInstance);
        SecretKeySpec keySpec = new SecretKeySpec(key, cipherInstance.split("/")[0]);
        if (iv == null) {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        }else {
            IvParameterSpec ivps = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        }
        return cipher.doFinal(input);
    }

    // 解密:
    private static byte[] decrypt(byte[] key, byte[] iv, byte[] input, String cipherInstance) throws Exception {
        // 解密:
        Cipher cipher = Cipher.getInstance(cipherInstance);
        SecretKeySpec keySpec = new SecretKeySpec(key, cipherInstance.split("/")[0]);
        if (iv == null) {
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
        }else {
            IvParameterSpec ivps = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        }
        return cipher.doFinal(input);
    }
}
