package Main.Controller.Encryption.Classical.CaesarCipher;

/**
 * @author: RyuZUSUNC
 * @create: 2021-03-02 15:15
 **/

public class Classical_CaesarCipher {
    public static String encode(String message,int keys){
        StringBuffer eStr = new StringBuffer();
        try{
            char[] messageChar = message.toCharArray();
            char temp = ' ';

            for (int i = 0; i < message.length(); i++) {
                if (messageChar[i] >= 'a' && messageChar[i] <= 'z') {
                    temp = (char) ((messageChar[i] - 'a' + keys) % 26 + 'a');
                }
                if(messageChar[i] >= 'A' && messageChar[i] <= 'Z'){
                    temp = (char) ((messageChar[i] - 'A' + keys) % 26 + 'A');
                }
                eStr.append(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return eStr.toString();
    }

    public static String decode(){
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encode("",3));
    }
}