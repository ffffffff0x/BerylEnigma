package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding;

import java.io.UnsupportedEncodingException;
import static ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseEncoding.impl.Base85.*;

/**
 * @author: RyuZUSUNC
 * @create: 2021-11-24 16:06
 **/

public class Coding_Base85 {
    //
    public static byte[] encodeAscii85(byte[] in) {
        return getAscii85Encoder().encode(in);
    }

    public static byte[] encodeRfc1924(byte[] in) {
        return getRfc1924Encoder().encode(in);
    }

    public static byte[] encodeZ85(byte[] in) {
        return getZ85Encoder().encode(in);
    }

    public static byte[] decodeAscii85(byte[] in) {
        return getAscii85Decoder().decode(in);
    }

    public static byte[] decodeRfc1924(byte[] in) {
        return getRfc1924Decoder().decode(in);
    }

    public static byte[] decodeZ85(byte[] in) {
        return getZ85Decoder().decode(in);
    }

    //
    public static String encodeAscii85ToString(byte[] in) {
        byte[] encoded = encodeAscii85(in);
        return new String(encoded);
    }

    public static String encodeRfc1924ToString(byte[] in) {
        byte[] encoded = encodeRfc1924(in);
        return new String(encoded);
    }

    public static String encodeZ85ToString(byte[] in) {
        byte[] encoded = encodeZ85(in);
        return new String(encoded);
    }

    public static String decodeAscii85ToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //将byte数组转换成String输出
        return new String(decodeAscii85(in), charset);
    }

    public static String decodeRfc1924ToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //将byte数组转换成String输出
        return new String(decodeRfc1924(in), charset);
    }

    public static String decodeZ85ToString(byte[] in, String charset) throws UnsupportedEncodingException {
        //将byte数组转换成String输出
        return new String(decodeZ85(in), charset);
    }

    //
    public static String encodeAscii85ToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return encodeAscii85ToString(bytes);
    }

    public static String encodeRfc1924ToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return encodeRfc1924ToString(bytes);
    }

    public static String encodeZ85ToString(String in, String charset) throws UnsupportedEncodingException {
        //将用户输入字符转换成byte
        byte[] bytes = in.getBytes(charset);
        return encodeZ85ToString(bytes);
    }

    public static String decodeAscii85ToString(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs = in.getBytes(charset);
        //将byte数组转换成String输出
        return new String(decodeAscii85(bs), charset);
    }

    public static String decodeRfc1924ToString(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs = in.getBytes(charset);
        //将byte数组转换成String输出
        return new String(decodeRfc1924(bs), charset);
    }

    public static String decodeZ85ToString(String in, String charset) throws UnsupportedEncodingException {
        byte[] bs = in.getBytes(charset);
        //将byte数组转换成String输出
        return new String(decodeZ85(bs), charset);
    }

    //
    public static String encodeAscii85SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(encodeAscii85ToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String encodeRfc1924SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(encodeRfc1924ToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String encodeZ85SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(encodeZ85ToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String decodeAscii85SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(decodeAscii85ToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String decodeRfc1924SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(decodeRfc1924ToString(message, charset)).append(split);
        }

        return result.toString();
    }

    public static String decodeZ85SplitToString(String in,String charset,String split) throws UnsupportedEncodingException {
        String[] allMessage = in.split(split);
        StringBuilder result = new StringBuilder();

        for (String message:allMessage) {
            result.append(decodeZ85ToString(message, charset)).append(split);
        }

        return result.toString();
    }

}
