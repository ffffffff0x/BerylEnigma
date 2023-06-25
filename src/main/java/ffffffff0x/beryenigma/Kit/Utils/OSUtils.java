package ffffffff0x.beryenigma.Kit.Utils;

import java.util.Locale;

/**
 * @author: RyuZUSUNC
 * @create: 2023/6/24 12:29
 **/
public class OSUtils {
    public static final String OS_WIN = "win";
    public static final String OS_MAC = "mac";
    public static final String OS_LINUX = "linux";

    /**
     * 获取当前系统类型
     *
     * @return String 系统类型
     */
    public static String getOS() {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if(osName.startsWith(OS_WIN)){
            return OS_WIN;
        }else if (osName.startsWith(OS_LINUX)) {
            return OS_LINUX;
        }else if (osName.startsWith(OS_MAC)) {
            return OS_MAC;
        }else {
            return null;
        }
    }
}
