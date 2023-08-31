package ffffffff0x.beryenigma.Kit.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ffffffff0x.beryenigma.App.Beans.HistoryInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author: RyuZUSUNC
 * @create: 2023/6/24 12:36
 **/
public class LogUtils {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    // windows LOG存储目录
    private static final String LOGPATH_WIN = System.getProperty("user.dir") +"\\app\\log";
    // mac LOG存储目录
    private static final String LOGPATH_MAC = System.getProperty("user.home") + "/Library/BeryEnigma/log";
    // linux LOG存储目录
    private static final String LOGPATH_LINUX = System.getProperty("user.dir") + "/log";

    private static LinkedList<HistoryInfo> logArrayList = new LinkedList<>();

    private static final String LOGFILENAME = "BeryEnigmaLog.log";

    private static File logFile;

    static {
        initLogFile();
    }

    // 新增一条历史记录日志
    public static void addLog(HistoryInfo historyInfo) {
        logArrayList.add(historyInfo);
        saveLogsToDisk(gson.toJson(historyInfo));
    }

    public static void addLog(String input, String output, String moduleName, String actionName,String config) {
        HistoryInfo historyInfo = new HistoryInfo(input, output, moduleName, actionName, config);
        logArrayList.add(historyInfo);
        saveLogsToDisk(gson.toJson(historyInfo));
    }

    public static void addLog(String input, String output, String moduleName, String actionName) {
        HistoryInfo historyInfo = new HistoryInfo(input, output, moduleName, actionName);
        logArrayList.add(historyInfo);
        saveLogsToDisk(gson.toJson(historyInfo));
    }

    public static void addLog(String input, String output, String moduleName) {
        HistoryInfo historyInfo = new HistoryInfo(input, output, moduleName);
        logArrayList.add(historyInfo);
        saveLogsToDisk(gson.toJson(historyInfo));
    }

    // 删除所有日志
    public static void deleteLogs() {
        try {
            if (logFile.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (SecurityException e) {
            // 处理安全性异常，如没有权限删除文件
            System.err.println("Security exception: " + e.getMessage());
        }
    }

    // 将日志写入文件系统
    private static void saveLogsToDisk(String log) {
        // 创建 FileWriter 对象，并传入文件路径和 true 参数以进行续写
        // 使用 BufferedWriter 包装 FileWriter 以提高性能
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile.getPath(), true))){
            // 写入字符串内容
            bufferedWriter.write(log);
            bufferedWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化LOG文件
    private static void initLogFile() {
        // 加载对应系统的LOG文件
        if (Objects.equals(OSUtils.getOS(), OSUtils.OS_WIN)) {
            logFile = new File(LOGPATH_WIN + LOGFILENAME);
        }else if (Objects.equals(OSUtils.getOS(), OSUtils.OS_LINUX)) {
            logFile = new File(LOGPATH_LINUX + LOGFILENAME);
        }else {
            logFile = new File(LOGPATH_MAC + LOGFILENAME);
        }

        // 如果没有对应的文件就创建
        if (!FileUtils.checkFileExist(logFile)) {
            try {
                if (logFile.createNewFile()) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("File already exists or creation failed.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        File file = new File(LOGPATH_WIN + LOGFILENAME);
        System.out.println(file.getPath());
    }
}
