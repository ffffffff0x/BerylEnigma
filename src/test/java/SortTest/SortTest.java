package SortTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: RyuZUSUNC
 * @create: 2021-04-30 15:13
 **/

public class SortTest {
    public static void main(String[] args) {
        System.out.println(ipportJudging("1.1.1.1"));
    }

    public static boolean ipJudging(String ip){
        //ip地址范围：(1~255).(0~255).(0~255).(0~255)
        String ipRegEx = "^([1-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))(\\.([0-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))){3}$";
        Pattern pattern = Pattern.compile(ipRegEx);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean ipportJudging(String ipport){
        //ipport范围：(1~255).(0~255).(0~255).(0~255):(0-65535)
        String domainRegEx = "^([1-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))(\\.([0-9]|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))){3}\\:\\d*$";
        Pattern pattern = Pattern.compile(domainRegEx);
        Matcher matcher = pattern.matcher(ipport);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void urlSort(StringBuilder source){
        String[] sortStr = new String[]{"http://www.ntcjjt.cn:5088/main/login.jsp","http://10.10.1.9:5088/main/login.jsp","http://www.ntgsjc.com","http://www.nttajl.com","http://ntwzy.com"};
        Arrays.sort(sortStr);
        for(int i=0;i<sortStr.length;++i){
            System.out.print(sortStr[i]+'\n');
        }
    }

    public static void ipSort(StringBuilder source){
        String ip_str = "192.168.10.34 127.0.0.1 3.3.3.3 105.70.11.55";

        // 为了让IP可以按字符串顺序比较，每一位需要0补充
        ip_str = ip_str.replaceAll("(\\d+)", "00$1");
        System.out.println(ip_str);

        // 然后每一个保留3位
        ip_str = ip_str.replaceAll("0*(\\d{3})", "$1");
        System.out.println(ip_str);

        // IP地址分割并排序
        String[] ips = ip_str.split(" +");
        Set<String> set = new TreeSet<>();
        Collections.addAll(set, ips);

        for (String ip : set) {
            System.out.println(ip.replaceAll("0*(\\d+)", "$1"));
        }
    }

}
