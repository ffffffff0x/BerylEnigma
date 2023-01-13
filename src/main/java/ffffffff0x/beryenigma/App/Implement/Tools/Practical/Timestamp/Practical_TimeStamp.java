package ffffffff0x.beryenigma.App.Implement.Tools.Practical.Timestamp;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author: RyuZUSUNC
 * @create: 2022/12/21 16:27
 **/
public class Practical_TimeStamp {
    /**
     * 获取当前毫秒级时间戳
     *
     * @return 毫秒级时间戳
     */
    public static Long getNowTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 将时间戳格式化为固定格式文本
     *
     * @param timestamp 时间戳
     * @param format 文本格式
     * @return 固定格式文本
     */
    public static String timeStampToString(Long timestamp,String format,ZoneId zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(Instant.ofEpochMilli(timestamp).atZone(zoneId));
    }

    /**
     * 将格式化日期转换为时间戳
     *
     * @param time 固定格式文本日期
     * @param format 转换格式
     * @return 时间戳
     */
    public static Long stringToTimeStamp(String time,String format,ZoneId zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(time,formatter);
        ZonedDateTime zdt = ZonedDateTime.of(ldt,zoneId);
        return zdt.toInstant().toEpochMilli();
    }

    /**
     * 获取所有时区信息
     */
    public static Map<String, String> getAllTimeZone() {
        return ZoneId.SHORT_IDS;
    }

    public static void main(String[] args) {
        System.out.println(getNowTimeStamp());
        System.out.println(timeStampToString(getNowTimeStamp(),"yyyy-MM-dd HH:mm:ss",ZoneId.systemDefault()));
        System.out.println(
                stringToTimeStamp(timeStampToString(getNowTimeStamp(),"yyyy-MM-dd HH:mm:ss",ZoneId.systemDefault()),
                "yyyy-MM-dd HH:mm:ss",
                ZoneId.systemDefault()));
        System.out.println(ZoneId.SHORT_IDS);
        System.out.println(ZoneId.systemDefault());
    }
}
