package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.BaseEncoding;

import java.io.UnsupportedEncodingException;

/**
 * @author: RyuZUSUNC
 * @create: 2021/10/13 21:21
 **/
public class BaseEncoding {

    public static byte[] encode(byte[] in){
        return null;
    }

    public static byte[] decode(byte[] in){
        return null;
    }

    public static String encodeToString(byte[] in) {
        byte[] encoded = encode(in);
        assert encoded != null;
        return new String(encoded);
    }

    public static String decodeToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //将byte数组转换成String输出
        assert decode(in) != null;
        return new String(decode(in), charset);
    }

    public static String encodeToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return encodeToString(bytes);
    }

    public static String decodeToString(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs = in.getBytes(charset);
        //将byte数组转换成String输出
        assert decode(bs) != null;
        return new String(decode(bs), charset);
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
