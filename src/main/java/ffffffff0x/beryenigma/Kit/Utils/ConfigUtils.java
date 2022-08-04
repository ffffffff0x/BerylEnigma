package ffffffff0x.beryenigma.Kit.Utils;

import java.util.Locale;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-04 16:04
 **/

public class ConfigUtils {
    public static final String CONFIGPATH_WIN = System.getProperty("user.dir") +"\\app\\BerylEnigmaConfig";
    public static final String CONFIGPATH_MAC = "";
    public static final String CONFIGPATH_LINUX = "";

    public static final String OS_WIN = "win";
    public static final String OS_MAC = "mac";
    public static final String OS_LINUX = "linux";

    public static String CONFIGPATH_NOW;
    public static String OS_NOW;

    static {
        OS_NOW = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if(OS_NOW.startsWith(OS_WIN)){
            CONFIGPATH_NOW = CONFIGPATH_WIN;
        }else if (OS_NOW.startsWith(OS_LINUX)) {
            CONFIGPATH_NOW = CONFIGPATH_LINUX;
        }else if (OS_NOW.startsWith(OS_MAC)) {
            CONFIGPATH_NOW = CONFIGPATH_MAC;
        }else {
            CONFIGPATH_NOW = null;
        }
    }
}
