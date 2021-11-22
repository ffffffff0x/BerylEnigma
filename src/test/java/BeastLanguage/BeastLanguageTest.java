package BeastLanguage;

/**
 * @author: RyuZUSUNC
 * @create: 2021-04-27 15:09
 **/

public class BeastLanguageTest {
    private static final char[] bd = {22007, 21596, 21834, '~'};

    public static String toBeast(String tf) {
        return HexToBeast(ToHex(tf));
    }

    public static String fromBeast(String tf) {
        return FromHex(BeastToHex(tf));
    }

    public static void setBeastChars(char cf0, char cf1, char cf2, char cf3) {
        bd[0] = cf0;
        bd[1] = cf1;
        bd[2] = cf2;
        bd[3] = cf3;
    }

    public static char[] getBeastChars() {
        return new char[]{bd[0], bd[1], bd[2], bd[3]};
    }

    private static String ToHex(String gbString) {
        char[] utfBytes = gbString.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char c : utfBytes) {
            StringBuilder hexB = new StringBuilder(Integer.toHexString(c));
            while (hexB.length() < 4) {
                hexB.insert(0, "0");
            }
            unicodeBytes.append(hexB);
        }
        return unicodeBytes.toString();
    }

    private static String FromHex(String dataStr) {
        StringBuilder buffer = new StringBuilder();
        int start = 0;
        for (int end = 4; end <= dataStr.length(); end += 4) {
            buffer.append((char) Integer.parseInt(dataStr.substring(start, end), 16));
            start += 4;
        }
        return buffer.toString();
    }

    private static String HexToBeast(String tf) {
        char[] tfArray = tf.toCharArray();
        StringBuilder beast = new StringBuilder();
        for (int i = 0; i < tfArray.length; i++) {
            int k = Integer.valueOf(String.valueOf(tfArray[i]), 16) + (i % 16);
            if (k >= 16) {
                k -= 16;
            }
            beast.append(bd[k / 4]).append(bd[k % 4]);
        }
        return beast.toString();
    }

    private static String BeastToHex(String bf) {
        char[] bfArray = bf.toCharArray();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i <= bf.length() - 2; i += 2) {
            int pos1 = 0;
            int pos2 = 0;
            char c = bfArray[i];
            while (pos1 <= 3 && c != bd[pos1]) {
                pos1++;
            }
            char c2 = bf.charAt(i + 1);
            while (pos2 <= 3 && c2 != bd[pos2]) {
                pos2++;
            }
            int k = ((pos1 * 4) + pos2) - ((i / 2) % 16);
            if (k < 0) {
                k += 16;
            }
            out.append(Integer.toHexString(k));
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(toBeast("测试文本NMSL"));
        System.out.println(fromBeast("呜啊~啊呜啊~啊~嗷嗷嗷嗷~~嗷~啊~啊嗷啊嗷啊嗷啊呜嗷嗷嗷啊~嗷嗷嗷呜呜啊嗷呜呜嗷呜呜啊啊呜嗷啊嗷啊呜~~~啊~嗷~呜嗷啊啊~"));
    }
}
