package StreamCipherTest.ZUC;

import org.bouncycastle.crypto.engines.Zuc128Engine;
import org.bouncycastle.crypto.engines.Zuc256Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.Security;

/**
 * @author: RyuZUSUNC
 * @create: 2025/3/1 9:24
 **/
public class ZUCExample {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * ZUC-128 加密（生成密钥流并与明文异或）
     */
    public static byte[] zuc128Encrypt(byte[] key, byte[] iv, byte[] plaintext) {
        Zuc128Engine zuc = new Zuc128Engine();
        zuc.init(true, new ParametersWithIV(new KeyParameter(key), iv)); // true 表示加密模式

        byte[] ciphertext = new byte[plaintext.length];
        zuc.processBytes(plaintext, 0, plaintext.length, ciphertext, 0);
        return ciphertext;
    }

    /**
     * ZUC-256 加密（生成密钥流并与明文异或）
     */
    public static byte[] zuc256Encrypt(byte[] key, byte[] iv, byte[] plaintext) {
        Zuc256Engine zuc = new Zuc256Engine();
        zuc.init(true, new ParametersWithIV(new KeyParameter(key), iv)); // true 表示加密模式

        byte[] ciphertext = new byte[plaintext.length];
        zuc.processBytes(plaintext, 0, plaintext.length, ciphertext, 0);
        return ciphertext;
    }

    /**
     * ZUC-128 解密（加密和解密操作相同，均为异或）
     */
    public static byte[] zuc128Decrypt(byte[] key, byte[] iv, byte[] ciphertext) {
        return zuc128Encrypt(key, iv, ciphertext); // 流密码解密即再次异或
    }

    /**
     * ZUC-128 解密（加密和解密操作相同，均为异或）
     */
    public static byte[] zuc256Decrypt(byte[] key, byte[] iv, byte[] ciphertext) {
        return zuc256Encrypt(key, iv, ciphertext); // 流密码解密即再次异或
    }


    public static void main(String[] args) {
        // ZUC-128 参数要求
        byte[] key_128 = Hex.decode("00112233445566778899aabbccddeeff"); // 128-bit (16字节)
        byte[] iv_128 = Hex.decode("00112233445566778899aabbbccddeef");  // 128-bit (16字节)
        byte[] plaintext_128 = "Hello ZUC-128!".getBytes();

        // 加密
        byte[] ciphertext = zuc128Encrypt(key_128, iv_128, plaintext_128);
        System.out.println("Ciphertext: " + Hex.toHexString(ciphertext));

        // 解密
        byte[] decrypted = zuc128Decrypt(key_128, iv_128, ciphertext);
        System.out.println("Decrypted: " + new String(decrypted));

        // ZUC-256 参数要求
        byte[] key_256 = Hex.decode("00112233445566778899aabbccddeeff00112233445566778899aabbccddeeff"); // 256-bit (32字节)
        byte[] iv_256 = Hex.decode("00112233445566778899aabbbccddeef001122334455665566");  // 184-bit (25字节) 初始向量长度为184bit分布到25个字节中，前面17个初始向量为8bit字节，后面8个初始向量为6bit字节（占据一个字节的低6位）。
        byte[] plaintext_256 = "Hello ZUC-256!".getBytes();

        // 加密
        byte[] ciphertext_256 = zuc256Encrypt(key_256, iv_256, plaintext_256);
        System.out.println("Ciphertext: " + Hex.toHexString(ciphertext_256));

        // 解密
        byte[] decrypted_256 = zuc256Decrypt(key_256, iv_256, ciphertext_256);
        System.out.println("Decrypted: " + new String(decrypted_256));
    }
}
