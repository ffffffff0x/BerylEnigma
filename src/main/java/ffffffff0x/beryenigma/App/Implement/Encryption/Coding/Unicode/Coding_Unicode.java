package ffffffff0x.beryenigma.App.Implement.Encryption.Coding.Unicode;

import org.apache.commons.text.translate.UnicodeEscaper;
import org.apache.commons.text.translate.UnicodeUnescaper;


public class Coding_Unicode {
    /**
     *
     * 字符串转换unicode
     * 0x20~0x7E 为 ASCII 可见字符，忽略对其编码
     * @param string
     * @return
     */
    public static String encode(String string) {
//        UnicodeEscaper ue = UnicodeEscaper.outsideOf(0x20, 0x7E);
        UnicodeEscaper ue = new UnicodeEscaper();
        return ue.translate(string);
    }

    /**
     *
     * unicode 转字符串
     * @param unicode
     * @return
     */
    public static String decode(String unicode) {
        UnicodeUnescaper uu = new UnicodeUnescaper();
        return uu.translate(unicode);
    }
}
