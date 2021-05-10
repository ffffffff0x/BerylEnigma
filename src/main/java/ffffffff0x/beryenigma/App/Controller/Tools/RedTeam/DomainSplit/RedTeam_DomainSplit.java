package ffffffff0x.beryenigma.App.Controller.Tools.RedTeam.DomainSplit;

import ffffffff0x.beryenigma.Kit.Utils.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-09 15:03
 **/

public class RedTeam_DomainSplit {
    public static Map<String,String> domainSplit(Object object){

        if(object instanceof File){
            for (String line: FileUtils.getFileLines((File)object)) {

            }
        }else {
            String text = (String)object;
            for (String line: text.split("\n")) {

            }
        }
        return null;
    }

    private static Set<String[]> domainSplit(String domain){
        HashSet<String[]> result = new HashSet<>();
        if(domain.contains("://")){
            String protocol = domain.split("://")[0] + "://";

        }else{

        }
        return null;
    }
}
