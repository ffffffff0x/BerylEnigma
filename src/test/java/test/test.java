package test;

import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.ASCII.Coding_ASCII;
import ffffffff0x.beryenigma.App.Controller.Encryption.Coding.BaseConversion.Coding_BaseConversion;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author: RyuZUSUNC
 * @create: 2021-12-01 14:51
 **/

public class test {
    private static String base92CHR(int val) {
        char tmp;
        if ((val < 0) || (val >= 91)) {
            return null;
        }
        if (val == 0) {
            return "!";
        }else if(val <= 61) {
            tmp = (char) (35 + val - 1);
            return String.valueOf(tmp);
        }else {
            tmp = (char) (97 + val - 62);
            return String.valueOf(tmp);
        }
    }

    private static Integer base92ORD(String val) {
        int num = val.charAt(0);
        if (val.equals("!")) {
            return 0;
        }else if (35 <= num && num <= 95) {
            return num - 35 + 1;
        }else if (97 <= num && num <= 125) {
            return num - 97 + 62;
        }
        return null;
    }

    public static String encode(String in) {
        String[] asciiStrList = Coding_ASCII.encode(in," ",0).split(" ");
        StringBuilder bitStr = new StringBuilder();
        StringBuilder resultStr = new StringBuilder();
        int count = 0;
        while (bitStr.length() < 13 && count < asciiStrList.length) {
            bitStr.append(Coding_BaseConversion.conversion(asciiStrList[count],10,2," ").replace(" ",""));
            count++;
        }
        while (bitStr.length() > 13 || count < asciiStrList.length) {
            int temp = Integer.parseInt(Coding_BaseConversion.conversion(bitStr.substring(0,13),2,10," ").replace(" ",""));
            resultStr.append(base92CHR(temp / 91));
            resultStr.append(base92CHR(temp % 91));
            bitStr = new StringBuilder(bitStr.substring(13, bitStr.length()));
            while (bitStr.length() < 13 && count < asciiStrList.length) {
                bitStr.append(Coding_BaseConversion.conversion(asciiStrList[count],10,2," ").replace(" ","").replace(" ",""));
                count++;
            }
        }
        if (bitStr.length() > 0) {
            if (bitStr.length() < 7) {
                bitStr.append("0".repeat(6 - bitStr.length()));
                resultStr.append(base92CHR(Integer.parseInt(Coding_BaseConversion.conversion(bitStr.toString(),2,10," ").replace(" ",""))));
            } else {
                bitStr.append("0".repeat(13 - bitStr.length()));
                int i = Integer.parseInt(Coding_BaseConversion.conversion(bitStr.toString(),2,10," ").replace(" ",""));
                resultStr.append(base92CHR(i / 91));
                resultStr.append(base92CHR(i % 91));
            }
        }
        System.out.println(resultStr);
        return resultStr.toString();
    }

    public static String decode(String in) {
        String[] asciiStrList = Coding_ASCII.encode(in," ",0).split(" ");
        StringBuilder bitStr = new StringBuilder();
        StringBuilder resultStr = new StringBuilder();
        if (in.equals("~")) {
            return "";
        }

        for (int i = 0; i < asciiStrList.length / 2; i++) {
            int x = base92ORD(asciiStrList[2 * i]) * 91 + base92ORD(asciiStrList[2 * i + 1]);
            bitStr.append(conversionB13(String.valueOf(x),10,2,"").replace(" ",""));
            while (8 <= bitStr.length()) {
                String temp = conversionB13(bitStr.substring(0,8),2,10," ").replace(" ","");
                resultStr.append(Coding_ASCII.deCode(temp," "));
                bitStr = new StringBuilder(bitStr.substring(8,bitStr.length()));
            }
        }
        if (asciiStrList.length % 2 ==1) {
            int x = base92ORD(asciiStrList[asciiStrList.length - 1]);
            String temp = conversionB6(String.valueOf(x),2,10," ").replace(" ","");
            bitStr.append(temp);
            while (8 <= bitStr.length()) {
                String temp1 = conversionB13(bitStr.substring(0,8),2,10," ").replace(" ","");
                resultStr.append(Coding_ASCII.deCode(temp1," "));
                bitStr = new StringBuilder(bitStr.substring(8,bitStr.length()));
            }
        }
        return resultStr.toString();
    }

    public static String conversionB13(String sourceNumber,int srcConv,int dstConv,String split) {
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
            if (dstConv == 2 && bi.length() < 13) {
                String zero = "0";
                sb.append(zero.repeat(13-bi.length())).append(bi);
            }else {
                sb.append(bi);
            }
            sb.append(split);
        }
        return sb.toString();
    }

    public static String conversionB6(String sourceNumber,int srcConv,int dstConv,String split) {
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
            if (dstConv == 2 && bi.length() < 6) {
                String zero = "0";
                sb.append(zero.repeat(6-bi.length())).append(bi);
            }else {
                sb.append(bi);
            }
            sb.append(split);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(encode("test"));
        System.out.println(decode("Jw_@V"));
    }
}

