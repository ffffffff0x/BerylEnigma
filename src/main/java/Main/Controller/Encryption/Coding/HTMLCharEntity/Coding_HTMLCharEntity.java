package Main.Controller.Encryption.Coding.HTMLCharEntity;

import App.Controller.Encryption.Coding.Unicode.Coding_Unicode;
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
                out.append("&#").append(new BigInteger(Coding_Unicode.encode(a).substring(2), 16).toString(10)).append(";");
            }
        }else if (REFERENCE_HEX.equals(reference)){
            for (String a:sourceSplit) {
                out.append("&#x").append(Coding_Unicode.encode(a).substring(2)).append(";");
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
                out.append(Coding_Unicode.decode("\\u"+new BigInteger(a.substring(2),10).toString(16)));
            }
        }else if (REFERENCE_HEX.equals(reference)){
            for (String a:sourceSplit) {
                out.append(Coding_Unicode.decode("\\u"+a.substring(3)));
            }
        }else {
            for (String a:sourceSplit) {
                out.append(StringEscapeUtils.unescapeHtml4(a+";"));
            }
        }
        return out.toString();
    }
}