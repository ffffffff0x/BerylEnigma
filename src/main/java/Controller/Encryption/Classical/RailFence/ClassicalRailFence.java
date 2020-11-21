package Controller.Encryption.Classical.RailFence;

/**
 * @author RyuZU
 */
public class ClassicalRailFence {
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
}
