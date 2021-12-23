package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.SymmetricEncryption.BlockCipher;

import java.util.Arrays;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-22 14:54
 **/

public class BlockCipherParameters {
    byte[] messageInput; //信息输入
    byte[] messageOutput; //信息输出
    byte[] key; //密钥
    byte[] iv; //IV(初始向量)
    String algorithm; //加密方式
    String encryptionMode; //加密模式
    String paddingMode; //填充模式
    String textEncoding; //文本编码

    public byte[] getMessageInput() {
        return messageInput;
    }

    public void setMessageInput(byte[] messageInput) {
        this.messageInput = messageInput;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getEncryptionMode() {
        return encryptionMode;
    }

    public void setEncryptionMode(String encryptionMode) {
        this.encryptionMode = encryptionMode;
    }

    public String getPaddingMode() {
        return paddingMode;
    }

    public void setPaddingMode(String paddingMode) {
        this.paddingMode = paddingMode;
    }

    public String getTextEncoding() {
        return textEncoding;
    }

    public void setTextEncoding(String textEncoding) {
        this.textEncoding = textEncoding;
    }

    public byte[] getMessageOutput() {
        return messageOutput;
    }

    public void setMessageOutput(byte[] messageOutput) {
        this.messageOutput = messageOutput;
    }

    @Override
    public String toString() {
        return "BlockCipherParameters{" +
                "messageInput=" + Arrays.toString(messageInput) +
                ", messageOutput=" + Arrays.toString(messageOutput) +
                ", key=" + Arrays.toString(key) +
                ", iv=" + Arrays.toString(iv) +
                ", algorithm='" + algorithm + '\'' +
                ", encryptionMode='" + encryptionMode + '\'' +
                ", paddingMode='" + paddingMode + '\'' +
                ", textEncoding='" + textEncoding + '\'' +
                '}';
    }
}
