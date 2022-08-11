package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.HEXCoder;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class Coding_HEXCoder {
    public static String encode(String message, String split, String charset) throws UnsupportedEncodingException {
        //接收转换结果
        String hexString;
        //java特殊转义字符
        String[] escapeArray = {"\b","\t","\n","\f","\r"};
        //校验参数是否包含特殊转义字符
        boolean flag = false;
        //迭代
        for(String escapeStr : escapeArray)
        {
            message = message.replace(escapeStr,"");
        }
        //16进制字符
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F',};
        StringBuilder sb = new StringBuilder();
        //String ---> byte[]
        byte[] bs = message.getBytes(charset);
        int bit;
        int count = bs.length;
        for (byte b : bs) {
            count--;
            if ("\\x".equals(split)|| "0x".equals(split)) {
                sb.append(split);
                bit = (b & 0x0f0) >> 4;
                sb.append(hexArray[bit]);
                bit = b & 0x0f;
                sb.append(hexArray[bit]);
            } else if (Objects.equals(split, "\\n")) {
                bit = (b & 0x0f0) >> 4;
                sb.append(hexArray[bit]);
                bit = b & 0x0f;
                sb.append(hexArray[bit]);
                if(count>0){
                    sb.append("\n");
                }
            } else {
                bit = (b & 0x0f0) >> 4;
                sb.append(hexArray[bit]);
                bit = b & 0x0f;
                sb.append(hexArray[bit]);
                if(count>0){
                    sb.append(split);
                }
            }
        }
        hexString = sb.toString();
        return hexString;
    }

    public static String decode(String message, String split, String charset) throws UnsupportedEncodingException {
        //接收结果
        String result;
        //转换大写
        message = message.toUpperCase();
        //16进制字符
        String hexDigital = "0123456789ABCDEF";
        String ika;

        if(Objects.equals(split, "0x"))
        {
            ika = message.replace("0X", "");
        }
        else if(Objects.equals(split, "\\x"))
        {
            ika = message.replace("\\X","");
        }
        else
        {
            StringBuilder ssvb = new StringBuilder();
            String[] ss = message.split(split);
            for (String ik : ss) {
                ssvb.append(ik);
            }
            ika = ssvb.toString();
        }

        char[] hexs = ika.toCharArray();
        //能被16整除一定可以被2整除
        byte[] bytes = new byte[ika.length()/2];
        int n;

        for (int j = 0; j < bytes.length; j++) {
            n = hexDigital.indexOf(hexs[2 * j]) * 16 + hexDigital.indexOf(hexs[2 * j + 1]);
            bytes[j] = (byte)(n & 0xff);
        }

        result = new String(bytes, charset);

        return result;
    }

    public static byte[] encode(byte[] message) {
        return encodeToString(message).getBytes();
    }

    public static String encodeToString(byte[] message) {
        //16进制字符
        char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F',};
        StringBuilder sb = new StringBuilder();
        int bit;
        for (byte b : message) {
            bit = (b & 0x0f0) >> 4;
            sb.append(hexArray[bit]);
            bit = b & 0x0f;
            sb.append(hexArray[bit]);
        }
        return sb.toString();
    }

    public static byte[] decode(byte[] message) {
        //16进制字符
        String hexDigital = "0123456789ABCDEF";
        String ika;

        ika = new String(message);

        char[] hexs = ika.toCharArray();
        //能被16整除一定可以被2整除
        byte[] bytes = new byte[ika.length()/2];
        int n;

        for (int j = 0; j < bytes.length; j++) {
            n = hexDigital.indexOf(hexs[2 * j]) * 16 + hexDigital.indexOf(hexs[2 * j + 1]);
            bytes[j] = (byte)(n & 0xff);
        }

        return bytes;
    }
}
