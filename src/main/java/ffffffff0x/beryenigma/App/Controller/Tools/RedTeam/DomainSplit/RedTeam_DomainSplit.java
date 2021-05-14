package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.DomainSplit;

import ffffffff0x.beryenigma.Kit.Utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 15:03
 **/

public class RedTeam_DomainSplit {
    public static Map<Integer, HashSet<String>> domainSplit(Object object){
        ArrayList<String[]> arrayList = new ArrayList<>();

        for (String domain: FileUtils.readLine(object)) {
            arrayList.add(split(domain));
        }

        Map<Integer, HashSet<String>> result = sortingDomain(arrayList);

        for (int i = 0; i < result.size(); i++) {
            System.out.println("level" + i);
            for (String a:result.get(i)) {
                System.out.println(a);
            }
        }

        return null;
    }

    private static String[] split(String domain){
        String protocol = "";
        StringBuilder stringBuilder = new StringBuilder();

        if(domain.contains("://")){
            protocol = domain.split("://")[0] + "://";
            domain = domain.split("://")[1];
        }

//        System.out.println(domain);
//        System.out.println(RegexStringNum(domain,"/"));
        String zero = "";

        for (String split:domain.split("/")) {
            zero = zero + split + "/";
            stringBuilder.append(protocol).append(zero).append("\n");
        }

        if (regexStringNum(domain,"/")==domain.split("/").length){
            return stringBuilder.toString().split("\n");
        }else{
            return stringBuilder.substring(0,stringBuilder.length()-2).split("\n");
        }
    }

    private static int regexStringNum(String targetStr, String patternStr) {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static Map<Integer, HashSet<String>> sortingDomain(ArrayList<String[]> arrayList){
        String[] temp;
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

        for (int i = 0; i < arrayList.get(1).length; i++) {
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
