package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.XOR;

/**
 * @author: RyuZUSUNC
 * @create: 2021/10/4 13:12
 **/
public class Coding_XOR {
    /**
     * 异或算法加密/解密
     *
     * @param data 数据（密文/明文）
     * @param key 密钥
     * @return 返回解密/加密后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return data;
        }

        byte[] result = new byte[data.length];

        // 使用密钥字节数组循环加密或解密
        for (int i = 0; i < data.length; i++) {
            // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
            result[i] = (byte) (data[i] ^ key[i % key.length] ^ (i & 0xFF));
        }

        return result;
    }
}
