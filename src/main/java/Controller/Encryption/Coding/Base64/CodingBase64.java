package Controller.Encryption.Coding.Base64;

import java.io.UnsupportedEncodingException;

public class CodingBase64 {
    public static String deCode(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs64 = java.util.Base64.getDecoder().decode(in);//获取用户输入字符通过base64加密，输出byte数组型值
        return new String(bs64, charset);//将byte数组转换成String输出
    }

    public static String enCode(String in, String charset) throws UnsupportedEncodingException {
        byte[] bytes = in.getBytes(charset);//将用户输入字符转换成byte（utf-8编码）
        return java.util.Base64.getEncoder().encodeToString(bytes);//输出值
    }

    public static String deCode(byte[] in, String charset) throws UnsupportedEncodingException {
        byte[] bs64 = java.util.Base64.getDecoder().decode(in) ;//获取用户输入字符通过base64加密，输出byte数组型值
        return new String(bs64, charset);//将byte数组转换成String输出
    }

    public static String enCode(byte[] in, String charset) throws UnsupportedEncodingException {
        byte[] bytes = in;//将用户输入字符转换成byte（utf-8编码）
        return java.util.Base64.getEncoder().encodeToString(bytes);//输出值
    }
}
