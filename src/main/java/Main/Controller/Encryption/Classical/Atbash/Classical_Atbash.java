package Main.Controller.Encryption.Classical.Atbash;

/**
 * @author RyuZU
 */
public class Classical_Atbash {
    public static String encode(String source)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if(c>=65&&c<=90) {
                c = (char)(90-(c-65));
            }
            if(c>=97&&c<=122) {
                c = (char)(122-(c-97));
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
