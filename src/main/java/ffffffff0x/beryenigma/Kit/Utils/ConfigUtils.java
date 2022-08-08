package ffffffff0x.beryenigma.Kit.Utils;

import ffffffff0x.beryenigma.Init.Init;

import javax.print.attribute.standard.Finishings;
import java.io.*;
import java.util.Locale;
import java.util.Properties;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2022-08-04 16:04
 **/

public class ConfigUtils {
    private static final String CONFIGPATH_WIN = System.getProperty("user.dir") +"\\app";
    private static final String CONFIGPATH_MAC = System.getProperty("user.home") + "/Library/BeryEnigma";
    private static final String CONFIGPATH_LINUX = System.getProperty("user.dir");

    private static final String OS_WIN = "win";
    private static final String OS_MAC = "mac";
    private static final String OS_LINUX = "linux";
    private static final String DEFAUTL_CONFIG_CLASSPATH = "/config/mainConfig.properties";
    private static final String CONFIG_COMMENTS = "ConfigFile";

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

    public static String getConfigPath(String osNow) {
        if(osNow.startsWith(OS_WIN)){
            return CONFIGPATH_WIN;
        }else if (osNow.startsWith(OS_LINUX)) {
            return CONFIGPATH_LINUX;
        }else if (osNow.startsWith(OS_MAC)) {
            return CONFIGPATH_MAC;
        }else {
            return null;
        }
    }

    public static Properties getLocalConfig(String configPath) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(configPath));
        return properties;
    }

    public static Properties getDefaultConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(ConfigUtils.class.getResourceAsStream(DEFAUTL_CONFIG_CLASSPATH));
        return properties;
    }

    public static boolean checkFileExist(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            return true;
        }
    }

    public static void createConfigFile(String filePath) throws IOException {
        Properties properties = new Properties();
        properties.load(ConfigUtils.class.getResourceAsStream(DEFAUTL_CONFIG_CLASSPATH));
        properties.store(new FileOutputStream(filePath),CONFIG_COMMENTS);
    }

    public static boolean checkFolderExist(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            return folder.mkdirs();
        } else {
            return true;
        }
    }

    public static void editConfigFile(String configKey,String configValue) {
        Init.MAIN_CONFIG.put(configKey,configValue);
    }

    public static void saveConfigFile(String filePath) throws IOException {
        Init.MAIN_CONFIG.store(new FileOutputStream(filePath),CONFIG_COMMENTS);
    }
}
