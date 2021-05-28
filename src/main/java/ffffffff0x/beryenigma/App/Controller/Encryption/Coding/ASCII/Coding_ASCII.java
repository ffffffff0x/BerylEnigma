package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.ASCII;

import javax.sound.midi.Soundbank;

public class Coding_ASCII {
    public static String encode(String Source, String Split, int PMNumber) {
        if(Split.equals(""))
        {
            Split = " ";
        }else if(Split.equals("\\n")){
            Split = "\n";
        }
        //结果保存
        StringBuilder ASCIIL = new StringBuilder();
        //输入值
        StringBuilder ssvb = new StringBuilder();
        String[] ss = Source.split("");
        for (String ik : ss) {
            ssvb.append(ik);
        }
        //输入值保存为char数组
        char[] chars = ssvb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                ASCIIL.append(Integer.valueOf(chars[i])+PMNumber).append(Split);
            } else {
                ASCIIL.append(Integer.valueOf(chars[i])+PMNumber);
            }
        }//ASCII转换
        return ASCIIL.toString();
    }

    public static String deCode(String a, String s) {
        //输出
        String out;
        if(s.equals("")){
            s = " ";
        }
        StringBuilder sbu = new StringBuilder();
        //输入值切片
        String[] chars = a.split(s);
        for (String aChar : chars) {
            sbu.append((char) Integer.parseInt(aChar));
        }//ASCII转换
        out = sbu.toString();
        return out;
    }
}
