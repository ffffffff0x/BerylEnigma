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
    // windows 配置存储目录
    private static final String CONFIGPATH_WIN = System.getProperty("user.dir") +"\\app";
    // mac 配置存储目录
    private static final String CONFIGPATH_MAC = System.getProperty("user.home") + "/Library/BeryEnigma";
    // linux 配置存储目录
    private static final String CONFIGPATH_LINUX = System.getProperty("user.dir");

    private static final String DEFAUTL_CONFIG_CLASSPATH = "/config/mainConfig.properties";
    private static final String CONFIG_COMMENTS = "ConfigFile";


    /**
     * 根据当前系统类型返回配置路径
     *
     * @param osNow 当前系统类型
     * @return String 配置路径
     */
    public static String getConfigPath(String osNow) {
        if(osNow.startsWith(OSUtils.OS_WIN)){
            return CONFIGPATH_WIN;
        }else if (osNow.startsWith(OSUtils.OS_LINUX)) {
            return CONFIGPATH_LINUX;
        }else if (osNow.startsWith(OSUtils.OS_MAC)) {
            return CONFIGPATH_MAC;
        }else {
            return null;
        }
    }

    /**
     * 获取本地存储的配置文件
     *
     * @param configPath 配置文件路径
     * @return Properties 配置文件
     * @throws IOException 文件异常
     */
    public static Properties getLocalConfig(String configPath) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(configPath));
        return properties;
    }

    /**
     * 获取默认的配置文件
     *
     * @return Properties 默认配置文件
     * @throws IOException 文件异常
     */
    public static Properties getDefaultConfig() throws IOException {
        Properties properties = new Properties();
        properties.load(ConfigUtils.class.getResourceAsStream(DEFAUTL_CONFIG_CLASSPATH));
        return properties;
    }

    /**
     * 根据默认配置文件创建本地配置文件
     *
     * @param filePath 本地配置文件目录
     * @throws IOException 文件异常
     */
    public static void createConfigFile(String filePath) throws IOException {
        Properties properties = new Properties();
        properties.load(ConfigUtils.class.getResourceAsStream(DEFAUTL_CONFIG_CLASSPATH));
        properties.store(new FileOutputStream(filePath),CONFIG_COMMENTS);
    }

    /**
     * 修改本地配置文件
     *
     * @param configKey 配置名 key
     * @param configValue 配置值 value
     */
    public static void editConfigFile(String configKey,String configValue) {
        Init.MAIN_CONFIG.put(configKey,configValue);
    }

    /**
     * 将本地配置文件写入存储
     *
     * @param filePath 配置文件路径
     * @throws IOException 文件异常
     */
    public static void saveConfigFile(String filePath) throws IOException {
        Init.MAIN_CONFIG.store(new FileOutputStream(filePath),CONFIG_COMMENTS);
    }
}
