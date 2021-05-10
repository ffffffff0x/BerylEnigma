package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.TargetClassification;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-06 11:11
 **/

public class TargetFinishingBean {
    String allUrl;
    String allIP;
    String allIPPort;
    int numUrl;
    int numIP;
    int numIPPort;
    int numduplication;
    int numoriginal;
    String all;
    Map<String,String> allmap = new HashMap<>();

    public Map<String, String> getAllmap() {
        allmap.put("IP.txt",allIP);
        allmap.put("URL.txt",allUrl);
        allmap.put("IPPort.txt",allIPPort);
        return allmap;
    }

    public int getNumduplication() {
        return numduplication;
    }

    public void setNumduplication(int numduplication) {
        this.numduplication = numduplication;
    }

    public int getNumoriginal() {
        return numoriginal;
    }

    public void setNumoriginal(int numoriginal) {
        this.numoriginal = numoriginal;
    }

    public void setAllmap(Map<String, String> allmap) {
        this.allmap = allmap;
    }

    public String getAllUrl() {
        return allUrl;
    }

    public void setAllUrl(String allUrl) {
        this.allUrl = allUrl;
    }

    public String getAllIP() {
        return allIP;
    }

    public void setAllIP(String allIP) {
        this.allIP = allIP;
    }

    public String getAllIPPort() {
        return allIPPort;
    }

    public void setAllIPPort(String allIPPort) {
        this.allIPPort = allIPPort;
    }

    public int getNumUrl() {
        return numUrl;
    }

    public void setNumUrl(int numUrl) {
        this.numUrl = numUrl;
    }

    public int getNumIP() {
        return numIP;
    }

    public void setNumIP(int numIP) {
        this.numIP = numIP;
    }

    public int getNumIPPort() {
        return numIPPort;
    }

    public void setNumIPPort(int numIPPort) {
        this.numIPPort = numIPPort;
    }

    public String getAll() {
        all = allIP + allUrl + allIPPort;
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
