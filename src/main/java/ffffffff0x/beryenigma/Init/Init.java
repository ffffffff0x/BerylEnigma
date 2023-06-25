package ffffffff0x.beryenigma.Init;

import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;
import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import ffffffff0x.beryenigma.Kit.Utils.OSUtils;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Init {
    public static final String CONFIG_FILENAME = "BerylEnigmaConfig";
    public static String CONFIGFILEPATH_NOW;
    public static String CONFIGPATH_NOW;
    public static String OS_NOW;
    public static Properties MAIN_CONFIG;
    private static final Properties DEFAULT_CONFIG;
    private static ResourceBundle languageResourceBundle;

    /* 程序初始化 */
    static {
        OS_NOW = OSUtils.getOS();
        if (OS_NOW != null) {
            CONFIGPATH_NOW = ConfigUtils.getConfigPath(OS_NOW);
            CONFIGFILEPATH_NOW = CONFIGPATH_NOW + "/" + CONFIG_FILENAME;
        }
        try {
            DEFAULT_CONFIG = ConfigUtils.getDefaultConfig();
            loadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载本地配置文件，如果没有就加载默认配置文件并创建本地配置文件
     *
     * @throws IOException 文件系统异常
     */
    private static void loadConfig() throws IOException {
        if (FileUtils.checkFolderExist(CONFIGPATH_NOW)) {
            if (FileUtils.checkFileExist(CONFIGFILEPATH_NOW)) {
                MAIN_CONFIG = ConfigUtils.getLocalConfig(CONFIGFILEPATH_NOW);
            }else {
                ConfigUtils.createConfigFile(CONFIGFILEPATH_NOW);
                MAIN_CONFIG = ConfigUtils.getLocalConfig(CONFIGFILEPATH_NOW);
            }
        } else {
            MAIN_CONFIG = DEFAULT_CONFIG;
        }
    }

    /**
     * 获取配置，如果没有就从默认配置文件中获取
     *
     * @param configName KeyName
     * @return 根据 KeyName 获取的 Value
     */
    public static String getConfig(String configName) {
        if (MAIN_CONFIG.containsKey(configName)) {
            return MAIN_CONFIG.getProperty(configName);
        } else {
            return DEFAULT_CONFIG.getProperty(configName);
        }
    }

    /**
     * 获取当前语言环境的词
     *
     * @param key 英文key
     * @return string
     */
    public static String getLanguage(String key) {
        try {
            return languageResourceBundle.getString(key);
        }catch (MissingResourceException e) {
            return key;
        }
    }

    public static ResourceBundle getLanguageResourceBundle() {
        languageResourceBundle = ResourceBundle.getBundle("Language");
        return languageResourceBundle;
    }
}
