package Controller.Encryption.Coding.ASCII;

public class CodingASCII {
    public static String enCode(String Source, String Split, int PMNumber)
    {
        if(Split.equals(""))
        {
            Split = " ";
        }

        StringBuilder ASCIIL = new StringBuilder();//结果保存
        StringBuilder ssvb = new StringBuilder();//输入值
        String[] ss = Source.split("");
        for (String ik : ss) {
            ssvb.append(ik);
        }
        char[] chars = ssvb.toString().toCharArray();//输入值保存为char数组
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                ASCIIL.append(Integer.valueOf(chars[i])+PMNumber).append(Split);
            }
            else {
                ASCIIL.append(Integer.valueOf(chars[i])+PMNumber);
            }
        }//ASCII转换
        return ASCIIL.toString();
    }

    public static String deCode(String a, String s)
    {
        String out;//输出
        if(s.equals(""))
        {
            out = "解密请输入分隔符";
        }
        else
        {
            StringBuilder sbu = new StringBuilder();
            String[] chars = a.split(s);//输入值切片
            for (String aChar : chars) {
                sbu.append((char) Integer.parseInt(aChar));
            }//ASCII转换
            out = sbu.toString();
        }
        return out;
    }
}
