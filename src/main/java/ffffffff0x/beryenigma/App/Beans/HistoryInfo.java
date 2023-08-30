package ffffffff0x.beryenigma.App.Beans;

/**
 * 日志/历史记录格式
 *
 * @author: RyuZUSUNC
 * @create: 2023/6/24 13:10
 **/
public class HistoryInfo {
    // 时间戳
    Long timestamp;
    // 输入
    String input;
    // 输出
    String output;
    // 使用功能模块
    String module;
    // 功能模块操作
    String action;
    // 功能模块配置
    String config;

    public HistoryInfo(Long timestamp, String input, String output, String module, String action, String config) {
        this.timestamp = timestamp;
        this.input = input;
        this.output = output;
        this.module = module;
        this.action = action;
        this.config = config;
    }

    public HistoryInfo(Long timestamp, String input, String output, String module, String action) {
        this.timestamp = timestamp;
        this.input = input;
        this.output = output;
        this.module = module;
        this.action = action;
    }

    public HistoryInfo(String input, String output, String module, String action, String config) {
        this.timestamp = System.currentTimeMillis();
        this.input = input;
        this.output = output;
        this.module = module;
        this.action = action;
        this.config = config;
    }

    public HistoryInfo(String input, String output, String module, String action) {
        this.timestamp = System.currentTimeMillis();
        this.input = input;
        this.output = output;
        this.module = module;
        this.action = action;
    }

    public HistoryInfo(String input, String output, String module) {
        this.timestamp = System.currentTimeMillis();
        this.input = input;
        this.output = output;
        this.module = module;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "timestamp='" + timestamp + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", module='" + module + '\'' +
                ", action='" + action + '\'' +
                ", config='" + config + '\'' +
                '}';
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
