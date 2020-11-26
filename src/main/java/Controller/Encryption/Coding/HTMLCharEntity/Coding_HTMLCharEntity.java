package Controller.Encryption.Coding.HTMLCharEntity;

import Controller.Encryption.Coding.Unicode.Coding_Unicode;
import org.apache.commons.text.StringEscapeUtils;

import java.math.BigInteger;

/**
 * @author RyuZU
 */
public class Coding_HTMLCharEntity {
    //拼接字符串
    public static String encode(String source,String reference){
        String[] sourceSplit = source.split("");
        StringBuilder out = new StringBuilder();
        for (String a:sourceSplit) {
            out.append(HTMLCharEntityEncode(a,reference));
        }
        return out.toString();
    }
    //拼接字符串
    public static String decode(String source, String reference){
        String[] sourceSplit = source.split(";");
        StringBuilder out = new StringBuilder();
        for (String a:sourceSplit) {
            if(reference.equals("CER: &[char];")){
                out.append(HTMLCharEntityDecode(a+";",reference));
            }else {
                out.append(HTMLCharEntityDecode(a,reference));
            }
        }
        return out.toString();
    }
    //HTML实体编码
    private static String HTMLCharEntityEncode(String source, String reference){
        String out = Coding_Unicode.encode(source).substring(2);

        if(reference.equals("NCR: &#[dec];")) {
            return "&#"+(new BigInteger(out,16).toString(10))+";";
        }else if (reference.equals("NCR: &#x[hex];")){
            return "&#x"+out+";";
        }else {
            return StringEscapeUtils.escapeHtml4(source);
        }
    }
    //HTML实体编码
    private static String HTMLCharEntityDecode(String source, String reference){

        if(reference.equals("NCR: &#[dec];")) {
            return Coding_Unicode.decode("\\u"+new BigInteger(source.substring(2),10).toString(16));
        }else if (reference.equals("NCR: &#x[hex];")){
            return Coding_Unicode.decode("\\u"+source.substring(3));
        }else {
            return StringEscapeUtils.unescapeHtml4(source);
        }
    }
}