package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.DomainSplit;

import ffffffff0x.beryenigma.Kit.Utils.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 15:03
 **/

public class RedTeam_DomainSplit {
    public static Map<Integer, HashSet<String>> domainSplit(Object object){
        ArrayList<String[]> allURL = new ArrayList<>();
        HashSet<String> notDirURL = new HashSet<>();

        for (String domain:FileUtils.readLine(object)) {
            if(regexStringNum(domain,"/")==domain.split("/").length){
                allURL.add(split(domain));
            }else{
                notDirURL.add(domain);
                allURL.add(split(domain.substring(0,domain.lastIndexOf("/"))));
            }
        }

        Map<Integer, HashSet<String>> result = sortingDomain(allURL);
        result.put(-1,notDirURL);
        return result;
    }

    /**
     * 用于分割单个域名中的所有目录
     * @param domain
     * @return
     */
    private static String[] split(String domain){
        //协议头
        String protocol = "";
        //用来做返回值的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //判断目标是否含有协议头
        if(domain.contains("://")){
            protocol = domain.split("://")[0] + "://";
            domain = domain.split("://")[1];
        }

//        System.out.println(domain);
//        System.out.println(RegexStringNum(domain,"/"));

        //用来缓存每次拼接的结果
        String zero = "";

        //每次拼接下一级目录并保存至StringBuilder
        for (String split:domain.split("/")) {
            zero = zero + split + "/";
            stringBuilder.append(protocol).append(zero).append("\n");
        }

        //返回值判断URL末尾是目录还是文件
        return stringBuilder.toString().split("\n");
    }

    /**
     * 用来判断URL中出现"/"的次数
     * @param targetStr
     * @param patternStr
     * @return
     */
    private static int regexStringNum(String targetStr, String patternStr) {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        //找到的次数
        int count = 0;
        // 如果找到了
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * 用来对每一个URL分割的目标分类并去重
     * @param arrayList
     * @return
     */
    private static Map<Integer, HashSet<String>> sortingDomain(ArrayList<String[]> arrayList){
        String[] temp;
        //倒序排序,用来确定最大下标
        for (int i = 0; i < arrayList.size()-1; i++) {
            for(int j=0;j<arrayList.size()-i-1;j++){
                if(arrayList.get(j+1).length > arrayList.get(j).length){
                    temp = arrayList.get(j);
                    arrayList.set(j,arrayList.get(j+1));
                    arrayList.set(j+1,temp);
                }
            }
        }

        Map<Integer,HashSet<String>> result = new HashMap<>();
        //按级别分类重组,使用Hashset去重
        for (int i = 0; i < arrayList.get(0).length; i++) {
            HashSet<String> hashSet = new HashSet();
            for (String[] list:arrayList) {
                if(list.length > i){
                    hashSet.add(list[i]);
                }
            }
            result.put(i,hashSet);
        }
        return result;
    }
}
