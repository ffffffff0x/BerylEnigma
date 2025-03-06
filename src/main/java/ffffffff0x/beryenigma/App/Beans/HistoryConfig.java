package ffffffff0x.beryenigma.App.Beans;

import com.google.gson.Gson;
import ffffffff0x.beryenigma.Kit.Utils.LogUtils;

/**
 * @author: RyuZUSUNC
 * @create: 2025/3/5 23:48
 **/
public class HistoryConfig {
    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HistoryConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
// 重写Object类的toString方法，用于返回对象的字符串表示
    public String toString() {
        return LogUtils.gson.toJson(this);
    }
}
