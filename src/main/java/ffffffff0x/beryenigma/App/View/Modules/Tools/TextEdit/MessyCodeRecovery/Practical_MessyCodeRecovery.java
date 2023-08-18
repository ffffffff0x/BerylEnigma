package ffffffff0x.beryenigma.App.View.Modules.Tools.TextEdit.MessyCodeRecovery;

import java.io.UnsupportedEncodingException;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-01-16 09:12
 **/

public class Practical_MessyCodeRecovery {
    public static String[] CHAR_ENCODE = new String[] {"Shift-Jis",
            "UTF-8","US-ASCII","GBK","ISO-8859-1","windows-1252",
            "GB2312","BIG5"};

    public static void recovery(String message) throws UnsupportedEncodingException {
        for (String s : CHAR_ENCODE) {
            for (String s1 : CHAR_ENCODE) {
                System.out.println(s + " To "+ s1 + ":  " + new String(message.getBytes(s),s1));
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        recovery("嵟弶偵偍撉傒偔偩偝偄");
    }
}
