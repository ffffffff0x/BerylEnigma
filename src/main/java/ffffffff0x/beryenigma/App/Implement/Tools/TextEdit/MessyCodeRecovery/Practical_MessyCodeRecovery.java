package ffffffff0x.beryenigma.App.Implement.Tools.TextEdit.MessyCodeRecovery;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-01-16 09:12
 **/

public class Practical_MessyCodeRecovery {
    public static String[] CHARENCODE = new String[] {"Shift_Jis",
            "UTF_8","US_ASCII","GBK","ISO_8859_1","windows-1252",
            "GB2312","BIG5"};

    public static void recovery(String message) throws UnsupportedEncodingException {
        System.out.println(new String(message.getBytes("Shift_Jis"),StandardCharsets.UTF_8));
        System.out.println(new String(message.getBytes("Shift_Jis"),StandardCharsets.US_ASCII));
        System.out.println(new String(message.getBytes("Shift_Jis"),"GBK"));
        System.out.println(new String(message.getBytes("Shift_Jis"), StandardCharsets.ISO_8859_1));
        System.out.println(new String(message.getBytes("Shift_Jis"), "windows-1252"));
        System.out.println(new String(message.getBytes("Shift_Jis"), "GB2312"));
        System.out.println(new String(message.getBytes("Shift_Jis"), "BIG5"));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        recovery("嵟弶偵偍撉傒偔偩偝偄");
    }
}
