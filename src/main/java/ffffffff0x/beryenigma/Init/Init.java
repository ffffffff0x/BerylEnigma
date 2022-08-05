package ffffffff0x.beryenigma.Init;

import ffffffff0x.beryenigma.Kit.Utils.ConfigUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Init {
    public static final String CONFIG_FILENAME = "BerylEnigmaConfig";
    public static String CONFIGFILEPATH_NOW;
    public static String CONFIGPATH_NOW;
    public static String OS_NOW;
    public static Properties MAIN_CONFIG;
    private static final Properties DEFAULT_CONFIG;

    public static ResourceBundle languageResourceBundle = ResourceBundle.getBundle("Language");

    static {
        OS_NOW = ConfigUtils.getOS();
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

    private static void loadConfig() throws IOException {
        if (ConfigUtils.checkFolderExist(CONFIGPATH_NOW)) {
            if (ConfigUtils.checkFileExist(CONFIGFILEPATH_NOW)) {
                MAIN_CONFIG = ConfigUtils.getLocalConfig(CONFIGFILEPATH_NOW);
            }else {
                ConfigUtils.createConfigFile(CONFIGFILEPATH_NOW);
                MAIN_CONFIG = ConfigUtils.getLocalConfig(CONFIGFILEPATH_NOW);
            }
        } else {
            MAIN_CONFIG = DEFAULT_CONFIG;
        }
    }

    public static String getConfig(String configName) {
        if (MAIN_CONFIG.containsKey(configName)) {
            return MAIN_CONFIG.getProperty(configName);
        } else {
            return DEFAULT_CONFIG.getProperty(configName);
        }
    }
}
