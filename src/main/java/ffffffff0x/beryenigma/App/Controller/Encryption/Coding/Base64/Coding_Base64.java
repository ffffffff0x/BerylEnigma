package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author RyuZU
 */
public class Coding_Base64 {

    public static String encodeToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public static String decodeToString(String in, String charset) throws UnsupportedEncodingException {
        //获取用户输入字符通过base64加密，输出byte数组型值
        byte[] bs64 = java.util.Base64.getDecoder().decode(in);
        //将byte数组转换成String输出
        return new String(bs64, charset);
    }

    public static String encodeToString(byte[] in) {
        return java.util.Base64.getEncoder().encodeToString(in);
    }

    public static String decodeToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //获取用户输入字符通过base64加密，输出byte数组型值
        byte[] bs64 = java.util.Base64.getDecoder().decode(in);
        //将byte数组转换成String输出
        return new String(bs64, charset);
    }

    public static byte[] encode(byte[] in) {
        return java.util.Base64.getEncoder().encode(in);
    }

    public static byte[] decode(byte[] in){
        return java.util.Base64.getDecoder().decode(in);
    }

    public static String urlEncodeToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return java.util.Base64.getUrlEncoder().encodeToString(bytes);
    }

    public static String urlDecodeToString(String in, String charset) throws UnsupportedEncodingException {
        //获取用户输入字符通过base64加密，输出byte数组型值
        byte[] bs64 = java.util.Base64.getUrlDecoder().decode(in);
        //将byte数组转换成String输出
        return new String(bs64, charset);
    }

    public static byte[] urlEncode(byte[] in){
        return java.util.Base64.getUrlEncoder().encode(in);
    }

    public static byte[] urlDecode(byte[] in){
        return java.util.Base64.getUrlDecoder().decode(in);
    }

    public static String encodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        if("\\n".equals(split)){
            split = "\n";
        }

        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(encodeToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String decodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        if("\\n".equals(split)){
            split = "\n";
        }

        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(decodeToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String urlEncodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        if("\\n".equals(split)){
            split = "\n";
        }

        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(urlEncodeToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String urlDecodeSplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        if("\\n".equals(split)){
            split = "\n";
        }

        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(urlDecodeToString(message, charset)).append(split);
        }

        return result.toString();
    }
}
