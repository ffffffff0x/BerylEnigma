package ffffffff0x.beryenigma.Kit.Utils;

import ffffffff0x.beryenigma.App.Beans.HistoryInfo;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author: RyuZUSUNC
 * @create: 2023/6/24 12:36
 **/
public class LogUtils {
    // windows LOG存储目录
    private static final String LOGPATH_WIN = System.getProperty("user.dir") +"\\app\\log";
    // mac LOG存储目录
    private static final String LOGPATH_MAC = System.getProperty("user.home") + "/Library/BeryEnigma/log";
    // linux LOG存储目录
    private static final String LOGPATH_LINUX = System.getProperty("user.dir") + "/log";

    private static LinkedList<HistoryInfo> logArrayList = new LinkedList<>();

    private static final String LOGFILENAME = "BeryEnigmaLog.log";

    private static File logFile;

    public LogUtils() {

    }

    // 新增一条历史记录日志
    public static void addLog(HistoryInfo historyInfo) {
        logArrayList.add(historyInfo);

        // TODO: 2023/6/25
    }

    // 删除所有日志
    public static void deleteLogs() {
        // TODO: 2023/6/25
    }

    private static void saveLogsToDisk() {

    }

    private static void initLogFile() {
        if (Objects.equals(OSUtils.getOS(), OSUtils.OS_WIN)) {
            logFile = new File(LOGPATH_WIN + LOGFILENAME);
        }else if (Objects.equals(OSUtils.getOS(), OSUtils.OS_LINUX)) {
            logFile = new File(LOGPATH_LINUX + LOGFILENAME);
        }else {
            logFile = new File(LOGPATH_MAC + LOGFILENAME);
        }

        if (!FileUtils.checkFileExist(logFile)) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // TODO: 2023/8/29
    }

    public static void main(String[] args) {
        File file = new File(LOGPATH_WIN + LOGFILENAME);
        System.out.println(file.getPath());
    }
}
