package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.HTMLCharEntity;

import org.apache.commons.text.StringEscapeUtils;

import java.math.BigInteger;

/**
 * @author RyuZU
 */
public class Coding_HTMLCharEntity {
    private static final String REFERENCE_DEC = "NCR: &#[dec];";
    private static final String REFERENCE_HEX = "NCR: &#x[hex];";

    //拼接字符串
    public static String encode(String source,String reference){
        String[] sourceSplit = source.split("");
        StringBuilder out = new StringBuilder();
        if(REFERENCE_DEC.equals(reference)) {
            for (String a:sourceSplit) {
                out.append("&#").append(new BigInteger(unicodeEncode(a).substring(2), 16).toString(10)).append(";");
            }
        }else if (REFERENCE_HEX.equals(reference)){
            for (String a:sourceSplit) {
                out.append("&#x").append(unicodeEncode(a).substring(2)).append(";");
            }
        }else {
            for (String a:sourceSplit) {
                out.append(StringEscapeUtils.escapeHtml4(a));
            }
        }
        return out.toString();
    }
    //拼接字符串
    public static String decode(String source, String reference){
        String[] sourceSplit = source.split(";");
        StringBuilder out = new StringBuilder();

        if(REFERENCE_DEC.equals(reference)) {
            for (String a:sourceSplit) {
                out.append(unicodeDecode("\\u"+new BigInteger(a.substring(2),10).toString(16)));
            }
        }else if (REFERENCE_HEX.equals(reference)){
            for (String a:sourceSplit) {
                out.append(unicodeDecode("\\u"+a.substring(3)));
            }
        }else {
            for (String a:sourceSplit) {
                out.append(StringEscapeUtils.unescapeHtml4(a+";"));
            }
        }
        return out.toString();
    }

   private static String unicodeEncode(String string) {
        StringBuilder unico = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            unico.append("\\u");
            for (int j = 0; j < 4-Integer.toHexString(c).length(); j++) {
                unico.append("0");
            }
            // 转换为unicode
            unico.append(Integer.toHexString(c));
        }
        return unico.toString();
    }

    private static String unicodeDecode(String unicode) {
        StringBuilder str = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            str.append((char) data);
        }
        return str.toString();
    }
}