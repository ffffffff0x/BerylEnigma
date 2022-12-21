package ffffffff0x.beryenigma.App.Implement.Tools.Practical.Timestamp;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;

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
    public static String timeStampToString(Long timestamp,String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()));
    }

    /**
     * 将格式化日期转换为时间戳
     *
     * @param time 固定格式文本日期
     * @param format 转换格式
     * @return 时间戳
     */
    public static Long stringToTimeStamp(String time,String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(time,formatter);
        ZonedDateTime zdt = ZonedDateTime.of(ldt,ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    public static void main(String[] args) {
        System.out.println(getNowTimeStamp());
        System.out.println(timeStampToString(getNowTimeStamp(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(stringToTimeStamp(timeStampToString(getNowTimeStamp(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(ZoneId.SHORT_IDS);
        System.out.println(ZoneId.systemDefault());
    }
}
