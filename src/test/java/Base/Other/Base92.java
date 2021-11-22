package Base.Other;

import java.nio.charset.StandardCharsets;

/**
 * @author: RyuZUSUNC
 * @create: 2021-11-12 10:05
 **/

public class Base92 {
    private static BaseX baseX92 = new BaseX(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`1234567890-=~!@#$%^&*()_+[]{}|;':,./<>?");

    private Base92() {}

    public static String base92(byte[] bytes) {
        return baseX92.encode(bytes);
    }

    public static byte[] unBase92(String value) {
        return baseX92.decode(value);
    }

    public static void main(String[] args) {
        System.out.println(base92("hello world".getBytes(StandardCharsets.UTF_8)));
    }
}
