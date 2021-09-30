package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.ReverseShellGenerator;

/**
 * @author: RyuZUSUNC
 * @create: 2021/9/27 22:05
 **/
public class ReverseShellDataBean {
    private String ip;
    private String port;
    private String shellType;
    private String shell;
    private String listenerType;

    public ReverseShellDataBean(String ip, String port, String shellType, String shell, String listenerType) {
        this.ip = ip;
        this.port = port;
        this.shellType = shellType;
        this.shell = shell;
        this.listenerType = listenerType;
    }

    @Override
    public String toString() {
        return "ReverseShellDataBean{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", shellType='" + shellType + '\'' +
                ", shell='" + shell + '\'' +
                ", listenerType='" + listenerType + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getShellType() {
        return shellType;
    }

    public void setShellType(String shellType) {
        this.shellType = shellType;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getListenerType() {
        return listenerType;
    }

    public void setListenerType(String listenerType) {
        this.listenerType = listenerType;
    }
}
