package ffffffff0x.beryenigma.App.View.Modules.Encryption.Coding.URL;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;

public class URLImpl {
    private static final URLCodec urlCodec = new URLCodec();

    public static String decode(String in, String charset) throws DecoderException, UnsupportedEncodingException {
        return urlCodec.decode(in,charset);
    }

    public static String encode(String in, String charset) throws UnsupportedEncodingException {
        return urlCodec.encode(in,charset).replaceAll("\\+","%20");
    }

    public static String encodeAll(String in, String charset) throws UnsupportedEncodingException {
        byte[] t = in.getBytes(charset);
        return new String(URLCodec.encodeUrl(new BitSet(),t),charset).replaceAll("\\+","%20");
    }
}
