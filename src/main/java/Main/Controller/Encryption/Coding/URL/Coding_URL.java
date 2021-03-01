package Main.Controller.Encryption.Coding.URL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Coding_URL {
    public static String decode(String in, String charset) throws UnsupportedEncodingException {
        return URLDecoder.decode(in,charset);
    }

    public static String encode(String in, String charset) throws UnsupportedEncodingException {
        return URLEncoder.encode(in,charset).replaceAll("\\+","%20");
    }
}
