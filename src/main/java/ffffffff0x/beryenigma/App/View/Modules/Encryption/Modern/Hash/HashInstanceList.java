package ffffffff0x.beryenigma.App.View.Modules.Encryption.Modern.Hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-28 13:29
 **/

public class HashInstanceList {
    private final String b32 = "32";
    private final String b64 = "64";
    private final String b128 = "128";
    private final String b256 = "256";
    private final String b192 = "192";
    private final String b512 = "512";
    private final String b160 = "160";
    private final String b288 = "288";
    private final String b320 = "320";
    private final String b384 = "384";
    private final String b224 = "224";
    private final String b512l224 = "512/224";
    private final String b512l256 = "512/256";
    private final String b1024 = "1024";

    private HashMap<String, List> instanceList = new HashMap<>();

    public HashInstanceList() {
        instanceList.put("MD5", List.of(b128));
        instanceList.put("MD5-16", List.of(b128));
        instanceList.put("SHA1", List.of(b160));
        instanceList.put("SHA2", List.of(b224,b256,b384,b512,b512l224,b512l256));
        instanceList.put("SHA3", List.of(b224,b256,b384,b512));
        instanceList.put("SM3", List.of(b256));
        instanceList.put("MD4", List.of(b128));
        instanceList.put("MD2", List.of(b128));
        instanceList.put("Blake2b", List.of(b160,b256,b384,b512));
        instanceList.put("Blake2s", List.of(b160,b224,b256));
//        instanceList.put("CRC", List.of(b32,b64));
        instanceList.put("DSTU7564", List.of(b256,b384,b512));
        instanceList.put("GOST3411", List.of(b256));
        instanceList.put("GOST3411-2012", List.of(b256,b512));
        instanceList.put("Haraka", List.of(b256,b512));
        instanceList.put("Keccak", List.of(b224,b256,b288,b384,b512));
        instanceList.put("RIPEMD", List.of(b128,b160,b256,b320));
        instanceList.put("Skein-256", List.of(b160,b224,b256));
        instanceList.put("Skein-512", List.of(b128,b160,b224,b256,b384,b512));
        instanceList.put("Skein-1024", List.of(b384,b512,b1024));
        instanceList.put("Tiger", List.of(b192));
        instanceList.put("Whirlpool", List.of(b512));
    }

    public HashMap<String, List> getInstanceList() {
        return instanceList;
    }

    public List<String> getInstanceNameList() {
        List<String> result = Arrays.asList("MD5",
                "MD5-16",
                "SHA1",
                "SHA2",
                "SHA3",
                "SM3",
                "MD4",
                "MD2",
                "Blake2b",
                "Blake2s",
//                "CRC",
                "DSTU7564",
                "GOST3411",
                "GOST3411-2012",
                "Haraka",
                "Keccak",
                "RIPEMD",
                "Skein-256",
                "Skein-512",
                "Skein-1024",
                "Tiger",
                "Whirlpool");
        return result;
    }
}