package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.BaseEncoding;

import org.apache.commons.codec.binary.Base16;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021-11-04 16:10
 **/

public class Coding_Base16 {
    public static Base16 Base16 = new Base16();

    public static byte[] encode(byte[] in) {
        return Base16.encode(in);
    }

    public static byte[] decode(byte[] in) {
        return Base16.decode(in);
    }

    public static String encodeToString(byte[] in) {
        byte[] encoded = encode(in);
        return new String(encoded);
    }

    public static String decodeToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //将byte数组转换成String输出
        return new String(decode(in), charset);
    }

    public static String encodeToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return encodeToString(bytes);
    }

    public static String decodeToString(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs16 = in.getBytes(charset);
        //将byte数组转换成String输出
        return new String(decode(bs16), charset);
    }

    public static String encodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(encodeToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String decodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(decodeToString(message, charset)).append(split);
        }

        return result.toString();
    }

}
