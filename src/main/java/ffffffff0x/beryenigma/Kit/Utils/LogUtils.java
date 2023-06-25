package ffffffff0x.beryenigma.Kit.Utils;

import ffffffff0x.beryenigma.App.Beans.HistoryInfo;

import java.util.ArrayList;

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

    private static ArrayList<HistoryInfo> logArrayList = new ArrayList<>();

    // 新增一条历史记录日志
    public static void addLog(HistoryInfo historyInfo) {
        logArrayList.add(historyInfo);
        // TODO: 2023/6/25
    }

    // 删除所有日志
    public static void deleteLogs() {
        // TODO: 2023/6/25
    }


}
