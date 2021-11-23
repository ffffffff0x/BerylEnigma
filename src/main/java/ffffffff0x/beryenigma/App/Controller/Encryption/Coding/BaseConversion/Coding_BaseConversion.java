package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseConversion;

import java.math.BigInteger;

public class Coding_BaseConversion {
    public static String conversion(String sourceNumber,int srcConv,int dstConv,String split) {
        String[] SourceNumb;
        StringBuilder sb = new StringBuilder();

        if(split.length()<=0){
            SourceNumb = sourceNumber.split(" ");
            split = " ";
        }else{
            SourceNumb = sourceNumber.split(split);
        }

        for (String a:SourceNumb) {
            String bi = new BigInteger(a,srcConv).toString(dstConv);
            if (dstConv == 2 && bi.length() < 8) {
                String zero = "0";
                sb.append(zero.repeat(8-bi.length())).append(bi);
            }else {
                sb.append(bi);
            }
            sb.append(split);
        }
        return sb.toString();
    }
}