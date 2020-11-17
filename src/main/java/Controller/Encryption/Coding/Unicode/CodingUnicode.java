package Controller.Encryption.Coding.Unicode;

public class CodingUnicode {
    /**
     *
     * 字符串转换unicode
     * @param string
     * @return
     */
    public static String encode(String string) {
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

    /**
     *
     * unicode 转字符串
     * @param unicode
     * @return
     */
    public static String decode(String unicode) {
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
