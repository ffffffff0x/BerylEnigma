package Controller.Encryption.Coding.BaseConversion;

import java.math.BigInteger;

public class Coding_BaseConversion {
    public static String conversion(String sourceNumber,int srcConv,int dstConv,String split)
    {
        String[] SourceNumb;
        StringBuilder sb = new StringBuilder();

        if(split.length()<=0){
            SourceNumb = sourceNumber.split(" ");
            split = " ";
        }else{
            SourceNumb = sourceNumber.split(split);
        }

        for (String a:SourceNumb) {
            sb.append(new BigInteger(a,srcConv).toString(dstConv));
            sb.append(split);
        }
        return sb.toString();
    }
}