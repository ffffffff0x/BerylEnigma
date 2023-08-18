package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.BaseEncoding.Base92;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author: RyuZUSUNC
 * @create: 2021-11-12 10:05
 **/

public class Base92Impl {
    private static Integer base92CHR(int val) {
        int tmp;
        if ((val < 0) || (val >= 91)) {
            return null;
        }
        if (val == 0) {
            return 33;
        }else if(val <= 61) {
            tmp = 35 + val - 1;
            return tmp;
        }else {
            tmp = 97 + val - 62;
            return tmp;
        }
    }

    private static Integer base92ORD(int val) {
        if (val == '!') {
            return 0;
        }else if (35 <= val && val <= 95) {
            return val - 35 + 1;
        }else if (97 <= val && val <= 125) {
            return val - 97 + 62;
        }
        return null;
    }

    private static String baseConversionBPadding(String sourceNumber, int srcConv, int dstConv,int padding) {
        StringBuilder sb = new StringBuilder();
        String bi = new BigInteger(sourceNumber,srcConv).toString(dstConv);
        if (dstConv == 2 && bi.length() < padding) {
            String zero = "0";
            sb.append(zero.repeat(padding - bi.length())).append(bi);
        }else {
            sb.append(bi);
        }
        return sb.toString();
    }

    public static byte[] encode(byte[] in) {
        StringBuilder bitStr = new StringBuilder();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        StringBuilder resultStr = new StringBuilder();
        int count = 0;
        while (bitStr.length() < 13 && count < in.length) {
            bitStr.append(baseConversionBPadding(String.valueOf(in[count] & 0xff),10,2,8));
            count++;
        }
        while (bitStr.length() > 13 || count < in.length) {
            int temp = Integer.parseInt(baseConversionBPadding(bitStr.substring(0,13),2,10,8));
            byteArrayOutputStream.write(base92CHR(temp / 91));
            byteArrayOutputStream.write(base92CHR(temp % 91));
//            resultStr.append(base92CHR(temp / 91));
//            resultStr.append(base92CHR(temp % 91));
            bitStr = new StringBuilder(bitStr.substring(13, bitStr.length()));
            while (bitStr.length() < 13 && count < in.length) {
                bitStr.append(baseConversionBPadding(String.valueOf(in[count] & 0xff),10,2,8));
                count++;
            }
        }
        if (bitStr.length() > 0) {
            if (bitStr.length() < 7) {
                bitStr.append("0".repeat(6 - bitStr.length()));
                byteArrayOutputStream.write(base92CHR(Integer.parseInt(baseConversionBPadding(bitStr.toString(),2,10,8))));
            } else {
                bitStr.append("0".repeat(13 - bitStr.length()));
                int i = Integer.parseInt(baseConversionBPadding(bitStr.toString(),2,10,8));
                byteArrayOutputStream.write(base92CHR(i / 91));
                byteArrayOutputStream.write(base92CHR(i % 91));
                //                resultStr.append(base92CHR(i / 91));
//                resultStr.append(base92CHR(i % 91));
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decode(byte[] in) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder bitStr = new StringBuilder();

        for (int i = 0; i < in.length / 2; i++) {
            int x = base92ORD(in[2 * i]) * 91 + base92ORD(in[2 * i + 1]);
            bitStr.append(baseConversionBPadding(String.valueOf(x),10,2,13));
            while (8 <= bitStr.length()) {
                int temp = Integer.parseInt(baseConversionBPadding(bitStr.substring(0,8),2,10,8));
                byteArrayOutputStream.write(temp);
                bitStr = new StringBuilder(bitStr.substring(8,bitStr.length()));
            }
        }
        if (in.length % 2 ==1) {
            int x = base92ORD(in[in.length - 1]);
            String temp = baseConversionBPadding(String.valueOf(x),10,2,6);
            bitStr.append(temp);
            while (8 <= bitStr.length()) {
                int temp1 = Integer.parseInt(baseConversionBPadding(bitStr.substring(0,8),2,10,8));
                byteArrayOutputStream.write(temp1);
                bitStr = new StringBuilder(bitStr.substring(8,bitStr.length()));
            }
        }
        return byteArrayOutputStream.toByteArray();
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

//    public static void main(String[] args) throws IOException {
//        String test = "test";
//        String testz = "中文";
//
//        byte[] btest = test.getBytes(StandardCharsets.UTF_8);
//        byte[] btestz = testz.getBytes(StandardCharsets.UTF_8);
//
//        System.out.println(new String(encode("中文".getBytes(StandardCharsets.UTF_8)),"UTF-8"));
//        System.out.println(new String(decode("sI)\\X;9f".getBytes(StandardCharsets.UTF_8)),"UTF-8"));
//    }
}
