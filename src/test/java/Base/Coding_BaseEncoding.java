package Base;

/**
 * @author: RyuZUSUNC
 * @create: 2021/10/12 20:46
 **/
public class Coding_BaseEncoding {
    public static final AsciiRadixCoder base58 = AsciiRadixCoder.of("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz");
    public static final AsciiRadixCoder base62 = AsciiRadixCoder.of("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

}
