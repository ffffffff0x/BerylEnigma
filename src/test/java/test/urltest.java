package test;

import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

/**
 * @author: RyuZUSUNC
 * @create: 2022/4/17 11:10
 **/
public class urltest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        URLCodec urlCodec = new URLCodec();
        String test = "qwe=+/ 123跟";
        byte[] t = URLCodec.encodeUrl(new BitSet(),test.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(t,StandardCharsets.UTF_8));
        System.out.println(urlCodec.encode(test,"UTF-8"));
    }
}
