package Main.Controller.Encryption.Classical.ROT;

/**
 * @author RyuZU
 */
public class Classical_ROT {
    public static String encode(String in, String rotnum)
    {
        rotnum = rotnum.replace("ROT","");
        //定义空String值
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            //获取String字符中某一个位置的字符并赋值给c
            char c = in.charAt(i);
            if ((c >= 'A') && (c <= 'Z')) {
                c += Integer.parseInt(rotnum);
                if (c > 'Z') {
                    c -= 26;
                }
            } else if ((c >= 'a') && (c <= 'z')) {
                c += Integer.parseInt(rotnum);
                if (c > 'z') {
                    c -= 26;
                }
            } else if ((c >= '0') && (c <= '9')) {
                c += Integer.parseInt(rotnum)-8;
                if (c > '9') {
                    c -= 10;
                }
            }
            s.append(c);
        }//ROT算法，类似凯撒加密
        return s.toString();
    }
}
