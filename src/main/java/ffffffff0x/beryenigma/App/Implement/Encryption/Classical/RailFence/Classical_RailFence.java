package ffffffff0x.beryenigma.App.Implement.Encryption.Classical.RailFence;

import java.util.ArrayList;

/**
 * @author RyuZU
 */
public class Classical_RailFence {
    public static String encode(String source, int quantity)
    {
        source = source.replace(" ","");
        char[] str = source.toCharArray();
        String[] srt= new String[quantity];
        int i = 0;
        for (char s:str)
        {
            srt[i] += String.valueOf(s);
            if(i<quantity-1)
            {
                i++;
            }
            else
            {
                i=0;
            }
        }
        StringBuilder sn = new StringBuilder();
        for (String n:srt) {
            sn.append(n);
        }
        return sn.toString().replace("null","");
    }

    public static String decode(String source, int quantity)
    {

        int i = 0;
        char[] str = source.toCharArray();
        String[] srt = new String[source.length()/quantity];
        StringBuilder ec = new StringBuilder();
        StringBuilder rc = new StringBuilder();
        if(source.length()%quantity==0) {
            for (char s:str) {
                srt[i] += String.valueOf(s);
                if(i<(source.length()/quantity)-1) {
                    i++;
                }
                else {
                    i=0;
                }
            }
        }
        else{
            int j = source.length()%quantity;

            for (int k = 1; k < j+1; k++) {
                ec.append(str[((source.length()/quantity+1)*k)-1]);
                str[((source.length()/quantity+1)*k)-1] = ' ';
            }

            for(char s:str) {
                rc.append(s);
            }
            str = rc.toString().replace(" ","").toCharArray();

            for (char s:str) {
                srt[i] += String.valueOf(s);
                if(i<(source.length()/quantity)-1)
                {
                    i++;
                }
                else
                {
                    i=0;
                }
            }
        }

        StringBuilder sn = new StringBuilder();
        for (String l:srt) {
            sn.append(l);
        }
        sn = new StringBuilder(sn.toString().replace("null", "") + ec.toString());
        return sn.toString();
    }

    private static int check(int line,int sit,int ssit,int key){
        if(line==1 ||line==key)
        {
            return sit+(key-1)*2;
        } else {
            if(ssit%2==1){
                return sit+(key-line)*2;
            } else {
                return sit+2*(line-1);
            }
        }
    }

    public static String decodeTypeW(String msg,int key) {
        String[] ans = new String[msg.length() + 1];
        int i;
        int len = msg.length();
        String[] str = msg.split("");
        int line=1;
        int sit=1;
        int ssit=1;

        //把密文按顺序安排到原来明文的位置上
        for(i=1;i<=len;i++) {
            ans[sit]=str[i-1];
            sit=check(line,sit,ssit,key);
            ssit++;
            if(sit>len)//超过长度了这个位置肯定不存在字符
            {
                line++;
                sit = line;//因为每一行第一个字符的位置恰好与行数的数字一样
                ssit = 1;
            }
        }

        StringBuilder result = new StringBuilder();
        for(i=1;i<=len;i++){
            result.append(ans[i]);
//            System.out.printf("%s",ans[i]);//cout<<ans;应该也行，没改
        }
        return result.toString();
    }

    public static String encodeTypeW(String msg,int key) {
        ArrayList<StringBuilder> listSB = new ArrayList<>();
        for (int i = 0; i < key; i++) {
            listSB.add(new StringBuilder());
        }
        int count = 0;
        for (String a:msg.split("")) {
            if (count < key){
                listSB.get(count).append(a);
            }else {
                listSB.get(key-2-(count-key)).append(a);
            }
            count ++;
            if (count >= key + key-2){
                count = 0;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder stringBuilder : listSB) {
            result.append(stringBuilder.toString());
        }

        return result.toString();
    }
}
